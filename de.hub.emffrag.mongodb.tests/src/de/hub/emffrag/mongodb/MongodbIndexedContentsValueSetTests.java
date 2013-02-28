package de.hub.emffrag.mongodb;

import org.junit.Test;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.IndexedContentsValueSetTests;

public class MongodbIndexedContentsValueSetTests extends IndexedContentsValueSetTests {
	
	@Override
	protected DataStore createTestDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);
	}
	
	@Override
	@Test
	public void addTest() {
		super.addTest();
	}

}
