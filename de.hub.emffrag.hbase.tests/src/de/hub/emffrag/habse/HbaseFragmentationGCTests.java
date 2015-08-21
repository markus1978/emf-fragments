package de.hub.emffrag.habse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.junit.BeforeClass;

import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.fragmentation.FragmentationGCTests;
import de.hub.emffrag.hbase.HBaseDataStore;

public class HbaseFragmentationGCTests extends FragmentationGCTests {
	
	@BeforeClass
	public static void disableLogging() {
		Logger rootLogger = LogManager.getRootLogger();
		rootLogger.setLevel(Level.WARN);

	}

	@Override
	protected IBaseDataStore createBaseDataStore() {
		URI uri = URI.createURI("hbase://localhost/testmodel");
		return new HBaseDataStore(uri.path().substring(1), true);
	}
}
