package de.hub.emffrag2;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FObjectImpl extends AccessNotifyingEObjectImpl implements FObject {

	private Fragmentation fragmentationToLoadFrom = null;
	
	private boolean isNotifying() {
		return true;
	}
	

	@Override
	public boolean eNotificationRequired() {
		return isNotifying();
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
	
	public void fUnload(Fragmentation fragmentationToLoadFrom) {
		this.fragmentationToLoadFrom = fFragmentation();
		if (fragmentationToLoadFrom == null) {
			throw new IllegalStateException("Cannot unload an object that does not belong to a fragmentation.");
		}
		
		// if this becomes something not MinimalEObjectImpl,
		// eBasicProperties
		// has to be cleaned instead
		//
		eSetDirectResource(null);

		eBasicSetSettings(new Object[] {});
		eBasicSetContainer(null);

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

}
