package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.io.OutputStream;

import de.hub.emffrag.EmfFragActivator;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;


public class AbstractDelegatingDataStore implements IBaseDataStore {

	protected final IBaseDataStore delegate;

	public AbstractDelegatingDataStore(IBaseDataStore delegate) {
		super();
		this.delegate = delegate;
	}

	public InputStream openInputStream(byte[] key) {
		EmfFragActivator.instance.debug(
				Ansi.format("DATASTORE: ", Color.RED) +
				Ansi.format("read ", Color.GREEN) + 
				Ansi.format(URIUtils.encode(key), Color.values()[Math.abs(key[key.length-1]) % Color.values().length]));
		return delegate.openInputStream(key);
	}

	public OutputStream openOutputStream(byte[] key) {
		EmfFragActivator.instance.debug(
				Ansi.format("DATASTORE: ", Color.RED) +
				Ansi.format("write ", Color.BLUE) + 
				Ansi.format(URIUtils.encode(key), Color.values()[Math.abs(key[key.length-1]) % Color.values().length]));
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
		EmfFragActivator.instance.debug(
				Ansi.format("DATASTORE: ", Color.RED) +
				Ansi.format("create ", Color.YELLOW) + 
				Ansi.format(URIUtils.encode(key), Color.values()[Math.abs(key[key.length-1]) % Color.values().length]));
		return delegate.checkAndCreate(key);
	}

	public void delete(byte[] key) {
		EmfFragActivator.instance.debug(
				Ansi.format("DATASTORE: ", Color.RED) +
				Ansi.format("delete ", Color.RED) + 
				Ansi.format(URIUtils.encode(key), Color.values()[Math.abs(key[key.length-1]) % Color.values().length]));
		delegate.delete(key);
	}

	@Override
	public void flush() {
		delegate.flush();
	}

	public void close() {
		delegate.close();
	}
	
}
