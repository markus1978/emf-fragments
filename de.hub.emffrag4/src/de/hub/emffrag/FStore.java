package de.hub.emffrag;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

public class FStore implements EStore {
	
	private final Fragmentation fragmentation;
	
	public FStore(Fragmentation fragmentation) {
		super();
		this.fragmentation = fragmentation;
	}
	
	public Fragmentation fragmentation() {
		return fragmentation;
	}

	private void setProtentialContainer(EStructuralFeature feature, Object container, Object contents) {
		if (feature instanceof EReference && ((EReference)feature).isContainment()) {
			((FStoreObject)contents).fSetContainer((FStoreObject)container, feature.getFeatureID());
			fragmentation.registerContainment(feature, (FStoreObject)container, (FStoreObject)contents);
		}
	}
	
	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		FObject fObject = (FObject)object;
		Object rawValue = fObject.fStoreObject().fGet(feature);

		Object value = (feature.isMany()) ? ((List<?>)rawValue).get(index) : rawValue;
		return fragmentation.proxifyValue(feature, value);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object rawValue) {
		FObject fObject = (FObject)object;
		Object value = fragmentation.deProxyValue(feature, rawValue);
		Object previousValue =  fObject.fStoreObject().fGet(feature);
		if (feature.isMany()) {			
			previousValue = ((List)previousValue).set(index, value);
		} else {
			fObject.fStoreObject().fSet(feature, value);
		}
		
		setProtentialContainer(feature, fObject.fStoreObject(), value);
		return fragmentation.proxifyValue(feature, previousValue);		
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
			return ((List<?>)fObject.fStoreObject().fGet(feature)).contains(fragmentation.deProxyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}



	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).indexOf(fragmentation.deProxyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).lastIndexOf(fragmentation.deProxyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object rawValue) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			Object value = fragmentation.deProxyValue(feature, rawValue);
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
			return fragmentation.proxifyValue(feature, value);
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
				result[i] = fragmentation.proxifyValue(feature, values.get(i));				
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
				array[i] = (T)fragmentation.proxifyValue(feature, values.get(i));				
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
		return fragmentation. proxify(fObject.fStoreObject().fContainer());
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		FObject fObject = (FObject)object;
		return fObject.fStoreObject().fContainingFeature();
	}

	@Override
	public EObject create(EClass eClass) {
		FStoreObject fStoreObject = new FStoreObject(eClass);
		return fragmentation.proxify(fStoreObject);
	}

}
