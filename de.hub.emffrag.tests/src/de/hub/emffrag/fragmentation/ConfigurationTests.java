package de.hub.emffrag.fragmentation;

import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.EmfFragActivator.ExtrinsicIdBehaviour;
import de.hub.emffrag.EmfFragActivator.IndexedValueSetBahaviour;

import junit.framework.Assert;

public class ConfigurationTests extends AbstractFragmentationTests {
	
	@Before
	public void ensureDefaultConfiguration() {
		EmfFragActivator.instance.indexedValueSetBahaviour = IndexedValueSetBahaviour.strict;
		EmfFragActivator.instance.extrinsicIdBehaviour = ExtrinsicIdBehaviour.strict;
		EmfFragActivator.instance.defaultModelForExtrinsicIdBehavior = null;
	}

	@Test
	public void testNeverContainsBehavior() {
		EmfFragActivator.instance.indexedValueSetBahaviour = IndexedValueSetBahaviour.neverContains;
		model.root().getContents().add(object1);
		try {
			Assert.assertFalse("Wrong result.", object1.getIndexedReferences().contains("something"));
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getClass().getCanonicalName());
		}
	}
	
	@Test
	public void testStrictContainsBehavior() {
		model.root().getContents().add(object1);
		try {
			object1.getIndexedReferences().contains("something");
		} catch (UnsupportedOperationException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Wrong exception thrown: " + e.getClass().getCanonicalName());
		}
		Assert.fail("Exception not thrown");
	}
	
	@Test
	public void testStrictExtrinsicIdBehavior() {
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
		} catch (NotInAFragmentedModelException e) {
			return;
		} catch (Exception e) {
			Assert.fail("Wrong exception thrown: " + e.getClass().getCanonicalName());
		}
		Assert.fail("Exception not thrown");
	}
	
	@Test
	public void testPreliminaryExtrinsicIdBehavior() {
		EmfFragActivator.instance.extrinsicIdBehaviour = ExtrinsicIdBehaviour.preliminary;
		
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
			Assert.assertTrue("Object has not a preliminary id.", FInternalObjectImpl.isPreliminary(((FObjectImpl)object2).fInternalObject().getExtrinsicID(false)));
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getClass().getCanonicalName());
		}
		object1.getFragmentedContents().add(object2);
		
		model.save(null);
		reinitializeModel();
		
		Assertions.root(model).getCrossReferences().assertSize(1).get(0).assertId(2);
		Assertions.root(model).getFragmentedContents().assertSize(1).get(0).assertId(2);
	}
	
	@Test
	public void testDefaultModelExtrinsicIdBehavior() {
		EmfFragActivator.instance.extrinsicIdBehaviour = ExtrinsicIdBehaviour.defaultModel;
		EmfFragActivator.instance.defaultModelForExtrinsicIdBehavior = model;
		
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
			boolean preliminaryId = FInternalObjectImpl.isPreliminary(((FObjectImpl)object2).fInternalObject().getExtrinsicID(false));
			Assert.assertNotNull("Id is null.", preliminaryId);
			Assert.assertFalse("Object has a preliminary id.", preliminaryId);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getClass().getCanonicalName());
		}
		object1.getFragmentedContents().add(object2);
		
		model.save(null);
		reinitializeModel();
		
		Assertions.root(model).getCrossReferences().assertSize(1).get(0).assertId(2);
		Assertions.root(model).getFragmentedContents().assertSize(1).get(0).assertId(2);
	}
}
