package de.hub.emffrag.hbase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.datastore.IBulkInsertExtension;
import de.hub.emffrag.datastore.IScanExtension;

/**
 * HBase implementation of {@link DataStore}. This implementation currently only
 * works for HBase running on localhost.
 */
public class HBaseDataStore implements IBaseDataStore, IBulkInsertExtension, IScanExtension {

	private Configuration config = null;
	private HBaseAdmin admin = null;
	
	private int scanCacheSize = 1000;

	private final String dataStoreId;
	private final boolean deleteExistingTable;
	private HTable hTable;

	private static final byte[] colFamily = "value".getBytes();
	private static final byte[] col = "value".getBytes();

	/**
	 * @param dataStoreId
	 *            Each model is covered by one table. This parameter is the
	 *            table name.
	 */
	public HBaseDataStore(String dataStoreId) {
		this(dataStoreId, false);
	}

	/**
	 * see {@link #HBaseDataStore(String)}
	 * 
	 * @param deleteExistingTable
	 *            if true and the table dataStoreId already exists it gets
	 *            deleted first.
	 */
	public HBaseDataStore(String dataStoreId, boolean deleteExistingTable) {
		this.dataStoreId = dataStoreId;
		this.deleteExistingTable = deleteExistingTable;
	}

	private void initialize() {
		try {
			if (admin == null) {
				if (config == null) {
					getHBaseConfig(null);
				}
				admin = new HBaseAdmin(config);
			}
			if (hTable == null) {
				hTable = getHBaseTable(dataStoreId, deleteExistingTable);
			}
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}

	private Configuration getHBaseConfig(String hbaseRootDir) {
		if (config == null) {
			config = HBaseConfiguration.create();
			config.set("hbase.zookeeper.quorum", "localhost");

			if (!(hbaseRootDir == null || hbaseRootDir.equals(""))) {
				String hbaseSite = "" + "<?xml version='1.0'?>" + "<?xml-stylesheet type='text/xsl' href='configuration.xsl'?>" + "<configuration>"
						+ "  <property>" + "    <name>hbase.zookeeper.quorum</name>" + "    <value>" + hbaseRootDir + "</value>" + "  </property>"
						+ "</configuration>                                                ";

				ByteArrayInputStream baos = new ByteArrayInputStream(hbaseSite.getBytes());
				config.addResource(baos);
				try {
					baos.close();
				} catch (IOException e) {
					Throwables.propagate(e);
				}
			}
		}
		return config;
	}

	private HTable getHBaseTable(String tableName, boolean deleteExistingTable) {
		Preconditions.checkArgument(tableName != null);
		HTable table = null;
		try {
			boolean tableExists = admin.tableExists(tableName);

			if (tableExists && deleteExistingTable) {
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
				tableExists = false;
			}

			if (!tableExists) {
				HTableDescriptor tableDescr = new HTableDescriptor(tableName);
				tableDescr.addFamily(new HColumnDescriptor(colFamily));
				admin.createTable(tableDescr);
			}

			table = new HTable(config, tableName);
		} catch (Exception e) {
			Throwables.propagate(e);
		}
		return table;
	}

	@Override
	public byte[] ceiling(byte[] key) {
		initialize();
		try {
			ResultScanner scanner = hTable.getScanner(new Scan(key));
			Result result = scanner.next();
			if (result == null || result.isEmpty()) {
				return null;
			} else {
				return result.getRow();
			}
		} catch (IOException e) {
			Throwables.propagate(e);
			return null;
		}
	}

	@Override
	public byte[] floor(byte[] key) {
		initialize();
		Result result;
		try {
			result = hTable.getRowOrBefore(key, colFamily);
			if (result == null || result.isEmpty()) {
				return null;
			} else {
				return result.getRow();
			}
		} catch (IOException e) {
			Throwables.propagate(e);
			return null;
		}
	}

	@Override
	public InputStream openInputStream(byte[] key) {
		initialize();
		Result result = null;
		try {
			result = hTable.get(new Get(key));
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		byte[] value = result.getValue(colFamily, col);
		if (value == null || result.isEmpty()) {
			return null;
		} else {
			return new ByteArrayInputStream(value);
		}
	}

	@Override
	public OutputStream openOutputStream(final byte[] key) {
		initialize();
		return new ByteArrayOutputStream() {
			@Override
			public void close() throws IOException {
				super.close();
				Put put = new Put(key);
				put.add(colFamily, col, toByteArray());
				hTable.put(put);
			}
		};
	}

	@Override
	public boolean check(byte[] key) {
		initialize();
		try {
			return !hTable.exists(new Get(key));
		} catch (IOException e) {
			Throwables.propagate(e);
			return false;
		}
	}

	@Override
	public boolean checkAndCreate(byte[] key) {
		initialize();
		// this feature is not yet working
		// RowLock lockRow = null;
		// try {
		// lockRow = hTable.lockRow(key);
		// } catch (IOException e) {
		// Throwables.propagate(e);
		// }
		// if (lockRow != null) {
		try {
			Result result = hTable.get(new Get(key));
			if (result.isEmpty()) {
				Put put = new Put(key);
				put.add(colFamily, col, new byte[] {});
				hTable.put(put);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Throwables.propagate(e);
		} finally {
			// try {
			// hTable.unlockRow(lockRow);
			// } catch (IOException e) {
			// Throwables.propagate(e);
			// }
		}
		// } else {
		// return false;
		// }
		return false;
	}

	@Override
	public void delete(byte[] bytes) {
		initialize();
		try {
			hTable.delete(new Delete(bytes));
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}

	@Override
	public void drop() {
		initialize();
		try {
			admin.deleteTable(dataStoreId);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}

	@Override
	public void close() {
		try {
			hTable.close();
			hTable = null;
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}
	
	@Override
	public void flush() {
	
	}

	private class Cursor implements ICursor {
		final ResultScanner scanner;
		final Iterator<Result> iterator;
		byte[] currentValue = null;
		
		public Cursor(ResultScanner scanner) {
			super();
			this.scanner = scanner;
			this.iterator = scanner.iterator();
			this.currentValue = null;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public byte[] next() {
			try {
				Result next = iterator.next();
				currentValue = next.getValue(colFamily, col);
				return next.getRow();
			} catch (Exception e) {
				EmfFragActivator.instance.warning("Scanner throws exception", e);
				return null;
			}
		}

		@Override
		public InputStream openNextInputStream() {
			if (currentValue == null) {
				return null;
			} else {
				return new ByteArrayInputStream(currentValue);
			}
		}

		@Override
		public void close() {
			scanner.close();
		}

	}

	@Override
	public ICursor cursor(byte[] key) {
		initialize();
		ResultScanner scanner = null;
		try {
			Scan scan = new Scan(key);
			scan.setCaching(scanCacheSize);
			scanner = hTable.getScanner(scan);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		
		return new Cursor(scanner);	
	}

	@Override
	public boolean bulkInsert(Map<byte[], byte[]> map) {
		initialize();
		List<Put> puts = new ArrayList<Put>();
		for (Entry<byte[], byte[]> entry: map.entrySet()) {
			Put put = new Put(entry.getKey());
			put.add(colFamily, col, entry.getValue());
			puts.add(put);
		}
	
		try {
			hTable.put(puts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public void setScanCacheSize(int scanCacheSize) {
		this.scanCacheSize = scanCacheSize;
	}

}
