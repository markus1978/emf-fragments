package de.hub.emffrag.mongodb

import de.hub.emffrag.tests.PerformanceMeasures

class MongodbPerformanceMeasures extends PerformanceMeasures {
	override createDataStore() {
		return new MongoDBDataStore("localhost", "testmodel", true);	
	}
}