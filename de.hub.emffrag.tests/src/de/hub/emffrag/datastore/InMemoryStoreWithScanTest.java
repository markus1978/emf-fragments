package de.hub.emffrag.datastore;

import de.hub.emffrag.datastore.IScanExtension;

public class InMemoryStoreWithScanTest extends InMemoryStoreTest {
	@Override
	protected IScanExtension createScanExtension() {
		return inMemoryDataStore.createScanningScanExtension();
	}
}
