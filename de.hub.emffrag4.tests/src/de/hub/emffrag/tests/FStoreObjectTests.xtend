package de.hub.emffrag.tests

import org.junit.Test

import static de.hub.emffrag.tests.FStoreObjectTestModelParser.*
import static org.junit.Assert.*

class FStoreObjectTests extends AbstractTests {
	
	@Test
	def emptyRootAndContainmentTest() {
		val object = create("Contents;")
		assertTrue(object.fIsRoot)
		assertNull(object.fContainer)
		assertSame(object, object.fRoot)
		assertSame(0, object.fContents.size)
		assertSame(0, object.fAllContents.size)
	}
	
	@Test
	def singleRootAndContainmentTest() {
		val container = create('''Container { content = Contents c1 {}}''')
		val contents = withName("c1")
		
		assertTrue(container.fIsRoot)
		assertFalse(contents.fIsRoot)
		assertSame(container, contents.fContainer)
		assertSame(container, contents.fRoot)
		assertSame(container_Content, contents.fContainingFeature)
		assertSame(0, contents.fContents.size)
		assertSame(0, contents.fAllContents.size)
		assertSame(1, container.fContents.size)
		assertSame(1, container.fAllContents.size)
	}
	
	@Test
	def multipleRootAndContainmentTest() {			
		val container = create('''
			Container {
				content = Contents;
				contents = Contents;
				contents = Contents;
			}
		''')
		
		assertTrue(container.fIsRoot)
		assertSame(3, container.fContents.size)
		assertSame(3, container.fAllContents.size)
		for(contents: container.fContents) {
			assertFalse(contents.fIsRoot)
			assertSame(container, contents.fContainer)
			assertSame(container, contents.fRoot)
		}
	}
	
	@Test
	def nestedRootAndContainmentTest() {
		val container = create('''
			Container {
				content = Contents;
				contents = Container {
					content = Contents;
					contents = Contents;
					contents = Contents;
				}
				contents = Contents;
			}
		''')
		
		assertTrue(container.fIsRoot)
		assertSame(3, container.fContents.size)
		assertSame(6, container.fAllContents.size)
		for(contents: container.fAllContents) {
			assertFalse(contents.fIsRoot)
			assertSame(container, contents.fRoot)
		}
	}
	
	@Test
	def testModificationTracking() {
		val container = create('''
			Container {
				content = Contents;
				contents = Container {
					content = Contents;
					contents = Contents leaf;
					contents = Contents;
				}
				contents = Contents;
			}
		''')
		val leaf = withName("leaf")		
			
		assertTrue(container.fModified)
		assertTrue(leaf.fModified)
		
		container.fMarkModified(false)
		assertFalse(container.fModified)
		assertFalse(leaf.fModified)
		
					
		leaf.fMarkModified(true)
		assertTrue(container.fModified)
		assertTrue(leaf.fModified)
	}
	
	@Test
	def testSimpleUnload() {
		val container = create('''Container;''')
		val uri = container.fUnload(null)
		
		assertNotNull(uri)
		assertNotNull(uri.segment)
		assertTrue(uri.segment.empty)
		assertTrue(container.fIsProxy)
		assertSame(uri, container.fProxyURI)
	}
	
	@Test
	def testFragments() {
		val f1 = create('''
			Container {
				fragment = Container f2 {
					content = Contents c1;
				}
				fragments = Contents f3;
			}
		''')
		val f2 = withName("f2")
		val f3 = withName("f3")
		
		assertTrue(f1.fIsRoot)
		assertTrue(f2.fIsRoot)
		assertTrue(f3.fIsRoot)
		
		assertFalse(withName("c1").fIsRoot)
		assertSame(f2, withName("c1").fRoot)
	}
}