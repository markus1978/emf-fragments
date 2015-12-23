package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.Fragmentation
import de.hub.emffrag.FragmentationImpl
import de.hub.emffrag.datastore.DataStoreImpl
import de.hub.emffrag.datastore.InMemoryDataStore
import de.hub.emffrag.tests.model.Container
import de.hub.emffrag.tests.model.TestModelPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EcorePackage

import static de.hub.emffrag.tests.FObjectTestModelParser.*
import org.junit.Assert

class PerformanceMeasures {
	private var Fragmentation fragmentation  = null
	static def void beforeClass() {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	def void before() {
		TestModelParser::clearNames
		val dataStore = new DataStoreImpl(new InMemoryDataStore(false), URI.createURI("test"))
		fragmentation = new FragmentationImpl(newArrayList(TestModelPackage.eINSTANCE), dataStore, 3)
	}
	
	def void measureModelTraversal() {
		before
		val ()=>Container createPart = [create('''
			Container o1 {
				contents = Container o11 {
					ref referenceds = o1
					content = Contents o111;
					contents = Contents o112;
					
				}
				contents = Contents o12;
				contents = Container o13 {
					ref referenced = o12
					content = Container leave {
						contents = Contents leave1;
					}
				}
			}
		''')]
		val model = createPart.apply
		for (i:0..10) {			
			(withName("leave") as Container).fragment = createPart.apply;		
		}
		val size = model.eAllContents.size
		println("size " + size)
		fragmentation.root = model
		
		while (true) {
			val start = System.currentTimeMillis
			var count = 0;
			for (i:0..(100000/size)) {
				count += size;
				Assert.assertSame(size, fragmentation.root.eAllContents.size)						
			}		
			val time = System.currentTimeMillis - start
			println('''For «count» object, we need «time» ms. This are «(count/time)*1000» objects per second.''')
		}
	} 
	
	public static def void main(String[] args) {
		beforeClass
		val pm = new PerformanceMeasures()
		pm.measureModelTraversal
	}
}