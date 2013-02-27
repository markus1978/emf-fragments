package de.hub.emffrag;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.fragmentation.FragmentedModel;

public class EmfFragActivator extends Plugin {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		init();
	}

	private static void init() {
		Map<String, Object> protocolToFactoryMap = Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap();
		protocolToFactoryMap.put("memory", new Resource.Factory() {
			public Map<String, DataStore> stores = new HashMap<String, DataStore>();
			@Override
			public Resource createResource(URI uri) {											
				String id = uri.authority() + uri.path();
				DataStore dataStore = stores.get(id);
				if (dataStore == null) {
					dataStore = new InMemoryDataStore(uri.scheme(), uri.authority(), uri.path().substring(1), false);
					stores.put(id, dataStore);
				}
				return new FragmentedModel(dataStore);
			}		
		});		
	}
	
	public static void standalone(EPackage... metaModels) {
		for (EPackage metaModel: metaModels) {
			EPackage.Registry.INSTANCE.put(metaModel.getNsURI(), metaModel);
		}
		EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		
		init();
	}

}
