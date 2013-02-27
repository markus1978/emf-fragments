package de.hub.emffrag.fragmentation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class ReflectiveMetaModelRegistryTests extends AbstractTests {
	
	private ReflectiveMetaModelRegistry registry;
	
	@Before
	public void resetRegistry() {
		registry = ReflectiveMetaModelRegistry.instance;
		registry.clear();		
	}
	
	@Test
	public void testRegisteringMetaModelsWithReferencesToOtherMetaModels() {
		registry.registerRegularMetaModel(EmfFragPackage.eINSTANCE);
		registry.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		try {
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					TestModelPackage.eINSTANCE.getTestObject(), registry.getOppositeClass(TestModelPackage.eINSTANCE.getTestObject()));
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					TestModelPackage.eINSTANCE.getTestObjectIndex(), registry.getOppositeClass(TestModelPackage.eINSTANCE.getTestObjectIndex()));
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					EmfFragPackage.eINSTANCE.getRoot(), registry.getOppositeClass(EmfFragPackage.eINSTANCE.getRoot()));
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					EmfFragPackage.eINSTANCE.getRoot_Contents(), registry.getOppositeFeature(EmfFragPackage.eINSTANCE.getRoot_Contents()));
		} catch (Exception e) {
			Assert.fail("Could not retrive the opposite of a referenced meta-model element: " + e.getMessage());
		}
	}

}
