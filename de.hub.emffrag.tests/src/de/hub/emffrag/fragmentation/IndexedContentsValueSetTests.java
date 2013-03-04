package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class IndexedContentsValueSetTests extends IndexedReferenceValueSetTests {

	@Override
	protected EList<TestObject> valueSet() {
		return testObject.getIndexedContents();
	}
	
	@Override
	protected EStructuralFeature testFeature() {
		return TestModelPackage.eINSTANCE.getTestObject_IndexedContents();
	}
	
	@Override
	protected void assertExtrinsicIdIndex() {
		model.assertExtrinsicIdIndex(0l, 3l);
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
		assertExtrinsicIdIndex();
		model.assertValueSetIndex(testObject, testFeature(), 0, 3);
	}
	

}
