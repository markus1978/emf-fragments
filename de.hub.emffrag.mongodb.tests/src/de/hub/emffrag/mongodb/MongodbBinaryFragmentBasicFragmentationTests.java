package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentBasicFragmentationTests;

public class MongodbBinaryFragmentBasicFragmentationTests extends BinaryFragmentBasicFragmentationTests {

	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
}
