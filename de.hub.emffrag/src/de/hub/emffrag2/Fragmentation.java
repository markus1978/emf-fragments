package de.hub.emffrag2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
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

import sun.reflect.misc.FieldUtil;
import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag2.FragmentsCache.FragmentsCacheListener;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;

/**
 * In contrast to {@link ResourceSetImpl}, this class does not normalize URIs,
 * does not use the {@link Resource.Factory.Registry}, and it also installs its
 * own {@link URIHandler}.
 */
public class Fragmentation extends ResourceSetImpl {

	

	private final IDataStore dataStore;
	private final IDataMap<Long> fragmentDataStoreIndex;
	private FragmentsCache fragmentsCache;
	private final UserCaches userCaches;

	// TODO make private after tests
	public boolean resolveProxies = true;

	public Fragmentation(IDataStore dataStore, int fragmentsCacheSize) {
		this.dataStore = dataStore;
		getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		fragmentDataStoreIndex = dataStore.getMap(("f_").getBytes(), LongKeyType.instance);
		FragmentsCacheListener cacheListener = new FragmentsCacheListener() {
			@Override
			public void onRemoval(Fragment fragment, boolean explicitly) {
				// Explicitly removed fragments should be unloaded, since
				// unloadFragments (and only unloadFragments) removes them
				// explicitly.
				//
				if (!explicitly) {
					doUnloadFragment(fragment);
				}
			}
		};
		fragmentsCache = new FragmentsCache(cacheListener, fragmentsCacheSize);
		userCaches = new UserCaches();		
	}

	// TODO remove after test
	public void resetCachesForTest() {
		// fragmentsCache = new FragmentsCache(fragmentsCache.size);
		// userObjectCache = CacheBuilder.newBuilder().weakValues().build();
	}

	@Override
	public EObject getEObject(URI uri, boolean loadOnDemand) {
		return super.getEObject(uri, loadOnDemand && resolveProxies);
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
		fragmentsCache.lock();
		for (Resource resource : getResources()) {
			resource.save(options);
		}
		dataStore.flush();
		fragmentsCache.unlock();
	}

