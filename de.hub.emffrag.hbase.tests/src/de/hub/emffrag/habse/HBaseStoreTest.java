package de.hub.emffrag.habse;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.fragmentation.AbstractDataStoreTest;
import de.hub.emffrag.hbase.HBaseDataStore;

public class HBaseStoreTest extends AbstractDataStoreTest {
	
	protected HBaseDataStore hbaseDataStore;

	@Override
	protected IBaseDataStore createBaseDataStore() {
		hbaseDataStore = new HBaseDataStore("test", true);
		return hbaseDataStore;
	}

	@Override
	protected URI createURI() {
		return URI.createURI("hbase://localhost/test");
	}

}
