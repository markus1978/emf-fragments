package de.hub.emffrag.internal;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.FragmentationUtil;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.internal.IDataMap;
import de.hub.emffrag.datastore.internal.LongKeyType;
import de.hub.jstattrack.TimeStatistic;
import de.hub.jstattrack.TimeStatistic.Timer;
import de.hub.jstattrack.services.Summary;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;

public class FStoreFragmentation {
	
	public static final TimeStatistic saveETStat = new TimeStatistic(TimeUnit.MICROSECONDS).with(Summary.class).register(FStoreFragmentation.class, "FragSaveET");
	public static final TimeStatistic loadETStat = new TimeStatistic(TimeUnit.MICROSECONDS).with(Summary.class).register(FStoreFragmentation.class, "FragLoadET");
	public static final TimeStatistic unloadETStat = new TimeStatistic(TimeUnit.MICROSECONDS).with(Summary.class).register(FStoreFragmentation.class, "FragUnloadET");
	
	private final IDataMap<Long> fragmentDataStoreIndex;
	private final IDataStore dataStore;

	private final List<EPackage> packages;
	private final LRUCache<Integer, FStoreObject> fragments;
	private FStoreObject lastAccessed = null;
	
	private final URI uri;
	private final FStoreFragmentationSet set;
	private boolean closed = false;
	
	public FStoreFragmentation(List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		this(null, null, packages, dataStore, fragmentsCacheSize);
	}
	
	public FStoreFragmentation(FStoreFragmentationSet set, URI uri, List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		this.set = set;
		this.uri = uri;
		this.fragments = new LRUCache<Integer, FStoreObject>(fragmentsCacheSize) {
			@Override
			protected void doRemove(FStoreObject value) {
				unloadFragment(value);
			}
		};
		this.dataStore = dataStore;
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
		return resolve(0, true);
	}
	
	public void setRoot(FStoreObject root) {
		FStoreFragmentation oldFragmentation = root.fFragmentation();
		FStoreFragmentation containerFragmentation = null;
		lock();
		if (oldFragmentation != null) {
			oldFragmentation.lock();
		}
		FStoreObject container = root.fContainer();
		if (container != null) {
			container = container.resolve(true);
			containerFragmentation = container.fFragmentation();
			containerFragmentation.lock();			
			container.fMarkModified(true);
		}
		if (oldFragmentation != null) {
			oldFragmentation.onRemoveFromFragmentation(root);			
		}
		try {
			if (!fragmentDataStoreIndex.exists((long)0)) {
				onAddToFragmentation(root);
			} else {
				throw new IllegalArgumentException("Fragmentation already has a root.");
			}
		} finally {
			unlock();
			if (oldFragmentation != null) {
				oldFragmentation.unlock();
			}
			if (containerFragmentation != null) {
				containerFragmentation.unlock();
			}
		}
	}
	
	private FStoreObject resolve(int fragmentID, boolean loadOnDemand) {
		FStoreObject fragmentRoot = fragments.get(fragmentID);
		if (fragmentRoot == null && loadOnDemand) {
			return loadFragment(fragmentID);
		} else {
			return fragmentRoot;
		}		
	}

