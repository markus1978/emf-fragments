package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.junit.Test;

public class CrossReferenceTests extends AbstractFragmentationTests {

	@Test
	public void testAddCrossReference() {
		model.addContent(object1);
		object1.getRegularContents().add(object2);
		object1.getCrossReferences().add(object2);
		model.save();
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(0l, 0l);
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_RegularContents());
		Assert.assertTrue(object1.getCrossReferences().contains(object2));
	}
	
	@Test
	public void testAddCrossFragmentReference() {
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		object1.getCrossReferences().add(object3);
		
		model.save();
		reinitializeModel();	

		model.assertFragmentsIndex(0l, 2l);
		model.assertExtrinsicIdIndex(0l, 0l);
		object1 = assertHasModelRootFragment();
		Assert.assertEquals("testValue", object1.getCrossReferences().get(0).getName());
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		object3 = assertHasContents(object2, metaModel.getTestObject_FragmentedContents());
		Assert.assertTrue(object1.getCrossReferences().contains(object3));
	}
	
	@Test
	public void testRemoveCrossReference() {
		model.addContent(object1);
		object1.getRegularContents().add(object2);
		object1.getCrossReferences().add(object2);
		object1.getCrossReferences().clear();
		model.save();
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(0l, 0l);
		object1 = assertHasModelRootFragment();
		object2 = assertHasContents(object1, metaModel.getTestObject_RegularContents());
		Assert.assertTrue(object1.getCrossReferences().isEmpty());
	}
	
	@Test
	public void testMoveCrossReferenceWithInFragment() {
		model.addContent(object1);
		object1.getRegularContents().add(object2);
		object2.getRegularContents().add(object3);
		object1.getCrossReferences().add(object3);
		object1.getRegularContents().add(object3);
		object1.getRegularContents().remove(object2);
		model.save();
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 0l);
		model.assertExtrinsicIdIndex(0l, 0l);
		object1 = assertHasModelRootFragment();
		Assert.assertEquals("testValue", object1.getCrossReferences().get(0).getName());
		object2 = assertHasContents(object1, metaModel.getTestObject_RegularContents());
		Assert.assertTrue(object1.getCrossReferences().contains(object2));
	}
	
	@Test
	public void testMoveCrossReferenceOverFragments() {
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		object1.getCrossReferences().add(object3);
		object1.getFragmentedContents().add(object3);
		object1.getFragmentedContents().remove(object2);
		model.save();
		
		reinitializeModel();		
		model.assertFragmentsIndex(0l, 2l);
		model.assertExtrinsicIdIndex(0l, 0l);
		object1 = assertHasModelRootFragment();
		Assert.assertEquals("testValue", object1.getCrossReferences().get(0).getName());
		object2 = assertHasContents(object1, metaModel.getTestObject_FragmentedContents());
		Assert.assertTrue(object1.getCrossReferences().contains(object2));
	}
}
