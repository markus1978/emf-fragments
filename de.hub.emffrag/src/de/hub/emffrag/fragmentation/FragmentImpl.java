package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.hub.emffrag.proxies.Proxy;
import de.hub.emffrag.proxies.ProxyContainer;
import de.hub.jstattrack.Statistic;
import de.hub.jstattrack.StatisticBuilder;
import de.hub.jstattrack.services.Summary;

public class FragmentImpl extends BinaryResourceImpl implements Fragment, ProxyContainer {
	
	private static Statistic stat = StatisticBuilder.create().withService(new Summary()).register(FragmentImpl.class, "cacheKeys");
	
	private static long accessCounter = 1;

	private final Fragmentation fragmentation;
	private final Cache<CacheKey, Proxy> proxyCache = CacheBuilder.newBuilder().weakValues().initialCapacity(500).build();
	private final long id;
	private long lastAccessCount = 0;
	
	/**
	 * TreeIterators must not work as internal objects that use internal objects internally.
	 * They must work with proxy objects internal to keep references to all the fragments
	 * of all the objects of all the value sets that it currently holds iterators to.
	 */
	public TreeIterator<EObject> pAllContents() {
		lastAccessCount = accessCounter++;
		final Resource proxy = (Resource)FragmentationProxyManager.INSTANCE.getProxy(this, this);
		return new AbstractTreeIterator<EObject>(proxy, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<EObject> getChildren(Object object) {
				return object == proxy ? proxy.getContents().iterator() : ((EObject) object)
						.eContents().iterator();
			}
		};
	}
	
	

	@Override
	public String getURIFragment(EObject eObject) {
		// TODO Auto-generated method stub
		String uriFragment = super.getURIFragment(eObject);
		if (uriFragment.endsWith("-1")) {
			super.getURIFragment(eObject);
		}
		return uriFragment;
	}



	private static class CacheKey {
		private final Object source;
		private final int identifyHashCode;

		public CacheKey(Object source) {
			super();
			stat.track(1);
			this.source = source;
			identifyHashCode = System.identityHashCode(source);
		}

		/**
		 * Hash code based on source identity
		 */
		@Override
		public int hashCode() {
			return identifyHashCode;
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
		ConcurrentMap<CacheKey, Proxy> cacheMap = proxyCache.asMap();
		return !cacheMap.isEmpty();
	}

	/**
	 * @param source
	 *            of the proxy to remove.
	 * @return A collection with all the sources of all the child proxies of the
	 *         given source's proxy.
	 */
	@Override
	public Collection<Object> fRemoveProxy(Object sourceAsObject) {
		lastAccessCount = accessCounter++;
		FObject source = (FObject) sourceAsObject;
		Preconditions.checkArgument(source.fFragment() == this);

		Collection<Proxy> proxiesToRemove = new ArrayList<Proxy>();
		Collection<Object> sourcesToRemove = new ArrayList<Object>();
		for (Proxy proxy : proxyCache.asMap().values()) {
			if (proxy.fRoot().fSource() == source) {
				proxiesToRemove.add(proxy);
				sourcesToRemove.add(proxy.fSource());
			}
		}

		for (Proxy proxy : proxiesToRemove) {
			proxyCache.invalidate(new CacheKey(proxy.fSource()));
		}

		return sourcesToRemove;
	}

	@Override
	public Proxy fGetProxyIfExists(Object source) {
		lastAccessCount = accessCounter++;
		return proxyCache.getIfPresent(new CacheKey(source));
	}

	@Override
	public void fPutProxy(Object source, Proxy proxy) {
		lastAccessCount = accessCounter++;
		proxyCache.put(new CacheKey(source), proxy);
	}

	/**
	 * Triggers a EMF-Fragments flavored unload after the regular unload object
	 * contents.
	 */
	protected void doUnload() {
		Iterator<EObject> allContents = getAllProperContents(unloadingContents);
		List<EObject> unloadedObjects = new ArrayList<EObject>();

		// This guard is needed to ensure that clear doesn't make the resource
		// become loaded.
		//
		if (!getContents().isEmpty()) {
			getContents().clear();
		}
		getErrors().clear();
		getWarnings().clear();

		while (allContents.hasNext()) {
			InternalEObject next = (InternalEObject) allContents.next();
			unloaded(next);
			unloadedObjects.add(next);
		}

		for (EObject unloadedObject : unloadedObjects) {
			if (unloadedObject instanceof FObjectImpl) {
				((FObjectImpl) unloadedObject).fUnload();
			}
		}
	}

	public Fragmentation fFragmentation() {
		return fragmentation;
	}

	/**
	 * Promotes change to the contents to {@link Fragmentation}. Should only be
	 * invoked for root fragment (TODO test this).
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
	 * Always required to propagate changes to fragmentation for automated
	 * fragmentation of new content.
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
		((FObjectImpl) eObject).fAttachToFragment(this);
	}

	/**
	 * Overrided to transfor proxies when the proxy source changes fragments.
	 */
	@Override
	public void detached(EObject eObject) {
		((FObjectImpl) eObject).fDetachFromFragment(this);
		super.detached(eObject);
	}
	
	public long getLastAccessCount() {
		return lastAccessCount;
	}

	@Override
	public TreeIterator<EObject> getAllContents() {
		final Proxy proxy = FragmentationProxyManager.INSTANCE.getProxy(this, this);
		return new AbstractTreeIterator<EObject>((Resource)proxy, false) {	
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<EObject> getChildren(Object object) {
				return object == proxy ? ((Resource)proxy).getContents().iterator()
						: ((EObject) object).eContents().iterator();
			}
		};
	}
	
}
