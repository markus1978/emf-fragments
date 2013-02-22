package de.hub.emffrag.fragmentation;

import de.hub.emffrag.datastore.DataIndex;

public class IndexedContainmentValueSet<K,V> extends IndexedValueSet<K,V> {
	
	private final FObjectImpl container;

	public IndexedContainmentValueSet(FragmentedModel model, DataIndex<K> index, FObjectImpl container) {
		super(model, index);
		this.container = container;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValueForExactKey(K key) {
		if (index.existis(key)) {
			return (V)((FInternalObjectImpl)model.resolveObjectURI(index.getURI(key).appendFragment("/"))).getUserObject();
		} else {
			return null;
		}
	}
	
	@Override
	public void setValueForKey(K key, V value) {
		// this creates an DB entry indirectly
		((FObjectImpl)value).updateContainment(container, -1, index.getURI(key));
	}

	@Override
	public void removeValueForKey(K key, V value) {
		super.removeValueForKey(key, value);
		((FObjectImpl)value).updateContainment(null, -1, null);
	}
}
