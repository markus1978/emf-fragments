package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.Fragment
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.tests.model.TestModelPackage
import org.eclipse.emf.common.util.URI
import org.junit.Test

import static org.junit.Assert.*
import org.eclipse.emf.ecore.EObject

class SerializationTests extends AbstractTests {
	
	private def performSaveLoadTest(EObject model) {
		
	}
	
	@Test
	def emptyFragmentTest() {
		EmfFragActivator.standalone(TestModelPackage.eINSTANCE)
		val fragment = new Fragment(DataStoreImpl.createDataStore(URI.createURI("mem://test/1")), 0)
		fragment.create
		fragment.save
		fragment.load
	}
	
	@Test
	def simpleTestModelTest() {
		EmfFragActivator.standalone(TestModelPackage.eINSTANCE)
		val fragment = new Fragment(DataStoreImpl.createDataStore(URI.createURI("mem://test/1")), 0)
		
		fragment.addContents(contents("Hello").fStoreObject);
		
		fragment.create
		fragment.save
		
		fragment.load
		
		assertSame(1, fragment.contents.size)
		val object = fragment.contents.get(0)
		assertEquals(TestModelPackage.eINSTANCE.contents, object.eClass)
		assertFalse(object.fIsProxy)
		assertEquals("Hello", (object).fGet(TestModelPackage.eINSTANCE.abstractClass_Name))
	}
	
	@Test
	def referencesTestModelTest() {
		EmfFragActivator.standalone(TestModelPackage.eINSTANCE)
		val fragment = new Fragment(DataStoreImpl.createDataStore(URI.createURI("mem://test/1")), 0)
		
		val container = container("container")
		fragment.addContents(container.fStoreObject);
		fragment.addContents(contents(container, "contents").fStoreObject);
		
		fragment.create
		fragment.save
		
		fragment.load
		
		assertSame(2, fragment.contents.size)
		val object = fragment.contents.get(0)
		assertEquals(TestModelPackage.eINSTANCE.container, object.eClass)
		assertFalse(object.fIsProxy)
		assertEquals("container", object.fGet(TestModelPackage.eINSTANCE.abstractClass_Name))
	}
}