package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.proxies.Proxy;
import de.hub.emffrag.testmodels.AbstractTestModelTests;
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
public class BasicFragmentationTests extends AbstractTestModelTests<TestObject, TestModelPackage> {

	private static final String TEST_OBJECT = "TestObject";

	public static final URI testFragmentationURI = URI.createURI("test");

	protected Fragmentation fragmentation;
	protected IDataStore dataStore;
	private IBaseDataStore baseDataStore = null;
	protected static  TestModelPackage tmPackage = TestModelPackage.eINSTANCE;
	
	@Override
	protected TestModelPackage testModelPackage() {
		return TestModelPackage.eINSTANCE;
	}

	protected URI getTestFragmentationURI() {
		return testFragmentationURI;
	}

	@BeforeClass
	public static void initializeEMFFragments() {
		EmfFragActivator.standalone(TestModelPackage.eINSTANCE);
		EmfFragActivator.instance.logInStandAlone = true;
		EmfFragActivator.instance.logFragmentPrettyPrints = true;
	}

	protected void initializeFragmentation(int fragmentsCacheSize, IDataStore dataStore) {
		fragmentation = new Fragmentation(dataStore, fragmentsCacheSize);
	}

	protected void initializeFragmentation(int fragmentsCacheSize) {
		createDataStore();
		initializeFragmentation(fragmentsCacheSize, dataStore);
	}
	
	protected void reinitializeFragmentation(int fragmentsCacheSize) {
		fragmentation.close();
		initializeFragmentation(fragmentsCacheSize, dataStore);
	}
	
	protected IBaseDataStore createBaseDataStore() {
		return new InMemoryDataStore(false);
	}

	private final void createDataStore() {
		baseDataStore = createBaseDataStore(); 
		dataStore = new DataStoreImpl(baseDataStore, getTestFragmentationURI());
	}

	@Before
	public void initializeFragmentation() {
		initializeFragmentation(1);
	}
	
	@After
	public void commonAssertions() {
		assertFragmentation();
	}

	protected void assertBaseDataStoreSize(int size) {
		if (baseDataStore != null && baseDataStore instanceof InMemoryDataStore) {
			Assert.assertEquals(size, ((InMemoryDataStore)baseDataStore).getNumberOfEntries());
		}
	}
	
	protected void assertFragmentation() {
		for(EObject content: fragmentation.getContents()) {
			if (content instanceof FObject) {
				Assert.assertSame(fragmentation, ((FObject)content).fFragmentation());
			}
			TreeIterator<EObject> eAllContents = content.eAllContents();
			while (eAllContents.hasNext()) {
				EObject next = eAllContents.next();
				if (next instanceof FObject) {
					Assert.assertSame(fragmentation, ((FObject)next).fFragmentation());
				}
			}
		}
	}
	
