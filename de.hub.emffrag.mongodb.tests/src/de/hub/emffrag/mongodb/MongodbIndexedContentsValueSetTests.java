package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.IndexedContentsValueSetTests;

public class MongodbIndexedContentsValueSetTests extends IndexedContentsValueSetTests {
	
	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
}
