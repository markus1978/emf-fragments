package de.hub.emffrag.datastore;

import org.eclipse.emf.common.util.URI;

public class InMemoryStoreTest extends AbstractDataStoreTest {
	
	protected InMemoryDataStore inMemoryDataStore;

	@Override
	protected IBaseDataStore createBaseDataStore() {
		inMemoryDataStore = new InMemoryDataStore(false);
		return inMemoryDataStore;
	}

	@Override
	protected URI createURI() {
		return URI.createURI("memory://localhost/test");
	}
}
