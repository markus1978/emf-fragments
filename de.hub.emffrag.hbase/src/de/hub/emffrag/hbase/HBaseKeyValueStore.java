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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.eclipse.emf.common.util.URI;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.inject.Singleton;

import de.hub.emffrag.fragmentation.IKeyValueTable;
import de.hub.emffrag.kvstore.IKeyValueStore;

@Singleton
public class HBaseKeyValueStore implements IKeyValueStore {

    private Configuration config = null;

    public static final byte[] colFamily = "value".getBytes();
    public static final byte[] col = "value".getBytes();

    private HBaseAdmin admin = null;    
    private Map<String, IKeyValueTable> tables = new HashMap<String, IKeyValueTable>();

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

    @Override
	public boolean tableExists(URI uri) {
        initialize();
        try {
            return admin.tableExists(uri.segment(0));
        } catch (Exception e) {
            Throwables.propagate(e);
            return false;
        }
	}

	@Override
    public IKeyValueTable getTable(URI tableURI, boolean createOnDemand) {
        Preconditions.checkArgument(tableURI != null);
        
        IKeyValueTable result = tables.get(tableURI);
        String tableName = tableURI.segment(0);
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
	            result = new TableImpl(tableURI, new HTable(config, tableName));
	        } catch (Exception e) {
	            Throwables.propagate(e);
	            return null;
	        }
	        tables.put(tableName, result);
        }
        return result;
    }

    @Override
    public void deleteTable(URI tableURI) {
    	String tableName = tableURI.segment(0);
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

    private class TableImpl implements IKeyValueTable {
        private final HTable hTable;
        private final URI baseURI;
        private final Map<String, Put> putsCache = new HashMap<String, Put>();

        public TableImpl(URI uri, HTable hTable) {
            super();
            this.baseURI = uri;
            this.hTable = hTable;
            hTable.setAutoFlush(false);
            try {
                hTable.setWriteBufferSize(100);
            } catch (IOException e) {
                Throwables.propagate(e);
            }
        }

        @Override
        public String read(URI key) {
        	if (putsCache.get(key) != null) {
        		flush();
        	}
            Result result = null;
            try {
                result = hTable.get(new Get(key.segment(1).getBytes()));
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
        public void write(URI key, String value) {        	
            Put put = new Put(key.segment(1).getBytes());
            put.add(colFamily, col, value.getBytes());
            putsCache.put(key.segment(1), put);
            if (putsCache.size() > 100) {
            	flush();
            }            
        }

		@Override
		public URI createEntry(URI uri) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public OutputStream createOutputStream(final URI key) {
			return new ByteArrayOutputStream() {
				@Override
				public void close() throws IOException {
					super.close();
					Put put = new Put(key.segment(1).getBytes());
					put.add(colFamily, col, toByteArray());
					putsCache.put(key.segment(1), put);
					if (putsCache.size() > 100) {
						flush();
					}
				}
			};
		}

		@Override
		public InputStream createInputStream(URI key) {
			if (putsCache.get(key) != null) {
        		flush();
        	}
            Result result = null;
            try {
                result = hTable.get(new Get(key.segment(1).getBytes()));
            } catch (IOException e) {
                Throwables.propagate(e);
            }
            byte[] value = result.getValue(colFamily, col);
            return new ByteArrayInputStream(value);			
		}

		@Override
		public void removeAnEntry(URI key) {
        	try {
				hTable.delete(new Delete(key.segment(1).getBytes()));
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
        public boolean entryExists(URI key) {
            try {
                return hTable.exists(new Get(key.segment(1).getBytes()));
            } catch (IOException e) {
                Throwables.propagate(e);
                return false;
            }
        }
        
        @Override
		public URI createNewEntry() {
    	   byte[] lastKey = null;
           try {
               lastKey = hTable.getRowOrBefore(new Long(Long.MAX_VALUE).toString().getBytes(), colFamily).getRow();
           } catch (IOException e) {
               Throwables.propagate(e);
           }
           return baseURI.appendSegment(new String(lastKey));
		}
    }
}
