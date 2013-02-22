package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;

import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

public class FInternalObjectImpl extends DynamicEObjectImpl {

	private boolean isCrossReferenced = false;

	public FInternalObjectImpl(EClass eClass) {
		super(eClass);
	}

	public boolean isFragmentRoot() {
		Resource eResource = eResource();
		return eResource != null && eResource instanceof Fragment
				&& (eContainer() == null || eResource != eContainer().eResource());
	}
	
	public String getExtrinsicID(boolean issueIfNecessary) {
		Fragment fragment = getFragment();
		if (fragment != null) {
			String extrinsicID = fragment.getID(this);
			if (extrinsicID == null || issueIfNecessary) {
				extrinsicID = fragment.getFragmentedModel().getExtrinsicIdIndex().issueExtrinsicID(this);				
			}
			return extrinsicID;
		} else {
			throw new RuntimeException("Objects has to be part of a fragmented model.");
		}
	}
	
	public EObject getUserObject() {
		return getUserObjectCache().getUserObject(this);
	}
	
	public UserObjectsCache getUserObjectCache() {
		Fragment fragment = getFragment();
		if (fragment != null) {
			return fragment.getUserObjectsCache();
		} else {
			return UserObjectsCache.newUserObjectsCache;
		}
	}

	public FragmentedModel getFragmentation() {
		Fragment fragment = getFragment();
		if (fragment != null) {
			return fragment.getFragmentedModel();
		} else {
			return null;
		}
	}

	public Fragment getFragment() {
		Resource eResource = eResource();
		if (eResource != null) {
			if (eResource instanceof Fragment) {
				return ((Fragment) eResource);
			} else {
				return null;
			}
		} else if (eIsProxy()) {
			EObject container = eContainer();
			while (container != null) {
				eResource = container.eResource();
				if (eResource != null) {
					if (eResource instanceof Fragment) {
						return ((Fragment) eResource);
					} else {
						return null;
					}
				}
			}
			return null;
		} else {		
			return null;
		}
	}

	/**
	 * Marks that this object is now cross referenced. Is called even if this
	 * object is already cross referenced.
	 */
	public void setIsCrossReferenced() {
		if (!isCrossReferenced()) {
			isCrossReferenced = true;
			Fragment resource = (Fragment) eResource();
			FragmentedModel fragmentedModel = getFragmentation();
			if (fragmentedModel != null) {
				if (resource != null) {
					fragmentedModel.getExtrinsicIdIndex().updateObjectURI(null, this);					
				} else {
					throw new IllegalStateException("Object cannot be cross referenced");
				}
			}
			// else: if the referenced object is not part of the model, TODO (multi fragmentation models)
			// should this be an error?
		}
	}

	public boolean isCrossReferenced() {
		return isCrossReferenced || (eResource() != null && ((Fragment) eResource()).getID(this) != null);
	}

	/**
	 * If the object changes resources, its cross referenced status has to move
	 * too. Further, the cross reference entry needs to be updated.
	 */
	@Override
	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
		Fragment newResource = (Fragment) eResource();
		Fragment oldResource = newResource;
		String extrinsicID = null;
		if (oldResource != null) {
			extrinsicID = oldResource.getID(this);
		}
		NotificationChain result = super.eSetResource(resource, notifications);
		FragmentedModel fragmentedModel = getFragmentation();
		if (fragmentedModel != null) {
			if (isCrossReferenced || extrinsicID != null) {
				fragmentedModel.getExtrinsicIdIndex().updateObjectURI(extrinsicID, this);
			}
		}
		// else: if the referenced object is not part of the model, TODO (multi fragmentation models) 
		// should this be an error?
		return result;
	}

	/**
	 * The EMF-implementation does somehow create a network of Java references
	 * that prevent to fully unload the contents of a resource. This method
	 * breaks the corresponding references.
	 */
	void trulyUnload() {
		if (eProperties != null) {
			eProperties.setEContents(null);
			eProperties.setECrossReferences(null);
		}
		eSettings = null;
	}
	
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		FragmentationType type = EMFFragUtil.getFragmentationType(eFeature);
		if (type == FragmentationType.None || type == FragmentationType.FragmentsContainment) {
			return super.eGet(featureID, resolve, coreType);			
		} else {
			if (type == FragmentationType.FragmentsIndexedContainment || type == FragmentationType.IndexedReferences) {
				return new FList<Object>(this, eFeature);
			} else {
				throw new RuntimeException("Supposed unreachable.");
			}
		}
	}
}
