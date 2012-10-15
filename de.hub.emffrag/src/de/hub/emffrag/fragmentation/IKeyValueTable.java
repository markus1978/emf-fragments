package de.hub.emffrag.fragmentation;

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

public interface IKeyValueTable {

	/**
	 * Creates a new entry in the persistence layer and returns its unique URI.
	 */
	public URI createNewEntry();
	
	public URI createEntry(URI uri);

	/**
	 * Removes an entry from the persistence layer.
	 * 
	 * @param uri
	 *            The URI that uniquly identifies the entry.
	 */
	public void removeAnEntry(URI uri);
	
	public boolean entryExists(URI uri);

	public OutputStream createOutputStream(URI key);

	public InputStream createInputStream(URI key);
	
	public String read(URI key);
	
	public void write(URI key, String value);

	public void flush();

}
