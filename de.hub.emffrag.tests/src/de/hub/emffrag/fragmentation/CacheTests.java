package de.hub.emffrag.fragmentation;

import java.text.NumberFormat;
import java.util.Random;

import junit.framework.Assert;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import de.hub.emffrag.datastore.LongKeyType;
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

		deleteReference(object2);
		deleteReference(object3);
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
			deleteReference(object1);
			object1 = object2;

			Thread.sleep(10);
			assertStatistic("loaded fragments", model.numberOfLoadedFragments(), 0, 10);
		}

		assertIndexDimenions(dataStore, "f", 0l, 100l, LongKeyType.instance);
		assertStatistics(0, 10, 0, 5, 90, 101, 101, 101);
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

			assertStatistic("loaded fragments", model.numberOfLoadedFragments(), 0, 10);
		}

		assertIndexDimenions(dataStore, "f", 0l, 100l, LongKeyType.instance);
		// Before EMF adds a value to a list, it checks if this value is already
		// in that list. If the list is small (<4), it compare each element with
		// the
		// added element and therefore has to resolve proxies if necessary. This
		// causes a few loads.
		assertStatistics(0, 10, 0, 5, 90, 101, 101, 101);
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
		Assert.assertEquals(3, model.numberOfLoadedFragments());

		deleteReference(object2);
		deleteReference(object3);
		Thread.sleep(10);
		model.purgeCache();
		Assert.assertEquals(2, model.numberOfLoadedFragments());

		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		object3 = assertHasContents(object2, metaModel.getTestObject_FragmentedContents());
		Assert.assertEquals("testValue", object2.getName());
		Assert.assertEquals("testValue", object3.getName());
		assertStatistics(3, 3, 1, 1, 1, 1, 3, 3);
	}

	private void assertStatistic(String name, int value, int min, int max) {
		Assert.assertTrue("Too many " + name + " " + value, value <= max);
		Assert.assertTrue("Too few " + name + " " + value, value >= min);
	}

	private void assertStatistics(int minLoaded, int maxLoaded, int minLoads, int maxLoads, int minUnloads, int maxUnloads,
			int minCreates, int maxCreates) {
		assertStatistic("loaded fragments", model.numberOfLoadedFragments(), minLoaded, maxLoaded);
		assertStatistic("loads", model.getStatistics().getLoads(), minLoads, maxLoads);
		assertStatistic("unloads", model.getStatistics().getUnloads(), minUnloads, maxUnloads);
		assertStatistic("creates", model.getStatistics().getCreates(), minCreates, maxCreates);
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
		model.purgeCache();
		assertStatistic("unloads", model.getStatistics().getUnloads(), 50, 200);
		assertStatistic("loaded fragments", model.numberOfLoadedFragments(), 5, 10);

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

	/**
	 * TODO
	 * 
	 * There is not data store that does not need
	 * memory when it grows.
	 */
//	@Test
	public void testConstantRamUsage() throws Exception {
		initializeModel(100);

		TestObject container = addObject(null, false);
		model.addContent(container);
		long totalMemory = -1;
		long freeMemory = -1;
		int meassure = 0;

		Random random = new Random(0);
		int numberOfFragments = 0;
		int objects = 100000;
		for (int i = 0; i <= objects; i++) {
			boolean fragmenting = random.nextBoolean();
			TestObject object = addObject(container, fragmenting);
			if (random.nextFloat() < 0.3f) {
				container = object;
			}
			if (fragmenting) {
				numberOfFragments++;
			}

			if (i % 1000 == 0) {
				System.gc();
				System.out.println("tm: " + NumberFormat.getIntegerInstance().format(Runtime.getRuntime().totalMemory())
						+ " fm: " + NumberFormat.getIntegerInstance().format(Runtime.getRuntime().freeMemory()));
				meassure++;
				if (meassure > (objects / 1000) / 2) {
					if (totalMemory == -1) {
						totalMemory = Runtime.getRuntime().totalMemory();
					} else {
						// Assert.assertTrue("too much total memory: " +
						// NumberFormat.getIntegerInstance().format(Runtime.getRuntime().totalMemory())
						// +
						// " vs " + totalMemory,
						// Runtime.getRuntime().totalMemory() <
						// totalMemory*1.3f);
					}
					if (freeMemory == -1) {
						totalMemory = Runtime.getRuntime().freeMemory();
					} else {
						// Assert.assertTrue("too much memory used: " +
						// NumberFormat.getIntegerInstance().format(Runtime.getRuntime().freeMemory()),
						// Runtime.getRuntime().freeMemory() > freeMemory*0.8f);
					}
				}
			}
		}

		assertIndexDimenions(dataStore, "f", 0l, (long) numberOfFragments, LongKeyType.instance);
		assertStatistics(0, 5000, 0, objects, (int) ((objects / 2) * 0.8f), (int) ((objects / 2) * 1.2),
				(int) ((objects / 2) * 0.8f), (int) ((objects / 2) * 1.2));
	}
	
	@SuppressWarnings("unused")
	private void printResource(Resource resource) {
		TreeIterator<EObject> allContents = resource.getAllContents();
		while (allContents.hasNext()) {
			FInternalObjectImpl next = (FInternalObjectImpl)allContents.next();
			System.out.print(resource.getURIFragment(next) + ":");
			System.out.print(next.eGet(ReflectiveMetaModelRegistry.instance.getOppositeFeature(metaModel.getTestObject_Name())) + ":");
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
		String uriFragment = object3.eResource().getURIFragment(((FObjectImpl)contents).internalObject());
		Assert.assertEquals("//@regularContents.0", uriFragment);
		
//		printResource(object3.eResource());

		deleteReference(object2);
		deleteReference(object3);
		Thread.sleep(10);
		model.purgeCache();
		Assert.assertEquals(2, model.numberOfLoadedFragments());
		
//		printResource(model.getResourceSet().getResource(URI.createURI("hbase://localhost/testmodel/Zl8AAAAAAAAAAQ"), true));
//		printResource(model.getResourceSet().getResource(URI.createURI("hbase://localhost/testmodel/Zl8AAAAAAAAAAg"), true));

		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		object3 = assertHasContents(object2, metaModel.getTestObject_FragmentedContents());
		contents = assertHasContents(object3, metaModel.getTestObject_RegularContents());
		assertStatistics(3, 3, 1, 1, 1, 1, 3, 3);
		
		uriFragment = object3.eResource().getURIFragment(((FObjectImpl)contents).internalObject());
		Assert.assertEquals("//@regularContents.0", uriFragment);		
	}
}