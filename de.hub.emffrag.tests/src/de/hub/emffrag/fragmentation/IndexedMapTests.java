package de.hub.emffrag.fragmentation;

import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex;

public class IndexedMapTests extends AbstractFragmentationTests {

	@Test
	public void simpleTest() {
		TestObjectIndex testIndex = TestModelFactory.eINSTANCE.createTestObjectIndex();
		model.addContent(testIndex);
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object1.getRegularContents().add(object3);
		testIndex.put("1", object1);
		testIndex.put("2", object2);
		testIndex.put("3", object3);
		model.save();
		
		System.out.println(dataStore.toString());
		
		reinitializeModel();
		testIndex = assertHasModelRootFragment(0);
		Assert.assertNotNull("Value is null", testIndex.exact("1"));
		Assert.assertNotNull("Value is null", testIndex.exact("2"));
		Assert.assertNotNull("Value is null", testIndex.exact("3"));
	}
	
	protected TestObjectIndex assertHasModelRootFragment(int index) {
		Assert.assertTrue(model.getRootContents().size() > index);
		Assert.assertTrue(model.getRootContents().get(index) instanceof TestObjectIndex);
		TestObjectIndex contents = (TestObjectIndex) model.getRootContents().get(index);
		Assert.assertTrue(contents.eResource() instanceof Fragment);
		return contents;
	}
}
