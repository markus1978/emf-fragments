package de.hub.emffrag.example;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class HelloWorldExample {

	public static final void main(String[] args) throws Exception {
		// necessary if you use EMF outside of a running eclipse environment
		EmfFragActivator.standalone(TestModelPackage.eINSTANCE);
		
		// initialize your model
		Resource resource = new ResourceSetImpl().createResource(URI.createURI("memory://localhost/test"));
		
		// create the model as usual
		TestObject testContainer = TestModelFactory.eINSTANCE.createTestObject();
		testContainer.setName("Container");
		resource.getContents().add(testContainer);
	
		TestObject testContents = TestModelFactory.eINSTANCE.createTestObject();
		TestObject testFragmentedContents = TestModelFactory.eINSTANCE.createTestObject();
		
		testContents.setName("Hello Old World!");
		testFragmentedContents.setName("Hello New World!");
		
		testContainer.getRegularContents().add(testContents);
		testContainer.getFragmentedContents().add(testFragmentedContents);
		
		// call save to force save of cached and unsaved parts of your model
		// before exiting the JVM
		resource.save(null);
		
		System.out.println("Key value store contents: ");
		System.out.println(((FragmentedModel)resource).getDataStore());
		
		// to read a model initialize the environment as before
		// initialize your model
		resource = new ResourceSetImpl().createResource(URI.createURI("memory://localhost/test"));
		
		// navigate the model as usual
		System.out.println("Iterate results: ");
		TreeIterator<EObject> allContents = resource.getAllContents();
		while (allContents.hasNext()) {
			System.out.println(allContents.next());			
		}
	}
}
