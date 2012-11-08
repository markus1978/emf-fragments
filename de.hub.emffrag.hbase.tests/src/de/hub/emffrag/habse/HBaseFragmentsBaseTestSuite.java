package de.hub.emffrag.habse;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.hub.emffrag.fragmentation.FragmentsBaseTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
		// fragmentation tests based on XMI-fragments
		HbaseBasicFragmentationTests.class, HBaseCrossReferenceTests.class, HBaseCacheTests.class,
		// fragmentation tests based on binary fragments
		HBaseBinaryFragmentBasicFragmentationTests.class, HBaseBinaryFragmentCrossReferenceTests.class, 
		HBaseBinaryFragmentCacheTests.class })
public class HBaseFragmentsBaseTestSuite extends FragmentsBaseTestSuite {
	// nothing
}
