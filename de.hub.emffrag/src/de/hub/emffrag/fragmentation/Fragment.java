package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.resource.Resource;

public interface Fragment extends Resource {
	public long fFragmentId();
	public boolean fIsRoot();
	public Fragmentation fFragmentation();
}
