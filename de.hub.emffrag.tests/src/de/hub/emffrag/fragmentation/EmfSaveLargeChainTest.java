package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

public class EmfSaveLargeChainTest {
	@Test
	public void theTest() throws Exception {
		EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new ResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {			
				return new BinaryResourceImpl(uri);
			}			
		});
		Resource resource = new ResourceSetImpl().createResource(URI.createURI("test.ecore"));
		EPackage thePkg = EcoreFactory.eINSTANCE.createEPackage();
		resource.getContents().add(thePkg);
		thePkg.setName("ThePkg");
		EClass lastClass = null;
		for (int i = 0; i < 10000; i++) {
			EClass newClass = EcoreFactory.eINSTANCE.createEClass();
			newClass.setName("Class" + i);
			thePkg.getEClassifiers().add(newClass);
			if (lastClass != null) {
				EReference ref = EcoreFactory.eINSTANCE.createEReference();
				ref.setName("ref");
				ref.setEType(newClass);
				lastClass.getEStructuralFeatures().add(ref);
			}
			lastClass = newClass;
		}
		
		resource.save(null);
	}	
}
