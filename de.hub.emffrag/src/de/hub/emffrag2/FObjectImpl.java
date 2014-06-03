package de.hub.emffrag2;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FObjectImpl extends MinimalEObjectImpl.Container implements FObject {

	private Fragmentation fragmentation = null;
	
	@Override
	public boolean eNotificationRequired() {	
		return true;
	}

	@Override
	public void eNotify(Notification notification) {
		Fragmentation fFragmentation = fFragmentation();
		if (fFragmentation != null) {
			if (eResource() != null) {
				fFragmentation.onChange(notification);
			} // eResource() is null when this object is re-loaded
		}
		if (eBasicHasAdapters() && eDeliver()) {
			super.eNotify(notification);
		}
	}

	/**
	 * Overridden to check if the object is still loaded, and resolves it, if
	 * necessary.
	 */
	@Override
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		ensureIsLoaded(resolve);
		return super.eGet(eFeature, resolve);		
	}
	
	private void ensureIsLoaded(boolean resolve) {
		if (eIsProxy() && resolve) {
			if (fragmentation == null) { // assertion
				throw new IllegalStateException("Unloaded FObject without fragmentation.");
			}
			EcoreUtil.resolve(this, fragmentation);
		}
	}

	/**
	 * @return true iff the object is at the top of the containment hierarchy
	 *         within the resource that contains it.
	 */
	public boolean fIsRoot() {
		ensureIsLoaded(true);
		return eDirectResource() != null;
//		Resource eResource = eResource();
//		if (eResource != null) {
//			return eResource.getContents().contains(this); // TODO performance
//		}
//		return false;
	}

	/**
	 * @return the fragmentation this object belongs to or null, if the object
	 *         was not added to a fragmentation yet.
	 */
	public Fragmentation fFragmentation() {
		if (fragmentation == null) {
			Fragment fragment = fFragment();
			if (fragment != null) {
				fragmentation = fragment.getFragmentation();
			}
		}
		return fragmentation;
	}	

	@Override
	public Fragment fFragment() {	
		return (Fragment)eResource();
	}

	/**
	 * In EMF termionology "unload" only clears all references to/from the
	 * resource, but not within the object graph. This method does the rest.
	 * Namingly clears contents, cross references, container, and all feature
	 * values (settings). This renders the object unusable for clients, but
	 * clients can still hold references to it. When clients attempt to use it,
	 * {@link #eGet(EStructuralFeature, boolean)} will trigger a reload of its
	 * fragment, which will re-instantiate this object.
	 */
	public void fTrueUnload(Fragmentation fromFragmentation) {
		this.fragmentation = fromFragmentation;

		// if this becomes something not MinimalEObjectImpl, eBasicProperties
		// has to be cleaned as well
		eBasicSetSettings(new Object[] {});
		eBasicSetContainer(null);

		// re-create empty eSettings for further use of the object
		int size = eClass().getFeatureCount() - eStaticFeatureCount();
		if (size != 0) {
			eBasicSetSettings(new Object[size]);
		}
	}
	
	
}
