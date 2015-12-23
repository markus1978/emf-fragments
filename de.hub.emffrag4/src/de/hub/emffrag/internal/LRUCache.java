package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class LRUCache<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 1L;
	private int cacheSize;
	private List<V> valuesToRemove = new ArrayList<V>();
	private int locks = 0;

	public LRUCache(int cacheSize) {
		super(16, 0.75f, true);
		this.cacheSize = cacheSize;
	}
	
	public void lock() {
		locks++;
	}
	
	public void unlock() {
		locks--;
		if (locks == 0) {
			while (!valuesToRemove.isEmpty()) {
				onRemove(valuesToRemove.remove(valuesToRemove.size() - 1));
			}
		}
	}

	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		if (size() > cacheSize) {
			if (locks > 0) {
				valuesToRemove.add(eldest.getValue());
			} else {
				onRemove(eldest.getValue());
			}
			return true;
		} else {
			return false;
		}
	}

	protected abstract void onRemove(V value);
}