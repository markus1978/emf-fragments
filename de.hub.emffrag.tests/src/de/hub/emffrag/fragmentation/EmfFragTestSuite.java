package de.hub.emffrag.fragmentation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	BasicFragmentationTests.class,
	FragmentationGCTests.class,
	CrossReferenceTests.class
})
public class EmfFragTestSuite {

}
