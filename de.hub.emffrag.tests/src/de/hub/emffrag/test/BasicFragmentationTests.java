package de.hub.emffrag.test;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.testmodels.frag.testmodel.Container;
import de.hub.emffrag.testmodels.frag.testmodel.Contents;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class BasicFragmentationTests extends CommonTests {

	@Before
	public void registerPackages() {
		if (!EPackage.Registry.INSTANCE.containsKey(TestModelPackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), TestModelPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		}
	}
	
	@Test
	public void testEmpty() {
		FragmentedModel model = new FragmentedModel(createTestDataStore(), null, TestModelPackage.eINSTANCE);
	}
	
	@Test
	public void testAddEmpty() {		
		DataStore dataStore = createTestDataStore();
		
		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		Container container = TestModelFactory.eINSTANCE.createContainer();
		Contents c = TestModelFactory.eINSTANCE.createContents();
		c.setValue("testValue");
		model.addContent(container);
		
		Assert.assertNotNull(container.eResource());
		Assert.assertTrue(container.eResource() instanceof Fragment);
		
		container.getFragmentedContents().add(c);
		URI rootFragmentURI = model.getRootFragmentURI();
		model.save();
		
		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Container);
		container = (Container)model.getRootContents().get(0);
		Assert.assertTrue(container.getFragmentedContents().size() == 1);
		Assert.assertEquals("testValue", container.getFragmentedContents().get(0).getValue());
	}
}
