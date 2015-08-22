package de.hub.emffrag.proxies;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.util.InternalEList;

public class ProxyInternalEListImpl<E> extends ProxyEListImpl<E> implements InternalEList<E> {

	public ProxyInternalEListImpl(InternalEList<E> source, Proxy parent) {
		super(source, parent);
	}

	public E basicGet(int index) {
		return fProxy(((InternalEList<E>)source).basicGet(index));
	}

	public List<E> basicList() {
		return fProxy(((InternalEList<E>)source).basicList());
	}

	public Iterator<E> basicIterator() {
		return fProxy(((InternalEList<E>)source).basicIterator());
	}

	public ListIterator<E> basicListIterator() {
		return fProxy(((InternalEList<E>)source).basicListIterator());
	}

	public ListIterator<E> basicListIterator(int index) {
		return fProxy(((InternalEList<E>)source).basicListIterator(index));
	}
	
	public Object[] basicToArray() {
		Object[] result = ((InternalEList<E>)source).basicToArray();
		for (int i = 0; i < result.length; i++) {
			result[i] = fProxy(result[i]);
		}
		return result;
	}

	public <T> T[] basicToArray(T[] array) {
		T[] result = ((InternalEList<E>)source).basicToArray(array);
		int size = source.size();
		for (int i = 0; i < size; i++) {
			result[i] = fProxy(result[i]);
		}
		return result;
	}

	public int basicIndexOf(Object object) {
		return ((InternalEList<E>)source).basicIndexOf(fSource(object));
	}

	public int basicLastIndexOf(Object object) {
		return ((InternalEList<E>)source).basicLastIndexOf(fSource(object));
	}

	public boolean basicContains(Object object) {
		return ((InternalEList<E>)source).basicContains(fSource(object));
	}

	public boolean basicContainsAll(Collection<?> c) {
		for (Object e : c)
            if (!basicContains(e))
                return false;
        return true;
	}

	public NotificationChain basicRemove(Object object, NotificationChain notifications) {
		disgurageUsage();
		return ((InternalEList<E>)source).basicRemove(object, notifications);
	}

	public NotificationChain basicAdd(E object, NotificationChain notifications) {
		disgurageUsage();
		return ((InternalEList<E>)source).basicAdd(object, notifications);
	}

	public void addUnique(E object) {
		((InternalEList<E>)source).addUnique(fSource(object));
	}

	public void addUnique(int index, E object) {
		((InternalEList<E>)source).addUnique(index, fSource(object));
	}

	public boolean addAllUnique(Collection<? extends E> c) {
		boolean modified = false;
        for (E e : c) {
            addUnique(e);
            modified = true;
        }
        return modified;
	}

	@SuppressWarnings("unchecked")
	public boolean addAllUnique(int index, Collection<? extends E> c) {
		try {
			boolean modified = false;
			ListIterator<E> e1 = listIterator(index);
			Iterator<? extends E> e2 = c.iterator();
			while (e2.hasNext()) {
				((InternalEList<E>) e1).addUnique(e2.next());
				modified = true;
			}
			return modified;
		} catch (NoSuchElementException exc) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
	}

	public E setUnique(int index, E object) {
		return ((InternalEList<E>)source).setUnique(index, fSource(object));
	}
}
