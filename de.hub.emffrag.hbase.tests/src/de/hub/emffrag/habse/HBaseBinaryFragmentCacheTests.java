package de.hub.emffrag.habse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.CacheTests;
import de.hub.emffrag.hbase.HBaseDataStore;

public class HBaseBinaryFragmentCacheTests extends CacheTests {
	
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
