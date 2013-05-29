package de.hub.emffrag.hbase;

import java.util.Map;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.ScanningDataStore;
import de.hub.emffrag.datastore.WriteCachingDataStore;
import de.hub.emffrag.fragmentation.FragmentedModelFactory;

public class EmfFragHBaseActivator extends Plugin {

	public static EmfFragHBaseActivator instance = null;
	public int scanCacheSize = 1000;
	public boolean useScanning = false;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
		init();		
	}

	private void init() {
		Map<String, Object> protocolToFactoryMap = Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap();
		protocolToFactoryMap.put("hbase", new FragmentedModelFactory() {
			@Override
			protected IDataStore createDataStore(URI uri) {
				HBaseDataStore baseDataStore = new HBaseDataStore(uri.path().substring(1));
				baseDataStore.setScanCacheSize(scanCacheSize);
				if (useScanning) {
					return new DataStoreImpl(new ScanningDataStore(new WriteCachingDataStore(baseDataStore, baseDataStore), baseDataStore), uri);
				} else {
					return new DataStoreImpl(new WriteCachingDataStore(baseDataStore, baseDataStore), uri);
				}				
			}			
		});
	}
	
	public static void standalone() {		
		if (instance == null) {
			instance = new EmfFragHBaseActivator();
			instance.init();
		}		
	}

}
