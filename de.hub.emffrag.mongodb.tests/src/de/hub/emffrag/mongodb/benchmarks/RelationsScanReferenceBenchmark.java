package de.hub.emffrag.mongodb.benchmarks;

import de.hub.emffrag.benchmarks.reference.BenchmarkRunner;
import de.hub.emffrag.benchmarks.reference.IBenchmark;
import de.hub.emffrag.benchmarks.reference.IBenchmarkFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class RelationsScanReferenceBenchmark extends AbstractMongoDBBenchmark implements IBenchmarkFactory {

	@Override
	public IBenchmark<?> createBenchmark() {
		RelationsScanReferenceBenchmark partOfSourceReferenceBenchmark = new RelationsScanReferenceBenchmark();
		partOfSourceReferenceBenchmark.reference = TestModelPackage.eINSTANCE.getTestObjectWithIndexes_IndexedReferences();
		partOfSourceReferenceBenchmark.factory = TestModelFactory.eINSTANCE;
		return partOfSourceReferenceBenchmark;
	}
	
	public static void main(String args[]) throws Exception {
		BenchmarkRunner.main(args);
	}
}
