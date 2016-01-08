package de.hub.emffrag.datastore.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.datastore.IBulkInsertExtension;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.IScanExtension;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.ScanDataStore;
import de.hub.emffrag.datastore.internal.IDataMap;
import de.hub.emffrag.datastore.internal.LongKeyType;
import de.hub.emffrag.datastore.BulkInsertDataStore;
import de.hub.emffrag.tests.model.TestModelPackage;

public abstract class AbstractDataStoreTest {

	protected abstract IBaseDataStore createBaseDataStore();
	/**
	 * @return null if no scan extension shall be used. See {@link ScanDataStore}.
	 */
	protected IScanExtension createScanExtension() {
		return null;
	}
	/**
	 * @return null if no bulk insert shall be used. See {@link BulkInsertDataStore}.
	 */
	protected IBulkInsertExtension createBulkInsertExtension() {
		return null;
	}
	
	protected abstract URI createURI();
	
	protected IDataStore dataStore;
	
	@BeforeClass
	public static void initializeEMFFragments() {
		EmfFragActivator.standalone(TestModelPackage.eINSTANCE);
		EmfFragActivator.instance.logInStandAlone = false;
	}
	
	@Before
	public void createDataStore() {
		IBaseDataStore baseDataStore = createBaseDataStore();
		IScanExtension scanExtension = createScanExtension();
		IBulkInsertExtension bulkInsertExtension = createBulkInsertExtension();
		URI uri = createURI();
		
		if (scanExtension != null) {
			baseDataStore = new ScanDataStore(baseDataStore, scanExtension);
		}
		if (bulkInsertExtension != null) {
			baseDataStore = new BulkInsertDataStore(baseDataStore, bulkInsertExtension, 100);
		}
		dataStore = new DataStoreImpl(baseDataStore, uri);
	}
	
	@Test
	public void testBasicWriteRead() {
		for (int i = 0; i < 5000; i++) {
			byte[] key = createKey(i);
			byte[] value = createValue(i);
			dataStore.checkAndCreate(key);
			write(key, value);
		}
		
		for (int i = 0; i < 5000; i++) {
			byte[] key = createKey(i);
			byte[] value = createValue(i);
			
			read(key, value);
		}
		
		for (int i = 5000; i < 10000; i++) {
			byte[] key = createKey(i);
			InputStream is = dataStore.openInputStream(key);
			Assert.assertNull(is);			
		}
	}
	
	private void read(byte[] key, byte[] value) {
		InputStream is = dataStore.openInputStream(key);
		Assert.assertNotNull(is);
		byte[] readValue = new byte[value.length];
		try {
			is.read(readValue, 0, value.length);
			is.close();
		} catch (IOException e) {
			Assert.fail(e.getClass().getName() + ": " + e.getMessage());
		}
		Assert.assertEquals(0, InMemoryDataStore.compareBytes(value, readValue));
	}
	
