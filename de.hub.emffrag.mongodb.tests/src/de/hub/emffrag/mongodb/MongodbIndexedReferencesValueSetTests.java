package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.IndexedReferenceValueSetTests;

public class MongodbIndexedReferencesValueSetTests extends IndexedReferenceValueSetTests {
	
	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}

}
