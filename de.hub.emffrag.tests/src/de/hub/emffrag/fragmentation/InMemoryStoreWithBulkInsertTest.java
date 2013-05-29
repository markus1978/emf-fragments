package de.hub.emffrag.fragmentation;

import de.hub.emffrag.datastore.IBulkInsertExtension;

public class InMemoryStoreWithBulkInsertTest extends InMemoryStoreTest {
	@Override
	protected IBulkInsertExtension createBulkInsertExtension() {
		return inMemoryDataStore;
	}
}
