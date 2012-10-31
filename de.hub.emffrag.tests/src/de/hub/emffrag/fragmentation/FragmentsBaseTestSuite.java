package de.hub.emffrag.fragmentation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.hub.emffrag.test.ReflectiveMetaModelTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ IndexTests.class, ReflectiveMetaModelTests.class, BasicFragmentationTests.class,
		CrossReferenceTests.class, CacheTests.class })
public class FragmentsBaseTestSuite {
	// nothing
}
