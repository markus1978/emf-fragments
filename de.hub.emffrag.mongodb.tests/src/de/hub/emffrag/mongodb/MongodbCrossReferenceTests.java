package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.CrossReferenceTests;

public class MongodbCrossReferenceTests extends CrossReferenceTests {
	
	@Override
	protected DataStore createTestDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
}
