package de.hub.emffrag.proxies;

import java.util.function.Consumer;

import org.eclipse.emf.common.util.TreeIterator;

public class ProxyTreeIteratorImpl<E> extends AbstractChildProxyImpl<TreeIterator<E>> implements TreeIterator<E> {

	public ProxyTreeIteratorImpl(TreeIterator<E> source, Proxy parent) {
		super(source, parent);
	}

	public void prune() {
		source.prune();
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

