package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

public class IndexBasedIdSemantics implements IdSemantics {
	
	public enum IdBehaviour { strict, preliminary, defaultModel };
	
	private static String preliminaryID = "PRELIMINARY_ID";
	
	private static boolean isPreliminary(String id) {
		return preliminaryID.equals(id);
	}
	
	private final IdBehaviour idBehavior;
	
	public IndexBasedIdSemantics(IdBehaviour behavior) {
		this.idBehavior = behavior;
	}
	
	String getId(FInternalObjectImpl internalObject, boolean issueIfNecessary) {
		if (internalObject.eIsProxy()) {
			EcoreUtil.resolve(internalObject, internalObject.getFragmentation());
		}
		
		String id = internalObject.getId();

		if (id != null) {
			if (internalObject.hasDefaultModelId) {
				FragmentedModel fragmentedModel = internalObject.getFragmentation();
				if (fragmentedModel != null) {
					fragmentedModel.getIdIndex().updateObjectURI(id, internalObject);
				}
				internalObject.hasDefaultModelId = false;
			}
			return id;
		} else {
			if (issueIfNecessary) {
				FragmentedModel fragmentedModel = internalObject.getFragmentation();
				if (fragmentedModel != null) {
					id = fragmentedModel.getIdIndex().issueId(internalObject);
					fragmentedModel.getIdIndex().updateObjectURI(id, internalObject);
					internalObject.setId(id);
					internalObject.hasPriliminaryId = false;
					return id;
				} else if (internalObject.hasPriliminaryId) {
					return preliminaryID;
				} else if (idBehavior == IdBehaviour.preliminary) {
					internalObject.hasPriliminaryId = true;
					return preliminaryID;
				} else if (idBehavior == IdBehaviour.defaultModel) {
					id = EmfFragActivator.instance.defaultModel.getIdIndex().issueId(internalObject);
					internalObject.hasDefaultModelId = true;
					internalObject.setId(id);
					return id;
				} else {
					throw new NotInAFragmentedModelException(
							"Could not issue an ID because the object is not part of a fragmented model.");
				}
			} else if (internalObject.hasPriliminaryId) {
				FragmentedModel fragmentedModel = internalObject.getFragmentation();
				if (fragmentedModel != null) {
					id = fragmentedModel.getIdIndex().issueId(internalObject);
					internalObject.setId(id);
					internalObject.hasPriliminaryId = false;
				} else {
					return preliminaryID;
				}
			}
		}

		return null;
	}
	
	/**
	 * Some objects need to have an IDs. These are objects with indexed value
	 * sets, or indexed class instances.
	 */
	public void onRequiredId(FInternalObjectImpl internalObject) {
		if (internalObject.getFragmentation() != null && getId(internalObject, false) == null) {
			boolean idIsRequired = false;
			idIsRequired |= ReflectiveMetaModelRegistry.instance.getInternalClass(EmfFragPackage.eINSTANCE.getIndexedMap())
					.isInstance(this);
			for (EStructuralFeature feature : internalObject.eClass().getEAllStructuralFeatures()) {
				if (feature instanceof EReference) {
					FragmentationType fragmentationType = EMFFragUtil.getFragmentationType(feature);
					idIsRequired |= fragmentationType == FragmentationType.FragmentsIndexedContainment
							|| fragmentationType == FragmentationType.IndexedReferences;
				}
			}
			if (idIsRequired) {
				getId(internalObject, true);
			}
		}
	}

	public void onObjectAsReferenced(FInternalObjectImpl internalObject) {
		getId(internalObject, true);
	}

	public URI getURI(FInternalObjectImpl internalObject, FragmentedModel model, 
			boolean forceIssue, SaveURI saveURI) {
		String id = getId(internalObject, forceIssue);
		if (id == null || isPreliminary(id)) {
			return null;
		}
		return model.getIdIndex().createIdUri(id);
	}

	public String getPrefixID(FInternalObjectImpl object) {
		String id = getId(object, true);
		if (isPreliminary(id)) {
			throw new NotInAFragmentedModelException("Indexed reference owner have to be added to a fragmented model before the indexed reference can be used.");
		}
		return id;
	}

	public void onObjectSaved(FInternalObjectImpl fInternalObject) {
		getId(fInternalObject, false);
	}
	
	@Override
	public void onContainerChange(FInternalObjectImpl object, FragmentedModel model) {
		String id = object.getId();
		if (id != null) {			
			model.getIdIndex().updateObjectURI(id, object);
			object.hasDefaultModelId = false;
		}	
	}

	@Override
	public FInternalObject resolveURI(URI uri, FragmentedModel model) {
		return (FInternalObject)model.getInternalResourceSet().getEObject(model.getIdIndex().getObjectUriForIdUri(uri), true);
	}

	void assertHasPreliminary(FInternalObjectImpl fInternalObject) {
		Assert.assertTrue("Object has not a preliminary id.", isPreliminary(getId(fInternalObject, false)));
	}

	void assertHasNotPreliminary(FInternalObjectImpl fInternalObject) {
		boolean preliminaryId = isPreliminary(getId(fInternalObject, false));
		Assert.assertFalse("Object has a preliminary id.", preliminaryId);
	}
}
