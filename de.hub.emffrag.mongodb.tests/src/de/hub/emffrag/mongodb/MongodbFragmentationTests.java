package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.BasicFragmentationTests;

public class MongodbFragmentationTests extends BasicFragmentationTests {

	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
	
}
