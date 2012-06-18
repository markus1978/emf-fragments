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
package de.hub.emffrag.kvstore;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;

@Singleton
public class TestKeyValueStore implements IKeyValueStore {

	private Map<String, TestTable> tables = new HashMap<String, TestTable>();
	
	public static final TestKeyValueStore INSTANCE = new TestKeyValueStore();
	
	private class TestTable implements IKeyValueStore.Table {

		private Map<String, String> map = new HashMap<String, String>();
		private List<String> keys = new ArrayList<String>();
		
		@Override
		public String get(String key) {
			return map.get(key);			
		}

		@Override
		public void set(String key, String value) {
			Object old = map.put(key, value);
			if (old == null) {
				keys.add(key);
				Collections.sort(keys);
			}
		}

		@Override
		public boolean exists(String key) {
			return map.get(key) != null;
		}

		@Override
		public String getLargestKey() {
			if (keys.isEmpty()) {
				return null;
			} else {
				return keys.get(keys.size() - 1);
			}
		}

		@Override
		public void remove(String key) {
			map.remove(key);
			keys.remove(key);
			Collections.sort(keys);
		}

		@Override
		public void flush() {
			
		}

		public void print(PrintStream out) {
			for (String key: keys) {
				out.println("#" + key);
				out.println(map.get(key));
			}
		}
		
	}
	
	@Override
	public Table getTable(String name, boolean createOnDemand) {
		TestTable table = tables.get(name);
		if (table == null && createOnDemand) {
			table = new TestTable();
			tables.put(name, table);
		}
		return table;
	}

	@Override
	public boolean tableExists(String name) {
		return tables.get(name) != null;
	}

	@Override
	public void deleteTable(String name) {
		tables.remove(name);
	}
	
	public void print(PrintStream out) {
		for (String name: tables.keySet()) {
			out.println("TABLE " + name);
			tables.get(name).print(out);
		}
	}

}
