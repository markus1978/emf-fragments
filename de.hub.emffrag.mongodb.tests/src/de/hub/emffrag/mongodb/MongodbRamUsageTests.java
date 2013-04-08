package de.hub.emffrag.mongodb;

import org.junit.Test;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.RamUsageTests;

public class MongodbRamUsageTests extends RamUsageTests {
	
	@Override
	@Test
	public void testConstantRamUsage() throws Exception {
		super.testConstantRamUsage();
	}

	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", "testmodel", true);
	}

	@Override
	@Test
	public void testRamUsageWithIndexedContainment() {
		super.testRamUsageWithIndexedContainment();
	}

	@Override
	@Test
	public void testRamUsageWithIndexedReferences() {
		super.testRamUsageWithIndexedReferences();
	}
	
	
	
}
