package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.DataIndex;

public class IndexedValueSetSemantics<K> extends AbstractValueSetSemantics<K> {

	public IndexedValueSetSemantics(FragmentedModel model, DataIndex<K> index) {
		super(model, index);
	}

	@Override
	public FInternalObjectImpl getValueForExactKey(K key) {
		String value = index.get(key);
		if (value == null) {
			return null;
		}
		URI objectUri = model.getExtrinsicIdIndex().getObjectUriForExtrinsicIdUri(URI.createURI(value));
		FInternalObjectImpl internalObject = (FInternalObjectImpl)model.resolveObjectURI(objectUri);
		return internalObject;
	}
	
	@Override
	public void setValueForKey(K key, FInternalObjectImpl internalObject) {
		internalObject.setIsCrossReferenced();
		Fragment fragment = internalObject.getFragment();
		URI uri = model.getExtrinsicIdIndex().createExtrinsicIdUri(fragment.getID(internalObject));
		index.set(key, uri.toString());
	}
	
	@Override
	public void removeValueForKey(K key, FInternalObjectImpl value) {
		index.remove(key);
	}
}
