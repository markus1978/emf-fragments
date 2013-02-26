package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class UserObjectCacheTests extends AbstractTests {

	@Test
	public void testUserObjectCache() {
		UserObjectsCache cache = new UserObjectsCache();
		TestObject object = TestModelFactory.eINSTANCE.createTestObject();
		FInternalObjectImpl internalObject = ((FObjectImpl)object).fInternalObject();
		cache.addUserObjectToCache(internalObject, (FObjectImpl)object);
		Assert.assertNotNull(cache.getUserObject(internalObject));
		cache.removeCachedUserObject(internalObject);
		Assert.assertFalse(cache.hasReferences());
	}
}
