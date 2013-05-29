package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IBulkInsertExtension;

public class HBaseStoreWithScanAndBulkInsertTest extends HBaseStoreWithScanTest {

	@Override
	protected IBulkInsertExtension createBulkInsertExtension() {
		return hbaseDataStore;
	}

}
