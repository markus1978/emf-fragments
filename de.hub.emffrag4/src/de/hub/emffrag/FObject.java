package de.hub.emffrag;

import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

public class FObject extends EStoreEObjectImpl {

	private FStoreObject fStoreObject = null;

	public FObject() {
		eSetStore(FStore.transientObjectsStore);
	}

	public FStoreObject fStoreObject() {
		if (fStoreObject == null) {
			if (eClass() == null) {
				throw new IllegalStateException();
			}
			fStoreObject = new FStoreObject(eClass());
			((FStore) eStore()).registerProxy(this);
		}
		return fStoreObject;
	}

	public void fSetStoreObject(FStoreObject fStoreObject) {
		this.fStoreObject = fStoreObject;
	}

	protected boolean eIsCaching() {
		return false;
	}
}
