package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.IndexedContentsValueSetTests;

public class HBaseIndexedContentsValueSetTests extends IndexedContentsValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		return HBaseDataStoreFactory.createDataStore();
	}
}
