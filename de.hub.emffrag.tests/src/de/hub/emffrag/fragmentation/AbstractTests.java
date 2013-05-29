package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.datastore.ScanningDataStore;
import de.hub.emffrag.datastore.WriteCachingDataStore;

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
		EmfFragActivator.standalone();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	}
	
	@Before
	public void resetErrorsAndWarnings() {
		EmfFragActivator.instance.resetWarningsAndErrors();
	}

	protected IDataStore createTestDataStore() {
		URI uri = URI.createURI("memory://localhost/testmodel");
		InMemoryDataStore baseDataStore = new InMemoryDataStore(false);
		// Full implementation of all features used. InMemoryStore can deal with writes while scanning.
		return new DataStoreImpl(new WriteCachingDataStore(new ScanningDataStore(baseDataStore, baseDataStore.createScanningScanExtension()), baseDataStore), uri);
	}

	protected IDataMap<Long> createIndex(String prefix, IDataStore store) {
		return store.getMap(prefix.getBytes(), LongKeyType.instance);
	}
}
