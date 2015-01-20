package de.hub.emffrag.datastore;

import org.eclipse.emf.common.util.URI;


public class DataStoreImpl extends AbstractDelegatingDataStore implements IDataStore {
	
	public static IDataStore createDataStore(URI uri) {
		return IDataStore.dataStoreFactoryRegistry.get(uri.scheme()).createDataStore(uri);
	}

	private final URI uri;
	
	public DataStoreImpl(IBaseDataStore baseDataStore, URI uri) {
		super(baseDataStore);
		this.uri = uri;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public <KT> IDataMap<KT> getMap(byte[] prefix, KeyType<KT> keyType) {
		return new DataMap<KT>(this, uri, prefix, keyType);
	}
	
}
