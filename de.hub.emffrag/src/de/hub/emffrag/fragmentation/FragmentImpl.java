package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.hub.emffrag.proxies.Proxy;
import de.hub.emffrag.proxies.ProxyContainer;

public class FragmentImpl extends BinaryResourceImpl implements Fragment, ProxyContainer {

	private final Fragmentation fragmentation;
	private final Cache<Object, Proxy> proxyCache = CacheBuilder.newBuilder().weakValues().build();
	private final long id;
	
	protected FragmentImpl() {
		id = -1;
		fragmentation = null;
	}
	
	public FragmentImpl(Fragmentation fragmentation, URI uri, long id) {
		super(uri);
		this.id = id;
		this.fragmentation = fragmentation;
	}
	
	@Override
	public long fFragmentId() {
		return id;
	}

	@Override
	public boolean fIsRoot() {
		return id == 0l;
	}
	
	@Override
	public boolean fHasProxies() {
		// we only count EObject proxies. EObjectProxies remain in memory
		// as long child EList or Iterator proxies are in memory, since
		// the EObject parent/child proxy links are hard references. 
		for (Object proxy: proxyCache.asMap().keySet()) {
			if (proxy instanceof EObject) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param source of the proxy to remove.
	 * @return A collection with all the sources of all the child proxies of the given source's proxy.
	 */
	@Override
	public Collection<Object> fRemoveProxy(Object sourceAsObject) {
		FObject source = (FObject)sourceAsObject;
		Preconditions.checkArgument(source.fFragment() == this);
		
		Collection<Proxy> proxiesToRemove = new ArrayList<Proxy>();
		Collection<Object> sourcesToRemove = new ArrayList<Object>();
		for (Proxy proxy: proxyCache.asMap().values()) {
			if (proxy.fGetRootSource() == source) {
				proxiesToRemove.add(proxy);
				sourcesToRemove.add(proxy.fGetSource());
			}
		}
		
		for (Proxy proxy: proxiesToRemove) {
			proxyCache.invalidate(proxy);
		}
		return sourcesToRemove;
	}
	
	@Override
	public Proxy fGetProxyIfExists(Object source) {
		return proxyCache.getIfPresent(source);
	}
	
	@Override
	public void fPutProxy(Object source, Proxy proxy) {
		proxyCache.put(source, proxy);
	}

	/**
	 * Perform a EMF-Fragments flavored unload.
	 */
	@Override
	protected void unloaded(InternalEObject internalEObject) {
		if (internalEObject instanceof FObjectImpl) {
			((FObjectImpl)internalEObject).fUnload();	
		}
		
		super.unloaded(internalEObject);
	}

	public Fragmentation fFragmentation() {
		return fragmentation;
	}
	
	/**
	 * Promotes change to the contents to {@link Fragmentation}. Should
	 * only be invoked for root fragment (TODO test this).
	 */
	@Override
	public void eNotify(Notification notification) {
		if (notification.getFeatureID(Resource.class) != RESOURCE__IS_MODIFIED) {
			Fragmentation fragmentation = fFragmentation();
			if (fragmentation != null && fIsRoot()) {
				fragmentation.onRootFragmentChange(notification);
			}
		}
		
		if (super.eNotificationRequired()) {
			super.eNotify(notification);
		}
	}
	
	
	/**
	 * Always required to propagate changes to fragmentation for automated fragmentation of new content.
	 */
	@Override
	public boolean eNotificationRequired() {
		return true;
	}

	/**
	 * Overrided to transfor proxies when the proxy source changes fragments.
	 */
	@Override
	public void attached(EObject eObject) {
		super.attached(eObject);
		((FObjectImpl)eObject).fAttachToFragment(this);
		TreeIterator<EObject> eAllContents = eObject.eAllContents();
		while (eAllContents.hasNext()) {
			FObjectImpl fObject = (FObjectImpl)eAllContents.next();
			fObject.fAttachToFragment(this);
		}
	}

	/**
	 * Overrided to transfor proxies when the proxy source changes fragments.
	 */
	@Override
	public void detached(EObject eObject) {		
		super.detached(eObject);
		((FObjectImpl)eObject).fDetachFrom(this);
		TreeIterator<EObject> eAllContents = eObject.eAllContents();
		while (eAllContents.hasNext()) {
			FObjectImpl fObject = (FObjectImpl)eAllContents.next();
			fObject.fDetachFrom(this);
		}
	}
}
