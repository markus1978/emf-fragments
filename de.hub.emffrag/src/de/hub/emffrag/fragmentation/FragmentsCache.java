package de.hub.emffrag.fragmentation;

import java.util.LinkedList;
import java.util.Queue;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.statistics.IWithStatistics;
import de.hub.emffrag.statistics.Statistic;
import de.hub.emffrag.statistics.services.Plot;
import de.hub.emffrag.statistics.services.Summary;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;

/**
 * This is a wrapper around a size-based google Guava cache. It provides an
 * additional lock mechanism that temporarily prevents eviction even if the
 * cache size is exceeded. All "overhanging" entries are removed when the lock
 * is removed. This allows us to change the fragmentation without accidently
 * unloading operation critical fragments.
 */
class FragmentsCache implements IWithStatistics {

	private final Queue<Fragment> cachedFragments = new LinkedList<Fragment>();
	private final int size;
	private final FragmentsCacheListener fragmentsCacheListener;
	private int locks = 0;
	
	private final Statistic statistic = new Statistic.StatisticBuilder()
			.withService(new Summary())
			.withService(new Plot(500))
			.register(this, "FragmentsCacheSize");
	
	public interface FragmentsCacheListener {
		public void onRemoval(Fragment fragment, boolean explicitly);
	}
	
	public FragmentsCache(FragmentsCacheListener fragmentsCacheListener, int size) {
		this.fragmentsCacheListener = fragmentsCacheListener;
		this.size = size;
	}

	public void lock() {
		locks++;
	}

	public void unlock() {
		if (!isLocked()) {
			throw new IllegalStateException("Cannot unlock a not locked fragments cache.");
		}

		locks--;
		invalidate();
	}
	
	private void invalidate() {
		if (!isLocked()) {
			while (cachedFragments.size() > size) {
				Fragment invalid = cachedFragments.poll();
				fragmentsCacheListener.onRemoval(invalid, false);
			}
		}
	}

	public boolean isLocked() {
		return locks > 0;
	}

	public void add(Fragment fragment) {
		cachedFragments.add(fragment);
		
		EmfFragActivator.instance.debug(
				Ansi.format("FRAGMENTATION-CACHE: ", Color.YELLOW) +
				Ansi.format("add, size=" + cachedFragments.size(), Color.BLACK));	
		
		invalidate();
	}

	/**
	 * Removes the given fragment explicitly. This means it is removed, even if
	 * the cache is locked. Returns true if the fragment was indeed removed.
	 */
	public boolean remove(Fragment fragment, boolean strict) {
		boolean removed = cachedFragments.remove(fragment);
		if (strict && !removed) {
			throw new IllegalArgumentException("Cache does not contain the removed element.");
		}
		fragmentsCacheListener.onRemoval(fragment, true);
		return removed;
	}

	@Override
	public void trackStatistics() {
		statistic.track(cachedFragments.size());
	}
}