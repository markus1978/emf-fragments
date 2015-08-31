package de.hub.emffrag.fragmentation;

import java.lang.ref.WeakReference;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.testmodels.fobject.testmodel.TestObject;

@FixMethodOrder(MethodSorters.JVM)
public class FragmentationGCTests extends AbstractFragmentationTests {
	
	@Test
	public void basicGCTest() {
		initializeFragmentation(0);
		TestObject testModel = createTOFromModelString("1f(2)");
		fragmentation.getContents().add(testModel);
		Assert.assertSame(2, fragmentation.getNumberOfLoadedFragments());
		
		fragmentation.gc();
		Assert.assertSame(1, fragmentation.getNumberOfLoadedFragments());
	}
	
	@Test
	public void modelCreateGCTest() {
		initializeFragmentation(0);
		TestObject testModel = createTOFromModelString("1f(2f(3r(0,0)),4,5f(0,0))");
		fragmentation.getContents().add(testModel);
		
		testModel = ((TestObject)fragmentation.getContents().get(0)).getFragmentedContents().get(0);
		testModel = null;
		gc();
		fragmentation.gc();
		
		Assert.assertSame(0, fragmentation.getNumberOfLoadedFragments());
	}
	
	private void performTraverseGCTest(String modelSpec, int maxOpenFragments) {
		initializeFragmentation(0);
		TestObject testModel = createTOFromModelString(modelSpec);
		int refCount = 1;
		TreeIterator<EObject> countIt = testModel.eAllContents();
		while(countIt.hasNext()) {
			refCount++;
			countIt.next();
		}
		
		fragmentation.getContents().add(testModel);		
		fragmentation.gc();
		Assert.assertTrue(fragmentation.getIndexOfLastAddedAndStillExistingFragment() > 3);
		
		TreeIterator<EObject> it = fragmentation.getRootFragment().getAllContents();
		
		int actualCount = 0;
		while (it.hasNext()) {
			actualCount++;
			it.next();
			gc();
			fragmentation.gc();
			Assert.assertTrue(fragmentation.getNumberOfLoadedFragments() <= maxOpenFragments);
		}
		
		Assert.assertSame(refCount, actualCount);
	}
	
	@Test
	public void modelTraverseGCTest1() {
		performTraverseGCTest("1f(2,3,4,5,6,7)", 1);		
	}
	
	@Test
	public void modelTraverseGCTest2() {
		performTraverseGCTest("1f(2f(3,4,5,6,7))", 2);
	}
	
	@Test
	public void modelTraverseGCTest3() {
		performTraverseGCTest("1f(2f(0,0,0,0,0),3f(0,0,0,0,0),4f(0,0,0,0,0))", 2);		
	}
	
	@Test
	public void modelTraverseGCTest4() {
		performTraverseGCTest("1f(2f(3f(4f(5),4f(5)),3f(4f(5),4f(5))),2f(3f(4f(5),4f(5)),3f(4f(5),4f(5))))", 4);		
	}
	
	@Test
	public void modelTraverseFromEObjectTest() {
		initializeFragmentation(0);
		TestObject testModel = createTOFromModelString("1f(2f(3f(4f(5),4f(5)),3f(4f(5),4f(5))),2f(3f(4f(5),4f(5)),3f(4f(5),4f(5))))");
		int refCount = 1;
		TreeIterator<EObject> countIt = testModel.eAllContents();
		while(countIt.hasNext()) {
			refCount++;
			countIt.next();
		}
		
		fragmentation.getContents().add(testModel);		
		fragmentation.gc();
		
		TreeIterator<EObject> it = fragmentation.getRootFragment().getContents().get(0).eAllContents();
		
		int actualCount = 1;
		while (it.hasNext()) {
			actualCount++;
			it.next();
			gc();
			fragmentation.gc();
			Assert.assertTrue(fragmentation.getNumberOfLoadedFragments() <= 4);
		}
		
		Assert.assertSame(refCount, actualCount);
	}
	
	@Test
	public void treeIteratorTest() {
		TestObject testModel = createTOFromModelString("1f(2f(3),4)");
		{
			int count = 0;
			TreeIterator<EObject> eAllContents = testModel.eAllContents();
			while (eAllContents.hasNext()) {
				count++;
				Assert.assertNotNull(((TestObject)eAllContents.next()).getName());
			}
			Assert.assertSame(3, count);
		}
		
		fragmentation.getContents().add(testModel);
		
		testModel = (TestObject)fragmentation.getContents().get(0);
		for (int i = 0; i < 100; i++) {
			int count = 0;
			TreeIterator<EObject> eAllContents = testModel.eAllContents();
			while (eAllContents.hasNext()) {
				count++;
				Assert.assertNotNull(((TestObject)eAllContents.next()).getName());
			}
			Assert.assertSame(3, count);
		}
	}
	
	public static void gc() {
		for (int i = 0; i < 2; i++) {
			Object obj = new Object();
			WeakReference<?> ref = new WeakReference<Object>(obj);
			obj = null;
			while (ref.get() != null) {
				System.gc();
			}
			System.runFinalization();
		}
	}
}
