package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentIndexedReferenceValueSetTests;

public class HBaseBinaryFragmentIndexedReferencesValueSetTests extends BinaryFragmentIndexedReferenceValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		return HBaseDataStoreFactory.createDataStore();
	}
}
