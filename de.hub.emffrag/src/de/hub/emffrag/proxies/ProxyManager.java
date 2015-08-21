package de.hub.emffrag.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;

import com.google.common.base.Preconditions;


public abstract class ProxyManager {
	
	private final ProxyContainer freeContainer;

	public ProxyManager(ProxyContainer freeContainer) {
		super();
		this.freeContainer = freeContainer;
	}

	public abstract boolean hasProxyType(Object source);	
	public abstract boolean hasProxyRootType(Object source);
	public abstract Class<?> getProxyType(Object source);	
	public abstract Class<?>[] getParentTypes(Class<?> proxyType);
	
	protected abstract ProxyContainer getContainerFromProxyRootSource(Object source);
	protected abstract void onFinishedClientOperation(Object source);
	
	private Proxy createNewProxy(Object source, ProxyContainer proxyContainer) {
		Preconditions.checkArgument(!(source instanceof Proxy));
		
		List<Class<?>> helper = new ArrayList<Class<?>>();
		
		Class<? extends Object> sourceClass = source.getClass();

		List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(sourceClass);
		helper.addAll(allInterfaces);
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
		
		ProxyContainer result = getContainerFromProxyRootSource(rootSource);
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
			existingProxy = createNewProxy(source, proxyContainer);
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
		
		private ProxyContainer pContainer = null;

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
		public void dSetContainer(ProxyContainer container) {
			this.pContainer = container;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getDeclaringClass() == Proxy.class) {
				return method.invoke(this, args);
			} else {	
				boolean isEMFOperation = false;
				if (args == null) {
					args = new Object[]{};
				}
				Object[] sourceArgs = new Object[args.length];
				for (int i = 0; i < sourceArgs.length; i++) {
					sourceArgs[i] = getSource(args[i]);
					isEMFOperation |= sourceArgs[i] != args[i];
				}
				
				Object result = method.invoke(source, sourceArgs);
				if (hasProxyType(result)) {
					isEMFOperation = true;
					if (hasProxyRootType(result)) {
						result = getProxy(result, getContainer(result, null));
					} else {
						Proxy resultProxy = getProxyWithParent(result, (Proxy)proxy);
						resultProxy.fSetParentProxy((Proxy)proxy);												
						result = resultProxy;
					}					
				}
				
				if (isEMFOperation) {
					onFinishedClientOperation(source);
				}
				return result;
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
