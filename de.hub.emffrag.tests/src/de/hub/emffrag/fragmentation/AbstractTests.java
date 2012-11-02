package de.hub.emffrag.fragmentation;

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.LongKeyType;

/**
 * Abstract base class for all tests. It registers the proper resource
 * implementations for the used file endings and URI protocols.
 * 
 * It also contains all tests that can be applied to all tested (persistence)
 * frameworks (XMI, XMI with reflection, EMF-Fragments).
 */
public class AbstractTests {

	@BeforeClass
	public static void setUp() {		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	}

	/**
	 * This test case loads the smallest Grabats model and then traverses it.
	 */
//	@Test TODO
	public void complexJObjectTest() throws Exception {
		loadAndTraverseGrabatsModel();
	}

	private void loadAndTraverseGrabatsModel() throws IOException {
		long start = System.currentTimeMillis();
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.getResource(URI.createURI("../de.hub.emffrag.testmodels/models/set0.xmi"), true);
		resource.load(null);
		long end = System.currentTimeMillis();

		System.out.println("## " + (end - start));

		start = System.currentTimeMillis();
		TreeIterator<EObject> allContents = resource.getAllContents();
		int count = 0;
		while (allContents.hasNext()) {
			allContents.next();
			count++;
		}
		end = System.currentTimeMillis();
		System.out.println("## " + count + " in " + (end - start));
		Assert.assertTrue(resource.getContents().size() > 0);
	}

	protected DataStore createTestDataStore() {
		return new InMemoryDataStore("hbase", "localhost", "testmodel", false);
	}

	protected DataIndex<Long> createIndex(String prefix, DataStore store) {
		return new DataIndex<Long>(store, prefix, LongKeyType.instance);
	}
}
