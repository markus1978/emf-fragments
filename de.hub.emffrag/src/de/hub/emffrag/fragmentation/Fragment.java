package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.proxies.ProxyContainer;

public interface Fragment extends Resource, ProxyContainer {
	public long fFragmentId();
	public boolean fIsRoot();
	public Fragmentation fFragmentation();
}
