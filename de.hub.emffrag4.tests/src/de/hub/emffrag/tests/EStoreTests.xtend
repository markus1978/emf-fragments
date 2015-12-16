package de.hub.emffrag.tests

import org.junit.Test
import de.hub.emffrag.tests.model.TestModelFactory
import static org.junit.Assert.*
import de.hub.emffrag.tests.model.TestModelPackage
import de.hub.emffrag.FAbstractStoreObject

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
		val contents = TestModelFactory.eINSTANCE.createContents
		contents.name = "Hello"
		
		assertSame("Hello", contents.name)
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
	
}