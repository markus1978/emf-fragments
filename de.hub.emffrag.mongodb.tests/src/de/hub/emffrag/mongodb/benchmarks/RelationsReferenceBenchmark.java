package de.hub.emffrag.mongodb.benchmarks;

import de.hub.emffrag.benchmarks.reference.BenchmarkRunner;
import de.hub.emffrag.benchmarks.reference.IBenchmark;
import de.hub.emffrag.benchmarks.reference.IBenchmarkFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class RelationsReferenceBenchmark extends AbstractMongoDBBenchmark implements IBenchmarkFactory {

	@Override
	public IBenchmark<?> createBenchmark() {
		RelationsReferenceBenchmark partOfSourceReferenceBenchmark = new RelationsReferenceBenchmark();
		partOfSourceReferenceBenchmark.reference = TestModelPackage.eINSTANCE.getTestObjectWithIndexes_IndexedReferences();
		partOfSourceReferenceBenchmark.factory = TestModelFactory.eINSTANCE;
		return partOfSourceReferenceBenchmark;
	}

//	@Override
//	protected DataStore createTestDataStore() {
//		EmfFragMongoDBActivator.standalone();
//		MongoDBDataStore mongoDBDataStore = new MongoDBDataStore("localhost", parameters.getModelName(), parameters.command == Command.importModel);
//		EmfFragMongoDBActivator.instance.tryToScan = false;
//		return mongoDBDataStore;
//	}
//	
	
	public static void main(String args[]) throws Exception {
		BenchmarkRunner.main(args);
	}
}
