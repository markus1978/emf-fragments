package de.hub.emffrag.mongodb;

import org.junit.Test;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.BasicFragmentationTests;

public class MongodbFragmentationTests extends BasicFragmentationTests {

	@Override
	protected DataStore createTestDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);
	}

	@Test
	@Override
	public void testAddFragment() {
		super.testAddFragment();
	}
	
	
}
