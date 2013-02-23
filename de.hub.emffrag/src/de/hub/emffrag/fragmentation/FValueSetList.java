package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class FValueSetList extends EcoreEList.Dynamic<FInternalObjectImpl> {

	private static final long serialVersionUID = 1L;
	private final DataIndex<Long> index;
	private final AbstractValueSetSemantics<Long> semantics;

	public FValueSetList(int kind, Class<?> dataClass, InternalEObject owner, EStructuralFeature feature) {
		super(kind, dataClass, owner, feature);
		FInternalObjectImpl object = (FInternalObjectImpl)owner;
	
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
		
		if (((EReference)feature).isContainment()) {
			semantics = new IndexedContainmentValueSetSemantics<Long>(model, index, object, feature);	
		} else {
			semantics = new IndexedValueSetSemantics<Long>(model, index);
		}
		
	}

	@Override
	protected Object[] newData(int capacity) {
		return null;
	}

	@Override
	protected FInternalObjectImpl assign(int index, FInternalObjectImpl object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] data() {
		return null;
	}

	@Override
	public void setData(int size, Object[] data) {
		
	}

	@Override
	public FInternalObjectImpl basicGet(int index) {
		return primitiveGet(index);
	}

	@Override
	protected FInternalObjectImpl primitiveGet(int index) {
		return get(index);
	}

	@Override
	public void addUnique(FInternalObjectImpl object) {
		size = (int)(long)index.last();
		super.addUnique(object);
	}

	@Override
	public void addUnique(int index, FInternalObjectImpl object) {
		super.addUnique(index, object);
	}

	@Override
	public boolean addAllUnique(Collection<? extends FInternalObjectImpl> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAllUnique(int index, Collection<? extends FInternalObjectImpl> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAllUnique(Object[] objects, int start, int end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAllUnique(int index, Object[] objects, int start, int end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void shrink() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void grow(int minimumCapacity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object clone() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean add(FInternalObjectImpl e) {
		semantics.setValueForKey(index.add(), e);		
		return true;
	}

	@Override
	public void doAddUnique(int index, FInternalObjectImpl element) {
		Long last = this.index.last();
		if ((last == null && index == 0) || (last + 1 == index)) {
			semantics.setValueForKey((long)index, element);
		}
	}

	@Override
	public boolean addAll(Collection<? extends FInternalObjectImpl> c) {
		boolean result = true;
		for (FInternalObjectImpl e: c) {
			result &= add(e);
		}
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends FInternalObjectImpl> c) {
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
	public FInternalObjectImpl get(int index) {
		FInternalObjectImpl result = semantics.getValueForExactKey((long)index);
		if (result == null) {
			throw new IndexOutOfBoundsException();
		}
		return result;
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public boolean isEmpty() {
		return index.last() == null;
	}

	@Override
	public Iterator<FInternalObjectImpl> iterator() {
		return semantics.iterator(0l, index.last());
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public ListIterator<FInternalObjectImpl> listIterator() {
		return semantics.iterator(0l, index.last());
	}

	@Override
	public ListIterator<FInternalObjectImpl> listIterator(int index) {
		return semantics.iterator((long)index, this.index.last());
	}

	@Override
	public FInternalObjectImpl doRemove(int index) {
		if ((long)index == this.index.last()) {
			FInternalObjectImpl value = get(index);
			semantics.removeValueForKey((long)index, value);
			return value;
		} else {
			throw new IllegalArgumentException("You can only remove the last entry of an indexed value set.");
		}
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
	public FInternalObjectImpl doSetUnique(int index, FInternalObjectImpl element) {
		FInternalObjectImpl result = get(index);
		semantics.setValueForKey((long)index, element);
		return result;
	}

	@Override
	public int size() {
		Long last = index.last();
		return last == null ? 0 : (int)(last + 1);
	}

	@Override
	public List<FInternalObjectImpl> subList(int fromIndex, int toIndex) {
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
	public void move(int newPosition, FInternalObjectImpl object) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public FInternalObjectImpl move(int newPosition, int oldPosition) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public Iterator<FInternalObjectImpl> basicIterator() {
		super.basicIterator();
		return iterator();
	}
	
	
}
