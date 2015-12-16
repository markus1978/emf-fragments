package de.hub.emffrag;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class Fragmentation {
	
	private final static Fragmentation transientFragmentation = new Fragmentation();
	
	private final Map<Long, Fragment> loadedFragments = new HashMap<Long, Fragment>();
	private final FStore store = new FStore(this);	
	private final static Cache<FStoreObject, FObjectImpl> fObjectCache = CacheBuilder.newBuilder().weakValues().build();
	
	protected static FStore transientStore() {
		return transientFragmentation.store;
	}
	
	protected FObjectImpl proxify(FStoreObject fStoreObject) {
		if (fStoreObject == null) {
			return null;
		}
		FObjectImpl fObject = fObjectCache.getIfPresent(fStoreObject);
		if (fObject == null) {
			EObject eObject = fStoreObject.eClass().getEPackage().getEFactoryInstance().create(fStoreObject.eClass());
			fObject = (FObjectImpl)eObject;
			fObject.eSetStore(store);
			fObject.fSetStoreObject(fStoreObject);
			registerNewObject(fObject);
		}
		return fObject;
	}
	
	protected Object proxifyValue(EStructuralFeature feature, Object value) {
		if (feature instanceof EReference) {
			return proxify((FStoreObject)value);
		} else {
			return value;
		}
	}
	
	protected Object deProxyValue(EStructuralFeature feature, Object rawValue) {
		Object value = (feature instanceof EReference) ? ((FObject)rawValue).fStoreObject() : rawValue;
		return value;
	}

	protected void registerNewObject(FObjectImpl fObject) {
		fObjectCache.put(fObject.fStoreObject(), fObject);
	}
	
	protected void registerContainment(EStructuralFeature feature, FStoreObject container, FStoreObject contents) {
		
	}
}