	@Test
	public void firstSimpleTest() {
		Assert.assertTrue(fragmentation.getContents() instanceof Proxy);
		
		TestObject testModel = createTOFromModelString("1r(2,3)");
		fragmentation.getContents().add(testModel);
		fragmentation.close();
		assertFragmentation();
		
		reinitializeFragmentation(1);
		Assert.assertTrue(fragmentation.getContents() instanceof Proxy);
		Assert.assertSame(1, fragmentation.getContents().size());
		Assert.assertTrue(EcoreUtil.equals(testModel, fragmentation.getContents().get(0)));
		Assert.assertTrue(fragmentation.getContents().get(0) instanceof Proxy);
		Assert.assertNotSame(testModel, fragmentation.getContents().get(0));
	}
	
//	private TestObject performBasicAutoAddContentsTest(String modelDefinition, int cacheSize, int loadedFragments, int allFragments) {
//		initializeFragmentation(cacheSize);
//		
//		TestObject model = createTOFromModelString(modelDefinition);
//		fragmentation.getContents().add(model);
//		Assert.assertEquals(loadedFragments, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(allFragments, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		
//		assertFragmentation();
//		
//		return model;
//	}
//	
//	private void performBasicAutoAddContentsReloadTest(String modelDefinition, int cacheSize, int loadedFragments, int allFragments) {		
//		TestObject model = performBasicAutoAddContentsTest(modelDefinition, cacheSize, loadedFragments, allFragments);
//		
//		List<Resource> copy = new ArrayList<Resource>(fragmentation.getResources());
//		for (Resource resource: copy) {
//			if (resource.isLoaded()) {
//				fragmentation.unloadFragment((Fragment)resource);
//			}
//		}
//		
//		Assert.assertEquals(1, fragmentation.getContents().size());
//		Assert.assertEquals(model, fragmentation.getContents().get(0));
//		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
//		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
//		Assert.assertEquals(loadedFragments, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(allFragments, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//	}
//	
//	private void performBasicAutoAddContentsReinitTest(String modelDefinition, int cacheSize, int loadedFragments, int allFragments) {
//		performBasicAutoAddContentsTest(modelDefinition, cacheSize, loadedFragments, allFragments);
//		Assert.assertEquals(1, fragmentation.getContents().size());
//		
//		reinitializeFragmentation(cacheSize);
//		
//		Assert.assertEquals(1, fragmentation.getContents().size());
//		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
//		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
//		Assert.assertEquals(loadedFragments, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(allFragments, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//	}
//	
//	@Test
//	public void testBasicAutoAddContentsTest() {
//		TestObject testObject = createTO(TEST_OBJECT);
//		save(testObject);
//		
//		EList<EObject> contents = fragmentation.getContents();
//		contents.add(testObject);
//
//		Assert.assertEquals(fragmentation.createURI(0l), fragmentation.getRootFragment().getURI());
//		Assert.assertEquals(1, contents.size());
//		Assert.assertEquals(1, fragmentation.getContents().size());
//		Assert.assertEquals(contents, fragmentation.getContents());
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//	}
//	
//	@Test
//	public void testBasicManualUnload() {
//		TestObject testObject = createTO(TEST_OBJECT);
//		fragmentation.getContents().add(testObject);
//		helperManualUnload(fragmentation.getRootFragment());
//		
//		Assert.assertTrue(testObject.fIsProxy());
//		Assert.assertEquals(fragmentation.createURI(0l, 0), ((InternalEObject) testObject).eProxyURI());
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(0, fragmentation.getNumberOfLoadedFragments());
//
//		save(testObject);
//	}
//
//	@Test
//	public void testBasicManualReloadViaGetOnCached() {
//		testBasicManualUnload();
//
//		TestObject testObject = getSaved(TEST_OBJECT);
//		Assert.assertEquals(TEST_OBJECT, testObject.getName());
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//	}
//	
//	@Test
//	public void testBasicManualReloadViaGet() {
//		testBasicManualUnload();
//
//		TestObject testObject = (TestObject) fragmentation.getContents().get(0);		
//		Assert.assertEquals(TEST_OBJECT, testObject.getName());
//		Assert.assertEquals(getSaved(TEST_OBJECT), testObject);
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//	}
//	
//	@Test
//	public void testBasicManualReloadViaSet() {
//		testBasicManualUnload();
//		
//		TestObject unloadedObject = getSaved(TEST_OBJECT);
//		unloadedObject.setName("AnotherName");
//		Assert.assertFalse(unloadedObject.fIsProxy());
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//	}
//	
//	@Test
//	public void TestBasicManualReloadViaList() {
//		testBasicManualUnload();
//
//		TestObject testObject = getSaved(TEST_OBJECT);
//		testObject.getFragmentedContents();
//		Assert.assertEquals(TEST_OBJECT, testObject.getName());
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());		
//	}
//
//	@Test
//	public void testBasicReinit() throws Exception {
//		performBasicAutoAddContentsReinitTest("1r(2r(3),4,5)c(2,5)", 1, 1, 1);
//	}
//	
//	@Test
//	public void testBasicReload() throws Exception {
//		performBasicAutoAddContentsReloadTest("1r(2r(3),4,5)c(2,5)", 1, 1, 1);
//	}
//	
//	@Test
//	public void testBasicRegularEMFContentsReinit() {
//		performBasicAutoAddContentsReinitTest("1a(E)", 1, 1, 1);						
//	}
//	
//	private void helperManualUnload(Fragment fragment) {
//		fragmentation.unloadFragment(fragment);
//	}
//
//	private void testBasicAutoAddFragment(int cacheSize) {
//		initializeFragmentation(cacheSize);
//
//		TestObject container = createTO("1");
//		fragmentation.getContents().add(container);
//		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
//
//		Assert.assertTrue(container.fIsRoot());
//		Assert.assertTrue(contents.fIsRoot());
//		Assert.assertEquals("1", container.getName());
//		Assert.assertEquals("2", contents.getName());
//		Assert.assertEquals(2, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(cacheSize > 1 ? 2 : 1, fragmentation.getNumberOfLoadedFragments());
//		
//		save(container);
//		save(contents);
//	}
//	
//	@Test
//	public void testBasicAutoAddFragment() {
//		testBasicAutoAddFragment(1);
//	}
//	
//	@Test
//	public void testBasicUserObjectCacheFunctionality() {
//		initializeFragmentation(1);
//		TestObject container = createTO("1");
//		fragmentation.getContents().add(container);
//		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
//		
//		helperManualUnload(container.fFragment());
//		Assert.assertEquals(container, contents.eContainer());
//		
//		helperManualUnload(contents.fFragment());
//		Assert.assertEquals(contents, container.getFragmentedContents().get(0));
//		
//		reinitializeFragmentation(2);
//		container = (TestObject)fragmentation.getContents().get(0);
//		contents = container.getFragmentedContents().get(0);
//		Assert.assertSame(container, contents.eContainer());
//		
//		helperManualUnload(container.fFragment());
//		Assert.assertSame(container, contents.eContainer());
//		
//		helperManualUnload(contents.fFragment());
//		Assert.assertSame(contents, container.getFragmentedContents().get(0));
//	}
//	
//	@Test
//	public void testSmallCacheReinitialization() {
//		TestObject model = createTOFromModelString("1f(2)");
//		fragmentation.getRootFragment().getContents().add(model);
//		TestObject testObject = queryTO(model, "1f2");
//		Assert.assertEquals("2", testObject.getName());
//		
//		reinitializeFragmentation(2);
//		model = (TestObject)fragmentation.getRootFragment().getContents().get(0);
//		testObject = queryTO(model, "1f2");
//		Assert.assertEquals("2", testObject.getName());
//		
//		reinitializeFragmentation(1);
//		model = (TestObject)fragmentation.getRootFragment().getContents().get(0);
//		System.out.println("PPP SOLL " + System.identityHashCode(model));
//		Assert.assertNotNull(model);
//		Assert.assertEquals("1", model.getName());
//		Assert.assertEquals(1, model.getFragmentedContents().size());
//		Assert.assertNotNull(model.getFragmentedContents().get(0));
//		Assert.assertEquals(1, model.getFragmentedContents().size());
//		Assert.assertEquals("2", model.getFragmentedContents().get(0).getName());
//		testObject = queryTO(model, "1f2");
//		Assert.assertNotNull(testObject);
//		Assert.assertEquals("2", testObject.getName());
//	}
//	
//	@Test
//	public void testUnloadReloadContainingFragment() {
//		initializeFragmentation(5);
//		
//		TestObject container = createTO("1");
//		fragmentation.getContents().add(container);
//		createTO("2", container, tmPackage.getTestObject_FragmentedContents());
//		Assert.assertEquals(1, container.getFragmentedContents().size());
//		
//		helperManualUnload(fragmentation.getRootFragment());
//		
//		Assert.assertEquals("1", container.getName());
//		Assert.assertEquals(1, container.getFragmentedContents().size());
//		Assert.assertEquals("2", container.getFragmentedContents().get(0).getName());
//	}
//	
//	@Test
//	public void testUnloadReloadContainedFragment() {
//		initializeFragmentation(5);
//		
//		TestObject container = createTO("1");
//		fragmentation.getContents().add(container);
//		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
//		Assert.assertEquals(1, container.getFragmentedContents().size());
//		Assert.assertEquals(container, contents.eContainer());
//		
//		helperManualUnload(contents.fFragment());
//		
//		Assert.assertEquals("2", contents.getName());
//		Assert.assertEquals(1, container.getFragmentedContents().size());
//		Assert.assertEquals("2", container.getFragmentedContents().get(0).getName());
//		Assert.assertEquals(container, contents.eContainer());
//	}
//	
//	@Test
//	public void testRecursiveAutoAddFragmentsToRoot1() {
//		testRecursiveAutoAddFragmentsToRoot("1f(2f(4f(6),5),3)");
//	}
//	
//	@Test
//	public void testRecursiveAutoAddFragmentsToRoot2() {	
//		testRecursiveAutoAddFragmentsToRoot("1f(2f(3f(4f(5f(6)))))");
//	}
//
//	private void testRecursiveAutoAddFragmentsToRoot(String testModelString) {				
//		performBasicAutoAddContentsTest(testModelString, 1, 1, 6);
//		
//		reinitializeFragmentation(1);
//		TestObject model = (TestObject)fragmentation.getContents().get(0);		
//		for (int i = 0; i < 5; i++) {
//			Assert.assertTrue("Failed attempt " + (i+1) + ": ", EcoreUtil.equals(createTOFromModelString(testModelString), model));
//		}
//	}
//
//	@Test
//	public void testRecursiveAutoAddFragmentsToObject() {
//		TestObject model = performBasicAutoAddContentsTest("1f(2f(3))", 1, 1, 3);
//		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertNotNull(model.fFragmentation());
//		Assert.assertSame(fragmentation, model.fFragmentation());
//		
//		TestObject queryResult = queryTO(model, "1f2f3");
//		queryResult.getFragmentedContents().add(createTOFromModelString("4f(5f(6))"));
//		Assert.assertNotNull(queryResult.fFragmentation());
//		
//		String testModelString = "1f(2f(3f(4f(5f(6)))))";
//		
//		Assert.assertFalse(fragmentation.fragmentsCacheIsLocked());
//		Assert.assertEquals(6, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		
//		reinitializeFragmentation(2);
//		model = (TestObject)fragmentation.getContents().get(0);
//		
//		for (int i = 0; i < 5; i++) {
//			Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(testModelString), model));
//		}	
//	}
//	
//	@Test
//	public void testInterFragmentCrossReferencesPerRecursiveAddSimple() {
//		TestObject model = createTOFromModelString("1f(2,3c(2))");
//		
//		initializeFragmentation(2); // BUGS
//		fragmentation.getContents().add(model);
//		
//		assertFragmentation();
//		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		fragmentation.close();
//		
//		reinitializeFragmentation(2);
//		model = (TestObject)fragmentation.getContents().get(0);
//		Assert.assertSame(0, queryTO(model, "1f2").getCrossReferences().size());
//		Assert.assertSame(1, queryTO(model, "1f3").getCrossReferences().size());
//				
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f3").getCrossReferences().get(0));		
//	}
//	
//	@Test
//	public void testInterFragmentCrossReferencesPerRecursiveAddComplex() {
//		TestObject model = createTOFromModelString("1f(2,3c(2),4c(2,3),5c(2,3,4))");
//		
//		initializeFragmentation(2); // BUGS
//		fragmentation.getContents().add(model);
//		
//		assertFragmentation();
//		
//		reinitializeFragmentation(2);
//		model = (TestObject)fragmentation.getContents().get(0);
//		Assert.assertSame(0, queryTO(model, "1f2").getCrossReferences().size());
//		Assert.assertSame(1, queryTO(model, "1f3").getCrossReferences().size());
//		Assert.assertSame(2, queryTO(model, "1f4").getCrossReferences().size());
//		Assert.assertSame(3, queryTO(model, "1f5").getCrossReferences().size());
//		
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f3").getCrossReferences().get(0));
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f4").getCrossReferences().get(0));
//		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f4").getCrossReferences().get(1));
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f5").getCrossReferences().get(0));
//		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f5").getCrossReferences().get(1));
//		Assert.assertEquals(queryTO(model, "1f4"), queryTO(model, "1f5").getCrossReferences().get(2));
//	}
//	
//	@Test
//	public void testInterFragmentCrossReferencesPerRegularAdd() {
//		initializeFragmentation(2); // BUGS		
//		TestObject model = createTOFromModelString("1f(2,3,4,5)");
//		fragmentation.getContents().add(model);
//		queryTO(model, "1f3").getCrossReferences().add(queryTO(model, "1f2"));
//		queryTO(model, "1f4").getCrossReferences().add(queryTO(model, "1f2"));
//		queryTO(model, "1f4").getCrossReferences().add(queryTO(model, "1f3"));
//		queryTO(model, "1f5").getCrossReferences().add(queryTO(model, "1f2"));
//		queryTO(model, "1f5").getCrossReferences().add(queryTO(model, "1f3"));
//		queryTO(model, "1f5").getCrossReferences().add(queryTO(model, "1f4"));
//			
//		assertFragmentation();
//		
//		reinitializeFragmentation(2);
//		model = (TestObject)fragmentation.getContents().get(0);
//		Assert.assertSame(0, queryTO(model, "1f2").getCrossReferences().size());
//		Assert.assertSame(1, queryTO(model, "1f3").getCrossReferences().size());
//		Assert.assertSame(2, queryTO(model, "1f4").getCrossReferences().size());
//		Assert.assertSame(3, queryTO(model, "1f5").getCrossReferences().size());
//		
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f3").getCrossReferences().get(0));
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f4").getCrossReferences().get(0));
//		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f4").getCrossReferences().get(1));
//		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f5").getCrossReferences().get(0));
//		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f5").getCrossReferences().get(1));
//		Assert.assertEquals(queryTO(model, "1f4"), queryTO(model, "1f5").getCrossReferences().get(2));
//	}
//	
//	@Test
//	public void testIntraFragmentCrossReferences() {
//		TestObject model = createTOFromModelString("1r(2,3c(2))");
//		
//		initializeFragmentation(2);
//		fragmentation.getContents().add(model);
//		
//		assertFragmentation();
//		
//		reinitializeFragmentation(2);
//		model = (TestObject)fragmentation.getContents().get(0);
//		Assert.assertSame(1, queryTO(model, "1r3").getCrossReferences().size());
//	
//		TestObject referenceValue = queryTO(model, "1r3").getCrossReferences().get(0);
//		Assert.assertFalse(referenceValue.eIsProxy());
//		Assert.assertEquals(queryTO(model, "1r2"), referenceValue);
//	}
//
//	@Test
//	public void testBasicAutoAddMany() {
//		TestObject container = createTO("container");
//		fragmentation.getContents().add(container);
//		List<TestObject> objects = new ArrayList<TestObject>();
//		for (int i = 0; i < 3; i++) {
//			objects.add(createTO("" + i));
//		}
//		container.getFragmentedContents().addAll(objects);
//		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//	}
//	
//	@Test
//	public void testReloadReferenceTest() {
//		testBasicAutoAddFragment(5);
//		
//		TestObject root = getSaved("1");
//		EList<TestObject> reference = root.getFragmentedContents();
//		helperManualUnload(root.fFragment());
//		Assert.assertTrue(root.fIsProxy());
//		root.fEnsureLoaded();
//		Assert.assertTrue(root == fragmentation.getContents().get(0));
//		EList<TestObject> reloadedReference = root.getFragmentedContents();
//		System.out.println("---- " + System.identityHashCode(reference));
//		System.out.println("---- " + System.identityHashCode(reloadedReference));
//		Assert.assertTrue(reference == reloadedReference);
//	}
//	
//	@Test
//	public void testBasicAutoRemove() {
//		initializeFragmentation(1);
//		testBasicAutoAddMany();
//		
//		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		
//		TestObject container = (TestObject) fragmentation.getContents().get(0);
//		List<TestObject> toRemove = new ArrayList<TestObject>(container.getFragmentedContents());
//		container.getFragmentedContents().remove(toRemove.get(toRemove.size() - 1));
//		
//		Assert.assertFalse(fragmentation.isFragmentsCacheLocked());
//		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//	}
//	
//	@Test
//	public void testBasicAutoRemoveMany() {
//		initializeFragmentation(1);
//		testBasicAutoAddMany();
//		
//		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		
//		TestObject container = (TestObject) fragmentation.getContents().get(0);
//		List<TestObject> toRemove = container.getFragmentedContents();
//		while (toRemove.size() != 0) {
//			int size = toRemove.size();
//			TestObject objectToRemove = toRemove.get(toRemove.size() - 1);
//			Assert.assertTrue(container.getFragmentedContents().remove(objectToRemove));
//			Assert.assertSame(container.getFragmentedContents(), toRemove);
//			Assert.assertEquals(size - 1, toRemove.size());
//		}
//		
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//	}
//
//	@Test
//	public void testBasicAutoRemoveManyAtOnce() {
//		initializeFragmentation(1);
//		testBasicAutoAddMany();
//		
//		Assert.assertEquals(4, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		
//		TestObject container = (TestObject) fragmentation.getContents().get(0);
//		List<TestObject> toRemove = new ArrayList<TestObject>(container.getFragmentedContents());
//		container.getFragmentedContents().removeAll(toRemove);
//		
//		Assert.assertEquals(1, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());		
//	}
//
//	@Test
//	public void testBasicAutoUnload() {
//		TestObject container = createTO("1");
//		fragmentation.getContents().add(container);
//		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
//
//		Assert.assertTrue(container.fIsProxy() && container.fIsUnLoaded());
//		Assert.assertFalse(contents.fIsProxy() || contents.fIsUnLoaded());
//
//		Assert.assertEquals(container, fragmentation.getContents().get(0));
//		
//		Assert.assertFalse(container.getFragmentedContents().isEmpty());
//		Assert.assertEquals(contents, container.getFragmentedContents().get(0));
//		
//		Assert.assertTrue(container.fIsProxy() && container.fIsUnLoaded());
//		Assert.assertFalse(contents.fIsProxy() || contents.fIsUnLoaded());
//
//		Assert.assertEquals("2", contents.getName());
//		Assert.assertFalse(container.getFragmentedContents().isEmpty());
//
//		Assert.assertEquals(1, fragmentation.getResources().size());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(2, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//	}
//
//	@Test
//	public void testBasicAutoReload() throws IOException {
//		TestObject container = createTO("1");
//		fragmentation.getContents().add(container);
//		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
//
//		Assert.assertEquals(1, fragmentation.getResources().size());
//		Assert.assertTrue(container.fIsProxy());
//		Assert.assertEquals(fragmentation.createURI(0l, 0), ((InternalEObject) container).eProxyURI());
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(2, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//
//		Assert.assertEquals("1", container.getName());
//		Assert.assertFalse(container.fIsProxy());
//		Assert.assertTrue(contents.fIsRoot());
//		Assert.assertEquals("2", contents.getName());
//
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(2, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//	}
//
//	@Test
//	public void testBasicAutoDeleteFragmentTest() {
//		String modelDescription = "1f(2f(3))";
//		TestObject model = createTOFromModelString(modelDescription);
//		fragmentation.getContents().add(model);
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		
//		EcoreUtil.delete(queryTO(model, "1f2"));
//
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		assertBaseDataStoreSize(1);
//	}
//
//	@Test
//	public void testRecursiveAutoDeleteFragmentsFromRoot() {
//		initializeFragmentation(1);
//		String modelDescription = "1f(2f(3))";
//		TestObject model = createTOFromModelString(modelDescription);
//		fragmentation.getContents().add(model);
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
//		
//		EcoreUtil.delete(model);
//
//		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
//		assertBaseDataStoreSize(1); // TODO shouldn't be the root fragment also be deleted?
//	}
//
//	private void testRecursiveAutoDeleteFragmentsFromObject(String modelDefinition, int cacheSize) {
//		initializeFragmentation(cacheSize);
//		TestObject model = createTOFromModelString(modelDefinition);
//		fragmentation.getContents().add(model);
//		
//		model.getFragmentedContents().remove(0);
//
//		Assert.assertEquals(cacheSize <= 2 ? cacheSize : 2, fragmentation.getNumberOfLoadedFragments());
//		Assert.assertEquals(1, model.getFragmentedContents().size());
//		Assert.assertEquals("3", model.getFragmentedContents().get(0).getName());
//		assertBaseDataStoreSize(2);
//	}
//	
//	@Test
//	public void testRecursiveAutoDeleteFragmentsFromObject1() {
//		testRecursiveAutoDeleteFragmentsFromObject("1f(2f(4f(6),5),3)", 2);
//	}
//	
//	@Test
//	public void testRecursiveAutoDeleteFragmentsFromObject2() {
//		testRecursiveAutoDeleteFragmentsFromObject("1f(2f(4f(6),5),3)", 1);
//	}
//
//	@Test
//	public void testBasicAutoFragmentReplace() {
//		testBasicAutoAddFragment(3);
//		TestObject container = (TestObject) fragmentation.getContents().get(0);
//		TestObject replaced = container.getFragmentedContents().get(0);
//		TestObject replacement = createTO("3");
//		container.getFragmentedContents().set(0, replacement);
//
//		Assert.assertEquals(1, container.getFragmentedContents().size());
//		Assert.assertEquals(container, replacement.eContainer());
//		Assert.assertNull(replaced.eContainer());
//		Assert.assertNull(replaced.fFragmentation());
//		Assert.assertTrue(replacement.fIsRoot());
//		Assert.assertEquals(2, fragmentation.getNumberOfLoadedFragments());
//	}
//	
//	@Test
//	public void testBasicManualModifyWhileUnloaded() {
//		initializeFragmentation(3);
//		TestObject container = createTO("1");
//		TestObject contents = createTO("2");
//		EList<TestObject> fragmentedContents = container.getFragmentedContents();
//		fragmentation.getContents().add(container);
//		helperManualUnload(fragmentation.getRootFragment());
//		fragmentedContents.add(contents);
//		
//		Assert.assertSame(container.getFragmentedContents(), fragmentedContents);
//		Assert.assertFalse(container.getFragmentedContents().isEmpty());
//		Assert.assertSame(contents, container.getFragmentedContents().get(0));
//	}
//	
//	@Test
//	public void testBasicAutoFragmentMove() {
//		initializeFragmentation(1);
//		TestObject container = createTO("container");
//		fragmentation.getContents().add(container);
//		TestObject source = createTO("source", container, tmPackage.getTestObject_FragmentedContents());
//		TestObject target = createTO("target", container, tmPackage.getTestObject_FragmentedContents());
//		TestObject object = createTO("object", source, tmPackage.getTestObject_FragmentedContents());
//		
//		object.fEnsureLoaded();
//		URI uri = object.fFragment().getURI();
//		target.getFragmentedContents().add(object);
//		
//		Assert.assertTrue(source.getFragmentedContents().isEmpty());
//		Assert.assertFalse(target.getFragmentedContents().isEmpty());
//		Assert.assertEquals(target, object.eContainer());
//		Assert.assertEquals(uri, object.fFragment().getURI());
//	}
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
//
//	@Test
//	public void testErrorAddedToRegularResource() {
//		TestObject object = createTO("Object");
//		XMIResourceImpl resource = new XMIResourceImpl(URI.createURI("testUri.xmi"));
//		try {
//			resource.getContents().add(object);
//		} catch (IllegalStateException e) {
//			return;
//		}
//		Assert.fail();
//	}
//
//	@Test
//	public void testErrorAddedToRegularResourceSet() {
//		Fragment fragment = new Fragment(URI.createURI("test"), 0l);
//		try {
//			new ResourceSetImpl().getResources().add(fragment);
//		} catch (IllegalStateException e) {
//			return;
//		}
//		Assert.fail();
//	}
//
//	@Test
//	public void testBasicAutoIsRootTest() {
//		testBasicAutoAddContentsTest();
//		TestObject parent = getSaved(TEST_OBJECT);
//		TestObject nonFragmentingChild = createTO("2", parent, tmPackage.getTestObject_RegularContents());
//		TestObject fragmentingChild = createTO("3", parent, tmPackage.getTestObject_FragmentedContents());
//
//		Assert.assertTrue(parent.fIsProxy());
//		Assert.assertTrue(nonFragmentingChild.fIsProxy());
//		Assert.assertFalse(fragmentingChild.fIsProxy());
//		Assert.assertEquals("3", fragmentingChild.getName());
//
//		Assert.assertTrue(parent.fIsRoot());
//		Assert.assertFalse(nonFragmentingChild.fIsRoot());
//		Assert.assertTrue(fragmentingChild.fIsRoot());
//	}
//
//	@Test
//	public void testBasicManualClose() {
//		testBasicAutoAddFragment();
//		TestObject container = (TestObject) fragmentation.getContents().get(0);
//
//		fragmentation.close();
//		initializeFragmentation(1, dataStore);
//
//		Assert.assertFalse(fragmentation.getContents().isEmpty());
//		Assert.assertNotEquals(container, fragmentation.getContents().get(0));
//	}
//	
//	@AfterClass
//	public static void afterAll() {		
//		StringBuilder report = new StringBuilder();
//		Statistics.report(report);
//		System.out.println(report.toString());
//	}
//
//	// TODO weird cases and left overs
//	// 1. moving a fragment root to a non fragmenting reference's value set
//	// 2. client references to iterators over value sets
}
