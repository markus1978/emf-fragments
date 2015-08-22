package de.hub.emffrag.fragmentation;

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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.proxies.ProxyFactory;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;

public final class Fragmentation {
	
	private final IDataStore dataStore;
	private final int fragmentCacheSize;
	private final IDataMap<Long> fragmentDataStoreIndex;
	private final ResourceSet resourceSet;	
	
	private int finishedClientOperations = 0;
	
	public Fragmentation(IDataStore dataStore, int fragmentsCacheSize) {
		this.dataStore = dataStore;
		this.fragmentCacheSize = fragmentsCacheSize;
		this.fragmentDataStoreIndex = dataStore.getMap(("f_").getBytes(), LongKeyType.instance);
		
		this.resourceSet =  new ResourceSetImpl() {
			@Override
			public Resource createResource(URI uri, String contentType) {
				return instantiateFragment(uri);
			}

			@Override
			protected void demandLoad(Resource resource) throws IOException {
				super.demandLoad(resource);
				Fragment fragment = (Fragment)resource;
				EmfFragActivator.instance.debug(
						Ansi.format("FRAGMENTATION: ", Color.BLUE) +
						Ansi.format("loaded ", Color.GREEN) + 
						Ansi.format(Fragmentation.this.toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
			}
		};
		
		resourceSet.getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		
		initRootFragment();
	}
	
	protected Map<?,?> getLoadOptions() {
		return null;
	}
	
	private Fragment initRootFragment() {
		if (!fragmentDataStoreIndex.exists(0l)) {
			try {
				createFragment().save(getLoadOptions());
			} catch (IOException e) {
				String msg = "IOException while creating the root fragment of " + this.toString() + ".";
				EmfFragActivator.instance.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		}
		URI rootURI = getURI(0l);
		Fragment fragment = (Fragment)resourceSet.getResource(rootURI, true);
		return fragment;
	}

	public URI getURI(long id) {
		return fragmentDataStoreIndex.getURI(id);
	}
	
	public Fragment getRootFragment() {
		Fragment root = (Fragment)resourceSet.getResource(getURI(0l), true);
		if (EmfFragActivator.instance.useDynamicProxies) {
			return (Fragment)FragmentationProxyManager.INSTANCE.getProxy(root, (FragmentImpl)root); // TODO	
		} else {
			return (Fragment)ProxyFactory.INSTANCE.create(root, null);
		}
	}
	
	/**
	 * Creates a new fragment in the datastore with the next available datastore
	 * key.
	 * ).
	 */
	private Fragment createFragment() {
		Long key = fragmentDataStoreIndex.add();
		URI uri = getURI(key);
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("created ", Color.YELLOW) + 
				Ansi.format(toString(uri), Color.values()[(int)(key % Color.values().length)]));		
		
		Fragment fragment = (Fragment)resourceSet.createResource(uri);
		return fragment;
	}

	/**
	 * Instantiates a new or existing fragment in memory.
	 */
	private Fragment instantiateFragment(URI uri) {
		Fragment fragment = new FragmentImpl(this, uri, fragmentDataStoreIndex.getKeyFromURI(uri));
		resourceSet.getResources().add(fragment);
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("instantiated ", Color.BLUE) + 
				Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
		
		return fragment;
	}
	
	public void gc() {
		EmfFragActivator.instance.debug(Ansi.format("FRAGMENTATION: ", Color.BLUE) + Ansi.format("gc start", Color.BLACK));
		int count = 0;
		Collection<Fragment> fragemntsToUnload = new ArrayList<Fragment>();	
		for(Resource resource: resourceSet.getResources()) {
			if (!((FragmentImpl)resource).fHasProxies()) {
				count++;
				if (count > fragmentCacheSize) {
					fragemntsToUnload.add((Fragment)resource);
				}
			}
		}
		
		for(Fragment fragment: fragemntsToUnload) {
			unloadFragment(fragment);
		}
		EmfFragActivator.instance.debug(Ansi.format("FRAGMENTATION: ", Color.BLUE) + Ansi.format("gc end", Color.BLACK));
	}

	private void unloadFragment(Fragment fragment) {
		try {
			// TODO track modification?
			fragment.save(getLoadOptions());
		} catch (IOException e) {
			EmfFragActivator.instance.error("IOException during fragment save.", e);
		}
		// remove content, errors, etc., make all contents a proxy, clear values in all contents 
		fragment.unload();
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("unloaded ", Color.RED) + 
				Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
		
		// remove from resource
		resourceSet.getResources().remove(fragment);
	}

	public EList<EObject> getContents() {
		return getRootFragment().getContents();
	}
	
	/**
	 * Is called when something is added to the root fragment.
	 */
	protected void onRootFragmentChange(Notification notification) {
		if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
			Fragment fragment = (Fragment) notification.getNotifier();
			if (fragment.isLoaded() && !((ResourceImpl)fragment).isLoading()) { // do not do
																// that while
																// loading/unloading
				// this is not part of a AccessNotifyingObject transaction, we have to lock manually.
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
		// lock the fragments cache to prevent accidental unloading of involved
		// fragments
		
		Notification nextNotification = null;
		try {
			nextNotification = (Notification)FieldUtils.readField(notification, "next", true);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Unexpection Notification implementationw without next field.");
		}
		
		if (nextNotification != null) {
			if (nextNotification.getEventType() == Notification.ADD && notification.getEventType() == Notification.REMOVE) {
				// move detected, do nothing for the remove event: the fragments do not need to be removed
				return;
			}
		}
		
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
			FObject fObject = (FObject) eObject;
			Iterator<EObject> contents = includeSelf ? all(fObject) : fObject.eAllContents();
			while (contents.hasNext()) {
				EObject eContent = (EObject) contents.next();
				if (eContent instanceof FObject) {
					FObject fContent = (FObject) eContent;
					EStructuralFeature eContainingFeature = fContent.eContainingFeature();
					if (eContainingFeature != null && isFragmenting(eContainingFeature)
							&& (fContent.fFragmentation() != this || !fContent.fIsRoot())) {
						Fragment fragment = createFragment();
						fragment.getContents().add(fContent);
					}
				}
			}
		}
	}

	private void removeContentRecursivly(EObject eObject, boolean includeSelf) {
		List<FObject> fragmentRootsToDelete = new ArrayList<FObject>();
		if (includeSelf) {
			if (eObject instanceof FObject) {
				FObject fObject = (FObject) eObject;
				if (fObject.fFragmentation() == this && fObject.fIsRoot()) {
					fragmentRootsToDelete.add(fObject);
				}
			}
		}
		Iterator<EObject> contents = eObject.eAllContents();
		while (contents.hasNext()) {
			EObject eContent = (EObject) contents.next();
			if (eContent instanceof FObject) {
				FObject fContent = (FObject) eContent;
				if (isFragmenting(fContent.eContainingFeature()) && fContent.fFragmentation() == this && fContent.fIsRoot()) {
					fragmentRootsToDelete.add(fContent);
				}
			}
		}
		
		// unroot the fragment roots to avoid reverseRemove/container schenanigans later
		for (FObject fragmentRoot : fragmentRootsToDelete) {
			((FObjectImpl)fragmentRoot).fBasicSetContainer(null); // TODO
		}

		// acutally delete the fragments
		for (FObject fragmentRoot : fragmentRootsToDelete) {
			deleteFragment(fragmentRoot);
		}
	}

	private void deleteFragment(FObject fObject) {
		Fragment fragment = fObject.fFragment();
		fragment.unload();
		resourceSet.getResources().remove(fragment);
		fragmentDataStoreIndex.remove(fragmentDataStoreIndex.getKeyFromURI(fragment.getURI()));

		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION: ", Color.BLUE) +
				Ansi.format("deleted ", Color.MAGENTA) + 
				Ansi.format(toString(fragment), Color.values()[(int)(fragment.fFragmentId() % Color.values().length)]));
	}

	public static boolean isFragmenting(EStructuralFeature reference) {
		return !reference.getEAnnotations().isEmpty(); // TODO reasonable detection of fragmentation annotations
	}
	
	public void close() {
		for (Resource resource: resourceSet.getResources()) {
			try {
				resource.save(getLoadOptions());
			} catch (IOException e) {
				EmfFragActivator.instance.error("IOException during fragment save.", e);
			}	
		}
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
		return resourceSet.getResources().size();
	}

	protected void onFinishedClientOperation() {
		// TODO better strategy to determine gc interval, gc necessity, e.g. count changes to resources.
		finishedClientOperations++;
		if (finishedClientOperations > fragmentCacheSize*10) {
			gc();
			finishedClientOperations = 0;
		}
	}
}
