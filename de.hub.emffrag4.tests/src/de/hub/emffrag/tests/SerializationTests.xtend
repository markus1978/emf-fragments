package de.hub.emffrag.tests

import de.hub.emffrag.tests.model.TestModelPackage
import org.junit.Before
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*

class SerializationTests extends AbstractTests {
	
	@Before
	def void before() {
		TestModelParser::clearNames		
	}
	
	@Test
	def simpleTest() {
		performSaveLoadTest(create('''Contents;'''), newArrayList(TestModelPackage.eINSTANCE))
	}
	
	@Test
	def attributeTest() {
		performSaveLoadTest(create('''Container f1;'''), newArrayList(TestModelPackage.eINSTANCE))
	}
	
	@Test
	def containmentTest() {
		performSaveLoadTest(create('''
			Container {
				content = Contents;
			}
		'''), newArrayList(TestModelPackage.eINSTANCE))
	}
	
	@Test
	def referenceTest() {
		performSaveLoadTest(create('''
			Container c1 {
				content = Contents c2 {
					ref referenced = c1
				}
			}
		'''), newArrayList(TestModelPackage.eINSTANCE))
	}
	
	@Test
	def complexTest() {
		performSaveLoadTest(create(FStoreObjectTests.complexFragmentText), newArrayList(TestModelPackage.eINSTANCE))
	}
}