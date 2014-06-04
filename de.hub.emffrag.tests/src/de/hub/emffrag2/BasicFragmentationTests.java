package de.hub.emffrag2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

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
public class BasicFragmentationTests {

	public static final URI testFragmentationURI = URI.createURI("test");

	protected Fragmentation fragmentation;
	protected IDataStore dataStore;
	private InMemoryDataStore baseDataStore = null;
	protected static TestModelFactory tmFactory;
	protected static TestModelPackage tmPackage;
	protected List<Object> modifiedTester = new ArrayList<Object>();

	protected Map<String, TestObject> savedTestObject = new HashMap<String, TestObject>();

	protected URI getTestFragmentationURI() {
		return testFragmentationURI;
	}

	@Before
	public void initializeEMF() {

	}

	@Before
	public void initializeTestModel() {
		savedTestObject.clear();
		modifiedTester.clear();
	}

	@BeforeClass
	public static void initializeEMFFragments() {
		tmFactory = TestModelFactory.eINSTANCE;
		tmPackage = TestModelPackage.eINSTANCE;
		EmfFragActivator.standalone(tmPackage);
		EmfFragActivator.instance.logInStandAlone = true;
	}

	protected void initializeFragmentation(int fragmentsCacheSize, IDataStore dataStore) {
		fragmentation = new Fragmentation(dataStore, fragmentsCacheSize);
	}

