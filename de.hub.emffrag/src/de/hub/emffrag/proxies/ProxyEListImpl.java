package de.hub.emffrag.proxies;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;

public class ProxyEListImpl<E> extends AbstractChildProxyImpl<EList<E>> implements EList<E> {

	public ProxyEListImpl(EList<E> source, Proxy parent) {
		super(source, parent);
	}

	public void move(int newPosition, E object) {
		source.move(newPosition, fSource(object));
	}

	public E move(int newPosition, int oldPosition) {
		return fProxy(source.move(newPosition, oldPosition));
	}

	public void forEach(Consumer<? super E> action) {
		disgurageUsage();
		source.forEach(action);
	}

	public int size() {
		return source.size();
	}

	public boolean isEmpty() {
		return source.isEmpty();
	}

	public boolean contains(Object o) {
		return source.contains(fSource(o));
	}

	public Iterator<E> iterator() {
		return fProxy(source.iterator());
	}

	public Object[] toArray() {		
		Object[] result = source.toArray();
		for (int i = 0; i < result.length; i++) {
			result[i] = fProxy(result[i]);
		}
		return result;
	}

	public <T> T[] toArray(T[] a) {
		T[] result = source.toArray(a);
		int size = source.size();
		for (int i = 0; i < size; i++) {
			result[i] = fProxy(result[i]);
		}
		return result;
	}

	public boolean add(E e) {
		return source.add(fSource(e));
	}

	public boolean remove(Object o) {
		return source.remove(fSource(o));
	}

	public boolean containsAll(Collection<?> c) {
		for (Object e : c)
            if (!contains(e))
                return false;
        return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
       try {
            boolean modified = false;
            ListIterator<E> e1 = listIterator(index);
            Iterator<? extends E> e2 = c.iterator();
            while (e2.hasNext()) {
                e1.add(e2.next());
                modified = true;
            }
            return modified;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
	}

	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<?> it = iterator();
		while (it.hasNext()) {
			if (c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}

	public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
	}

	public void replaceAll(UnaryOperator<E> operator) {
		disgurageUsage();
		source.replaceAll(operator);
	}

	public boolean removeIf(Predicate<? super E> filter) {
		disgurageUsage();
		return source.removeIf(filter);
	}

	public void sort(Comparator<? super E> c) {
		disgurageUsage();
		source.sort(c);
	}

	public void clear() {
		source.clear();
	}

	public boolean equals(Object o) {
		return source.equals(o);
	}

	public int hashCode() {
		return source.hashCode();
	}

	public E get(int index) {
		return fProxy(source.get(index));
	}

	public E set(int index, E element) {
		return fProxy(source.set(index, fSource(element)));
	}

	public void add(int index, E element) {
		source.add(index, fSource(element));
	}

	public Stream<E> stream() {
		disgurageUsage();
		return source.stream();
	}

	public E remove(int index) {
		return fProxy(source.remove(index));
	}

	public Stream<E> parallelStream() {
		disgurageUsage();
		return source.parallelStream();
	}

	public int indexOf(Object o) {
		return source.indexOf(fSource(o));
	}

	public int lastIndexOf(Object o) {
		return source.lastIndexOf(fSource(o));
	}

	public ListIterator<E> listIterator() {
		return fProxy(source.listIterator());
	}

	public ListIterator<E> listIterator(int index) {
		return fProxy(source.listIterator(index));
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return fProxy(source.subList(fromIndex, toIndex));
	}

	public Spliterator<E> spliterator() {
		disgurageUsage();
		return source.spliterator();
	}
}
