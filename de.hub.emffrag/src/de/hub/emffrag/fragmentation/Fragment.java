package de.hub.emffrag.fragmentation;

import org.apache.commons.collections.map.ReferenceMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class Fragment extends XMIResourceImpl {
	
	private ReferenceMap userObjectCache = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);
	
	public FObjectImpl getUserObject(EObject internalObject) {
		return (FObjectImpl)userObjectCache.get(internalObject);		
	}
				
	public boolean hasReferences() {	
		return !userObjectCache.isEmpty();
	}
	
	public void initializeUserObject(FObjectImpl userObject) {
		assert(userObject.internalObject == null);
		// TODO
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not added) internal objects
	}

	private void addCachedEObject(EObject internalObject, FObjectImpl userObject) {
		userObjectCache.put(internalObject, userObject);
	}

	private void removeCachedEObject(EObject internalObject, FObjectImpl userObject) {
		userObjectCache.remove(internalObject);
	}

}
