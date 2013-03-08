package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.hub.emffrag.EmfFragActivator;

public class FStoreImpl implements EStore {

	private static FStoreImpl instance = new FStoreImpl();

	public static FStoreImpl getInstance() {
		return instance;
	}

	private Object getUserValue(Object internalValue, EStructuralFeature userFeature) {
		if (userFeature != null && userFeature.getEType() instanceof EEnum) {
			return EcoreUtil.createFromString((EDataType) userFeature.getEType(), internalValue.toString());
		} else if (internalValue != null && internalValue instanceof DynamicEObjectImpl) {
			FInternalObjectImpl internalObject = (FInternalObjectImpl) internalValue;
			return getUserObject(internalObject);
		} else {
			return internalValue;
		}
	}
	
	public EObject getUserObject(FInternalObjectImpl internalObject) {
		Fragment fragment = (Fragment) internalObject.eResource();
		EObject userObject = null;
		if (fragment != null) {
			userObject = fragment.getUserObjectsCache().getUserObject(internalObject);	
		} else {
			userObject = UserObjectsCache.newUserObjectsCache.getUserObject(internalObject);				
		}
		return userObject;
	}

	public FInternalObjectImpl getInternalObject(EObject userObject) {
		FInternalObjectImpl internalObject = ((FObjectImpl) userObject).fInternalObject();
		if (internalObject == null) {
			// This object was not yet added to a model
			internalObject = UserObjectsCache.newUserObjectsCache.createInternalObject((FObjectImpl)userObject);
		} else if (internalObject.eIsProxy()) {
			FragmentedModel model = internalObject.getFragmentation();
			if (model == null) {
				model = EmfFragActivator.instance.defaultModel;
			}
			if (model != null) {
				internalObject = (FInternalObjectImpl)EcoreUtil.resolve(internalObject, model.getInternalResourceSet());
				if (internalObject.eIsProxy()) {
					throw new RuntimeException("Could not resolve " + internalObject.eProxyURI());
				}
				// mark as used ...
				internalObject.getFragment().getUserObjectsCache().getUserObject(internalObject);
			} else {
				throw new NotInAFragmentedModelException("An user object that appreas to be new is a proxy.");
			}			
		}
		return internalObject;
	}

	private EStructuralFeature getInternalFeature(EStructuralFeature feature) {
		return ReflectiveMetaModelRegistry.instance.getInternalFeature(feature);		
	}

	private Object getInternalValue(Object userValue, EStructuralFeature internalFeature) {
		EClassifier eType = internalFeature.getEType();
		if (eType instanceof EEnum) {
			return eType.getEPackage().getEFactoryInstance().createFromString((EDataType)eType, userValue.toString());
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
		EObject internalObject = getInternalObject(object);
		feature = getInternalFeature(feature);
		Object internalValue = getInternalValue(value, feature);		
		Object result = null;
		if (feature.isMany()) {
			result = getUserValue(((EList) internalObject.eGet(feature)).set(index, internalValue), feature);
		} else {
			Object oldValue = getUserValue(internalObject.eGet(feature), feature);
			internalObject.eSet(feature, internalValue);
			result = oldValue;
		}
		if (feature instanceof EReference && internalValue != null && !((EReference) feature).isContainment()) {
			EmfFragActivator.instance.idSemantics.onObjectAsReferenced((FInternalObjectImpl)internalObject);
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
			EmfFragActivator.instance.idSemantics.onObjectAsReferenced((FInternalObjectImpl)internalValue);
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
		EStructuralFeature internalFeature = getInternalObject(object).eContainingFeature();
		return ReflectiveMetaModelRegistry.instance.getRegularFeature(internalFeature);		
	}

	@Override
	public EObject create(EClass eClass) {
		throw new UnsupportedOperationException();
	}

}
