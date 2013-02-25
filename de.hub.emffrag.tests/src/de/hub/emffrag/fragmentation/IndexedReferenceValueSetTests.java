package de.hub.emffrag.fragmentation;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class IndexedReferenceValueSetTests extends AbstractFragmentationTests {
	
	protected TestObject testObject;
	
	@Before
	public void indexInitialization() {
		testObject = Assertions.createTestObject(0);
	}
	
	@Test
	public void emptySetTest() {
		root.getContents().add(testObject);
		assertValueSet(valueSet(), 0);
		
		model.save();
		reinitializeModel();
		testObject = Assertions.root(model).assertId(0).value();
		
		assertValueSet(valueSet(), 0);
		model.assertFragmentsIndex(0l, 1l);
		model.assertExtrinsicIdIndex(0l, 0l);
		model.assertValueSetIndex(testObject, testFeature(), -1, -1);
	}
	

	@Test
	public void addTest() {
		root.getContents().add(testObject);
		root.getContents().add(object1);
		object1.getFragmentedContents().add(object2);
		object1.getRegularContents().add(object3);
		valueSet().add(object1);
		valueSet().add(object2);
		valueSet().add(object3);
		
		model.save();	
		reinitializeModel();
		testObject = assertTestObject();
		
		assertValueSet(valueSet(), 3);
		assertObjectInValueSet(valueSet(), 0);
		assertObjectInValueSet(valueSet(), 1);
		assertObjectInValueSet(valueSet(), 2);		
		
		assertFragmentsIndex();
		assertExtrinsicIdIndex();
		model.assertValueSetIndex(testObject, testFeature(), 0l, 2l);
	}
	
	protected EList<TestObject> valueSet() {
		return testObject.getIndexedReferences();
	}
	
	protected EStructuralFeature testFeature() {
		return TestModelPackage.eINSTANCE.getTestObject_IndexedReferences();
	}
	
	@Test
	public void iteratorTest() {
		addTest();
		
		Iterator<TestObject> iterator = valueSet().iterator();
		assertIterator(iterator, 3);
	}
	
	@Test
	public void boundedIteratorTest1() {
		performBoundedIteratorTest(0, 3);
	}
	
	@Test
	public void boundedIteratorTest2() {
		performBoundedIteratorTest(1, 2);
	}
	
	@Test
	public void boundedIteratorTest3() {
		performBoundedIteratorTest(2, 1);
	}
	
	@Test
	public void boundedIteratorTest4() {
		performBoundedIteratorTest(3, 0);
	}
		
	private void performBoundedIteratorTest(int first, int size) {
		addTest();
		Iterator<TestObject> iterator = valueSet().listIterator(first);
		assertIterator(iterator, size);
	}
	
	@Test
	public void removeTest() {
		addTest();		
		Assert.assertEquals(3, valueSet().size());
		valueSet().remove(2);
		
		model.save();
		reinitializeModel();
		testObject = assertTestObject();
		
		assertObjectInValueSet(valueSet(), 1);
		TestObject object = null;
		try {
			object = valueSet().get(2);
		} catch (IndexOutOfBoundsException e) {			
		}
		Assert.assertNull("Object is not null.", object);
		Assert.assertEquals(2, valueSet().size());
		assertIterator(valueSet().iterator(), 2);	
		assertFragmentsIndex();
		assertExtrinsicIdIndex();
		model.assertValueSetIndex(testObject, testFeature(), 0l, 1l);
	}

	protected void assertFragmentsIndex() {
		model.assertFragmentsIndex(0l, 3l);
	}
	
	@Test
	public void setTest() {
		addTest();
		valueSet().set(1, valueSet().get(1));
		
		model.save();
		reinitializeModel();
		testObject = assertTestObject();
		
		assertObjectInValueSet(valueSet(), 1);
		assertIterator(valueSet().iterator(), 3);
		assertFragmentsIndex();
		assertExtrinsicIdIndex();
		model.assertValueSetIndex(testObject, testFeature(), 0l, 2l);
	}

	protected void assertExtrinsicIdIndex() {
		model.assertExtrinsicIdIndex(0l, 3l);
	}
		
	protected void assertIterator(Iterator<TestObject> iterator, int size) {
		Assert.assertTrue("Iterator is emtpy.", iterator.hasNext() || size == 0);
		int i = 0;
		while (iterator.hasNext()) {
			assertObject(iterator.next());
			i++;
		}
		Assert.assertEquals("Iterator has the wrong size.", size, i);
	}
	
	protected void assertObject(Object value) {
		Assert.assertNotNull("Value is null.", value);
		Assert.assertTrue("Value has wrong type.", value instanceof TestObject);
		Assert.assertTrue("Value is broken", ((TestObject)value).getName().startsWith("testValue"));
	}
	
	protected void assertObjectInValueSet(EList<TestObject> valueSet, int index) {
		TestObject value = valueSet.get(index);
		assertObject(value);
	}
	
	protected void assertValueSet(EList<TestObject> valueSet, int size) {
		Assert.assertNotNull("Index is null.", valueSet);
		Assert.assertEquals("Wrong size", size, valueSet.size());
	}
	
	protected TestObject assertTestObject() {
		return Assertions.root(model, 2, 0).assertId(0).value();
	}
}
