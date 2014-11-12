package de.hub.emffrag2;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FObjectImpl extends AccessNotifyingEObjectImpl implements FObject {

	private Fragmentation fragmentationToLoadFrom = null;
	private Fragment itsLoadingIntoFragment = null;

	private boolean isNotifying() {
		return true;
	}

	@Override
	public boolean eNotificationRequired() {
		return isNotifying();
	}
	
	@Override
	public boolean fIsProxy() {
		return super.eIsProxy();
	}

	@Override
	public boolean eIsProxy() {
		fEnsureLoaded();
		return super.eIsProxy();
	}

	@Override
	public void eNotify(Notification notification) {
		fEnsureLoaded();

		Fragment fragment = (Fragment) eResource();
		// We have to ensure that we are not currently loading the
		// containing fragment
		//
		if (fragment != null && fragment.isLoaded() && !fragment.isLoading()) {
			fragment.setModified(true);
			Fragmentation fFragmentation = fFragmentation();
			if (fFragmentation != null) {
				fFragmentation.onChange(notification);
			}

			if (eBasicHasAdapters() && eDeliver()) {
				super.eNotify(notification);
			}
		}
	}

	@Override
	protected void onAccess() {
		if (isNotifying()) {
			fEnsureLoaded();
		}
	}

	protected void fSetFragmentationToLoadFrom(Fragmentation fragmentationToLoadFrom) {
		this.fragmentationToLoadFrom = fragmentationToLoadFrom;
	}
	
	protected void fSetItsLoadingIntoFragment(Fragment fragment) {
		this.itsLoadingIntoFragment = fragment;
	}

	public void fUnload(Fragmentation fragmentationToLoadFrom) {
		this.fragmentationToLoadFrom = fragmentationToLoadFrom;
		if (this.fragmentationToLoadFrom == null) {
			throw new IllegalStateException("Cannot unload an object that does not belong to a fragmentation.");
		}
		
		boolean isRoot = eDirectResource() != null;

		// if this becomes something not MinimalEObjectImpl,
		// eBasicProperties
		// has to be cleaned instead
		//
		eSetDirectResource(null);

		eBasicSetSettings(new Object[] {});
		// keep container for fragment roots (because it will not be recovered when reloaded)
		if (!isRoot) {
			eBasicSetContainer(null);
		}

		// re-create empty eSettings for further use of the object
		int size = eClass().getFeatureCount() - eStaticFeatureCount();
		if (size != 0) {
			eBasicSetSettings(new Object[size]);
		}
	}

	@Override
	public boolean fIsUnLoaded() {
		return fragmentationToLoadFrom != null;
	}

	public void fEnsureLoaded() {
		if (fIsUnLoaded()) {
			EcoreUtil.resolve(this, fragmentationToLoadFrom);
		}
	}

	/**
	 * @return true iff the object is at the top of the containment hierarchy
	 *         within the resource that contains it.
	 */
	public boolean fIsRoot() {
		fEnsureLoaded();
		return eDirectResource() != null;
	}

	/**
	 * @return the fragmentation this object belongs to or null, if the object
	 *         was not added to a fragmentation yet.
	 */
	public Fragmentation fFragmentation() {
		Fragment fragment = fFragment();
		if (fragment != null) {
			Fragmentation fragmentation = fragment.getFragmentation();
			return fragmentation;
		} else {
			return null;
		}
	}

	@Override
	public Fragment fFragment() {
		return (Fragment) eResource();
	}

	/**
	 * Overridden to ensure {@link Resource} is a {@link Fragment}.
	 */
	@Override
	protected void eSetDirectResource(Internal resource) {
		if (resource != null && !(resource instanceof Fragment)) {
			throw new IllegalStateException("FObjects can only be added to Fragments not to other Resources.");
		}
		super.eSetDirectResource(resource);
	}

	/**
	 * Overridden to ensure this is only contained by other {@link FObject}s.
	 * TODO: not covered by tests.
	 */
	@Override
	public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContFeatID, NotificationChain msgs) {
		if (newContainer != null && !(newContainer instanceof FObjectImpl)) {
			throw new IllegalStateException("FObjects can only be contained by other FObjects not other EObjects.");
		}
		return super.eBasicSetContainer(newContainer, newContFeatID, msgs);
	}

	/**
	 * Modified copy override that does nothing if the old container is the new
	 * container. Unloading and loading of containers causes unwanted behavior
	 * otherwise. On unloading the "old" container becomes a cached user object.
	 * On loading this cached user object is reused, hence the container is
	 * already there. When adding the contents to the container, the reverse add
	 * tries to reset the container, which entails removing the "old" container
	 * and setting the "new" (same) container. This remove and set does not
	 * work, because remove also removes the content from the container
	 * (reverses the currently executed action) and set does only set the
	 * container to the contents but NOT vice versa. Therefore removing and
	 * resetting the same container does not yield the same before state.
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			return eInverseAdd(otherEnd, eDerivedStructuralFeatureID(featureID, baseClass), msgs);
		} else {
			if (eInternalContainer() != null) {
				// do nothing if the new container already is the old container
				if (otherEnd == eInternalContainer()) {
					return msgs;
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return eBasicSetContainer(otherEnd, featureID, msgs);
		}
	}

	/**
	 * Overridden to weak cache references. 
	 */
	@Override
	protected EList<?> createListWrapper(EList<?> source, EStructuralFeature feature) {
		Fragmentation fragmentation = fFragmentation();
		if (fragmentation == null && itsLoadingIntoFragment != null) {
			fragmentation = itsLoadingIntoFragment.getFragmentation();
		}
		if (fragmentation != null) {
			Fragment fragment = fFragment();
			if (fragment == null) {
				fragment = itsLoadingIntoFragment;
			}
			long fragmentId = fragment.fFragmentId();
			int objectId = fragment.getID(this, true);
			int featureId = feature.getFeatureID();
			EList<?> cachedReference = fragmentation.getUserCaches()
					.getRegisteredUserReference(fragmentId, objectId, featureId);
			
			if (cachedReference != null) {
				// cachedReference.clear();
				return cachedReference;
			} else {
				EList<?> newReference = super.createListWrapper(source, feature);
				fragmentation.getUserCaches().registerUserReference(fragmentId, objectId, featureId, newReference);
				return newReference;
			}
		} else {
			return super.createListWrapper(source, feature);
		}	
	}
}
