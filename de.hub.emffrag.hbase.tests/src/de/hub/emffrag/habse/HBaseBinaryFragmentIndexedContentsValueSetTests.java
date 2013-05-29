package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentIndexedContentsValueSetTests;

public class HBaseBinaryFragmentIndexedContentsValueSetTests extends BinaryFragmentIndexedContentsValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		return HBaseDataStoreFactory.createDataStore();
	}
}
