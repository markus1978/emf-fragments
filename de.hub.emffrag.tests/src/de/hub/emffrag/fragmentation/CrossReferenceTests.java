package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class CrossReferenceTests extends AbstractFragmentationTests {
	
	@Test
	public void testIdPersitence() {
		root.getContents().add(object1);
		String id = ((IndexBasedIdSemantics)EmfFragActivator.instance.idSemantics).getId(((FObjectImpl)object1).fInternalObject(), true);
		
		model.save(null);
		reinitializeModel();
		
		TestObject value = Assertions.root(model).assertId(1).value();
		String newId = ((IndexBasedIdSemantics)EmfFragActivator.instance.idSemantics).getId(((FObjectImpl)value).fInternalObject(), false);
		Assert.assertNotNull("ID not persisted.", newId);
		Assert.assertEquals("Wrong ID.", id, newId);
	}

	@Test
	public void testAddCrossReference() {
		root.getContents().add(object1);
		object1.getRegularContents().add(object2);
		object1.getCrossReferences().add(object2);
		model.save(null);
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 1l);
		model.assertIdIndex(0l, 0l);
		Assertions
				.root(model).assertId(1)
				.getRegularContents().assertSize(1).get(0).assertId(2).save()
				.eContainer().assertId(1).getCrossReferences().assertSize(1).assertContains()
				.get(0).assertId(2);
	}
	
	@Test
	public void testAddCrossFragmentReference() {
		root.getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		object1.getCrossReferences().add(object3);
		
		model.save(null);
		reinitializeModel();	

		model.assertFragmentsIndex(0l, 3l);
		model.assertIdIndex(0l, 0l);
		
		Assertions
				.root(model).assertId(1).save().getCrossReferences().assertSize(1).get(0).assertId(3)
				.load().getFragmentedContents().assertSize(1).get(0).getFragmentedContents().assertSize(1).get(0).assertId(3);
	}
	
	@Test
	public void testRemoveCrossReference() {
		root.getContents().add(object1);
		object1.getRegularContents().add(object2);
		object1.getCrossReferences().add(object2);
		object1.getCrossReferences().clear();
		model.save(null);
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 1l);
		model.assertIdIndex(0l, 0l);
		
		Assertions
				.root(model).assertId(1)
				.getCrossReferences().assertSize(0)
				.getRegularContents().assertSize(1).get(0).assertId(2);
	}
	
	@Test
	public void testMoveCrossReferenceWithInFragment() {
		root.getContents().add(object1);
		object1.getRegularContents().add(object2);
		object2.getRegularContents().add(object3);
		object1.getCrossReferences().add(object3);
		object1.getRegularContents().add(object3);
		object1.getRegularContents().remove(object2);
		model.save(null);
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 1l);
		model.assertIdIndex(0l, 0l);
		Assertions
				.root(model).assertId(1).save().getRegularContents().assertSize(1).get(0).assertId(3)
				.load().getCrossReferences().assertSize(1).get(0).assertId(3);
	}
	
	@Test
	public void testMoveCrossReferenceOverFragments() {
		root.getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		object1.getCrossReferences().add(object3);
		object1.getFragmentedContents().add(object3);
		object1.getFragmentedContents().remove(object2);
		model.save(null);
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 3l);
		model.assertIdIndex(0l, 0l);
		Assertions
				.root(model).assertId(1).save().getFragmentedContents().assertSize(1).get(0).assertId(3)
				.load().getCrossReferences().assertSize(1).get(0).assertId(3);
		
	}
	
	@Test
	public void testIncrementalFragmentChange() {
		model.root().getContents().add(object1);
		
		object1.getRegularContents().add(object2);
		object2.getRegularContents().add(object3);
		object1.getCrossReferences().add(object3);
		object1.getFragmentedContents().add(object2);

		TestObject object3New = Assertions
			.root(model).assertId(1).getCrossReferences().assertSize(1)
			.get(0).assertId(3).value();
		Assert.assertEquals(object3, object3New);
		
		model.save(null);
		reinitializeModel();
		
		Assertions
				.root(model).assertId(1).getCrossReferences().assertSize(1)
				.get(0).assertId(3);
	}

}
