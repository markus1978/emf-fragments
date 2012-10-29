package de.hub.emffrag.test;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.fragmentation.FragmentedModel;

public class CacheTests extends AbstractFragmentationTests {
	
	@Override
	protected boolean doInitializeModel() {
		return false;
	}

	@Test
	public void testUnloading() throws Exception {
		model = new FragmentedModel(dataStore, null, 1, metaModel);
		rootFragmentURI = model.getRootFragmentURI();
		
		model.addContent(object1);
		object1.getFragmentedContents().add(object2);
		object2.getFragmentedContents().add(object3);
		Assert.assertEquals(3, model.numberOfLoadedFragments());
		
		object2 = null;
		object3 = null;
		System.gc();
		Thread.sleep(100);
		model.purgeCache();
		Assert.assertEquals(2, model.numberOfLoadedFragments());
	}
	
	@Test
	public void testAutomatedUnloading() {
		
	}
	
	@Test
	public void testCacheLimits() {
		
	}
}
