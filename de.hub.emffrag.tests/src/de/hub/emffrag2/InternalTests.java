package de.hub.emffrag2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

/**
 * EMF-Fragments makes some assumption about how EMF internally works. These
 * tests work on the hidden internal functionality of EMF-fragments that
 * directly depends on these assumptions. This is not a client API-based test.
 */
public class InternalTests {
	
	public static final URI testFragmentationURI = URI.createURI("test");
	
	protected Fragmentation fragmentation;
	protected IDataStore dataStore;
	protected TestModelFactory tmFactory;
	protected TestModelPackage tmPackage;
	
	protected Map<String, TestObject> savedTestObject = new HashMap<String, TestObject>();
	
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
		tmFactory = TestModelFactory.eINSTANCE;
		tmPackage = TestModelPackage.eINSTANCE;
	}
	
	public void initializeFragmentation(int fragmentsCacheSize) {
		InMemoryDataStore baseDataStore = new InMemoryDataStore(false);		
		dataStore = new DataStoreImpl(baseDataStore, testFragmentationURI);
		fragmentation = new Fragmentation(dataStore, fragmentsCacheSize);
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
		rootFragment.unload();
		
		Assert.assertTrue(testObject.eIsProxy());;
		Assert.assertEquals(fragmentation.createURI(0l, ""), ((InternalEObject)testObject).eProxyURI());
		Assert.assertFalse(rootFragment.fragmentIsLoaded());
		
		savedTestObject.put("TestObject", testObject);
	}
	
	@Test
	public void reloadTest() {
		savedTestObject.clear();
		unloadTest();
		
		TestObject testObject = (TestObject)fragmentation.getContents().get(0);
		Assert.assertEquals("TestObject", testObject.getName());
		Assert.assertEquals(savedTestObject.get("TestObject"), testObject);
	}
	
	@Test
	public void basicFragmentationTest() {
		initializeFragmentation(2);
		
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		TestObject contents = createTO("2", container, tmPackage.getTestObject_FragmentedContents());
		
		Assert.assertTrue(container.fIsRoot());
		Assert.assertTrue(contents.fIsRoot());
		Assert.assertEquals(2, fragmentation.getResources().size());		
	}
	
	@Test
	public void basicAutoUnloadTest() {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		createTO("2", container, tmPackage.getTestObject_FragmentedContents());
		
		Assert.assertEquals(1, fragmentation.getResources().size());				
	}
	
	@Test
	public void basicAutoReloadTest() throws IOException {
		TestObject container = createTO("1");
		fragmentation.getContents().add(container);
		Fragment rootFragment = fragmentation.getRootFragment();		
		createTO("2", container, tmPackage.getTestObject_FragmentedContents());
		
		Assert.assertEquals(1, fragmentation.getResources().size());
		Assert.assertTrue(container.eIsProxy());
		Assert.assertEquals(fragmentation.createURI(0l, ""), ((InternalEObject)container).eProxyURI());
		Assert.assertFalse(rootFragment.fragmentIsLoaded());
		
		IDataMap<Long> fragments = dataStore.getMap("f_".getBytes(), LongKeyType.instance);
		Assert.assertEquals((Long)1l, fragments.last());		
		Assert.assertEquals("1", container.getName());
		Assert.assertFalse(container.eIsProxy());
	}
}
