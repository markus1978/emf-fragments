package de.hub.emffrag.datastore;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.internal.IDataMap;
import de.hub.emffrag.datastore.internal.KeyType;


public interface IDataStore extends IBaseDataStore {
	
	public interface IDataStoreFactory {
		IDataStore createDataStore(URI uri);
	}
	
	public static Map<String, IDataStoreFactory> dataStoreFactoryRegistry = new HashMap<String, IDataStoreFactory>();

	public URI getURI();
	
	public <KT> IDataMap<KT> getMap(byte[] prefix, KeyType<KT> keyType);
	
}
