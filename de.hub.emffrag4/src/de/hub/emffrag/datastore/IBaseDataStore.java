package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;

public interface IBaseDataStore {
	
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
	boolean checkAndCreate(byte[] key);

	void delete(byte[] bytes);

	/**
	 * Called to flush all caches.
	 */
	void flush();
	
	/**
	 * Closes the datastore (not called by EMF-Fragments).
	 */
	void close();
	
	/**
	 * Retrieve database statistics, if available. Can return null.
	 */
	Object getStats();
}
