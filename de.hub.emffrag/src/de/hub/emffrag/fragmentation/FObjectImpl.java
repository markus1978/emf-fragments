package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;

import de.hub.emffrag.util.EMFFragUtil;

public class FObjectImpl extends EStoreEObjectImpl {

	protected FInternalObjectImpl internalObject;
	
	public FObjectImpl() {
		eSetStore(FStoreImpl.getInstance());
	}

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
		if (newContainer != null) {
			int featureID = EOPPOSITE_FEATURE_BASE - newContainerFeatureID;
			EStructuralFeature feature = newContainer.eClass().getEStructuralFeature(featureID);
			if (feature != null && EMFFragUtil.isFragFreature(feature)) {
				// TODO 
				// create a new fragment, if necessary
			} else {
				// TODO
				// remove an old fragment, if necessary
			}
		} else {
			// TODO
			// what if the object was a fragment root before?			
		}
	}

	@Override
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		// disables superclass implementation to avoid instant remove through
		// double realization of containment
		return msgs;
	}

	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		// disables superclass implementation to avoid instant remove through
		// double realization of containment
		return msgs;
	}
}
