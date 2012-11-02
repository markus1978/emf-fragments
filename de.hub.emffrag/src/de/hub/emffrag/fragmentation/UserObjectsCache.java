package de.hub.emffrag.fragmentation;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class UserObjectsCache {
	
	public interface UserObjectsCacheListener {
		public void handleUsed();
		public void handleReferenced();
		public void handleUnReferenced();
	}
	
	final static UserObjectsCache newUserObjectsCache = new UserObjectsCache();
	
	private final Map<FInternalObjectImpl, UserObjectReference> cache = new ConcurrentHashMap<FInternalObjectImpl, UserObjectReference>();
	private UserObjectsCacheListener listener = null;
	
	private class UserObjectReference extends WeakReference<FObjectImpl> {
		private final FInternalObjectImpl internalObject;
		public UserObjectReference(FObjectImpl referent, ReferenceQueue<? super FObjectImpl> q) {
			super(referent, q);
			internalObject = referent.internalObject();
		}		
	}
	
	void setListener(UserObjectsCacheListener listener) {
		this.listener = listener;
	}

	public FObjectImpl getUserObject(FInternalObjectImpl internalObject) {
		UserObjectReference reference = cache.get(internalObject);
		FObjectImpl result = null;
		if (reference == null) {
			result = createUserObject(internalObject);			
		} else {
			if (reference.get() == null) {
				cache.remove(internalObject);				
				result = getUserObject(internalObject);
			} else {
				result = reference.get();
			}
			
			if (listener != null && result != null) {
				listener.handleUsed();
			}
		}
	
		return result;		
	}

	public boolean hasReferences() {
		return !cache.isEmpty();
	}

	private FObjectImpl createUserObject(FInternalObjectImpl internalObject) {
		EPackage userMetaModel = ReflectiveMetaModelRegistry.instance.getOppositeMetaModel(internalObject.eClass().getEPackage());
		FObjectImpl userObject = (FObjectImpl) userMetaModel.getEFactoryInstance().create(ReflectiveMetaModelRegistry.instance.getOppositeClass(internalObject.eClass()));
		userObject.setInternalObject(internalObject);
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		return userObject;
	}

	FInternalObjectImpl createInternalObject(FObjectImpl userObject) {
		assert (userObject.internalObject() == null);
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not
		// added) internal objects

		// TODO this seems to have low performance
		EClass userClass = userObject.eClass();
		EPackage internalPackage = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(userClass.getEPackage());
		FInternalObjectImpl internalObject = new FInternalObjectImpl((EClass)internalPackage.getEClassifier(userClass.getName()));		
		
		boolean hasReferences = hasReferences();
		userObject.setInternalObject(internalObject);
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		if (listener != null && !hasReferences) {			
			listener.handleReferenced();
		}
		return internalObject;
	}

	public void addUserObjectToCache(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		cache.put(internalObject, new UserObjectReference(userObject, CacheController.instance().getAllUserObjectsReferenceQueue()));
		if (listener != null) {
			listener.handleUsed();
		}
	}

	public void removeCachedUserObject(FInternalObjectImpl internalObject) {
		cache.remove(internalObject);
	}
	
	private void handleRemovedReference(FInternalObjectImpl internalObject) {
		removeCachedUserObject(internalObject);
		if (listener != null && !hasReferences()) {
			listener.handleUnReferenced();
		}
	}

	public static void delegateRemovedReference(Reference<? extends FObjectImpl> reference) {
		UserObjectReference userObjectReference = (UserObjectReference)reference;
		FInternalObjectImpl internalObject = userObjectReference.internalObject;
		Fragment fragment = internalObject.getFragment();
		if (fragment != null) {
			fragment.getUserObjectsCache().handleRemovedReference(internalObject);
		}
	}

	/**
	 * This method is used for test purposes. It deliberately queues the
	 * corresponding weak references and therefore simulates the garbage
	 * collection of the given user object.
	 */
	static void deleteReference(FObjectImpl object) {
		FInternalObjectImpl internalObject = object.internalObject();
		if (internalObject != null) {
			UserObjectsCache userObjectsCache = newUserObjectsCache;
			Fragment fragment = internalObject.getFragment();
			if (fragment != null) {
				userObjectsCache = fragment.getUserObjectsCache();
			}
			
			UserObjectReference userObjectReference = userObjectsCache.cache.get(internalObject);
			if (userObjectReference != null) {
				userObjectReference.enqueue();
			}
		}
	}
}
