package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class FObjectImpl extends AccessNotifyingEObjectImpl implements FObject {
	
	private static PooledStackMultiMap<FObject, Fragmentation> fragmentationsWithOpenTransactions = new PooledStackMultiMap<FObject, Fragmentation>();

	private Fragmentation fragmentationToLoadFrom = null;
	private Fragment itsLoadingIntoFragment = null;

	private boolean isNotifying() {
		return true;
	}

	@Override
	public boolean eNotificationRequired() {
		return isNotifying();
	}

	@Override
	public boolean fIsProxy() {
		return super.eIsProxy();
	}

	@Override
	public boolean eIsProxy() {
		fEnsureLoaded();
		return super.eIsProxy();
	}
	
	private Fragment markModified() {
		Fragment fragment = (Fragment) eResource();
		if (fragment != null && fragment.isLoaded() && !fragment.isLoading()) {
			fragment.setModified(true);
		}
		return fragment;
	}
	
	@Override
	protected void onBeginTransaction() {
		fEnsureLoaded();
		Fragmentation fragmentation = fFragmentation();
		if (fragmentation != null) {
			fragmentation.lockFragmentsCache();
			fragmentationsWithOpenTransactions.push(this, fragmentation);
		} else {
			fragmentationsWithOpenTransactions.push(this, Fragmentation.NULL);
		}
	}
	
	@Override
	protected void onEndTransaction() {
		Fragmentation fragmentationWithOpenTransaction = fragmentationsWithOpenTransactions.pop(this);
		if (fragmentationWithOpenTransaction != null) {
			fragmentationWithOpenTransaction.unlockFragmentsCache();
		}
	}

	@Override
	public void eNotify(Notification notification) {
		fEnsureLoaded();

		// TODO this mark modified might be too late for larger changes that work over multiple notifications.
		// E.g. when an object is moved, its removed from its old container first and than added. With 
		// very small fragment caches, this results in unloading of old container resource before it was marked modified.
		// Modification must be tracked at a lower level, or save always, or better lock fragments over whole transactions.
		Fragment fragment = markModified();
		// We have to ensure that we are not currently loading the
		// containing fragment
		//
		if (fragment != null && fragment.isLoaded() && !fragment.isLoading()) {
			Fragmentation fFragmentation = fFragmentation();
			if (fFragmentation != null) {
				fFragmentation.onChange(notification);
			}

			if (eBasicHasAdapters() && eDeliver()) {
				super.eNotify(notification);
			}
		}
	}

	@Override
	protected void onAccess() {
		if (isNotifying()) {
			fEnsureLoaded();
		}
	}

	protected void fSetFragmentationToLoadFrom(
			Fragmentation fragmentationToLoadFrom) {
		this.fragmentationToLoadFrom = fragmentationToLoadFrom;
	}

	protected void fSetItsLoadingIntoFragment(Fragment fragment) {
		this.itsLoadingIntoFragment = fragment;
	}

	@SuppressWarnings("rawtypes")
	public void fUnload(Fragmentation fragmentationToLoadFrom) {
		this.fragmentationToLoadFrom = fragmentationToLoadFrom;
		if (this.fragmentationToLoadFrom == null) {
			throw new IllegalStateException(
					"Cannot unload an object that does not belong to a fragmentation.");
		}

		boolean isRoot = eDirectResource() != null;

		// if this becomes something not MinimalEObjectImpl,
		// eBasicProperties
		// has to be cleaned instead
		//
		eSetDirectResource(null);

		// keep container for fragment roots (because it will not be recovered
		// when reloaded)
		if (!isRoot) {
			fNoAccessBasicSetContainer(null);
		}

		// re-create empty eSettings for further use of the object
		int size = eClass().getFeatureCount() - eStaticFeatureCount();
		if (size != 0) {
			eBasicSetSettings(new Object[size]);
		}
	}

	@Override
	public boolean fIsUnLoaded() {
		return fragmentationToLoadFrom != null;
	}

	public void fEnsureLoaded() {
		if (fIsUnLoaded()) {
			// if this object gets re-loaded the load process will reuse it,
			// deproxyfy it and re-add its settings automatically.
			EcoreUtil.resolve(this, fragmentationToLoadFrom);
		}
	}

	/**
	 * @return true iff the object is at the top of the containment hierarchy
	 *         within the resource that contains it.
	 */
	public boolean fIsRoot() {
		fEnsureLoaded();
		return eDirectResource() != null;
	}

	/**
	 * @return the fragmentation this object belongs to or null, if the object
	 *         was not added to a fragmentation yet.
	 */
	public Fragmentation fFragmentation() {
		Fragment fragment = fFragment();
		if (fragment != null) {
			Fragmentation fragmentation = fragment.getFragmentation();
			return fragmentation;
		} else {
			return null;
		}
	}

	@Override
	public Fragment fFragment() {
		return (Fragment) eResource();
	}

	/**
	 * Overridden to ensure {@link Resource} is a {@link Fragment}.
	 */
	@Override
	protected void eSetDirectResource(Internal resource) {
		if (resource != null && !(resource instanceof Fragment)) {
			throw new IllegalStateException(
					"FObjects can only be added to Fragments not to other Resources.");
		}
		super.eSetDirectResource(resource);
	}

	/**
	 * Overridden to ensure this is only contained by other {@link FObject}s.
	 * TODO: not covered by tests.
	 */
	@Override
	public NotificationChain eBasicSetContainer(InternalEObject newContainer,
			int newContFeatID, NotificationChain msgs) {
		if (newContainer != null && !(newContainer instanceof FObjectImpl)) {
			throw new IllegalStateException(
					"FObjects can only be contained by other FObjects not other EObjects.");
		}
		return super.eBasicSetContainer(newContainer, newContFeatID, msgs);
	}

	/**
	 * Modified copy override that does nothing if the old container is the new
	 * container. Unloading and loading of containers causes unwanted behavior
	 * otherwise. On unloading the "old" container becomes a cached user object.
	 * On loading this cached user object is reused, hence the container is
	 * already there. When adding the contents to the container, the reverse add
	 * tries to reset the container, which entails removing the "old" container
	 * and setting the "new" (same) container. This remove and set does not
	 * work, because remove also removes the content from the container
	 * (reverses the currently executed action) and set does only set the
	 * container to the contents but NOT vice versa. Therefore removing and
	 * resetting the same container does not yield the same before state.
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, Class<?> baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			return eInverseAdd(otherEnd,
					eDerivedStructuralFeatureID(featureID, baseClass), msgs);
		} else {
			if (eInternalContainer() != null) {
				// do nothing if the new container already is the old container
				if (otherEnd == eInternalContainer()) {
					return msgs;
				}
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return eBasicSetContainer(otherEnd, featureID, msgs);
		}
	}

	/**
	 * Copy override that does not set the base container to null for similar
	 * reasons than in
	 * {@link #eInverseAdd(InternalEObject, int, Class, NotificationChain)}.
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, Class<?> baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			return eInverseRemove(otherEnd, eDerivedStructuralFeatureID(featureID, baseClass), msgs);
		} else {
			return msgs;
		}
	}

	/**
	 * Overridden to weak cache references.
	 */
	@Override
	protected <E> EList<E> createListWrapper(EList<E> source,
			EStructuralFeature feature) {
		Fragmentation fragmentation = fFragmentation();
		if (fragmentation == null && itsLoadingIntoFragment != null) {
			fragmentation = itsLoadingIntoFragment.getFragmentation();
		}
		if (fragmentation != null) {
			Fragment fragment = fFragment();
			if (fragment == null) {
				fragment = itsLoadingIntoFragment;
			}
			long fragmentId = fragment.fFragmentId();
			int objectId = fragment.getID(this, true);
			int featureId = feature.getFeatureID();
			EList<E> cachedReference = fragmentation
					.getUserCaches()
					.getRegisteredUserReference(fragmentId, objectId, featureId);

			if (cachedReference != null) {
				((AccessNotifyingEListWrapper<E>) cachedReference).setDelegateList(source);
				return cachedReference;
			} else {
				EList<E> newReference = super.createListWrapper(source, feature);
				fragmentation.getUserCaches().registerUserReference(fragmentId, objectId, featureId, newReference);
				return newReference;
			}
		} else {
			return super.createListWrapper(source, feature);
		}
	}
	
	@Override
	protected <E> void onAccess(AccessNotifyingEListWrapper<E> listWrapper) {
		// empty, the implementation below does break some test cases
	}

//	@Override
//	protected <E> void onAccess(AccessNotifyingEListWrapper<E> listWrapper) {
//		if (fIsUnLoaded()) {
//			// install an empty list for potential loading
//			BasicEList<E> save = (BasicEList<E>)listWrapper.getDelegateList();
//			BasicEList<E> newList = new BasicEList<E>();
//			listWrapper.setDelegateList(newList);
//			onAccess(); 
//			
//			// compare if loaded list is equal to existing list
//			boolean equal = true;
//			int size = save.size();
//			equal = equal && (size == newList.size());
//			if (equal) {
//				for (int i = 0; i < size; i++) {
//					equal = equal && (save.get(i) == newList.get(i));
//				}
//			}
//			if (!equal) {
//				// list was changed during absence -> raise mod count
//				// save.setData(save.size(), save.data());
//			}			
//			listWrapper.setDelegateList(save);	
//		}
//	}
}
