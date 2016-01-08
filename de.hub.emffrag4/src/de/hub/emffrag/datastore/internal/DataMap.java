package de.hub.emffrag.datastore.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.IBaseDataStore;


public class DataMap<KT> implements IDataMap<KT> {

	protected final IBaseDataStore store;
	private final byte[] fullPrefix;
	private final byte[] fullPrefixNext;
	protected final KeyType<KT> keyType;
	private final URI dataStoreUri;

	public DataMap(IBaseDataStore store, URI dataStoreURI, byte[] prefix, KeyType<KT> keyType) {
		super();
		this.store = store;
		this.fullPrefix = (new String(prefix) + "_").getBytes();
		this.fullPrefixNext = (new String(prefix) + (char) ('_' + 1)).getBytes();
		this.keyType = keyType;
		this.dataStoreUri = dataStoreURI;
	}

	@Override
	public byte[] getStoreKey(KT key) {
		byte[] serialize = keyType.serialize(key);
		return ByteBuffer.allocate(fullPrefix.length + serialize.length).put(fullPrefix).put(serialize).array();
	}

	@Override
	public URI getURI(KT key) {
		return dataStoreUri.appendSegment(URIUtils.encode(getStoreKey(key)));
	}

	@Override
	public KT add() {
		KT last = last();
		KT key = null;
		if (last == null) {
			key = keyType.nullKey();
		} else {
			key = keyType.next(last);
		}
		if (add(key)) {
			return key;
		} else {
			// TODO error handling: retry for a couple of times and then fail?
			throw new RuntimeException("Could not add key " + new String(keyType.serialize(key)));
		}
	}

	@Override
	public KT first() {
		byte[] lastStoreKeyInIndex = store.ceiling(fullPrefix);
		if (lastStoreKeyInIndex == null) {
			return null;
		}

		if (prefixMatches(lastStoreKeyInIndex)) {
			return keyType.deserialize(lastStoreKeyInIndex, fullPrefix.length);
		} else {
			return null;
		}
	}

	private boolean prefixMatches(byte[] lastStoreKeyInIndex) {
		boolean prefixMatches = true;
		for (int i = 0; i < fullPrefix.length; i++) {
			prefixMatches &= fullPrefix[i] == lastStoreKeyInIndex[i];
		}
		return prefixMatches;
	}

	@Override
	public KT last() {
		byte[] lastStoreKeyInIndex = store.floor(fullPrefixNext);
		if (lastStoreKeyInIndex == null) {
			return null;
		}

		if (prefixMatches(lastStoreKeyInIndex)) {
			return keyType.deserialize(lastStoreKeyInIndex, fullPrefix.length);
		} else {
			return null;
		}
	}
	
	@Override
	public KT exactOrNext(KT key) {
		byte[] ceiling = store.ceiling(getStoreKey(key));
		return ceiling == null ? null : keyType.deserialize(ceiling, fullPrefix.length);
	}
	
	@Override
	public boolean exists(KT key) {
		return !store.check(getStoreKey(key));
	}
	
	@Override
	public KT next(KT key) {
		byte[] ceiling = store.ceiling(getStoreKey(keyType.next(key)));
		return ceiling == null ? null : keyType.deserialize(ceiling, fullPrefix.length);
	}

	@Override
	public boolean add(KT key) {
		byte[] storeKey = getStoreKey(key);
		return store.checkAndCreate(storeKey);
	}

	@Override
	public void set(KT key, String value) {
		PrintWriter printWriter = new PrintWriter(openOutputStream(key));
		printWriter.print(value);
		printWriter.close();
	}

	@Override
	public String get(KT key) {
		InputStream openInputStream = openInputStream(key);
		return openInputStream == null ? null : slurp(openInputStream, 1024);
	}
	
	@Override
	public String remove(KT key) {
		byte[] storeKey = getStoreKey(key);
		InputStream openInputStream = store.openInputStream(storeKey);
		if (openInputStream == null) {
			return null;
		} else {
			String result = slurp(openInputStream, 1024);
			store.delete(storeKey);
			return result;
		}
	}

	@Override
	public OutputStream openOutputStream(KT key) {
		return store.openOutputStream(getStoreKey(key));
	}

	@Override
	public InputStream openInputStream(KT key) {
		return store.openInputStream(getStoreKey(key));
	}

	private static String slurp(final InputStream is, final int bufferSize) {
		final char[] buffer = new char[bufferSize];
		final StringBuilder out = new StringBuilder();
		try {
			final Reader in = new InputStreamReader(is, "UTF-8");
			try {
				for (;;) {
					int rsz = in.read(buffer, 0, buffer.length);
					if (rsz < 0)
						break;
					out.append(buffer, 0, rsz);
				}
			} finally {
				in.close();
			}
		} catch (UnsupportedEncodingException ex) {
			/* ... */
		} catch (IOException ex) {
			/* ... */
		}
		return out.toString();
	}

	@Override
	public KT getKeyFromURI(URI crossReferenceURI) {
		byte[] key = URIUtils.decode(crossReferenceURI.segment(1));
		return keyType.deserialize(key, fullPrefix.length);
	}

	@Override
	public void close() {
		
	}
	
}
