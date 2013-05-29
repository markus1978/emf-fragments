package de.hub.emffrag.habse;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.WriteCachingDataStore;
import de.hub.emffrag.hbase.HBaseDataStore;

public class HBaseDataStoreFactory {

	public static IDataStore createDataStore() {
		URI uri = URI.createURI("hbase://localhost/testmodel");
		HBaseDataStore baseDataStore = new HBaseDataStore(uri.path().substring(1), true);
//		return new DataStoreImpl(baseDataStore, uri);		
//		return new DataStoreImpl(new WriteCachingDataStore(new ScanningDataStore(baseDataStore, baseDataStore), baseDataStore), uri);
//		return new DataStoreImpl(new ScanningDataStore(baseDataStore, baseDataStore), uri);
		return new DataStoreImpl(new WriteCachingDataStore(baseDataStore, baseDataStore), uri);
	}
}
