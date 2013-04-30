package de.hub.emffrag.benchmarks.reference1;


import junit.framework.Assert;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import de.hub.emffrag.fragmentation.AbstractFragmentationTests;
import de.hub.emffrag.testmodels.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;

public class ReferenceRepresentationBenchmarkTests extends AbstractFragmentationTests {
	
	private IBenchmark.IBenchmarkContext benchmarkContext = new IBenchmark.IBenchmarkContext() {			
		@Override
		public void onStop() {				
		}
		
		@Override
		public void onStart() {
		}
	};

	@Test
	public void testCreateModel() {
		IBenchmark<EObject> benchmark = new ReferenceRepresentationBenchmark(10000, 1000, 100, 100, 
				new ReferenceRepresentationBenchmarkConfigurationImpl(TestModelFactory.eINSTANCE, TestModelPackage.eINSTANCE.getTestObject_CrossReferences()));
		
		TestObjectWithIndexes model = TestModelFactory.eINSTANCE.createTestObjectWithIndexes();
		benchmark.populateModel(model, benchmarkContext);
		
//		Assert.assertEquals(3, model.eContents().size());
//		Assert.assertEquals(2, model.getCrossReferences().size());
//		Assert.assertEquals(2, model.getCrossReferences().get(0).getCrossReferences().size());
		
		TreeIterator<EObject> eAllContents = model.eAllContents();
		int c = 0;
		while (eAllContents.hasNext()) {
			c++;
			eAllContents.next();
		}
		Assert.assertEquals(10, c);
	}
	
	@Test
	public void testTraverseModel() {
		IBenchmark<EObject> benchmark = new ReferenceRepresentationBenchmark(10, 5, 2, 3, 
				new ReferenceRepresentationBenchmarkConfigurationImpl(TestModelFactory.eINSTANCE, TestModelPackage.eINSTANCE.getTestObject_CrossReferences()));
		
		TestObjectWithIndexes model = TestModelFactory.eINSTANCE.createTestObjectWithIndexes();
		benchmark.populateModel(model, benchmarkContext);
		
		Assert.assertTrue(benchmark.runTask(model, benchmarkContext));
	}
}
