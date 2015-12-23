package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.hub.emffrag.FObject;
import de.hub.emffrag.FObjectImpl;
import de.hub.emffrag.FURI;

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
			fStoreObject = fStoreObject.fFragmentation().resolve(fStoreObject.fProxyURI());			
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
//		System.out.println("<-" + fStoreObject);
		FObject fObject = proxyCache.getIfPresent(uri);
		if (fObject != null) {
			proxyCache.invalidate(uri);
			objectCache.put(fStoreObject, fObject);
		}
	}
	
	public void onFStoreObjectUnloaded(FStoreObject fStoreObject, FURI uri) {
//		System.out.println("->" + fStoreObject);
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
}
