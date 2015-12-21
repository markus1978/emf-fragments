package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.FStore
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.InMemoryDataStore
import de.hub.emffrag.internal.FStoreFragmentation
import de.hub.emffrag.tests.model.TestModelPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static de.hub.emffrag.tests.FStoreObjectTestModelParser.*
import static org.junit.Assert.*
import de.hub.emffrag.internal.FStoreObject

class FStoreFragmentationTests extends AbstractTests {
	
	var FStoreFragmentation fragmentation = null
	
	@BeforeClass
	static def void beforeClass() {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	@Before
	def void before() {
		TestModelParser::clearNames
		fragmentation = new FStoreFragmentation(newArrayList(TestModelPackage.eINSTANCE), new DataStoreImpl(new InMemoryDataStore(false), URI.createURI("test")), 0)
	}
	
	@Test
	def addNonFragmentedContentsToFragmentation() {
		val root = create('''
			Container c1 {
				contents = Container c2 {
					content = Contents c3;
				}
				content = Contents c4;
			}
		''')		
		fragmentation.root = root
		
		assertSame(root, fragmentation.getRoot)
		assertSame(1, fragmentation.loadedFragments)
		assertSame(fragmentation, root.fFragmentation)
	}
	
	@Test
	def addFragmentedContentsToFragmentation() {
		val root = create('''
			Container f1 {
				fragments = Container f2 {
					content = Contents c3;
				}
				content = Contents c4;
			}
		''')		
		fragmentation.root = root
		
		assertSame(root, fragmentation.getRoot)
		assertSame(2, fragmentation.loadedFragments)
		assertSame(fragmentation, root.fFragmentation)
		assertSame(fragmentation, withName("f2").fFragmentation)
		assertSame(fragmentation, withName("c3").fFragmentation)
		assertSame(fragmentation, withName("c4").fFragmentation)
		assertSame(0, root.fFragmentID)
		assertSame(1, withName("f2").fFragmentID)
	}
	
	@Test
	def addFragmentsToExistingContent() {
		val root = create('''Container f1;''')		
		fragmentation.root = root
		
		assertSame(root, fragmentation.getRoot)
		assertSame(1, fragmentation.loadedFragments)
		
		val f2 = create('''
			Container f2 {
				contents = Contents c3;
			}
		''')
		root.fSet(container_Fragment, f2)
		f2.fSetContainer(root, container_Fragment);
		val c4 = create("Contents c4")
		root.fSet(container_Content, c4)
		c4.fSetContainer(root, container_Content);
		
		assertSame(root, fragmentation.getRoot)
		assertSame(2, fragmentation.loadedFragments)
		assertSame(fragmentation, f2.fFragmentation)
		assertSame(fragmentation, withName("c3").fFragmentation)
		assertSame(fragmentation, c4.fFragmentation)
	}
	
	@Test
	def addIndirectFragmentsToExistingContent() {
		val root = create('''Container f1;''')		
		fragmentation.root = root
		
		assertSame(root, fragmentation.getRoot)
		assertSame(1, fragmentation.loadedFragments)
		
		val c2 = create('''
			Container c2 {
				fragment = Contents f3;
			}
		''')
		root.fSet(container_Content, c2)
		c2.fSetContainer(root, container_Content);		
		
		assertSame(root, fragmentation.getRoot)
		assertSame(2, fragmentation.loadedFragments)
		assertSame(fragmentation, c2.fFragmentation)
		assertSame(fragmentation, withName("f3").fFragmentation)
		assertTrue(withName("f3").fIsRoot)
	}
	
	@Test
	def simpleSingleFragmentUnloadTest() {
		val root = create('''Container f1;''')
		fragmentation.root = root;
		assertTrue(fragmentation.getRoot.fModified)
		fragmentation.unloadFragment(root);
		
		assertTrue(root.fIsProxy)
		assertSame(0, fragmentation.loadedFragments)
		
		assertNotNull(fragmentation.getRoot)
		assertNotSame(root, fragmentation.getRoot)
	}
	
	@Test
	def simpleComplexFragmentUnloadTest() {
		val root = create(FStoreObjectTests.complexFragmentText)
		val rootCopy = EcoreUtil.copy(FStore.fINSTANCE.proxify(root))
		fragmentation.root = root;
		assertTrue(fragmentation.getRoot.fModified)
		fragmentation.unloadFragment(root);
		
		assertTrue(root.fIsProxy)
		assertSame(0, fragmentation.loadedFragments)
		
		assertNotNull(fragmentation.getRoot)
		assertNotSame(root, fragmentation.getRoot)
		assertSame(1, fragmentation.loadedFragments)
		
		assertTrue(EcoreUtil.equals(rootCopy, FStore.fINSTANCE.proxify(fragmentation.getRoot)))
	}
	
	@Test
	def parentFragmentUnloadTest() {
		val root = create('''
			Container f1 {
				fragment = Container f2 {
					content = Container c1 {
						fragment = Contents f3;
					}
				}
			}
		''')
		fragmentation.root = root
		assertSame(3, fragmentation.loadedFragments)
		fragmentation.unloadFragment(withName("f2"))
		assertSame(2, fragmentation.loadedFragments)
		assertTrue(withName("f3").fContainer.fIsProxy)
		assertTrue((root.fGet(container_Fragment) as FStoreObject).fIsProxy)
		assertSame((root.fGet(container_Fragment) as FStoreObject).fProxyURI.fragment, withName("f3").fContainer.fProxyURI.fragment)
	}
	
	@Test
	def parentFragmentResolveTest() {
		val root = create('''
			Container f1 {
				fragment = Container f2 {
					content = Container c1 {
						fragment = Contents f3;
					}
				}
			}
		''')
		fragmentation.root = root
		fragmentation.unloadFragment(withName("f2"))
		assertTrue(withName("f2").fIsProxy)
		val resolved = fragmentation.resolve(withName("f2").fProxyURI)
		assertFalse(resolved.fIsProxy)	
		assertNotNull(resolved.fContainer)
		assertTrue(resolved.fContainer.fIsProxy)
		assertSame(root, fragmentation.resolve(resolved.fContainer.fProxyURI))
		assertSame(3, fragmentation.loadedFragments)	
	}
}