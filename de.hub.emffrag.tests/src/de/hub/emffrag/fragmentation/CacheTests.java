package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class CacheTests extends AbstractFragmentationTests {

	@Override
	protected boolean doInitializeModel() {
		return false;
	}

	private void initializeModel(int cacheSize) {
		model = createFragmentedModel(dataStore, null, cacheSize, metaModel);
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
		model.assertNumberOfLoadedFragments(3);

		deleteReference(object2);
		deleteReference(object3);
		Thread.sleep(100);

		model.assertNumberOfLoadedFragments(2);
	}

	@Test
	public void testAutomatedUnloadingNestedFragments() throws Exception {
		initializeModel(1);

		model.addContent(object1);
		for (int i = 0; i < 100; i++) {
			object2 = TestModelFactory.eINSTANCE.createTestObject();
			object2.setName("testValue" + i);
			object1.getFragmentedContents().add(object2);
			deleteReference(object1);
			object1 = object2;

			Thread.sleep(10);
			model.assertNumberOfLoadedFragments(0, 10);
		}

		model.assertFragmentsIndex(0l, 100l);
		model.assertStatistics(0, 10, 0, 5, 90, 101, 101, 101);
	}

	private void deleteReference(TestObject object) {
		UserObjectsCache.deleteReference((FObjectImpl) object);
	}

	@Test
	public void testAutomatedUnloadingSequentialFragments() throws Exception {
		initializeModel(1);

		model.addContent(object1);
		for (int i = 0; i < 100; i++) {
			object2 = TestModelFactory.eINSTANCE.createTestObject();
			object2.setName("testValue" + i);
			object1.getFragmentedContents().add(object2);

			deleteReference(object2);
			Thread.sleep(10);

			model.assertNumberOfLoadedFragments(0, 10);
		}

		model.assertFragmentsIndex(0l, 100l);
		// Before EMF adds a value to a list, it checks if this value is already
		// in that list. If the list is small (<4), it compare each element with
		// the added element and therefore has to resolve proxies if necessary.
		// This causes a few loads.
		model.assertStatistics(0, 10, 0, 5, 90, 110, 101, 101);
	}

	@Test
	public void testReloadOfUnloadedFragment() throws Exception {
		initializeModel(1);

		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);

		Assert.assertTrue(model.getFragment(rootFragmentURI).getUserObjectsCache().hasReferences());
		Assert.assertNotNull(model.getFragment(object2.eResource().getURI()));
		Assert.assertTrue(model.getFragment(object2.eResource().getURI()).getUserObjectsCache().hasReferences());
		Assert.assertNotNull(model.getFragment(object3.eResource().getURI()));
		Assert.assertTrue(model.getFragment(object3.eResource().getURI()).getUserObjectsCache().hasReferences());
		model.assertNumberOfLoadedFragments(3);

		deleteReference(object2);
		deleteReference(object3);
		Thread.sleep(10);
		model.assertNumberOfLoadedFragments(2);

		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		object3 = assertHasContents(object2, metaModel.getTestObject_FragmentedContents());
		Assert.assertEquals("testValue", object2.getName());
		Assert.assertEquals("testValue", object3.getName());
		model.assertStatistics(3, 3, 1, 1, 1, 1, 3, 3);
	}

	@Test
	public void testLastUsedCacheTest() throws Exception {
		initializeModel(5);

		model.addContent(object1);
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

		// clear all references and call gc
		for (int i = 0; i < 100; i++) {
			deleteReference(children[i]);
			children[i] = null;
		}

		Thread.sleep(100);
		model.assertStatistics(5, 10, -1, -1, 50, 200, -1, -1);

		// touch the first 5 objects and see if they were cached
		for (int i = 0; i < 5; i++) {
			Assert.assertEquals("testValue" + i, object1.getFragmentedContents().get(i).getName());
		}
		Assert.assertEquals(0, model.getStatistics().getLoads());
		// touch the next 5 objects and see if they were not cached
		for (int i = 5; i < 10; i++) {
			Assert.assertEquals("testValue" + i, object1.getFragmentedContents().get(i).getName());
		}
		Assert.assertEquals(5, model.getStatistics().getLoads());
	}

	@Test
	public void testArbitraryOperations() {
		initializeModel(5);
		TestObject container = addObject(null, false);
		model.addContent(container);
		TestObject contents = addObject(container, true);
		deleteReference(addObject(container, true));
		deleteReference(addObject(container, true));
		container = contents;
		contents = addObject(container, true);
		deleteReference(addObject(container, true));
		removeObject(contents);
		deleteReference(contents);
	}

	@SuppressWarnings("unused")
	private void printResource(Resource resource) {
		TreeIterator<EObject> allContents = resource.getAllContents();
		while (allContents.hasNext()) {
			FInternalObjectImpl next = (FInternalObjectImpl) allContents.next();
			System.out.print(resource.getURIFragment(next) + ":");
			System.out.print(next.eGet(ReflectiveMetaModelRegistry.instance.getOppositeFeature(metaModel.getTestObject_Name()))
					+ ":");
			System.out.print(next.eIsProxy() + ":");
			System.out.print(System.identityHashCode(next) + ":");
			System.out.println("");
		}
	}

	@Test
	public void testRegularContainmentAfterReload() throws Exception {
		initializeModel(1);

		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		TestObject contents = TestModelFactory.eINSTANCE.createTestObject();
		contents.setName("testValue");
		object3.getRegularContents().add(contents);
		String uriFragment = object3.eResource().getURIFragment(((FObjectImpl) contents).internalObject());
		Assert.assertEquals("//@regularContents.0", uriFragment);

		deleteReference(object2);
		deleteReference(object3);
		Thread.sleep(10);
		model.assertNumberOfLoadedFragments(2);

		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		object3 = assertHasContents(object2, metaModel.getTestObject_FragmentedContents());
		contents = assertHasContents(object3, metaModel.getTestObject_RegularContents());
		model.assertStatistics(3, 3, 1, 1, 1, 1, 3, 3);

		uriFragment = object3.eResource().getURIFragment(((FObjectImpl) contents).internalObject());
		Assert.assertEquals("//@regularContents.0", uriFragment);
	}
}