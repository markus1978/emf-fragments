package de.hub.emffrag.kvstore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.TreeMap;

import javax.xml.bind.DatatypeConverter;

import de.hub.emffrag.datastore.DataStore;

public class InMemoryDataStore extends DataStore {
	
	private static final byte[] EMTPY = new byte[] { 0 };
	
	TreeMap<byte[], byte[]> store = new TreeMap<byte[], byte[]>(new Comparator<byte[]>() {
		@Override
		public int compare(byte[] o1, byte[] o2) {
			return compareBytes(o1, o2);
		}		
	});
	

	/**
	 * Comparator for byte arrays as used in HBase, supposed to be
	 * lexicographically.
	 */
	public static int compareBytes(byte[] left, byte[] right) {
		for (int i = 0, j = 0; i < left.length && j < right.length; i++, j++) {
			int a = (left[i] & 0xff);
			int b = (right[j] & 0xff);
			if (a != b) {
				return a - b;
			}
		}
		return left.length - right.length;
	}

	public InMemoryDataStore(String protocol, String domain, String dataStoreId) {
		super(protocol, domain, dataStoreId);
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
				store.put(key, value);
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
			buffer.append(", URI: " + getURIString() + "/" + DatatypeConverter.printBase64Binary(key) + "\n");
			buffer.append(new String(store.get(key)) + "\n");
		}
		return buffer.toString();
	}
	

}
