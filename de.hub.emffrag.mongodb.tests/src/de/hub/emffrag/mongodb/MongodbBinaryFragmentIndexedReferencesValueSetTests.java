package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentIndexedReferenceValueSetTests;

public class MongodbBinaryFragmentIndexedReferencesValueSetTests extends BinaryFragmentIndexedReferenceValueSetTests {
	
	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}

}