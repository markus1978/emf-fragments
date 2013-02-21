package de.hub.emffrag.example;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.fragmentation.ReflectiveMetaModelRegistry;
import de.hub.emffrag.testmodels.frag.testmodel.Container;
import de.hub.emffrag.testmodels.frag.testmodel.Contents;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class HelloWorldExample {

	public static final void main(String[] args) {
		// necessary if you use EMF outside of a running eclipse environment
		EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), TestModelPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());

		// use the next lines to use HBase as IKeyValueStore
		// Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("hbase",
		// new XMIResourceFactoryImpl());
		// IKeyValueStore keyValueStore = new HBaseKeyValueStore();

		// use the next lines to use an in-memory-test IKeyValueStore
		DataStore dataStore = new InMemoryDataStore("memory", "localhost", "test", false);

		// initialize your model
		ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		URI rootFragmentURI = model.getRootFragmentURI();

		// create a root object and add it to the model
		Container testContainer = TestModelFactory.eINSTANCE.createContainer();
		model.addContent(testContainer);

		// create the rest of your model as usual
		Contents testContents = TestModelFactory.eINSTANCE.createContents();
		Contents testFragmentedContents = TestModelFactory.eINSTANCE.createContents();

		testContents.setValue("Hello Old World!");
		testFragmentedContents.setValue("Hello New World!");

		testContainer.getContents().add(testContents);
		testContainer.getFragmentedContents().add(testFragmentedContents);

		// call save to force save of cached and unsaved parts of your model
		// before exiting the JVM
		model.save();

		System.out.println("Key value store contents: ");
		System.out.println(dataStore);

		// to read a model initialize the environment as before
		// initialize your model
		FragmentedModel readModel = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);

		// navigate the model as usual
		System.out.println("Iterate results: ");
		TreeIterator<EObject> allContents = readModel.getRootContents().get(0).eAllContents();
		while (allContents.hasNext()) {
			EObject next = allContents.next();
			if (next instanceof Contents) {
				System.out.println(((Contents) next).getValue());
			}
		}
	}
}
