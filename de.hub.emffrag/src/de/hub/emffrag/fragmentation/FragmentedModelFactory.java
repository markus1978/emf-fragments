package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.ScanningDataStore;
import de.hub.emffrag.datastore.WriteCachingDataStore;

public class FragmentedModelFactory implements Resource.Factory {
	
	public Map<String, IDataStore> stores = new HashMap<String, IDataStore>();
	
	@Override
	public Resource createResource(URI uri) {											
		String id = uri.authority() + uri.path();
		IDataStore dataStore = stores.get(id);
		if (dataStore == null) {
			dataStore = createDataStore(uri);
			stores.put(id, dataStore);
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
	
	protected IDataStore createDataStore(URI uri) {
		InMemoryDataStore baseDataStore = new InMemoryDataStore(false);
		return new DataStoreImpl(new WriteCachingDataStore(new ScanningDataStore(baseDataStore, baseDataStore.createScanningScanExtension()), baseDataStore), uri);
	}
}
