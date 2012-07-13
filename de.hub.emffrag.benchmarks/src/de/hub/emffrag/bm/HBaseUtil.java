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
package de.hub.emffrag.bm;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

public class HBaseUtil {
	
	private Configuration config = null;
	
	public static final byte[] colFamily = "value".getBytes();
	public static final byte[] col = "value".getBytes();
	
	public Configuration getHBaseConfig(String hbaseRootDir) {
		if (config == null) {
			config = HBaseConfiguration.create();
			
			if (!(hbaseRootDir == null || hbaseRootDir.equals(""))) {
				String hbaseSite = ""
						 + "<?xml version='1.0'?>"
						 + "<?xml-stylesheet type='text/xsl' href='configuration.xsl'?>"
						 + "<configuration>"
						 + "  <property>"
						 + "    <name>hbase.zookeeper.quorum</name>"
						 + "    <value>" + hbaseRootDir + "</value>"
						 + "  </property>"
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

	public boolean dropTable(String tableName) {
		HBaseAdmin admin = null;
	    try {
            if (config == null) {
                getHBaseConfig(null);
            }
            admin = new HBaseAdmin(config);
    	    if (admin.tableExists(tableName)) {
        	    admin.disableTable(tableName);
        	    admin.deleteTable(tableName);
        	    return true;
    	    }    	    
	    } catch (Exception e) {
	        Throwables.propagate(e);
	    } finally {
	    	try {
				admin.close();
			} catch (IOException e) {
				Throwables.propagate(e);
			}
	    }
	    return false;
	}
	
	public HTable getHBaseTable(String tableName) {
	    Preconditions.checkArgument(tableName != null);
		HTable table = null;
		HBaseAdmin admin = null;
		try {
			if (config == null) {
				getHBaseConfig(null);
			}
			admin = new HBaseAdmin(config);
			
			boolean tableExists = admin.tableExists(tableName);
			
			if (!tableExists) {
				HTableDescriptor tableDescr = new HTableDescriptor(tableName);
				tableDescr.addFamily(new HColumnDescriptor(colFamily));
				admin.createTable(tableDescr);
			}
			
			table = new HTable(config, tableName);				
		} catch (Exception e) {
			Throwables.propagate(e);
		} finally {
			try {
				admin.close();
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
		return table;
	}

    public boolean tableExists(String tableName) {
        if (config == null) {
            getHBaseConfig(null);
        }
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(config);
            return admin.tableExists(tableName);
        } catch (Exception e) {
            Throwables.propagate(e);
            return false;
        } finally {
        	try {
				admin.close();
			} catch (IOException e) {
				Throwables.propagate(e);
			}
        }
     }
}
