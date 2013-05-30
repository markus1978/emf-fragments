package de.hub.emffrag.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hub.emffrag.fragmentation.FragmentsBaseTestSuite;
import de.hub.emffrag.habse.HBaseFragmentsBaseTestSuite;
import de.hub.emffrag.mongodb.MongodbFragmentsBaseTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
	FragmentsBaseTestSuite.class,
	MongodbFragmentsBaseTestSuite.class,
	HBaseFragmentsBaseTestSuite.class
})
public class EmfFragAllTestsSuite {

}
