package de.hub.emffrag.datastore;

import org.eclipse.emf.common.util.URI;


public interface IDataStore extends IBaseDataStore {

	public URI getURI();
	
	public <KT> IDataMap<KT> getMap(byte[] prefix, KeyType<KT> keyType);
	
}
