package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.resource.Resource;

public interface Fragment extends Resource {

	UserObjectsCache getUserObjectsCache();

	public FragmentedModel getFragmentedModel();
	
//	public void setID(FInternalObjectImpl object, String id);
//	
//	public String getID(FInternalObjectImpl object);

}
