package de.hub.emffrag;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import com.google.common.collect.Lists;

import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.internal.FStoreObject;
import de.hub.emffrag.internal.ObjectInputStream;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;

public class Fragmentation {
	private final IDataStore dataStore;
	private final int fragmentCacheSize;
	private final IDataMap<Long> fragmentDataStoreIndex;

	private Map<Integer, FStoreObject> fragments = new HashMap<Integer, FStoreObject>();
	
	public Fragmentation(IDataStore dataStore, int fragmentsCacheSize) {
		this.dataStore = dataStore;
		this.fragmentCacheSize = fragmentsCacheSize;
		this.fragmentDataStoreIndex = dataStore.getMap(("f_").getBytes(), LongKeyType.instance);
	}
	
	public FStoreObject getRoot() {
		return resolve(0);
	}
	
	public void setRoot(FStoreObject root) {
		if (!fragmentDataStoreIndex.exists((long)0)) {
			try {
				createFragment();
			} catch (RuntimeException e) {
				String msg = "IOException while creating the root fragment of " + this.toString() + ".";
				EmfFragActivator.instance.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		}
		root.fSetFragmentID(this, 0);
	}
	
	public FStoreObject resolve(int fragmentID) {
		FStoreObject fragmentRoot = fragments.get(fragmentID);
		if (fragmentRoot == null) {
			gc();
			if (fragmentDataStoreIndex.exists((long)fragmentID)) {
				InputStream inputStream = fragmentDataStoreIndex.openInputStream((long) fragmentID);
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream) {
					@Override
					protected EPackage getPackage(int packageID) {
						// TODO Auto-generated method stub
						return null;
					}				
				};
				fragmentRoot = objectInputStream.readFragment();
				fragmentRoot.fSetFragmentID(this, fragmentID);
				objectInputStream.close();
				fragments.put(fragmentID, fragmentRoot);
				return fragmentRoot;
			} else {
				return null;
			}
		} else {
			return fragmentRoot;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public FStoreObject resolve(FURI uri) {
		FStoreObject root = resolve(uri.fragment());
		FStoreObject object = root;
		Iterator<Integer> segmentIterator = Lists.reverse(uri.segment()).iterator();
		while(segmentIterator.hasNext()) {
			Integer index = segmentIterator.next();
			Integer featureID = segmentIterator.next();
			if (index == -1) {
				object = (FStoreObject) object.fGet(featureID);
			} else {
				object = ((List<FStoreObject>)object.fGet(featureID)).get(index);
			}			
		}
		
		return object;
	}
	
	private int createFragment() {
		long id = fragmentDataStoreIndex.add();
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("created ", Color.YELLOW) + 
				Ansi.format(Integer.toString((int)id), Color.values()[(int)(id % Color.values().length)]));		
		
		return (int)id;
	}
	
	private void deleteFragment(int id) {
		fragmentDataStoreIndex.remove((long)id);

		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("deleted ", Color.MAGENTA) + 
				Ansi.format(Integer.toString((int)id), Color.values()[(int)id % Color.values().length]));
	}

	public void onAddToFragmentation(FStoreObject fStoreObject) {		
		if (fStoreObject.fIsRoot()) {
			fStoreObject.fSetFragmentID(this, createFragment());
		}
		for (FStoreObject content: fStoreObject.fAllContents()) {
			if (content.fIsRoot()) {
				content.fSetFragmentID(this, createFragment());
			}	
		}
	}
	
	public void onRemoveFromFragmentation(FStoreObject fStoreObject) {
		if (fStoreObject.fIsRoot()) {
			deleteFragment(fStoreObject.fFragmentID());
			fStoreObject.fSetFragmentID(null, -1);
		}
		for (FStoreObject content: fStoreObject.fAllContents()) {
			if (content.fIsRoot()) {
				deleteFragment(fStoreObject.fFragmentID());
				content.fSetFragmentID(null, -1);
			}	
		}
	}
	
	private void gc() {
		if (fragments.size() > fragmentCacheSize) {
			// TODO
		}
	}
}
