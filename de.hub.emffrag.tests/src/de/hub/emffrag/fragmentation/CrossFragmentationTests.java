package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.testmodels.fobject.testmodel.TestObject;

/**
 * In principle one fragmentation should allow it contents to reference elements
 * from a different fragmentation. These test cases make sure this is the case.
 */
@FixMethodOrder(MethodSorters.JVM)
public class CrossFragmentationTests extends AbstractFragmentationTests {

	private Fragmentation additionalFragmentation = null;
	private IDataStore additionalDataStore = null;
	
	protected void reinitializeAdditionalFragmentation() {
		additionalFragmentation.close();
		additionalFragmentation = new Fragmentation(additionalDataStore, 1);
	}

	@Before
	public void initializeAdditionalFragmentation() {
		additionalDataStore = new DataStoreImpl(createBaseDataStore(), URI.createURI("additionalTest"));
		additionalFragmentation = new Fragmentation(additionalDataStore, 1);
	}
	
	@Test
	public void manualResovleTest() {
		TestObject container = createTO("container");
		fragmentation.getRootFragment().getContents().add(container);
		TestObject contents = createTO("contents");
		additionalFragmentation.getRootFragment().getContents().add(contents);
		container.getRegularContents().add(contents);
		
		reinitializeFragmentation(1);
		reinitializeAdditionalFragmentation();
		
		container = (TestObject)fragmentation.getRootFragment().getContents().get(0);
		contents = (TestObject)additionalFragmentation.getRootFragment().getContents().get(0);
		
		EObject proxy = container.getRegularContents().get(0);
		Assert.assertTrue(proxy.eIsProxy());
		FObject resolvedProxy = additionalFragmentation.getFObject(((InternalEObject)proxy).eProxyURI(), true);
		Assert.assertTrue(resolvedProxy != null);
		Assert.assertTrue(!resolvedProxy.eIsProxy());
		Assert.assertSame(resolvedProxy, contents);
	}
	
	@Test
	public void fragmentationSetResovleTest() {
		final Map<URI, IDataStore> dataStores = new HashMap<URI, IDataStore>();
		
		FragmentationSet fs = new FragmentationSet(new IDataStore.IDataStoreFactory() {			
			@Override
			public IDataStore createDataStore(URI uri) {
				IDataStore dataStore = dataStores.get(uri);
				if (dataStore == null) {
					dataStore = new DataStoreImpl(createBaseDataStore(), uri);
					dataStores.put(uri, dataStore);
				}
				return dataStore;
			}
		}, 1);
		
		Fragmentation one = fs.getFragmentation(URI.createURI("one"));
		Fragmentation two = fs.getFragmentation(URI.createURI("two"));
		
		TestObject container = createTO("container");
		TestObject contents = createTO("contents");
		
		one.getContents().add(container);
		container.getRegularContents().add(contents);
		two.getContents().add(contents);
		
		fs.close();
		
		one = fs.getFragmentation(URI.createURI("one"));
		container = (TestObject)one.getContents().get(0);		
		
		Assert.assertNotNull(container);
		Assert.assertSame(1, container.getRegularContents().size());
		TestObject reloadedContents = container.getRegularContents().get(0);
		Assert.assertTrue(!reloadedContents.eIsProxy());
		
		two = fs.getFragmentation(URI.createURI("two"));
		contents = (TestObject)two.getContents().get(0);
		Assert.assertSame(contents, reloadedContents);
		Assert.assertNotNull(reloadedContents.eContainer());
		Assert.assertSame(container, reloadedContents.eContainer());
	}
	
	@Test
	public void fragemtationCrossReferenceResolveTest() {
		final Map<URI, IDataStore> dataStores = new HashMap<URI, IDataStore>();
		
		FragmentationSet fs = new FragmentationSet(new IDataStore.IDataStoreFactory() {			
			@Override
			public IDataStore createDataStore(URI uri) {
				IDataStore dataStore = dataStores.get(uri);
				if (dataStore == null) {
					dataStore = new DataStoreImpl(createBaseDataStore(), uri);
					dataStores.put(uri, dataStore);
				}
				return dataStore;
			}
		}, 1);
		
		Fragmentation one = fs.getFragmentation(URI.createURI("one"));
		Fragmentation two = fs.getFragmentation(URI.createURI("two"));
		
		TestObject container = createTO("container");
		TestObject contents = createTO("contents");
		
		one.getContents().add(container);
		container.getRegularContents().add(contents);
		container.getCrossReferences().add(contents);
		two.getContents().add(contents);
		
		fs.close();
		
		one = fs.getFragmentation(URI.createURI("one"));
		container = (TestObject)one.getContents().get(0);		
		
		Assert.assertNotNull(container);
		Assert.assertSame(1, container.getCrossReferences().size());
		TestObject reloadedContents = container.getCrossReferences().get(0);
		Assert.assertTrue(!reloadedContents.eIsProxy());
		
		two = fs.getFragmentation(URI.createURI("two"));
		contents = (TestObject)two.getContents().get(0);
		Assert.assertSame(contents, reloadedContents);
		Assert.assertNotNull(reloadedContents.eContainer());
		Assert.assertSame(container, reloadedContents.eContainer());
	}
	
//	@Override
//	public void commonAssertions() {
//		
//	}
}
