package de.hub.emffrag2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
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
	private InMemoryDataStore baseDataStore = null;
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

	protected void createDataStore() {
		baseDataStore = new InMemoryDataStore(false);
		dataStore = new DataStoreImpl(baseDataStore, getTestFragmentationURI());
	}

	@Before
	public void initializeFragmentation() {
		initializeFragmentation(1);
	}

	protected void assertBaseDataStoreSize(int size) {
		if (baseDataStore != null) {
			Assert.assertEquals(size, baseDataStore.getNumberOfEntries());
		}
	}
	
	private TestObject performBasicAutoAddContentsTest(String modelDefinition, int cacheSize, int loadedFragments, int allFragments) {
		initializeFragmentation(cacheSize);
		
		TestObject model = createTOFromModelString(modelDefinition);
		fragmentation.getContents().add(model);
		Assert.assertEquals(loadedFragments, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(allFragments, fragmentation.getNumberOfAllFragments());
		
		return model;
	}
	
	private void performBasicAutoAddContentsReloadTest(String modelDefinition, int cacheSize, int loadedFragments, int allFragments) {		
		TestObject model = performBasicAutoAddContentsTest(modelDefinition, cacheSize, loadedFragments, allFragments);
		
		List<Resource> copy = new ArrayList<Resource>(fragmentation.getResources());
		for (Resource resource: copy) {
			if (resource.isLoaded()) {
				fragmentation.unloadFragment((Fragment)resource);
			}
		}
		
		Assert.assertEquals(1, fragmentation.getContents().size());
		Assert.assertEquals(model, fragmentation.getContents().get(0));
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
		Assert.assertEquals(loadedFragments, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(allFragments, fragmentation.getNumberOfAllFragments());
	}
	
	private void performBasicAutoAddContentsReinitTest(String modelDefinition, int cacheSize, int loadedFragments, int allFragments) {
		performBasicAutoAddContentsTest(modelDefinition, cacheSize, loadedFragments, allFragments);
		
		fragmentation.close();		
		reinitializeFragmentation(cacheSize);
		
		Assert.assertEquals(1, fragmentation.getContents().size());
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), fragmentation.getContents().get(0)));
		Assert.assertEquals(loadedFragments, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(allFragments, fragmentation.getNumberOfAllFragments());
	}
	
	@Test
	public void testBasicAutoAddContentsTest() {
		TestObject testObject = createTO(TEST_OBJECT);
		save(testObject);
		
		EList<EObject> contents = fragmentation.getContents();
		contents.add(testObject);

		Assert.assertEquals(fragmentation.createURI(0l), fragmentation.getRootFragment().getURI());
		Assert.assertEquals(1, contents.size());
		Assert.assertEquals(1, fragmentation.getContents().size());
		Assert.assertEquals(contents, fragmentation.getContents());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}
	
	@Test
	public void testBasicManualUnload() {
		TestObject testObject = createTO(TEST_OBJECT);
		fragmentation.getContents().add(testObject);
		helperManualUnload(fragmentation.getRootFragment());
		
		Assert.assertTrue(testObject.eIsProxy());
		Assert.assertEquals(fragmentation.createURI(0l, 0), ((InternalEObject) testObject).eProxyURI());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(0, fragmentation.getNumberOfLoadedFragments());

		save(testObject);
	}

	@Test
	public void testBasicManualReloadViaGet() {
		testBasicManualUnload();

		TestObject testObject = (TestObject) fragmentation.getContents().get(0);
		Assert.assertEquals(TEST_OBJECT, testObject.getName());
		Assert.assertEquals(getSaved(TEST_OBJECT), testObject);
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}
	
	@Test
	public void testBasicManualReloadViaSet() {
		testBasicManualUnload();
		
		TestObject unloadedObject = getSaved(TEST_OBJECT);
		unloadedObject.setName("AnotherName");
		Assert.assertFalse(unloadedObject.eIsProxy());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}

	@Test
	public void testBasicReinit() throws Exception {
		performBasicAutoAddContentsReinitTest("1r(2r(3),4,5)c(2,5)", 1, 1, 1);
	}
	
	@Test
	public void testBasicReload() throws Exception {
		performBasicAutoAddContentsReloadTest("1r(2r(3),4,5)c(2,5)", 1, 1, 1);
	}
	
	@Test
	public void testBasicRegularEMFContentsReinit() {
		performBasicAutoAddContentsReinitTest("1a(E)", 1, 1, 1);						
	}
	
	private void helperManualUnload(Fragment fragment) {
		fragmentation.unloadFragment(fragment);
	}

	@Test
	public void testBasicAutoAddFragment() {
		initializeFragmentation(2);

		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());

		Assert.assertTrue(container.fIsRoot());
		Assert.assertTrue(contents.fIsRoot());
		Assert.assertEquals("1", container.getName());
		Assert.assertEquals("2", contents.getName());
		Assert.assertEquals(2, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfLoadedFragments());
	}

	@Test
	public void testRecursiveAutoAddFragmentsToRoot() {				
		performBasicAutoAddContentsTest("1f(2f(4f(6),5),3)", 1, 1, 6);
		performBasicAutoAddContentsTest("1f(2f(4f(6),5),3)", 3, 3, 6);
		
		reinitializeFragmentation(2);
		TestObject model = (TestObject)fragmentation.getContents().get(0);
		
		System.out.println("### " + fragmentation.resolveProxies);
		System.out.println(printTO(model)); // EcoreUtil seams to get a different result ... TODO
		System.out.println("### " + fragmentation.resolveProxies);
		System.out.println(printTO(model)); // EcoreUtil seams to get a different result ... TODO
		System.out.println("### " + fragmentation.resolveProxies);
		System.out.println(printTO(model)); // EcoreUtil seams to get a different result ... TODO
		System.out.println("### " + fragmentation.resolveProxies);
	}

	@Test
	public void testRecursiveAutoAddFragmentsToObject() {
		TestObject model = performBasicAutoAddContentsTest("1f(2f(3))", 2, 2, 3);
		queryTO(model, "1f2f3").getFragmentedContents().add(createTOFromModelString("4f(5f(6))"));

		Assert.assertEquals(6, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfLoadedFragments());
		
		reinitializeFragmentation(2);
		model = (TestObject)fragmentation.getContents().get(0);
		
		System.out.println(printTO(model)); // EcoreUtil seams to get a different result ... TODO
		System.out.println(printTO(model)); // EcoreUtil seams to get a different result ... TODO
		System.out.println(printTO(model)); // EcoreUtil seams to get a different result ... TODO
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString("1f(2f(3f(4f(5f(6)))))"), model));		
	}

	@Test
	public void testBasicAutoAddMany() {
		TestObject container = createTO("container");
		fragmentation.getContents().add(container);
		List<TestObject> objects = new ArrayList<TestObject>();
		for (int i = 0; i < 3; i++) {
			objects.add(createTO("" + i));
		}
		container.getFragmentedContents().addAll(objects);
		Assert.assertEquals(4, fragmentation.getNumberOfAllFragments());
	}

	@Test
	public void testBasicAutoRemoveMany() {
		initializeFragmentation(4);
		testBasicAutoAddMany();
		TestObject container = (TestObject) fragmentation.getContents().get(0);
		List<TestObject> toRemove = new ArrayList<TestObject>(container.getFragmentedContents());
		container.getFragmentedContents().removeAll(toRemove);
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}

	@Test
	public void testBasicAutoUnload() {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());

		Assert.assertTrue(container.eIsProxy() && container.fIsUnLoaded());
		Assert.assertFalse(contents.eIsProxy() || contents.fIsUnLoaded());

		Assert.assertEquals(container, fragmentation.getContents().get(0));
		
		Assert.assertFalse(container.getFragmentedContents().isEmpty());
		Assert.assertEquals(contents, container.getFragmentedContents().get(0));
		
		Assert.assertTrue(container.eIsProxy() && container.fIsUnLoaded());
		Assert.assertFalse(contents.eIsProxy() || contents.fIsUnLoaded());

		Assert.assertEquals("2", contents.getName());
		Assert.assertFalse(container.getFragmentedContents().isEmpty());

		Assert.assertEquals(1, fragmentation.getResources().size());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfAllFragments());
	}

	@Test
	public void testBasicAutoReload() throws IOException {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());

		Assert.assertEquals(1, fragmentation.getResources().size());
		Assert.assertTrue(container.eIsProxy());
		Assert.assertEquals(fragmentation.createURI(0l, 0), ((InternalEObject) container).eProxyURI());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfAllFragments());

		Assert.assertEquals("1", container.getName());
		Assert.assertFalse(container.eIsProxy());
		Assert.assertTrue(contents.fIsRoot());
		Assert.assertEquals("2", contents.getName());

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfAllFragments());
	}

	@Test
	public void testBasicAutoDeleteFragmentTest() {
		String modelDescription = "1f(2f(3))";
		TestObject model = createTOFromModelString(modelDescription);
		fragmentation.getContents().add(model);
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(3, fragmentation.getNumberOfAllFragments());
		
		EcoreUtil.delete(queryTO(model, "1f2"));

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(1);
	}

	@Test
	public void testRecursiveAutoDeleteFragmentsFromRoot() {
		String modelDescription = "1f(2f(3))";
		TestObject model = createTOFromModelString(modelDescription);
		fragmentation.getContents().add(model);
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(3, fragmentation.getNumberOfAllFragments());
		
		EcoreUtil.delete(model);

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(0);
	}

	@Test
	public void testRecursiveAutoDeleteFragmentsFromObject() {
		initializeFragmentation(3);
		testRecursiveAutoAddFragmentsToRoot();
		((TestObject) fragmentation.getContents().get(0)).getFragmentedContents().remove(0);

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(1);
	}

	@Test
	public void testBasicAutoFragmentReplace() {
		initializeFragmentation(3);
		testBasicAutoAddFragment();
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
	public void testBasicManualModifyWhileUnloaded() {
		TestObject container = createTO("1");
		TestObject contents = createTO("2");
		EList<TestObject> fragmentedContents = container.getFragmentedContents();
		fragmentation.getContents().add(container);
		helperManualUnload(fragmentation.getRootFragment());
		fragmentedContents.add(contents);
		
		Assert.assertFalse(container.getFragmentedContents().isEmpty());
		// TODO: more assertion, once the former passes
	}
	
	@Test
	public void testBasicAutoFragmentMove() {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject source = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
		TestObject target = createTO("3", container, tmPackage.getTestObject_FragmentedContents());
		TestObject object = createTO("4", source, tmPackage.getTestObject_FragmentedContents());
		
		object.fEnsureLoaded();
		URI uri = object.fFragment().getURI();
		target.getFragmentedContents().add(object);
		
		Assert.assertTrue(source.getFragmentedContents().isEmpty());
		Assert.assertFalse(target.getFragmentedContents().isEmpty());
		Assert.assertEquals(target, object.eContainer());
		Assert.assertEquals(uri, object.fFragment().getURI());
	}

	@Test
	public void testBasicManualAdapterReload() {
		testBasicAdapterNotification();
		TestObject testObject = getSaved(TEST_OBJECT);
		fragmentation.unloadFragment((Fragment) testObject.eResource());
		Assert.assertEquals(1, modifiedTester.size());
		testObject.setName("YetAnotherName");
		Assert.assertEquals(1, testObject.eAdapters().size());
		Assert.assertEquals(2, modifiedTester.size());
	}

	@Test
	public void testBasicAdapterNotification() {
		testBasicAutoAddContentsTest();
		TestObject testObject = getSaved(TEST_OBJECT);
		testObject.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				modifiedTester.add(msg);
			}
		});

		Assert.assertTrue(modifiedTester.isEmpty());
		testObject.setName("AnotherName");
		Assert.assertEquals(1, modifiedTester.size());
	}

	@Test
	public void testErrorAddedToRegularResource() {
		TestObject object = createTO("Object");
		XMIResourceImpl resource = new XMIResourceImpl(URI.createURI("testUri.xmi"));
		try {
			resource.getContents().add(object);
		} catch (IllegalStateException e) {
			return;
		}
		Assert.fail();
	}

	@Test
	public void testErrorAddedToRegularResourceSet() {
		Fragment fragment = new Fragment(URI.createURI("test"), 0l);
		try {
			new ResourceSetImpl().getResources().add(fragment);
		} catch (IllegalStateException e) {
			return;
		}
		Assert.fail();
	}

	@Test
	public void testBasicAutoIsRootTest() {
		testBasicAutoAddContentsTest();
		TestObject parent = getSaved(TEST_OBJECT);
		TestObject nonFragmentingChild = createTO("2", parent, tmPackage.getTestObject_RegularContents());
		TestObject fragmentingChild = createTO("3", parent, tmPackage.getTestObject_FragmentedContents());

		Assert.assertTrue(parent.eIsProxy());
		Assert.assertTrue(nonFragmentingChild.eIsProxy());
		Assert.assertFalse(fragmentingChild.eIsProxy());
		Assert.assertEquals("3", fragmentingChild.getName());

		Assert.assertTrue(parent.fIsRoot());
		Assert.assertFalse(nonFragmentingChild.fIsRoot());
		Assert.assertTrue(fragmentingChild.fIsRoot());
	}

	@Test
	public void testBasicManualClose() {
		testBasicAutoAddFragment();
		TestObject container = (TestObject) fragmentation.getContents().get(0);

		fragmentation.close();
		initializeFragmentation(1, dataStore);

		Assert.assertFalse(fragmentation.getContents().isEmpty());
		Assert.assertNotEquals(container, fragmentation.getContents().get(0));
	}

}
