package de.hub.emffrag.habse;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.hub.emffrag.fragmentation.FragmentsBaseTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	    // general datastore tests
		HBaseStoreTest.class,
		HBaseStoreWithScanTest.class,
		HBaseStoreWithBulkInsertTest.class,
		HBaseStoreWithScanAndBulkInsertTest.class,
		// fragmentation tests based on XMI-fragments
		HbaseBasicFragmentationTests.class, 
		HBaseCrossReferenceTests.class, 
		HBaseCacheTests.class,
		HBaseIndexedContentsValueSetTests.class,
		HBaseIndexedReferencesValueSetTests.class,
		// fragmentation tests based on binary fragments
		HBaseBinaryFragmentBasicFragmentationTests.class, 
		HBaseBinaryFragmentCrossReferenceTests.class, 
		HBaseBinaryFragmentCacheTests.class,
		HBaseBinaryFragmentIndexedContentsValueSetTests.class,
		HBaseBinaryFragmentIndexedReferencesValueSetTests.class})
public class HBaseFragmentsBaseTestSuite extends FragmentsBaseTestSuite {
	// nothing
}
