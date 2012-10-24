package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

public class FInternalObjectImpl extends DynamicEObjectImpl {
	
	public FInternalObjectImpl(EClass eClass) {
		super(eClass);
	}

	/*
	 * Marks that this object is now cross references. Is called even if this
	 * object is already cross references.
	 */
	public void setIsCrossReferenced() {
		// TODO cross reference support
	}

	public boolean isFragmentRoot() {
		Resource eResource = eResource();
		return eResource != null && eResource instanceof Fragment && (eContainer() == null || eResource != eContainer().eResource());
	}

	public FragmentedModel getFragmentation() {
		Resource eResource = eResource();
		if (eResource instanceof Fragment) {
			return ((Fragment)eResource).getFragmentedModel();
		}
		return null;
	}

	public Fragment getFragment() {
		Resource eResource = eResource();
		if (eResource instanceof Fragment) {
			return ((Fragment)eResource);
		}
		return null;
	}

}
