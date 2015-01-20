package de.hub.emffrag.fragmentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.FragmentsCache;
import de.hub.emffrag.fragmentation.FragmentsCache.FragmentsCacheListener;
import de.hub.emffrag.testmodels.AbstractTestModelTests;
import de.hub.emffrag.testmodels.fobject.testmodel.TestObject;
import de.hub.emffrag.testmodels.fobject.testmodel.fobject.meta.TestModelPackage;

@FixMethodOrder(MethodSorters.JVM)
public class FragmentsCacheTests extends AbstractTestModelTests<TestObject, TestModelPackage> {

	protected static  TestModelPackage tmPackage = TestModelPackage.eINSTANCE;
	
	@Override
	protected TestModelPackage testModelPackage() {
		return TestModelPackage.eINSTANCE;
	}

	private List<Fragment> removedFragments = new ArrayList<Fragment>();
	private FragmentsCacheListener fragmentsCacheListener = new FragmentsCacheListener() {			
		@Override
		public void onRemoval(Fragment fragment, boolean explicitly) {
			removedFragments.add(fragment);
		}
	};
	
	@Before
	public void init() {
		removedFragments.clear();
	}
	
	@Test
	public void lockAddSingleTest() {		
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		
		cache.add(new Fragment(URI.createURI("test/1"), 1));
		Assert.assertEquals(0, removedFragments.size());
		cache.lock();
		
		cache.add(new Fragment(URI.createURI("test/2"), 2));
		Assert.assertEquals(0, removedFragments.size());
		
		cache.unlock();
		Assert.assertEquals(1, removedFragments.size());
		Assert.assertNotNull(removedFragments.get(0));
		Assert.assertEquals(1, removedFragments.get(0).fFragmentId());
	}
	
	@Test
	public void notLockAddSingleTest() {		
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		
		cache.add(new Fragment(URI.createURI("test/1"), 1));
		Assert.assertEquals(0, removedFragments.size());
		
		cache.add(new Fragment(URI.createURI("test/2"), 2));
		Assert.assertEquals(1, removedFragments.size());
		Assert.assertNotNull(removedFragments.get(0));
		Assert.assertEquals(1, removedFragments.get(0).fFragmentId());
	}
	
	@Test
	public void lockAddMultipleTest() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		
		cache.add(new Fragment(URI.createURI("test/0"), 0));
		Assert.assertEquals(0, removedFragments.size());
		cache.lock();
		
		for (int i = 1; i < 10; i++) {
			cache.add(new Fragment(URI.createURI("test/" + i), i));
			Assert.assertEquals(0, removedFragments.size());			
		}
		
		cache.unlock();
		Assert.assertEquals(9, removedFragments.size());
		for (int i = 0; i < 9; i++) {
			Assert.assertEquals(i, removedFragments.get(i).fFragmentId());
		}
	}
	
	@Test
	public void lockEmptyAddMultipleTest() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		
		cache.lock();
		
		for (int i = 0; i < 10; i++) {
			cache.add(new Fragment(URI.createURI("test/" + i), i));
			Assert.assertEquals(0, removedFragments.size());			
		}
		
		cache.unlock();
		Assert.assertEquals(9, removedFragments.size());
		for (int i = 0; i < 9; i++) {
			Assert.assertEquals(i, removedFragments.get(i).fFragmentId());
		}
	}
	
	@Test
	public void lockRemoveSingleTest() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		Fragment fragment = new Fragment(URI.createURI("test/0" ), 0);
		cache.add(fragment);
		cache.lock();
		cache.remove(fragment, true);		
		// Fragments is removed, locked or not locked. This is expected behavior.
		Assert.assertEquals(1, removedFragments.size());
		Assert.assertEquals(0, removedFragments.get(0).fFragmentId());
		cache.unlock();
		Assert.assertEquals(1, removedFragments.size());
		Assert.assertEquals(0, removedFragments.get(0).fFragmentId());
	}
	
	@Test
	public void refillCacheAfterLockedAddAndExplicitRemoveTest() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		
		Fragment fragment = new Fragment(URI.createURI("test/0" ), 0);
		cache.add(fragment);
		cache.lock();
		
		fragment = new Fragment(URI.createURI("test/1"), 1); 
		cache.add(fragment);
		cache.remove(fragment, true);
				
		Assert.assertEquals(1, removedFragments.size());
		Assert.assertEquals(1, removedFragments.get(0).fFragmentId());
		cache.unlock();
		
		removedFragments.clear();
		Assert.assertEquals(0, removedFragments.size());
	}
	
	@Test
	public void nestedLocksTest() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		cache.lock();
		cache.lock();
		
		Assert.assertTrue(cache.isLocked());
		
		cache.unlock();
		Assert.assertTrue(cache.isLocked());
		
		cache.unlock();
		Assert.assertFalse(cache.isLocked());
		
		try {
			cache.unlock();
		} catch (Exception e) {
			return;
		}
		
		Assert.fail("Exception expected.");
	}
	
	@Test
	public void unlockUnlockedCacheTest() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		
		try {
			cache.unlock();
		} catch (Exception e) {
			return;
		}
		
		Assert.fail("Exception expected.");
	}
	
	@Test
	public void removeNotExistingElement() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		Fragment fragment = new Fragment(URI.createURI("test/0" ), 0);
		
		try {
			cache.remove(fragment, true);
		} catch (Exception e) {
			return;
		}
		
		Assert.fail("Exception expected.");
	}
	
	@Test
	public void removeNotExistingElementNotStrict() {
		FragmentsCache cache = new FragmentsCache(fragmentsCacheListener, 1);
		Fragment fragment = new Fragment(URI.createURI("test/0" ), 0);
		
		cache.remove(fragment, false);				
	}
	
}
