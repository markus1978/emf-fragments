package de.hub.emffrag.fragmentation;

import org.apache.commons.collections.map.ReferenceMap;

public class UserObjectsCache {

	public final static UserObjectsCache newUserObjectsCache = new UserObjectsCache();
	private ReferenceMap userObjectCache = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);
	
	public FObjectImpl getUserObject(FInternalObjectImpl internalObject) {
		return (FObjectImpl)userObjectCache.get(internalObject);		
	}
	
	public boolean hasReferences() {	
		return !userObjectCache.isEmpty();
	}
	
	public FObjectImpl createUserObject(FInternalObjectImpl internalObject) {
		// TODO investigate the need for internal and user meta-models
		FObjectImpl userObject = null;
		userObject.internalObject = internalObject;
		UserObjectsCache.newUserObjectsCache.addUserObjectToCache(internalObject, (FObjectImpl)userObject);
		return userObject;
	}
	
	public FInternalObjectImpl createInternalObject(FObjectImpl userObject) {
		assert(userObject.internalObject == null);
		// TODO investigate the need for internal and user meta-models
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not added) internal objects
		FInternalObjectImpl internalObject = null;
		UserObjectsCache.newUserObjectsCache.addUserObjectToCache(internalObject, (FObjectImpl)userObject);
		return internalObject;
	}

	public void addUserObjectToCache(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		userObjectCache.put(internalObject, userObject);
	}
	
	public void removeCachedUserObject(FInternalObjectImpl internalObject) {
		userObjectCache.remove(internalObject);
	}
	
}
