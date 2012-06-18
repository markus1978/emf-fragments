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

public interface IKeyValueStore {

    public interface Table {    
        public String get(String key);        
        public void set(String key, String value);
        public boolean exists(String key);
        public String getLargestKey();
        public void flush();
		public void remove(String key);
    }
    
    public Table getTable(String name, boolean createOnDemand);
    
    public boolean tableExists(String name);
    
    public void deleteTable(String name);
    
}
