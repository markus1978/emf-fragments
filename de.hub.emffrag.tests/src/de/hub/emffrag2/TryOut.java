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
		
		TestObject one = TestModelFactory.eINSTANCE.createTestObject();
		one.setName("1");
		resource.getContents().add(one);
		
		TestObject two = TestModelFactory.eINSTANCE.createTestObject();
		two.setName("2");
		one.getFragmentedContents().add(two);
		
		TestObject three = TestModelFactory.eINSTANCE.createTestObject();
		three.setName("3");
		two.getFragmentedContents().add(three);
		
		System.out.println(one.getName());
		System.out.println(one.getFragmentedContents().get(0).getName());
		System.out.println(one.getFragmentedContents().get(0).getFragmentedContents().get(0).getName());
	}
}
