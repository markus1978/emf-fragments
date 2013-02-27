package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
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
	
	@Before
	public void resetRegistry() {
//		ReflectiveMetaModelRegistry.instance.clear(); // TODO weird stuff happends if we clear the registry everytime.
	}

	@BeforeClass
	public static void setUp() {		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	}

	protected DataStore createTestDataStore() {
		return new InMemoryDataStore("hbase", "localhost", "testmodel", false);
	}

	protected DataIndex<Long> createIndex(String prefix, DataStore store) {
		return new DataIndex<Long>(store, prefix, LongKeyType.instance);
	}
}
