package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class FList<E> implements EList<E> {
	
	private final DataIndex<Long> index;
	private final AbstractValueSet<Long, E> valueSet;
	
	private long last = 0;
	
	public FList(FInternalObjectImpl object, EStructuralFeature feature) {		
		Fragment fragment = object.getFragment();
		if (fragment == null) {
			throw new IllegalStateException("Operation on indexed value sets can only be performed for objects contained in a fragmented model.");
		}
		FragmentedModel model = fragment.getFragmentedModel();
		DataStore dataStore = model.getDataStore();
		
		String extrinsicID = object.getExtrinsicID(true);
		int featureId = feature.getFeatureID();
		String indexPrefix = FragmentedModel.INDEX_FEATURES_PREFIX + "_" + extrinsicID + "_" + featureId;

		index = new DataIndex<Long>(dataStore, indexPrefix, LongKeyType.instance);
		Long first = index.first();
		Long last = index.last();
		if (first == null || last == null) {
			this.last = -1;
		} else {
			this.last = last;
			if (first != 0) {
				throw new RuntimeException("Indexed value sets always start with index 0");
			}
		}
		
		if (((EReference)feature).isContainment()) {
			valueSet = new IndexedContainmentValueSet<Long, E>(model, index, (FObjectImpl)object.getUserObject());	
		} else {
			valueSet = new IndexedValueSet<Long, E>(model, index);
		}
		
	}

	@Override
	public boolean add(E e) {
		valueSet.setValueForKey(LongKeyType.instance.next(last), e);
		return true;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = true;
		for (E e: c) {
			result &= add(e);
		}
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public E get(int index) {
		return (E)valueSet.getValueForExactKey((long)index);
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean isEmpty() {
		return last >= 0;
	}

	@Override
	public Iterator<E> iterator() {
		return valueSet.iterator(0l, last);
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public ListIterator<E> listIterator() {
		return valueSet.iterator(0l, last);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return valueSet.iterator((long)index, last);
	}

	@Override
	public E remove(int index) {
		E value = get(index);
		valueSet.removeValueForKey((long)index, value);
		return value;
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public E set(int index, E element) {
		E result = get(index);
		valueSet.setValueForKey((long)index, element);
		return result;
	}

	@Override
	public int size() {
		return (int)(last + 1);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public void move(int newPosition, E object) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public E move(int newPosition, int oldPosition) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	
}
