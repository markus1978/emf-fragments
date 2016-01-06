package de.hub.emffrag.tests

import de.hub.emffrag.FAbstractStoreObject
import de.hub.emffrag.tests.model.Container
import de.hub.emffrag.tests.model.Contents
import de.hub.emffrag.tests.model.TestModelPackage
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import static org.junit.Assert.*

class EStoreTests extends AbstractTests {
	
	@Test
	def abstractStoreObjectTests() {
		val one = 1 << 0
		val two = 1 << 1
		val three = 1 << 2
		
		val storeObject = new FAbstractStoreObject() {			
			override protected fieldMask() {
				return one.bitwiseOr(two).bitwiseOr(three)
			}
			
			override protected lastField() {
				return three
			}
			
			override protected firstField() {
				return one
			}	
			
			def Object get(int field) {
				return getField(field)
			}		
			
			def void set(int field, Object value) {
				setField(field, value)
			}
		}
		
		storeObject.set(one, "ONE")
		assertSame("ONE", storeObject.get(one))
		
		storeObject.set(two, "TWO")
		assertSame("ONE", storeObject.get(one))
		assertSame("TWO", storeObject.get(two))
		
		storeObject.set(three, "THREE")
		assertSame("ONE", storeObject.get(one))
		assertSame("TWO", storeObject.get(two))
		assertSame("THREE", storeObject.get(three))	
		
		storeObject.set(three, null)	
		assertSame("ONE", storeObject.get(one))
		assertSame("TWO", storeObject.get(two))
		
		storeObject.set(two, null)	
		assertSame("ONE", storeObject.get(one))
		
		storeObject.set(one, null)
		assertNull(storeObject.get(one))
		assertNull(storeObject.get(two))
		assertNull(storeObject.get(three))
	}
	
	@Test
	def simpleTest() {
		val Contents contents = create("Contents Hello;")
		
		assertEquals("Hello", contents.name)
		assertNull(contents.eContainer)
	}
	
	@Test
	def manyContainmentTest() {
		val contents = contents("contents")
		val container = container("container")
		container.contents.add(contents)
		
		assertNotNull(contents.eContainer)
		assertSame(container, contents.eContainer)
		assertSame(TestModelPackage.eINSTANCE.container_Contents, contents.eContainingFeature)
	}
	
	@Test
	def singleContainmentTest() {
		val contents = contents("contents")
		assertNull(contents.eContainer)
		
		val container = container("container")
		container.content = contents
		
		assertNotNull(contents.eContainer)
		assertSame(container, contents.eContainer)
		assertSame(TestModelPackage.eINSTANCE.container_Content, contents.eContainingFeature)
		
		val newContents = contents("new contents")
		container.content = newContents
		
		assertNull(contents.eContainer)
		assertNull(contents.eContainingFeature)
		assertNotNull(newContents.eContainer)
		assertSame(container, newContents.eContainer)
		assertSame(TestModelPackage.eINSTANCE.container_Content, newContents.eContainingFeature)
	}
	
	@Test
	def singleManyContainmentTest() {
		val contents = contents("contents")
		val container = container("container")
		container.contents += contents
		
		assertNotNull(contents.eContainer)
		assertSame(container, contents.eContainer)
		assertSame(TestModelPackage.eINSTANCE.container_Contents, contents.eContainingFeature)
		assertSame(1, container.contents.size)
		
		val newContents = contents("new contents")
		container.contents.set(0, newContents)
		
		assertNull(contents.eContainer)
		assertNull(contents.eContainingFeature)
		assertNotNull(newContents.eContainer)
		assertSame(container, newContents.eContainer)
		assertSame(TestModelPackage.eINSTANCE.container_Contents, newContents.eContainingFeature)
	}
	
	@Test
	def complexModelTest() {
		val Container root = create('''
			Container c1 {
				contents = Container c2 {
					content = Contents c3;
				}
				content = Contents c4;
			}
		''')		
		
		assertSame(3, root.eAllContents.size)	
		assertSame(3, root.fStoreObject.fAllContents(false).size)
	}
	
}