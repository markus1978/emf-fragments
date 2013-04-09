package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes;

public class IndexedContentsValueSetTests extends IndexedReferenceValueSetTests {

	@Override
	protected EList<TestObject> valueSet() {
		return ((TestObjectWithIndexes)testObject).getIndexedContents();
	}
	
	@Override
	protected EStructuralFeature testFeature() {
		return TestModelPackage.eINSTANCE.getTestObjectWithIndexes_IndexedContents();
	}
	
	@Override
	protected void assertIdIndex() {
		model.assertIdIndex(0l, 0l);
	}
	
	@Override
	protected void assertFragmentsIndex() {
		model.assertFragmentsIndex(0l, 1l);
	}
	
	@Override
	protected TestObject assertTestObject() {
		return Assertions.root(model).assertId(0).value();
	}
	
	@Test
	public void moveValueTest1() {
		root.getContents().add(testObject);
		root.getContents().add(testObject2);
		root.getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object1.getRegularContents().add(object3);
		valueSet().add(object1);
		valueSet().add(object2);
		valueSet().add(object3);
		((TestObjectWithIndexes)testObject2).getIndexedContents().add(object2);
									
		Assert.assertEquals(0, valueSet().indexOf(object1));
		Assert.assertEquals(-1, valueSet().indexOf(object2));
		Assert.assertEquals(2, valueSet().indexOf(object3));
		boolean exceptionThrown = false;
		try {
			valueSet().get(1);
		} catch (IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		Assert.assertTrue(exceptionThrown);
		Assert.assertEquals(0, ((TestObjectWithIndexes)testObject2).getIndexedContents().indexOf(object2));
		EmfFragActivator.instance.assertWarningsAndErrors(true);
	}
	
	@Test
	public void addTest() {
		root.getContents().add(testObject);
		testObject.getFragmentedContents().add(object1);
		testObject.getFragmentedContents().add(object2);
		testObject.getFragmentedContents().add(object3);
		valueSet().add(object1);	
		valueSet().add(object2);				
		valueSet().add(object3);
	
		model.save(null);		
		reinitializeModel();		
		testObject = Assertions.root(model).assertId(0).value();
		
		assertValueSet(valueSet(), 3);
		assertObjectInValueSet(valueSet(), 0);
		assertObjectInValueSet(valueSet(), 1);
		assertObjectInValueSet(valueSet(), 2);	
		
		assertFragmentsIndex();
		assertIdIndex();
		model.assertValueSetIndex(testObject, testFeature(), 0, 3);
	}
	

}
