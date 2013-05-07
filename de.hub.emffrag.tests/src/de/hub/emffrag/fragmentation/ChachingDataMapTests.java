package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.datastore.StringKeyType;
import de.hub.emffrag.datastore2.AbstractScanningDataStore;
import de.hub.emffrag.datastore2.IDataSortedMap;
import de.hub.emffrag.datastore2.InMemoryDataStore;

public class ChachingDataMapTests extends AbstractFragmentationTests {

	@Test
	public void simpleTest() {
		InMemoryDataStore dataStore = new InMemoryDataStore(URI.createURI("memory://localhost/test"), false);
		IDataSortedMap<String> map = dataStore.getMap("test".getBytes(), StringKeyType.instance);
		map.add("a");
		map.set("a", "testValue");
	}
	
	@Test
	public void testManualSave() {
		InMemoryDataStore dataStore = new InMemoryDataStore(URI.createURI("memory://localhost/test"), false);
		IDataSortedMap<Long> map = dataStore.getMap("testMap".getBytes(), LongKeyType.instance);
		
		for (int i = 0; i < 10; i++) {
			map.set(Long.valueOf(i), "testValue" + i);
		}
		assertTelemetry(dataStore, 0, 0, 1, 1);
		
		map = dataStore.getMap("testMap".getBytes(), LongKeyType.instance);
		Assert.assertTrue(map.exists(Long.valueOf(1)));
		assertTelemetry(dataStore, 0, 0, 1, 1);
		
		dataStore.clearCaches();
		
		assertTelemetry(dataStore, 0, 0, 0, 0);
		map = dataStore.getMap("testMap".getBytes(), LongKeyType.instance);
		Assert.assertNotNull(map);
		for (int i = 0; i < 10; i++) {
			Assert.assertTrue(map.exists(Long.valueOf(i)));
			Assert.assertEquals("testValue" + i, map.get(Long.valueOf(i)));
		}
		assertTelemetry(dataStore, 0, 0, 1, 1);
	}
	
	private void assertTelemetry(AbstractScanningDataStore dataStore, long minCachedScanCursors, long maxCachedScanCursors, long minCachedMaps, long maxCachedMaps) {
		Assert.assertTrue(dataStore.telemetryCachedMaps() >= minCachedMaps);
		Assert.assertTrue(dataStore.telemetryCachedMaps() <= maxCachedMaps);
		
		Assert.assertTrue(dataStore.telemetryCachedScanCursors() >= minCachedScanCursors);
		Assert.assertTrue(dataStore.telemetryCachedScanCursors()  <= maxCachedScanCursors);
	}
}
