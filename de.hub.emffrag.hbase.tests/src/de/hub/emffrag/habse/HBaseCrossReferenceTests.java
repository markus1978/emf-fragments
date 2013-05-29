package de.hub.emffrag.habse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.CrossReferenceTests;

public class HBaseCrossReferenceTests extends CrossReferenceTests {
	
	@BeforeClass
	public static void disableLogging() {
		Logger rootLogger = LogManager.getRootLogger();
		rootLogger.setLevel(Level.WARN);

	}

	@Override
	protected IDataStore createTestDataStore() {
		return HBaseDataStoreFactory.createDataStore();
	}
}
