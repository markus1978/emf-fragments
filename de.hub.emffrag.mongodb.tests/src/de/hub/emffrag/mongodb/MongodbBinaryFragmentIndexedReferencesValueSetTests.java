package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentIndexedReferenceValueSetTests;

public class MongodbBinaryFragmentIndexedReferencesValueSetTests extends BinaryFragmentIndexedReferenceValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}

}
