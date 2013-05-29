package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentBasicFragmentationTests;

public class MongodbBinaryFragmentBasicFragmentationTests extends BinaryFragmentBasicFragmentationTests {

	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}
}
