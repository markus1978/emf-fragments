package de.hub.emffrag.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Preconditions;

import de.hub.emffrag.FObject;
import de.hub.emffrag.FObjectImpl;

public class FObjectProxyManager {

	private static class WeakValueMap<K,V> implements Map<K,V> {
		private final ReferenceQueue<V> valueRefQueue = new ReferenceQueue<V>();
		private final Map<K, WeakReference<V>> map = new HashMap<K, WeakReference<V>>();
		private final Map<WeakReference<V>, K> keys = new HashMap<WeakReference<V>, K>();
		
		public void cleanUp() {
			Reference<? extends V> next = valueRefQueue.poll();
			while (next != null) {
				K key = keys.remove(next);
				map.remove(key);
				next = valueRefQueue.poll();
			}
		}
		public int size() {
			return map.size();
		}

		public boolean isEmpty() {
			return map.isEmpty();
		}

		public boolean containsKey(Object key) {
			return map.containsKey(key);
		}

		public boolean containsValue(Object value) {
			return map.containsValue(value);
		}

		public V get(Object key) {
			cleanUp();
			WeakReference<V> ref = map.get(key);
			if (ref != null) {
				V value = ref.get();
				if (value == null) {
					keys.remove(ref);
					map.remove(key);
				}
				return value;
			} else {
				return null;
			}
		}

		public V put(K key, V value) {
			Preconditions.checkArgument(key != null);
			Preconditions.checkArgument(value != null);
			WeakReference<V> ref = new WeakReference<>(value, valueRefQueue);
			keys.put(ref, key);
			ref = map.put(key, ref);
			cleanUp();
			return (ref == null) ? null : ref.get();
		}

		public V remove(Object key) {
			cleanUp();
			WeakReference<V> ref = map.remove(key);
			if (ref != null) {
				keys.remove(ref);
				return ref.get();
			} else {
				return null;
			}
		}

		public void putAll(Map<? extends K, ? extends V> m) {
			for (Map.Entry<? extends K, ? extends V> entry: m.entrySet()) {
				put(entry.getKey(), entry.getValue());
			}
		}

		public void clear() {
			map.clear();
			keys.clear();
		}

		public Set<K> keySet() {
			return map.keySet();
		}

		public Collection<V> values() {
			throw new RuntimeException("Not implemented");
		}

		public Set<Entry<K, V>> entrySet() {
			throw new RuntimeException("Not implemented");
		}

		public boolean equals(Object o) {
			return map.equals(o);
		}

		public int hashCode() {
			return map.hashCode();
		}
	}
 	
	private final WeakValueMap<FStoreObject, FObject> objectCache = new WeakValueMap<FStoreObject, FObject>();
	private final WeakValueMap<FURI, FObject> proxyCache = new WeakValueMap<FURI, FObject>();
	
	public FObject getFObject(FStoreObject fStoreObject) {
		FObject fObject = null;
		if (fStoreObject == null) {
			return null;
		} else if (fStoreObject.fIsProxy()) {
			FURI uri = fStoreObject.fProxyURI();
			fObject = proxyCache.get(uri);
			FStoreObject resolved = fStoreObject.resolve(true);
			if (resolved == null) {
				throw new RuntimeException("Could not resolve " + fStoreObject.fProxyURI());
			} else {
				fStoreObject = resolved;
			}
		} 
		
		if (fObject == null) {
			fObject = objectCache.get(fStoreObject);
		}
		
		if (fObject == null) {			
			EObject eObject = fStoreObject.fClass().getEPackage().getEFactoryInstance().create(fStoreObject.fClass());
			fObject = (FObjectImpl)eObject;
			((FObjectImpl)fObject).fSetStoreObject(fStoreObject);
			registerFObject(fStoreObject, fObject);
		}
		return fObject;
	}
	
	public void registerFObject(FStoreObject fStoreObject, FObject fObject) {
		objectCache.put(fStoreObject, fObject);
	}
	
	public void onFStoreObjectLoaded(FURI uri, FStoreObject fStoreObject) {
		FObject fObject = proxyCache.get(uri);
		if (fObject != null) {
			objectCache.put(fStoreObject, fObject);
		}
		proxyCache.remove(uri);
	}
	
	public void onFStoreObjectUnloaded(FStoreObject fStoreObject, FURI uri) {
		FObject fObject = objectCache.get(fStoreObject);
		if (fObject != null) {
			proxyCache.put(uri, fObject);
			objectCache.remove(fStoreObject);
		}
	}
	
	public void reset() {
		List<FStoreObject> keys = new ArrayList<FStoreObject>(objectCache.keySet());
		for (FStoreObject key: keys) {
			if (key.fFragmentation() != null) {
				objectCache.remove(key);
			}
		}
		proxyCache.clear();
	}
	
	public void fullReset() {		
		objectCache.clear();
		proxyCache.clear();
	}
	
	public void removeProxies(URI fragmentationURI) {
		List<FURI> oldUris = new ArrayList<FURI>();
		Iterator<FURI> iterator = proxyCache.keySet().iterator();
		while(iterator.hasNext()) {
			FURI proxyURI = iterator.next();
			URI proxyURIsFragmentation = proxyURI.fragmentation();
			if ((fragmentationURI != null && fragmentationURI.equals(proxyURIsFragmentation)) || fragmentationURI == proxyURIsFragmentation) {
				oldUris.add(proxyURI);
			}
		}
		for (FURI oldUri: oldUris) {
			proxyCache.remove(oldUri);
		}
	}
}
