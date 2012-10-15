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

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.fragmentation.IKeyValueTable;

public interface IKeyValueStore {
    
    public IKeyValueTable getTable(URI uri, boolean createOnDemand);
    
    public boolean tableExists(URI uri);
    
    public void deleteTable(URI uri);
    
}
