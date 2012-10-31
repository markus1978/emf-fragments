package de.hub.emffrag.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.hub.emffrag.fragmentation.CacheTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ IndexTests.class, ReflectiveMetaModelTests.class, BasicFragmentationTests.class,
		CrossReferenceTests.class, CacheTests.class })
public class FragmentsBaseTestSuite {
	// nothing
}
