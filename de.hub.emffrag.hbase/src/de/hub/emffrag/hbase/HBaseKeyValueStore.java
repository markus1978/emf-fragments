/*******************************************************************************
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.hub.emffrag.hbase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.inject.Singleton;

import de.hub.emffrag.kvstore.IKeyValueStore;

@Singleton
public class HBaseKeyValueStore implements IKeyValueStore {

    private Configuration config = null;

    public static final byte[] colFamily = "value".getBytes();
    public static final byte[] col = "value".getBytes();

    private HBaseAdmin admin = null;    
    private Map<String, Table> tables = new HashMap<String, IKeyValueStore.Table>();

    private Configuration getHBaseConfig(String hbaseRootDir) {
        if (config == null) {
            config = HBaseConfiguration.create();

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

    public HTable getHBaseTable(String tableName) {
        Preconditions.checkArgument(tableName != null);
        HTable table = null;
        try {
            initialize();
            boolean tableExists = admin.tableExists(tableName);

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

    private void initialize() {
        try {
            if (admin == null) {
                if (config == null) {
                    getHBaseConfig(null);
                }
                admin = new HBaseAdmin(config);
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

    public boolean tableExists(String tableName) {
        initialize();
        try {
            return admin.tableExists(tableName);
        } catch (Exception e) {
            Throwables.propagate(e);
            return false;
        }
    }

    @Override
    public Table getTable(String tableName, boolean createOnDemand) {
        Preconditions.checkArgument(tableName != null);
        
        Table result = tables.get(tableName);
        if (result == null) {
	        try {
	            initialize();
	            if (createOnDemand) {
	                boolean tableExists = admin.tableExists(tableName);
	
	                if (!tableExists) {
	                    HTableDescriptor tableDescr = new HTableDescriptor(tableName);
	                    tableDescr.addFamily(new HColumnDescriptor(colFamily));
	                    admin.createTable(tableDescr);
	                }
	            }
	            result = new TableImpl(new HTable(config, tableName));
	        } catch (Exception e) {
	            Throwables.propagate(e);
	            return null;
	        }
	        tables.put(tableName, result);
        }
        return result;
    }

    @Override
    public void deleteTable(String tableName) {
        try {
            initialize();
            if (admin.tableExists(tableName)) {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

    private class TableImpl implements IKeyValueStore.Table {
        private final HTable hTable;
        private final Map<String, Put> putsCache = new HashMap<String, Put>();

        public TableImpl(HTable hTable) {
            super();
            this.hTable = hTable;
            hTable.setAutoFlush(false);
            try {
                hTable.setWriteBufferSize(100);
            } catch (IOException e) {
                Throwables.propagate(e);
            }
        }

        @Override
        public String get(String key) {
        	if (putsCache.get(key) != null) {
        		flush();
        	}
            Result result = null;
            try {
                result = hTable.get(new Get(key.getBytes()));
            } catch (IOException e) {
                Throwables.propagate(e);
            }
            byte[] value = result.getValue(colFamily, col);
			if (value == null) {
				return "";
			} else {
				return new String(value);
			}
        }

        @Override
        public void set(String key, String value) {        	
            Put put = new Put(key.getBytes());
            put.add(colFamily, col, value.getBytes());
            putsCache.put(key, put);
            if (putsCache.size() > 100) {
            	flush();
            }            
        }

        @Override
		public void remove(String key) {
        	try {
				hTable.delete(new Delete(key.getBytes()));
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}

		@Override
        public void flush() {
            try {
            	try {
                    hTable.put(new ArrayList<Put>(putsCache.values()));
                    putsCache.clear();
                } catch (IOException e) {
                    Throwables.propagate(e);
                }
                hTable.flushCommits();
            } catch (IOException e) {
                Throwables.propagate(e);
            }
        }

        @Override
        public boolean exists(String key) {
            try {
                return hTable.exists(new Get(key.getBytes()));
            } catch (IOException e) {
                Throwables.propagate(e);
                return false;
            }
        }

        @Override
        public String getLargestKey() {
            byte[] lastKey = null;
            try {
                lastKey = hTable.getRowOrBefore(new Long(Long.MAX_VALUE).toString().getBytes(), colFamily).getRow();
            } catch (IOException e) {
                Throwables.propagate(e);
            }
            return new String(lastKey);            
        }

    }
}
