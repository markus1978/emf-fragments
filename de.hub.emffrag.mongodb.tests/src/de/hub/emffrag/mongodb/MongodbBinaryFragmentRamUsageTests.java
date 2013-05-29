package de.hub.emffrag.mongodb;

import org.junit.Test;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.RamUsageTests;

public class MongodbBinaryFragmentRamUsageTests extends RamUsageTests {
	
	@Override
	@Test
	public void testConstantRamUsage() throws Exception {
		super.testConstantRamUsage();
	}
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}
}
