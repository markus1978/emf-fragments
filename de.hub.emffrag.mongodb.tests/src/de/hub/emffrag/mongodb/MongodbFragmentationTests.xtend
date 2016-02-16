package de.hub.emffrag.mongodb

import de.hub.emffrag.tests.FragmentationTests

class MongodbFragmentationTests extends FragmentationTests {
	override createDataStore() {
		return new MongoDBDataStore("localhost", null, "testmodel", true);	
	}
}