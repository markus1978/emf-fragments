package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.IndexedReferenceValueSetTests;

public class MongodbIndexedReferencesValueSetTests extends IndexedReferenceValueSetTests {
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}

}
