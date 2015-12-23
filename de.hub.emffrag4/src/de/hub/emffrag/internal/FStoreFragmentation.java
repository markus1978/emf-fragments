package de.hub.emffrag.internal;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.FURI;
import de.hub.emffrag.FragmentationUtil;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;

public class FStoreFragmentation {
	private final IDataMap<Long> fragmentDataStoreIndex;

	private final List<EPackage> packages;
	private final Map<Integer, FStoreObject> fragments;
	private FStoreObject lastAccessed = null;
	
	@SuppressWarnings("serial")
	public FStoreFragmentation(List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		if (fragmentsCacheSize > 0) {
			this.fragments = new LRUCache<Integer, FStoreObject>(fragmentsCacheSize) {
				@Override
				protected void onRemove(FStoreObject value) {
					unloadFragment(value);
				}
			};
		} else {
			this.fragments = new HashMap<Integer, FStoreObject>();
		}
		this.fragmentDataStoreIndex = dataStore.getMap(("f_").getBytes(), LongKeyType.instance);
		this.packages = new ArrayList<EPackage>(packages);
		this.packages.sort(new Comparator<EPackage>() {
			@Override
			public int compare(EPackage o1, EPackage o2) {
				return o1.getNsURI().compareTo(o2.getNsURI());
			}
		});
	}
	
	public FStoreObject getRoot() {
		return resolve(0);
	}
	
	public void setRoot(FStoreObject root) {
		if (!fragmentDataStoreIndex.exists((long)0)) {
			onAddToFragmentation(root);
		} else {
			throw new IllegalArgumentException("Fragmentation already has a root.");
		}		
	}
	
	public FStoreObject resolve(int fragmentID) {
		FStoreObject fragmentRoot = fragments.get(fragmentID);
		if (fragmentRoot == null) {
			return loadFragment(fragmentID);
		} else {
			return fragmentRoot;
		}		
	}

