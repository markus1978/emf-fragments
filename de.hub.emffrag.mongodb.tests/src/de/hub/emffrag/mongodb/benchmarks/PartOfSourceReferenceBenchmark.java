package de.hub.emffrag.mongodb.benchmarks;

import de.hub.emffrag.benchmarks.reference.BenchmarkRunner;
import de.hub.emffrag.benchmarks.reference.IBenchmark;
import de.hub.emffrag.benchmarks.reference.IBenchmarkFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class PartOfSourceReferenceBenchmark extends AbstractMongoDBBenchmark implements IBenchmarkFactory {

	@Override
	public IBenchmark<?> createBenchmark() {
		PartOfSourceReferenceBenchmark partOfSourceReferenceBenchmark = new PartOfSourceReferenceBenchmark();
		partOfSourceReferenceBenchmark.reference = TestModelPackage.eINSTANCE.getTestObject_CrossReferences();
		partOfSourceReferenceBenchmark.factory = TestModelFactory.eINSTANCE;
		return partOfSourceReferenceBenchmark;
	}
	
	public static void main(String args[]) throws Exception {
		BenchmarkRunner.main(args);
	}
}
