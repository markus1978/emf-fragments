package de.hub.emffrag.datastore2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.KeyType;

public class CachingDataMap<KT> extends DataMap<KT> {
	
	private final MapCache<KT> cache;
	private KT first = null;
	private KT last = null;
	
	public CachingDataMap(IDataStore store, URI dataStoreURI, byte[] prefix, KeyType<KT> keyType) {
		super(store, dataStoreURI, prefix, keyType);
		cache = new MapCache<KT>(keyType);
	}

	@Override
	public KT first() {
		if (first == null) {
			first = super.first();
		}
		return first;
	}

	@Override
	public KT last() {
		if (last == null) {
			last = super.last();
		}
		return last;
	}

	@Override
	public KT exactOrNext(KT key) {
		if (cache.contains(key)) {
			return cache.exactOrNext(key);
		} else {
			return super.exactOrNext(key);
		}
	}

	@Override
	public boolean exists(KT key) {
		if (cache.contains(key)) {
			return cache.exists(key);
		} else {
			return super.exists(key);
		}
	}

	@Override
	public KT next(KT key) {
		if (cache.contains(key)) {
			return cache.next(key);
		} else {
			return super.next(key);
		}
	}

	@Override
	public boolean add(KT key) {
		if (cache.add(key)) {
			last = key;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void set(KT key, String value) {
		if (cache.contains(key)) {
			cache.set(key, value);
		} else {
			super.set(key,value);
		}
	}

	@Override
	public String get(KT key) {
		if (cache.contains(key)) {
			return cache.get(key);
		} else {
			return super.get(key);
		}
	}

	@Override
	public String remove(KT key) {
		String result = null;
		if (cache.contains(key)) {
			result = cache.remove(key);
		} else {
			result = super.remove(key);
		}
		
		if (first != null && keyType.compare(first, key) <= 0) {
			first = null;
		}
		if (last != null && keyType.compare(last, key) >= 0) {
			last = null;
		}
		return result;
	}

	@Override
	public OutputStream openOutputStream(final KT key) {
		if (cache.contains(key)) {
			return new ByteArrayOutputStream() {
				@Override
				public void close() throws IOException {
					super.close();
					byte[] value = toByteArray();
					cache.set(key, new String(value));
				}
			};
		} else {
			return super.openOutputStream(key);
		}
	}

	@Override
	public InputStream openInputStream(KT key) {
		if (cache.contains(key)) {
			String value = cache.get(key);
			return new ByteArrayInputStream(value.getBytes());
		} else {
			return super.openInputStream(key);
		}
	}

	private void saveCache() {
		store.bulkInsert(cache.contents(this));
		cache.clear();
	}
	
	@Override
	public void close() {
		saveCache();
	}
}
