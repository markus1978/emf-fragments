package de.hub.emffrag.datastore;

public class InMemoryStoreWithBulkInsertTest extends InMemoryStoreTest {
	@Override
	protected IBulkInsertExtension createBulkInsertExtension() {
		return inMemoryDataStore;
	}
}