	private void write(byte[] key, byte[] value) {
		OutputStream os = dataStore.openOutputStream(key);
		try {
			os.write(value);
			os.close();
		} catch (Exception e) {
			Assert.fail(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	private byte[] createValue(int i) {
		return ("testValue" + i).getBytes();
	}
	
	@Test
	public void testRandomReadWrite() {
		Random random = new Random(0);
		Map<BigInteger, byte[]> reference = new HashMap<BigInteger, byte[]>();
		for (int i = 0; i < 100; i++) {
			long randomKey = random.nextLong(); 
			BigInteger bigIntegerKey = BigInteger.valueOf(randomKey);
			byte[] key = bigIntegerKey.toByteArray();
			byte[] value = createValue(i);
			dataStore.checkAndCreate(key);
			write(key, value);
			reference.put(bigIntegerKey, value);
			read(key, value);
		}
		
		for (Entry<BigInteger, byte[]> entry: reference.entrySet()) {
			read(entry.getKey().toByteArray(), entry.getValue());
		}
	}
	
	@Test
	public void testCeiling() {
		writePattern();
		
		Assert.assertEquals(BigInteger.valueOf(0), new BigInteger(dataStore.ceiling(createKey(0))));
		Assert.assertEquals(BigInteger.valueOf(1), new BigInteger(dataStore.ceiling(createKey(1))));
		Assert.assertEquals(BigInteger.valueOf(6), new BigInteger(dataStore.ceiling(createKey(2))));
	}
	private void writePattern() {
		int[] keyPattern = new int[] {0, 1, 6, 10, 20};
		for (int key: keyPattern) {
			dataStore.checkAndCreate(createKey(key));
			write(createKey(key), createValue(key));
		}
	}
	
	@Test
	public void testFloor() {
		writePattern();
		
		Assert.assertEquals(BigInteger.valueOf(0), new BigInteger(dataStore.floor(createKey(0))));
		Assert.assertEquals(BigInteger.valueOf(1), new BigInteger(dataStore.floor(createKey(1))));
		Assert.assertEquals(BigInteger.valueOf(1), new BigInteger(dataStore.floor(createKey(2))));
	}
	
	@Test
	public void testCheck() {
		writePattern();
		
		Assert.assertFalse(dataStore.check(createKey(0)));
		Assert.assertFalse(dataStore.check(createKey(1)));
		Assert.assertTrue(dataStore.check(createKey(2)));
		Assert.assertTrue(dataStore.check(createKey(2)));
	}
	
	@Test
	public void testCheckAndCreate() {
		writePattern();
		
		Assert.assertFalse(dataStore.checkAndCreate(createKey(0)));
		Assert.assertFalse(dataStore.checkAndCreate(createKey(1)));
		Assert.assertTrue(dataStore.checkAndCreate(createKey(2)));
		Assert.assertFalse(dataStore.checkAndCreate(createKey(2)));
	}
	
	@Test
	public void testDelete() {
		writePattern();
		
		Assert.assertFalse(dataStore.check(createKey(0)));
		dataStore.delete(createKey(0));
		Assert.assertTrue(dataStore.check(createKey(0)));
		Assert.assertEquals(BigInteger.valueOf(1), new BigInteger(dataStore.ceiling(createKey(0))));
	}
	
	@Test
	public void testWriteDeleted() {
		boolean exceptionCatched = false;
		byte[] key = createKey(0);
		Assert.assertTrue(dataStore.checkAndCreate(key));		
		dataStore.delete(key);
		try {
			OutputStream out = dataStore.openOutputStream(key);
			out.close();
		} catch (Exception e) {
			exceptionCatched = true;
		}
				
		Assert.assertTrue(exceptionCatched);
	}
	
	@Test
	public void testDoubleDelete() {
		boolean exceptionCatched = false;
		byte[] key = createKey(0);
		dataStore.checkAndCreate(key);		
		dataStore.delete(key);
		try {
			dataStore.delete(key);
		} catch (Exception e) {
			exceptionCatched = true;
		}
				
		Assert.assertTrue(exceptionCatched);		
	}
	
	@Test
	public void testLastAdd() {
		IDataMap<Long> map = dataStore.getMap("".getBytes(), LongKeyType.instance);
		long last = 0;
		for (int i = 0; i < 10; i++) {
			Long lastLongValue = map.last();
			if (lastLongValue == null) {
				last = -1;
			} else {	
				last = lastLongValue;
			}
			map.add(lastLongValue == null ? LongKeyType.instance.nullKey() : LongKeyType.instance.next(lastLongValue));
			Assert.assertTrue(last < map.last());
		}
	}
	
	@Test
	public void testAdd() {
		writePattern();
		IDataMap<Long> map = dataStore.getMap("".getBytes(), LongKeyType.instance);	
		long last = -1;
		for (int i = 0; i < 100; i++) {
			Long value = map.add();
			Assert.assertTrue(last < value);
			last = value;
		}
	}
	
	@Test
	public void testAddWithRemoves() {
		IDataMap<Long> map = dataStore.getMap("".getBytes(), LongKeyType.instance);	
		for (int i = 0; i < 100; i++) {
			Long value = map.add();
			dataStore.delete(map.getStoreKey(value));
		}
	}
	
	private byte[] createKey(int key) {
		return BigInteger.valueOf(key).toByteArray();
	}
}
