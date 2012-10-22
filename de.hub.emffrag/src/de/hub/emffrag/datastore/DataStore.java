package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;

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

	public abstract byte[] ceiling(byte[] key);

	public abstract byte[] floor(byte[] key);

	public abstract InputStream openInputStream(byte[] key);

	public abstract OutputStream openOutputStream(byte[] key);

	public abstract boolean check(byte[] key);

	public abstract boolean ckeckAndCreate(byte[] key);

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
