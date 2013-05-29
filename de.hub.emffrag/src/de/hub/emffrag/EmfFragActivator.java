package de.hub.emffrag;

import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import de.hub.emffrag.fragmentation.FGlobalEventListener;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.fragmentation.FragmentedModelFactory;
import de.hub.emffrag.fragmentation.IdSemantics;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;

public class EmfFragActivator extends Plugin {
	
	public FragmentedModel defaultModel = null;
	public boolean useBinaryFragments = false;
	public boolean collectStatistics = false;
	public FGlobalEventListener globalEventListener = FGlobalEventListener.emptyInstance;
	
	public IdSemantics idSemantics = new IndexBasedIdSemantics(IdBehaviour.strict);
	
	public static EmfFragActivator instance = null;
	
	public boolean logInStandAlone = false;
	private boolean isStandAlone = false;
	public int cacheSize = 100;
	
	private int warningsAndErrors = 0;


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
		
		try {
			instance.getLog();
		} catch (Exception e) {
			instance.isStandAlone = true;
		}
	}
	
	private void log(int level, String msg, Exception e) {
		if (!isStandAlone) {
			try {
				getLog().log(new Status(level, getBundle().getSymbolicName(), Status.OK, msg, e));
			} catch (Exception ex) {
				isStandAlone = true;
			}	
		}
		if (isStandAlone) {
			if (logInStandAlone) {
				System.out.println("LOG(" + level + "): " + (msg != null ? msg : "(null)") + (e != null ? ": " + e.getMessage() : ""));
			}
		}		
	}
	
	public void debug(String msg) {
		log(Status.OK, msg, null);
	}

	public void info(String msg) {
		log(Status.INFO, msg, null);
	}

	public void warning(String msg) {
		warningsAndErrors++;
		log(Status.WARNING, msg, null);		
	}
	
	public void warning(String msg, Exception e) {
		warningsAndErrors++;
		log(Status.WARNING, msg, e);
	}
	
	public void error(String msg) {
		warningsAndErrors++;
		log(Status.ERROR, msg, null);
	}
	
	public void error(String msg, Exception e) {
		warningsAndErrors++;
		log(Status.ERROR, msg, e);
	}	
	
	public void assertWarningsAndErrors(boolean value) {
		Assert.assertEquals(value, warningsAndErrors == 0);
	}
	
	public void resetWarningsAndErrors() {
		warningsAndErrors = 0;
	}

}
