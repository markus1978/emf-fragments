package de.hub.emffrag.proxies;


public interface Proxy {
	public Object fGetSource();
	public Object fGetRootSource();
	public void fSetParentProxy(Proxy parentProxy);
}