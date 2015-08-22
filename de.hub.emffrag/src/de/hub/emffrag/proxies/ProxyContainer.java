package de.hub.emffrag.proxies;

import java.util.Collection;

public interface ProxyContainer {
	
	public boolean fHasProxies();
	
	/**
	 * @return the sources of all the children of the removed proxy.
	 */
	public Collection<Object> fRemoveProxy(Object source);
	
	public Proxy fGetProxyIfExists(Object source);
	
	public void fPutProxy(Object source, Proxy proxy);
}
