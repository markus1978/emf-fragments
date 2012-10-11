package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

public class FInternalObjectImpl extends DynamicEObjectImpl {

	/*
	 * Marks that this object is now cross references. Is called even if this
	 * object is already cross references.
	 */
	public void setIsCrossReferenced() {
		// TODO cross reference support
	}

	public boolean isFragmentRoot() {
		Resource eResource = eResource();
		return eResource != null && eResource instanceof Fragment && eResource != eContainer().eResource();
	}

	public FragmentedModel getFragmentation() {
		Resource eResource = eResource();
		if (eResource instanceof Fragment) {
			return ((Fragment)eResource).getFragmentedModel();
		}
		return null;
	}

}
