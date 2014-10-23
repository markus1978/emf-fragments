package de.hub.emffrag2;

import java.util.ArrayList;
import java.util.List;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * This is a wrapper around a size-based google Guava cache. It provides an
 * additional lock mechanism that temporarily prevents eviction even if the
 * cache size is exceeded. All "overhanging" entries are removed when the
 * lock is removed. This allows us to change the fragmentation without
 * accidently unloading operation critical fragments.
 */
class FragmentsCache {
	
	public interface FragmentsCacheListener {
		public void onRemoval(Fragment fragment, boolean explicitly);
	}

	private final FragmentsCacheListener fragmentsCacheListener;
	private Cache<Fragment, Fragment> backend;
	private List<Fragment> lockedFragments = new ArrayList<Fragment>();
	private boolean isLocked = false;
	private int size;
	
	private class MyRemovalListener implements RemovalListener<Fragment, Fragment> {
		@Override
		public void onRemoval(RemovalNotification<Fragment, Fragment> notification) {
			Fragment fragment = notification.getValue();
			if (notification.getCause() == RemovalCause.EXPLICIT) {
				fragmentsCacheListener.onRemoval(fragment, true);
			} else {			
				if (isLocked) {
					lockedFragments.add(fragment);
				} else {
					fragmentsCacheListener.onRemoval(fragment, false);
				}
			}
		}	
	}

	public FragmentsCache(FragmentsCacheListener fragmentsCacheListener, int size) {
		this.fragmentsCacheListener = fragmentsCacheListener;
		backend = CacheBuilder.newBuilder().maximumSize(size).removalListener(new MyRemovalListener()).build();
		this.size = size;
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

		int space = size - (int) backend.size();
		if (space > lockedFragments.size()) {
			space = lockedFragments.size();
		}
		for (int i = 0; i < space; i++) {
			Fragment lastLockedFragment = lockedFragments.remove(lockedFragments.size() - 1);
			backend.put(lastLockedFragment, lastLockedFragment);
		}
		if (lockedFragments.isEmpty()) {
			isLocked = false;
		} else {
			// Keep the lock in case the unloaded cases other loads during save.
			//
			List<Fragment> copy = new ArrayList<Fragment>(lockedFragments);
			lockedFragments.clear();
			for (Fragment leftOverFragment : copy) {
				// only implicitly removed fragments are possible here
				//
				fragmentsCacheListener.onRemoval(leftOverFragment, false);
			}
			unlock();
		}
	}

	public void add(Fragment fragment) {
		backend.put(fragment, fragment);
	}

	/**
	 * Removes the given fragment explicitly. This means it is removed, even if the cache is locked.
	 */
	public void remove(Fragment eResource) {
		if (backend.getIfPresent(eResource) != null) {
			backend.invalidate(eResource);
		} else {
			throw new IllegalArgumentException("Cache does not contain the removed element.");
		}
	}
}