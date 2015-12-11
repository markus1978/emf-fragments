package de.hub.emffrag.datastore;

import java.util.Map;

public interface IBulkInsertExtension {

	boolean bulkInsert(Map<byte[], byte[]> map);
	
}
