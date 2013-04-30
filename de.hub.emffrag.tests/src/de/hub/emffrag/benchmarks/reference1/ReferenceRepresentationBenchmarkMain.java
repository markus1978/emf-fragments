package de.hub.emffrag.benchmarks.reference1;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.benchmarks.reference1.IBenchmark.IBenchmarkContext;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.AbstractFragmentationTests;
import de.hub.emffrag.fragmentation.BinaryFragmentImpl;
import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;
import de.hub.emffrag.fragmentation.NoReferencesIdSemantics;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class ReferenceRepresentationBenchmarkMain extends AbstractFragmentationTests {

	private final class BenchmarkContext implements IBenchmarkContext {
		private long time;

		@Override
		public void onStart() {
			time = System.currentTimeMillis();
		}

		@Override
		public void onStop() {
			time = System.currentTimeMillis() - time;
		}
	}
	
	@Override
	protected FragmentedModel createFragmentedModel(DataStore dataStore, int cacheSize, EPackage metaModel) {
		return new FragmentedModel(dataStore, cacheSize) {
			@Override
			protected Fragment newFragment(URI uri, FragmentedModel model) {
				return new BinaryFragmentImpl(uri, model);
			}			
		};
	}

	public long numberOfObjects = 10000;
	public long numberOfObjectsToTravers = 100;
	public int fragmentSize = 100;
	public long numberOfChildren = 100;
	
	private BenchmarkContext benchmarkContext = new BenchmarkContext();
	
	@Test
	public void runCrossReferenceEMFFragments() {	
		EmfFragActivator.instance.idSemantics = new NoReferencesIdSemantics(IdBehaviour.defaultModel);
		EmfFragActivator.instance.defaultModel = model;
		
		ReferenceRepresentationBenchmarkConfigurationImpl configuration = 
				new ReferenceRepresentationBenchmarkConfigurationImpl(TestModelFactory.eINSTANCE, TestModelPackage.eINSTANCE.getTestObject_CrossReferences());

		ReferenceRepresentationBenchmark benchmark = new ReferenceRepresentationBenchmark(numberOfObjects, numberOfObjectsToTravers, numberOfChildren, fragmentSize, configuration);
		
		runImport(benchmark);
		reinitializeModel();
		runTask(benchmark);
	}	
	
	protected void runImport(IBenchmark<EObject> benchmark) {
		TestObjectWithIndexes object = TestModelFactory.eINSTANCE.createTestObjectWithIndexes();
		root.getContents().add(object);
		benchmark.populateModel(object, benchmarkContext);
		model.save(null);
		System.out.println(numberOfObjects + " " + numberOfObjectsToTravers + " " + fragmentSize + " " + numberOfChildren + " " + benchmarkContext.time);
	}
	
	protected void runTask(IBenchmark<EObject> benchmark) {		
		benchmark.runTask(root.getContents().get(0), benchmarkContext);
		System.out.println(numberOfObjects + " " + numberOfObjectsToTravers + " " + fragmentSize + " " + numberOfChildren + " " + benchmarkContext.time);
	}

}
