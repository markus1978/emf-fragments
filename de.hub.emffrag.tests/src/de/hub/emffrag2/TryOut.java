package de.hub.emffrag2;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;

public class TryOut {
	
	public static ResourceSet fragmentation = null;
	
	public static void main(String args[]) throws Exception {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("frag", new Resource.Factory() {
			@Override
			public Resource createResource(URI uri) {
				return new Fragment(uri);
			}
		});
		
		fragmentation = new Fragmentation();
		Resource resource = fragmentation.createResource(URI.createURI("test.frag"));
		
		TestObject container = TestModelFactory.eINSTANCE.createTestObject();
		resource.getContents().add(container);
		
		TestObject contents = TestModelFactory.eINSTANCE.createTestObject();
		container.getFragmentedContents().add(contents);
		
//		resource.getContents().add(container);
//		resource.save(null);
//		
//		resource.unload();
//		container.getFragmentedContents().get(0);		
	}
}
