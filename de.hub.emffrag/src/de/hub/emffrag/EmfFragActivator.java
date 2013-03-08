package de.hub.emffrag;

import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.fragmentation.FragmentedModelFactory;
import de.hub.emffrag.fragmentation.IdSemantics;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;

public class EmfFragActivator extends Plugin {
	
	public enum IndexedValueSetBahaviour { strict, neverContains };

	public FragmentedModel defaultModel = null;
	public IndexedValueSetBahaviour indexedValueSetBahaviour = IndexedValueSetBahaviour.strict;
	public boolean useBinaryFragments = false;
	public boolean collectStatistics = false;
	
	public IdSemantics idSemantics = new IndexBasedIdSemantics(IdBehaviour.strict);
	
	public static EmfFragActivator instance = null;


	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);		
		instance = this;
		init();		
		
		Bundle bundle = Platform.getBundle("de.hub.emffrag.mongodb");
		if (bundle != null) {
			try {
				bundle.start();
			} catch (Exception e) {
				
			}
		}
	}

	private void init() {
		Map<String, Object> protocolToFactoryMap = Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap();
		protocolToFactoryMap.put("memory", new FragmentedModelFactory());
	}
	
	public static void standalone(EPackage... metaModels) {
		for (EPackage metaModel: metaModels) {
			EPackage.Registry.INSTANCE.put(metaModel.getNsURI(), metaModel);
		}
		EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		
		instance = new EmfFragActivator();
		instance.init();
	}

}
