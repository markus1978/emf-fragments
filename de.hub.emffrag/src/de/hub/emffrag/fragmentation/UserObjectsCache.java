package de.hub.emffrag.fragmentation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Caches {@link FObjectImpl} instances for {@link FInternalObjectImpl}
 * instances to avoid unnecessary duplication of user values. When an internal
 * object gets unloaded the cache entry is replaced with a cache entry based on
 * URI. If an internal object is loaded, this is done vice versa.
 */
public class UserObjectsCache {

	public final static UserObjectsCache instance = new UserObjectsCache();

	private final Cache<FInternalObjectImpl, FObjectImpl> cache;
	private final Cache<URI, FObjectImpl> uriCache;

	public UserObjectsCache() {
		cache = CacheBuilder.newBuilder().weakValues().build();
		uriCache = CacheBuilder.newBuilder().weakValues().build();
	}

	public FObjectImpl getUserObject(final FInternalObjectImpl internalObject) {
		try {
			FObjectImpl userValue = cache.getIfPresent(internalObject);
			if (userValue == null) {
				URI uri = EcoreUtil.getURI(internalObject);
				userValue = uriCache.getIfPresent(uri);
				if (userValue != null) {
					uriCache.invalidate(uri);
					cache.put(internalObject, userValue);
					userValue.fSetInternalObject(internalObject, true);
				}
			}

			if (userValue == null) {
				return cache.get(internalObject, new Callable<FObjectImpl>() {
					@Override
					public FObjectImpl call() throws Exception {
						return createUserObject(internalObject);
					}
				});
			} else {
				return userValue;
			}
		} catch (ExecutionException e) {
			throw new RuntimeException();
		}
	}

	private FObjectImpl createUserObject(FInternalObjectImpl internalObject) {
		EPackage userMetaModel = ReflectiveMetaModelRegistry.instance
				.getUserMetaModel(internalObject.eClass().getEPackage());
		FObjectImpl userObject = (FObjectImpl) userMetaModel
				.getEFactoryInstance().create(
						ReflectiveMetaModelRegistry.instance
								.getUserClass(internalObject.eClass()));
		userObject.fSetInternalObject(internalObject, false);
		cache.put(internalObject, userObject);
		return userObject;
	}

	FInternalObjectImpl createInternalObject(FObjectImpl userObject) {
		// create an uncontained internal object
		// add the new internal object to the map for all uncontained (not
		// added) internal objects

		// TODO this seems to have low performance
		EClass userClass = userObject.eClass();
		EPackage internalPackage = ReflectiveMetaModelRegistry.instance
				.registerUserMetaModel(userClass.getEPackage());
		FInternalObjectImpl internalObject = new FInternalObjectImpl(
				(EClass) internalPackage.getEClassifier(userClass.getName()));

		cache.put(internalObject, userObject);
		userObject.fSetInternalObject(internalObject, false);
		return internalObject;
	}

	public int size() {
		cache.cleanUp();
		return (int) cache.size();
	}

	public boolean hasUserObject(FInternalObjectImpl instance) {
		FObjectImpl userValue = cache.getIfPresent(instance);
		if (userValue == null) {
			URI uri = EcoreUtil.getURI(instance);
			userValue = uriCache.getIfPresent(uri);
		}

		return userValue != null;
	}

	public void unload(FInternalObjectImpl object) {
		FObjectImpl userValue = cache.getIfPresent(object);
		if (userValue != null) {
			cache.invalidate(object);
			URI uri = EcoreUtil.getURI(object);
			if (uri.segmentCount() > 0) {
				uriCache.put(uri, userValue);
			}
		}
	}
}
