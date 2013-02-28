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
		String extrinsicID = internalObject.getExtrinsicID(true);
		if (extrinsicID == null || FInternalObjectImpl.isPreliminary(extrinsicID)) {
			throw new NotInAFragmentedModelException("Indexed value sets can only be used, if the values are already part of a fragmented model.");
		}
		URI uri = model.getExtrinsicIdIndex().createExtrinsicIdUri(extrinsicID);
		index.set(key, uri.toString());
	}
	
	@Override
	public void removeValueForKey(K key, FInternalObjectImpl value) {
		index.remove(key);
	}
}
