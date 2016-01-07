package de.hub.emffrag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.internal.FStoreFragmentation;
import de.hub.emffrag.internal.FStoreObject;

public class FragmentationImpl implements Fragmentation {
	private static final Map<FStoreFragmentation, Fragmentation> fragmentations = new HashMap<FStoreFragmentation, Fragmentation>();
	
	public static Fragmentation get(FStoreFragmentation fStoreFragmentation) {
		return fragmentations.get(fStoreFragmentation);
	}
	
	private final FStoreFragmentation fStoreFragmentation;

	public FragmentationImpl(List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		fStoreFragmentation = new FStoreFragmentation(packages, dataStore, fragmentsCacheSize);
		fragmentations.put(fStoreFragmentation, this);
	}

	@SuppressWarnings("unchecked")
	public <T extends FObject> T getRoot() {
		FStoreObject fStoreRoot = fStoreFragmentation.getRoot();
		if (fStoreRoot == null) {
			return null;
		} else {
			return (T) FStore.fINSTANCE.proxyManager.getFObject(fStoreRoot);
		}
	}
	
	public void setRoot(FObject root) {
		fStoreFragmentation.setRoot(root.fStoreObject());
	}

	public FStoreFragmentation getFStoreFragmentation() {
		return fStoreFragmentation;
	}

	@Override
	public void close() {		
		fStoreFragmentation.close();
		fragmentations.remove(fStoreFragmentation);
	}

	@Override
	public IDataStore getDataStore() {
		return fStoreFragmentation.getDataStore();
	}
}
