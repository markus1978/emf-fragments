package de.hub.emffrag.datastore;

import de.hub.emffrag.datastore.IBulkInsertExtension;

public class InMemoryStoreWithBulkInsertTest extends InMemoryStoreTest {
	@Override
	protected IBulkInsertExtension createBulkInsertExtension() {
		return inMemoryDataStore;
	}
}
