package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.statistics.Statistics;
import de.hub.emffrag.testmodels.fobject.testmodel.TestObject;
import de.hub.emffrag.testmodels.fobject.testmodel.fobject.meta.TestModelPackage;

/**
 * Basic fragmentation tests. These tests test the fragmentation based on
 * containment only. They do not test the proper handling of cross reference
 * during fragmentation.
 * 
 * The test names are hierarchical. Everything starting with "test" is a test.
 * "Basic" means in object test, no recursive add/remove/modify of multiple
 * objects. "Recursive" means the opposite of "Basic". "Error" raises expected
 * error conditions. "Auto" means fragmentation triggered by EMF-Fragments.
 * "Manual" means that functionality that is usually triggered automatically, is
 * triggered by the test manually.
 */
@FixMethodOrder(MethodSorters.JVM)
public class BasicFragmentationTests extends AbstractFragmentationTests {

	@Test
	public void testBasicAddContentsTest() {
		TestObject testObject = createTO(TEST_OBJECT);
		save(testObject);
		
		EList<EObject> contents = fragmentation.getContents();
		contents.add(testObject);

		Assert.assertEquals(fragmentation.getURI(0l), fragmentation.getRootFragment().getURI());
		Assert.assertEquals(1, contents.size());
		Assert.assertEquals(1, fragmentation.getContents().size());
		Assert.assertEquals(contents, fragmentation.getContents());
		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}

