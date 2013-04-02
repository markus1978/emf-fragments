package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.apache.commons.collections.map.ReferenceMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * Caches {@link FObjectImpl} instances for {@link FInternalObjectImpl}
 * instances to avoid unnecessary duplication of user values. Each
 * {@link Fragment} is associated with {@link UserObjectsCache}, Before a new
 * user object is created, the corresponding {@link UserObjectsCache} is
 * consulted.
 */
public class UserObjectsCache {

	final static UserObjectsCache newUserObjectsCache = new UserObjectsCache();
	
	private final ReferenceMap cache = new ReferenceMap();

	void moveUserObject(FInternalObjectImpl internalObject, UserObjectsCache newCache) {
		FObjectImpl userObject = (FObjectImpl)cache.get(internalObject);
		if (userObject != null) {
			cache.remove(internalObject);
			newCache.addUserObjectToCache(internalObject, userObject);
		}
	}

	public FObjectImpl getUserObject(FInternalObjectImpl internalObject) {
		FObjectImpl result = (FObjectImpl)cache.get(internalObject);
		if (result == null) {
			result = createUserObject(internalObject);
		}

		return result;
	}

	private FObjectImpl createUserObject(FInternalObjectImpl internalObject) {
		EPackage userMetaModel = ReflectiveMetaModelRegistry.instance.getUserMetaModel(internalObject.eClass().getEPackage());
		FObjectImpl userObject = (FObjectImpl) userMetaModel.getEFactoryInstance().create(
				ReflectiveMetaModelRegistry.instance.getUserClass(internalObject.eClass()));
		userObject.fSetInternalObject(internalObject);
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		return userObject;
	}

	FInternalObjectImpl createInternalObject(FObjectImpl userObject) {
		assert (userObject.fInternalObject() == null);
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not
		// added) internal objects

		// TODO this seems to have low performance
		EClass userClass = userObject.eClass();
		EPackage internalPackage = ReflectiveMetaModelRegistry.instance.registerUserMetaModel(userClass.getEPackage());
		FInternalObjectImpl internalObject = new FInternalObjectImpl((EClass) internalPackage.getEClassifier(userClass.getName()));

		userObject.fSetInternalObject(internalObject);
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		return internalObject;
	}

	public void addUserObjectToCache(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		cache.put(internalObject,userObject);		
	}

	public void removeCachedUserObject(FInternalObjectImpl internalObject) {
		cache.remove(internalObject);
	}

	void assertNotCached(EObject object) {
		Assert.assertNull("Object is cached.", cache.get(((FObjectImpl) object).fInternalObject()));
	}

	void assertCached(EObject object) {
		Assert.assertNotNull("Object not is cached.", cache.get(((FObjectImpl) object).fInternalObject()));
	}

	boolean assertHasReferences() {	
		return !cache.isEmpty();
	}
}
