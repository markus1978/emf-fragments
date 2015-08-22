package de.hub.emffrag.proxies;


public abstract class AbstractProxyImpl<T> implements Proxy {

	protected T source;
	
	public AbstractProxyImpl(T source) {
		super();
		this.source = source;
	}
	
	public void fSetSource(T source) {
		this.source = source;
	}

	@Override
	public Object fSource() {
		return source;
	}

	@SuppressWarnings("unchecked")
	protected final <E> E fProxy(E source) {
		if (source == null) {
			return null;
		}
		ProxyContainer proxyContainer = fRoot().fContainer();
		Proxy result = proxyContainer.fGetProxyIfExists(source);
		if (result == null) {
			result = ProxyFactory.INSTANCE.create(source, this);
			if (result != null) {
				proxyContainer.fPutProxy(source, result);	
			}
		}
		
		return (E)(result == null ? source : result);
	}
	
	@SuppressWarnings("unchecked")
	protected final <E> E fSource(E object) {
		if (object instanceof Proxy) {
			return (E)((Proxy)object).fSource();
		} else {
			return object;
		}
	}
	
	protected final <E> E disgurageUsage() {
		throw new IllegalAccessError("The use of this method by clients is forbidded, since it interferes with emf-fragments operation.");
	}
	
}
