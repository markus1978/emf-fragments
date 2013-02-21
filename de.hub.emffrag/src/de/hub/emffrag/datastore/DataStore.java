package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;

/**
 * TODO scan functionality
 * 
 * This is an abstraction for so called sorted-"BigTables", like HBase tables.
 * It is ignoring any version or multi-column concepts and just realizes
 * key-value pairs lexicographically ordered by key.
 */
public abstract class DataStore {

	private final String protocol;
	private final String domain;
	private final String dataStoreId;

	public DataStore(String protocol, String domain, String dataStoreId) {
		super();
		this.protocol = protocol;
		this.domain = domain;
		this.dataStoreId = dataStoreId;
	}

	public static final Comparator<byte[]> byteComparator = new Comparator<byte[]>() {
		@Override
		public int compare(byte[] o1, byte[] o2) {
			return compareBytes(o1, o2);
		}
	};

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

	/**
	 * @return the least key greater than or equal to the given key, or null if
	 *         there is no such key.
	 */
	public abstract byte[] ceiling(byte[] key);

	/**
	 * @return the greatest key less than or equal to the given key, or null if
	 *         there is no such key.
	 */
	public abstract byte[] floor(byte[] key);

	public abstract InputStream openInputStream(byte[] key);

	public abstract OutputStream openOutputStream(byte[] key);

	/**
	 * @return True if no entry with this key exists and the key is still
	 *         available.
	 */
	public abstract boolean check(byte[] key);

	/**
	 * @return True if the key has not existed before.
	 */
	public abstract boolean ckeckAndCreate(byte[] key);

	public abstract void delete(byte[] bytes);

	public String getProtocol() {
		return protocol;
	}

	public String getDomain() {
		return domain;
	}

	public String getDataStoreId() {
		return dataStoreId;
	}

	public String getURIString() {
		return protocol + "://" + domain + "/" + dataStoreId;
	}

}
