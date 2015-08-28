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
	public void gcTest() {
		String start = "";
		String end = "";
		for (int i = 0; i < 100; i++) {
			start += i+"f(";
			end += ")";
		}
		runGCTest(start+end);
	}
	
	private void runGCTest(String modelString) {
		initializeFragmentation(0);
		TestObject testModel = createTOFromModelString(modelString);
		
		int objects = 1;
		TreeIterator<EObject> eAllContents = testModel.eAllContents();
		while(eAllContents.hasNext()) {
			eAllContents.next();
			objects++;
		}
		
		fragmentation.getContents().add(testModel);
		
		systemGC();
		fragmentation.gc();
		Assert.assertSame(1, fragmentation.getNumberOfLoadedFragments());
		
		TreeIterator<EObject> allContents = fragmentation.getRootFragment().getAllContents();
		int count = 0;
		while (allContents.hasNext()) {
			FObject next = (FObject)allContents.next();
			systemGC();
			fragmentation.gc();
			
			Assert.assertTrue(next != null && !next.eIsProxy());
			Assert.assertTrue(next.fFragmentation() == fragmentation);
			count++;
		}
		
		Assert.assertSame(objects, count);
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
	
	private void systemGC() {
		for (int i = 0; i < 2; i++) {
			Object obj = new Object();
			WeakReference<Object> ref = new WeakReference<Object>(obj);
			obj = null;
			while (ref.get() != null) {
				System.gc();
			}
			Runtime.getRuntime().runFinalization();
		}
	}
}
