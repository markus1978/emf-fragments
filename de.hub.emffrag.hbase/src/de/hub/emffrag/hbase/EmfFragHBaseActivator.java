package de.hub.emffrag.hbase;

import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.datastore.IDataStore;

public class EmfFragHBaseActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {

		IDataStore.dataStoreFactoryRegistry.put("hbase", new IDataStore.IDataStoreFactory() {
			@Override
			public IDataStore createDataStore(URI uri) {
				return HBaseDataStore.createDataStore(uri, 1000, 100);
			}
		});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
