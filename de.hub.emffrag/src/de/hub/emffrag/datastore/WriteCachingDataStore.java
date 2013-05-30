package de.hub.emffrag.datastore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;

import de.hub.emffrag.EmfFragActivator;


public class WriteCachingDataStore extends AbstractDelegatingDataStore {
	
	private final TreeMap<byte[], byte[]> cache = new TreeMap<byte[], byte[]>(InMemoryDataStore.byteComparator);
	private final IBulkInsertExtension bulkInsertExtension;
	private int bulkInsertSize = 1000;

	public void setBulkInsertSize(int bulkInsertSize) {
		this.bulkInsertSize = bulkInsertSize;
	}

	public WriteCachingDataStore(IBaseDataStore baseDataStore, IBulkInsertExtension bulkInsertExtension) {
		super(baseDataStore);
		this.bulkInsertExtension = bulkInsertExtension;
		if (EmfFragActivator.instance != null) {
			this.bulkInsertSize = EmfFragActivator.instance.bulkInsertSize;
		}
	}

	private void performBulkInsert() {	
		bulkInsertExtension.bulkInsert(cache);
		cache.clear();
	}

	@Override
	public InputStream openInputStream(byte[] key) {
		byte[] cachedValue = cache.get(key);
		if (cachedValue == null) {
			return super.openInputStream(key);	
		} else {
			return new ByteArrayInputStream(cachedValue);
		}
	}

	@Override
	public OutputStream openOutputStream(final byte[] key) {
		return new ByteArrayOutputStream() {
			@Override
			public void close() throws IOException {
				super.close();
				cache.put(key, toByteArray());
				if (cache.size() > bulkInsertSize) {
					performBulkInsert();
				}
			}	
		};
	}
	
	private byte[] computeFloorOrCeiling(byte[] cachedValue, byte[] dbValue, int compareValue) {
		if (cachedValue == null) {
			return dbValue;
		} else {
			if (dbValue == null) {
				return cachedValue;
			} else {
				if (cachedValue != null && InMemoryDataStore.byteComparator.compare(cachedValue, dbValue) == compareValue) {
					return cachedValue;
				} else {
					return dbValue;
				}
			}
		}	
	}

	@Override
	public byte[] ceiling(byte[] key) {
		byte[] cacheCeiling = cache.ceilingKey(key);
		byte[] dbCeiling = super.ceiling(key);
		
		return computeFloorOrCeiling(cacheCeiling, dbCeiling, -1);
	}

	@Override
	public byte[] floor(byte[] key) {
		byte[] cacheFloor = cache.floorKey(key);
		byte[] dbFloor = super.floor(key);
		return computeFloorOrCeiling(cacheFloor, dbFloor, 1);
	}

	@Override
	public boolean check(byte[] key) {
		if (cache.get(key) != null) {
			return false;
		} else {	
			return super.check(key);
		}
	}

	@Override
	public boolean checkAndCreate(byte[] key) {
		if (cache.get(key) != null) {	
			return false;
		} else {	
			if (super.checkAndCreate(key)) {
				cache.put(key, new byte[]{});
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void delete(byte[] bytes) {
		cache.remove(bytes);
		super.delete(bytes);
	}

	@Override
	public void flush() {
		performBulkInsert();
		super.flush();
	}
}
