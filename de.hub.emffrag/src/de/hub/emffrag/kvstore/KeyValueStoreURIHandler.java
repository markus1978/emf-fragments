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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

import com.google.common.base.Preconditions;

public class KeyValueStoreURIHandler extends URIHandlerImpl{
    
    private final IKeyValueStore kvs;

    public KeyValueStoreURIHandler(IKeyValueStore kvs) {
        super();
        this.kvs = kvs;
    }

    @Override
    public boolean canHandle(URI uri) {
        String scheme = uri.scheme();
        return scheme != null && scheme.equals("hbase");
    }

    @Override
    public OutputStream createOutputStream(final URI uri, Map<?, ?> options) throws IOException {
        return new ByteArrayOutputStream() {
            @Override
            public void close() throws IOException {
                super.close();
                String value = new String(toByteArray());
                IKeyValueStore.Table table = kvs.getTable(uri.segment(0), true);
                table.set(uri.segment(1), value);
            }            
        };
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        Preconditions.checkArgument(uri.segmentCount() == 2);
        IKeyValueStore.Table table = kvs.getTable(uri.segment(0), false);
        String value = table.get(uri.segment(1));
        return new ByteArrayInputStream(value.getBytes());
    }

	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
        IKeyValueStore.Table table = kvs.getTable(uri.segment(0), true);
        table.remove(uri.segment(1));
	}
    
    

}
