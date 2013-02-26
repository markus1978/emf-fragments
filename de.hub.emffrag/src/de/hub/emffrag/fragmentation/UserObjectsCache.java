package de.hub.emffrag.fragmentation;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Throwables;

public class UserObjectsCache {
	
	public interface UserObjectsCacheListener {
		public void handleUsed();
		public void handleReferenced();
		public void handleUnReferenced();
	}
	
	private final static ExecutorService es = Executors.newSingleThreadExecutor();
	
	final static UserObjectsCache newUserObjectsCache = new UserObjectsCache();
	private static UOCController uocController = new UOCController();
	
	private final Map<FInternalObjectImpl, UserObjectReference> cache = new ConcurrentHashMap<FInternalObjectImpl, UserObjectReference>();
	private UserObjectsCacheListener listener = null;
	
	private class UserObjectReference extends WeakReference<FObjectImpl> {
		private final FInternalObjectImpl internalObject;
		public UserObjectReference(FObjectImpl referent, ReferenceQueue<? super FObjectImpl> q) {
			super(referent, q);
			internalObject = referent.fInternalObject();
		}		
	}
	
	static class UOCController implements Runnable {	
		private final ReferenceQueue<FObjectImpl> userObjectsReferenceQueue = new ReferenceQueue<FObjectImpl>();
		private Future<?> future = null; 
		
		@Override
		public void run() {
			try {
				Reference<? extends FObjectImpl> reference;
				while ((reference = userObjectsReferenceQueue.remove()) != null) {
					FInternalObjectImpl internalObject = ((UserObjectReference)reference).internalObject;
					UserObjectsCache uoc = internalObject.getUserObjectCache();
					uoc.handleRemovedReference(internalObject);
				}
			} catch (InterruptedException e) {
				Throwables.propagate(e);
			}
		}
		
		UOCController() {
			future = es.submit(this);
		}
		
		void shutdown() {
			future.cancel(true);
		}
	}
	
	static void resetUOCController() {
		uocController.shutdown();
		uocController = new UOCController();
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
		EPackage internalPackage = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(userClass.getEPackage());
		FInternalObjectImpl internalObject = new FInternalObjectImpl((EClass)internalPackage.getEClassifier(userClass.getName()));		
		
		boolean hasReferences = hasReferences();
		userObject.fSetInternalObject(internalObject);
		addUserObjectToCache(internalObject, (FObjectImpl) userObject);
		if (listener != null && !hasReferences) {			
			listener.handleReferenced();
		}
		return internalObject;
	}

	public void addUserObjectToCache(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		cache.put(internalObject, new UserObjectReference(userObject, uocController.userObjectsReferenceQueue));
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

	/**
	 * This method is used for test purposes. It deliberately queues the
	 * corresponding weak references and therefore simulates the garbage
	 * collection of the given user object.
	 */
	static void deleteReference(FObjectImpl object) {
		FInternalObjectImpl internalObject = object.fInternalObject();
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