	public FStoreObject loadFragment(final int fragmentID) {
//		EmfFragActivator.instance.debug(
//				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
//				Ansi.format("load ", Color.GREEN) + 
//				Ansi.format("" + fragmentID, Color.values()[(int)(fragmentID % Color.values().length)]));
		Preconditions.checkArgument(fragmentID >= 0);
		FStoreObject fragmentRoot;
		if (fragmentDataStoreIndex.exists((long)fragmentID)) {
			InputStream inputStream = fragmentDataStoreIndex.openInputStream((long) fragmentID);
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream, fragmentID) {
				@Override
				protected EPackage getPackage(int packageID) {
					return packages.get(packageID);
				}

				@Override
				protected FStoreObject createProxy(FURI uri) {
					FStoreObjectImpl proxy = new FStoreObjectImpl(uri);
					proxy.fSetFragmentID(FStoreFragmentation.this, fragmentID);
					return proxy;
				}

				@Override
				protected FStoreObject createObject() {
					FStoreObjectImpl object = new FStoreObjectImpl();
					object.fSetFragmentID(FStoreFragmentation.this, fragmentID);
					return object;
				}				
			};
			fragmentRoot = objectInputStream.readFragment();
			fragmentRoot.fSetFragmentID(this, fragmentID);
			objectInputStream.close();
			fragments.put(fragmentID, fragmentRoot);
			fragmentRoot.fMarkModified(false);
			return fragmentRoot;
		} else {
			return null;
		}
	}
	
	public void unloadFragment(FStoreObject fragmentRoot) {
		int fragmentID = fragmentRoot.fFragmentID();
//		EmfFragActivator.instance.debug(
//				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
//				Ansi.format("load ", Color.RED) + 
//				Ansi.format(fragmentID + (fragmentRoot.fModified()?"*":""), Color.values()[(int)(fragmentID % Color.values().length)]));
		if (fragmentRoot.fModified()) {
			OutputStream outputStream = fragmentDataStoreIndex.openOutputStream((long) fragmentID);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream, true) {
				@Override
				protected int getPackageID(EPackage pkg) {
					return packages.indexOf(pkg);
				}			
			};
			objectOutputStream.writeFragment(fragmentRoot);
			objectOutputStream.close();
		} else {
			unloadFragmentContent(fragmentRoot, new FStreamURIImpl(fragmentID));
		}
		fragments.remove(fragmentID);
	}
	
	@SuppressWarnings("unchecked")
	private void unloadFragmentContent(FStoreObject fStoreObject, FStreamURIImpl uri) {		
		for(EReference reference: fStoreObject.fClass().getEAllReferences()) {
			if (reference.isContainment() && !FragmentationUtil.isFragmenting(reference)) {
				if (fStoreObject.fIsSet(reference)) {
					if (reference.isMany()) {
						int index = 0;
						for (FStoreObject content: (List<FStoreObject>)fStoreObject.fGet(reference)) {
							uri.onDown(reference.getFeatureID(), index++);
							unloadFragmentContent(content, uri);
							uri.onUp();
						}
					} else {
						uri.onDown(reference.getFeatureID(), -1);
						unloadFragmentContent((FStoreObject) fStoreObject.fGet(reference), uri);
						uri.onUp();		
					}
				}
			}
		}
			
		fStoreObject.fUnload(uri);
	}
	
	public void saveFragment(FStoreObject fragmentRoot) {
		int fragmentID = fragmentRoot.fFragmentID();
		OutputStream outputStream = fragmentDataStoreIndex.openOutputStream((long) fragmentID);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream, true) {
			@Override
			protected int getPackageID(EPackage pkg) {
				return packages.indexOf(pkg);
			}			
		};
		objectOutputStream.writeFragment(fragmentRoot);
		objectOutputStream.close();
		fragmentRoot.fMarkModified(false);
	}
	
	@SuppressWarnings("unchecked")
	public FStoreObject resolve(FURI uri) {
		FStoreObject root = resolve(uri.fragment());
		FStoreObject object = root;
		Iterator<Integer> segmentIterator = Lists.reverse(uri.segment()).iterator();
		while(segmentIterator.hasNext()) {
			Integer index = segmentIterator.next();
			Integer featureID = segmentIterator.next();
			EStructuralFeature feature = object.fClass().getEStructuralFeature(featureID);
			if (index == -1) {				
				object = (FStoreObject) object.fGet(feature);
			} else {
				object = ((List<FStoreObject>)object.fGet(feature)).get(index);
			}			
		}
		
		return object;
	}
	
	private int createFragment(FStoreObject root) {
		long id = fragmentDataStoreIndex.add();
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("created ", Color.YELLOW) + 
				Ansi.format(Integer.toString((int)id), Color.values()[(int)(id % Color.values().length)]));		
		
		root.fSetFragmentID(this, (int)id);
		fragments.put((int)id, root);
		
		return (int)id;
	}
	
	private void deleteFragment(FStoreObject root) {
		int id = root.fFragmentID();
		fragmentDataStoreIndex.remove((long)id);

		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("deleted ", Color.MAGENTA) + 
				Ansi.format(Integer.toString((int)id), Color.values()[(int)id % Color.values().length]));
	}

	public void onAddToFragmentation(FStoreObject fStoreObject) {	
		if (fragments instanceof LRUCache) {
			((LRUCache<?,?>)fragments).lock();
		}
		
		if (fStoreObject.fIsRoot()) {
			createFragment(fStoreObject);
		}
		for (FStoreObject content: fStoreObject.fAllContents()) {
			if (content.fIsRoot()) {
				createFragment(content);
			}	
		}
		
		if (fragments instanceof LRUCache) {
			((LRUCache<?,?>)fragments).unlock();
		}
	}
	
	public void onRemoveFromFragmentation(FStoreObject fStoreObject) {
		if (fStoreObject.fIsRoot()) {
			deleteFragment(fStoreObject);
			fStoreObject.fSetFragmentID(null, -1);
		}
		for (FStoreObject content: fStoreObject.fAllContents()) {
			if (content.fIsRoot()) {
				deleteFragment(fStoreObject);
				content.fSetFragmentID(null, -1);
			}	
		}
	}
	
	public int loadedFragments() {
		return fragments.size();
	}
	
	public void close() {
		Collection<FStoreObject> fragmentRootsCopy = new ArrayList<FStoreObject>(fragments.values());
		for (FStoreObject root: fragmentRootsCopy) {
			unloadFragment(root);
		}
	}

	public void access(FStoreObject fStoreObject) {
		FStoreObject fragment = fStoreObject.fRoot();
		if (lastAccessed != fragment) {
			lastAccessed = fragment;
			fragments.get(fragment.fFragmentID());
		}
	}
}
