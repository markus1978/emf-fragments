package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.Fragmentation
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.InMemoryDataStore
import de.hub.emffrag.tests.model.TestModelPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EcorePackage
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static de.hub.emffrag.tests.FStoreObjectTestModelParser.*
import static org.junit.Assert.*

class FragmentationTests extends AbstractTests {
	
	var Fragmentation fragmentation = null
	
	@BeforeClass
	static def void beforeClass() {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	@Before
	def void before() {
		TestModelParser::clearNames
		fragmentation = new Fragmentation(newArrayList(TestModelPackage.eINSTANCE), new DataStoreImpl(new InMemoryDataStore(false), URI.createURI("test")), 0)
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
		
		assertSame(root, fragmentation.root)
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
		
		assertSame(root, fragmentation.root)
		assertSame(2, fragmentation.loadedFragments)
		assertSame(fragmentation, root.fFragmentation)
		assertSame(fragmentation, withName("f2").fFragmentation)
		assertSame(fragmentation, withName("c3").fFragmentation)
		assertSame(fragmentation, withName("c4").fFragmentation)
	}
	
	@Test
	def addFragmentsToExistingContent() {
		val root = create('''Container f1;''')		
		fragmentation.root = root
		
		assertSame(root, fragmentation.root)
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
		
		assertSame(root, fragmentation.root)
		assertSame(2, fragmentation.loadedFragments)
		assertSame(fragmentation, f2.fFragmentation)
		assertSame(fragmentation, withName("c3").fFragmentation)
		assertSame(fragmentation, c4.fFragmentation)
	}
	
	@Test
	def addIndirectFragmentsToExistingContent() {
		val root = create('''Container f1;''')		
		fragmentation.root = root
		
		assertSame(root, fragmentation.root)
		assertSame(1, fragmentation.loadedFragments)
		
		val c2 = create('''
			Container c2 {
				fragment = Contents f3;
			}
		''')
		root.fSet(container_Content, c2)
		c2.fSetContainer(root, container_Content);		
		
		assertSame(root, fragmentation.root)
		assertSame(2, fragmentation.loadedFragments)
		assertSame(fragmentation, c2.fFragmentation)
		assertSame(fragmentation, withName("f3").fFragmentation)
		assertTrue(withName("f3").fIsRoot)
	}
}