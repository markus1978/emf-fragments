package de.hub.emffrag.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;


public abstract class ProxyManager {
	
	private final ProxyContainer freeContainer;

	public ProxyManager(ProxyContainer freeContainer) {
		super();
		this.freeContainer = freeContainer;
	}

	protected abstract boolean hasProxyType(Object source);	
	protected abstract boolean hasProxyRootType(Object source);
	protected abstract Class<?> getProxyType(Object source);	
	protected abstract Class<?>[] getParentTypes(Class<?> proxyType);
	
	protected abstract ProxyContainer getContainerFromProxyRootSource(Object source);
	
	private Proxy createNewProxy(Object source, ProxyContainer proxyContainer) {
		Preconditions.checkArgument(!(source instanceof Proxy));
		
		List<Class<?>> helper = new ArrayList<Class<?>>();
		
		Class<? extends Object> sourceClass = source.getClass();

		for(Class<?> sourceInterface: sourceClass.getInterfaces()) {
			helper.add(sourceInterface);
		}
		helper.add(Proxy.class);
		Proxy proxy = (Proxy)java.lang.reflect.Proxy.newProxyInstance(
				sourceClass.getClassLoader(), helper.toArray(new Class<?>[]{}), new ProxyHandler(source));
		proxyContainer.fPutProxy(source, proxy);
		return proxy;
	}
	
	private ProxyContainer getContainer(Object source, Proxy parent) {
		Preconditions.checkArgument(hasProxyRootType(source) ? parent == null : parent != null);
		Object rootSource = hasProxyRootType(source) ? source : parent.fGetRootSource();
		Preconditions.checkArgument(hasProxyRootType(rootSource));
		
		ProxyContainer result = getContainerFromProxyRootSource(source);
		if (result == null) {
			return freeContainer;
		} else {
			return result;
		}			
	}
	
	public Proxy getProxy(Object source, ProxyContainer proxyContainer) {
		Preconditions.checkArgument(proxyContainer != null);
		
		Proxy existingProxy = proxyContainer.fGetProxyIfExists(source);
		if (existingProxy == null) {
			createNewProxy(source, proxyContainer);
		}
		return existingProxy;
	}
	
	/**
	 * Creates a new proxy if no proxy exists in the cache for the given source.
	 */
	private Proxy getProxyWithParent(Object source, Proxy parent) {
		Preconditions.checkArgument(hasProxyRootType(source) ? parent == null : parent != null);
		return getProxy(source, getContainer(source, parent));		
	}
	
	private Object getSource(Object proxy) {
		return (proxy instanceof Proxy) ? ((Proxy)proxy).fGetSource() : proxy;
	}
	
	private class ProxyHandler implements InvocationHandler, Proxy {
		private final Object source;
		private Proxy parentProxy = null;

		public ProxyHandler(Object source) {
			super();
			this.source = source;
		}

		@Override
		public void fSetParentProxy(Proxy parentProxy) {
			this.parentProxy = parentProxy;
		}

		@Override
		public Object fGetSource() {
			return source;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getDeclaringClass() == Proxy.class) {
				return method.invoke(this, args);
			} else {	
				Object[] sourceArgs = new Object[args.length];
				for (int i = 0; i < sourceArgs.length; i++) {
					sourceArgs[i] = getSource(args[i]);
				}
				
				Object result = method.invoke(source, sourceArgs);
				if (hasProxyType(result)) {
					if (hasProxyRootType(result)) {
						return getProxy(result, null);
					} else {
						Proxy resultProxy = getProxyWithParent(result, (Proxy)proxy);
						resultProxy.fSetParentProxy((Proxy)proxy);												
						return resultProxy;
					}					
				} else {
					return result;
				}
			}
		}

		@Override
		public Object fGetRootSource() {
			if (parentProxy == null) {
				return source; 
			} else {
				return parentProxy.fGetRootSource();
			}
		}	
	}
}
