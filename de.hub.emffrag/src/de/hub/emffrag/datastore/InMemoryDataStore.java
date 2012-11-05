package de.hub.emffrag.datastore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;

public class InMemoryDataStore extends DataStore {
	
	private static final byte[] EMTPY = new byte[] { 0 };
	
	private TreeMap<byte[], byte[]> store = new TreeMap<byte[], byte[]>(byteComparator);

	private final boolean fleeting;

	public InMemoryDataStore(String protocol, String domain, String dataStoreId, boolean fleeting) {
		super(protocol, domain, dataStoreId);
		this.fleeting = fleeting;
	}

	@Override
	public byte[] ceiling(byte[] key) {
		return store.ceilingKey(key);
	}

	@Override
	public byte[] floor(byte[] key) {
		return store.floorKey(key);
	}

	@Override
	public InputStream openInputStream(byte[] key) {
		byte[] value = store.get(key);
		if (value == null) {
			return null;
		} else {
	        return new ByteArrayInputStream(value);
		}
	}

	@Override
	public OutputStream openOutputStream(final byte[] key) {
		return new ByteArrayOutputStream() {
			@Override
			public void close() throws IOException {
				super.close();
				byte[] value = toByteArray();
				store.put(key, fleeting ? EMTPY : value);
			}
		};
	}

	@Override
	public boolean check(byte[] key) {
		return store.get(key) == null;
	}

	@Override
	public boolean ckeckAndCreate(byte[] key) {
		boolean result = check(key);
		if (result) {
			store.put(key, EMTPY);
		}
		return result;
	}

	@Override
	public void delete(byte[] key) {
		store.remove(key);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getURIString() + "\n");
		for (byte[] key: store.keySet()) {
			buffer.append("key: ");
			for (byte b: key) {
				buffer.append(b + " ");
			}
			buffer.append(", URI: " + getURIString() + "/" + URIUtils.encode(key) + "\n");
			buffer.append(new String(store.get(key)) + "\n");
		}
		return buffer.toString();
	}
	

}
