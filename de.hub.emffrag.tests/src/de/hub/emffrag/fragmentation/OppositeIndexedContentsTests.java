package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class OppositeIndexedContentsTests extends OppositeFragmentedContentsTests {

	@Override
	protected EReference testedReference() {
		return TestModelPackage.eINSTANCE.getTestObject_IndexedContents();
	}

	@Test
	@Override
	public void testInverseRemoveOpposite() {
		// this is not supported for indexed sets
	}
}
