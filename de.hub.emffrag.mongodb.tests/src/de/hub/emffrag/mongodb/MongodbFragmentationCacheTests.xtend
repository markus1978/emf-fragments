package de.hub.emffrag.mongodb

import de.hub.emffrag.tests.FragmentationCacheTests

class MongodbFragmentationCacheTests extends FragmentationCacheTests {
	override createDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);	
	}
}