	private FStoreObject loadFragment(final int fragmentID) {
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("<<< ", Color.GREEN) + 
				Ansi.format(uri != null ? uri.toString() + "/" + fragmentID : Integer.toString(fragmentID), Color.values()[(int)(fragmentID % Color.values().length)]));
		Preconditions.checkArgument(fragmentID >= 0);
		FStoreObject fragmentRoot;
		if (fragmentDataStoreIndex.exists((long)fragmentID)) {
			InputStream inputStream = fragmentDataStoreIndex.openInputStream((long) fragmentID);
			Timer timer = loadETStat.timer();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream,  getURI(), fragmentID) {
				@Override
				protected EPackage getPackage(int packageID) {
					return packages.get(packageID);
				}

				@Override
				protected FStoreObject createProxy(FURI uri, EClass eClass) {
					FStoreObjectImpl proxy = new FStoreObjectImpl(uri, eClass);
					URI fragmentationURI = uri.fragmentation();
					FStoreFragmentation fragmentation = null;
					if (fragmentationURI == null) {
						fragmentation = FStoreFragmentation.this;
					} else {
						fragmentation = set.getFragmentation(fragmentationURI);
					}
					proxy.fSetFragmentID(fragmentation, uri.fragment());
					return proxy;
				}

				@Override
				protected FStoreObject createObject(EClass eClass) {
					FStoreObjectImpl object = new FStoreObjectImpl(eClass);
					object.fSetFragmentID(FStoreFragmentation.this, fragmentID);
					return object;
				}				
			};
			fragmentRoot = objectInputStream.readFragment();
			fragmentRoot.fSetFragmentID(this, fragmentID);
			timer.track();
			objectInputStream.close();
			fragments.put(fragmentID, fragmentRoot);
			fragmentRoot.fMarkModified(false);
			return fragmentRoot;
		} else {
			return null;
		}
	}
	
	public void unloadFragment(FStoreObject fragmentRoot) {		
		Preconditions.checkArgument(fragmentRoot.fFragmentation() == this);
		Preconditions.checkArgument(fragments.get(fragmentRoot.fFragmentID()) != null);
		Preconditions.checkState(!closed);
		
		Timer timer = unloadETStat.timer();
		int fragmentID = fragmentRoot.fFragmentID();
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format(">>> ", Color.RED) + 
				Ansi.format(uri != null ? uri.toString() + "/" + fragmentID : Integer.toString(fragmentID), Color.values()[(int)(fragmentID % Color.values().length)]));
		if (fragmentRoot.fModified()) {
			saveFragment(fragmentRoot, true);
		} else {
			unloadFragmentContent(fragmentRoot, new FStreamURIImpl(getURI(), fragmentID));
		}
		fragments.remove(fragmentID);
		timer.track();
	}
	
	@SuppressWarnings("unchecked")
	private void unloadFragmentContent(FStoreObject fStoreObject, FStreamURIImpl uri) {		
		EClass fClass = fStoreObject.fClass();
		for(EReference reference: fClass.getEAllReferences()) {
			if (reference.isContainment() && !FragmentationUtil.isFragmenting(reference)) {
				if (fStoreObject.fIsSet(reference)) {
					if (reference.isMany()) {
						int index = 0;
						for (FStoreObject content: (List<FStoreObject>)fStoreObject.fGet(reference)) {
							uri.onDown(fClass.getFeatureID(reference), index++);
							unloadFragmentContent(content, uri);
							uri.onUp();
						}
					} else {
						uri.onDown(fClass.getFeatureID(reference), -1);
						unloadFragmentContent((FStoreObject) fStoreObject.fGet(reference), uri);
						uri.onUp();		
					}
				}
			}
		}
			
		fStoreObject.fUnload(uri);
	}
	
	private void saveFragment(FStoreObject fragmentRoot, boolean withUnload) {
		int fragmentID = fragmentRoot.fFragmentID();
		OutputStream outputStream = fragmentDataStoreIndex.openOutputStream((long) fragmentID);
		Timer timer = saveETStat.timer();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream, withUnload) {
			@Override
			protected int getPackageID(EPackage pkg) {
				return packages.indexOf(pkg);
			}			
		};
		objectOutputStream.writeFragment(fragmentRoot);
		timer.track();
		objectOutputStream.close();
		fragmentRoot.fMarkModified(false);
	}
	
	@SuppressWarnings("unchecked")
	public FStoreObject resolve(FURI uri, boolean loadOnDemand) {
		Preconditions.checkState(!closed);
		FStoreObject root = resolve(uri.fragment(), loadOnDemand);
		if (root == null) {
			return null;
		}
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
		long idAsLong = fragmentDataStoreIndex.add();
		int fragmentID = (int)idAsLong;
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("*** ", Color.YELLOW) + 
				Ansi.format(uri != null ? uri.toString() + "/" + fragmentID : Integer.toString(fragmentID), Color.values()[(int)(fragmentID % Color.values().length)]));		
		
		root.fSetFragmentID(this, fragmentID);
		root.fMarkModified(true);
		fragments.put(fragmentID, root);
		
		return fragmentID;
	}
	
	private void deleteFragment(FStoreObject root) {
		Preconditions.checkArgument(root.fFragmentation() == this);
		
		int fragmentID = root.fFragmentID();
		fragmentDataStoreIndex.remove((long)fragmentID);
		fragments.remove(root.fFragmentID());

		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("xxx ", Color.MAGENTA) + 
				Ansi.format(uri != null ? uri.toString() + "/" + fragmentID : Integer.toString(fragmentID), Color.values()[(int)(fragmentID % Color.values().length)]));
	}

	public void onAddToFragmentation(FStoreObject fStoreObject) {
		if (fStoreObject.fIsRoot()) {
			createFragment(fStoreObject);			
		}		
		for (FStoreObject content: fStoreObject.fAllContents(false)) {
			if (content.fIsRoot()) {
				createFragment(content);
			}	
		}
	}
	
	public void onRemoveFromFragmentation(FStoreObject fStoreObject) {
		if (fStoreObject.fIsRoot()) {
			deleteFragment(fStoreObject);
			fStoreObject.fSetFragmentID(null, -1);
		}
		for (FStoreObject content: fStoreObject.fAllContents(false)) {
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
		Preconditions.checkState(!closed);
		Collection<FStoreObject> fragmentRootsCopy = new ArrayList<FStoreObject>(fragments.values());
		for (FStoreObject root: fragmentRootsCopy) {
			unloadFragment(root);
		}
		FStore.fINSTANCE.proxyManager.removeProxies(uri);
		closed = true;
	}
	
	public void save() {
		Preconditions.checkState(!closed);
		for (FStoreObject fragmentRoot: fragments.values()) {
			saveFragment(fragmentRoot, false);
		}
	}

	public void access(FStoreObject fStoreObject) {
		FStoreObject fragment = fStoreObject.fRoot();
		if (lastAccessed != fragment) {
			lastAccessed = fragment;
			fragments.get(fragment.fFragmentID());
		}
	}

	public IDataStore getDataStore() {
		return dataStore;
	}

	public URI getURI() {
		return uri;
	}

	public void lock() {
		fragments.lock();
	}
	
	public void unlock() {
		fragments.unlock();
	}
	
	@Override
	public String toString() {
		if (uri != null) {
			return "F(" + uri + ")";
		} else {
			return "F";
		}
	}
}
