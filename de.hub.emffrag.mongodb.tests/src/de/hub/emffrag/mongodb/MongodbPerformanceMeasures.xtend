package de.hub.emffrag.mongodb

import de.hub.emffrag.tests.PerformanceMeasures
import org.junit.Test

class MongodbPerformanceMeasures extends PerformanceMeasures {
	override createDataStore() {
		return new MongoDBDataStore("localhost", null, "testmodel", true);	
	}
	
	@Test
	override measureModelCreate() {
		super.measureModelCreate()
	}
	
}