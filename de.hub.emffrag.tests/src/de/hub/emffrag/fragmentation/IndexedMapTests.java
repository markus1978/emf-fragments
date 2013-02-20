package de.hub.emffrag.fragmentation;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex;

public class IndexedMapTests extends AbstractFragmentationTests {
	
	private TestObjectIndex testIndex;
	
	@Before
	@Override
	public void standardInitialization() {
		super.standardInitialization();
		testIndex = TestModelFactory.eINSTANCE.createTestObjectIndex();
	}

	@Test
	public void addObjectsToMapTest() {
		model.addContent(testIndex);
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object1.getRegularContents().add(object3);
		testIndex.put("1", object1);
		testIndex.put("2", object2);
		testIndex.put("3", object3);
		model.save();
		
		reinitializeModel();
		
		testIndex = assertHasModelRootFragment(0);
		assertIndex(testIndex, "1", "3");
		assertIndexedObject(testIndex, "1");
		assertIndexedObject(testIndex, "2");
		assertIndexedObject(testIndex, "3");		
	}
	
	@Test
	public void iteratorTest() {
		addObjectsToMapTest();
		
		Iterator<TestObject> iterator = testIndex.iterator();
		assertIterator(iterator, 3);
	}
	
	@Test
	public void removeObjectsFromMapTest() {
		addObjectsToMapTest();		
		testIndex.remove("2");
		assertIndexedObject(testIndex, "1");
		Assert.assertNull("Object is not null.", testIndex.exact("2"));
		assertIndexedObject(testIndex, "3");
		assertIterator(testIndex.iterator(), 2);			
	}
	
	@Test
	public void nextValueTests1() {
		addObjectsToMapTest();
		object1 = testIndex.next("1");
		assertObject(object1);
		Assert.assertEquals("Next gives wrong object.", testIndex.exact("1"), object1);
	}
	
	@Test
	public void nextValueTests2() {
		addObjectsToMapTest();
		object1 = testIndex.next("0");
		assertObject(object1);
		Assert.assertEquals("Next gives wrong object.", testIndex.exact("1"), object1);
	}
	
	@Test
	public void nextValueTests3() {
		removeObjectsFromMapTest();
		object1 = testIndex.next("2");
		assertObject(object1);
		Assert.assertEquals("Next gives wrong object.", testIndex.exact("3"), object1);
	}
	
	@Test
	public void nextValueTests4() {
		addObjectsToMapTest();
		object1 = testIndex.next("4");
		Assert.assertNull("Value that should not exist is there.", object1);
	}
	
	@Test
	public void putValueTest1() {
		performPutValueTest("4", "1", "4", 4);
	}
	
	@Test
	public void putValueTest2() {
		performPutValueTest("0", "0", "3", 4);
	}
	
	@Test
	public void putValueTest3() {
		performPutValueTest("1", "1", "3", 3);
	}
	
	@Test
	public void putValueTest4() {
		performPutValueTest("1a", "1", "3", 4);
	}
	
	private void performPutValueTest(String key, String firstKey, String lastKey, int size) {
		addObjectsToMapTest();
		
		TestObject testObject = createTestObject();
		object1 = testIndex.exact("1");
		object1.getRegularContents().add(testObject);
		testIndex.put(key, testObject);
		
		model.save();
		reinitializeModel();
		
		testIndex = assertHasModelRootFragment(0);
		assertIndex(testIndex, firstKey, lastKey);
		assertObject(testIndex.exact(key));
		assertIterator(testIndex.iterator(), size);
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
	
	protected TestObjectIndex assertHasModelRootFragment(int index) {
		Assert.assertTrue(model.getRootContents().size() > index);
		Assert.assertTrue(model.getRootContents().get(index) instanceof TestObjectIndex);
		TestObjectIndex contents = (TestObjectIndex) model.getRootContents().get(index);
		Assert.assertTrue(contents.eResource() instanceof Fragment);
		return contents;
	}
	
	protected void assertObject(Object value) {
		Assert.assertNotNull("Value is null.", value);
		Assert.assertTrue("Value has wrong type.", value instanceof TestObject);
		Assert.assertTrue("Value is broken", ((TestObject)value).getName().equals("testValue"));
	}
	
	protected void assertIndexedObject(TestObjectIndex index, String key) {
		TestObject value = index.exact(key);
		assertObject(value);
	}
	
	protected void assertIndex(TestObjectIndex index, String firstKey, String lastKey) {
		Assert.assertNotNull("Index is null.", index);
		Assert.assertEquals("Wrong first key.", firstKey, index.getFirstKey());
		Assert.assertEquals("Wrong last key.", lastKey, index.getLastKey());
	}
}
