package de.hub.emffrag.mongodb;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;

public class MongoDBDataStoreFactory {

	public static IDataStore createDataStore() {
		URI uri = URI.createURI("mongodb://localhost/testmodel");
		MongoDBDataStore baseDataStore = new MongoDBDataStore(uri.authority(), uri.path().substring(1), true);
		return new DataStoreImpl(baseDataStore, uri);		
//		return new DataStoreImpl(new WriteCachingDataStore(new ScanningDataStore(baseDataStore, baseDataStore), baseDataStore), uri);
//		return new DataStoreImpl(new ScanningDataStore(baseDataStore, baseDataStore), uri);
//		return new DataStoreImpl(new WriteCachingDataStore(baseDataStore, baseDataStore), uri);
	}
}
