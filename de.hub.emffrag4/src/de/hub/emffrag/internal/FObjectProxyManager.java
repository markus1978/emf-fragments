package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.hub.emffrag.FObject;
import de.hub.emffrag.FObjectImpl;

public class FObjectProxyManager {

	private final Cache<FStoreObject, FObject> objectCache = CacheBuilder.newBuilder().weakValues().build();
	private final Cache<FURI, FObject> proxyCache = CacheBuilder.newBuilder().weakValues().build();
	
	public FObject getFObject(FStoreObject fStoreObject) {
		FObject fObject = null;
		if (fStoreObject == null) {
			return null;
		} else if (fStoreObject.fIsProxy()) {
			FURI uri = fStoreObject.fProxyURI();
			fObject = proxyCache.getIfPresent(uri);
			FStoreObject resolved = fStoreObject.resolve(true);
			if (resolved == null) {
				throw new RuntimeException("Could not resolve " + fStoreObject.fProxyURI());
			} else {
				fStoreObject = resolved;
			}
		} 
		
		if (fObject == null) {
			fObject = objectCache.getIfPresent(fStoreObject);
		}
		
		if (fObject == null) {			
			EObject eObject = fStoreObject.fClass().getEPackage().getEFactoryInstance().create(fStoreObject.fClass());
			fObject = (FObjectImpl)eObject;
			((FObjectImpl)fObject).fSetStoreObject(fStoreObject);
			registerFObject(fStoreObject, fObject);
		}
		return fObject;
	}
	
	public void registerFObject(FStoreObject fStoreObject, FObject fObject) {
		objectCache.put(fStoreObject, fObject);
	}
	
	public void onFStoreObjectLoaded(FURI uri, FStoreObject fStoreObject) {
		FObject fObject = proxyCache.getIfPresent(uri);
		if (fObject != null) {
			proxyCache.invalidate(uri);
			objectCache.put(fStoreObject, fObject);
		}
	}
	
	public void onFStoreObjectUnloaded(FStoreObject fStoreObject, FURI uri) {
		FObject fObject = objectCache.getIfPresent(fStoreObject);
		if (fObject != null) {
			objectCache.invalidate(fStoreObject);
			proxyCache.put(uri, fObject);
		}
	}
	
	public void reset() {
		List<FStoreObject> keys = new ArrayList<FStoreObject>(objectCache.asMap().keySet());
		for (FStoreObject key: keys) {
			if (key.fFragmentation() != null) {
				objectCache.invalidate(key);
			}
		}
		proxyCache.invalidateAll();
	}
	
	public void fullReset() {		
		objectCache.invalidateAll();
		proxyCache.invalidateAll();
	}
	
	public void removeProxies(URI fragmentationURI) {
		Iterator<FURI> iterator = proxyCache.asMap().keySet().iterator();
		while(iterator.hasNext()) {
			FURI proxyURI = iterator.next();
			URI proxyURIsFragmentation = proxyURI.fragmentation();
			if ((fragmentationURI != null && fragmentationURI.equals(proxyURIsFragmentation)) || fragmentationURI == proxyURIsFragmentation) {
				iterator.remove();
			}
		}
	}
}