	protected void initializeFragmentation(int fragmentsCacheSize) {
		createDataStore();
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

	protected TestObject createTO(String name) {
		return createTO(name, null, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected TestObject createTO(String name, TestObject parent, EReference reference) {
		TestObject testObject = tmFactory.createTestObject();
		testObject.setName(name);
		if (parent != null) {
			((List) parent.eGet(reference)).add(testObject);
		}

		return testObject;
	}

	protected void assertBaseDataStoreSize(int size) {
		if (baseDataStore != null) {
			Assert.assertEquals(size, baseDataStore.getNumberOfEntries());
		}
	}

	@Test
	public void testBasicAutoAddContentsTest() {
		TestObject testObject = createTO("TestObject");
		EList<EObject> contents = fragmentation.getContents();
		contents.add(testObject);

		Assert.assertEquals(fragmentation.createURI(0l), fragmentation.getRootFragment().getURI());
		Assert.assertEquals(1, contents.size());
		Assert.assertEquals(1, fragmentation.getContents().size());
		Assert.assertEquals(contents, fragmentation.getContents());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());

		savedTestObject.put("TestObject", testObject);
	}
	
	@Test
	public void testBasicManualRegularEMFContentsUnloadReload() {
		testBasicAutoAddContentsTest();
		TestObject testObject = savedTestObject.get("TestObject");
		testObject.getArbitraryContents().add(EcoreFactory.eINSTANCE.createEObject());
		helperManualUnload(fragmentation.getRootFragment());
		
		Assert.assertFalse(testObject.getArbitraryContents().isEmpty());
		Assert.assertTrue(testObject.getArbitraryContents().get(0) instanceof EObject);
	}
	
	@Test
	public void testBasicAutoRegularEMFContents() {
		testBasicAutoAddContentsTest();
		TestObject container = savedTestObject.get("TestObject");
		TestObject contents = createTO("2");
		EObject emfContent = EcoreFactory.eINSTANCE.createEObject();
		contents.getArbitraryContents().add(emfContent);
		container.getFragmentedContents().add(contents);
		
		Assert.assertEquals("TestObject", container.getName());
		Assert.assertEquals("2", contents.getName());
		Assert.assertEquals(contents, container.getFragmentedContents().get(0));
		Assert.assertFalse(contents.getArbitraryContents().isEmpty());
		Assert.assertNotEquals(emfContent, contents.getArbitraryContents().get(0));
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfAllFragments());
	}
	
	private void helperManualUnload(Fragment fragment) {
		try {
			fragment.save(null);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
		fragmentation.unloadFragment(fragment);
	}

	@Test
	public void testBasicManualUnload() {
		TestObject testObject = createTO("TestObject");
		fragmentation.getContents().add(testObject);
		helperManualUnload(fragmentation.getRootFragment());
		

		Assert.assertTrue(testObject.eIsProxy());
		Assert.assertEquals(fragmentation.createURI(0l, ""), ((InternalEObject) testObject).eProxyURI());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(0, fragmentation.getNumberOfLoadedFragments());

		savedTestObject.put("TestObject", testObject);
	}

	@Test
	public void testBasicManualReloadViaGet() {
		testBasicManualUnload();

		TestObject testObject = (TestObject) fragmentation.getContents().get(0);
		Assert.assertEquals("TestObject", testObject.getName());
		Assert.assertEquals(savedTestObject.get("TestObject"), testObject);
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}
	
	@Test
	public void testBasicManualReloadViaSet() {
		testBasicManualUnload();
		TestObject unloadedObject = savedTestObject.get("TestObject");
		Assert.assertTrue(unloadedObject.eIsProxy());
		unloadedObject.setName("AnotherName");
		Assert.assertFalse(unloadedObject.eIsProxy());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
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
		TestObject container = createTO("1");
		TestObject tmp = container;
		for (int i = 2; i < 4; i++) {
			tmp = createTO("" + i, tmp, tmPackage.getTestObject_FragmentedContents());
		}
		fragmentation.getContents().add(container);
		Iterator<EObject> it = fragmentation.getRootFragment().getAllContents();
		while (it.hasNext()) {
			TestObject next = (TestObject) it.next();
			Assert.assertNotNull(next.getName());
			Assert.assertTrue(next.fIsRoot());
		}
		Assert.assertEquals(3, fragmentation.getNumberOfAllFragments());
	}

	@Test
	public void testRecursiveAutoAddFragmentsToObject() {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2");
		createTO("3", contents, tmPackage.getTestObject_FragmentedContents());
		container.getFragmentedContents().add(contents);

		Iterator<EObject> it = fragmentation.getRootFragment().getAllContents();
		while (it.hasNext()) {
			TestObject next = (TestObject) it.next();
			Assert.assertNotNull(next.getName());
			Assert.assertTrue(next.fIsRoot());
		}
		Assert.assertEquals(3, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
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

		Assert.assertTrue(container.eIsProxy());
		Assert.assertFalse(contents.eIsProxy());

		Assert.assertEquals(container, fragmentation.getContents().get(0));
		Assert.assertEquals(contents, container.getFragmentedContents().get(0));
		Assert.assertTrue(container.eIsProxy());
		Assert.assertFalse(contents.eIsProxy());

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
		Assert.assertEquals(fragmentation.createURI(0l, ""), ((InternalEObject) container).eProxyURI());
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
		testBasicAutoUnload();
		TestObject container = (TestObject) fragmentation.getContents().get(0);
		container.getFragmentedContents().remove(0);

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(1);
	}

	@Test
	public void testRecursiveAutoDeleteFragmentsFromRoot() {
		initializeFragmentation(3);
		testRecursiveAutoAddFragmentsToRoot();
		fragmentation.getContents().remove(0);

		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		assertBaseDataStoreSize(1);
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
	public void testBasicManualAdapterReload() {
		testBasicAdapterNotification();
		TestObject testObject = savedTestObject.get("TestObject");
		fragmentation.unloadFragment((Fragment) testObject.eResource());
		Assert.assertEquals(1, modifiedTester.size());
		testObject.setName("YetAnotherName");
		Assert.assertEquals(1, testObject.eAdapters().size());
		Assert.assertEquals(2, modifiedTester.size());
	}

	@Test
	public void testBasicAdapterNotification() {
		testBasicAutoAddContentsTest();
		TestObject testObject = savedTestObject.get("TestObject");
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
	public void testErrorIsProxy() {
		testBasicAutoAddContentsTest();
		TestObject object = savedTestObject.get("TestObject");
		((FObjectImpl) object).eSetProxyURI(URI.createURI("testUri"), null);
		try {
			object.getName();
		} catch (IllegalStateException e) {
			return;
		}
		Assert.fail();
	}

	@Test
	public void testErrorSetProxyURI() {
		try {
			((InternalEObject) createTO("1")).eSetProxyURI(null);
		} catch (UnsupportedOperationException e) {
			return;
		}
		Assert.fail();
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
		TestObject parent = savedTestObject.get("TestObject");
		TestObject nonFragmentingChild = createTO("2", parent, tmPackage.getTestObject_RegularContents());
		TestObject fragmentingChild = createTO("3", parent, tmPackage.getTestObject_FragmentedContents());

//		Assert.assertTrue(parent.eIsProxy());
//		Assert.assertTrue(nonFragmentingChild.eIsProxy());
//		Assert.assertFalse(fragmentingChild.eIsProxy());
		Assert.assertEquals("3", fragmentingChild.getName());

		Assert.assertTrue(parent.fIsRoot());
		Assert.assertFalse(nonFragmentingChild.fIsRoot());
		Assert.assertTrue(fragmentingChild.fIsRoot());
	}

	@Test
	public void closeTest() {
		testBasicAutoAddFragment();
		TestObject container = (TestObject) fragmentation.getContents().get(0);

		fragmentation.close();
		initializeFragmentation(1, dataStore);

		Assert.assertFalse(fragmentation.getContents().isEmpty());
		Assert.assertNotEquals(container, fragmentation.getContents().get(0));
	}

}
