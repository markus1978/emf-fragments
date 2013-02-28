package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.BinaryFragmentIndexedContentsValueSetTests;

public class MongodbBinaryFragmentIndexedContentsValueSetTests extends BinaryFragmentIndexedContentsValueSetTests {
	
	@Override
	protected DataStore createTestDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);
	}

}
