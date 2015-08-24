package de.hub.emffrag;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Assert;
import org.osgi.framework.BundleContext;

public class EmfFragActivator extends Plugin {
	
	public static EmfFragActivator instance = null;
	
	public boolean logInStandAlone = false;
	public boolean logFragmentPrettyPrints = false;
	
	private boolean isStandAlone = false;	
	private int warningsAndErrors = 0;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);		
		instance = this;
		init();		
	}

	private void init() {

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
