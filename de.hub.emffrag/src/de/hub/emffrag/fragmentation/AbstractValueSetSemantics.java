package de.hub.emffrag.fragmentation;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import de.hub.emffrag.datastore.DataIndex;

public abstract class AbstractValueSetSemantics<K> {

	protected final FragmentedModel model;
	protected final DataIndex<K> index;
	
	public AbstractValueSetSemantics(FragmentedModel model, DataIndex<K> index) {
		super();
		this.model = model;
		this.index = index;
	}
	
	public abstract FInternalObjectImpl getValueForExactKey(K key);
	
	public abstract void setValueForKey(K key, FInternalObjectImpl value);
	
	public abstract void removeValueForKey(K key, FInternalObjectImpl value);
	
	public ListIterator<FInternalObjectImpl> iterator(K first, K last) {
		final K firstIncl = index.exactOrNext(first);
		final K lastExcl = last == null ? null : index.next(last);
		return new ListIterator<FInternalObjectImpl>() {			
			K current = null;
			K next = firstIncl;
			@Override
			public boolean hasNext() {
				if (next == null) {
					if (current != null) {
						next = index.next(current);
					} else {
						next = null;
					}
				}
				return next != null && !next.equals(lastExcl);
			}

			@Override
			public FInternalObjectImpl next() {
				if (next == null) {
					if (current != null) {
						next = index.next(current);
					} else {
						throw new NoSuchElementException();
					}
				}
				FInternalObjectImpl result = getValueForExactKey(next);
				current = next;
				next = null;
				return result;			
			}

			@Override
			public void add(FInternalObjectImpl e) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean hasPrevious() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public FInternalObjectImpl previous() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void set(FInternalObjectImpl e) {
				throw new UnsupportedOperationException();
			}							
		};
	}
}
