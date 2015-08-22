package de.hub.emffrag.proxies;

import java.util.Iterator;
import java.util.function.Consumer;

public class ProxyIteratorImpl<E> extends AbstractChildProxyImpl<Iterator<E>> implements Iterator<E>{

	public ProxyIteratorImpl(Iterator<E> source, Proxy parent) {
		super(source, parent);
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
