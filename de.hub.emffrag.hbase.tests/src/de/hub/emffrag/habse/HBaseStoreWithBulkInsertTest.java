package de.hub.emffrag.habse;

import de.hub.emffrag.datastore.IBulkInsertExtension;

public class HBaseStoreWithBulkInsertTest extends HBaseStoreTest {
	@Override
	protected IBulkInsertExtension createBulkInsertExtension() {
		return hbaseDataStore;
	}
}
