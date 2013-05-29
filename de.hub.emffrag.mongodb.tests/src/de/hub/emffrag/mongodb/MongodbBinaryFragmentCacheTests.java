package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.CacheTests;

public class MongodbBinaryFragmentCacheTests extends CacheTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}
}
