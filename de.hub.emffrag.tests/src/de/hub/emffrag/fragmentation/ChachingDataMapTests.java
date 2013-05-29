package de.hub.emffrag.fragmentation;


public class ChachingDataMapTests extends AbstractFragmentationTests {
	
//	IDataMap<Long> map = null; 
//	
//	@Before
//	public void init() {
//		map = dataStore.getMap("test".getBytes(), LongKeyType.instance);
//	}
//
//	@Test
//	public void simpleTest() {
//		map.add((long)1);
//		map.set((long)1, "testValue");
//	}
//	
//	@Test
//	public void testManualSave() {
//		for (int i = 0; i < 10; i++) {
//			map.set(Long.valueOf(i), "testValue" + i);
//		}
//		assertTelemetry(0, 0, 1, 1);
//		
//		map = dataStore.getMap("testMap".getBytes(), LongKeyType.instance);
//		Assert.assertTrue(map.exists(Long.valueOf(1)));
//		assertTelemetry(0, 0, 1, 1);
//		
//		((AbstractScanningDataStore)dataStore).clearCaches();
//		
//		assertTelemetry(0, 0, 0, 0);
//		map = dataStore.getMap("testMap".getBytes(), LongKeyType.instance);
//		Assert.assertNotNull(map);
//		for (int i = 0; i < 10; i++) {
//			Assert.assertTrue(map.exists(Long.valueOf(i)));
//			Assert.assertEquals("testValue" + i, map.get(Long.valueOf(i)));
//		}
//		assertTelemetry(0, 0, 1, 1);
//	}
//	
//	@Test
//	public void testContiniousAdd() {
//		for (int i = 0; i < 100; i++) { 
//			map.add();
//		}
//		
//		assertTelemetry(0, 0, 1, 1);
//		Assert.assertEquals(Long.valueOf(99), map.last()); 
//	}
//	
//	@Test
//	public void testMapBoundaries() {
//		map.add();
//		Assert.assertEquals(Long.valueOf(0), map.first());
//		Assert.assertEquals(Long.valueOf(0), map.last());
//	}
//	
//	@Test
//	public void testGarbageCollectOfMaps() throws Exception {
//		InMemoryDataStore dataStore = new InMemoryDataStore(URI.createURI("memory://localhost/test"), false);
//		dataStore.getMap("testMap".getBytes(), LongKeyType.instance);
//		
//		System.gc();
//		Thread.sleep(200);
//		dataStore.cleanUpCaches();
//		System.gc();
//		Thread.sleep(200);
//		
//		assertTelemetry(0, 0, 0, 0);
//	}
//	
//	private void assertTelemetry(long minCachedScanCursors, long maxCachedScanCursors, long minCachedMaps, long maxCachedMaps) {
//		AbstractScanningDataStore dataStore = (AbstractScanningDataStore)this.dataStore;
//		Assert.assertTrue(dataStore.telemetryCachedMaps() >= minCachedMaps);
//		Assert.assertTrue(dataStore.telemetryCachedMaps() <= maxCachedMaps);
//		
//		Assert.assertTrue(dataStore.telemetryCachedScanCursors() >= minCachedScanCursors);
//		Assert.assertTrue(dataStore.telemetryCachedScanCursors()  <= maxCachedScanCursors);
//	}
}
