package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.datastore.DataIndex;

public class IndexedContainmentValueSetSemantics<K> extends IndexedValueSetSemantics<K> {
	
	private final FInternalObjectImpl container;
	private final EStructuralFeature feature;

	public IndexedContainmentValueSetSemantics(FragmentedModel model, DataIndex<K> index, FInternalObjectImpl container, EStructuralFeature feature) {
		super(model, index);
		this.container = container;
		this.feature = feature;
	}

	@Override
	public FInternalObjectImpl getValueForExactKey(K key) {
		if (index.exists(key)) {
			return (FInternalObjectImpl)model.resolveObjectURI(index.getURI(key).appendFragment("/"));
		} else {
			return null;
		}
	}
	
	@Override
	public void setValueForKey(K key, FInternalObjectImpl value) {
		index.set(key, ""); // TODO this will indirectly create a fragment, but what if there already is a fragment .. test it at least	
		if (feature == null) {
			value.eBasicSetContainerForIndexClass(container, index.getURI(key));
		} else {
			value.fragmentURIForContainerChange(index.getURI(key));
		}
//		value.updateContainment((FInternalObjectImpl)value.eContainer(), value.eContainingFeature(), container, feature, index.getURI(key));
	}

	@Override
	public void removeValueForKey(K key, FInternalObjectImpl value) {
		super.removeValueForKey(key, value);
//		value.updateContainment((FInternalObjectImpl)value.eContainer(), value.eContainingFeature(), null, feature, null);
	}
	
	
}