	/**
	 * Saves everything still loaded and closes the data store. Do not use this
	 * instance afterwards.
	 */
	public void close() {
		List<Resource> copy = new ArrayList<Resource>(getResources());
		for (Resource resource : copy) {
			unloadFragment((Fragment) resource);
		}
		dataStore.close();
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
	 * key. Instantiates the new fragment (see {@link #instantiateFragment(URI)}
	 * ).
	 */
	protected Fragment createNewFragment() {
		Long key = fragmentDataStoreIndex.add();
		URI uri = fragmentDataStoreIndex.getURI(key);
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("created ", Color.YELLOW) + 
				Ansi.format(toString(uri), Color.values()[(int)(key % Color.values().length)]));
		
		Fragment fragment = instantiateFragment(uri);
		return fragment;
	}

	/**
	 * Instantiates a new or existing fragment in memory.
	 */
	protected Fragment instantiateFragment(URI uri) {
		Fragment fragment = createFragment(uri, fragmentDataStoreIndex.getKeyFromURI(uri));
		getResources().add(fragment);
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("instantiated ", Color.BLUE) + 
				Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
		
		fragmentsCache.add(fragment);
		return fragment;
	}
	
	protected Fragment createFragment(URI uri, long key) {
		return new Fragment(uri, fragmentDataStoreIndex.getKeyFromURI(uri));
	}

	/**
	 * Overridden for debugging. Loads a fragment from the datastore. Assumes it
	 * exists in the datastore.
	 */
	@Override
	protected void demandLoad(Resource resource) throws IOException {
		resolveProxies = false;
		try {
			if (resource.isLoaded()) {
				throw new IllegalStateException("Cannot load an already loaded fragment.");
			}
			super.demandLoad(resource);
			Fragment fragment = (Fragment) resource;
			if (!fragment.getErrors().isEmpty()) {
				EmfFragActivator.instance.error("Errors in resource after loading it as fragment " + toString(fragment) + ".");
			}
			if (!fragment.getWarnings().isEmpty()) {
				EmfFragActivator.instance.warning("Warnings in resource after loading it as fragment " + toString(fragment)
						+ ".");
			}
			
			EmfFragActivator.instance.debug(
					Ansi.format("FRAGMENTATION: ", Color.BLUE) +
					Ansi.format("loaded ", Color.GREEN) + 
					Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		resolveProxies = true;
	}

	/**
	 * This saves, unloads, and removes the given resource from this resource
	 * set. It does not delete the resource or its contents. Used to unload a
	 * fragment from memory. 
	 */
	private void doUnloadFragment(Fragment fragment) {
		if (fragment.isLoaded()) {
			resolveProxies = false;
			boolean isModified = fragment.isModified();
			if (isModified && !fragment.fIsDeleted()) {
				try {
					fragment.save(getLoadOptions());
				} catch (IOException e) {
					String msg = "IOException while unloading " + toString(fragment) + ".";
					EmfFragActivator.instance.error(msg, e);
					throw new RuntimeException(msg, e);
				}
			}
			fragment.unload();
			resolveProxies = true;
			this.resources.remove(fragment);			
			
			EmfFragActivator.instance.debug(
					Ansi.format("FRAGMENTATION: ", Color.BLUE) +
					Ansi.format("unloaded ", Color.RED) + 
					Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]) +
					(isModified ? "*" : ""));
			
		} else {
			throw new IllegalStateException("Cannot unload a not loaded fragment.");
		}
	}

	/**
	 * This saves, unloads, and removes the given resource from this resource
	 * set. It does not delete the resource or its contents. Used to unload a
	 * fragment from memory. Removes it from the fragments cache.
	 * 
	 * This should not be called directly and serves only maintanence purposes.
	 */
	public void unloadFragment(Fragment fragment) {
		fragmentsCache.remove(fragment, true);
		doUnloadFragment(fragment);
	}
	
	protected UserCaches getUserCaches() {
		return userCaches;
	}

	protected void registerUserObject(Fragment fragment, int id, FObjectImpl fObject) {
		userCaches.registerUserObject(fragment.fFragmentId(), id, fObject);
	}

	protected FObjectImpl getRegisteredUserObject(Fragment fragment, int id) {
		return userCaches.getRegisterdUserObject(fragment.fFragmentId(), id);
	}

	protected FObjectImpl getRegisteredUserObject(URI uri) {
		int id = Integer.parseInt(uri.fragment());
		long fragmentId = fragmentDataStoreIndex.getKeyFromURI(uri.trimFragment());
		return userCaches.getRegisterdUserObject(fragmentId, id);
	}
	
	public URI createURI(long fragmentId, Integer objectId) {
		URI uri = fragmentDataStoreIndex.getURI(fragmentId);
		if (objectId != null) {
			uri = uri.appendFragment(String.valueOf(objectId));
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
		if (!resolveProxies) {
			return;
		}
		if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
			Fragment fragment = (Fragment) notification.getNotifier();
			if (fragment.isLoaded() && !fragment.isLoading()) { // do not do
																// that while
																// loading/unloading
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
		if (!resolveProxies) {
			return;
		}
		Object feature = notification.getFeature();
		if (feature != null && feature instanceof EReference && isFragmenting((EReference) feature)) {
			recursivlyReactToChange(notification, true);
		}
	}

	private void recursivlyReactToChange(Notification notification, boolean includeSelf) {
		// lock the fragments cache to prevent accidental unloading of involved
		// fragments
		fragmentsCache.lock();		
		
		Notification nextNotification = null;
		try {
			nextNotification = (Notification)FieldUtils.readField(notification, "next", true);
		} catch (IllegalAccessException e) {
			fragmentsCache.unlock();
			throw new IllegalArgumentException("Unexpection Notification implementationw without next field.");
		}
		
		if (nextNotification != null) {
			if (nextNotification.getEventType() == Notification.ADD && notification.getEventType() == Notification.REMOVE) {
				// move detected, do nothing for the remove event: the fragments do not need to be removed
				fragmentsCache.unlock();
				return;
			}
		}
		
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
		if (eObject instanceof FObjectImpl) {
			FObjectImpl fObject = (FObjectImpl) eObject;
			fObject.fEnsureLoaded();
			Iterator<EObject> contents = includeSelf ? all(fObject) : fObject.eAllContents();
			while (contents.hasNext()) {
				EObject eContent = (EObject) contents.next();
				if (eContent instanceof FObjectImpl) {
					FObjectImpl fContent = (FObjectImpl) eContent;
					fContent.fEnsureLoaded();
					EStructuralFeature eContainingFeature = fContent.eContainingFeature();
					if (eContainingFeature == null) {
						System.out.println("### no");
					}
					if (eContainingFeature != null && isFragmenting(eContainingFeature)
							&& (fContent.fFragmentation() != this || !fContent.fIsRoot())) {
						addFragment(fContent);
					}
				}
			}
		}
	}

	private void removeContentRecursivly(EObject eObject, boolean includeSelf) {
		List<FObjectImpl> fragmentRootsToDelete = new ArrayList<FObjectImpl>();
		if (includeSelf) {
			if (eObject instanceof FObjectImpl) {
				FObjectImpl fObject = (FObjectImpl) eObject;
				fObject.fEnsureLoaded();
				if (fObject.fFragmentation() == this && fObject.fIsRoot()) {
					fragmentRootsToDelete.add(fObject);
				}
			}
		}
		Iterator<EObject> contents = eObject.eAllContents();
		while (contents.hasNext()) {
			EObject eContent = (EObject) contents.next();
			if (eContent instanceof FObjectImpl) {
				FObjectImpl fContent = (FObjectImpl) eContent;
				fContent.fEnsureLoaded();
				if (isFragmenting(fContent.eContainingFeature()) && fContent.fFragmentation() == this && fContent.fIsRoot()) {
					fragmentRootsToDelete.add(fContent);
				}
			}
		}
		
		// unroot the fragment roots to avoid reverseRemove/container schenanigans later
		for (FObjectImpl fragmentRoot : fragmentRootsToDelete) {
			fragmentRoot.eBasicSetContainer(null);
		}

		// acutally delete the fragments
		for (FObjectImpl fragmentRoot : fragmentRootsToDelete) {
			deleteFragment(fragmentRoot);
		}
	}

	private void addFragment(FObjectImpl fObject) {
		Fragment fragment = createNewFragment();
		fragment.getContents().add(fObject);
	}

	private void deleteFragment(FObjectImpl fObject) {
		Fragment fragment = fObject.fFragment();
		fragmentDataStoreIndex.remove(fragmentDataStoreIndex.getKeyFromURI(fragment.getURI()));
		fragment.fDelete();
		
		// TODO, should this be removed? It only causes problems.
		// resolveProxies = false;
		// fragment.unload();
		// resolveProxies = true;

		// this will also remove this from ResourceSet#getResources()
		if (fragmentsCache.remove(fragment, false)) {
			// FragmentsCache#remove is called not in strict mode, because the
			// deleted fragment might not be cached.
			// Since fragments are removed from the loaded fragments
			// (#getResources()) implicitly, we have to
			// remove it explicitly, if the fragment was not in the cache.
			getResources().remove(fragment);
		}

		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("deleted ", Color.MAGENTA) + 
				Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
	}

	private boolean isFragmenting(EStructuralFeature reference) {
		return !reference.getEAnnotations().isEmpty(); // TODO reasonable detection of fragmentation annotations
	}

	/**
	 * @return An upper bound for the number of fragments that are in this
	 *         fragmentation. An upper bound because removed fragments might
	 *         also be counted.
	 */
	public long getIndexOfLastAddedAndStillExistingFragment() {
		return fragmentDataStoreIndex.last() + 1;
	}

	/**
	 * @return Number of fragments currently loaded.
	 */
	public int getNumberOfLoadedFragments() {
		return getResources().size();
	}
	
	public boolean isFragmentsCacheLocked() {
		return fragmentsCache.isLocked();
	}

	protected String toString(Fragment fragment) {
		return toString(fragment.getURI());
	}

	protected String toString(URI fragmentURI) {
		return "f[" + dataStore.getURI().toString() + "/" + fragmentDataStoreIndex.getKeyFromURI(fragmentURI) + "]";
	}

	@Override
	public String toString() {
		return "fragmentation[" + dataStore.getURI() + "]";
	}
}
