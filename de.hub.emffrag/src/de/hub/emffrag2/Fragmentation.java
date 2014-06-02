package de.hub.emffrag2;

import java.io.IOException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;

/**
 * In contrast to {@link ResourceSetImpl}, this class does not normalize URIs,
 * does not use the {@link Resource.Factory.Registry}, and it also installs its
 * own {@link URIHandler}.
 */
public class Fragmentation extends ResourceSetImpl {

	private class Adapter extends EContentAdapter {
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			Fragmentation.this.onChange(notification);
		}
	}

	private static class UserObjectID {
		private final String fragmentID;
		private final int intrinsicObjectID;

		private UserObjectID(String fragmentID, int intrinsicObjectID) {
			super();
			this.fragmentID = fragmentID;
			this.intrinsicObjectID = intrinsicObjectID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fragmentID == null) ? 0 : fragmentID.hashCode());
			result = prime * result + intrinsicObjectID;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserObjectID other = (UserObjectID) obj;
			if (fragmentID == null) {
				if (other.fragmentID != null)
					return false;
			} else if (!fragmentID.equals(other.fragmentID))
				return false;
			if (intrinsicObjectID != other.intrinsicObjectID)
				return false;
			return true;
		}
	}

	private final IDataMap<Long> fragmentDataStoreIndex;
	private final Adapter adapter = new Adapter();
	private final Cache<Fragment, Fragment> resourceCache;
	private final Cache<UserObjectID, FObjectImpl> userObjectCache;

	public Fragmentation(IDataStore dataStore, int fragmentsCacheSize) {
		getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		fragmentDataStoreIndex = dataStore.getMap(("f_").getBytes(), LongKeyType.instance);

		resourceCache = CacheBuilder.newBuilder().maximumSize(fragmentsCacheSize)
				.removalListener(new RemovalListener<Fragment, Fragment>() {
					@Override
					public void onRemoval(RemovalNotification<Fragment, Fragment> notification) {
						removeResource(notification.getValue());
					}
				}).build();

		userObjectCache = CacheBuilder.newBuilder().weakValues().build();
	}

	@Override
	public URIConverter getURIConverter() {
		if (uriConverter == null) {
			uriConverter = new ExtensibleURIConverterImpl() {
				/**
				 * The base implementation is unnecessary and quite costly, we
				 * only use one "form" of URIs.
				 */
				@Override
				public URI normalize(URI uri) {
					return uri;
				}
			};
		}
		return uriConverter;
	}

	public Fragment getRootFragment() {
		EList<Resource> resources = getResources();
		if (resources.isEmpty()) {
			if (fragmentDataStoreIndex.exists(0l)) {
				getResource(fragmentDataStoreIndex.getURI(0l), true);
			} else {
				URI uri = nextAvailableFragmentURI();
				Resource resource = createResource(uri);
				afterLoadFragment((Fragment)resource);
			}
		}
		return (Fragment) resources.get(0);
	}

	public EList<EObject> getContents() {
		Fragment rootFragment = getRootFragment();
		if (!rootFragment.fragmentIsLoaded()) {
			try {
				demandLoad(rootFragment);
			} catch (IOException e) {
				// TODO
				throw new RuntimeException(e);
			}
		}
		return rootFragment.getContents();
	}

	/**
	 * Does not use the {@link Resource.Factory.Registry} of the super class,
	 * only creates {@link Fragment}s directly.
	 */
	@Override
	public Resource createResource(URI uri, String contentType) {
		Fragment resource = new Fragment(uri);
		getResources().add(resource);
		afterInstantiateFragment(resource);
		return resource;
	}

	@Override
	protected void demandLoad(Resource resource) throws IOException {
		super.demandLoad(resource);
		afterLoadFragment((Fragment) resource);
	}

	/**
	 * This saves, unloads, and removes the given resource from this resource
	 * set. It does not delete the resource or its contents.
	 */
	protected void removeResource(Resource resource) {
		beforeUnLoadFragment((Fragment) resource);
		try {
			resource.save(getLoadOptions());
		} catch (IOException e) {
			// TODO
		}
		resource.unload();
		this.resources.remove(resource);
	}

	public void registerUserObject(Fragment fragment, int id, FObjectImpl fObject) {
		userObjectCache.put(new UserObjectID(fragment.fFragmentId(), id), fObject);
	}

	public FObjectImpl getRegisteredUserObject(Fragment fragment, int id) {
		return userObjectCache.getIfPresent(new UserObjectID(fragment.fFragmentId(), id));
	}

	private void afterInstantiateFragment(Fragment fragment) {
		resourceCache.put(fragment, fragment);
	}

	private void afterLoadFragment(Fragment fragment) {
		fragment.eAdapters().add(adapter);
	}

	private void beforeUnLoadFragment(Fragment fragment) {
		fragment.eAdapters().remove(adapter);
	}

	private URI nextAvailableFragmentURI() {
		Long nextAvailableFragmentID = fragmentDataStoreIndex.add();
		return fragmentDataStoreIndex.getURI(nextAvailableFragmentID);
	}

	public URI createURI(long fragmentId, String objectId) {
		URI uri = fragmentDataStoreIndex.getURI(fragmentId);
		if (objectId != null) {
			uri = uri.appendFragment("/" + objectId);
		}
		return uri;
	}

	public URI createURI(long fragmentId) {
		return createURI(fragmentId, null);
	}

	private void onChange(Notification notification) {
		Object feature = notification.getFeature();
		int type = notification.getEventType();
		if (type == Notification.ADD || type == Notification.SET) {
			if (feature != null && feature instanceof EStructuralFeature) {
				EStructuralFeature eFeature = (EStructuralFeature) feature;
				if (!eFeature.getEAnnotations().isEmpty()) { // TODO
					// do fragment
					FObjectImpl fObject = (FObjectImpl) notification.getNewValue();
					Resource resource = createResource(nextAvailableFragmentURI());
					afterLoadFragment((Fragment)resource);
					resource.getContents().add(fObject);
				}
			}
		} else {
			// TODO
		}
	}
}
