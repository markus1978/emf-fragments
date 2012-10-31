package de.hub.emffrag.fragmentation;

import org.apache.commons.collections.map.ReferenceMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class UserObjectsCache {

	public final static UserObjectsCache newUserObjectsCache = new UserObjectsCache();
	private ReferenceMap userObjectCache = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);

	public FObjectImpl getUserObject(FInternalObjectImpl internalObject) {
		FObjectImpl result = (FObjectImpl) userObjectCache.get(internalObject);
		if (result == null) {
			result = createUserObject(internalObject);
		}
		return result;
	}

	public boolean hasReferences() {
		return !userObjectCache.isEmpty();
	}

	private FObjectImpl createUserObject(FInternalObjectImpl internalObject) {
		EPackage userMetaModel = ReflectiveMetaModelRegistry.instance.getOppositeMetaModel(internalObject.eClass().getEPackage());
		FObjectImpl userObject = (FObjectImpl) userMetaModel.getEFactoryInstance().create(ReflectiveMetaModelRegistry.instance.getOppositeClass(internalObject.eClass()));
		userObject.setInternalObject(internalObject);
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		return userObject;
	}

	public FInternalObjectImpl createInternalObject(FObjectImpl userObject) {
		assert (userObject.internalObject() == null);
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not
		// added) internal objects

		// TODO this seems to have low performance
		EClass userClass = userObject.eClass();
		EPackage internalPackage = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(userClass.getEPackage());
		FInternalObjectImpl internalObject = new FInternalObjectImpl((EClass)internalPackage.getEClassifier(userClass.getName()));		
		
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		return internalObject;
	}

	public void addUserObjectToCache(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		userObjectCache.put(internalObject, userObject);
	}

	public void removeCachedUserObject(FInternalObjectImpl internalObject) {
		userObjectCache.remove(internalObject);
	}

}
