package de.hub.emffrag.mongodb;

import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.datastore.IDataStore;

public class EmfFragMongoDBActivator implements BundleActivator {

	public static EmfFragMongoDBActivator instance = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
		init();
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}
	
	private void init() {
		IDataStore.dataStoreFactoryRegistry.put("mongodb", new IDataStore.IDataStoreFactory() {			
			@Override
			public IDataStore createDataStore(URI uri) {
				return MongoDBDataStore.createDataStore(uri, false);
			}
		});
	}

	public static void standalone() {
		instance = new EmfFragMongoDBActivator();
		instance.init();
	}
}
