package de.hub.emffrag;

import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

import de.hub.emffrag.internal.FStore;
import de.hub.emffrag.internal.FStoreFragmentation;
import de.hub.emffrag.internal.FStoreObject;
import de.hub.emffrag.internal.FStoreObjectImpl;


public class FObjectImpl extends EStoreEObjectImpl implements FObject {

	private FStoreObject fStoreObject = null;

	protected FObjectImpl() {
		super(FStore.fINSTANCE);
		eContainer = null;
	}

	@Override
	public FStoreObject fStoreObject() {
		if (fStoreObject == null) {
			if (eClass() == null) {
				throw new IllegalStateException();
			}
			fStoreObject = new FStoreObjectImpl(eClass());
			fStoreObject.fMarkModified(true);
			access(fStoreObject);
			FStore.fINSTANCE.proxyManager.registerFObject(fStoreObject, this);
		} else if (fStoreObject.fIsProxy()) {
			fStoreObject = fStoreObject.fFragmentation().resolve(fStoreObject.fProxyURI());
		} else {
			access(fStoreObject.fRoot());
		}
		
		return fStoreObject;
	}
	
	private void access(FStoreObject fStoreObject) {
		FStoreFragmentation fragmentation = fStoreObject.fFragmentation();
		if (fragmentation != null) {
			fragmentation.access(fStoreObject);
		}
	}

	public void fSetStoreObject(FStoreObject fStoreObject) {
		this.fStoreObject = fStoreObject;
		eContainer = EUNINITIALIZED_CONTAINER;
	}

	protected boolean eIsCaching() {
		return false;
	}

	@Override
	public Fragmentation fFragmentation() {
		return FragmentationImpl.get(fStoreObject.fFragmentation());
	}
	
	@Override
	public String toString() {
		return fStoreObject().toString();
	}
}
