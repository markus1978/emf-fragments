package de.hub.emffrag;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class FStore implements EStore {

	private final static Cache<FStoreObject, FObject> fObjectCache = CacheBuilder.newBuilder().weakValues().build();
	protected final static FStore transientObjectsStore = new FStore();
	
	protected void registerProxy(FObject fObject) {
		fObjectCache.put(fObject.fStoreObject(), fObject);
	}
	
	private FObject proxify(FStoreObject fStoreObject) {
		if (fStoreObject == null) {
			return null;
		}
		FObject fObject = fObjectCache.getIfPresent(fStoreObject);
		if (fObject == null) {
			EObject eObject = fStoreObject.eClass().getEPackage().getEFactoryInstance().create(fStoreObject.eClass());
			fObject = (FObject)eObject;
			fObject.eSetStore(this);
			fObject.fSetStoreObject(fStoreObject);
			registerProxy(fObject);
		}
		return fObject;
	}
	
	private Object proxifyValue(EStructuralFeature feature, Object value) {
		if (feature instanceof EReference) {
			return proxify((FStoreObject)value);
		} else {
			return value;
		}
	}
	
	private Object deProxyValue(EStructuralFeature feature, Object rawValue) {
		Object value = (feature instanceof EReference) ? ((FObject)rawValue).fStoreObject() : rawValue;
		return value;
	}
	
	private void setProtentialContainer(EStructuralFeature feature, Object container, Object contents) {
		if (feature instanceof EReference && ((EReference)feature).isContainment()) {
			((FStoreObject)contents).fSetContainer((FStoreObject)container, feature.getFeatureID());
		}
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		FObject fObject = (FObject)object;
		Object rawValue = fObject.fStoreObject().fGet(feature);

		Object value = (feature.isMany()) ? ((List<?>)rawValue).get(index) : rawValue;
		return proxifyValue(feature, value);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object rawValue) {
		FObject fObject = (FObject)object;
		Object value = deProxyValue(feature, rawValue);
		Object previousValue =  fObject.fStoreObject().fGet(feature);
		if (feature.isMany()) {			
			previousValue = ((List)previousValue).set(index, value);
		} else {
			fObject.fStoreObject().fSet(feature, value);
		}
		
		setProtentialContainer(feature, fObject.fStoreObject(), value);
		return proxifyValue(feature, previousValue);		
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		return fObject.fStoreObject().fIsSet(feature);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		Object oldValue = fObject.fStoreObject().fGet(feature);
		fObject.fStoreObject().fUnSet(feature);
		setProtentialContainer(feature, null, oldValue);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).isEmpty();
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).size();
		}
		throw new IllegalArgumentException();
	}

	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).contains(deProxyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}



	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).indexOf(deProxyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).lastIndexOf(deProxyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object rawValue) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			Object value = deProxyValue(feature, rawValue);
			((List)fObject.fStoreObject().fGet(feature)).add(value);
			setProtentialContainer(feature, fObject.fStoreObject(), value);
			return;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			Object oldValue = ((List<?>)fObject.fStoreObject().fGet(feature)).remove(index);
			setProtentialContainer(feature, null, oldValue);
			return oldValue;
		}
		throw new IllegalArgumentException();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			Object value = ((List<?>)fObject.fStoreObject().fGet(feature)).remove(sourceIndex);
			((List)fObject.fStoreObject().fGet(feature)).add(targetIndex, value);
			return proxifyValue(feature, value);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			List<?> values = (List<?>)fObject.fStoreObject().fGet(feature);
			for (Object value: values) {
				setProtentialContainer(feature, null, value);
			}
			values.clear();
			return;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			List<?> values = ((List<?>)fObject.fStoreObject().fGet(feature));
			int size = values.size();
			Object[] result = new Object[size];
			for (int i = 0; i < size; i++) {
				result[i] = proxifyValue(feature, values.get(i));				
			}
			return result;
		}
		throw new IllegalArgumentException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			List<?> values = ((List<?>)fObject.fStoreObject().fGet(feature));
			int size = values.size();
			for (int i = 0; i < size; i++) {
				array[i] = (T)proxifyValue(feature, values.get(i));				
			}
			return array;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		Object value = fObject.fStoreObject().fGet(feature);
		if (value != null) {
			return value.hashCode();
		} else {
			return 0;
		}
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		FObject fObject = (FObject)object;
		return proxify(fObject.fStoreObject().fContainer());
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		FObject fObject = (FObject)object;
		return fObject.fStoreObject().fContainingFeature();
	}

	@Override
	public EObject create(EClass eClass) {
		FStoreObject fStoreObject = new FStoreObject(eClass);
		return proxify(fStoreObject);
	}

}
