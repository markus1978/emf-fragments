package de.hub.emffrag.fragmentation;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class CacheTests extends AbstractFragmentationTests {
	
	@Override
	protected boolean doInitializeModel() {
		return false;
	}
	
	private void initializeModel(int cacheSize) {
		model = new FragmentedModel(dataStore, null, cacheSize, metaModel);
		rootFragmentURI = model.getRootFragmentURI();
	}

	@Test
	public void testUnloading() throws Exception {
		initializeModel(1);
		
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		
		Assert.assertTrue(model.getFragment(rootFragmentURI).getUserObjectsCache().hasReferences());
		Assert.assertNotNull(model.getFragment(object2.eResource().getURI()));
		Assert.assertTrue(model.getFragment(object2.eResource().getURI()).getUserObjectsCache().hasReferences());
		Assert.assertNotNull(model.getFragment(object3.eResource().getURI()));
		Assert.assertTrue(model.getFragment(object3.eResource().getURI()).getUserObjectsCache().hasReferences());
		Assert.assertEquals(3, model.numberOfLoadedFragments());
		
		object2 = null;
		object3 = null;
		System.gc();
		Thread.sleep(100);
		model.purgeCache();
		Assert.assertEquals(2, model.numberOfLoadedFragments());
	}
	
	@Test
	public void testAutomatedUnloadingNestedFragments() throws Exception {
		initializeModel(1);
		
		model.addContent(object1);
		for (int i = 0; i < 100; i++) {
			object2 = TestModelFactory.eINSTANCE.createTestObject();
			object2.setName("testValue" + i);
			object1.getFragmentedContents().add(object2);
			object1 = object2;
		
			System.gc();
			Thread.sleep(10);
			
			Assert.assertTrue("cache is too big", model.numberOfLoadedFragments() <= 10);
		}		
	}
	
	@Test
	public void testAutomatedUnloadingSequentialFragments() throws Exception {
		initializeModel(1);
		
		model.addContent(object1);
		for (int i = 0; i < 100; i++) {
			object2 = TestModelFactory.eINSTANCE.createTestObject();
			object2.setName("testValue" + i);
			object1.getFragmentedContents().add(object2);
		
			System.gc();
			Thread.sleep(10);
			
			Assert.assertTrue("cache is too big", model.numberOfLoadedFragments() <= 10);
		}		
	}
	
	@Test
	public void testReloadOfUnloadedFragment() throws Exception {
		// TODO
	}

// 	TODO	
//	@Test
//	public void testCacheLimits() throws Exception {
//		initializeModel(100);
//		
//		TestObject container = addObject(null, false);
//		model.addContent(container);
//		
//		Random random = new Random(0);
//		
//		for (int i = 0; i < 100000; i++) {
//			if (container.eContainer() == null || random.nextBoolean()) {
//				boolean fragmenting = random.nextBoolean();
//				container = addObject(container, fragmenting);
//			} else {
////				TestObject newContainer = (TestObject)container.eContainer();
////				removeObject(container);
////				container = newContainer;
//			}
//			
//			if (i % 1000 == 0) {
//				System.gc();
//				Thread.sleep(100);
//				model.purgeCache();
//				Assert.assertTrue("at iteration " + i + " cache got too big (" + model.numberOfLoadedFragments() + ")", model.numberOfLoadedFragments() <= 130);
//			}
//		}
//	}
}
