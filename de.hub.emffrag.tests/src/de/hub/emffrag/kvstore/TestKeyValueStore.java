package de.hub.emffrag.kvstore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.fragmentation.IKeyValueTable;

@Deprecated
public class TestKeyValueStore implements IKeyValueStore {
	
	private Map<String, IKeyValueTable> tables = new HashMap<String, IKeyValueTable>();
	
	private class TableImpl implements IKeyValueTable {
		
		private final Map<String, String> entries = new HashMap<String, String>();
		private long lastKey = 0;
		private final URI uri;
		
		public TableImpl(URI uri) {
			super();
			this.uri = uri;
		}

		@Override
		public URI createNewEntry() {
			return uri.appendSegment(Long.toString(lastKey++));
		}		

		@Override
		public URI createEntry(URI uri) {
			return uri;
		}

		@Override
		public boolean entryExists(URI uri) {
			return entries.get(uri.segment(1)) != null;
		}

		@Override
		public void flush() {
			
		}

		@Override
		public void removeAnEntry(URI uri) {
			entries.remove(uri.segment(1));
		}

		@Override
		public OutputStream createOutputStream(URI key) {
			return new ByteArrayOutputStream() {
				@Override
				public void close() throws IOException {
					super.close();
					String value = new String(toByteArray());
					entries.put(uri.segment(1), value);
				}
			};
		}

		@Override
		public InputStream createInputStream(URI key) {
			String value = entries.get(key.segment(1));
			if (value == null) {
				return null;
			} else {
		        return new ByteArrayInputStream(value.getBytes());
			}
		}

		@Override
		public String read(URI key) {
			return entries.get(key.segment(1));
		}

		@Override
		public void write(URI key, String value) {
			entries.put(key.segment(1), value);
		}	
		
		
	}

	@Override
	public IKeyValueTable getTable(URI uri, boolean createOnDemand) {
		IKeyValueTable table = tables.get(uri.segment(0));
		if (table == null && createOnDemand) {
			table = new TableImpl(uri);
			tables.put(uri.segment(0), table);
		}
		return table;
	}

	@Override
	public boolean tableExists(URI uri) {
		return tables.get(uri.segment(0)) != null;
	}

	@Override
	public void deleteTable(URI uri) {
		tables.remove(uri.segment(0));
	}

}
