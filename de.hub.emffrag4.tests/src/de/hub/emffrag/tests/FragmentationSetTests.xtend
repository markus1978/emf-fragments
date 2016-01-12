package de.hub.emffrag.tests

import de.hub.emffrag.FragmentationSet
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.IDataStore
import de.hub.emffrag.internal.FStore
import de.hub.emffrag.tests.model.Container
import de.hub.emffrag.tests.model.Contents
import de.hub.emffrag.tests.model.TestModelPackage
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import static org.junit.Assert.*

class FragmentationSetTests extends AbstractDataStoreTests {
	override cacheSize() {
		return 1
	}
	
	var FragmentationSet fs = null
	val Map<URI, IDataStore> datastores = newHashMap()
	val (URI)=>IDataStore dataStoreFactory = [uri|
		var result = datastores.get(uri)
		if (result == null) {
			result = new DataStoreImpl(createDataStore, uri)
			datastores.put(uri, result)
		}
		return result
	]
	
	@Before
	def void initializeFragmentationSet() {
		datastores.clear
		fs = new FragmentationSet(packages, dataStoreFactory, cacheSize)		
	}
	
	override protected reinit() {
		fs.close
		fs = new FragmentationSet(packages, dataStoreFactory, cacheSize)
		FStore.fINSTANCE.proxyManager.reset		
	}

	@Test
	def singleFragmantationTest() {
		val uri = URI.createURI("test") 
		fs.getFragmentation(uri).root = create(complexFragmentedModelText)
		
		reinit
		assertTrue(fs.fragmentations.empty)
		val fragmentation = fs.getFragmentation(uri)
		assertEquals(1, fs.fragmentations.size)
		assertTrue(fs.fragmentations.contains(fragmentation))
		assertNotNull(fragmentation.root)
		assertTrue(EcoreUtil.equals(create(complexFragmentedModelText), fragmentation.root))
	}	
	
	@Test
	def multipleReferencedFragmentationsTest() {
		{
			val f1 = fs.getFragmentation(URI.createURI("f1"))
			val f2 = fs.getFragmentation(URI.createURI("f2"))
			f1.root = create('''Container f1c1;''')
			val Container f2c1 = create('''Container f2c1''')
			f2.root = f2c1
			f2c1.referenced = f1.root
		
			assertEquals(f1.root, f2c1.referenced)
		}
		
		reinit
		
		val Container f1c1 = fs.getFragmentation(URI.createURI("f1")).root
		val Container f2c1 = fs.getFragmentation(URI.createURI("f2")).root
		assertNotNull(f1c1)
		assertNotNull(f2c1)
		assertNotNull(f2c1.referenced)
		assertSame(f1c1, f2c1.referenced)
	}
	
	@Test
	def multipleReferencedFragmentedFragmentationsTest() {
		{
			val f1 = fs.getFragmentation(URI.createURI("f1"))
			val f2 = fs.getFragmentation(URI.createURI("f2"))
			f1.root = create('''Container f1c1 {
				fragment = Container f1c2 {
					content = Contents f1c3;
				}
			}''')
			val Contents f1c3 = withName("f1c3");
			f2.root = create('''Container f2c1 {
				fragment = Container f2c2 {
					content = Container f2c3;
				} 
			}''')
			val Container f2c3 = withName("f2c3")
			f2c3.referenced = f1c3
			assertEquals(f1c3, f2c3.referenced)
		}
		
		reinit
		
		val Container f1c1 = fs.getFragmentation(URI.createURI("f1")).root
		val Container f2c1 = fs.getFragmentation(URI.createURI("f2")).root
		assertNotNull(((f2c1.fragment as Container).content as Container).referenced)
		assertSame((f1c1.fragment as Container).content, ((f2c1.fragment as Container).content as Container).referenced)
	}
	
	@Test
	def void moveFragmentToDifferentFragmentationTest() {
		val Container original = create('''Container root {
			fragments = Container child1 {

			}
		}''')
		val originalCopy = EcoreUtil.copy(original)
		fs.getFragmentation(URI.createURI("root")).root = original
		
		fs.getFragmentation(URI.createURI("child1")).root = withName("child1")
		
		assertSame(1, original.fragments.size)		
		assertSame(2, fs.fragmentations.size)
		assertNotSame(original.fFragmentation, withName("child1").fFragmentation)
		assertSame(original, withName("child1").eContainer)
		assertSame(TestModelPackage.eINSTANCE.container_Fragments, withName("child1").eContainingFeature)
		assertSame(withName("child1"), fs.getFragmentation(URI.createURI("child1")).root)
		assertTrue(EcoreUtil.equals(originalCopy, original))
	}
}