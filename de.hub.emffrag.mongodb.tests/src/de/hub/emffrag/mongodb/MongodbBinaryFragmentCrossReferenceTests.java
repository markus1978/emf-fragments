package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentCrossReferenceTests;

public class MongodbBinaryFragmentCrossReferenceTests extends BinaryFragmentCrossReferenceTests {
	
	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
}
