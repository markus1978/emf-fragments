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
import org.eclipse.emf.ecore.util.EcoreUtil

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
					ref referenced = o1
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
		for (i:0..100) {	
			if (i%3 == 0) {		
				(withName("leave") as Container).fragment = createPart.apply;		
			} else {
				(withName("leave") as Container).content = createPart.apply;
			}		
		}
		val size = model.eAllContents.size
		val copy = EcoreUtil.copy(model)
		println("size " + size)
		fragmentation.root = model
		Assert.assertTrue(EcoreUtil.equals(copy, fragmentation.root))
		
		while (true) {
			val start = System.currentTimeMillis
			var count = 0;
			for (i:0..(100000/size)) {
				count += size;
				//Assert.assertSame(size, fragmentation.root.eAllContents.size)
				Assert.assertTrue(EcoreUtil.equals(copy, fragmentation.root))						
			}		
			val time = System.currentTimeMillis - start
			println('''For «count» object, we need «time» ms. This are «(count/time)»k objects per second.''')
		}
	} 
	
	public static def void main(String[] args) {
		beforeClass
		val pm = new PerformanceMeasures()
		pm.measureModelTraversal
	}
}