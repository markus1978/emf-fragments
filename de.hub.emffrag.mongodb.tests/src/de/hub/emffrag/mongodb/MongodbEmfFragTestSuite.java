package de.hub.emffrag.mongodb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MongodbBasicFragmentationTests.class,
	MongodbCrossReferenceTests.class,
	MongodbFragmentationGCTests.class
})
public class MongodbEmfFragTestSuite {

}
