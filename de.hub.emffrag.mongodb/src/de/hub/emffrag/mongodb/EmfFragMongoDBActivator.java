package de.hub.emffrag.mongodb;

import java.util.Map;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.FragmentedModelFactory;

public class EmfFragMongoDBActivator extends Plugin {

	public static EmfFragMongoDBActivator instance = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
		init();		
	}

	private void init() {
		Map<String, Object> protocolToFactoryMap = Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap();
		protocolToFactoryMap.put("mongodb", new FragmentedModelFactory() {
			@Override
			protected DataStore createDataStore(URI uri) {
				return new MongoDBDataStore(uri.authority(), uri.path().substring(1));
			}			
		});
	}
	
	public static void standalone() {		
		instance = new EmfFragMongoDBActivator();
		instance.init();
	}

}
