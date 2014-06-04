package de.hub.emffrag2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import de.hub.emffrag.EmfFragActivator;
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

	private static class UserObjectID {
		private final long fragmentID;
		private final int intrinsicObjectID;

		private UserObjectID(long fragmentID, int intrinsicObjectID) {
			super();
			this.fragmentID = fragmentID;
			this.intrinsicObjectID = intrinsicObjectID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (fragmentID ^ (fragmentID >>> 32));
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
			if (fragmentID != other.fragmentID)
				return false;
			if (intrinsicObjectID != other.intrinsicObjectID)
				return false;
			return true;
		}
	}

	/**
	 * This is a wrapper around a size-based google Guava cache. It provides an
	 * additional lock mechanism that temporarily prevents eviction even if the
	 * cache size is exceeded. All "overhanging" entries are removed when the
	 * lock is removed. This allows us to change the fragmentation without
	 * accidently unloading operation critical fragments.
	 */
	private class FragmentsCache implements RemovalListener<Fragment, Fragment> {
		private Cache<Fragment, Fragment> backend;
		private List<Fragment> lockedFragments = new ArrayList<Fragment>();
		private boolean isLocked = false;
		private int size;

		FragmentsCache(int size) {
			backend = CacheBuilder.newBuilder().maximumSize(size).removalListener(this).build();
			this.size = size;
		}

		@Override
		public void onRemoval(RemovalNotification<Fragment, Fragment> notification) {
			Fragment fragment = notification.getValue();
			// We must only lock for fragments that are not explicitly removed (i.e. during delete)
			//
			if (isLocked && notification.getCause() != RemovalCause.EXPLICIT) {
				lockedFragments.add(fragment);
			} else {
				unloadFragment(fragment);
			}
		}

		public void lock() {
			if (isLocked) {
				throw new IllegalStateException("Can only lock the fragments cache once.");
			}
			isLocked = true;
		}

		public void unlock() {
			if (!isLocked) {
				throw new IllegalStateException("Cannot unlock a not locked fragments cache.");
			}
			isLocked = false;
			int space = size - (int) backend.size();
			if (space > lockedFragments.size()) {
				space = lockedFragments.size();
			}
			for (int i = 0; i < space; i++) {
				Fragment lastLockedFragment = lockedFragments.remove(lockedFragments.size() - 1);
				backend.put(lastLockedFragment, lastLockedFragment);
			}
			for (Fragment leftOverFragment : lockedFragments) {
				unloadFragment(leftOverFragment);
			}
			lockedFragments.clear();
		}

		public void add(Fragment fragment) {
			backend.put(fragment, fragment);
		}

		public void remove(Fragment eResource) {
			backend.invalidate(eResource);
		}
	}

	private final IDataStore dataStore;
	private final IDataMap<Long> fragmentDataStoreIndex;
	private final FragmentsCache fragmentsCache;
	private final Cache<UserObjectID, FObjectImpl> userObjectCache;

	public Fragmentation(IDataStore dataStore, int fragmentsCacheSize) {
		this.dataStore = dataStore;
		getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		fragmentDataStoreIndex = dataStore.getMap(("f_").getBytes(), LongKeyType.instance);
		fragmentsCache = new FragmentsCache(fragmentsCacheSize);
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

	/**
	 * @return The root fragment of this fragmentation. It is created if it does
	 *         not yet exists.
	 */
	public Fragment getRootFragment() {
		if (!fragmentDataStoreIndex.exists(0l)) {
			try {
				createNewFragment().save(getLoadOptions());
			} catch (IOException e) {
				String msg = "IOException while creating the root fragment of " + this.toString() + ".";
				EmfFragActivator.instance.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		}
		Fragment fragment = (Fragment) getResource(fragmentDataStoreIndex.getURI(0l), true);
		return fragment;
	}

	public EList<EObject> getContents() {
		return getRootFragment().getContents();
	}

	/**
	 * Saves all loaded fragments and flushes the data store.
	 */
	public void save(Map<?, ?> options) throws IOException {
		for (Resource resource : getResources()) {
			resource.save(options);
		}
		dataStore.flush();
	}

	/**
	 * Saves everything still loaded and closes the data store. Do not use this
	 * instance afterwards.
	 */
	public void close() {
		try {
			save(getLoadOptions());
		} catch (IOException e) {
			String msg = "IOException while closing " + this.toString() + ".";
			EmfFragActivator.instance.error(msg, e);
			throw new RuntimeException(msg, e);
		} finally {
			dataStore.close();
		}
	}

	/**
	 * Does not use the {@link Resource.Factory.Registry} of the super class,
	 * only creates {@link Fragment}s directly.
	 */
	@Override
	public Resource createResource(URI uri, String contentType) {
		return instantiateFragment(uri);
	}

	/**
	 * Creates a new fragment in the datastore with the next available datastore
	 * key. Instantiates the new fragment (see {@link #instantiateFragment(URI)}).
	 */
	protected Fragment createNewFragment() {
		Long key = fragmentDataStoreIndex.add();
		URI uri = fragmentDataStoreIndex.getURI(key);
		Fragment fragment = instantiateFragment(uri);
		EmfFragActivator.instance.debug("created " + toString(fragment));
		return fragment;
	}

	/**
	 * Instantiates a new or existing fragment in memory.
	 */
	protected Fragment instantiateFragment(URI uri) {
		Fragment fragment = new Fragment(uri, fragmentDataStoreIndex.getKeyFromURI(uri));
		getResources().add(fragment);
		fragmentsCache.add(fragment);
		EmfFragActivator.instance.debug("instantiated " + toString(fragment));
		return fragment;
	}
	
	/**
	 * Overridden for debugging. Loads a fragment from the datastore. Assumes it
	 * exists in the datastore.
	 */
	@Override
	protected void demandLoad(Resource resource) throws IOException {
		super.demandLoad(resource);
		Fragment fragment = (Fragment)resource;
		if (!fragment.getErrors().isEmpty()) {
			EmfFragActivator.instance.error("Errors in resource after loading it as fragment " + toString(fragment) + ".");
		}
		if (!fragment.getWarnings().isEmpty()) {
			EmfFragActivator.instance.warning("Warnings in resource after loading it as fragment " + toString(fragment) + ".");
		}
		EmfFragActivator.instance.debug("loaded " + toString(fragment));
	}

	/**
	 * This saves, unloads, and removes the given resource from this resource
	 * set. It does not delete the resource or its contents. Used to unload a
	 * fragment from memory.
	 */
	protected void unloadFragment(Fragment fragment) {
		if (fragment.isLoaded()) {
			try {
				fragment.save(getLoadOptions());
			} catch (IOException e) {
				String msg = "IOException while unloading " + toString(fragment) + ".";
				EmfFragActivator.instance.error(msg, e);
				throw new RuntimeException(msg, e);
			}
			fragment.unload();
		}
		this.resources.remove(fragment);
		EmfFragActivator.instance.debug("unloaded " + toString(fragment));
	}

	protected void registerUserObject(Fragment fragment, int id, FObjectImpl fObject) {
		userObjectCache.put(new UserObjectID(fragment.fFragmentId(), id), fObject);
	}

	protected FObjectImpl getRegisteredUserObject(Fragment fragment, int id) {
		return userObjectCache.getIfPresent(new UserObjectID(fragment.fFragmentId(), id));
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
	
	/**
	 * Is called when something is added to the root fragment.
	 */
	protected void onRootFragmentChange(Notification notification) {
		if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
			Fragment fragment = (Fragment)notification.getNotifier();
			if (fragment.isLoaded() && !fragment.isLoading()) { // do not do that while loading/unloading
				recursivlyReactToChange(notification, false);
			}
		} 
	}

	/**
	 * Is called if an object in this {@link Fragmentation} was changed.
	 * Depending on the situation it will: - create a new fragment - check the
	 * contents of new value for fragments - remove the old value from this
	 * fragmentation (incl. deleting fragments)
	 */
	protected void onChange(Notification notification) {
		Object feature = notification.getFeature();
		if (feature != null && feature instanceof EReference && isFragmenting((EReference) feature)) {
			recursivlyReactToChange(notification, true);							
		}					
	}
	
	private void recursivlyReactToChange(Notification notification, boolean includeSelf) {
		// lock the fragments cache to prevent accidental unloading of involved fragments
		fragmentsCache.lock();
		try {		
			// do the appropriate thing
			int type = notification.getEventType();
			if (type == Notification.ADD || type == Notification.SET) {
				addContentRecursivly((EObject) notification.getNewValue(), includeSelf);
				if (notification.getOldValue() != null) {
					removeContentRecursivly((EObject) notification.getOldValue(), includeSelf);
				}
			} else if (type == Notification.REMOVE || type == Notification.UNSET) {
				removeContentRecursivly((EObject) notification.getOldValue(), includeSelf);
			} else if (type == Notification.ADD_MANY) {
				for (Object added : (Collection<?>) notification.getNewValue()) {
					addContentRecursivly((EObject) added, includeSelf);
				}
			} else if (type == Notification.REMOVE_MANY) {
				for (Object removed : (Collection<?>) notification.getOldValue()) {
					removeContentRecursivly((EObject) removed, includeSelf);
				}
			}
		} finally {
			// release the lock on the cache
			fragmentsCache.unlock();
		}
	}

	/**
	 * Returns an iterator like {@link EObject#eAllContents()} that also
	 * contains the object itself.
	 */
	private static Iterator<EObject> all(final EObject root) {
		final TreeIterator<EObject> contents = root.eAllContents();
		return new Iterator<EObject>() {
			private boolean first = true;

			@Override
			public boolean hasNext() {
				return first || contents.hasNext();
			}

			@Override
			public EObject next() {
				if (first) {
					first = false;
					return root;
				} else {
					return contents.next();
				}
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	private void addContentRecursivly(EObject eObject, boolean includeSelf) {
		if (eObject instanceof FObject) {
			FObject fObject = (FObject)eObject;
			fObject.fEnsureLoaded();
			Iterator<EObject> contents = includeSelf ? all(fObject) : fObject.eAllContents();
			while (contents.hasNext()) {
				EObject eContent = (EObject)contents.next();
				if (eContent instanceof FObject) {
					FObject fContent = (FObject)eContent;
					fContent.fEnsureLoaded();
					if (isFragmenting(fContent.eContainingFeature()) && (fContent.fFragmentation() != this || !fContent.fIsRoot())) {
						addFragment(fContent);
					}
				}				
			}
		}		
	}

	private void removeContentRecursivly(EObject eObject, boolean includeSelf) {
		if (includeSelf) {
			if (eObject instanceof FObject) {
				FObject fObject = (FObject)eObject;
				fObject.fEnsureLoaded();
				if (fObject.fFragmentation() == this && fObject.fIsRoot()) {
					deleteFragment(fObject);
				}
			}			
		}
		Iterator<EObject> contents = eObject.eAllContents();
		while (contents.hasNext()) {
			EObject eContent = (EObject)contents.next();
			if (eContent instanceof FObject) {
				FObject fContent = (FObject)eContent;
				fContent.fEnsureLoaded();
				if (isFragmenting(fContent.eContainingFeature()) && fContent.fFragmentation() == this && fContent.fIsRoot()) {
					deleteFragment(fContent);
				}
			}
		}
	}

	private void addFragment(FObject fObject) {
		Fragment fragment = createNewFragment();
		fragment.getContents().add(fObject);
	}

	private void deleteFragment(FObject fObject) {
		Fragment fragment = fObject.fFragment();
		fragment.getContents().remove(fObject);
		fragmentDataStoreIndex.remove(fragmentDataStoreIndex.getKeyFromURI(fragment.getURI()));
		fragment.unload();

		// this will also remove this from ResourceSet#getResources()
		fragmentsCache.remove(fragment);
		
		EmfFragActivator.instance.debug("deleted " + toString(fragment));
	}

	private boolean isFragmenting(EStructuralFeature reference) {
		return !reference.getEAnnotations().isEmpty(); // TODO
	}

	/**
	 * @return An upper bound for the number of fragments that are in this
	 *         fragmentation. An upper bound because removed fragments might
	 *         also be counted.
	 */
	public long getNumberOfAllFragments() {
		return fragmentDataStoreIndex.last() + 1;
	}

	/**
	 * @return Number of fragments currently loaded.
	 */
	public int getNumberOfLoadedFragments() {
		return getResources().size();
	}
	
	protected String toString(Fragment fragment) {
		return "f[" + dataStore.getURI().toString() + "/" + fragmentDataStoreIndex.getKeyFromURI(fragment.getURI()) + "]";
	}

	@Override
	public String toString() {
		return "fragmentation[" + dataStore.getURI() + "]";
	}		
}
