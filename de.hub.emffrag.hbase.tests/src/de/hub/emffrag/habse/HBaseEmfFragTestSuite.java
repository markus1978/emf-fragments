package de.hub.emffrag.habse;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	HbaseBasicFragmentationTests.class,
	HbaseCrossReferenceTests.class,
	HbaseFragmentationGCTests.class
})
public class HBaseEmfFragTestSuite {

}
