package de.hub.emffrag.datastore;

public class InMemoryStoreWithScanAndBulkInsertTest extends InMemoryStoreWithScanTest {

	@Override
	protected IBulkInsertExtension createBulkInsertExtension() {
		return inMemoryDataStore;
	}

}
