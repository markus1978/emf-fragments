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
package de.hub.emffrag.datastore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

import de.hub.emffrag.EmfFragActivator;
import de.hub.util.Ansi;
import de.hub.util.Ansi.Color;


public class DataStoreURIHandler extends URIHandlerImpl {
    
    private final de.hub.emffrag.datastore.IDataStore store;

    public DataStoreURIHandler(de.hub.emffrag.datastore.IDataStore store) {
		super();
		this.store = store;
	}

	@Override
    public boolean canHandle(URI uri) {
		return uri.equals(store.getURI());
        //String scheme = uri.scheme();
        //return scheme == null || scheme.equals(store.getURI().scheme());
    }

    @Override
    public OutputStream createOutputStream(final URI uri, Map<?, ?> options) throws IOException {
    	OutputStream result = store.openOutputStream(URIUtils.decode(uri.segment(1)));
    	if (result == null) {
    		EmfFragActivator.instance.error(
    				Ansi.format("URIHANDLER: ", Color.BLACK) +
    				Ansi.format("could not create outputstream for ", Color.RED) + uri.toString());
    		throw new IOException("Requested resource for URI " + uri.toString() + " does not exist.");
    	}
    	return result;
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
    	InputStream result = store.openInputStream(URIUtils.decode(uri.segment(1)));
    	if (result == null) {
    		EmfFragActivator.instance.error(
    				Ansi.format("URIHANDLER: ", Color.BLACK) +
    				Ansi.format("could not create inputstream for ", Color.RED) + uri.toString());
    		throw new IOException("Requested resource for URI " + uri.toString() + " does not exist.");
    	}
		return result;
    }

	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
		store.delete(URIUtils.decode(uri.segment(1)));
	}
    
}
