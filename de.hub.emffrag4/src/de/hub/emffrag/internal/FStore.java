package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

import de.hub.emffrag.FObject;

public class FStore implements EStore {
	
	public static final FStore fINSTANCE = new FStore();
	
	public final FObjectProxyManager proxyManager = new FObjectProxyManager();
	private final List<FStoreFragmentation> lockedFragmentations = new ArrayList<FStoreFragmentation>(1);
	
	private void lock(Object object) {
		if (object instanceof FStoreObject) {
			FStoreFragmentation fragmentation = ((FStoreObject)object).fFragmentation();
			if (fragmentation != null && !lockedFragmentations.contains(fragmentation)) {
				lockedFragmentations.add(fragmentation);
				fragmentation.lock();
			}
		}		
	}
	
	private void unlock() {
		for(FStoreFragmentation fragmentation: lockedFragmentations) {
			fragmentation.unlock();
		}
		lockedFragmentations.clear();
	}
	
	private FObject proxify(FStoreObject fStoreObject) {
		return proxyManager.getFObject(fStoreObject);
	}
	
	private Object proxifyValue(EStructuralFeature feature, Object value) {
		if (feature instanceof EReference) {
			return proxify((FStoreObject)value);
		} else {
			return value;
		}
	}
	
	private Object deProxifyValue(EStructuralFeature feature, Object rawValue) {
		Object value = (feature instanceof EReference && rawValue != null) ? ((FObject)rawValue).fStoreObject() : rawValue;
		return value;
	}

	private void setProtentialContainer(EStructuralFeature feature, Object container, Object contents) {
		if (feature instanceof EReference && ((EReference)feature).isContainment()) {
			((FStoreObject)contents).fSetContainer((FStoreObject)container, (EReference)feature, false);
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
		Object value = deProxifyValue(feature, rawValue);
		FStoreObject fStoreObject = fObject.fStoreObject();
		Object previousValue =  fStoreObject.fGet(feature);
		lock(fStoreObject); lock(value);
		if (feature.isMany()) {			
			previousValue = ((List)previousValue).set(index, value);
		} else {
			fStoreObject.fSet(feature, value);
		}
		fStoreObject.fMarkModified(true); // this needs to be happen before the container is set! Otherwise set container might try to unload fObject and will not save it, since it is not yet marked modified.
		
		if (value != null) {
			setProtentialContainer(feature, fStoreObject, value); // TODO only get fStoreObject once, prevent potential unload/immediate load scenaries.
		}
		unlock();
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
		FStoreObject fStoreObject = fObject.fStoreObject();
		Object oldValue = fStoreObject.fGet(feature);
		lock(fStoreObject); lock(oldValue);
		fStoreObject.fUnSet(feature);
		fStoreObject.fMarkModified(true);
		setProtentialContainer(feature, null, oldValue);
		unlock();
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
			return ((List<?>)fObject.fStoreObject().fGet(feature)).contains(deProxifyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}



	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).indexOf(deProxifyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			return ((List<?>)fObject.fStoreObject().fGet(feature)).lastIndexOf(deProxifyValue(feature, value));
		}
		throw new IllegalArgumentException();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object rawValue) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			Object value = deProxifyValue(feature, rawValue);
			FStoreObject fStoreObject = fObject.fStoreObject();
			lock(fStoreObject); lock(value);
			((List)fStoreObject.fGet(feature)).add(value);
			setProtentialContainer(feature, fStoreObject, value);
			fStoreObject.fMarkModified(true);
			unlock();
			return;
		}		
		throw new IllegalArgumentException();
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			FStoreObject fStoreObject = fObject.fStoreObject();
			lock(fStoreObject);
			Object oldValue = ((List<?>)fStoreObject.fGet(feature)).remove(index);
			lock(oldValue);
			setProtentialContainer(feature, null, oldValue);
			fStoreObject.fMarkModified(true);
			unlock();
			return proxifyValue(feature, oldValue);
		}
		throw new IllegalArgumentException();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			FStoreObject fStoreObject = fObject.fStoreObject();
			lock(fStoreObject);			
			Object value = ((List<?>)fStoreObject.fGet(feature)).remove(sourceIndex);
			lock(value);
			((List)fStoreObject.fGet(feature)).add(targetIndex, value);
			fStoreObject.fMarkModified(true);
			unlock();
			return proxifyValue(feature, value);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		FObject fObject = (FObject)object;
		if (feature.isMany()) {
			FStoreObject fStoreObject = fObject.fStoreObject();
			List<?> values = (List<?>)fStoreObject.fGet(feature);
			lock(fStoreObject);
			for (Object value: values) {
				lock(value);
				setProtentialContainer(feature, null, value);
			}
			values.clear();
			fStoreObject.fMarkModified(true);
			unlock();
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
		return (InternalEObject)proxify(fObject.fStoreObject().fContainer());
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		FObject fObject = (FObject)object;
		return fObject.fStoreObject().fContainingFeature();
	}

	@Override
	public EObject create(EClass eClass) {
		FStoreObject fStoreObject = new FStoreObjectImpl(eClass);
		fStoreObject.fMarkModified(true);
		return proxify(fStoreObject);
	}

}
