package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

public class FObjectImpl extends MinimalEObjectImpl implements FObject {

	@Override
	public boolean fIsRoot() {
		return eDirectResource() != null;
	}

	public Fragmentation fFragmentation() {
		Fragment fragment = fFragment();
		if (fragment != null) {
			Fragmentation fragmentation = fragment.fFragmentation();
			return fragmentation;
		} else {
			return null;
		}
	}

	public Fragment fFragment() {
		return (Fragment) eResource();
	}
	
	public void fUnload() {
		// if this becomes something not MinimalEObjectImpl,
		// eBasicProperties
		// has to be cleaned instead
		//
		eSetDirectResource(null);
		eBasicSetContainer(null);
		eBasicSetSettings(new Object[] {});
	}

	/**
	 * Overriden to promote notifications about changes to the fragmentation in
	 * order to add content to the fragmentation.
	 */
	@Override
	public void eNotify(Notification notification) {		
		// We have to ensure that we are not currently loading the
		// containing fragment 
		// TODO check for proxy instead, should be sufficient with non eager proxy resolution.
		//
		Fragment fragment = (Fragment)eResource();
		if (fragment != null && fragment.isLoaded() && !fragment.isLoading()) {
			Fragmentation fFragmentation = fragment.fFragmentation();
			if (fFragmentation != null) {
				fFragmentation.onChange(notification);
			}
		}
		
		super.eNotify(notification);
	}

	// TODO can be removed is its use in Fragmentation becomes unnessecary.
	protected void fBasicSetContainer(InternalEObject object) {
		eBasicSetContainer(object);
	}
	
	private Collection<Object> freeProxyChildrenSources = new HashSet<Object>();
	
	protected void fDetachFrom(Fragment fragment) {
		freeProxyChildrenSources.addAll(fragment.fRemoveProxy(this));
	}
	
	protected void fAttachToFragment(Fragment fragment) {
		for (Object freeProxyChildSource: freeProxyChildrenSources) {
			FragmentationProxyManager.INSTANCE.getProxy(freeProxyChildSource, fragment);
		}
		freeProxyChildrenSources.clear();
	}

}
