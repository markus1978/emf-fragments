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
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class BasicFragmentationTests extends CommonTests {
	
	private DataStore dataStore = null;
	private FragmentedModel model = null;
	private TestModelPackage metaModel = null;
	private URI rootFragmentURI = null;
	private TestObject object1 = null;
	private TestObject object2 = null;
	private TestObject object3 = null;

	@Before
	public void registerPackages() {
		if (!EPackage.Registry.INSTANCE.containsKey(TestModelPackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), TestModelPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		}
	}
	
	@Before
	public void standardInitialization() {
		dataStore = createTestDataStore();
		metaModel = TestModelPackage.eINSTANCE;
		model = new FragmentedModel(dataStore, null, metaModel);
		rootFragmentURI = model.getRootFragmentURI();
		
		object1 = TestModelFactory.eINSTANCE.createTestObject();
		object1.setName("testValue");
		object2 = TestModelFactory.eINSTANCE.createTestObject();
		object2.setName("testValue");
		object3 = TestModelFactory.eINSTANCE.createTestObject();
		object3.setName("testValue");
	}
	
	private void reinitializeModel() {
		model = new FragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
	}
	
	private void assertRootFragment(EObject object) {
		Assert.assertNotNull(object.eResource());
		Assert.assertTrue(object.eResource() instanceof Fragment);
	}
	
	private TestObject assertHasModelRootFragment() {
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof TestObject);
		TestObject contents = (TestObject) model.getRootContents().get(0);
		Assert.assertEquals("testValue", contents.getName());
		Assert.assertTrue(contents.eResource() instanceof Fragment);
		return contents;
	}
	
	@SuppressWarnings("rawtypes")
	private TestObject assertHasContents(TestObject container, EStructuralFeature feature) {
		Assert.assertTrue(feature.isMany());
		Assert.assertEquals(1, ((EList)container.eGet(feature)).size());
		Object contentsObject = ((EList)container.eGet(feature)).get(0);
		Assert.assertTrue(contentsObject instanceof TestObject);
		TestObject contents = (TestObject)contentsObject;
		Assert.assertFalse(contents.eIsProxy());
		Assert.assertEquals("testValue", ((TestObject)contents).getName());
		Assert.assertTrue(contents.eResource() instanceof Fragment);
		return (TestObject)contents;
	}
	
	private void assertContainment(EObject container, EObject contents, EStructuralFeature feature, boolean fragmenting) {
		Assert.assertSame(container, contents.eContainer());
		if (fragmenting) {		
			Assert.assertNotSame(container.eResource(), contents.eResource());
		} else {
			Assert.assertSame(container.eResource(), contents.eResource());
		}
		Assert.assertSame(feature, contents.eContainingFeature());				
	}
	
	private void assertIndexDimenions(DataStore dataStore, String prefix, long minKey, long maxKey) {
		DataIndex<Long> dataIndex = new DataIndex<Long>(dataStore, prefix, LongKeyType.instance);
		Assert.assertEquals(minKey, (long) dataIndex.first());
		Assert.assertEquals(maxKey, (long) dataIndex.last());
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
		model.addContent(object1);
		assertRootFragment(object1);
		model.save();
		reinitializeModel();
		assertHasModelRootFragment();
	}

	/**
	 * Test adding a object to a fragmenting reference.
	 */
	@Test
	public void testAddFragment() {
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		model.save();

		reinitializeModel();
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		assertContainment(object1, object2, metaModel.getTestObject_FragmentedContents(), true);
		assertIndexDimenions(dataStore, "f", 0l, 1l);
	}

	@Test
	public void testRemoveObject() {
		model.addContent(object1);
		assertRootFragment(object1);
		object1.getRegularContents().add(object2);
		model.save();		
				
		reinitializeModel();
		assertIndexDimenions(dataStore, "f", 0l, 0l);
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_RegularContents());
		assertContainment(object1, object2, metaModel.getTestObject_RegularContents(), false);		

		Assert.assertTrue(object1.getRegularContents().remove(object2));
		model.save();

		reinitializeModel();
		object1 = assertHasModelRootFragment();
		Assert.assertTrue(object1.eContents().isEmpty());	
		assertIndexDimenions(dataStore, "f", 0l, 0l);
	}

	@Test
	public void testRemoveRootObject() {

	}

	@Test
	public void testRemoveFragmentRoot() {
		model.addContent(object1);
		assertRootFragment(object1);
		object1.getFragmentedContents().add(object2);
		model.save();
		
		assertIndexDimenions(dataStore, "f", 0l, 1l);
		
		object1.getFragmentedContents().remove(object2);
		model.save();

		reinitializeModel();
		object1 = assertHasModelRootFragment();
		Assert.assertTrue(object1.eContents().isEmpty());	
		assertIndexDimenions(dataStore, "f", 0l, 0l);		
	}

	private TestObject addObject(TestObject container, boolean fragmented) {
		TestObject contents = TestModelFactory.eINSTANCE.createTestObject();
		contents.setName("testValue");

		if (container != null) {
			if (fragmented) {
				container.getFragmentedContents().add(contents);
			} else {
				container.getRegularContents().add(contents);
			}
		}

		return contents;
	}
	
	@SuppressWarnings("unchecked")
	private boolean removeObject(TestObject contents) {
		EStructuralFeature containingFeature = contents.eContainingFeature();
		((EList<EObject>) contents.eContainer().eGet(containingFeature)).remove(contents);
		return containingFeature.getName().equals(TestModelPackage.eINSTANCE.getTestObject_FragmentedContents().getName());				
	}

	/**
	 * This test arbitrarily adds and removes objects forming a chain of
	 * containers containing each other. This test does not test the reuse of a
	 * once remove object.
	 */
	@Test
	public void testContiniousAddAndRemove() {
		TestObject container = addObject(null, false);
		model.addContent(container);
		
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
					TestObject newContainer = (TestObject)container.eContainer();
					if (removeObject(container)) {
						fragmentationDepth--;
					}				
					container = newContainer;
				}
				model.save();
				assertIndexDimenions(dataStore, "f", 0, fragmentationDepth);
			}
			while (container.eContainer() != null) {
				TestObject newContainer = (TestObject)container.eContainer();
				removeObject(container);
				container = newContainer;
			}
		} catch (RuntimeException e) {
			System.out.println(dataStore);
			throw e;
		}

		Assert.assertNotNull(container.eResource());
		model.save();

		reinitializeModel();
		reinitializeModel();
		object1 = assertHasModelRootFragment();
		Assert.assertTrue(object1.eContents().isEmpty());	
		assertIndexDimenions(dataStore, "f", 0l, 0l);	
	}
	
	@SuppressWarnings("unused")
	private void print(TestObject object2) {
		System.out.println(System.identityHashCode(object2));
		System.out.println(System.identityHashCode(((FObjectImpl)object2).internalObject()));
		System.out.println(((FObjectImpl)object2).internalObject().eResource().getURI());
		System.out.println(object2.eResource().getURI());
		System.out.println(object2.eContainer().eResource().getURI());
		System.out.println(object2.eContainingFeature().getName());
	}

	@Test
	public void testMoveFragmentRootToNonFragmentingReference() {
		model.addContent(object1);
		
		object1.getFragmentedContents().add(object2);						
		object1.getRegularContents().add(object2);		
		object2 = object1.getRegularContents().get(0);
		
		model.save();
		reinitializeModel();
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_RegularContents());
		assertContainment(object1, object2, metaModel.getTestObject_RegularContents(), false);
		assertIndexDimenions(dataStore, "f", 0l, 0l);
	}

	@Test
	public void testMoveFragmentRootToOtherFragmentingReference() {
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);		
		object1.getFragmentedContents().add(object3);
		model.save();
		
		reinitializeModel();
		object1 = assertHasModelRootFragment();
		Assert.assertEquals(2, object1.getFragmentedContents().size());
		Assert.assertTrue(object1.getFragmentedContents().get(0).getFragmentedContents().isEmpty());
		Assert.assertTrue(object1.getFragmentedContents().get(1).getFragmentedContents().isEmpty());
		assertIndexDimenions(dataStore, "f", 0l, 2l);
	}

	@Test
	public void testMoveObjectToFragmentingReference() {
		model.addContent(object1);
		object1.getRegularContents().add(object2);
		object1.getFragmentedContents().add(object2);
		model.save();
		
		reinitializeModel();
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		assertContainment(object1, object2, metaModel.getTestObject_FragmentedContents(), true);
		assertIndexDimenions(dataStore, "f", 0l, 1l);
	}

	@Test
	public void testMoveContainedObjectToAnotherFragment() {
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object1.getRegularContents().add(object3);
		object2.getRegularContents().add(object3);
		model.save();
		
		reinitializeModel();
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		object3 = assertHasContents(object2, metaModel.getTestObject_RegularContents());
		assertContainment(object1, object2, metaModel.getTestObject_FragmentedContents(), true);
		assertContainment(object2, object3, metaModel.getTestObject_RegularContents(), false);
		assertIndexDimenions(dataStore, "f", 0l, 1l);
	}
}
