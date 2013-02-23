package de.hub.emffrag.fragmentation;

import java.io.IOException;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreEList;

import com.google.common.base.Throwables;

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
			if (extrinsicID == null && issueIfNecessary) {
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
			// else: if the referenced object is not part of the model, TODO
			// (multi fragmentation models)
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
		// else: if the referenced object is not part of the model, TODO (multi
		// fragmentation models)
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
	protected EStructuralFeature.Internal.SettingDelegate eSettingDelegate(final EStructuralFeature eFeature) {
		FragmentationType type = EMFFragUtil.getFragmentationType(eFeature);
		if (type == FragmentationType.None || type == FragmentationType.FragmentsContainment) {
			return ((EStructuralFeature.Internal) eFeature).getSettingDelegate();
		} else {
			return new EStructuralFeatureImpl.InternalSettingDelegateMany(EStructuralFeatureImpl.InternalSettingDelegateMany.DATA_DYNAMIC, eFeature) {
				@Override
				protected Setting createDynamicSetting(InternalEObject owner) {
					int kind = EcoreEList.Generic.kind(eFeature);
					return new FValueSetList(kind, FInternalObjectImpl.class, FInternalObjectImpl.this, eFeature);
				}				
			};										
		}		
	}

//	@Override
//	public Object eGet(int featureID, boolean resolve, boolean coreType) {
//		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
//		FragmentationType type = EMFFragUtil.getFragmentationType(eFeature);
//		if (type == FragmentationType.None || type == FragmentationType.FragmentsContainment) {
//			return super.eGet(featureID, resolve, coreType);
//		} else {
//			if (type == FragmentationType.FragmentsIndexedContainment || type == FragmentationType.IndexedReferences) {
//				return new FList(this, eFeature);
//			} else {
//				throw new RuntimeException("Supposed unreachable.");
//			}
//		}
//	}

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
		if (!eIsProxy()) {
			// Do not update containment if the object is a proxy. In that case
			// the object is loaded and not modified by a user.
			updateContainment(newContainer, newContainerFeatureID, null);
		}
	}
//
//	void setContainer(InternalEObject newContainer, int newContainerFeatureID, URI newFragmentURI) {
//		super.eBasicSetContainer(newContainer, newContainerFeatureID);
//		updateContainment(newContainer, newContainerFeatureID, newFragmentURI);
//	}

	/**
	 * Creates/deletes fragments if appropriately.
	 * 
	 * @param newContainer
	 *            The container that this object is now a part of, or null if
	 *            the object is removed from a container or placed into its own
	 *            independent fragment (e.g. as part of a indexed map).
	 * @param newContainerFeatureID
	 *            The feature ID under which this object is to be contained, or
	 *            -1.
	 * @param newFragmentURI
	 *            Optional URI if a new fragment is to be created, this works
	 *            only if there is no container for the object, and the object
	 *            is stored in its own independent fragment.
	 */
	void updateContainment(InternalEObject newContainer, int newContainerFeatureID, URI newFragmentURI) {
		FragmentedModel fragmentation = getFragmentation();
		FragmentedModel containerFragmentation = null;
		if (newContainer != null) {
			containerFragmentation = ((FInternalObjectImpl) newContainer).getFragmentation();
		}
		if (containerFragmentation == null && fragmentation == null) {
			// both models are not part of a fragmentation, we do nothing yet.
			return;
		} else if (fragmentation == null) {
			fragmentation = containerFragmentation;
		}

		// The object was moved to a new (including null) container. This can
		// have an effect on the fragmentation. The following code realizes
		// these effects.
		if (newContainer != null && newFragmentURI == null) {
			int featureID = EOPPOSITE_FEATURE_BASE - newContainerFeatureID;
			EStructuralFeature feature = newContainer.eClass().getEStructuralFeature(featureID);
			FragmentationType fragmentationType = EMFFragUtil.getFragmentationType(feature);
			if (feature != null && fragmentationType != FragmentationType.None) {
				// if the object is not yet root of a fragment, a new fragment
				// has to be created
				if (!isFragmentRoot()) {
					if (fragmentation == null) {
						throw new RuntimeException(
								"You cannot add a value to a fragmenting reference if the new container is not part of a fragmented model");
					} else {
						if (fragmentationType == FragmentationType.FragmentsContainment) {
							fragmentation.createFragment(this, (FObjectImpl) this.getUserObject());
						}
						// else:
						// the fragment was already be created, we have nothing left to do
					}
				}
			} 
			// else:
			// if the object was a fragment root, the Fragment implementation
			// will automatically delete itself
		} else if (newFragmentURI != null) {
			// this object is added or moved to be an independent root (e.g. as
			// a value in a indexed map)
			if (fragmentation == null) {
				throw new RuntimeException(
						"You cannot at a value to a fragmenting reference if the new container is not part of a fragmented model");
			} else {			
				Fragment oldFragment = null;
				if (isFragmentRoot()) {
					oldFragment = getFragment();
				}
				fragmentation.createFragment(newFragmentURI, this, (FObjectImpl) this.getUserObject());
				if (oldFragment != null) {
					removeFragment(oldFragment);
				}
			}
		} else {
			// this object was removed from the model, it has to be moved to the
			// new objects realm (if necessary) and if it was a fragment root
			// the fragment has to be deleted.
			if (isFragmentRoot()) {
				removeFragment(getFragment());
			}
			UserObjectsCache.newUserObjectsCache.addUserObjectToCache(this, (FObjectImpl) this.getUserObject());
		}
	}

	/**
	 * Removes the fragment of the given root. Removes the resource and the
	 * corresponding entry in persistence. This must not remove the fragment
	 * immediately, since the fragmentRoot is still attached to it.
	 * 
	 * @param fObjectImpl
	 *            The root of the fragment to delete.
	 */
	private void removeFragment(Fragment oldFragment) {
		if  (oldFragment.getResourceSet() != null) {
			try {
				oldFragment.delete(null);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
		// else:
		// the resource was already deleted automatically, because it got empty
	}

}
