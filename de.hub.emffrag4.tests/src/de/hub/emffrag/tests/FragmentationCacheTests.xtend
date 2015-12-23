package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.FObject
import de.hub.emffrag.FStore
import de.hub.emffrag.FragmentationImpl
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.IDataStore
import de.hub.emffrag.datastore.InMemoryDataStore
import de.hub.emffrag.internal.FStoreObject
import de.hub.emffrag.tests.model.AbstractClass
import de.hub.emffrag.tests.model.Container
import de.hub.emffrag.tests.model.TestModelPackage
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import static org.junit.Assert.*
import de.hub.emffrag.internal.LRUCache

class FragmentationCacheTests extends AbstractTests {
	var FragmentationImpl fragmentation = null
	var IDataStore dataStore = null
	
	@BeforeClass
	static def void beforeClass() {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	@Before
	def void before() {
		TestModelParser::clearNames
		dataStore = new DataStoreImpl(new InMemoryDataStore(false), URI.createURI("test"))
		fragmentation = new FragmentationImpl(newArrayList(TestModelPackage.eINSTANCE), dataStore, 1)
	}
	
	def void reinit() {
		fragmentation.close();
		fragmentation = new FragmentationImpl(newArrayList(TestModelPackage.eINSTANCE), dataStore, 1)
		FStore.fINSTANCE.proxyManager.reset
	}
	
	@Test
	public def void lruCacheEvictionTest() {
		val counter = newArrayList
		val cache = new LRUCache<String,String>(1) {			
			override protected onRemove(String value) {
				counter.add(value)
			}			
		}
		cache.put("Hello", "Hello")
		cache.put("Hello", "Hello")
		cache.put("Hello2", "Hello2")
		cache.put("Hello2", "Hello2")
		
		assertSame(1, counter.size)
		assertTrue(counter.contains("Hello"))
	}
	
	@Test
	public def void lruCacheLRUEvictionTest() {
		val counter = newArrayList
		val cache = new LRUCache<String,String>(3) {			
			override protected onRemove(String value) {
				counter.add(value)
			}			
		}
		cache.put("1", "1")
		cache.put("2", "2")
		cache.put("3", "3")
		cache.get("1")
		cache.get("3")
		cache.put("tomuch", "tomuch")
		
		assertSame(1, counter.size)
		assertTrue(counter.contains("2"))
	}
	
	@Test
	public def void simpleFragmentTest() {
		val Container testModel = create('''
			Container f1 {
				fragment = Container f2 {
					fragment = Container f3 { 
						fragment = Container f4 {
							fragment = Container f5;
						}
					}
				}
			}
		''')
		val copy = EcoreUtil.copy(testModel)
		fragmentation.root = testModel
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
		assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	public def void parrallelFragmentTest() {
		val Container testModel = create('''
			Container f1 {
				fragments = Container f2;
				fragments = Container f3;
			}
		''')
		val copy = EcoreUtil.copy(testModel)
		fragmentation.root = testModel
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
		assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	public def void fragmentRootReferenceTest() {
		val Container testModel = create('''
			Container f1 {
				fragment = Container f2 {
					ref referenced = f1
				}
			}
		''')
		fragmentation.root = testModel
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
		assertSame(testModel, testModel.fragment.referenced)
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	public def void nonFragmentRootReferenceTest() {
		val Container testModel = create('''
			Container f1 {
				content = Contents c1;
				fragment = Container f2 {
					ref referenced = c1
				}
			}
		''')
		fragmentation.root = testModel
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
		assertSame(testModel.content, testModel.fragment.referenced)
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	public def void simpleComplexFragmentationTest() {
		val Container testModel = create(complexFragmentedModelText)			
		val copy = EcoreUtil.copy(testModel)
		fragmentation.root = testModel
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
		assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
}