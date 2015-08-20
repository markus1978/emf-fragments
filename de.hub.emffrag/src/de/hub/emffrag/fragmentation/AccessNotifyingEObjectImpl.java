package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

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
 * reflective/dynamic feature delegation. It also notifies when a "Transaction"
 * is started an ended. All EMF operations that are triggered through EMF's
 * public interface (EObject,EList) are considered "Transaction"s.
 */
public abstract class AccessNotifyingEObjectImpl extends MinimalEObjectImpl {

	/**
	 * Is called on each read access to this object, or one of its contained
	 * feature lists/maps. Return true, if the object was changed as a result of
	 * this call.
	 */
	protected void onAccess() {

	}
	
	protected <E> void onAccess(AccessNotifyingEListWrapper<E> listWrapper) {
		
	}

	protected void onBeginTransaction() {

	}

	protected void onEndTransaction() {

	}

	@Override
	public void dynamicSet(int dynamicFeatureID, Object value) {
		if (value instanceof EList) {
			EStructuralFeature eStructuralFeature = eDynamicFeature(dynamicFeatureID);
			if (!eStructuralFeature.isTransient()) {
				if (FeatureMapUtil.isFeatureMap(eStructuralFeature)) {
					// TODO wrapper for feature maps
				} else if (eStructuralFeature.isMany()) {
					super.dynamicSet(dynamicFeatureID, createListWrapper((EList<?>) value, eStructuralFeature));
					return;
				}
			}
		}
		super.dynamicSet(dynamicFeatureID, value);
	}

	protected <E> EList<E> createListWrapper(final EList<E> source, EStructuralFeature feature) {
		return new AccessNotifyingEListWrapper<E>(source);
	}

	@Override
	public InternalEObject eInternalContainer() {
		onAccess();
		return super.eInternalContainer();
	}
	
