package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

public interface IDataMap<KT> {

	public byte[] getStoreKey(KT key);

	public URI getURI(KT key);

	public KT add();

	public KT first();

	public KT last();

	public KT exactOrNext(KT key);

	public boolean exists(KT key);

	public KT next(KT key);

	/**
	 * @return True if the key could be added, thus was a new key.
	 */
	public boolean add(KT key);

	public void set(KT key, String value);

	public String get(KT key);

	public String remove(KT key);

	public OutputStream openOutputStream(KT key);

	public InputStream openInputStream(KT key);

	public KT getKeyFromURI(URI crossReferenceURI);
	
	public void close();

}
