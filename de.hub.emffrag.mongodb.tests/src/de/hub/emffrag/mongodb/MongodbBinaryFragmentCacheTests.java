package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.CacheTests;

public class MongodbBinaryFragmentCacheTests extends CacheTests {
	
	@Override
	protected DataStore createTestDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
}
