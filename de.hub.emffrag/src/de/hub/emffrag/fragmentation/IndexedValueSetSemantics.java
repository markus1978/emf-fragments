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
		URI objectUri = model.getIdIndex().getObjectUriForIdUri(URI.createURI(value));
		FInternalObjectImpl internalObject = (FInternalObjectImpl)model.resolveObjectURI(objectUri);
		return internalObject;
	}
	
	@Override
	public void setValueForKey(K key, FInternalObjectImpl internalObject) {
		String id = internalObject.getId(true);
		if (id == null || FInternalObjectImpl.isPreliminary(id)) {
			throw new NotInAFragmentedModelException("Indexed value sets can only be used, if the values are already part of a fragmented model.");
		}
		URI uri = model.getIdIndex().createIdUri(id);
		index.set(key, uri.toString());
	}
	
	@Override
	public void removeValueForKey(K key, FInternalObjectImpl value) {
		index.remove(key);
	}
}
