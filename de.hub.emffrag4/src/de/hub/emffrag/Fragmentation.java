package de.hub.emffrag;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.internal.FStoreFragmentation;
import de.hub.emffrag.internal.FStoreObject;

public class Fragmentation {
	private final FStoreFragmentation fStoreFragmentation;

	public Fragmentation(List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		fStoreFragmentation = new FStoreFragmentation(packages, dataStore, fragmentsCacheSize);
	}

	@SuppressWarnings("unchecked")
	public <T extends FObject> T getRoot() {
		FStoreObject fStoreRoot = fStoreFragmentation.getRoot();
		if (fStoreRoot == null) {
			return null;
		} else {
			return (T) FStore.fINSTANCE.proxify(fStoreRoot);
		}
	}

	/**
	 * Set the root object of the fragmentation. A fragmentation can only have
	 * one root object, which can be set exactly one time.
	 * 
	 * @param root The root object.
	 * @throws IllegalArgumentException if the fragmentation already has a root.
	 */
	public void setRoot(FObject root) {
		fStoreFragmentation.setRoot(root.fStoreObject());
	}
}
