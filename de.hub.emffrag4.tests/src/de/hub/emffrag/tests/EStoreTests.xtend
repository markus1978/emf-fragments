package de.hub.emffrag.tests

import org.junit.Test
import de.hub.emffrag.tests.model.TestModelFactory
import static org.junit.Assert.*
import de.hub.emffrag.tests.model.TestModelPackage

class EStoreTests {
	
	private def contents(String name) {
		val obj = TestModelFactory.eINSTANCE.createContents
		obj.name = "Hello"
		return obj;
	}
	
	private def container(String name) {
		val obj = TestModelFactory.eINSTANCE.createContainer
		obj.name = "Hello"
		return obj;
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