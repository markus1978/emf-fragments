package de.hub.emffrag2;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FObjectImpl extends MinimalEObjectImpl.Container {
	
	private Fragmentation fragmentation = null;
	
	@Override
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		if (eIsProxy() && resolve) {
			if (fragmentation == null) { // assertion
				throw new IllegalStateException("Unloaded FObject without fragmentation.");
			}
			EcoreUtil.resolve(this, fragmentation);
		}
		Object value = super.eGet(eFeature, resolve);
		if (eIsProxy()) { // assertion
			throw new IllegalStateException("FObject unloaded immediately after eGet.");
		}
		return value;
	}
	
	private void fEnsureFragmentation(Fragmentation newFragmentation, boolean fragments) {
		Fragmentation oldFragmentation = fFragmentation();
		
		if (newFragmentation == null && oldFragmentation == null) {
			return;
		} else if (newFragmentation == null || oldFragmentation == null) {
			if (newFragmentation == null) {
				// TODO remove
			} else {
				if (fragments) {
					newFragmentation.doFragment(this);
				} 
				// TODO ensure children				
			} 
		} else if (newFragmentation == oldFragmentation) {
			if (!fragments && fIsRoot()) {
				// TODO remove fragment
			}
		} else {
			if (fIsRoot()) {
				// TODO remove
			}
			// TODO remove children
			
			if (fragments) {
				// TODO create
			}
			// TODO ensure children
		}
	}
	

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		Fragmentation newFragmentation = null;
		if (newContainer instanceof FObjectImpl) {
			newFragmentation = ((FObjectImpl)newContainer).fFragmentation();
		}
		
		fEnsureFragmentation(newFragmentation, fIsFragmenting(newContainer, newContainerFeatureID));
		
		super.eBasicSetContainer(newContainer, newContainerFeatureID);	
	}
	
	public boolean fIsRoot() {
		Resource eResource = eResource();
		if (eResource != null) {
			return eResource.getContents().contains(this); // TODO performance
		}
		return false;
	}

	public Fragmentation fFragmentation() {
		if (fragmentation == null) {
			Resource eResource = eResource();
			if (eResource != null && eResource instanceof Fragment) {
				fragmentation = ((Fragment)eResource).getFragmentation();
			}
		}
		return fragmentation;
	}

	private boolean fIsFragmenting(InternalEObject newContainer, int newContainerFeatureID) {
		int containingFeatureID = InternalEObject.EOPPOSITE_FEATURE_BASE - newContainerFeatureID;
		EStructuralFeature containingFeature = newContainer.eClass().getEStructuralFeature(containingFeatureID);
		return !containingFeature.getEAnnotations().isEmpty(); // TODO look for the right annotation
	}



	/**
	 * In EMF termionology "unload" only clears all references to/from the
	 * resource, but not within the object graph. This method does the rest.
	 * Namingly clears contents, cross references, container, and all feature
	 * values (settings). This renders the object unusable for clients.
	 */
	public void fTrueUnload(Fragmentation fromFragmentation) {
		this.fragmentation = fromFragmentation;
		
		// if this becomes something not MinimalEObjectImpl, eBasicProperties has to be cleaned as well
		eBasicSetSettings(new Object[] {});
		eBasicSetContainer(null);
	}
}
