package de.hub.emffrag.habse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.CrossReferenceTests;
import de.hub.emffrag.hbase.HBaseDataStore;

public class HBaseCrossReferenceTests extends CrossReferenceTests {
	
	@BeforeClass
	public static void disableLogging() {
		Logger rootLogger = LogManager.getRootLogger();
		rootLogger.setLevel(Level.WARN);

	}

	@Override
	protected DataStore createTestDataStore() {
		return new HBaseDataStore("testmodel", true);
	}
}
