package de.hub.emffrag.datastore2;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import de.hub.emffrag.datastore.KeyType;

public interface IDataStore {
	
	public <KT> IDataSortedMap<KT> getMap(byte[] prefix, KeyType<KT> keyType);
	
	public InputStream openInputStream(byte[] key);

	public OutputStream openOutputStream(byte[] key);

	/**
	 * Delete the whole store. It cannot be used afterwards.
	 */
	public void drop();
	
	/**
	 * @return the least key greater than or equal to the given key, or null if
	 *         there is no such key.
	 */
	byte[] ceiling(byte[] key);

	/**
	 * @return the greatest key less than or equal to the given key, or null if
	 *         there is no such key.
	 */
	byte[] floor(byte[] key);

	/**
	 * @return True if no entry with this key exists and the key is still
	 *         available.
	 */
	boolean check(byte[] key);

	/**
	 * @return True if the key has not existed before.
	 */
	boolean ckeckAndCreate(byte[] key);
	
	boolean bulkInsert(Map<byte[], byte[]> map);

	void delete(byte[] bytes);

	void close();
}
