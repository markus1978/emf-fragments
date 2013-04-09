package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

/**
 * Since EMF does not allow to use the same model in a regular fashion
 * (generated classes) and purely reflective fashion, we need to maintain two
 * versions of the same meta-model. One is used by clients/users as the regular
 * meta-model. The other is used internally by EMF-Fragments as a purely
 * reflective version.
 * 
 * This class allows to manage meta-models and their two versions and access the
 * corresponding opposite elements of the other meta-model.
 * 
 */
public class ReflectiveMetaModelRegistry extends HashMap<String, Object> implements EPackage.Registry {

	private static final long serialVersionUID = 1L;
	
	public static final ReflectiveMetaModelRegistry instance = new ReflectiveMetaModelRegistry();

	private ReflectiveMetaModelRegistry() {
	}

	private final Map<String, EPackage> userMetaModelRegistry = new HashMap<String, EPackage>();
	private final Multimap<EClass, EReference> incomingIndexedReferences = HashMultimap.create();
	private final Map<EReference, Integer> inverseReferenceIds = new HashMap<EReference, Integer>();

	public EPackage registerUserMetaModel(EPackage source) {
		String nsURI = source.getNsURI();
		EPackage target = (EPackage)get(nsURI);
		if (target == null) {
			target = generateReflectiveMetaModel(source);
		}
		return target;
	}
		
	@Override
	public EPackage getEPackage(String nsURI) {
		EPackage target = (EPackage)get(nsURI);
		if (target == null) {
			EPackage source = EPackage.Registry.INSTANCE.getEPackage(nsURI);
			if (source != null) {
				target = registerUserMetaModel(source);
			}
		}
		return target;
	}

	@Override
	public EFactory getEFactory(String nsURI) {
		return getEPackage(nsURI).getEFactoryInstance();
	}

	public EClass getInternalClass(EClass theClass) {
		return getOtherClass(this, theClass);
	}
	
	public EStructuralFeature getInternalFeature(EStructuralFeature feature) {
		return getOtherFeature(this, feature);
	}
	
	public EPackage getInternalMetaModel(EPackage thePackage) {
		return getOtherMetaModel(this, thePackage);
	}
	
	private EClass getOtherClass(Map<String, ? extends Object> mapping, EClass theClass) {
		EPackage metaModel = getOtherMetaModel(mapping, theClass.getEPackage());
		return metaModel == null ? null : (EClass)metaModel.getEClassifier(theClass.getName()); // TODO performance
	}
	
	private EStructuralFeature getOtherFeature(Map<String, ? extends Object> mapping, EStructuralFeature feature) {
		EClass theClass = getOtherClass(mapping, feature.getEContainingClass());
		return theClass == null ? null : theClass.getEStructuralFeature(feature.getName()); // TODO performance
	}
	
	private EPackage getOtherMetaModel(Map<String, ? extends Object> mapping, EPackage thePackage) {
		return (EPackage)mapping.get(thePackage.getNsURI());
	}
	
	public EStructuralFeature getRegularFeature(EStructuralFeature feature) {
		return getOtherFeature(userMetaModelRegistry, feature);
	}

	public EPackage getUserMetaModel(EPackage thePackage) {
		return getOtherMetaModel(userMetaModelRegistry, thePackage);
	}

	public EClass getUserClass(EClass theClass) {
		return getOtherClass(userMetaModelRegistry, theClass);
	}

