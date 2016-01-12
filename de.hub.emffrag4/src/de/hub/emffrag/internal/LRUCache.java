package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;

public abstract class LRUCache<K, V> {
	private List<Entry<K, V>> toRemove = new ArrayList<Entry<K,V>>();
	private int locks = 0;	
	private final LinkedHashMap<K, V> map;

	public LRUCache(int cacheSize) {
		final int actualCacheSize = cacheSize <= 0 ? Integer.MAX_VALUE : cacheSize;
		map = new LinkedHashMap<K, V>(16, 0.75f, true) {
			private static final long serialVersionUID = 1L;
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				if (size() > actualCacheSize) {
					if (locks > 0) {
						toRemove.add(eldest);
					} else {
						doRemove(eldest.getValue());
					}
					return true;
				} else {
					return false;
				}
			}
		};
		
	}
	
	public void lock() {
		locks++;
	}
	
	public void unlock() {
		locks--;
		if (locks == 0) {
			while (!toRemove.isEmpty()) {
				int index = toRemove.size() - 1;
				Entry<K, V> entry = toRemove.get(index);
				doRemove(entry.getValue());
			}
		}
	}
	
	public V remove(Object key) {
		Iterator<Entry<K, V>> iterator = toRemove.iterator();
		while(iterator.hasNext()) {
			Entry<K, V> next = iterator.next();
			if (next.getKey().equals(key)) {
				iterator.remove();
				return next.getValue();
			}
		} 
		return map.remove(key);
	}
	
	public V get(Object key) {
		for (Entry<K, V> entry: toRemove) {
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return map.get(key);
	}
	
	public int size() {
		return map.size() + toRemove.size();
	}
	
	public V put(K key, V value) {
		return map.put(key, value);
	}

	protected abstract void doRemove(V value);

	public Collection<V> values() {
		Preconditions.checkState(toRemove.isEmpty());
		return map.values();
	}
	
	@Override
	public String toString() {
		return "toRemove(" + toRemove.toString() + ")\nmap(" + map.toString() + ")"; 
	}
}