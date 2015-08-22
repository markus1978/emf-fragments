package de.hub.emffrag.proxies;

import java.util.ListIterator;
import java.util.function.Consumer;

public class ProxyListIteratorImpl<E> extends AbstractChildProxyImpl<ListIterator<E>> implements ListIterator<E> {

	public ProxyListIteratorImpl(ListIterator<E> source, Proxy parent) {
		super(source, parent);
	}

	public boolean hasPrevious() {
		return source.hasPrevious();
	}

	public E previous() {
		return fProxy(source.previous());
	}

	public int nextIndex() {
		return source.nextIndex();
	}

	public int previousIndex() {
		return source.previousIndex();
	}

	public void set(E e) {
		source.set(fSource(e));
	}

	public void add(E e) {
		source.add(fSource(e));
	}

	public boolean hasNext() {
		return source.hasNext();
	}

	public E next() {
		return fProxy(source.next());
	}

	public void remove() {
		source.remove();
	}

	public void forEachRemaining(Consumer<? super E> action) {
		source.forEachRemaining(action);
	}
}
