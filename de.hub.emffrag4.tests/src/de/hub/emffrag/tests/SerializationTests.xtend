package de.hub.emffrag.tests

import de.hub.emffrag.FObject
import de.hub.emffrag.FStore
import de.hub.emffrag.internal.ObjectInputStream
import de.hub.emffrag.internal.ObjectOutputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import org.eclipse.emf.ecore.util.EcoreUtil

import static org.junit.Assert.*
import de.hub.emffrag.tests.model.TestModelPackage
import java.util.List
import org.eclipse.emf.ecore.EPackage
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import org.junit.Before

class SerializationTests extends AbstractTests {
	
	@Before
	def void before() {
		TestModelParser::clearNames		
	}
		
	private def performSaveLoadTest(FObject model) {
		val List<EPackage> thePackages = newArrayList(TestModelPackage.eINSTANCE)
		val byteArrayOutputStream = new ByteArrayOutputStream
		val objectOutputStream = new ObjectOutputStream(byteArrayOutputStream, false) {			
			override protected getPackageID(EPackage pkg) {
				return thePackages.indexOf(pkg)
			}			
		}
		objectOutputStream.writeFragment(model.fStoreObject);
		objectOutputStream.close;
		
		val byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray)
		val objectInputStream = new ObjectInputStream(byteArrayInputStream) {			
			override protected getPackage(int packageID) {
				return thePackages.get(packageID)
			}			
		}
		
		val readCopy = FStore.fINSTANCE.proxify(objectInputStream.readFragment)
		assertFalse(readCopy.fStoreObject.fIsProxy)
		assertFalse(model.fStoreObject.fIsProxy)
		assertTrue(readCopy.fStoreObject.fAllContents.forall[!it.fIsProxy])
		assertTrue(model.fStoreObject.fAllContents.forall[!it.fIsProxy])
		
		assertSame(model.fStoreObject.fAllContents.size, readCopy.fStoreObject.fAllContents.size)
		assertTrue(EcoreUtil.equals(model, readCopy))
	}
	
	@Test
	def simpleTest() {
		performSaveLoadTest(create('''Contents;'''))
	}
	
	@Test
	def attributeTest() {
		performSaveLoadTest(create('''Container f1;'''))
	}
	
	@Test
	def containmentTest() {
		performSaveLoadTest(create('''
			Container {
				content = Contents;
			}
		'''))
	}
	
	@Test
	def referenceTest() {
		performSaveLoadTest(create('''
			Container c1 {
				content = Contents c2 {
					ref referenced = c1
				}
			}
		'''))
	}
	
	@Test
	def complexTest() {
		performSaveLoadTest(create(FStoreObjectTests.complexFragmentText))
	}
}