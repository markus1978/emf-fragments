package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentCrossReferenceTests;

public class MongodbBinaryFragmentCrossReferenceTests extends BinaryFragmentCrossReferenceTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}
}
