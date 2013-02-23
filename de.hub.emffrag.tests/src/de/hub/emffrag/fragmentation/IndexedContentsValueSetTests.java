package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class IndexedContentsValueSetTests extends IndexedReferenceValueSetTests {

	@Override
	protected EList<TestObject> valueSet() {
		return testObject.getIndexedContents();
	}
	
	@Test
	public void addTest() {
		model.addContent(testObject);
		testObject.getFragmentedContents().add(object1);
		testObject.getFragmentedContents().add(object2);
		testObject.getFragmentedContents().add(object3);
		valueSet().add(object1);	
		valueSet().add(object2);				
		valueSet().add(object3);
	
		model.save();		
		reinitializeModel();		
		testObject = assertHasModelRootFragment(0);
		
		assertValueSet(valueSet(), 3);
		assertObjectInValueSet(valueSet(), 0);
		assertObjectInValueSet(valueSet(), 1);
		assertObjectInValueSet(valueSet(), 2);		
	}
	

}
