package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

public class DelegatedDataMap<KT> implements IDataMap<KT> {
	private IDataMap<KT> delegate;

	public DelegatedDataMap(IDataMap<KT> delegate) {
		super();
		this.delegate = delegate;
	}

	public byte[] getStoreKey(KT key) {
		return delegate.getStoreKey(key);
	}

	public URI getURI(KT key) {
		return delegate.getURI(key);
	}

	public KT add() {
		return delegate.add();
	}

	public KT first() {
		return delegate.first();
	}

	public KT last() {
		return delegate.last();
	}

	public KT exactOrNext(KT key) {
		return delegate.exactOrNext(key);
	}

	public boolean exists(KT key) {
		return delegate.exists(key);
	}

	public KT next(KT key) {
		return delegate.next(key);
	}

	public boolean add(KT key) {
		return delegate.add(key);
	}

	public void set(KT key, String value) {
		delegate.set(key, value);
	}

	public String get(KT key) {
		return delegate.get(key);
	}

	public String remove(KT key) {
		return delegate.remove(key);
	}

	public OutputStream openOutputStream(KT key) {
		return delegate.openOutputStream(key);
	}

	public InputStream openInputStream(KT key) {
		return delegate.openInputStream(key);
	}

	public KT getKeyFromURI(URI crossReferenceURI) {
		return delegate.getKeyFromURI(crossReferenceURI);
	}

	public void close() {
		delegate.close();
	}
}