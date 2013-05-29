package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

public abstract class FragmentsCache {
	
	private static class E {
		E next;
		E prev;
		Fragment entry;
		E(Fragment entry) { this.entry = entry; }
		
		void move(E newPrev) {			
			remove();
			add(newPrev);
		}
		
		void add(E newPrev) {
			prev = newPrev;
			next = newPrev.next;
			newPrev.next = this;
			next.prev = this;
		}
		
		void remove() {
			prev.next = next;
			next.prev = prev;
			
			next = null;
			prev = null;
		}
		
		boolean isProtected() {
			return next == null;
		}
	}

	private final E start;
	private final E end;
	private int size = 0;	
	private final Map<URI, E> uriMap = new HashMap<URI, E>();
	private final int maxSize;
	
	public FragmentsCache(int maxSize) {
		this.maxSize = maxSize;
		start = new E(null);
		end = new E(null);
		start.next = end;
		end.prev = start;
	}
	
	public void protect(Fragment fragment) {
		E e = uriMap.get(fragment.getURI());
		if (!e.isProtected()) {
			e.remove();
			size--;
		}		
	}
	
	public void unprotect(Fragment fragment) {
		E e = uriMap.get(fragment.getURI());
		if (e.isProtected()) {
			e.add(start);
			size++;
		} else {
			e.move(start);
		}
		evictIfNecessary();
	}
	
	private void evictIfNecessary() {
		while (size > maxSize) {
			E r = end.prev;
			r.remove();
			size--;
			uriMap.remove(r.entry.getURI());
			onEvict(r.entry);
		}
	}
	
	public void put(URI uri, Fragment fragment) {
		assert (uriMap.get(uri) == null);
		E e = new E(fragment);
		uriMap.put(uri, e);
		e.add(start);
		size++;
		evictIfNecessary();
	}
	
	public void remove(URI uri) {
		E e = uriMap.remove(uri);
		if (!e.isProtected()) {
			e.remove();
			size--;
		}
	}
	
	public void touch(Fragment fragment) {
		E e = uriMap.get(fragment.getURI());
		if (!e.isProtected()) {
			e.move(start);
		}
	}
	
	protected abstract void onEvict(Fragment fragment);

	public int size() {
		return size;
	}
}
