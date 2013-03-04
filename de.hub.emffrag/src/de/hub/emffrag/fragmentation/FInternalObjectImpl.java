package de.hub.emffrag.fragmentation;

import java.io.IOException;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreEList;

import com.google.common.base.Throwables;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.EmfFragActivator.IdBehaviour;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

public class FInternalObjectImpl extends DynamicEObjectImpl implements FInternalObject {

	private boolean hasPriliminaryId = false;
	private static String preliminaryID = "PRELIMINARY_ID";

	public static boolean isPreliminary(String id) {
		return preliminaryID.equals(id);
	}

	public FInternalObjectImpl(EClass eClass) {
		super(eClass);
	}

	public boolean isFragmentRoot() {
		Resource eResource = eResource();
		return eResource != null && eResource instanceof Fragment
				&& (eContainer() == null || eResource != eContainer().eResource());
	}

	public String getId(boolean issueIfNecessary) {
		String id = getId();
		
		if (id != null) {
			if (EmfFragActivator.instance.idBehaviour == IdBehaviour.defaultModel) {
				FragmentedModel fragmentedModel = getFragmentation();
				if (fragmentedModel != null) {
					fragmentedModel.getIdIndex().updateObjectURI(id, this);
				}
			} 
			return id;
		} else {			
			if (issueIfNecessary) {
				FragmentedModel fragmentedModel = getFragmentation();
				if (fragmentedModel != null) {
					id = fragmentedModel.getIdIndex().issueId(this);
					setId(id);
					hasPriliminaryId = false;
					return id;
				} else if (hasPriliminaryId) {
					return preliminaryID;
				} else if (EmfFragActivator.instance.idBehaviour == IdBehaviour.preliminary) {
					hasPriliminaryId = true;
					return preliminaryID;
				} else if (EmfFragActivator.instance.idBehaviour == IdBehaviour.defaultModel) {
					id = EmfFragActivator.instance.defaultModelForIdBehavior.getIdIndex().issueId(this);
					setId(id);
					return id;
				} else {
					throw new NotInAFragmentedModelException("Could not issue an ID because the object is not part of a fragmented model.");
				}
			} else if (hasPriliminaryId) {
				FragmentedModel fragmentedModel = getFragmentation();
				if (fragmentedModel != null) {
					id = fragmentedModel.getIdIndex().issueId(this);
					setId(id);
					hasPriliminaryId = false;
				} else {
					return preliminaryID;
				}
			}
		}
		
		return null;
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

	public boolean hasId() {
		return getId() != null || hasPriliminaryId;
	}

	/**
	 * If the object changes resources, its ID index entry has to move
	 * too. Further, the cross reference entry needs to be updated.
	 * 
	 * TODO This should be part of updateContainment ?
	 */
	@Override
	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
		Fragment oldResource = (Fragment) eResource();
		Fragment newResource = (Fragment) resource;

		NotificationChain result = super.eSetResource(resource, notifications);

		if (oldResource != null && newResource != null && oldResource.getFragmentedModel() == newResource.getFragmentedModel()) {
			// case: object is moved from one to another fragment in the same
			// model
			String id = getId();
			FragmentedModel fragmentedModel = getFragmentation();
			if (fragmentedModel != null) {
				if (hasId() || id != null) {
					fragmentedModel.getIdIndex().updateObjectURI(id, this);
				}
			}
		} else {
			if (oldResource != null) {
				if (newResource != null) {
					throw new UnsupportedOperationException("Moving objects between fragment models is not yet supported."); // TODO
				}

				// case: the object is removed from the fragmented model
				String id = getId();
				FragmentedModel fragmentedModel = getFragmentation();
				if (fragmentedModel != null) {
					if (hasId() || id != null) {
						fragmentedModel.getIdIndex().updateObjectURI(id, this);
					}
				}
			}
			if (newResource != null) {
				// case: the object is added to the fragmented model
				getUserObjectCache().removeCachedUserObject(this);
				newResource.getUserObjectsCache().addUserObjectToCache(this, (FObjectImpl) getUserObject());

				FragmentedModel newFragmentedModel = newResource.getFragmentedModel();
				if (newFragmentedModel != null) {
					EPackage metaModel = eClass().getEPackage();
					newFragmentedModel.getInternalResourceSet().getPackageRegistry().put(metaModel.getNsURI(), metaModel);
				}
			}
		}

		return result;
	}

