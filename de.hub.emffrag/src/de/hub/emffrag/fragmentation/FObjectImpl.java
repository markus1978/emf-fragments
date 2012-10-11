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

		// The object was moved to a new (including null) container. This can
		// have an effect on the fragmentation. The following code realizes
		// these effects.
		if (newContainer != null) {
			int featureID = EOPPOSITE_FEATURE_BASE - newContainerFeatureID;
			EStructuralFeature feature = newContainer.eClass().getEStructuralFeature(featureID);
			if (feature != null && EMFFragUtil.isFragFreature(feature)) {
				// if the object is not yet root of a fragment, a new fragment
				// has to be created
				if (!internalObject.isFragmentRoot()) {
					internalObject.getFragmentation().crateFragment(this.internalObject, this, newContainer, feature);
				}
			} else {
				// if the object was root of a fragment, this fragment has to be
				// removed now
				if (internalObject.isFragmentRoot()) {
					internalObject.getFragmentation().removeFragment(this);
				}
			}
		} else {			
			// this object was removed from the model, it has to be moved to the
			// new objects realm (if necessary) and if it was a fragment root the
			// fragment has to be deleted.
			// TODO realm for new objects
			if (internalObject.isFragmentRoot()) {
				internalObject.getFragmentation().removeFragment(this);
			}
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
