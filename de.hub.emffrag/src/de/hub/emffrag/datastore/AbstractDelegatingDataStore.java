package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;


public class AbstractDelegatingDataStore implements IBaseDataStore {

	protected final IBaseDataStore delegate;

	public AbstractDelegatingDataStore(IBaseDataStore delegate) {
		super();
		this.delegate = delegate;
	}

	public InputStream openInputStream(byte[] key) {
		return delegate.openInputStream(key);
	}

	public OutputStream openOutputStream(byte[] key) {
		return delegate.openOutputStream(key);
	}

	public void drop() {
		delegate.drop();
	}

	public byte[] ceiling(byte[] key) {
		return delegate.ceiling(key);
	}

	public byte[] floor(byte[] key) {
		return delegate.floor(key);
	}

	public boolean check(byte[] key) {
		return delegate.check(key);
	}

	public boolean checkAndCreate(byte[] key) {
		return delegate.checkAndCreate(key);
	}

	public void delete(byte[] bytes) {
		delegate.delete(bytes);
	}

	@Override
	public void flush() {
		delegate.flush();
	}

	public void close() {
		delegate.close();
	}
	
}