	/**
	 * Some objects need to have an IDs. These are objects with
	 * indexed value sets, or indexed class instances.
	 */
	private void ensureHasRequiredId() {
		if (getFragmentation() != null && getId(false) == null) {
			boolean idIsRequired = false;
			idIsRequired |= ReflectiveMetaModelRegistry.instance.getInternalClass(EmfFragPackage.eINSTANCE.getIndexedMap()).isInstance(this);
			for (EStructuralFeature feature: eClass().getEAllStructuralFeatures()) {
				if (feature instanceof EReference) { 
					FragmentationType fragmentationType = EMFFragUtil.getFragmentationType(feature);
					idIsRequired |= fragmentationType == FragmentationType.FragmentsIndexedContainment ||
							fragmentationType == FragmentationType.IndexedReferences;
				}
			}
			if (idIsRequired) {
				getId(true);			
			}
		}
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
			return new EStructuralFeatureImpl.InternalSettingDelegateMany(
					EStructuralFeatureImpl.InternalSettingDelegateMany.DATA_DYNAMIC, eFeature) {
				@Override
				protected Setting createDynamicSetting(InternalEObject owner) {
					int kind = EcoreEList.Generic.kind(eFeature);
					return new FValueSetList(kind, FInternalObjectImpl.class, FInternalObjectImpl.this, eFeature);
				}
			};
		}
	}

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
		if (!eIsProxy()) {
			// Do not update containment if the object is a proxy. In that case
			// the object is loaded and not modified by a user.
			updateContainment(newContainer, newContainerFeatureID, null);
		}
	}

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
			EStructuralFeature feature;
			if (newContainerFeatureID <= EOPPOSITE_FEATURE_BASE) {
				newContainerFeatureID = EOPPOSITE_FEATURE_BASE - newContainerFeatureID;
				feature = newContainer.eClass().getEStructuralFeature(newContainerFeatureID);
			} else {
				EStructuralFeature containerFeature = this.eClass().getEStructuralFeature(newContainerFeatureID);
				feature = ((EReference) containerFeature).getEOpposite();
				if (feature == null) {
					throw new RuntimeException("Unknown opposite.");
				}
			}
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
						// the fragment was already be created, we have nothing
						// left to do
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
				Fragment fragment = getFragment();
				fragment.getContents().remove(this);
				removeFragment(fragment);
			}
			UserObjectsCache.newUserObjectsCache.addUserObjectToCache(this, (FObjectImpl) this.getUserObject());
		}
		
		ensureHasRequiredId();
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
		if (oldFragment.getResourceSet() != null) {
			try {
				oldFragment.delete(null);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
		// else:
		// the resource was already deleted automatically, because it got empty
	}

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FInternalObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
//	@Override
//	protected EClass eStaticClass() {
//		return InternalPackage.Literals.FINTERNAL_OBJECT;
//	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return (String)eDynamicGet(InternalPackage.eINSTANCE.getFInternalObject_Id(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		eDynamicSet(InternalPackage.eINSTANCE.getFInternalObject_Id(), newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {		
//		switch (featureID) {
//			case InternalPackage.FINTERNAL_OBJECT__ID:
//				return getId();
//		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
//		switch (featureID) {
//			case InternalPackage.FINTERNAL_OBJECT__ID:
//				setId((String)newValue);
//				return;
//		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
//		switch (featureID) {
//			case InternalPackage.FINTERNAL_OBJECT__ID:
//				setId(ID_EDEFAULT);
//				return;
//		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
//		switch (featureID) {
//			case InternalPackage.FINTERNAL_OBJECT__ID:
//				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
//		}
		return super.eIsSet(featureID);
	}

}
