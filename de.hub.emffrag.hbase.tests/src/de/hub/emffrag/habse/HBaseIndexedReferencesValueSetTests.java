package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.IndexedReferenceValueSetTests;

public class HBaseIndexedReferencesValueSetTests extends IndexedReferenceValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		return HBaseDataStoreFactory.createDataStore();
	}
	
}
