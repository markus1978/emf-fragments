package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.CacheTests;

public class MongodbCacheTests extends CacheTests {
	
	@Override
	protected DataStore createTestDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
}
