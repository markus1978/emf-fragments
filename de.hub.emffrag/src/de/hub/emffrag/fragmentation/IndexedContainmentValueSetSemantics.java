package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import de.hub.emffrag.datastore.DataIndex;

public class IndexedContainmentValueSetSemantics<K> extends IndexedValueSetSemantics<K> {
	
	private final FInternalObjectImpl container;
	private final int featureId;

	public IndexedContainmentValueSetSemantics(FragmentedModel model, DataIndex<K> index, FInternalObjectImpl container, EStructuralFeature feature) {
		super(model, index);
		this.container = container;
		this.featureId = feature == null ? - 1 : feature.getFeatureID();
	}

	@Override
	public FInternalObjectImpl getValueForExactKey(K key) {
		if (index.existis(key)) {
			return (FInternalObjectImpl)model.resolveObjectURI(index.getURI(key).appendFragment("/"));
		} else {
			return null;
		}
	}
	
	@Override
	public void setValueForKey(K key, FInternalObjectImpl value) {
		index.set(key, ""); // TODO this will indirectly create a fragment, but what if there already is a fragment .. test it at least
		value.updateContainment(container, InternalEObject.EOPPOSITE_FEATURE_BASE - featureId, index.getURI(key));
	}

	@Override
	public void removeValueForKey(K key, FInternalObjectImpl value) {
		super.removeValueForKey(key, value);
		value.updateContainment(null, featureId, null);
	}
}
