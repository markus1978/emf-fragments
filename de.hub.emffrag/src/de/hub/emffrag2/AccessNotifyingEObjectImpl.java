package de.hub.emffrag2;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

/**
 * EMF's adapter based notification only notifies for changes. This extension to
 * {@link MinimalEObjectImpl} calls {@link #onAccess()} each time the object was
 * read accessed (including accesses to its value and feature sets). Requires
 * reflective/dynamic feature delegation.
 */
public abstract class AccessNotifyingEObjectImpl extends MinimalEObjectImpl {

	/**
	 * Is called on each read access to this object, or one of its contained
	 * feature lists/maps.
	 */
	protected void onAccess() {

	}

	@Override
	public void dynamicSet(int dynamicFeatureID, Object value) {
		if (value instanceof EList) {
			EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
			if (!eStructuralFeature.isTransient()) {
				if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
					// TODO
				} else if (eStructuralFeature.isMany()) {
					super.dynamicSet(dynamicFeatureID, createListWrapper((EList<?>) value, eStructuralFeature));
					return;
				}
			}
		}
		super.dynamicSet(dynamicFeatureID, value);
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	protected EList<?> createListWrapper(final EList<?> source, EStructuralFeature feature) {
		return new DelegatingEcoreEList.Dynamic(this, feature) {
			@Override
			protected List<?> delegateList() {
				onAccess();
				return source;
			}
		};
	}

	@Override
	public InternalEObject eInternalContainer() {
		onAccess();
		return super.eInternalContainer();
	}

	protected void eBasicSetContainer(InternalEObject newContainer) {
		onAccess();
		super.eBasicSetContainer(newContainer);
	}

	@Override
	public Resource.Internal eDirectResource() {
		onAccess();
		return super.eDirectResource();
	}

	@Override
	protected void eSetDirectResource(Resource.Internal resource) {
		onAccess();
		super.eSetDirectResource(resource);
	}

	@Override
	protected Object[] eBasicSettings() {
		onAccess();
		return super.eBasicSettings();
	}
}
