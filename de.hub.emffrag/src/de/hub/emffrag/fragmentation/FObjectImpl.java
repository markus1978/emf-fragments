package de.hub.emffrag.fragmentation;

import java.lang.ref.WeakReference;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource.Internal;

public class FObjectImpl extends EStoreEObjectImpl {

	private FInternalObjectImpl internalObject;
	private WeakReference<InternalEObject> containerReference = null;

	public FObjectImpl() {
		eSetStore(FStoreImpl.getInstance());
		eContainerFeatureID = Integer.MAX_VALUE;
	}

	protected void fSetInternalObject(FInternalObjectImpl internalObject) {
		this.internalObject = internalObject;
	}

	public FInternalObjectImpl fInternalObject() {
		if (internalObject == null) {
			// This object was not yet added to a model
			internalObject = UserObjectsCache.newUserObjectsCache.createInternalObject(this);
		}

		internalObject.onAccess();
		
		return internalObject;
	}

	/**
	 * Changes the super class behavior so that the fragmented model is always
	 * returned as the objects resource.
	 */
	@Override
	public Internal eDirectResource() {
		return (Internal) fInternalObject().getFragmentation();
	}

	/**
	 * EStoreObjectImpl holds a strong java reference towards container. This is
	 * bad, since those cannot be differentiated between actual user references.
	 * We have to replace EBasicEObjectImpl.eContainer (modified by this method
	 * and eInitializeContainer()) with a java weak reference.
	 */
	@Override
	public InternalEObject eInternalContainer() {
		if (containerReference == null || eContainerFeatureID == Integer.MAX_VALUE) {
			eInitializeContainer();
			return containerReference.get();
		} else {
			InternalEObject container = containerReference.get();
			if (container == null) {
				eInitializeContainer();
				return containerReference.get();
			} else {
				return container;
			}
		}
	}

	/**
	 * See doc of {@link #eInternalContainer()}
	 */
	@Override
	protected void eInitializeContainer() {
		super.eInitializeContainer();
		containerReference = new WeakReference<InternalEObject>(eContainer);
		eContainer = EUNINITIALIZED_CONTAINER;
	}

	/**
	 * See doc of {@link #eInternalContainer()}
	 */
	@Override
	public int eContainerFeatureID() {
		if (containerReference == null || eContainerFeatureID == Integer.MAX_VALUE) {
			eInitializeContainer();
			return eContainerFeatureID;
		} else {
			InternalEObject container = containerReference.get();
			if (container == null) {
				eInitializeContainer();
			}
			return eContainerFeatureID;
		}
	}

	/**
	 * See doc of {@link #eInternalContainer()}.
	 */
	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		// Container are handled internally, externally there are not container
		// to change. This disables the according super class functionality.

		// update the weak reference that is used instead of EMF's hard
		// reference super.eBasicSetContainer(newContainer,
		// newContainerFeatureID);
		containerReference = new WeakReference<InternalEObject>(eContainer);
	}

	public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain msgs) {
		// Container are handled internally, externally there are not container
		// to change. This disables the according super class functionality.
		return msgs;
	}

	@Override
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		// disables superclass implementation to avoid instant remove through
		// double realization of containment
		return msgs;
	}

	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		// disables superclass implementation to avoid instant remove through
		// double realization of containment
		return msgs;
	}
}