	public InternalEObject fNoAccessInternalContainer() {
		return super.eInternalContainer();
	}

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer) {
		onAccess();
		super.eBasicSetContainer(newContainer);
	}
	
	protected void fNoAccessBasicSetContainer(InternalEObject newContainer) {	
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

	protected final Object[] fNoAccessBasicSettings() {
		Object[] result =  super.eBasicSettings();
		if (result == null) {
			eSettings();
			result = super.eBasicSettings();
		}
		return result;
	}

	/**
	 * Overridden: When getting a many feature for the first, EMF creates an
	 * EList as a value set realization. We create a wrapper around that list
	 * for access notification to those lists. EMF's code structure does not
	 * allow to return the wrapper, on the eGet that creates it. Therefore, we
	 * have to get it again.
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		Object value = super.eGet(featureID, resolve, coreType);
		if (value != null && value instanceof EList<?>) {
			return fNoAccessDynamicGet(featureID);
		}

		return value;
	}

	/**
	 * Returns the value directly, without the feature setting and without an
	 * access notification.
	 */
	public Object fNoAccessDynamicGet(int featureID) {
		Object[] eBasicSettings = fNoAccessBasicSettings();
		return eBasicSettings[featureID - eStaticFeatureCount()];
	}

	public void fNoAccessDynamicSet(int featureID, Object value) {
		Object[] eBasicSettings = fNoAccessBasicSettings();
		eBasicSettings[featureID - eStaticFeatureCount()] = value;
	}

	@SuppressWarnings("unchecked")
	private <E> void fEnsureAccessNotifyingListWarpper(EList<E> wrapper, EStructuralFeature feature) {
		int featureID = feature.getFeatureID();
		EList<E> value = (EList<E>) fNoAccessDynamicGet(featureID);
		if (value == null) {
			fNoAccessDynamicSet(featureID, wrapper);
		} else if (value != wrapper) {
			if (value instanceof AccessNotifyingEListWrapper) {
				throw new IllegalStateException();
			} else {
				((AccessNotifyingEListWrapper<E>) wrapper).setDelegateList(value);
				fNoAccessDynamicSet(featureID, wrapper);
			}
		}
	}

	@Override
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		onBeginTransaction();
		try {
			super.eSet(eFeature, newValue);
		} finally {
			onEndTransaction();
		}
	}

	@Override
	public void eUnset(EStructuralFeature eFeature) {
		onBeginTransaction();
		try {
			super.eUnset(eFeature);
		} finally {
			onEndTransaction();
		}
	}

	/**
	 * A wrapper for a serious of interfaces, which are simultanously
	 * implemented by EMF's value sets.
	 */
	public class AccessNotifyingEListWrapper<E> implements InternalEList<E>, InternalEList.Unsettable<E>,
			EStructuralFeature.Setting {
		private EList<E> delegateList = null;

		private AccessNotifyingEListWrapper(EList<E> delegateList) {
			this.delegateList = delegateList;
		}

		public void setDelegateList(EList<E> delegateList) {
			this.delegateList = delegateList;
		}
		
		public EList<E> getDelegateList() {
			return delegateList;
		}
		
		private void onAccess() {
			// AccessNotifyingEObjectImpl.this.onAccess(this);
			AccessNotifyingEObjectImpl.this.onAccess();
			// onAccess might have compromised the installed list wrapper. We
			// make sure it gets installed anyways.
			fEnsureAccessNotifyingListWarpper(this, ((EStructuralFeature.Setting) delegateList).getEStructuralFeature());
		}

		private EList<E> delegateList() {
			onAccess();
			return delegateList;
		}

		private InternalEList<E> delegateInternalEList() {
			return (InternalEList<E>) delegateList();
		}

		private EStructuralFeature.Setting delegateSetting() {
			return (EStructuralFeature.Setting) delegateList();
		}

		public boolean add(E arg0) {
			onBeginTransaction();
			try {
				return delegateInternalEList().add(arg0);
			} finally {
				onEndTransaction();
			}
		}

		public void add(int arg0, E arg1) {
			onBeginTransaction();
			try {
				delegateInternalEList().add(arg0, arg1);
			} finally {
				onEndTransaction();
			}
		}

		public boolean addAll(Collection<? extends E> arg0) {
			onBeginTransaction();
			try {
				return delegateInternalEList().addAll(arg0);
			} finally {
				onEndTransaction();
			}
		}

		public boolean addAll(int arg0, Collection<? extends E> arg1) {
			onBeginTransaction();
			try {
				return delegateInternalEList().addAll(arg0, arg1);
			} finally {
				onEndTransaction();
			}
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
			onBeginTransaction();
			try {
				delegateInternalEList().clear();
			} finally {
				onEndTransaction();
			}
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
			return new IteratorWrapper<E>(this, delegateInternalEList().iterator());
		}

		public int lastIndexOf(Object arg0) {
			return delegateInternalEList().lastIndexOf(arg0);
		}

		public ListIterator<E> listIterator() {
			return new ListIteratorWrapper<E>(this, delegateInternalEList().listIterator());
		}

		public ListIterator<E> listIterator(int arg0) {
			return new ListIteratorWrapper<E>(this, delegateInternalEList().listIterator(arg0));
		}

		public void move(int newPosition, E object) {
			onBeginTransaction();
			try {
				delegateInternalEList().move(newPosition, object);
			} finally {
				onEndTransaction();
			}
		}

		public E move(int newPosition, int oldPosition) {
			onBeginTransaction();
			try {
				return delegateInternalEList().move(newPosition, oldPosition);
			} finally {
				onEndTransaction();
			}
		}

		public E remove(int arg0) {
			onBeginTransaction();
			try {
				return delegateInternalEList().remove(arg0);
			} finally {
				onEndTransaction();
			}
		}

		public boolean remove(Object arg0) {
			onBeginTransaction();
			try {
				return delegateInternalEList().remove(arg0);
			} finally {
				onEndTransaction();
			}
		}

		public boolean removeAll(Collection<?> arg0) {
			onBeginTransaction();
			try {
				return delegateInternalEList().removeAll(arg0);
			} finally {
				onEndTransaction();
			}
		}

		public boolean retainAll(Collection<?> arg0) {
			onBeginTransaction();
			try {
				return delegateInternalEList().retainAll(arg0);
			} finally {
				onEndTransaction();
			}
		}

		public E set(int arg0, E arg1) {
			onBeginTransaction();
			try {
				return delegateInternalEList().set(arg0, arg1);
			} finally {
				onEndTransaction();
			}
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
			onBeginTransaction();
			try {
				delegateSetting().set(newValue);
			} finally {
				onEndTransaction();
			}
		}

		public boolean isSet() {
			onBeginTransaction();
			try {
				return delegateSetting().isSet();
			} finally {
				onEndTransaction();
			}
		}

		public void unset() {
			onBeginTransaction();
			try {
				delegateSetting().unset();
			} finally {
				onEndTransaction();
			}
		}
	}
	
	private class IteratorWrapper<E> implements Iterator<E> {
		private final AccessNotifyingEListWrapper<E> listWrapper;
		private final Iterator<E> delegate;

		public IteratorWrapper(AccessNotifyingEListWrapper<E> listWrapper, Iterator<E> delegate) {
			super();
			this.listWrapper = listWrapper;
			this.delegate = delegate;
		}

		private Iterator<E> delegateIterator() {
			//listWrapper.onAccess();
			return delegate;
		}

		public boolean hasNext() {
			return delegateIterator().hasNext();
		}

		public E next() {
			return delegateIterator().next();
		}

		public void remove() {
			onBeginTransaction();
			try {
				delegateIterator().remove();
			} finally {
				onEndTransaction();
			}
		}

		public void forEachRemaining(Consumer<? super E> action) {
			delegateIterator().forEachRemaining(action);
		}
	}
	
	private class ListIteratorWrapper<E> implements ListIterator<E> {
		private final AccessNotifyingEListWrapper<E> listWrapper;
		private final ListIterator<E> delegate;
		
		public ListIteratorWrapper(AccessNotifyingEListWrapper<E> listWrapper, ListIterator<E> delegate) {
			super();
			this.listWrapper = listWrapper;
			this.delegate = delegate;
		}

		private ListIterator<E> delegateIterator() {
			//listWrapper.onAccess();
			return delegate;
		}

		public boolean hasNext() {
			return delegateIterator().hasNext();
		}

		public E next() {
			return delegateIterator().next();
		}

		public boolean hasPrevious() {
			return delegateIterator().hasPrevious();
		}

		public void forEachRemaining(Consumer<? super E> action) {
			delegateIterator().forEachRemaining(action);
		}

		public E previous() {
			return delegateIterator().previous();
		}

		public int nextIndex() {
			return delegateIterator().nextIndex();
		}

		public int previousIndex() {
			return delegateIterator().previousIndex();
		}

		public void remove() {
			onBeginTransaction();
			try {
				delegateIterator().remove();
			} finally {
				onEndTransaction();
			}
		}

		public void set(E e) {
			try {
				delegateIterator().set(e);
			} finally {
				onEndTransaction();
			}
		}

		public void add(E e) {
			try {
				delegateIterator().add(e);
			} finally {
				onEndTransaction();
			}
		}
	}
}
