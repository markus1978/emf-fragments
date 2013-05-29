package de.hub.emffrag.datastore;

import java.io.InputStream;

public interface IScanExtension {

	/**
	 * ICursor is created for a specific key (see
	 * {@link IScanExtension#cursor(byte[])}). After it is created
	 * {@link #next()} is called to retrieve the actual start key. After that
	 * {@link #next()} is called to retrieve the successor keys.
	 */
	interface ICursor {

		/**
		 * @return true if their is a next element, also true if there is an
		 *         element at all, when called for the first time.
		 */
		public boolean hasNext();

		/**
		 * @return the successor key or the first value when called for the first time.
		 */
		public byte[] next();

		/**
		 * @return an input stream for the value of the key that {@link #next()} returned before. 
		 */
		public InputStream openNextInputStream();

		/**
		 * After calling this method, the {@link ICursor} cannot be used anymore.
		 */
		public void close();

	}

	public ICursor cursor(byte[] key);

}
