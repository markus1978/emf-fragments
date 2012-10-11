package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;

public interface IKeyValueTable {

	/**
	 * Creates a new entry in the persistence layer and returns its unique URI.
	 */
	public URI createANewEntry();
	
	/**
	 * Removes an entry from the persistence layer.
	 * @param uri The URI that uniquly identifies the entry.
	 */
	public void removeAnEntry(URI uri);
}
