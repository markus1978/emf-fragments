package de.hub.emffrag.tests

import de.hub.emffrag.internal.LRUCache
import de.hub.emffrag.tests.model.Container
import java.lang.ref.WeakReference
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import static org.junit.Assert.*

class FragmentationCacheTests extends AbstractDataStoreTests {
	
	override cacheSize() {
		return 1
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
	public def void complexFragmentationTest() {
		val Container testModel = create(complexFragmentedModelText)			
		val copy = EcoreUtil.copy(testModel)
		fragmentation.root = testModel
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
		assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	public def void gcTest() {
		val Container testModel = create(complexFragmentedModelText)			
		val copy = EcoreUtil.copy(testModel)
		fragmentation.root = testModel
		
		gc
		assertSame(testModel, fragmentation.root)
		assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		assertSame(1, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	public def void memoryLeakTest() {
		val ()=>Container createPart = [create('''
			Container o1 {
				contents = Container o11 {
					ref referenceds = o1
					content = Contents o111;
					contents = Contents o112;
					
				}
				contents = Contents o12;
				contents = Container o13 {
					ref referenced = o12
					content = Container leave {
						contents = Contents leave1;
					}
				}
			}
		''')]
		val model = createPart.apply
		for (i:0..10) {			
			(withName("leave") as Container).fragment = createPart.apply;		
		}
		
		val copy = EcoreUtil.copy(model)
		fragmentation.root = model
		
		var long lastMemory = 0
		for(i:0..5000) {
			if (i%1000 == 0) {
				gc
				lastMemory = Runtime.runtime.freeMemory
			}
			assertTrue(EcoreUtil.equals(copy, fragmentation.root))		
		}
		assertTrue(Math.abs(lastMemory - Runtime.runtime.freeMemory) < lastMemory*0.01)
	}
	
	def static void gc() {
		for (i:0..2) {
			var obj = new Object();
			val ref = new WeakReference<Object>(obj);
			obj = null;
			while (ref.get() != null) {
				System.gc();
			}
			System.runFinalization();
		}
	}
}