	private EPackage generateReflectiveMetaModel(final EPackage source) {
		EPackage target = EcoreUtil.copy(source);
		
		put(source.getNsURI(), target);
		userMetaModelRegistry.put(source.getNsURI(), source);

		// check and modify target properties
		{
			TreeIterator<EObject> iterator = target.eAllContents();
			while (iterator.hasNext()) {
				EObject next = iterator.next();
				if (next instanceof EClass) {
					((EClassImpl) next).setGeneratedInstanceClass(false);
					((EClass) next).setInstanceClass(null);
					EList<EClass> superTypes = ((EClass)next).getESuperTypes();
					List<Integer> superTypesToChange = new ArrayList<Integer>(superTypes.size());
					int index = 0;
					for (EClass superType: superTypes) {
						if (superType.getEPackage() != target) {
							superTypesToChange.add(index);
						}
						index++;
					}
					for (Integer superTypeIndex: superTypesToChange) {
						EClass superType = superTypes.get(superTypeIndex);
						registerUserMetaModel(superType.getEPackage());
						superTypes.set(superTypeIndex, getInternalClass(superType));						
					}
					superTypes.add(InternalPackage.eINSTANCE.getFInternalObject());
				} else if (next instanceof EDataType) {
					EDataType sourceDataType = (EDataType)source.getEClassifier(((EDataType) next).getName());
					((EDataType) next).setInstanceTypeName(sourceDataType.getInstanceTypeName());					
				} else if (next instanceof EReference) {
					FragmentationType fragmentationType = EMFFragUtil.getFragmentationType((EReference)next);
					EReference reference = (EReference)next;
					if (reference.isContainment() && fragmentationType == FragmentationType.None) {
						reference.setResolveProxies(false);
					} else if (fragmentationType == FragmentationType.FragmentsIndexedContainment || fragmentationType == FragmentationType.IndexedReferences) {
						reference.setUnique(false);
						reference.setTransient(true);
						incomingIndexedReferences.put((EClass)reference.getEType(), reference);
					}
					EClassifier type = reference.getEType();
					if (type instanceof EClass) {
						registerUserMetaModel(((EClass)type).getEPackage());
						reference.setEType(getInternalClass((EClass)type));
					}
				}
			}
		}
		
		// check and modify source properties
		{
			TreeIterator<EObject> iterator = source.eAllContents();
			while (iterator.hasNext()) {
				EObject next = iterator.next();
				if (next instanceof EReference) {
					FragmentationType fragmentationType = EMFFragUtil.getFragmentationType((EReference)next);
					EReference reference = (EReference)next;
					if (fragmentationType == FragmentationType.FragmentsIndexedContainment || fragmentationType == FragmentationType.IndexedReferences) {
						reference.setUnique(false);
					}
				}
			}
		}

		EFactory targetFactory = new InternalEFactoryImpl(source.getEFactoryInstance());
		targetFactory.setEPackage(target);
		target.setEFactoryInstance(targetFactory);
		return target;
	}
	
	private static class InternalEFactoryImpl extends EFactoryImpl {
		private final EFactory sourceFactory;
		
		public InternalEFactoryImpl(EFactory sourceFactory) {
			super();
			this.sourceFactory = sourceFactory;
		}

		@Override
		public EObject create(EClass eClass) {
			return new FInternalObjectImpl(eClass);
		}

		@Override
		public Object createFromString(EDataType eDataType, String stringValue) {
			if (eDataType instanceof EEnum) {
				return sourceFactory.createFromString(eDataType, stringValue);	
			} else {
				return super.createFromString(eDataType, stringValue);
			}			
		}		
	}
	
	public int getInverseReferenceIndex(EReference reference) {
		Integer index = inverseReferenceIds.get(reference);
		if (index == null) {
			Collection<EReference> collection = incomingIndexedReferences.get((EClass)reference.getEType());
			ArrayList<EReference> sortedIncomingReferences = new ArrayList<EReference>(collection);
			Collections.sort(sortedIncomingReferences, new Comparator<EReference>() {				
				private String getQualifier(EReference reference) {
					return reference.getEContainingClass().getName() + "." + reference.getName(); 
				}
				@Override
				public int compare(EReference o1, EReference o2) {
					return getQualifier(o1).compareTo(getQualifier(o2));
				}				
			});
			index = sortedIncomingReferences.indexOf(reference);
			inverseReferenceIds.put(reference, index);
		}
		return index;
	}

	@Override
	public void clear() {
		super.clear();
		userMetaModelRegistry.clear();
	}
}
