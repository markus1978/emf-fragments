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
 * cache size is exceeded. All "overhanging" entries are removed when the lock
 * is removed. This allows us to change the fragmentation without accidently
 * unloading operation critical fragments.
 */
class FragmentsCache {

	public interface FragmentsCacheListener {
		public void onRemoval(Fragment fragment, boolean explicitly);
	}

	private final FragmentsCacheListener fragmentsCacheListener;
	private Cache<Fragment, Fragment> backend;
	private List<Fragment> lockedFragments = new ArrayList<Fragment>();
	private int locks = 0;
	private int size;

	private class MyRemovalListener implements RemovalListener<Fragment, Fragment> {
		@Override
		public void onRemoval(RemovalNotification<Fragment, Fragment> notification) {
			Fragment fragment = notification.getValue();
			if (notification.getCause() == RemovalCause.EXPLICIT) {
				fragmentsCacheListener.onRemoval(fragment, true);
			} else {
				if (isLocked()) {
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
		locks++;
	}

	public void unlock() {
		if (!isLocked()) {
			throw new IllegalStateException("Cannot unlock a not locked fragments cache.");
		}

		if (locks == 1) {
			int space = size - (int) backend.size();
			if (space > lockedFragments.size()) {
				space = lockedFragments.size();
			}
			for (int i = 0; i < space; i++) {
				Fragment lastLockedFragment = lockedFragments.remove(lockedFragments.size() - 1);
				backend.put(lastLockedFragment, lastLockedFragment);
			}
			if (lockedFragments.isEmpty()) {
				locks--;
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
		} else {
			locks--;
		}
	}

	public boolean isLocked() {
		return locks > 0;
	}

	public void add(Fragment fragment) {
		backend.put(fragment, fragment);
	}

	/**
	 * Removes the given fragment explicitly. This means it is removed, even if
	 * the cache is locked. Returns true if the fragment was indeed removed.
	 */
	public boolean remove(Fragment eResource, boolean strict) {
		if (backend.getIfPresent(eResource) != null) {
			backend.invalidate(eResource);
			return true;
		} else {
			if (strict) {
				throw new IllegalArgumentException("Cache does not contain the removed element.");
			}
			return false;
		}
	}
}