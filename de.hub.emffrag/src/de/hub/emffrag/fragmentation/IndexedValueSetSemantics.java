package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.IDataIndex;
import de.hub.emffrag.fragmentation.IdSemantics.SaveURI;

public class IndexedValueSetSemantics<K> extends AbstractValueSetSemantics<K> {
		
	public IndexedValueSetSemantics(FragmentedModel model, IDataIndex<K> index) {
		super(model, index);
	}

	@Override
	public FInternalObjectImpl getValueForExactKey(K key) {
		String value = index.get(key);
		if (value == null) {
			return null;
		}		
		FInternalObject internalObject = EmfFragActivator.instance.idSemantics.resolveURI(URI.createURI(value), model);
		return (FInternalObjectImpl)internalObject;
	}
	
	@Override
	public void setValueForKey(final K key, FInternalObjectImpl internalObject) {	
		SaveURI save = new SaveURI() {					
			@Override
			public void saveURI(URI uri) {		
				index.set(key, uri.toString());
			}
		};
		URI uri = EmfFragActivator.instance.idSemantics.getURI(internalObject, model, true, save);
		index.set(key, uri.toString());
	}

	@Override
	public void removeValueForKey(K key, FInternalObjectImpl value) {
		index.remove(key);
	}
}
