package de.hub.emffrag.fragmentation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({ 
		// non fragmentation tests
		IndexTests.class, ReflectiveMetaModelTests.class,
		// fragmentation tests based on XMI-fragments
		BasicFragmentationTests.class, 
		CrossReferenceTests.class, 
		CacheTests.class, 
		FileSystemDataStoreTests.class, 
		IndexedReferenceValueSetTests.class,
		IndexedContentsValueSetTests.class,
		IndexedMapTests.class, 
		ContainmentIndexedMapTests.class,
		// fragmentation tests based on binary fragments
		BinaryFragmentBasicFragmentationTests.class, 
		BinaryFragmentCrossReferenceTests.class, 
		BinaryFragmentCacheTests.class, 
		BinaryFragmentFileSystemDataStoreTests.class,
		BinaryFragmentIndexedReferenceValueSetTests.class,
		BinaryFragmentIndexedContentsValueSetTests.class,
		BinaryFragmentIndexedMapTests.class,
		BinaryFragmentContainmentIndexedMapTests.class,
		// opposite test, those change the TestModelPackage and should be run last
		OppositeFragmentedContentsTests.class,
		OppositeIndexedContentsTests.class,
		OppositeIndexedReferencesTests.class})
public class FragmentsBaseTestSuite {
	// nothing
}
