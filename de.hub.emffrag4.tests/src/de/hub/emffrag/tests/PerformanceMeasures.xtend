package de.hub.emffrag.tests

import de.hub.emffrag.tests.model.Container
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Assert
import org.junit.Test

import static de.hub.emffrag.tests.FObjectTestModelParser.*

class PerformanceMeasures extends AbstractDataStoreTests {
	
	override cacheSize() {
		return 10
	}
	
	@Test
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
					ref referenced = o1
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
				Assert.assertEquals(size, fragmentation.root.eAllContents.size)						
			}		
			val time = System.currentTimeMillis - start
			println('''For «count» object, we need «time» ms. This are «(count/time)»k objects per second.''')
		}
	} 
}