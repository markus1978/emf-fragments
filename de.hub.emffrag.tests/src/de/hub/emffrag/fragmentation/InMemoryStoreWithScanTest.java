package de.hub.emffrag.fragmentation;

import de.hub.emffrag.datastore.IScanExtension;

public class InMemoryStoreWithScanTest extends InMemoryStoreTest {
	@Override
	protected IScanExtension createScanExtension() {
		return inMemoryDataStore.createScanningScanExtension();
	}
}
