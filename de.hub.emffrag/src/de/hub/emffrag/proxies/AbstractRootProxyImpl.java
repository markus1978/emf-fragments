package de.hub.emffrag.proxies;


public abstract class AbstractRootProxyImpl<T> extends AbstractProxyImpl<T> implements RootProxy {

	
	public AbstractRootProxyImpl(T source) {
		super(source);
	}

	@Override
	public RootProxy fRoot() {
		return this;
	}

}
