package de.hub.emffrag.fragmentation;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.model.emffrag.Root;
import de.hub.emffrag.testmodels.testmodel.TestEnum;
import de.hub.emffrag.testmodels.testmodel.TestObject;

public class BasicFragmentationTests extends AbstractFragmentationTests {
	
	/**
	 * Test the pure creation of an empty fragmented model.
	 */
	@Test
	public void testEmpty() {
		FragmentedModel model = new FragmentedModel(createTestDataStore());
		model.save(null);
		model.assertFragmentsIndex(0l, 0l);
		model.assertIdIndex(-1l, -1l);
	}

	/**
	 * Test adding a single object to an empty fragmented model as new root
	 * object.
	 */
	@Test
	public void testAddRootObject() {		
		model.root().getContents().add(object1);
		Assertions.root(model).assertId(1);
		
		model.save(null);
		reinitializeModel();		
		
		Assertions.root(model).assertId(1).getFragmentedContents().assertSize(0);		
		model.assertFragmentsIndex(0l, 1l);
		model.assertIdIndex(-1l, -1l);
	}

	/**
	 * Test adding a object to a fragmenting reference.
	 */
	@Test
	public void testAddFragment() {
		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
	
		model.save(null);

		reinitializeModel();

		Assertions
			.root(model).assertId(1)
			.getFragmentedContents().assertSize(1)
			.get(0).assertId(2).assertDifferentFragmentAsContainer()
			.eContainer().assertId(1);
		model.assertFragmentsIndex(0l, 2l);
		model.assertIdIndex(-1l, -1l);
	}
	
	@Test
	public void testIncrementalFragmentChange() {
		object1.getRegularContents().add(object2);
		object2.getRegularContents().add(object3);
		model.root().getContents().add(object1);
		
		Assertions
			.root(model).assertId(1).getRegularContents().assertSize(1)
			.get(0).assertId(2).getRegularContents().assertSize(1)
			.get(0).assertId(3);
		
		Assert.assertEquals(object1.getRegularContents().get(0), object2);
		Assert.assertEquals(object1.getRegularContents().get(0).getRegularContents().get(0), object3);
		
		model.save(null);
		reinitializeModel();
		
		Assertions
			.root(model).assertId(1).getRegularContents().assertSize(1)
			.get(0).assertId(2).getRegularContents().assertSize(1)
			.get(0).assertId(3);
	}
	
	@Test
	public void testRecursiveFragmentation1() {
		object2.getFragmentedContents().add(object3);
		
		Assert.assertTrue(((FObjectImpl)object2).fInternalObject().eContents().size() == 1);
		
		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		
		performRecursiveFragmentationTest();
	}
	
	@Test
	public void testRecursiveFragmentation2() {
		object2.getFragmentedContents().add(object3);
		object1.getFragmentedContents().add(object2);
		model.root().getContents().add(object1);
		
		performRecursiveFragmentationTest();
	}
	
	@Test
	public void testRecursiveFragmentation3() {
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);	
		model.root().getContents().add(object1);
		
