package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import de.hub.emffrag.EmfFragActivator;

public class FObjectImpl extends MinimalEObjectImpl implements FObject {
	
	@Override
	public boolean fIsRoot() {
		return eDirectResource() != null;
	}

	public Fragmentation fFragmentation() {
		FragmentImpl fragment = fFragment();
		if (fragment != null) {
			Fragmentation fragmentation = fragment.fFragmentation();
			return fragmentation;
		} else {
			return null;
		}
	}

	public FragmentImpl fFragment() {
		return (FragmentImpl)eResource();		
	}
	
	public void fUnload() {
		// if this becomes something not MinimalEObjectImpl,
		// eBasicProperties
		// has to be cleaned instead
		//
		eSetDirectResource(null);
		eBasicSetContainer(null);
		Object[] eSettings = eBasicSettings();
		if (eSettings != null) {
			for (int i = 0; i < eSettings.length; i++) {
				eSettings[i] = null;
			}
		}
	}

	@Override
	public void eSetProxyURI(URI uri) {
		if (uri.toString().endsWith("-1")) {
			EmfFragActivator.instance.error("Bad uri: " + uri.toString());
		}
		super.eSetProxyURI(uri);
	}

	/**
	 * Overriden to promote notifications about changes to the fragmentation in
	 * order to add content to the fragmentation.
	 */
	@Override
	public void eNotify(Notification notification) {		
		if ((Fragmentation.config & Fragmentation.NO_NOTIFY) == 0) {
			if ((Fragmentation.config & Fragmentation.READONLY) == 0) {
				// We have to ensure that we are not currently loading the
				// containing fragment 
				// TODO check for proxy instead, should be sufficient with non eager proxy resolution.
				//
				FragmentImpl fragment = (FragmentImpl)eResource();
				if (fragment != null && fragment.isLoaded() && !fragment.isLoading()) {
					Fragmentation fFragmentation = fragment.fFragmentation();
					if (fFragmentation != null) {
						fFragmentation.onChange(notification);
					}
				}
			}
			
			if (super.eNotificationRequired()) {
				super.eNotify(notification);
			}
		}
	}
	
	@Override
	public boolean eNotificationRequired() {
		return true;
	}
	
	private Collection<Object> freeProxyChildrenSources = new HashSet<Object>();
	
	protected void fDetachFromFragment(FragmentImpl fragment) {
		freeProxyChildrenSources.addAll(fragment.fRemoveProxy(this));
	}
	
	protected void fAttachToFragment(FragmentImpl fragment) {
		InternalEObject internalContainer = eInternalContainer();
		if (internalContainer != null && !internalContainer.eIsProxy() && internalContainer.eDirectResource() != null) {
			fragment.fHold(((FObject)eContainer()).fFragment().fIsHold());
		}
		for (Object freeProxyChildSource: freeProxyChildrenSources) {
			FragmentationProxyManager.INSTANCE.getProxy(freeProxyChildSource, fragment);
		}
		freeProxyChildrenSources.clear();
	}
	
	/**
	 * TreeIterators must not work as internal objects that use internal objects internally.
	 * They must work with proxy objects internal to keep references to all the fragments
	 * of all the objects of all the value sets that it currently holds iterators to.
	 */
	public TreeIterator<EObject> pAllContents() {
		Object proxy = FragmentationProxyManager.INSTANCE.getProxy(this, fFragment());
		return new AbstractTreeIterator<EObject>(proxy, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<EObject> getChildren(Object object) {
				return ((EObject) object).eContents().iterator();						
			}
		};
	}
}
