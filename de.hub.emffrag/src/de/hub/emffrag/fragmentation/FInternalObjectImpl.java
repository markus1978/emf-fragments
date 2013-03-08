package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

public class FInternalObjectImpl extends DynamicEObjectImpl implements FInternalObject {

	boolean hasPriliminaryId = false;
	boolean hasDefaultModelId = false;

	public FInternalObjectImpl(EClass eClass) {
		super(eClass);
		onLoad();
	}

	public boolean isFragmentRoot() {
		Resource eResource = eResource();
		return eResource != null && eResource instanceof Fragment
				&& (eContainer() == null || eResource != eContainer().eResource());
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

	@Override
	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
		Resource oldResource = eResource();
		NotificationChain result = super.eSetResource(resource, notifications);
		updateAfterResourceChange(oldResource);
		return result;
	}

	private void updateAfterResourceChange(Resource oldResource) {
		EmfFragActivator.instance.idSemantics.onRequiredId(this);
		Fragment oldFragment = (Fragment) oldResource;
		Fragment newFragment = (Fragment) getFragment();
		if (oldFragment == newFragment) {
			return;
		}

		UserObjectsCache oldUOC = oldFragment != null ? oldFragment.getUserObjectsCache()
				: UserObjectsCache.newUserObjectsCache;
		UserObjectsCache newUOC = newFragment != null ? newFragment.getUserObjectsCache()
				: UserObjectsCache.newUserObjectsCache;

		updateUOCAfterFragmentChange(oldUOC, newUOC, this, newFragment);

		if (newFragment != null && oldFragment == null) {
			onCreate();
			EPackage metaModel = eClass().getEPackage();
			FragmentedModel fragmentedModel = newFragment.getFragmentedModel();
			if (fragmentedModel != null) {
				fragmentedModel.getInternalResourceSet().getPackageRegistry().put(metaModel.getNsURI(), metaModel);
			}
		}

		if (newFragment != null) {
			updateIdsAfterContainerChange(this, newFragment);
		} else {
			// TODO remove objects from the index?
		}
	}

	private EList<EObject> eContentsWithOutFragments() {
		EList<EObject> result = eProperties().getEContents();
		if (result == null) {
			EStructuralFeature[] eStructuralFeatures = ((EClassImpl.FeatureSubsetSupplier) this.eClass()
					.getEAllStructuralFeatures()).containments();
			EStructuralFeature[] eStructuralFeaturesWithOutFragmentsArray = null;
			if (eStructuralFeatures != null) {
				List<EStructuralFeature> eStructuralFeaturesWithOutFragments = new ArrayList<EStructuralFeature>(
						eStructuralFeatures.length);
				for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
					FragmentationType fragmentationType = EMFFragUtil.getFragmentationType(eStructuralFeature);
					if (fragmentationType == FragmentationType.None || eStructuralFeature instanceof EAttribute) {
						eStructuralFeaturesWithOutFragments.add(eStructuralFeature);
					}
				}
				eStructuralFeaturesWithOutFragmentsArray = eStructuralFeaturesWithOutFragments
						.toArray(new EStructuralFeature[eStructuralFeaturesWithOutFragments.size()]);
			}

			EContentsEList<EObject> eContentsEListWithOutFragments = eStructuralFeaturesWithOutFragmentsArray == null ? EContentsEList
					.<EObject> emptyContentsEList() : new EContentsEList<EObject>(this,
					eStructuralFeaturesWithOutFragmentsArray);

			eBasicProperties().setEContents(result = eContentsEListWithOutFragments);
		}

