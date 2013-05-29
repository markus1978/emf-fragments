package de.hub.emffrag.fragmentation;

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;

import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.IDataMap;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class AbstractReflectiveModelTests extends AbstractTests {
	
	protected EPackage metaModel = null;
	protected FInternalObjectImpl object1 = null;
	protected FInternalObjectImpl object2 = null;
	protected FInternalObjectImpl object3 = null;
	protected IDataStore dataStore = null;
	
	private EFactory originalFactories[];
	private EPackage metaModels[];
	
	@Before
	public void standardInitialization() {
		originalFactories = null;
		metaModels = null;
		
		dataStore = createTestDataStore();
		metaModel = ReflectiveMetaModelRegistry.instance.registerUserMetaModel(TestModelPackage.eINSTANCE);
		object1 = new FInternalObjectImpl((EClass)metaModel.getEClassifier("TestObject"));
		object2 = new FInternalObjectImpl((EClass)metaModel.getEClassifier("TestObject"));
		object3 = new FInternalObjectImpl((EClass)metaModel.getEClassifier("TestObject"));
	}
	
	protected ResourceFactoryImpl createResourceFactoryImpl() {
		return new XMIResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new XMIFragmentImpl(uri, null);
			}
		};
	}
	
	protected ResourceSet createAndConfigureAResourceSet(IDataStore dataStore, EPackage... metaModels) {
		ResourceSet resourceSet = new ResourceSetImpl();
		boolean saveOriginalFactories = false;
		if (this.originalFactories == null) {
			this.metaModels = metaModels;
			this.originalFactories = new EFactory[metaModels.length];
			saveOriginalFactories = true;
		}
		int i = 0;
		for (EPackage metaModel : metaModels) {
			resourceSet.getPackageRegistry().put(metaModel.getNsURI(), metaModel);
			if (saveOriginalFactories) {
				originalFactories[i++] = metaModel.getEFactoryInstance();
			}
			metaModel.setEFactoryInstance(new org.eclipse.emf.ecore.impl.EFactoryImpl() {
				@Override
				public EObject create(EClass eClass) {
					return new FInternalObjectImpl(eClass);
				}
			});
		}
		resourceSet.getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(dataStore.getURI().scheme(), createResourceFactoryImpl()); 
		return resourceSet;
	}
	
	@After
	public void reset() {
		int i = 0;
		if (metaModels != null) {
			for (EPackage metaModel: metaModels) {
				metaModel.setEFactoryInstance(originalFactories[i++]);
			}
		}
	}

	protected Resource[] createResourceSet(IDataStore dataStore, EPackage metaModel, int numberOfResources, boolean loadResources) {
		IDataMap<Long> index = dataStore.getMap("f_".getBytes(), LongKeyType.instance);
		ResourceSet resourceSet = createAndConfigureAResourceSet(dataStore, metaModel);

		Resource[] resources = new Resource[numberOfResources];
		for (int i = 0; i < numberOfResources; i++) {
			URI uri = index.getURI((long) i);
			if (loadResources) {
				resources[i] = resourceSet.getResource(uri, true);
			} else {
				resources[i] = resourceSet.createResource(uri);
			}
		}

		return resources;
	}

	protected void saveResources(Resource[] resources) {
		for (Resource resource : resources) {
			try {
				if (resource.getResourceSet() != null) {
					resource.save(null);
				}
			} catch (IOException e) {
				Assert.assertTrue("IO error that could not be happening: " + e.getMessage(), false);
			}
		}
	}

}