		performRecursiveFragmentationTest();
	}
	
	
	private void performRecursiveFragmentationTest() {
		Assertions
			.root(model).assertId(1).getFragmentedContents().assertSize(1)
			.get(0).assertId(2).getFragmentedContents().assertSize(1)
			.get(0).assertId(3);
	
		Assert.assertEquals(object1.getFragmentedContents().get(0), object2);
		Assert.assertEquals(object1.getFragmentedContents().get(0).getFragmentedContents().get(0), object3);
		
		model.assertFragmentsIndex(0, 3);
	
		model.save(null);
		reinitializeModel();
	
		Assertions
			.root(model).assertId(1).getFragmentedContents().assertSize(1)
			.get(0).assertId(2).getFragmentedContents().assertSize(1)
			.get(0).assertId(3);
	}

	@Test
	public void testRemoveObject() {
		model.root().getContents().add(object1);
		Assertions.root(model).assertId(1);
		object1.getRegularContents().add(object2);
		model.save(null);		
				
		reinitializeModel();
		model.assertFragmentsIndex(0l, 1l);
		object1 = Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(1)
				.get(0).assertId(2).assertSameFragmentAsContainer()
				.eContainer().assertId(1).value();	

		Assert.assertTrue(object1.getRegularContents().remove(object1.getRegularContents().get(0)));
		model.save(null);

		reinitializeModel();
		Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(0)
				.getFragmentedContents().assertSize(0)
				.eContents().assertSize(0);
			
		model.assertFragmentsIndex(0l, 1l);
		model.assertIdIndex(-1l, -1l);
	}

	@Test
	public void testRemoveRootObject() {

	}

	@Test
	public void testRemoveFragmentRoot() {
		model.root().getContents().add(object1);
		Assertions.root(model).assertId(1);
		
		object1.getFragmentedContents().add(object2);
		model.save(null);
		
		model.assertFragmentsIndex(0l, 2l);
		
		object1.getFragmentedContents().remove(object2);
		model.save(null);

		reinitializeModel();
		Assertions
				.root(model).assertId(1)
				.eContents().assertSize(0)
				.getFragmentedContents().assertSize(0);
			
		model.assertFragmentsIndex(0l, 1l);	
		model.assertIdIndex(-1l, -1l);
	}

	/**
	 * This test arbitrarily adds and removes objects forming a chain of
	 * containers containing each other. This test does not test the reuse of a
	 * once remove object.
	 */
	@Test
	public void testContiniousAddAndRemove() {
		TestObject container = addObject(null, false);
		model.root().getContents().add(container);
		
		Random random = new Random(0);
		int fragmentationDepth = 0;
		
		try {
			for (int i = 0; i < 100; i++) {
				if (container.eContainer() instanceof Root || random.nextBoolean()) {
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
				model.save(null);
				model.assertFragmentsIndex(0l, (long)fragmentationDepth + 1);
			}
			while (container.eContainer() instanceof TestObject) {
				TestObject newContainer = (TestObject)container.eContainer();
				removeObject(container);
				container = newContainer;
			}
		} catch (RuntimeException e) {
			System.out.println(dataStore);
			throw e;
		}

		Assert.assertNotNull(container.eResource());
		model.save(null);

		reinitializeModel();
		reinitializeModel();
		Assertions.root(model).eContents().assertSize(0);
		model.assertIdIndex(-1l, -1l);
	}
	
	@SuppressWarnings("unused")
	private void print(TestObject object2) {
		System.out.println(System.identityHashCode(object2));
		System.out.println(System.identityHashCode(((FObjectImpl)object2).fInternalObject()));
		System.out.println(((FObjectImpl)object2).fInternalObject().eResource().getURI());
		System.out.println(object2.eResource().getURI());
		System.out.println(object2.eContainer().eResource().getURI());
		System.out.println(object2.eContainingFeature().getName());
	}

	@Test
	public void testMoveFragmentRootToNonFragmentingReference() {
		model.root().getContents().add(object1);
		
		object1.getFragmentedContents().add(object2);						
		object1.getRegularContents().add(object2);		
		object2 = object1.getRegularContents().get(0);
		
		model.save(null);
		reinitializeModel();
		Assertions
				.root(model).assertId(1)
				.getFragmentedContents().assertSize(0)
				.getRegularContents().assertSize(1)
				.get(0).assertId(2).assertSameFragmentAsContainer();
		
		model.assertFragmentsIndex(0l, 1l);
		model.assertIdIndex(-1l, -1l);
	}

	@Test
	public void testMoveFragmentRootToOtherFragmentingReference() {
		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);		
		object1.getFragmentedContents().add(object3);
		model.save(null);
		
		reinitializeModel();
		Assertions
				.root(model).assertId(1).getFragmentedContents().assertSize(2)
				.get(0).assertId(2).assertDifferentFragmentAsContainer().getFragmentedContents().assertSize(0);
		Assertions
				.root(model).getFragmentedContents()
				.get(1).assertId(3).assertDifferentFragmentAsContainer()
				.eContainer().assertId(1);
		
		model.assertFragmentsIndex(0l, 3l);
		model.assertIdIndex(-1l, -1l);
	}

	@Test
	public void testMoveObjectToFragmentingReference() {
		model.root().getContents().add(object1);
		object1.getRegularContents().add(object2);
		object1.getFragmentedContents().add(object2);
		model.save(null);
		
		reinitializeModel();
		Assertions
				.root(model).assertId(1).getRegularContents().assertSize(0)
				.getFragmentedContents().assertSize(1).get(0).assertId(2).assertDifferentFragmentAsContainer()
				.eContainer().assertId(1);
		
		model.assertFragmentsIndex(0l, 2l);
		model.assertIdIndex(-1l, -1l);
	}

	@Test
	public void testMoveContainedObjectToAnotherFragment() {
		model.root().getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object1.getRegularContents().add(object3);
		object2.getRegularContents().add(object3);
		model.save(null);
		
		reinitializeModel();
		Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(0)
				.getFragmentedContents().assertSize(1)
				.get(0).assertId(2).assertDifferentFragmentAsContainer()
				.getRegularContents().assertSize(1).get(0).assertId(3).assertSameFragmentAsContainer()
				.eContainer().assertId(2);
		
		model.assertFragmentsIndex(0l, 2l);
		model.assertIdIndex(-1l, -1l);
	}
	
	@Test
	public void testEnums() {
		model.root().getContents().add(object1);
		try {
			object1.setEnumAttribute(TestEnum.LITERAL1);
			Assert.assertEquals("Wrong value.", TestEnum.LITERAL1, object1.getEnumAttribute());
			object1.setEnumAttribute(TestEnum.LITERAL2);
			Assert.assertEquals("Wrong value.", TestEnum.LITERAL2, object1.getEnumAttribute());
		} catch (ClassCastException e) {
			Assert.fail("Internally EMF-Fragments uses wrong enum types.");
		}
		
		model.save(null);
		reinitializeModel();
		Assert.assertEquals("Wrong value.", TestEnum.LITERAL2,Assertions.root(model).value().getEnumAttribute());
	}
}
