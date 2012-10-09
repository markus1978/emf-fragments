package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FStoreImpl implements EStore {

	private static FStoreImpl instance = new FStoreImpl();

	public static FStoreImpl getInstance() {
		return instance;
	}

	private Object getUserValue(Object internalValue, EStructuralFeature userFeature) {
		if (userFeature != null && userFeature.getEType() instanceof EEnum) {
			return EcoreUtil.createFromString((EDataType) userFeature.getEType(), internalValue.toString());
		} else if (internalValue != null && internalValue instanceof DynamicEObjectImpl) {
			EObject internalObject = (EObject) internalValue;
			Fragment fragment = (Fragment) internalObject.eResource();
			EObject userObject = null;
			if (fragment != null) {
				// TODO
				// Retrieve the user object from the fragment's object cache
			} else {
				// TODO
				// Retrieve the user object. I do not yet know how to do that. 
			}

			if (userObject == null) {
				// create a new user object and add it to the appropriate cache;
				// or use caches that create new user objects if asked for one before
			}
			return userObject;
		} else {
			return internalValue;
		}
	}

	private EObject getInternalObject(InternalEObject userObject) {
		EObject internalObject = ((FObjectImpl) userObject).internalObject;
		if (internalObject == null) {
			// TODO
			// instantiate an internal object ... this requires the fragmented
			// model as context information
		}
		if (internalObject.eIsProxy()) {
			// TODO
			// resolve the internal object ... this requires the fragmented
			// model as context information (more specifically it requires the
			// resource set)
			// internalObject = EcoreUtil.resolve(internalObject, resourceSet);
		}
		return internalObject;
	}

	private EStructuralFeature getInternalFeature(EStructuralFeature feature) {
		// TODO
		// Previously we used two meta-models, one for the user and one for the
		// internal model. The reason is that there is no known way to create
		// both user and internal objects from the same factory.
		return feature;
	}

	private Object getInternalValue(Object userValue, EStructuralFeature internalFeature) {
		if (internalFeature.getEType() instanceof EEnum) {
			return EcoreUtil.createFromString((EDataType) internalFeature.getEType(), userValue.toString());
		} else if (userValue != null && userValue instanceof FObjectImpl) {
			return getInternalObject((InternalEObject) userValue);
		} else {
			return userValue;
		}
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		feature = getInternalFeature(feature);
		EObject internalObject = getInternalObject(object);
		if (feature.isMany()) {
			return getUserValue(((EList<?>) internalObject.eGet(feature)).get(index), feature);
		} else {
			return getUserValue(internalObject.eGet(feature), feature);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		feature = getInternalFeature(feature);
		Object internalValue = getInternalValue(value, feature);
		EObject internalObject = getInternalObject(object);
		Object result = null;
		if (feature.isMany()) {
			result = getUserValue(((EList) internalObject.eGet(feature)).set(index, internalValue), feature);
		} else {
			Object oldValue = getUserValue(internalObject.eGet(feature), feature);
			internalObject.eSet(feature, internalValue);
			result = oldValue;
		}
		if (feature instanceof EReference && !((EReference) feature).isContainment()) {
			((FInternalObjectImpl) internalValue).setIsCrossReferenced();
		}
		return result;
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		if (!(getInternalObject(object) instanceof DynamicEObjectImpl)) {
			System.out.println("##");
		}

		return getInternalObject(object).eIsSet(feature);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		getInternalObject(object).eUnset(feature);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		return ((EList<?>) getInternalObject(object).eGet(feature)).isEmpty();
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		return ((EList<?>) getInternalObject(object).eGet(feature)).size();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		feature = getInternalFeature(feature);
		return ((EList) getInternalObject(object).eGet(feature)).contains(getInternalValue(value, feature));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		feature = getInternalFeature(feature);
		return ((EList) getInternalObject(object).eGet(feature)).indexOf(getInternalValue(value, feature));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		feature = getInternalFeature(feature);
		return ((EList) getInternalObject(object).eGet(feature)).lastIndexOf(getInternalValue(value, feature));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		feature = getInternalFeature(feature);
		Object internalValue = getInternalValue(value, feature);
		((EList) getInternalObject(object).eGet(feature)).add(index, internalValue);
		if (feature instanceof EReference && !((EReference) feature).isContainment()) {
			((FInternalObjectImpl) internalValue).setIsCrossReferenced();
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		feature = getInternalFeature(feature);
		return getUserValue(((EList<?>) getInternalObject(object).eGet(feature)).remove(index), feature);
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		feature = getInternalFeature(feature);
		return getUserValue(((EList<?>) getInternalObject(object).eGet(feature)).move(targetIndex, sourceIndex), feature);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		((EList<?>) getInternalObject(object).eGet(feature)).clear();

	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		Object[] array = ((EList<?>) getInternalObject(object).eGet(feature)).toArray();
		Object[] result = new Object[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = getUserValue(array[i], feature);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		feature = getInternalFeature(feature);
		Object[] values = ((EList<?>) getInternalObject(object).eGet(feature)).toArray(new Object[array.length]);
		Object[] result = new Object[array.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = getUserValue(values[i], feature);
		}
		return (T[]) result;
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		feature = getInternalFeature(feature);
		return getInternalObject(object).eGet(feature).hashCode();
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		return (InternalEObject) getUserValue(getInternalObject(object).eContainer(), null);
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		EStructuralFeature fiFeature = getInternalObject(object).eContainingFeature();
		EClass fiClass = fiFeature.getEContainingClass();
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(fiClass.getEPackage().getNsURI());
		return ((EClass) ePackage.getEClassifier(fiClass.getName())).getEStructuralFeature(fiFeature.getFeatureID());
	}

	@Override
	public EObject create(EClass eClass) {
		throw new UnsupportedOperationException();
	}

}
