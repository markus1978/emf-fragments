package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.DataIndex;

public class IndexedValueSet<K,V> extends AbstractValueSet<K, V> {

	public IndexedValueSet(FragmentedModel model, DataIndex<K> index) {
		super(model, index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValueForExactKey(K key) {
		String value = index.get(key);
		if (value == null) {
			return null;
		}
		URI objectUri = model.getExtrinsicIdIndex().getObjectUriForExtrinsicIdUri(URI.createURI(value));
		FInternalObjectImpl internalObject = (FInternalObjectImpl)model.resolveObjectURI(objectUri);
		return (V)internalObject.getUserObject();
	}
	
	@Override
	public void setValueForKey(K key, V value) {
		FInternalObjectImpl internalObject = ((FObjectImpl)value).internalObject();
		internalObject.setIsCrossReferenced();
		Fragment fragment = internalObject.getFragment();
		URI uri = model.getExtrinsicIdIndex().createExtrinsicIdUri(fragment.getID(internalObject));
		index.set(key, uri.toString());
	}
	
	@Override
	public void removeValueForKey(K key, V value) {
		index.remove(key);
	}
}
