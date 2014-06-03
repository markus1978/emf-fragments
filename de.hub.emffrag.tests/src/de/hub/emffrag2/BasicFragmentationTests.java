package de.hub.emffrag2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class BasicFragmentationTests {
	
	public static final URI testFragmentationURI = URI.createURI("test");
	
	protected Fragmentation fragmentation;
	protected IDataStore dataStore;
	protected static TestModelFactory tmFactory;
	protected static TestModelPackage tmPackage;
	protected List<Object> modifiedTester = new ArrayList<Object>();
	
	protected Map<String, TestObject> savedTestObject = new HashMap<String, TestObject>();
	
	protected URI getTestFragmentationURI() {
		return testFragmentationURI;
	}
	
	@Before
	public void initializeEMF() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("frag", new Resource.Factory() {
			@Override
			public Resource createResource(URI uri) {
				return new Fragment(uri);
			}
		});
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
	
	protected void initializeFragmentation(int fragmentsCacheSize) {
		createDataStore();
		fragmentation = new Fragmentation(dataStore, fragmentsCacheSize);
	}
	
	protected void createDataStore() {
		InMemoryDataStore baseDataStore = new InMemoryDataStore(false);		
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
			((List)parent.eGet(reference)).add(testObject);
		}
		
		return testObject;
	}
	
	@Test
	public void addContentsTest() {
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
	public void unloadTest() {
		TestObject testObject = createTO("TestObject");
		fragmentation.getContents().add(testObject);	
		Fragment rootFragment = fragmentation.getRootFragment();
		try {
			rootFragment.save(null);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
		fragmentation.unloadFragment(rootFragment);
		
		Assert.assertTrue(testObject.eIsProxy());;
		Assert.assertEquals(fragmentation.createURI(0l, ""), ((InternalEObject)testObject).eProxyURI());
		Assert.assertFalse(rootFragment.fragmentIsLoaded());
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(0, fragmentation.getNumberOfLoadedFragments());
		
		savedTestObject.put("TestObject", testObject);
	}
	
	@Test
	public void reloadTest() {
		unloadTest();
		
		TestObject testObject = (TestObject)fragmentation.getContents().get(0);
		Assert.assertEquals("TestObject", testObject.getName());
		Assert.assertEquals(savedTestObject.get("TestObject"), testObject);
		Assert.assertEquals(1, fragmentation.getNumberOfAllFragments());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
	}
	
	@Test
	public void basicFragmentationTest() {
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
	public void basicAutoUnloadTest() {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
		
		Assert.assertTrue(container.eIsProxy());
		Assert.assertFalse(contents.eIsProxy());
		Assert.assertEquals("2", contents.getName());
		
		Assert.assertEquals(1, fragmentation.getResources().size());
		Assert.assertEquals(1, fragmentation.getNumberOfLoadedFragments());
		Assert.assertEquals(2, fragmentation.getNumberOfAllFragments());
	}
	
	@Test
	public void basicAutoReloadTest() throws IOException {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		Fragment rootFragment = fragmentation.getRootFragment();		
		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
		
		Assert.assertEquals(1, fragmentation.getResources().size());
		Assert.assertTrue(container.eIsProxy());
		Assert.assertEquals(fragmentation.createURI(0l, ""), ((InternalEObject)container).eProxyURI());
		Assert.assertFalse(rootFragment.fragmentIsLoaded());
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
	public void adapterNotificationTest() {		
		addContentsTest();
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
	public void adapterReloadTest() {
		adapterNotificationTest();
		TestObject testObject = savedTestObject.get("TestObject");
		fragmentation.unloadFragment((Fragment)testObject.eResource());
		Assert.assertEquals(1, modifiedTester.size());
		testObject.setName("YetAnotherName");
		Assert.assertEquals(1, testObject.eAdapters().size());
		Assert.assertEquals(2, modifiedTester.size());		
	}
	
	@Test
	public void isRootTest() {
		addContentsTest();
		TestObject parent = savedTestObject.get("TestObject");		
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
}
