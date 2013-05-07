package de.hub.emffrag.datastore2;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.eclipse.emf.common.util.URI;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import de.hub.emffrag.datastore.KeyType;

public abstract class AbstractScanningDataStore implements IDataStore, RemovalListener<byte[], DataMap<?>> {

	private final Cache<BigInteger, ICursor> cachedScanCursors = CacheBuilder.newBuilder().maximumSize(100).build();
	private final Cache<BigInteger, DataMap<?>> cachedMaps = CacheBuilder.newBuilder().weakKeys().weakValues().removalListener(this).build();
	 
	protected final URI uri;
	
	public AbstractScanningDataStore(URI uri) {
		super();
		this.uri = uri;
	}
	
	@Override
	public void onRemoval(RemovalNotification<byte[], DataMap<?>> notification) {
		notification.getValue().close();
	}

	@Override
	public final InputStream openInputStream(byte[] key) {
		ICursor cursor = cachedScanCursors.getIfPresent(new BigInteger(key));
		if (cursor == null) {
			cursor = cursor(key);
		}
		
		if (cursor == null) {
			return null;
		}
		
		InputStream result = cursor.openNextInputStream();
		if (cursor.hasNext()) {
			cachedScanCursors.put(new BigInteger(cursor.next()), cursor);
		}
		return result;
	}
	
	interface ICursor {
		public boolean hasNext();
		public byte[] next();
		public InputStream openNextInputStream();
	}
	
	protected abstract ICursor cursor(byte[] key);

	@SuppressWarnings("unchecked")
	@Override
	public <KT> IDataSortedMap<KT> getMap(final byte[] prefix, final KeyType<KT> keyType) {	
		try {
			return (IDataSortedMap<KT>)cachedMaps.get(new BigInteger(prefix), new Callable<DataMap<KT>>() {
				@Override
				public DataMap<KT> call() throws Exception {
					return new CachingDataMap<KT>(AbstractScanningDataStore.this, uri, prefix, keyType);
				}
			});
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		clearCaches();
	}
	
	public void clearCaches() {
		cachedMaps.invalidateAll();
		cachedMaps.cleanUp();
//		for (DataMap<?> map: cachedMaps.asMap().values()) {
//			map.close();
//			
//		}
		cachedScanCursors.invalidateAll();
		cachedScanCursors.cleanUp();
	}
	
	public long telemetryCachedMaps() {
		return cachedMaps.size();
	}
	
	public long telemetryCachedScanCursors() {
		return cachedScanCursors.size();
	}
}
