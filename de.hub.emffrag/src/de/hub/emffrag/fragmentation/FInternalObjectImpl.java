package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;

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

	public FragmentedModel getFragmentation() {
		Resource eResource = eResource();
		if (eResource instanceof Fragment) {
			return ((Fragment) eResource).getFragmentedModel();
		}
		return null;
	}

	public Fragment getFragment() {
		Resource eResource = eResource();
		if (eResource instanceof Fragment) {
			return ((Fragment) eResource);
		}
		return null;
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
					String extrinsicID = fragmentedModel.updateCrossReference(null, this);
					resource.setID(this, extrinsicID);
				} else {
					throw new IllegalStateException("Object cannot be cross referenced");
				}
			}
			// else: if the referenced object is not part of the model, TODO should this be an error?
		}
	}
	
	public boolean isCrossReferenced() {
		return isCrossReferenced || (eResource() != null && ((Fragment)eResource()).getID(this) != null);
	}

	/**
	 * If the object changes resources, its cross referenced status has to move
	 * too. Further, the id table needs to be updated.
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
				extrinsicID = fragmentedModel.updateCrossReference(extrinsicID, this);
				newResource.setID(this, extrinsicID);
			}
		}
		// else: if the referenced object is not part of the model, TODO should this be an error?
		return result;		
	}

}
