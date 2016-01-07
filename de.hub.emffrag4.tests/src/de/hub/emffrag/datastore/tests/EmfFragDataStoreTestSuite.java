package de.hub.emffrag.datastore.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	InMemoryStoreTest.class,
	InMemoryStoreWithBulkInsertTest.class,
	InMemoryStoreWithScanTest.class,
	InMemoryStoreWithScanAndBulkInsertTest.class
})
public class EmfFragDataStoreTestSuite {

}
