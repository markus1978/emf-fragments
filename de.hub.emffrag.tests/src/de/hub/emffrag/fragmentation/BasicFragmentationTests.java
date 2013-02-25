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
		Assertions.root(model).assertId(1);
		
		model.save();
		reinitializeModel();
		
		Assertions.root(model).assertId(1).getFragmentedContents().assertSize(0);		
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

		Assertions
			.root(model).assertId(1)
			.getFragmentedContents().assertSize(1)
			.get(0).assertId(2).assertDifferentFragmentAsContainer()
			.eContainer().assertId(1);
		model.assertFragmentsIndex(0l, 1l);
		model.assertExtrinsicIdIndex(-1l, -1l);
	}

	@Test
	public void testRemoveObject() {
		model.addContent(object1);
		Assertions.root(model).assertId(1);
		object1.getRegularContents().add(object2);
		model.save();		
				
		reinitializeModel();
		model.assertFragmentsIndex(0l, 0l);
		object1 = Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(1)
				.get(0).assertId(2).assertSameFragmentAsContainer()
				.eContainer().assertId(1).value();	

		Assert.assertTrue(object1.getRegularContents().remove(object1.getRegularContents().get(0)));
		model.save();

		reinitializeModel();
		Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(0)
				.getFragmentedContents().assertSize(0)
				.eContents().assertSize(0);
			
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(-1l, -1l);
	}

	@Test
	public void testRemoveRootObject() {

	}

	@Test
	public void testRemoveFragmentRoot() {
		model.addContent(object1);
		Assertions.root(model).assertId(1);
		
		object1.getFragmentedContents().add(object2);
		model.save();
		
		model.assertFragmentsIndex(0l, 1l);
		
		object1.getFragmentedContents().remove(object2);
		model.save();

		reinitializeModel();
		Assertions
				.root(model).assertId(1)
				.eContents().assertSize(0)
				.getFragmentedContents().assertSize(0);
			
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
		Assertions.root(model).eContents().assertSize(0);
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
		Assertions
				.root(model).assertId(1)
				.getFragmentedContents().assertSize(0)
				.getRegularContents().assertSize(1)
				.get(0).assertId(2).assertSameFragmentAsContainer();
		
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
		Assertions
				.root(model).assertId(1).getFragmentedContents().assertSize(2)
				.get(0).assertId(2).assertDifferentFragmentAsContainer().getFragmentedContents().assertSize(0);
		Assertions
				.root(model).getFragmentedContents()
				.get(1).assertId(3).assertDifferentFragmentAsContainer()
				.eContainer().assertId(1);
		
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
		Assertions
				.root(model).assertId(1).getRegularContents().assertSize(0)
				.getFragmentedContents().assertSize(1).get(0).assertId(2).assertDifferentFragmentAsContainer()
				.eContainer().assertId(1);
		
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
		Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(0)
				.getFragmentedContents().assertSize(1)
				.get(0).assertId(2).assertDifferentFragmentAsContainer()
				.getRegularContents().assertSize(1).get(0).assertId(3).assertSameFragmentAsContainer()
				.eContainer().assertId(2);
		
		model.assertFragmentsIndex(0l, 1l);
		model.assertExtrinsicIdIndex(-1l, -1l);
	}
}
