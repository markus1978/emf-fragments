package de.hub.emffrag.mongodb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MongodbFragmentationTests.class,
	MongodbFragmentationCacheTests.class
})
public class MongodbEmfFragTestSuite {

}
