package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

public class DataIndexCache<KT> implements IDataIndex<KT> {
	
	private final IDataIndex<KT> base;
	
	private KT last = null;
	private KT first = null;

	public DataIndexCache(IDataIndex<KT> base) {
		super();
		this.base = base;
	}

	public byte[] getStoreKey(KT key) {
		return base.getStoreKey(key);
	}

	public URI getURI(KT key) {
		return base.getURI(key);
	}

	public KT add() {
		return base.add();
	}

	public KT first() {
		if (first == null) {
			first = base.first(); 
		}
		return first;
	}

	public KT last() {
		if (last == null) {
			last = base.last(); 
		}
		return last;
	}

	public KT exactOrNext(KT key) {
		return base.exactOrNext(key);
	}

	public boolean exists(KT key) {
		return base.exists(key);
	}

	public KT next(KT key) {
		return base.next(key);
	}

	public boolean add(KT key) {
		last = key;
		return base.add(key);
	}

	public void set(KT key, String value) {
		last = null;
		base.set(key, value);
	}

	public String get(KT key) {
		return base.get(key);
	}

	public String remove(KT key) {
		last = null;
		return base.remove(key);
	}

	public OutputStream openOutputStream(KT key) {
		return base.openOutputStream(key);
	}

	public InputStream openInputStream(KT key) {
		return base.openInputStream(key);
	}

	public KT getKeyFromURI(URI crossReferenceURI) {
		return base.getKeyFromURI(crossReferenceURI);
	}
}
