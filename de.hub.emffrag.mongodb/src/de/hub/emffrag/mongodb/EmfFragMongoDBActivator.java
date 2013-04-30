package de.hub.emffrag.mongodb;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.BundleContext;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.FragmentedModelFactory;

public class EmfFragMongoDBActivator extends Plugin {

	public static EmfFragMongoDBActivator instance = null;
	public boolean tryToScan = false;
	
	private Map<String, DB> dataBases = new HashMap<String, DB>();

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
		init();		
	}
	
	public DB getDataBase(String host, int port) {
		DB dataBase = dataBases.get(host + port);
		if (dataBase == null) {
			MongoClient dbClient;
			try {
				ReadPreference.nearest();
				if (port != -1) {
					dbClient = new MongoClient(host, port);
				} else {
					dbClient = new MongoClient(host);	
				}			
			} catch (UnknownHostException e) {
				throw new IllegalArgumentException("Given host does not exists or DB is not running: " + host);	
			}
			dataBase = dbClient.getDB("emffrag");
			dataBases.put(host+port, dataBase);
		}
		return dataBase;				
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
		if (instance == null) {
			instance = new EmfFragMongoDBActivator();
			instance.init();
		}		
	}

}
