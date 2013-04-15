package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class FValueSetList extends EcoreEList.Dynamic<FInternalObjectImpl> {

	private static final long serialVersionUID = 1L;
	private final DataIndex<Long> index;
	private final AbstractValueSetSemantics<Long> semantics;
	private final String id;

	public FValueSetList(int kind, Class<?> dataClass, InternalEObject owner, EStructuralFeature feature) {
		super(kind, dataClass, owner, feature);
		FInternalObjectImpl object = (FInternalObjectImpl) owner;

		FragmentedModel model = object.getFragmentation();
		if (model == null) {
			model = EmfFragActivator.instance.defaultModel;
		}
		if (model == null) {
			throw new NotInAFragmentedModelException(
					"Operation on indexed value sets can only be performed for objects contained in a fragmented model.");
		}
		DataStore dataStore = model.getDataStore();

		this.id = createPrefix(object, feature);
		index = new DataIndex<Long>(dataStore, id, LongKeyType.instance);

		if (((EReference) feature).isContainment()) {
			semantics = new IndexedContainmentValueSetSemantics<Long>(model, index, object, feature);
		} else {
			semantics = new IndexedValueSetSemantics<Long>(model, index);
		}

	}

	static String createPrefix(FInternalObjectImpl object, EStructuralFeature feature) {
		String id = EmfFragActivator.instance.idSemantics.getPrefixID(object);

		int featureId = feature.getFeatureID();
		if (((EReference) feature).isContainment()) {
			return FragmentedModel.INDEX_FEATURES_PREFIX + "_" + id + "_" + featureId;
		} else {
			return FragmentedModel.INDEX_FEATURES_PREFIX + "-" + id + "_" + featureId;
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
		size = (int) (long) index.last();
		super.addUnique(object);
	}

	@Override
	public void addUnique(int index, FInternalObjectImpl object) {		
		int indexIndex = ReflectiveMetaModelRegistry.instance.getInverseReferenceIndex((EReference)eStructuralFeature);
		EList<Long> indexes = object.getIndexes();
		if (indexes.size() > indexIndex) {
			Long beforeIndex = indexes.get(indexIndex);
			object.indexBeforeAdd = beforeIndex == null ? -1 : beforeIndex;
			object.addValueSetId = id;
		}
		super.addUnique(index, object);
		object.indexBeforeAdd = -1;
		object.addValueSetId = null;
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
//		throw new UnsupportedOperationException();
	}

	@Override
	public Object clone() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(FInternalObjectImpl e) {
		Long key = index.add();
		performAdd(key, e);		
		return true;
	}

	@Override
	public void doAddUnique(int index, FInternalObjectImpl element) {
		Long last = this.index.last();
		if ((last == null && index == 0) || (last + 1 == index)) {
			performAdd((long) index, element);
		} else {
			EmfFragActivator.instance.warning("Can only add to the bad of an indexed value set. Add the value to the end instead.");
			add(element);
		}
	}
	
	private void performAdd(long key, FInternalObjectImpl object) {
		semantics.setValueForKey(key, object);
		EReference reference = (EReference) eStructuralFeature;
		int index = ReflectiveMetaModelRegistry.instance.getInverseReferenceIndex(reference);
		EList<Long> indexes = object.getIndexes();
		if (indexes.size() > index && indexes.get(index) != null && indexes.get(index).intValue() != -1 && object.indexBeforeAdd == -1) {
			EmfFragActivator.instance
					.warning("An object was added to an indexed value set, but seems to be already part of another indexed value set for the same feature." +
							 "This is not really supported. Some operations of the original value set will not work properly." +
							 "The effected feature is " + eStructuralFeature.getName() + " of class " + eStructuralFeature.getEContainingClass().getName() + ".");
		}
		for (int i = indexes.size(); i <= index; i++) {
			indexes.add(Long.valueOf(-1));
		}
		indexes.set(index, key);
	}

	@Override
	public boolean addAll(Collection<? extends FInternalObjectImpl> c) {
		boolean result = true;
		for (FInternalObjectImpl e : c) {
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
		int indexIndex = ReflectiveMetaModelRegistry.instance.getInverseReferenceIndex((EReference) eStructuralFeature);
		if (o instanceof FInternalObject) {			
			Long index = ((FInternalObject) o).getIndexes().get(indexIndex);
			if (index != null) {
				return o == performGet(index.longValue());
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean result = true;
		for (Object o : c) {
			result &= contains(o);
		}
		return result;
	}

	@Override
	public FInternalObjectImpl get(int index) {
		FInternalObjectImpl result = performGet((long) index);
		if (result == null) {
			throw new IndexOutOfBoundsException();
		}
		return result;
	}
	
	private FInternalObjectImpl performGet(long index) {
		return semantics.getValueForExactKey((long) index);
	}

	@Override
	public int indexOf(Object o) {				
		if (o instanceof FInternalObjectImpl) {			
			FInternalObjectImpl fInternalObject = (FInternalObjectImpl) o;
			if (fInternalObject.indexBeforeAdd != -1 && id.equals(fInternalObject.addValueSetId)) {
				return (int)fInternalObject.indexBeforeAdd;
			} else {
				int indexIndex = ReflectiveMetaModelRegistry.instance.getInverseReferenceIndex((EReference) eStructuralFeature);			
				Long index = fInternalObject.getIndexes().get(indexIndex);
				if (index == null) {
					return -1;
				} else {
					if (performGet(index.longValue()) == o) {
						return index.intValue();
					} else {
						return -1;
					}
				}
			}
		} else {
			return -1;
		}
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
		return indexOf(o);
	}

	@Override
	public ListIterator<FInternalObjectImpl> listIterator() {
		return semantics.iterator(0l, index.last());
	}

	@Override
	public ListIterator<FInternalObjectImpl> listIterator(int index) {
		return semantics.iterator((long) index, this.index.last());
	}

	@Override
	public FInternalObjectImpl doRemove(int index) {
		FInternalObjectImpl value = get(index);
		semantics.removeValueForKey((long) index, value);
		if (id.equals(value.addValueSetId)) {
			int indexIndex = ReflectiveMetaModelRegistry.instance.getInverseReferenceIndex((EReference) eStructuralFeature);
			EList<Long> indexes = value.getIndexes();		
			indexes.set(indexIndex, Long.valueOf(-1));
		}
		return value;		
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof FInternalObjectImpl) {
			int indexIndex = ReflectiveMetaModelRegistry.instance.getInverseReferenceIndex((EReference) eStructuralFeature);
			EList<Long> indexes = ((FInternalObject) o).getIndexes();
			if (indexes.size() <= indexIndex) {
				return false;
			} else {
				Long index = indexes.get(indexIndex);
				if (index == null) {
					return false;
				} else {
					return remove(index.intValue()) != null;
				}
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = true;
		for (Object o : c) {
			result &= remove(o);
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("This method is not supported for indexed value sets.");
	}

	@Override
	public FInternalObjectImpl doSetUnique(int index, FInternalObjectImpl element) {
		FInternalObjectImpl result = get(index);
		semantics.setValueForKey((long) index, element);
		return result;
	}

	@Override
	public int size() {
		Long last = index.last();
		return last == null ? 0 : (int) (last + 1);
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
