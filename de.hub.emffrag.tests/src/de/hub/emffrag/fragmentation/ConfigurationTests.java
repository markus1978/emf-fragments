package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.EmfFragActivator.IndexedValueSetBahaviour;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes;

public class ConfigurationTests extends AbstractFragmentationTests {
	
	@Before
	public void ensureDefaultConfiguration() {
		EmfFragActivator.instance.indexedValueSetBahaviour = IndexedValueSetBahaviour.strict;
		EmfFragActivator.instance.idSemantics = new IndexBasedIdSemantics(IdBehaviour.strict);
		EmfFragActivator.instance.defaultModel = null;
	}
	
	@Override
	protected TestObject createTestObject(int id) {
		return Assertions.createTestObjectWithIndexes(id);
	}

	@Test
	public void testNeverContainsBehavior() {
		EmfFragActivator.instance.indexedValueSetBahaviour = IndexedValueSetBahaviour.neverContains;
		model.root().getContents().add(object1);
		try {
			Assert.assertFalse("Wrong result.", ((TestObjectWithIndexes)object1).getIndexedReferences().contains("something"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown: " + e.getClass().getCanonicalName());
		}
	}
	
	@Test
	public void testStrictContainsBehavior() {
		model.root().getContents().add(object1);
		try {
			 ((TestObjectWithIndexes)object1).getIndexedReferences().contains("something");
		} catch (UnsupportedOperationException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Wrong exception thrown: " + e.getClass().getCanonicalName());
		}
		Assert.fail("Exception not thrown");
	}
	
	@Test
	public void testStrictIdBehavior() {
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
		} catch (NotInAFragmentedModelException e) {
			return;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Wrong exception thrown: " + e.getClass().getCanonicalName());
		}
		Assert.fail("Exception not thrown");
	}
	
	@Test
	public void testPreliminaryIdBehavior() {
		EmfFragActivator.instance.idSemantics = new IndexBasedIdSemantics(IdBehaviour.preliminary);
		
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
			FInternalObjectImpl fInternalObject = ((FObjectImpl)object2).fInternalObject();
			((IndexBasedIdSemantics)EmfFragActivator.instance.idSemantics).assertHasPreliminary(fInternalObject);
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
	public void testDefaultModelIdBehavior1() {
		EmfFragActivator.instance.idSemantics = new IndexBasedIdSemantics(IdBehaviour.defaultModel);
		EmfFragActivator.instance.defaultModel = model;
		
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
			FInternalObjectImpl fInternalObject = ((FObjectImpl)object2).fInternalObject();
			((IndexBasedIdSemantics)EmfFragActivator.instance.idSemantics).assertHasNotPreliminary(fInternalObject);
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
	public void testDefaultModelIdBehavior2() {
		EmfFragActivator.instance.idSemantics = new IndexBasedIdSemantics(IdBehaviour.defaultModel);
		EmfFragActivator.instance.defaultModel = model;
		
		model.root().getContents().add(object1);		
		try {
			object1.getCrossReferences().add(object2);
			FInternalObjectImpl fInternalObject = ((FObjectImpl)object2).fInternalObject();
			((IndexBasedIdSemantics)EmfFragActivator.instance.idSemantics).assertHasNotPreliminary(fInternalObject);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getClass().getCanonicalName());
		}
		object1.getRegularContents().add(object2);
		
		model.save(null);
		reinitializeModel();
		
		Assertions.root(model).getCrossReferences().assertSize(1).get(0).assertId(2);
		Assertions.root(model).getRegularContents().assertSize(1).get(0).assertId(2);
	}
}
