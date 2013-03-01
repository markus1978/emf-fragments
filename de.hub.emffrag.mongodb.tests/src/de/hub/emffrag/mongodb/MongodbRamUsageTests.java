package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.RamUsageTests;

public class MongodbRamUsageTests extends RamUsageTests {
	
	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
	
}
