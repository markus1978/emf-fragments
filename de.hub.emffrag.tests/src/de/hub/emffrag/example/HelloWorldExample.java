package de.hub.emffrag.example;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.hub.emffrag.fragmentation.FResourceSet;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.fragmentation.ReflectiveMetaModelRegistry;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class HelloWorldExample {

	public static final void main(String[] args) throws Exception {
		// necessary if you use EMF outside of a running eclipse environment
		EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), TestModelPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());

		// initialize your model
		ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		Resource resource = new FResourceSet().createResource(URI.createURI("memory://localhost/test"));

		// create a object and add it to the model root
		TestObject testContainer = TestModelFactory.eINSTANCE.createTestObject();
		testContainer.setName("Container");
		resource.getContents().add(testContainer);

		// create the rest of your model as usual
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
		FragmentedModel readModel = new FragmentedModel(URI.createURI("memory://localhost/test"));

		// navigate the model as usual
		System.out.println("Iterate results: ");
		TreeIterator<EObject> allContents = readModel.getAllContents();
		while (allContents.hasNext()) {
			System.out.println(allContents.next());			
		}
	}
}
