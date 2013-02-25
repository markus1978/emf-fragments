package de.hub.emffrag.fragmentation;

import org.junit.Before;
import org.junit.Test;

import de.hub.emffrag.datastore.StringKeyType;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;

public class ContainmentIndexedMapTests extends IndexedMapTests {

	@Before
	public void indexInitialization() {
		testIndex = TestModelFactory.eINSTANCE.createTestContainmentIndex();
	}	
	
	@Override
	protected void assertFragmentsIndex() {
		model.assertFragmentsIndex(0l, 0l);
	}
	
	@Override
	protected void assertExtrinsicIdIndex(boolean plusOne) {
		model.assertExtrinsicIdIndex(0l, 0l);
	}

	@Test
	public void addObjectsToMapTest() {
		model.addContent(testIndex);
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
		
		assertFragmentsIndex();
		assertExtrinsicIdIndex(false);
		model.assertIndexClassIndex(testIndex, "1", "3", StringKeyType.instance);
	}
}
