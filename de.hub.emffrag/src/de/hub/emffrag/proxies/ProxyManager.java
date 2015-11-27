package de.hub.emffrag.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ClassUtils;

import com.google.common.base.Preconditions;

import de.hub.jstattrack.CountStatistic;
import de.hub.jstattrack.services.BatchedPlot;
import de.hub.jstattrack.services.Summary;


public abstract class ProxyManager {
	
	private final ProxyContainer freeContainer;
	private CountStatistic proxyStat = new CountStatistic(1, TimeUnit.MINUTES).with(Summary.class).with(BatchedPlot.class).register(ProxyManager.class, "Created proxies");

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
	
	private Map<Class<?>, Class<?>[]> classToInterfacesMap = new HashMap<Class<?>, Class<?>[]>();
	
	private Proxy createNewProxy(Object source, ProxyContainer proxyContainer) {
		Preconditions.checkArgument(!(source instanceof Proxy));
		
		Class<?> sourceClass = source.getClass();
		Class<?>[] interfaces = classToInterfacesMap.get(sourceClass);
		if (interfaces == null) {
			List<Class<?>> helper = new ArrayList<Class<?>>();		
			List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(sourceClass);
			helper.addAll(allInterfaces);
			helper.add(DynamicProxy.class);
			interfaces = helper.toArray(new Class<?>[]{});
			classToInterfacesMap.put(sourceClass, interfaces);
		}
		
		Proxy proxy = (Proxy)java.lang.reflect.Proxy.newProxyInstance(sourceClass.getClassLoader(), interfaces, new ProxyHandler(source));
		proxyContainer.fPutProxy(source, proxy);
		proxyStat.track();
		return proxy;
	}
	
	private ProxyContainer getContainer(Object source, Proxy parent) {
		Preconditions.checkArgument(hasProxyRootType(source) ? parent == null : parent != null);
		Object rootSource = hasProxyRootType(source) ? source : parent.fRoot().fSource();
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
	
	protected Object resolve(Object object, ProxyContainer container) {
		return object;
	}
	
	protected Method replace(Method method) {
		return method;
	}
	
	/**
	 * Creates a new proxy if no proxy exists in the cache for the given source.
	 */
	private Proxy getProxyWithParent(Object source, Proxy parent) {
		Preconditions.checkArgument(hasProxyRootType(source) ? parent == null : parent != null);
		return getProxy(source, getContainer(source, parent));		
	}
	
	private Object getSource(Object proxy) {
		return (proxy instanceof Proxy) ? ((Proxy)proxy).fSource() : proxy;
	}
	
	private class ProxyHandler implements InvocationHandler, DynamicProxy, RootProxy {
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
		public Object fSource() {
			return source;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getDeclaringClass() == Proxy.class || method.getDeclaringClass() == DynamicProxy.class) {
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
				
				Object result = replace(method).invoke(source, sourceArgs);
				if (result instanceof Object[]) {
					Object[] array = (Object[])result;
					for (int i = 0; i < array.length; i++) {
						Object resultItem = array[i];
						resultItem = resolve(resultItem, ((Proxy)proxy).fRoot().fContainer());
						if (hasProxyType(resultItem)) {
							isEMFOperation = true;
							resultItem = proxify(resultItem, proxy);
						}
						array[i] = resultItem;
					}
					result = array;
				} else {
					result = resolve(result, ((Proxy)proxy).fRoot().fContainer());
					if (hasProxyType(result)) {
						isEMFOperation = true;
						result = proxify(result, proxy);
					}
				}				
				
				if (isEMFOperation) {
					onFinishedClientOperation(source);
				}
				return result;
			}
		}
		
		private Object proxify(Object result, Object proxy) {			
			if (hasProxyRootType(result)) {
				result = getProxy(result, getContainer(result, null));
			} else {
				Proxy resultProxy = getProxyWithParent(result, (Proxy)proxy);
				if (resultProxy instanceof DynamicProxy) {
					((DynamicProxy)resultProxy).fSetParentProxy((Proxy)proxy);
				}
				result = resultProxy;
			}					
			return result;
		}

		@Override
		public RootProxy fRoot() {
			if (parentProxy == null) {
				return this; 
			} else {
				return parentProxy.fRoot();
			}
		}

		@Override
		public ProxyContainer fContainer() {
			return getContainer(source, parentProxy);
		}	
	}
}
