package de.hub.emffrag.test;

import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
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

	/**
	 * Test the pure creation of an empty fragmented model.
	 */
	@Test
	public void testEmpty() {
		FragmentedModel model = new FragmentedModel(createTestDataStore(), null, TestModelPackage.eINSTANCE);
		model.save();
	}

	/**
	 * Test adding a single object to an empty fragmented model as new root
	 * object.
	 */
	@Test
	public void testAddRootObject() {
		DataStore dataStore = createTestDataStore();

		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		Contents c = TestModelFactory.eINSTANCE.createContents();
		c.setValue("testValue");
		model.addContent(c);

		Assert.assertNotNull(c.eResource());
		Assert.assertTrue(c.eResource() instanceof Fragment);

		URI rootFragmentURI = model.getRootFragmentURI();
		model.save();

		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Contents);
		Assert.assertEquals("testValue", ((Contents) model.getRootContents().get(0)).getValue());
	}

	private void assertIndexDimenions(DataStore dataStore, String prefix, long minKey, long maxKey) {
		DataIndex<Long> dataIndex = new DataIndex<Long>(dataStore, prefix, LongKeyType.instance);
		Assert.assertEquals(minKey, (long) dataIndex.first());
		Assert.assertEquals(maxKey, (long) dataIndex.last());
	}

	/**
	 * Test adding a object to a fragmenting reference.
	 */
	@Test
	public void testAddFragment() {
		DataStore dataStore = createTestDataStore();

		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		Container container = TestModelFactory.eINSTANCE.createContainer();
		Contents contents = TestModelFactory.eINSTANCE.createContents();
		contents.setValue("testValue");
		model.addContent(container);

		Assert.assertNotNull(container.eResource());
		Assert.assertTrue(container.eResource() instanceof Fragment);

		container.getFragmentedContents().add(contents);
		URI rootFragmentURI = model.getRootFragmentURI();
		model.save();

		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Container);
		container = (Container) model.getRootContents().get(0);
		Assert.assertTrue(container.getFragmentedContents().size() == 1);
		Assert.assertEquals("testValue", container.getFragmentedContents().get(0).getValue());

		assertIndexDimenions(dataStore, "f", 0l, 1l);
	}

	@Test
	public void testRemoveObject() {
		DataStore dataStore = createTestDataStore();

		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		Container container = TestModelFactory.eINSTANCE.createContainer();
		Contents contents = TestModelFactory.eINSTANCE.createContents();
		contents.setValue("testValue");
		model.addContent(container);

		Assert.assertNotNull(container.eResource());
		Assert.assertTrue(container.eResource() instanceof Fragment);

		container.getContents().add(contents);
		URI rootFragmentURI = model.getRootFragmentURI();
		model.save();
		assertIndexDimenions(dataStore, "f", 0l, 0l);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Container);
		container = (Container) model.getRootContents().get(0);
		Assert.assertTrue(container.getContents().size() == 1);

		container.getFragmentedContents().remove(contents);
		model.save();

		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Container);
		container = (Container) model.getRootContents().get(0);
		Assert.assertTrue(container.getFragmentedContents().size() == 0);
		assertIndexDimenions(dataStore, "f", 0l, 0l);
	}

	@Test
	public void testRemoveRootObject() {

	}

	@Test
	public void testRemoveFragmentRoot() {
		DataStore dataStore = createTestDataStore();

		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		Container container = TestModelFactory.eINSTANCE.createContainer();
		Contents contents = TestModelFactory.eINSTANCE.createContents();
		contents.setValue("testValue");
		model.addContent(container);

		Assert.assertNotNull(container.eResource());
		Assert.assertTrue(container.eResource() instanceof Fragment);

		container.getFragmentedContents().add(contents);
		URI rootFragmentURI = model.getRootFragmentURI();
		model.save();
		container.getFragmentedContents().remove(contents);
		model.save();

		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Container);
		container = (Container) model.getRootContents().get(0);
		Assert.assertTrue(container.getFragmentedContents().size() == 0);
		assertIndexDimenions(dataStore, "f", 0l, 0l);
	}

	public Contents addObject(Container container, boolean fragmented) {
		Contents contents = TestModelFactory.eINSTANCE.createContents();
		contents.setValue("testValue");

		if (container != null) {
			if (fragmented) {
				container.getFragmentedContents().add(contents);
			} else {
				container.getContents().add(contents);
			}
		}

		return contents;
	}
	
	@SuppressWarnings("unchecked")
	public boolean removeObject(Contents contents) {
		EStructuralFeature containingFeature = contents.eContainingFeature();
		((EList<EObject>) contents.eContainer().eGet(containingFeature)).remove(contents);
		return containingFeature.getName().equals(TestModelPackage.eINSTANCE.getContainer_FragmentedContents().getName());				
	}

	/**
	 * This test arbitrarily adds and removes objects forming a chain of
	 * containers containing each other. This test does not test the reuse of a
	 * once remove object.
	 */
	@Test
	public void testContiniousAddAndRemove() {
		DataStore dataStore = createTestDataStore();

		FragmentedModel model = new FragmentedModel(dataStore, null, TestModelPackage.eINSTANCE);
		Contents container = addObject(null, false);
		model.addContent(container);
		URI rootFragmentURI = model.getRootFragmentURI();
		
		Random random = new Random(0);
		int fragmentationDepth = 0;
		
		try {
			for (int i = 0; i < 100; i++) {
				if (container.eContainer() == null || random.nextBoolean()) {
					boolean fragmenting = random.nextBoolean();
					container = addObject(container, fragmenting);
					if (fragmenting) {
						fragmentationDepth++;
					}
				} else {
					Contents newContainer = (Contents)container.eContainer();
					if (removeObject(container)) {
						fragmentationDepth--;
					}				
					container = newContainer;
				}
				model.save();
				assertIndexDimenions(dataStore, "f", 0, fragmentationDepth);
			}
			while (container.eContainer() != null) {
				Contents newContainer = (Contents)container.eContainer();
				removeObject(container);
				container = newContainer;
			}
		} catch (RuntimeException e) {
			System.out.println(dataStore);
			throw e;
		}

		Assert.assertNotNull(container.eResource());
		model.save();

		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof Container);
		Assert.assertTrue(container.getFragmentedContents().size() == 0);
		assertIndexDimenions(dataStore, "f", 0l, 0l);
	}

	@Test
	public void testMoveFragmentRootToNonFragmentingReference() {

	}

	@Test
	public void testMoveFragmentRootToOtherFragmentingReference() {

	}

	@Test
	public void testMoveObjectToFragmentingReference() {

	}

	@Test
	public void testMoveObjectToAnotherFragment() {

	}
}
