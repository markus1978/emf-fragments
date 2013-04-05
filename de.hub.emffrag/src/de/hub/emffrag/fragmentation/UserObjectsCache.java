package de.hub.emffrag.fragmentation;

import org.apache.commons.collections.map.ReferenceMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * Caches {@link FObjectImpl} instances for {@link FInternalObjectImpl}
 * instances to avoid unnecessary duplication of user values. Each
 * {@link Fragment} is associated with {@link UserObjectsCache}, Before a new
 * user object is created, the corresponding {@link UserObjectsCache} is
 * consulted.
 */
public class UserObjectsCache {

	final static UserObjectsCache instance = new UserObjectsCache();
	
	private final ReferenceMap cache = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);

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
		cache.put(internalObject,userObject);	
		userObject.fSetInternalObject(internalObject);
		return userObject;
	}

	FInternalObjectImpl createInternalObject(FObjectImpl userObject) {
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not
		// added) internal objects

		// TODO this seems to have low performance
		EClass userClass = userObject.eClass();
		EPackage internalPackage = ReflectiveMetaModelRegistry.instance.registerUserMetaModel(userClass.getEPackage());
		FInternalObjectImpl internalObject = new FInternalObjectImpl((EClass) internalPackage.getEClassifier(userClass.getName()));

		cache.put(internalObject,userObject);	
		userObject.fSetInternalObject(internalObject);
		return internalObject;
	}
}
