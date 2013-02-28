package de.hub.emffrag.hbase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

import de.hub.emffrag.datastore.DataStore;

/**
 * HBase implementation of {@link DataStore}. This implementation currently only
 * works for HBase running on localhost.
 */
public class HBaseDataStore extends DataStore {

	private Configuration config = null;
	private HBaseAdmin admin = null;

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
		super("habse", "localhost", dataStoreId);
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
				String hbaseSite = "" + "<?xml version='1.0'?>" + "<?xml-stylesheet type='text/xsl' href='configuration.xsl'?>"
						+ "<configuration>" + "  <property>" + "    <name>hbase.zookeeper.quorum</name>" + "    <value>"
						+ hbaseRootDir + "</value>" + "  </property>"
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
			if (result.isEmpty()) {
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
		if (value == null) {
			return new ByteArrayInputStream(new byte[] {});
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
			return hTable.exists(new Get(key));
		} catch (IOException e) {
			Throwables.propagate(e);
			return false;
		}
	}

	@Override
	public boolean ckeckAndCreate(byte[] key) {
		initialize();
		// this feature is not yet working
//		RowLock lockRow = null;
//		try {
//			lockRow = hTable.lockRow(key);
//		} catch (IOException e) {
//			Throwables.propagate(e);
//		}
//		if (lockRow != null) {
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
//				try {
//					hTable.unlockRow(lockRow);
//				} catch (IOException e) {
//					Throwables.propagate(e);
//				}
			}
//		} else {
//			return false;
//		}
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
	
	
}
