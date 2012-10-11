package de.hub.emffrag.fragmentation;

import org.apache.commons.collections.map.ReferenceMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class Fragment extends XMIResourceImpl {
	
	private ReferenceMap userObjectCache = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);
	private FragmentedModel model = null;
	
	public FObjectImpl getUserObject(EObject internalObject) {
		return (FObjectImpl)userObjectCache.get(internalObject);		
	}
				
	public boolean hasReferences() {	
		return !userObjectCache.isEmpty();
	}
	
	public void initializeUserObject(FObjectImpl userObject) {
		assert(userObject.internalObject == null);
		// TODO initialize new user objects
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not added) internal objects
	}

	public void addUserObjectToCache(EObject internalObject, FObjectImpl userObject) {
		userObjectCache.put(internalObject, userObject);
	}

	public void removeCachedUserObject(EObject internalObject) {
		userObjectCache.remove(internalObject);
	}
	
	protected void setFragmentedModel(FragmentedModel model) {
		this.model = model;
	}

	public FragmentedModel getFragmentedModel() {
		return model;
	}

}
