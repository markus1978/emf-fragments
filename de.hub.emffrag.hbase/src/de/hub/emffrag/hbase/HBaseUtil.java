package de.hub.emffrag.hbase;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

public class HBaseUtil {
	
	public static final byte[] colFamily = "value".getBytes();
	public static final byte[] col = "value".getBytes();

	public static void dropTable(String name) {
		try {
			if (admin == null) {
				if (config == null) {
					getHBaseConfig(null);
				}
				admin = new HBaseAdmin(config);
			}
			if (admin.tableExists(name)) {
				admin.disableTable(name);
				admin.deleteTable(name);
			}
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}

	private static Configuration config = null;
	private static HBaseAdmin admin = null;
	
	public static Configuration getHBaseConfig(String hbaseRootDir) {
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

	public static HTable getHBaseTable(String tableName, boolean deleteExistingTable) throws MasterNotRunningException, ZooKeeperConnectionException {
		Preconditions.checkArgument(tableName != null);
		
		if (admin == null) {
			if (config == null) {
				getHBaseConfig(null);
			}
			admin = new HBaseAdmin(config);
		}
		
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
}
