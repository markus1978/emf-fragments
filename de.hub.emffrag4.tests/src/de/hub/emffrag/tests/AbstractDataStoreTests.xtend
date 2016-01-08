package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.FStore
import de.hub.emffrag.FragmentationImpl
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.IDataStore
import de.hub.emffrag.datastore.InMemoryDataStore
import de.hub.emffrag.tests.model.TestModelPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EcorePackage
import org.junit.Before
import org.junit.BeforeClass
import de.hub.emffrag.datastore.IBaseDataStore
import java.util.List
import org.eclipse.emf.ecore.EPackage

class AbstractDataStoreTests extends AbstractTests {
	protected var FragmentationImpl fragmentation = null
	protected var IDataStore dataStore = null
	
	protected var List<EPackage> packages = null
	
	@BeforeClass
	static def void beforeClass() {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	@Before
	def void before() {
		packages = packages ?: packages()
		TestModelParser::clearNames
		dataStore = new DataStoreImpl(createDataStore, URI.createURI("test"))
		fragmentation = new FragmentationImpl(packages, dataStore, cacheSize)
		FStore.fINSTANCE.proxyManager.fullReset
	}
	
	protected def void reinit() {
		fragmentation.close();
		fragmentation = new FragmentationImpl(packages, dataStore, cacheSize)
		FStore.fINSTANCE.proxyManager.reset
	}
	
	protected def void reinit(IDataStore dataStore) {
		fragmentation.close();
		fragmentation = new FragmentationImpl(packages, dataStore, cacheSize)
		FStore.fINSTANCE.proxyManager.reset
	}
	
	protected def IBaseDataStore createDataStore() {
		new InMemoryDataStore(false)
	}
	
	protected def cacheSize() {
		return 0
	}
	
	protected def List<EPackage> packages() {
		return newArrayList(TestModelPackage.eINSTANCE)
	}
}