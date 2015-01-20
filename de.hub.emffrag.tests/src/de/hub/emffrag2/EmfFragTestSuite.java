package de.hub.emffrag2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AccessNotificationTests.class,
	FragmentsCacheTests.class,
	UUIDBinaryResourceImplTests.class,
	BasicFragmentationTests.class
})
public class EmfFragTestSuite {

}
