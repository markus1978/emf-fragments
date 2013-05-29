package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IScanExtension;

public class HBaseStoreWithScanTest extends HBaseStoreTest {
	@Override
	protected IScanExtension createScanExtension() {
		return hbaseDataStore;
	}
}
