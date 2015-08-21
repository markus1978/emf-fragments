package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IBaseDataStore;
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
public class AbstractFragmentationTests extends AbstractTestModelTests<TestObject, TestModelPackage> {

	protected static final String TEST_OBJECT = "TestObject";

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
}
