package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class CacheTests extends AbstractFragmentationTests {

	@Override
	protected boolean doInitializeModel() {
		return false;
	}

	private void initializeModel(int cacheSize) {
		model = createFragmentedModel(dataStore, cacheSize, metaModel);
		root = model.root();
	}

	@Test
	public void testUnloading() throws Exception {
		initializeModel(3);

		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);

		FInternalObjectImpl iObject2 = ((FObjectImpl)object2).fInternalObject();
		FInternalObjectImpl iObject3 = ((FObjectImpl)object3).fInternalObject();
		
		Assert.assertTrue(((FObjectImpl)object1).fInternalObject().getFragment().getUserObjectsCache().assertHasReferences());
		Assert.assertNotNull(model.getFragment(iObject2.eResource().getURI()));
		Assert.assertTrue(model.getFragment(iObject2.eResource().getURI()).getUserObjectsCache().assertHasReferences());
		Assert.assertNotNull(model.getFragment(iObject3.eResource().getURI()));
		Assert.assertTrue(model.getFragment(iObject3.eResource().getURI()).getUserObjectsCache().assertHasReferences());

		model.assertNumberOfLoadedFragments(3);
		
		Assertions.root(model).assertId(1).getFragmentedContents().assertSize(1).get(0).getFragmentedContents().assertSize(1).get(0).assertId(3);				
	}

	@Test
	public void testAutomatedUnloadingNestedFragments() throws Exception {
		initializeModel(5);

		model.root().getContents().add(object1);
		for (int i = 0; i < 100; i++) {
			object2 = TestModelFactory.eINSTANCE.createTestObject();
			object2.setName("testValue" + i);
			object1.getFragmentedContents().add(object2);
			object1 = object2;

			model.assertNumberOfLoadedFragments(0, 10);
		}

		model.assertMaxFragmentsIndexSize(102l);
		model.assertStatistics(0, 10, 0, 5, 90, 103, 100, 103);
	}

	@Test
	public void testAutomatedUnloadingSequentialFragments() throws Exception {
		initializeModel(5);

		model.root().getContents().add(object1);
		for (int i = 0; i < 100; i++) {
			object2 = TestModelFactory.eINSTANCE.createTestObject();
			object2.setName("testValue" + i);
			object1.getFragmentedContents().add(object2);

			model.assertNumberOfLoadedFragments(0, 10);
		}

		model.assertMaxFragmentsIndexSize(102l);
		// Before EMF adds a value to a list, it checks if this value is already
		// in that list. If the list is small (<4), it compare each element with
		// the added element and therefore has to resolve proxies if necessary.
		// This causes a few loads.
		model.assertStatistics(0, 10, 0, 6, 90, 110, 100, 103);
	}

	@Test
	public void testReloadOfUnloadedFragment() throws Exception {
		initializeModel(3);

		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);

		Assert.assertTrue(((FObjectImpl)object1).fInternalObject().getFragment().getUserObjectsCache().assertHasReferences());
		FInternalObjectImpl iObject2 = ((FObjectImpl)object2).fInternalObject();
		FInternalObjectImpl iObject3 = ((FObjectImpl)object3).fInternalObject();
		Assert.assertNotNull(model.getFragment(iObject2.eResource().getURI()));
		Assert.assertTrue(model.getFragment(iObject2.eResource().getURI()).getUserObjectsCache().assertHasReferences());
		Assert.assertNotNull(model.getFragment(iObject3.eResource().getURI()));
		Assert.assertTrue(model.getFragment(iObject3.eResource().getURI()).getUserObjectsCache().assertHasReferences());

		model.assertNumberOfLoadedFragments(3);

		Assertions
				.root(model).assertId(1)
				.getFragmentedContents().assertSize(1)
				.get(0).assertId(2).assertDifferentFragmentAsContainer()
				.getFragmentedContents().assertSize(1)
				.get(0).assertId(3).assertDifferentFragmentAsContainer();
		
		model.assertStatistics(0, 3, 1, 5, 1, 5, 4, 4);
	}

	@Test
	public void testLastUsedCacheTest() throws Exception {
		initializeModel(5);

		model.root().getContents().add(object1);
		TestObject[] children = new TestObject[100];
		for (int i = 0; i < 100; i++) {
			children[i] = TestModelFactory.eINSTANCE.createTestObject();
			children[i].setName("testValue" + i);
			object1.getFragmentedContents().add(children[i]);
		}

		// use the first 10 objects, in reverse order, they should be removed
		// last in that reversed order
		for (int i = 9; i >= 0; i--) {
			children[i].getName();
		}

		for (int i = 0; i < 100; i++) {
			children[i] = null;
		}

		model.assertStatistics(5, 10, -1, -1, 50, 200, -1, -1);

		// touch the first 5 objects and see if they were cached
		for (int i = 0; i < 5; i++) {
			Assert.assertEquals("testValue" + i, object1.getFragmentedContents().get(i).getName());
		}
		model.assertStatistics(0, 5, 0, 5, -1, -1, -1, -1);
		// touch the next 5 objects and see if they were not cached
		for (int i = 5; i < 10; i++) {
			Assert.assertEquals("testValue" + i, object1.getFragmentedContents().get(i).getName());
		}
		model.assertStatistics(0, 5, 5, 10, -1, -1, -1, -1);
	}

	@Test
	public void testArbitraryOperations() {
		initializeModel(5);
		TestObject container = addObject(null, false);
		model.root().getContents().add(container);
		TestObject contents = addObject(container, true);
		addObject(container, true);
		addObject(container, true);
		container = contents;
		contents = addObject(container, true);
		addObject(container, true);
		removeObject(contents);		
	}

	@Test
	public void testRegularContainmentAfterReload() throws Exception {
		initializeModel(3);

		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		TestObject contents = Assertions.createTestObject(4);
		object3.getRegularContents().add(contents);

		model.assertNumberOfLoadedFragments(3);

		object3 = Assertions
				.root(model).assertId(1)
				.getFragmentedContents().assertSize(1)
				.get(0).assertId(2).assertDifferentFragmentAsContainer()
				.getFragmentedContents().assertSize(1)				
				.get(0).assertId(3).assertDifferentFragmentAsContainer().value();
		
		contents = Assertions.context(object3)
				.getRegularContents().assertSize(1)
				.get(0).assertId(4).assertSameFragmentAsContainer().value();
		
		model.assertStatistics(0, 3, -1, -1, -1, -1, 4, 4);
	}
}