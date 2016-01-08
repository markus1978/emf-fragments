package de.hub.emffrag.datastore.internal;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class MapCache<KT> {
	
	private final KeyType<KT> keyType;
	private final TreeMap<KT, String> cachedValues = new TreeMap<KT, String>(new Comparator<KT>() {
		@Override
		public int compare(KT o1, KT o2) {
			return keyType.compare(o1, o2);
		}
	});
	
	private KT first = null;
	private KT last = null;
	
	public MapCache(KeyType<KT> keyType) {
		super();
		this.keyType = keyType;
	}

	public boolean contains(KT key) {
		if (first == null) {
			return false;
		} else {
			return keyType.compare(first, key) <= 0 && keyType.compare(last, key) >= 0;
		}
	}
	
	public boolean add(KT key) {
		if (last == null || keyType.compare(last, key) == -1) {
			last = key;
		}
		if (first == null || keyType.compare(first, key) == 1) {
			first = key;
		}
		
		return last == key;
	}
	
	public void set(KT key, String value) {
		add(key);
		cachedValues.put(key, value);
	}
	
	public String get(KT key) {
		return cachedValues.get(key);
	}
	
	public KT exactOrNext(KT key) {
		return cachedValues.ceilingKey(key);
	}
	
	public boolean exists(KT key) {
		return cachedValues.containsKey(key);
	}
	
	public KT next(KT key) {
		return exactOrNext(keyType.next(key));
	}
	
	public String remove(KT key) {
		return cachedValues.remove(key);
	}

	public Map<byte[], byte[]> contents(IDataMap<KT> map) {
		Map<byte[], byte[]> result = new HashMap<byte[], byte[]>();
		for (java.util.Map.Entry<KT, String> entry: cachedValues.entrySet()) {
			result.put(map.getStoreKey(entry.getKey()), entry.getValue().getBytes());
		}
		return result;
	}

	public void clear() {
		cachedValues.clear();
	}
}
