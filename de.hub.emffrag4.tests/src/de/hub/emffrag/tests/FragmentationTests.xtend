package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.FObject
import de.hub.emffrag.FStore
import de.hub.emffrag.FragmentationImpl
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.IDataStore
import de.hub.emffrag.datastore.InMemoryDataStore
import de.hub.emffrag.internal.FStoreObject
import de.hub.emffrag.tests.model.AbstractClass
import de.hub.emffrag.tests.model.Container
import de.hub.emffrag.tests.model.TestModelPackage
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import static org.junit.Assert.*

class FragmentationTests extends AbstractTests {
	var FragmentationImpl fragmentation = null
	var IDataStore dataStore = null
	
	@BeforeClass
	static def void beforeClass() {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	@Before
	def void before() {
		TestModelParser::clearNames
		dataStore = new DataStoreImpl(new InMemoryDataStore(false), URI.createURI("test"))
		fragmentation = new FragmentationImpl(newArrayList(TestModelPackage.eINSTANCE), dataStore, 0)
	}
	
	def void reinit() {
		fragmentation.close();
		fragmentation = new FragmentationImpl(newArrayList(TestModelPackage.eINSTANCE), dataStore, 0)
		FStore.fINSTANCE.proxyManager.reset
	}
	
	@Test
	public def void rootTest() {		
		val Container testModel = create('''Container f1;''')
		
		fragmentation.root = testModel
		
		assertSame(testModel, fragmentation.root)
		fragmentation.FStoreFragmentation.unloadFragment(testModel.fStoreObject)
		assertSame(testModel, fragmentation.root)
	}
	
	@Test
	public def void uniqueProxyTest() {
		val Container testModel = create(FStoreObjectTests.complexFragmentedModelText)
		val copy = EcoreUtil.copy(testModel)
		
		fragmentation.root = testModel
		assertTrue(hasUniqueObjects(fragmentation.root))
		assertTrue(hasUniqueProxies(fragmentation.root))
		assertTrue(hasUniqueStoreObjects(fragmentation.root))
		
		assertTrue(hasUniqueObjects(copy))
		assertTrue(hasUniqueProxies(copy))
		assertTrue(hasUniqueStoreObjects(copy))
		
		reinit()
		assertTrue(hasUniqueObjects(fragmentation.root))
		reinit()
		assertTrue(hasUniqueProxies(fragmentation.root))
		reinit()
		assertTrue(hasUniqueStoreObjects(fragmentation.root))		
	}
	
	def hasUniqueProxies(FObject object) {
		val Map<FStoreObject, FObject> fStoreObjectMap = newHashMap
		object.allContentsAndReferences.forall[
			val original = it as FObject
			val proxy = fStoreObjectMap.get(original.fStoreObject)
			if (proxy == null) {
				fStoreObjectMap.put(original.fStoreObject, original)
				return true
			} else {
				return proxy == original 
			}
		]
	}
	
	def hasUniqueStoreObjects(FObject object) {
		val Map<FObject, FStoreObject> fStoreObjectMap = newHashMap
		object.allContentsAndReferences.forall[
			val original = it as FObject
			val storeObject = fStoreObjectMap.get(original)
			if (storeObject == null) {
				fStoreObjectMap.put(original, original.fStoreObject)
				return true
			} else {
				return storeObject == original.fStoreObject 
			}
		]
	}
	
	def hasUniqueObjects(FObject object) {
		val Map<String, FObject> map = newHashMap
		object.allContentsAndReferences.forall[
			val original = it as AbstractClass
			val other = map.get(original.name)
			if (other == null) {
				map.put(original.name, original)
				return true
			} else {
				return other == original && other.fStoreObject == other.fStoreObject
			}
		]
	}
	
	def allContentsAndReferences(EObject object) {
		val result = newArrayList(object)
		result.addAll(object.eAllContents.toList)
		new ArrayList(result).forEach[content|
			content.eClass.EAllReferences.filter[content.eIsSet(it)].forEach[feature|
				if (feature.many) {
					result.addAll(content.eGet(feature) as List<EObject>)
				} else {
					result += content.eGet(feature) as EObject
				}
			]
		]
		return result
	}
	
	@Test
	public def void uniqueRootProxy() {
		val Container testModel = create('''Container f1 {
			ref referenced = f1
		}''')	
		val copy = EcoreUtil.copy(testModel)	
		assertSame(copy, copy.eGet(contents_Referenced))
		
		fragmentation.root = testModel		
		
		reinit()	
		assertSame(fragmentation.root, fragmentation.root.eGet(contents_Referenced))
		assertSame(copy, copy.eGet(contents_Referenced))
		copy.pretty
	}
	
	@Test
	public def void fragmentedModelTest() {
		val Container testModel = create(complexFragmentedModelText)
		val copy = EcoreUtil.copy(testModel)
		fragmentation.root = testModel
		reinit()
		assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		assertSame(3, fragmentation.FStoreFragmentation.loadedFragments)
	}
	
	@Test
	def fragmentRootReferenceTest() {
		val Container testModel = create('''
			Container f1 {
				fragment = Container f2 {
					ref referenced = f1
				}
			}
		''')
		fragmentation.root = testModel
		fragmentation.FStoreFragmentation.close // unload all fragments
		assertSame(0, fragmentation.FStoreFragmentation.loadedFragments)
		assertSame(testModel, testModel.fragment.referenced)
	}
	
	@Test
	def nonFragmentRootReferenceTest() {
		val Container testModel = create('''
			Container f1 {
				content = Contents c1;
				fragment = Container f2 {
					ref referenced = c1
				}
			}
		''')
		fragmentation.root = testModel
		fragmentation.FStoreFragmentation.close // unload all fragments
		assertSame(0, fragmentation.FStoreFragmentation.loadedFragments)
		assertSame(testModel.content, testModel.fragment.referenced)
	}
}