		return result;
	}

	private void updateUOCAfterFragmentChange(UserObjectsCache oldUOC, UserObjectsCache newUOC, FInternalObjectImpl object,
			Fragment fragment) {
		oldUOC.moveUserObject(object, newUOC);

		for (EObject content : object.eContentsWithOutFragments()) {
			updateUOCAfterFragmentChange(oldUOC, newUOC, (FInternalObjectImpl) content, fragment);
		}
	}

	private void updateIdsAfterContainerChange(FInternalObjectImpl object, Fragment fragment) {
		EmfFragActivator.instance.idSemantics.onContainerChange(object, fragment.getFragmentedModel());	
		for (EObject content : object.eContentsWithOutFragments()) {
			updateIdsAfterContainerChange((FInternalObjectImpl) content, fragment);
		}
	}

	private void updateAfterContainerChange(Fragment oldFragment) {
		EStructuralFeature eContainingFeature = eContainingFeature();
		FragmentationType fragmentationType = eContainingFeature != null ? EMFFragUtil.getFragmentationType(eContainingFeature)
				: FragmentationType.None;
		boolean isFragmenting = fragmentationType == FragmentationType.FragmentsContainment
				|| fragmentationType == FragmentationType.FragmentsIndexedContainment;
		FragmentedModel fragmentation = getFragmentation();
		if (isFragmenting && fragmentation != null) {
			if (fragmentURIForContainerChange != null) {
				fragmentation.createFragment(fragmentURIForContainerChange, this);
			} else if (!isFragmentRoot()) {
				fragmentation.createFragment(null, this);
			}
		}

		Fragment newFragment = getFragment();
		if (eContainer() == null && newFragment != null) {
			// Object was removed (container set to null.
			// This will delete the resource (fragment) based on normal emf
			// semantics.
			newFragment.getContents().remove(this);
		}

		if (oldFragment != eResource()) {
			updateAfterResourceChange(oldFragment);
			if (oldFragment != null) {
				// delete if the old fragment is empty and is not deleted
				// already
				if (oldFragment.getContents().isEmpty() && oldFragment.getResourceSet() != null) {
					oldFragment.getFragmentedModel().deleteFragment(oldFragment);
				}
			}
		} else {
			if (newFragment != null) {
				updateIdsAfterContainerChange(this, newFragment);
			}
		}

		fragmentURIForContainerChange = null;
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

	void eBasicSetContainerForIndexClass(FInternalObjectImpl indexClass, URI uri) {
		if (!eIsProxy()) {
			// Do not update containment if the object is a proxy. In that case
			// the object is loaded and not modified by a user.
			Resource oldResource = eResource();
			indexClass.getFragmentation().createFragment(uri, this);
			updateAfterResourceChange(oldResource);
		}
	}

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		Fragment oldFragment = getFragment();
		super.eBasicSetContainer(newContainer, newContainerFeatureID);

		if (!eIsProxy()) {
			// Do not update containment if the object is a proxy. In that case
			// the object is loaded and not modified by a user.
			updateAfterContainerChange(oldFragment);
		}
	}

	private void onLoad() {
		if (EmfFragActivator.instance.collectStatistics) {
			setLoaded(getLoaded() + 1);
		}
	}

	private void onCreate() {
		if (EmfFragActivator.instance.collectStatistics) {
			long loaded = getLoaded();
			if (loaded > 0) {
				setLoaded(loaded - 1);
			}
		}
	}

	void onAccess() {
		if (EmfFragActivator.instance.collectStatistics) {
			setAccessed(getAccessed() + 1);
		}
	}

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getAccessed() <em>Accessed</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAccessed()
	 * @generated
	 * @ordered
	 */
	protected static final long ACCESSED_EDEFAULT = 0L;
	/**
	 * The default value of the '{@link #getLoaded() <em>Loaded</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLoaded()
	 * @generated
	 * @ordered
	 */
	protected static final long LOADED_EDEFAULT = 0L;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FInternalObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InternalPackage.Literals.FINTERNAL_OBJECT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	// @Override
	// protected EClass eStaticClass() {
	// return InternalPackage.Literals.FINTERNAL_OBJECT;
	// }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId() {
		return (String) eDynamicGet(InternalPackage.FINTERNAL_OBJECT__ID, InternalPackage.Literals.FINTERNAL_OBJECT__ID, true,
				true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId) {
		eDynamicSet(InternalPackage.FINTERNAL_OBJECT__ID, InternalPackage.Literals.FINTERNAL_OBJECT__ID, newId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<EObject> getExtensions() {
		return (EList<EObject>) eDynamicGet(InternalPackage.FINTERNAL_OBJECT__EXTENSIONS,
				InternalPackage.Literals.FINTERNAL_OBJECT__EXTENSIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public long getAccessed() {
		return (Long) eDynamicGet(InternalPackage.FINTERNAL_OBJECT__ACCESSED,
				InternalPackage.Literals.FINTERNAL_OBJECT__ACCESSED, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAccessed(long newAccessed) {
		eDynamicSet(InternalPackage.FINTERNAL_OBJECT__ACCESSED, InternalPackage.Literals.FINTERNAL_OBJECT__ACCESSED,
				newAccessed);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public long getLoaded() {
		return (Long) eDynamicGet(InternalPackage.FINTERNAL_OBJECT__LOADED, InternalPackage.Literals.FINTERNAL_OBJECT__LOADED,
				true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLoaded(long newLoaded) {
		eDynamicSet(InternalPackage.FINTERNAL_OBJECT__LOADED, InternalPackage.Literals.FINTERNAL_OBJECT__LOADED, newLoaded);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case InternalPackage.FINTERNAL_OBJECT__EXTENSIONS:
			return ((InternalEList<?>) getExtensions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case InternalPackage.FINTERNAL_OBJECT__ID:
			return getId();
		case InternalPackage.FINTERNAL_OBJECT__EXTENSIONS:
			return getExtensions();
		case InternalPackage.FINTERNAL_OBJECT__ACCESSED:
			return getAccessed();
		case InternalPackage.FINTERNAL_OBJECT__LOADED:
			return getLoaded();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case InternalPackage.FINTERNAL_OBJECT__ID:
			setId((String) newValue);
			return;
		case InternalPackage.FINTERNAL_OBJECT__EXTENSIONS:
			getExtensions().clear();
			getExtensions().addAll((Collection<? extends EObject>) newValue);
			return;
		case InternalPackage.FINTERNAL_OBJECT__ACCESSED:
			setAccessed((Long) newValue);
			return;
		case InternalPackage.FINTERNAL_OBJECT__LOADED:
			setLoaded((Long) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case InternalPackage.FINTERNAL_OBJECT__ID:
			setId(ID_EDEFAULT);
			return;
		case InternalPackage.FINTERNAL_OBJECT__EXTENSIONS:
			getExtensions().clear();
			return;
		case InternalPackage.FINTERNAL_OBJECT__ACCESSED:
			setAccessed(ACCESSED_EDEFAULT);
			return;
		case InternalPackage.FINTERNAL_OBJECT__LOADED:
			setLoaded(LOADED_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case InternalPackage.FINTERNAL_OBJECT__ID:
			return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
		case InternalPackage.FINTERNAL_OBJECT__EXTENSIONS:
			return !getExtensions().isEmpty();
		case InternalPackage.FINTERNAL_OBJECT__ACCESSED:
			return getAccessed() != ACCESSED_EDEFAULT;
		case InternalPackage.FINTERNAL_OBJECT__LOADED:
			return getLoaded() != LOADED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	private URI fragmentURIForContainerChange = null;

	public void fragmentURIForContainerChange(URI uri) {
		fragmentURIForContainerChange = uri;
	}
}
