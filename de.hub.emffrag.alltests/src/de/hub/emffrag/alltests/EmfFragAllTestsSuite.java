package de.hub.emffrag.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hub.emffrag.habse.HBaseEmfFragTestSuite;
import de.hub.emffrag.mongodb.MongodbEmfFragTestSuite;
import de.hub.emffrag2.EmfFragTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
	EmfFragTestSuite.class,
	MongodbEmfFragTestSuite.class,
	HBaseEmfFragTestSuite.class
})
public class EmfFragAllTestsSuite {

}
