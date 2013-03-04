package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class OppositeIndexedReferencesTests extends OppositeFragmentedContentsTests {

	@Override
	protected EReference testedReference() {
		return TestModelPackage.eINSTANCE.getTestObjectWithIndexes_IndexedReferences();
	}

	@Test
	@Override
	public void testInverseRemoveOpposite() {
		// this is not supported for indexed sets
	}

	@Override
	protected void addObjectsToModel() {
		super.addObjectsToModel();
		object1.getRegularContents().add(object2);
		object1.getRegularContents().add(object3);
	}
	
	
}
