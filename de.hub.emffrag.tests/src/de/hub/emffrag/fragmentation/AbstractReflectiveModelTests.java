package de.hub.emffrag.fragmentation;

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

public class AbstractReflectiveModelTests extends AbstractTests {
	
	protected TestModelPackage metaModel = null;
	protected FInternalObjectImpl object1 = null;
	protected FInternalObjectImpl object2 = null;
	protected FInternalObjectImpl object3 = null;
	protected DataStore dataStore = null;
	
	@Before
	public void standardInitialization() {
		dataStore = createTestDataStore();
		metaModel = ReflectiveMetaModelRegistry.instance.registerUserMetaModel(TestModelPackage.eINSTANCE);
		object1 = new FInternalObjectImpl(metaModel.getTestObject());
		object2 = new FInternalObjectImpl(metaModel.getTestObject());
		object3 = new FInternalObjectImpl(metaModel.getTestObject());
	}
	
	protected ResourceFactoryImpl createResourceFactoryImpl() {
		return new XMIResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new XMIFragmentImpl(uri, null);
			}
		};
	}
	
	protected ResourceSet createAndConfigureAResourceSet(DataStore dataStore, EPackage... metaModels) {
		ResourceSet resourceSet = new ResourceSetImpl();
		for (EPackage metaModel : metaModels) {
			resourceSet.getPackageRegistry().put(metaModel.getNsURI(), metaModel);
			metaModel.setEFactoryInstance(new org.eclipse.emf.ecore.impl.EFactoryImpl() {
				@Override
				public EObject create(EClass eClass) {
					return new FInternalObjectImpl(eClass);
				}
			});
		}
		resourceSet.getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(dataStore.getProtocol(), createResourceFactoryImpl()); 
		return resourceSet;
	}

	protected Resource[] createResourceSet(DataStore dataStore, EPackage metaModel, int numberOfResources, boolean loadResources) {
		DataIndex<Long> index = new DataIndex<Long>(dataStore, "f_", LongKeyType.instance);
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
