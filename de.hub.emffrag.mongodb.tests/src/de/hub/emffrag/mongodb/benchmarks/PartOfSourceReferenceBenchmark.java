package de.hub.emffrag.mongodb.benchmarks;

import de.hub.emffrag.benchmarks.reference2.BenchmarkRunner;
import de.hub.emffrag.benchmarks.reference2.IBenchmark;
import de.hub.emffrag.benchmarks.reference2.IBenchmarkFactory;
import de.hub.emffrag.benchmarks.reference2.ReferenceBenchmark;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.mongodb.EmfFragMongoDBActivator;
import de.hub.emffrag.mongodb.MongoDBDataStore;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class PartOfSourceReferenceBenchmark extends ReferenceBenchmark implements IBenchmarkFactory {

	@Override
	public IBenchmark<?> createBenchmark() {
		PartOfSourceReferenceBenchmark partOfSourceReferenceBenchmark = new PartOfSourceReferenceBenchmark();
		partOfSourceReferenceBenchmark.reference = TestModelPackage.eINSTANCE.getTestObject_CrossReferences();
		partOfSourceReferenceBenchmark.factory = TestModelFactory.eINSTANCE;
		return partOfSourceReferenceBenchmark;
	}

	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", parameters.getModelName(), parameters.command == Command.importModel);
	}
	
	public static void main(String args[]) throws Exception {
		BenchmarkRunner.main(args);
	}
}