	@Test
	public void testBasicAddFragment() {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2", container, TestModelPackage.eINSTANCE.getTestObject_FragmentedContents());

		Assert.assertTrue(container.fIsRoot());
		Assert.assertTrue(contents.fIsRoot());
		Assert.assertEquals("1", container.getName());
		Assert.assertEquals("2", contents.getName());
		Assert.assertEquals(2, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		Assert.assertEquals(2, fragmentation.getNumberOfLoadedFragments());
		
		reinitializeFragmentation(1);
		container = (TestObject)fragmentation.getContents().get(0);
		
		Assert.assertTrue(container.fIsRoot());
		Assert.assertSame(1, container.getFragmentedContents().size());
		contents = container.getFragmentedContents().get(0);
		Assert.assertTrue(contents.fIsRoot());
		Assert.assertEquals("1", container.getName());
		Assert.assertEquals("2", contents.getName());
		Assert.assertSame(container, contents.eContainer());
	}
	
	private TestObject performBasicAddContentsTest(String modelDefinition, int allFragments) {
		TestObject model = createTOFromModelString(modelDefinition);
		fragmentation.getContents().add(model);
		Assert.assertEquals(allFragments, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		assertFragmentation();
		
		return model;
	}

	@Test
	public void testRecursiveAddFragmentsToRoot1() {
		testRecursiveAddFragmentsToRoot("1f(2f(4f(6),5),3)");
	}
	
	@Test
	public void testRecursiveAddFragmentsToRoot2() {	
		testRecursiveAddFragmentsToRoot("1f(2f(3f(4f(5f(6)))))");
	}

	private void testRecursiveAddFragmentsToRoot(String testModelString) {				
		performBasicAddContentsTest(testModelString, 6);
		
		reinitializeFragmentation(1);
		TestObject model = (TestObject)fragmentation.getContents().get(0);		
		for (int i = 0; i < 5; i++) {
			Assert.assertTrue("Failed attempt " + (i+1) + ": ", EcoreUtil.equals(createTOFromModelString(testModelString), model));
		}
	}

	@Test
	public void testRecursiveAddFragmentsToObject() {
		TestObject model = performBasicAddContentsTest("1f(2f(3))", 3);
		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		Assert.assertNotNull(model.fFragmentation());
		Assert.assertSame(fragmentation, model.fFragmentation());
		
		TestObject queryResult = queryTO(model, "1f2f3");
		queryResult.getFragmentedContents().add(createTOFromModelString("4f(5f(6))"));
		Assert.assertNotNull(queryResult.fFragmentation());
		
		String testModelString = "1f(2f(3f(4f(5f(6)))))";
		
		Assert.assertEquals(6, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		reinitializeFragmentation(2);
		model = (TestObject)fragmentation.getContents().get(0);
		
		for (int i = 0; i < 5; i++) {
			Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(testModelString), model));
		}	
	}

	@Test
	public void testBasicAddMany() {
		TestObject container = createTO("container");
		fragmentation.getContents().add(container);
		List<TestObject> objects = new ArrayList<TestObject>();
		for (int i = 0; i < 3; i++) {
			objects.add(createTO("" + i));
		}
		container.getFragmentedContents().addAll(objects);
		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
	}

	@Test
	public void testBasicAutoRemove() {
		testBasicAddMany();
		
		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		TestObject container = (TestObject) fragmentation.getContents().get(0);
		List<TestObject> toRemove = new ArrayList<TestObject>(container.getFragmentedContents());
		container.getFragmentedContents().remove(toRemove.get(toRemove.size() - 1));
		
		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
	}
	
	@Test
	public void testBasicAutoRemoveMany() {
		testBasicAddMany();
		
		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		TestObject container = (TestObject) fragmentation.getContents().get(0);
		List<TestObject> toRemove = container.getFragmentedContents();
		while (toRemove.size() != 0) {
			int size = toRemove.size();
			TestObject objectToRemove = toRemove.get(toRemove.size() - 1);
			Assert.assertTrue(container.getFragmentedContents().remove(objectToRemove));
			Assert.assertSame(container.getFragmentedContents(), toRemove);
			Assert.assertEquals(size - 1, toRemove.size());
		}
		
		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}

	@Test
	public void testBasicAutoRemoveManyAtOnce() {
		testBasicAddMany();
		
		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		TestObject container = (TestObject) fragmentation.getContents().get(0);
		List<TestObject> toRemove = new ArrayList<TestObject>(container.getFragmentedContents());
		container.getFragmentedContents().removeAll(toRemove);
		
		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
	}

	@Test
	public void testBasicAutoDeleteFragmentTest() {
		String modelDescription = "1f(2f(3))";
		TestObject model = createTOFromModelString(modelDescription);
		fragmentation.getContents().add(model);
		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		EcoreUtil.delete(queryTO(model, "1f2"));

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(1);
	}

	@Test
	public void testRecursiveAutoDeleteFragmentsFromRoot() {
		String modelDescription = "1f(2f(3))";
		TestObject model = createTOFromModelString(modelDescription);
		fragmentation.getContents().add(model);
		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		
		EcoreUtil.delete(model);

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(1); // TODO shouldn't be the root fragment also be deleted?
	}

	@Test
	public void testRecursiveAutoDeleteFragmentsFromObject() {
		TestObject model = createTOFromModelString("1f(2f(4f(6),5),3)");
		fragmentation.getContents().add(model);
		
		model.getFragmentedContents().remove(0);

		Assert.assertEquals(1, model.getFragmentedContents().size());
		Assert.assertEquals("3", model.getFragmentedContents().get(0).getName());
		assertBaseDataStoreSize(2);
	}

	@Test
	public void testBasicAutoFragmentReplace() {
		testBasicAddFragment();
		TestObject container = (TestObject) fragmentation.getContents().get(0);
		TestObject replaced = container.getFragmentedContents().get(0);
		TestObject replacement = createTO("3");
		container.getFragmentedContents().set(0, replacement);

		Assert.assertEquals(1, container.getFragmentedContents().size());
		Assert.assertEquals(container, replacement.eContainer());
		Assert.assertNull(replaced.eContainer());
		Assert.assertNull(replaced.fFragmentation());
		Assert.assertTrue(replacement.fIsRoot());
		Assert.assertEquals(2, fragmentation.getNumberOfLoadedFragments());
	}

	@Test
	public void testBasicAutoFragmentMove() {
		TestObject container = createTO("container");
		fragmentation.getContents().add(container);
		TestObject source = createTO("source", container, TestModelPackage.eINSTANCE.getTestObject_FragmentedContents());
		TestObject target = createTO("target", container, TestModelPackage.eINSTANCE.getTestObject_FragmentedContents());
		TestObject object = createTO("object", source, TestModelPackage.eINSTANCE.getTestObject_FragmentedContents());
		
		URI uri = object.fFragment().getURI();
		target.getFragmentedContents().add(object);
		
		Assert.assertTrue(source.getFragmentedContents().isEmpty());
		Assert.assertFalse(target.getFragmentedContents().isEmpty());
		Assert.assertEquals(target, object.eContainer());
		Assert.assertEquals(uri, object.fFragment().getURI());
	}
//
//	@Test
//	public void testBasicManualAdapterReload() {
//		testBasicAdapterNotification();
//		TestObject testObject = getSaved(TEST_OBJECT);
//		fragmentation.unloadFragment((Fragment) testObject.eResource());
//		Assert.assertEquals(1, modifiedTester.size());
//		testObject.setName("YetAnotherName");
//		Assert.assertEquals(1, testObject.eAdapters().size());
//		Assert.assertEquals(2, modifiedTester.size());
//	}
//
//	@Test
//	public void testBasicAdapterNotification() {
//		testBasicAutoAddContentsTest();
//		TestObject testObject = getSaved(TEST_OBJECT);
//		testObject.eAdapters().add(new AdapterImpl() {
//			@Override
//			public void notifyChanged(Notification msg) {
//				modifiedTester.add(msg);
//			}
//		});
//
//		Assert.assertTrue(modifiedTester.isEmpty());
//		testObject.setName("AnotherName");
//		Assert.assertEquals(1, modifiedTester.size());
//	}

	@Test
	public void testBasicAutoIsRootTest() {
		testBasicAddContentsTest();
		TestObject parent = getSaved(TEST_OBJECT);
		TestObject nonFragmentingChild = createTO("2", parent, TestModelPackage.eINSTANCE.getTestObject_RegularContents());
		TestObject fragmentingChild = createTO("3", parent, TestModelPackage.eINSTANCE.getTestObject_FragmentedContents());

		Assert.assertEquals("3", fragmentingChild.getName());

		Assert.assertTrue(parent.fIsRoot());
		Assert.assertFalse(nonFragmentingChild.fIsRoot());
		Assert.assertTrue(fragmentingChild.fIsRoot());
	}

	@Test
	public void testBasicManualClose() {
		testBasicAddFragment();
		TestObject container = (TestObject) fragmentation.getContents().get(0);

		fragmentation.close();
		
		initializeFragmentation(1, dataStore);

		Assert.assertFalse(fragmentation.getContents().isEmpty());
		Assert.assertNotEquals(container, fragmentation.getContents().get(0));
	}
	
	@AfterClass
	public static void afterAll() {		
		StringBuilder report = new StringBuilder();
		Statistics.report(report);
		System.out.println(report.toString());
	}

//	// TODO weird cases and left overs
//	// 1. moving a fragment root to a non fragmenting reference's value set
//	// 2. client references to iterators over value sets
}
