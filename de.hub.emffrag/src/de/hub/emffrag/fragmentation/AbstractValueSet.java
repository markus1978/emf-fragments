package de.hub.emffrag.fragmentation;

import java.util.ListIterator;

import de.hub.emffrag.datastore.DataIndex;

public abstract class AbstractValueSet<K,V> {

	protected final FragmentedModel model;
	protected final DataIndex<K> index;
	
	public AbstractValueSet(FragmentedModel model, DataIndex<K> index) {
		super();
		this.model = model;
		this.index = index;
	}
	
	public abstract V getValueForExactKey(K key);
	
	public abstract void setValueForKey(K key, V value);
	
	public abstract void removeValueForKey(K key, V value);
	
	public ListIterator<V> iterator(K first, K last) {
		final K firstIncl = index.exactOrNext(first);
		final K lastExcl = index.next(last);
		return new ListIterator<V>() {			
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
			public V next() {
				if (next == null) {
					next = index.next(current);
				}
				V result = getValueForExactKey(next);
				current = next;
				next = null;
				return result;			
			}

			@Override
			public void add(V e) {
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
			public V previous() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				removeValueForKey(current, getValueForExactKey(current));
			}

			@Override
			public void set(V e) {
				setValueForKey(current, e);	
			}							
		};
	}
}
