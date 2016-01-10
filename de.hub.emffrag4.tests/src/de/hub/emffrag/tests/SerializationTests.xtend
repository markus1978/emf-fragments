package de.hub.emffrag.tests

import de.hub.emffrag.FObject
import de.hub.emffrag.internal.FStore
import de.hub.emffrag.internal.FStoreObjectImpl
import de.hub.emffrag.internal.FURI
import de.hub.emffrag.internal.ObjectInputStream
import de.hub.emffrag.internal.ObjectOutputStream
import de.hub.emffrag.tests.model.TestModelPackage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import static org.junit.Assert.*

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
		model.fStoreObject.fSetFragmentID(null, 0)
		objectOutputStream.writeFragment(model.fStoreObject)
		objectOutputStream.close
		
		val byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray)
		val objectInputStream = new ObjectInputStream(byteArrayInputStream, 0) {
			override protected getPackage(int packageID) {
				return thePackages.get(packageID)
			}
			override protected createObject(EClass eClass) {
				return new FStoreObjectImpl(eClass);
			}
			override protected createProxy(FURI uri, EClass eClass) {
				return new FStoreObjectImpl(uri, eClass);
			}
			
		}
		
		val readCopy = FStore.fINSTANCE.proxyManager.getFObject(objectInputStream.readFragment())
		readCopy.eAllContents.forEach[
			(it as FObject).fStoreObject.fRoot			
		]
		assertFalse(readCopy.fStoreObject.fIsProxy)
		assertFalse(model.fStoreObject.fIsProxy)
		assertTrue(readCopy.fStoreObject.fAllContents(false).forall[!it.fIsProxy])
		assertTrue(model.fStoreObject.fAllContents(false).forall[!it.fIsProxy])
		
		assertSame(model.fStoreObject.fAllContents(false).size, readCopy.fStoreObject.fAllContents(false).size)
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