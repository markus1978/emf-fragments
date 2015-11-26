package de.hub.emffrag.datastore;

public class InMemoryStoreWithScanTest extends InMemoryStoreTest {
	@Override
	protected IScanExtension createScanExtension() {
		return inMemoryDataStore.createScanningScanExtension();
	}
}
