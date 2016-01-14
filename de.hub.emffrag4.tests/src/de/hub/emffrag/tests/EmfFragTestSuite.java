package de.hub.emffrag.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hub.emffrag.datastore.tests.EmfFragDataStoreTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
	EmfFragDataStoreTestSuite.class,
	FStoreObjectTests.class,
	EStoreTests.class,
	SerializationTests.class,
	FStoreFragmentationTests.class,
	FragmentationTests.class,
	FragmentationCacheTests.class,
	FragmentationSetTests.class,
	FragmentationSetCacheTests.class
})
public class EmfFragTestSuite {

}
