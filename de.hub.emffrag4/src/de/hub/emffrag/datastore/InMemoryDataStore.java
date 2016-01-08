package de.hub.emffrag.datastore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import de.hub.emffrag.datastore.internal.URIUtils;


public class InMemoryDataStore implements IBaseDataStore, IBulkInsertExtension {
	
	public static final Comparator<byte[]> byteComparator = new Comparator<byte[]>() {
		@Override
		public int compare(byte[] o1, byte[] o2) {
			return compareBytes(o1, o2);
		}
	};

	/**
	 * Comparator for byte arrays as used in HBase, supposed to be
	 * lexicographically.
	 */
	public static int compareBytes(byte[] left, byte[] right) {
		for (int i = 0, j = 0; i < left.length && j < right.length; i++, j++) {
			int a = (left[i] & 0xff);
			int b = (right[j] & 0xff);
			if (a != b) {
				return Integer.valueOf(a).compareTo(Integer.valueOf(b)); // Integer.compare is not present in java 1.6
			}
		}
		return Integer.valueOf(left.length).compareTo(Integer.valueOf(right.length)); // Integer.compare is not present in java 1.6
	}

	private static final byte[] EMTPY = new byte[] { 0 };
	
	private TreeMap<byte[], byte[]> store = new TreeMap<byte[], byte[]>(byteComparator);

	private final boolean fleeting;

	public InMemoryDataStore(boolean fleeting) {
		this.fleeting = fleeting;
	}
	
	@Override
	public boolean bulkInsert(Map<byte[], byte[]> map) {
		store.putAll(map);	
		return true;
	}
	
	public IScanExtension createScanningScanExtension() {
		return new IScanExtension() {
			@Override
			public ICursor cursor(final byte[] key) {	
				return new ICursor() {
					byte[] currentKey = null;
					@Override
					public InputStream openNextInputStream() {
						byte[] value = store.get(currentKey);
						if (value != null) {
							return new ByteArrayInputStream(value);
						} else  {
							return null;
						}
					}
					
					@Override
					public byte[] next() {
						currentKey = store.tailMap(currentKey).entrySet().iterator().next().getKey();
						return currentKey;
					}
					
					@Override
					public boolean hasNext() {
						if (currentKey == null) {
							currentKey = key;
						}
						return store.tailMap(currentKey).entrySet().iterator().hasNext();
					}

					@Override
					public void close() {
						currentKey = null;
					}
											
				};
			}
		};
	}
	
	public IScanExtension createFirstOnlyScanExtension() {
		return new IScanExtension() {
			@Override
			public ICursor cursor(byte[] key) {
				final byte[] value = store.get(key);
				if (value == null) {
					return null;
				}
				return new ICursor() {	
					boolean first = true;
					@Override
					public InputStream openNextInputStream() {
						return new ByteArrayInputStream(value);
					}
					
					@Override
					public byte[] next() {
						if (first) {
							first = false;
							return value;
						} else {
							throw new IndexOutOfBoundsException();
						}
					}
					
					@Override
					public boolean hasNext() {
						return first;
					}

					@Override
					public void close() {
						
					}
					
				};
			}
		};
	}

	@Override
	public byte[] ceiling(byte[] key) {
		return store.ceilingKey(key);
	}

	@Override
	public byte[] floor(byte[] key) {
		return store.floorKey(key);
	}
	
	@Override
	public InputStream openInputStream(byte[] key) {
		byte[] value = store.get(key);
		if (value != null) {
			return new ByteArrayInputStream(value);
		} else {
			return null;
		}
	}

	@Override
	public void close() {

	}

	@Override
	public void flush() {
		
	}

	@Override
	public OutputStream openOutputStream(final byte[] key) {
		byte[] value = store.get(key);
		if (value != null) {
			return new ByteArrayOutputStream() {
				@Override
				public void close() throws IOException {
					super.close();
					byte[] value = toByteArray();
					store.put(key, fleeting ? EMTPY : value);
				}
			};
		} else {
			throw new IllegalArgumentException("Datastore entry with key " + URIUtils.encode(key) + " does not exist and cannot be written to.");
		}
		
	}

	@Override
	public boolean check(byte[] key) {
		return store.get(key) == null;
	}

	@Override
	public boolean checkAndCreate(byte[] key) {
		boolean result = check(key);
		if (result) {
			store.put(key, EMTPY);
		}
		return result;
	}

	@Override
	public void delete(byte[] key) {
		if (store.get(key) != null) {
			store.remove(key);
		} else {
			throw new IllegalArgumentException("Datastore entry with key " + URIUtils.encode(key) + " does not exist and cannot be deleted.");
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		for (byte[] key: store.keySet()) {
			buffer.append("key: ");
			for (byte b: key) {
				buffer.append(b + " ");
			}
			buffer.append(", URI: " + "/" + URIUtils.encode(key) + "\n");
			buffer.append("value: ");
			buffer.append(new String(store.get(key)) + "\n");
		}
		return buffer.toString();
	}

	@Override
	public void drop() {
		store.clear();
	}
	
	public int getNumberOfEntries() {
		return store.size();
	}

	@Override
	public Object getStats() {
		return "count=" + store.keySet().size();
	}
}
