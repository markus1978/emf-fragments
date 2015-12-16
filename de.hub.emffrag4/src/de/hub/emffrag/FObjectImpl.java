package de.hub.emffrag;

import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

public class FObjectImpl extends EStoreEObjectImpl implements FObject {

	private FStoreObject fStoreObject = null;

	protected FObjectImpl() {
		eSetStore(Fragmentation.transientStore());
	}

	@Override
	public FStoreObject fStoreObject() {
		if (fStoreObject == null) {
			if (eClass() == null) {
				throw new IllegalStateException();
			}
			fStoreObject = new FStoreObject(eClass());
			((FStore) eStore()).fragmentation().registerNewObject(this);
		}
		return fStoreObject;
	}

	protected void fSetStoreObject(FStoreObject fStoreObject) {
		this.fStoreObject = fStoreObject;
	}

	protected boolean eIsCaching() {
		return false;
	}
}
