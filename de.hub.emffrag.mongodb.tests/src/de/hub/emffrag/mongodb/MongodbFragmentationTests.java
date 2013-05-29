package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BasicFragmentationTests;

public class MongodbFragmentationTests extends BasicFragmentationTests {

	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}
	
}
