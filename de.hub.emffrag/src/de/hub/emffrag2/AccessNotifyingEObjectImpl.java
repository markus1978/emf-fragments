package de.hub.emffrag2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;

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

	@SuppressWarnings({ "rawtypes" })
	protected EList<?> createListWrapper(final EList<?> source, EStructuralFeature feature) {
		return new EListWrapper() {
			@Override
			protected EList<?> delegateList() {
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
	
	/**
	 * A wrapper for a serious of interfaces, which are simultanously implemented by EMF's value sets.
	 */
	private static abstract class EListWrapper<E> implements InternalEList<E>, InternalEList.Unsettable<E>, EStructuralFeature.Setting {
		protected abstract EList<E> delegateList();
		
		private InternalEList<E> delegateInternalEList() {
			return (InternalEList<E>)delegateList();
		}
		
		private EStructuralFeature.Setting delegateSetting() {
			return (EStructuralFeature.Setting)delegateList();
		}
		
		public boolean add(E arg0) {
			return delegateInternalEList().add(arg0);
		}
		public void add(int arg0, E arg1) {
			delegateInternalEList().add(arg0, arg1);
		}
		public boolean addAll(Collection<? extends E> arg0) {
			return delegateInternalEList().addAll(arg0);
		}
		public boolean addAll(int arg0, Collection<? extends E> arg1) {
			return delegateInternalEList().addAll(arg0, arg1);
		}
		public E basicGet(int index) {
			return delegateInternalEList().basicGet(index);
		}
		public List<E> basicList() {
			return delegateInternalEList().basicList();
		}
		public Iterator<E> basicIterator() {
			return delegateInternalEList().basicIterator();
		}
		public ListIterator<E> basicListIterator() {
			return delegateInternalEList().basicListIterator();
		}
		public ListIterator<E> basicListIterator(int index) {
			return delegateInternalEList().basicListIterator(index);
		}
		public Object[] basicToArray() {
			return delegateInternalEList().basicToArray();
		}
		public <T> T[] basicToArray(T[] array) {
			return delegateInternalEList().basicToArray(array);
		}
		public int basicIndexOf(Object object) {
			return delegateInternalEList().basicIndexOf(object);
		}
		public int basicLastIndexOf(Object object) {
			return delegateInternalEList().basicLastIndexOf(object);
		}
		public boolean basicContains(Object object) {
			return delegateInternalEList().basicContains(object);
		}
		public boolean basicContainsAll(Collection<?> collection) {
			return delegateInternalEList().basicContainsAll(collection);
		}
		public NotificationChain basicRemove(Object object, NotificationChain notifications) {
			return delegateInternalEList().basicRemove(object, notifications);
		}
		public NotificationChain basicAdd(E object, NotificationChain notifications) {
			return delegateInternalEList().basicAdd(object, notifications);
		}
		public void addUnique(E object) {
			delegateInternalEList().addUnique(object);
		}
		public void addUnique(int index, E object) {
			delegateInternalEList().addUnique(index, object);
		}
		public boolean addAllUnique(Collection<? extends E> collection) {
			return delegateInternalEList().addAllUnique(collection);
		}
		public boolean addAllUnique(int index, Collection<? extends E> collection) {
			return delegateInternalEList().addAllUnique(index, collection);
		}
		public void clear() {
			delegateInternalEList().clear();
		}
		public boolean contains(Object arg0) {
			return delegateInternalEList().contains(arg0);
		}
		public boolean containsAll(Collection<?> arg0) {
			return delegateInternalEList().containsAll(arg0);
		}
		public boolean equals(Object arg0) {
			return delegateInternalEList().equals(arg0);
		}
		public E get(int arg0) {
			return delegateInternalEList().get(arg0);
		}
		public int hashCode() {
			return delegateInternalEList().hashCode();
		}
		public int indexOf(Object arg0) {
			return delegateInternalEList().indexOf(arg0);
		}
		public boolean isEmpty() {
			return delegateInternalEList().isEmpty();
		}
		public Iterator<E> iterator() {
			return delegateInternalEList().iterator();
		}
		public int lastIndexOf(Object arg0) {
			return delegateInternalEList().lastIndexOf(arg0);
		}
		public ListIterator<E> listIterator() {
			return delegateInternalEList().listIterator();
		}
		public ListIterator<E> listIterator(int arg0) {
			return delegateInternalEList().listIterator(arg0);
		}
		public void move(int newPosition, E object) {
			delegateInternalEList().move(newPosition, object);
		}
		public E move(int newPosition, int oldPosition) {
			return delegateInternalEList().move(newPosition, oldPosition);
		}
		public E remove(int arg0) {
			return delegateInternalEList().remove(arg0);
		}
		public boolean remove(Object arg0) {
			return delegateInternalEList().remove(arg0);
		}
		public boolean removeAll(Collection<?> arg0) {
			return delegateInternalEList().removeAll(arg0);
		}
		public boolean retainAll(Collection<?> arg0) {
			return delegateInternalEList().retainAll(arg0);
		}
		public E set(int arg0, E arg1) {
			return delegateInternalEList().set(arg0, arg1);
		}
		public E setUnique(int index, E object) {
			return delegateInternalEList().setUnique(index, object);
		}
		public int size() {
			return delegateInternalEList().size();
		}
		public List<E> subList(int arg0, int arg1) {
			return delegateInternalEList().subList(arg0, arg1);
		}
		public Object[] toArray() {
			return delegateInternalEList().toArray();
		}
		public <T> T[] toArray(T[] arg0) {
			return delegateInternalEList().toArray(arg0);
		}
		public EObject getEObject() {
			return delegateSetting().getEObject();
		}
		public EStructuralFeature getEStructuralFeature() {
			return delegateSetting().getEStructuralFeature();
		}
		public Object get(boolean resolve) {
			return delegateSetting().get(resolve);
		}
		public void set(Object newValue) {
			delegateSetting().set(newValue);
		}
		public boolean isSet() {
			return delegateSetting().isSet();
		}
		public void unset() {
			delegateSetting().unset();
		}
	}	
}
