package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentIndexedContentsValueSetTests;

public class MongodbBinaryFragmentIndexedContentsValueSetTests extends BinaryFragmentIndexedContentsValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}

}
