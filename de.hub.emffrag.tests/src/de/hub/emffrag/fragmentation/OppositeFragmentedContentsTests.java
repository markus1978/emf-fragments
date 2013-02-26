package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class OppositeFragmentedContentsTests extends AbstractFragmentationTests {

	@Override
	protected TestModelPackage getMetaModel() {		
		testedReference().setEOpposite(TestModelPackage.eINSTANCE.getTestObject_CrossReferences());
		TestModelPackage.eINSTANCE.getTestObject_CrossReferences().setEOpposite(testedReference());
		return TestModelPackage.eINSTANCE;
	}
	
	protected EReference testedReference() {
		return TestModelPackage.eINSTANCE.getTestObject_FragmentedContents();
	}
	
	protected void addObjectsToModel() {
		root.getContents().add(object1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddOpposite() {
		addObjectsToModel();
		((EList<TestObject>)object1.eGet(testedReference())).add(object2);
		Assertions.root(model).get(testedReference()).assertSize(1).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
		
		model.save(null);
		reinitializeModel();
		Assertions.root(model).get(testedReference()).assertSize(1).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
	}
	
	@Test
	public void testAddReverseOpposite() {
		addObjectsToModel();
		object2.getCrossReferences().add(object1);
		Assertions.root(model).get(testedReference()).assertSize(1).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
		
		model.save(null);
		reinitializeModel();
		Assertions.root(model).get(testedReference()).assertSize(1).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddMultipleOpposite() {
		addObjectsToModel();
		((EList<TestObject>)object1.eGet(testedReference())).add(object2);
		((EList<TestObject>)object1.eGet(testedReference())).add(object3);
		Assertions.root(model).get(testedReference()).assertSize(2).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
		Assertions.root(model).get(testedReference()).assertSize(2).get(1).assertId(3).getCrossReferences().assertSize(1).get(0).assertId(1);
		
		model.save(null);
		reinitializeModel();
		Assertions.root(model).get(testedReference()).assertSize(2).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
		Assertions.root(model).get(testedReference()).assertSize(2).get(1).assertId(3).getCrossReferences().assertSize(1).get(0).assertId(1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRemoveOpposite() {
		addObjectsToModel();
		((EList<TestObject>)object1.eGet(testedReference())).add(object2);
		Assertions.root(model).get(testedReference()).assertSize(1).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
		
		((EList<TestObject>)object1.eGet(testedReference())).remove(0);
		Assertions.context(object2).getCrossReferences().assertSize(0);
		
		model.save(null);
		reinitializeModel();		
		Assertions.root(model).get(testedReference()).assertSize(0);
		model.assertFragmentsIndex(0l, 1l);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInverseRemoveOpposite() {
		addObjectsToModel();
		((EList<TestObject>)object1.eGet(testedReference())).add(object2);
		Assertions.root(model).get(testedReference()).assertSize(1).get(0).assertId(2).getCrossReferences().assertSize(1).get(0).assertId(1);
		
		object2.getCrossReferences().remove(0);
		Assertions.context(object2).getCrossReferences().assertSize(0);
		
		model.save(null);
		reinitializeModel();		
		Assertions.root(model).get(testedReference()).assertSize(0);
		model.assertFragmentsIndex(0l, 1l);
	}
}
