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

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;


public class HBasePerformance {
    private double sizes[] = new double[] { 25000/*1e6, 2e6, 3e6, 4e6, 5e6, 6e6, 7e5, 8e6, 9e6, 1e7, 2e7, 3e7, 4e7, 5e7, 6e7, 7e7, 8e7, 9e7, 1e8, 2e8, 3e8, 1e9*/}; //Util.logSpace(9, 10, 20);
    private int repetitions = 25000;
    private int valueSize = 25000;
    private Random random = new Random();
    
    private HBaseUtil hBaseUtil = new HBaseUtil();
    
    public double performMeasure(long size) {
        String tableName = "test" + size;
        HTable table = null;
//        hBaseUtil.dropTable(tableName);
        if (!hBaseUtil.tableExists(tableName)) { 
            hBaseUtil.dropTable(tableName);
            table = hBaseUtil.getHBaseTable(tableName);
            
            Random valueRandom = new Random();
            
            List<Put> puts = new ArrayList<Put>(1000);
            for (int i = 0; i < size; i++) {
                byte[] key = ("" + i).getBytes();
                Put put = new Put(key);
                
                byte[] value = new byte[valueSize];
                valueRandom.nextBytes(value);           
                
                put.add(HBaseUtil.colFamily, HBaseUtil.col, value);
                puts.add(put);
                if (puts.size() > 1000) {
                    System.out.println("#"  + i);
                    try {
                        table.put(puts);
                    } catch (Exception e) {
                        System.err.println("Unexpected DB error: " + e.getMessage());
                        return 0;
                    }
                    puts.clear();
                }
            }
            try {
                table.put(puts);
            } catch (Exception e) {
                System.err.println("Unexpected DB error: " + e.getMessage());
                return 0;
            }
        } else {
            table = hBaseUtil.getHBaseTable(tableName);
        }
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < repetitions; i++) {
            double index = random.nextDouble()*size;
            Get get = new Get((""+(long)index).getBytes());
            Result result = null;
            try {
                result = table.get(get);
            } catch (Exception e) {
                System.err.println("Unexpected DB error: " + e.getMessage());
                return 0;
            }
            result.getValue(HBaseUtil.colFamily, HBaseUtil.col);
        }
        long end = System.currentTimeMillis();
        
        return (end - start) / (1.0 * repetitions);
    }
    
    public void performExperiment() {
    	PrintStream out = null;
        try {
            out = new PrintStream(new File("out.csv"));
            System.out.println(sizes.toString());
            for (int run = 0; run < 1; run++) {         
                for (double size: sizes) {
                    System.gc();
                    double result = performMeasure((int)size);
                    String outString = run + " " + (int)size + " " + result;
                    out.println(outString);
                    System.out.println(outString);
                }
            }
        } catch (Exception e) {
            System.err.println("IO error: " + e.getMessage());
        } finally {
        	out.close();
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        new HBasePerformance().performExperiment();
    }
}
