package de.hub.emffrag.datastore;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.eclipse.emf.common.util.URI;

public class DataIndex<KT> {

	private final DataStore store;
	private final byte[] fullPrefix;
	private final byte[] fullPrefixNext;
	private final KeyType<KT> keyType;

	public DataIndex(DataStore store, String prefix, KeyType<KT> keyType) {
		super();
		this.store = store;
		this.fullPrefix = (prefix + "_").getBytes();
		this.fullPrefixNext = (prefix + (char) ('_' + 1)).getBytes();
		this.keyType = keyType;
	}

	public byte[] getStoreKey(KT key) {
		byte[] serialize = keyType.serialize(key);
		return ByteBuffer.allocate(fullPrefix.length + serialize.length).put(fullPrefix).put(serialize).array();
	}

	public URI getURI(KT key) {
		return URI.createURI(store.getURIString()).appendSegment(URIUtils.encode(getStoreKey(key)));
	}

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
			// TODO error handling
			throw new RuntimeException("Could not add key " + new String(keyType.serialize(key)));
		}
	}

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
	
	public KT exactOrNext(KT key) {
		byte[] ceiling = store.ceiling(getStoreKey(key));
		return ceiling == null ? null : keyType.deserialize(ceiling, fullPrefix.length);
	}
	
	public boolean exists(KT key) {
		return !store.check(getStoreKey(key));
	}
	
	public KT next(KT key) {
		byte[] ceiling = store.ceiling(getStoreKey(keyType.next(key)));
		return ceiling == null ? null : keyType.deserialize(ceiling, fullPrefix.length);
	}

	/**
	 * @return True if the key could be added, thus was a new key.
	 */
	public boolean add(KT key) {
		byte[] storeKey = getStoreKey(key);
		return store.ckeckAndCreate(storeKey);
	}

	public void set(KT key, String value) {
		PrintWriter printWriter = new PrintWriter(openOutputStream(key));
		printWriter.print(value);
		printWriter.close();
	}

	public String get(KT key) {
		InputStream openInputStream = openInputStream(key);
		return openInputStream == null ? null : slurp(openInputStream, 1024);
	}
	
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

	public OutputStream openOutputStream(KT key) {
		return store.openOutputStream(getStoreKey(key));
	}

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

	public KT getKeyFromURI(URI crossReferenceURI) {
		byte[] key = URIUtils.decode(crossReferenceURI.segment(1));
		return keyType.deserialize(key, fullPrefix.length);
	}
}
