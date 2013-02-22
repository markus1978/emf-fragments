package de.hub.emffrag.fragmentation;

import java.io.IOException;
import java.lang.ref.WeakReference;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource.Internal;

import com.google.common.base.Throwables;

import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

public class FObjectImpl extends EStoreEObjectImpl {

	private FInternalObjectImpl internalObject;
	private WeakReference<InternalEObject> containerReference = null;

	public FObjectImpl() {
		eSetStore(FStoreImpl.getInstance());
		eContainerFeatureID = Integer.MAX_VALUE;
	}

	protected void setInternalObject(FInternalObjectImpl internalObject) {
		this.internalObject = internalObject;
	}

	public FInternalObjectImpl internalObject() {
		if (internalObject == null) {
			// This object was not yet added to a model
			internalObject = UserObjectsCache.newUserObjectsCache.createInternalObject(this);
		}
		return internalObject;
	}

	@Override
	public Internal eDirectResource() {
		return (Internal) internalObject().eResource();
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
	 * See doc of {@link #eInternalContainer()}. Additionally this method is
	 * used to initialize fragmentation operations.
	 */
	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		// update the weak reference that is used instead of EMF's hard
		// reference
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
		updateContainment(newContainer, newContainerFeatureID, null);
	}

	/**
	 * Creates/deletes fragments if appropriately.
	 * 
	 * @param newContainer
	 *            The container that this object is now a part of, or null if
	 *            the object is removed from a container or placed into its own
	 *            independent fragment (e.g. as part of a indexed map).
	 * @param newContainerFeatureID
	 *            The feature ID under which this object is to be contained, or
	 *            -1.
	 * @param newFragmentURI
	 *            Optional URI if a new fragment is to be created, this works
	 *            only if there is no container for the object, and the object
	 *            is stored in its own independent fragment.
	 */
	public void updateContainment(InternalEObject newContainer, int newContainerFeatureID, URI newFragmentURI) {
		containerReference = new WeakReference<InternalEObject>(eContainer);
		eContainer = EUNINITIALIZED_CONTAINER;

		FInternalObjectImpl internalObject = internalObject();
		FragmentedModel fragmentation = internalObject.getFragmentation();
		if (fragmentation == null && newContainer != null) {
			fragmentation = ((FObjectImpl) newContainer).internalObject().getFragmentation();
		}

		// The object was moved to a new (including null) container. This can
		// have an effect on the fragmentation. The following code realizes
		// these effects.
		if (newContainer != null && newFragmentURI == null) {
			int featureID = EOPPOSITE_FEATURE_BASE - newContainerFeatureID;
			EStructuralFeature feature = newContainer.eClass().getEStructuralFeature(featureID);
			if (feature != null && EMFFragUtil.getFragmentationType(feature) != FragmentationType.None) {
				// if the object is not yet root of a fragment, a new fragment
				// has to be created
				if (!internalObject.isFragmentRoot()) {
					if (fragmentation == null) {
						throw new RuntimeException(
								"You cannot at a value to a fragmenting reference if the new container is not part of a fragmented model");
					} else {
						fragmentation.createFragment(this.internalObject, this);
					}
				}
			}
			// else:
			// if the object was a fragment root, the Fragment implementation
			// will automatically delete itself
		} else if (newFragmentURI != null) {
			// this object is added or moved to be an independent root (e.g. as
			// a value in a indexed map)
			if (fragmentation == null) {
				throw new RuntimeException(
						"You cannot at a value to a fragmenting reference if the new container is not part of a fragmented model");
			} else {
				if (internalObject.isFragmentRoot()) {
					removeFragment(this.internalObject);
				}
				fragmentation.createFragment(newFragmentURI, this.internalObject, this);
			}
		} else {
			// this object was removed from the model, it has to be moved to the
			// new objects realm (if necessary) and if it was a fragment root
			// the fragment has to be deleted.
			if (internalObject.isFragmentRoot()) {
				removeFragment(this.internalObject);
			}
			UserObjectsCache.newUserObjectsCache.addUserObjectToCache(internalObject, this);
		}
	}
	
	/**
	 * Removes the fragment of the given root. Removes the resource and the
	 * corresponding entry in persistence. This must not remove the fragment
	 * immediately, since the fragmentRoot is still attached to it.
	 * 
	 * @param fObjectImpl
	 *            The root of the fragment to delete.
	 */
	protected void removeFragment(FInternalObjectImpl fragmentRoot) {
		Fragment oldFragment = fragmentRoot.getFragment();
		try {
			oldFragment.delete(null);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
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
