package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

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
	private final Cache<CacheKey, Proxy> proxyCache = CacheBuilder.newBuilder().weakValues().build();
	private final long id;
	
	private static class CacheKey {
		private final Object source;

		public CacheKey(Object source) {
			super();
			this.source = source;
		}
		
		public Object get() {
			return source;
		}

		/**
		 * Hash code based on source identity
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((source == null) ? 0 : System.identityHashCode(source));
			return result;
		}

		/**
		 * Equals for sameness of source.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CacheKey other = (CacheKey) obj;
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (source != other.source)
				return false;
			return true;
		}
	}
	
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
		proxyCache.cleanUp();
		// we only count EObject proxies. EObjectProxies remain in memory
		// as long child EList or Iterator proxies are in memory, since
		// the EObject parent/child proxy links are hard references. 
		ConcurrentMap<CacheKey, Proxy> cacheMap = proxyCache.asMap();
		for (CacheKey proxyKey: cacheMap.keySet()) {
			Object source = proxyKey.get();
			if (FragmentationProxyManager.INSTANCE.hasProxyRootType(source)) {
				return true;
			}
		}
		return !cacheMap.isEmpty();
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
			if (proxy.fRoot().fSource() == source) {
				proxiesToRemove.add(proxy);
				sourcesToRemove.add(proxy.fSource());
			}
		}
		
		for (Proxy proxy: proxiesToRemove) {
			proxyCache.invalidate(new CacheKey(proxy.fSource()));
		}
			
		return sourcesToRemove;
	}
	
	@Override
	public Proxy fGetProxyIfExists(Object source) {
		return proxyCache.getIfPresent(new CacheKey(source));
	}
	
	@Override
	public void fPutProxy(Object source, Proxy proxy) {
		proxyCache.put(new CacheKey(source), proxy);		
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
	}

	/**
	 * Overrided to transfor proxies when the proxy source changes fragments.
	 */
	@Override
	public void detached(EObject eObject) {		
		((FObjectImpl)eObject).fDetachFromFragment(this);
		super.detached(eObject);
	}
}
