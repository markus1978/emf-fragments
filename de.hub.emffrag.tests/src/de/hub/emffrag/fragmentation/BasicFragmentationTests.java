package de.hub.emffrag.fragmentation;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class BasicFragmentationTests extends AbstractFragmentationTests {
	
	/**
	 * Test the pure creation of an empty fragmented model.
	 */
	@Test
	public void testEmpty() {
		FragmentedModel model = new FragmentedModel(createTestDataStore(), null, TestModelPackage.eINSTANCE);
		model.save();
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		model.assertFragmentsIndex(0l, 1l);
		model.assertExtrinsicIdIndex(-1l, -1l);
	}

	@Test
	public void testRemoveObject() {
		model.addContent(object1);
		assertRootFragment(object1);
		object1.getRegularContents().add(object2);
		model.save();		
				
		reinitializeModel();
		model.assertFragmentsIndex(0l, 0l);
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_RegularContents());
		assertContainment(object1, object2, metaModel.getTestObject_RegularContents(), false);		

		Assert.assertTrue(object1.getRegularContents().remove(object2));
		model.save();

		reinitializeModel();
		object1 = assertHasModelRootFragment();
		Assert.assertTrue(object1.eContents().isEmpty());	
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		
		model.assertFragmentsIndex(0l, 1l);
		
		object1.getFragmentedContents().remove(object2);
		model.save();

		reinitializeModel();
		object1 = assertHasModelRootFragment();
		Assert.assertTrue(object1.eContents().isEmpty());	
		model.assertFragmentsIndex(0l, 0l);	
		model.assertExtrinsicIdIndex(-1l, -1l);
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
				model.assertFragmentsIndex(0l, (long)fragmentationDepth);
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
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		model.assertFragmentsIndex(0l, 2l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		model.assertFragmentsIndex(0l, 1l);
		model.assertExtrinsicIdIndex(-1l, -1l);
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
		model.assertFragmentsIndex(0l, 1l);
		model.assertExtrinsicIdIndex(-1l, -1l);
	}
}
