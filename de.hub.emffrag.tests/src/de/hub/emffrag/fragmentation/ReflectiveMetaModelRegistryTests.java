package de.hub.emffrag.fragmentation;

import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class ReflectiveMetaModelRegistryTests extends AbstractTests {
	
	@Test
	public void testRegisteringMetaModelsWithReferencesToOtherMetaModels() {
		ReflectiveMetaModelRegistry registry = ReflectiveMetaModelRegistry.instance;
		registry.registerUserMetaModel(EmfFragPackage.eINSTANCE);
		registry.registerUserMetaModel(TestModelPackage.eINSTANCE);
		try {
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					TestModelPackage.eINSTANCE.getTestObject(), registry.getInternalClass(TestModelPackage.eINSTANCE.getTestObject()));
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					TestModelPackage.eINSTANCE.getTestObjectIndex(), registry.getInternalClass(TestModelPackage.eINSTANCE.getTestObjectIndex()));
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					EmfFragPackage.eINSTANCE.getRoot(), registry.getInternalClass(EmfFragPackage.eINSTANCE.getRoot()));
			Assert.assertNotSame("Registered opposite is the same as the original.", 
					EmfFragPackage.eINSTANCE.getRoot_Contents(), registry.getInternalFeature(EmfFragPackage.eINSTANCE.getRoot_Contents()));
		} catch (Exception e) {
			Assert.fail("Could not retrive the opposite of a referenced meta-model element: " + e.getMessage());
		}
	}

}
