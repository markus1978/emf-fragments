package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;

public class FragmentedModelFactory implements Resource.Factory {
	
	public Map<String, DataStore> stores = new HashMap<String, DataStore>();
	
	@Override
	public Resource createResource(URI uri) {											
		String id = uri.authority() + uri.path();
		DataStore dataStore = stores.get(id);
		if (dataStore == null) {
			dataStore = createDataStore(uri);
			stores.put(id, dataStore);
		}
		
		for (String key: stores.keySet()) {
			System.out.println(key.length() + " " + key);
		}
		
		return new FragmentedModel(dataStore) {
			@Override
			protected Fragment newFragment(URI uri, FragmentedModel model) {
				if (EmfFragActivator.instance.useBinaryFragments) {
					return new BinaryFragmentImpl(uri, model);
				} else {
					return super.newFragment(uri, model);
				}
			}					
		};
	}		
	
	protected DataStore createDataStore(URI uri) {
		return new InMemoryDataStore(uri.scheme(), uri.authority(), uri.path().substring(1), false);
	}
}
