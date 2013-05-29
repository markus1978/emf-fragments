package de.hub.emffrag.datastore;

import java.io.InputStream;
import java.math.BigInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import de.hub.emffrag.datastore.IScanExtension.ICursor;

public class ScanningDataStore extends AbstractDelegatingDataStore implements RemovalListener<BigInteger, ICursor> {

	private final IScanExtension scanExtension;
	private final Cache<BigInteger, ICursor> cachedScanCursors = CacheBuilder.newBuilder().maximumSize(100).removalListener(this).build();
	
	public ScanningDataStore(IBaseDataStore baseDataStore, IScanExtension scanExtension) {
		super(baseDataStore);
		this.scanExtension = scanExtension;
	}

	@Override
	public void onRemoval(RemovalNotification<BigInteger, ICursor> notification) {
		if (notification.getCause() == RemovalCause.SIZE) { 
			notification.getValue().close();
		}
	}

	@Override
	public final InputStream openInputStream(byte[] key) {
		BigInteger keyAsBigInteger = new BigInteger(key);
		ICursor cursor = cachedScanCursors.getIfPresent(keyAsBigInteger);
		InputStream is = null;
		if (cursor == null) {
			cursor = scanExtension.cursor(key);
			if (cursor == null || !cursor.hasNext()) {
				return null;
			}

			BigInteger firstScannerKey = new BigInteger(cursor.next());
			if (firstScannerKey.equals(keyAsBigInteger)) {
				is = cursor.openNextInputStream();
			} 		
		} else {
			cachedScanCursors.invalidate(keyAsBigInteger);
			is = cursor.openNextInputStream();
		}

		if (cursor.hasNext()) {
			cachedScanCursors.put(new BigInteger(cursor.next()), cursor);
		}
		return is;
		
	}
}
