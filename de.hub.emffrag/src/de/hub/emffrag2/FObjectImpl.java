package de.hub.emffrag2;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FObjectImpl extends MinimalEObjectImpl.Container implements FObject {

	private Fragmentation unloadedFromFragmentation = null;

	@Override
	public boolean eNotificationRequired() {
		return true;
	}

	@Override
	public void eNotify(Notification notification) {
		Fragment fragment = (Fragment) eResource();
		// We have to ensure that we are not currently loading the
		// containing fragment
		//
		if (fragment != null && fragment.isLoaded() && !fragment.isLoading()) {
			Fragmentation fFragmentation = fFragmentation();
			if (fFragmentation != null) {
				fFragmentation.onChange(notification);
			}
			
			if (eBasicHasAdapters() && eDeliver()) {
				super.eNotify(notification);
			}
		}		
	}

	/**
	 * Overridden to check if the object is still loaded, and resolves it, if
	 * necessary.
	 */
	@Override
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		if (resolve) {
			fEnsureLoaded();
		}
		return super.eGet(eFeature, resolve);
	}

	@Override
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		fEnsureLoaded();
		super.eSet(eFeature, newValue);
	}

	@Override
	public void eUnset(EStructuralFeature eFeature) {
		fEnsureLoaded();
		super.eUnset(eFeature);
	}

	@Override
	public boolean eIsSet(EStructuralFeature eFeature) {
		Fragment fragment = (Fragment) eResource();
		// We have to ensure that we are not currently loading the
		// containing fragment
		//
		if (eIsProxy()) {
			if (fragment == null || (fragment.isLoaded() && !fragment.isLoading())) {
				fEnsureLoaded();
			}
		}
		return super.eIsSet(eFeature);
	}

	@Override
	public Object eInvoke(EOperation eOperation, EList<?> arguments) throws InvocationTargetException {
		fEnsureLoaded();
		return super.eInvoke(eOperation, arguments);
	}

	/**
	 * Clears {@link #unloadedFromFragmentation}, since object is not unloaded
	 * anymore, once its proxy URI is null.
	 */
	@Override
	public void eSetProxyURI(URI uri) {
		throw new UnsupportedOperationException("FObject have a different proxy mechanism. EMF's eSetProxyURI must not be called.");		
	}
	
	public void eSetProxyURI(URI uri, Fragmentation fragmentation) {
		if (uri == null) {
			this.unloadedFromFragmentation = null;
		} else {
			this.unloadedFromFragmentation = fragmentation;
			
			// if this becomes something not MinimalEObjectImpl, eBasicProperties
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
		super.eSetProxyURI(uri);
	}

	public void fEnsureLoaded() {
		if (eIsProxy()) {			
			if (unloadedFromFragmentation == null) {
				throw new IllegalStateException("Object unloaded without a fragmentation.");
			}
			EcoreUtil.resolve(this, unloadedFromFragmentation);
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
