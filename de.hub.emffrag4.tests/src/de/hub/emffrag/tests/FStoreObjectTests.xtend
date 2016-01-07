package de.hub.emffrag.tests

import de.hub.emffrag.internal.FStoreObjectImpl
import org.eclipse.emf.ecore.EReference
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
		assertSame(0, object.fContents(false).size)
		assertSame(0, object.fAllContents(false).size)
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
		assertSame(0, contents.fContents(false).size)
		assertSame(0, contents.fAllContents(false).size)
		assertSame(1, container.fContents(false).size)
		assertSame(1, container.fAllContents(false).size)
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
		assertSame(3, container.fContents(false).size)
		assertSame(3, container.fAllContents(false).size)
		for(contents: container.fContents(false)) {
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
		assertSame(3, container.fContents(false).size)
		assertSame(6, container.fAllContents(false).size)
		for(contents: container.fAllContents(false)) {
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
	
	@Test
	def testContentsRootWhenRootContainerChanges() {
		val originalRoot = create('''
			Container originalRoot {
				content = Contents leave;
				
			}
		''')		
		
		val newRoot = create('''
			Container newRoot;
		''')
		
		newRoot.fSet(newRoot.fClass.getEStructuralFeature("content"), originalRoot)
		originalRoot.fSetContainer(newRoot, newRoot.fClass.getEStructuralFeature("content") as EReference, false)
		assertFalse(originalRoot.fIsRoot)
		assertSame(newRoot, originalRoot.fRoot)
		assertSame(newRoot, withName("leave").fRoot)
	}
	
	@Test
	def testContents() {
		val model = create('''
			Container {
				contents = Contents;
				content = Container {
					content = Contents;
					contents = Contents;
					fragment = Contents;
				}
				fragment = Contents;
				fragments = Contents;
			}
		''')
		
		assertSame(2, model.fContents(true).size)
		assertSame(4, model.fAllContents(true).size)
		assertSame(4, model.fContents(false).size)
		assertSame(7, model.fAllContents(false).size)		
	}
	
	@Test
	def testToString() {
		val model = create(complexFragmentedModelText);
		val str = (model as FStoreObjectImpl).toFullTreeString()
		assertNotNull(str)
	}
}