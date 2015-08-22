package de.hub.emffrag.proxies;


public abstract class AbstractChildProxyImpl<T> extends AbstractProxyImpl<T> {

	private final Proxy parent;

	public AbstractChildProxyImpl(T source, Proxy parent) {
		super(source);
		this.parent = parent;
	}

	@Override
	public RootProxy fRoot() {
		return parent.fRoot();
	}
}
