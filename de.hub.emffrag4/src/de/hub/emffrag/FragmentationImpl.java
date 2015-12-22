package de.hub.emffrag;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.internal.FStoreFragmentation;
import de.hub.emffrag.internal.FStoreObject;

public class FragmentationImpl implements Fragmentation {
	private final FStoreFragmentation fStoreFragmentation;

	public FragmentationImpl(List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		fStoreFragmentation = new FStoreFragmentation(packages, dataStore, fragmentsCacheSize);
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
	}
}
