package de.hub.emffrag.fragmentation;

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.ReflectiveMetaModelRegistry;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;

/**
 * EMF-Fragments needs a customized version of the used client meta-models as
 * meta-models for internal reflective object. These meta-models are generated
 * from client meta-models via {@link ReflectiveMetaModelRegistry}. This class
 * contains tests for the generated meta-models. Since EMF-Fragments heavily
 * depends on EMF internals, we think these tests are necessary to detect
 * possible slight changes in EMF semantics.
 */
public class ReflectiveMetaModelTests extends AbstractTests {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testContainmentReferences() {
		TestModelPackage metaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		FInternalObjectImpl internalContainer = new FInternalObjectImpl(metaModel.getTestObject());
		FInternalObjectImpl internalContents = new FInternalObjectImpl(metaModel.getTestObject());
		EReference fragmentedContentsReference = metaModel.getTestObject_FragmentedContents();
		EList contents = (EList) internalContainer.eGet(fragmentedContentsReference);
		contents.add(internalContents);
		
		Assert.assertEquals(1, contents.size());
		Assert.assertEquals(internalContents, contents.get(0));
	}
	
	@Test
	public void testIsSet() {
		TestModelPackage metaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		FInternalObjectImpl internalContainer = new FInternalObjectImpl(metaModel.getTestObject());
		internalContainer.eIsSet(metaModel.getTestObject_RegularContents());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testNonContainmentReferences() {
		TestModelPackage metaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		FInternalObjectImpl t1 = new FInternalObjectImpl(metaModel.getTestObject());
		FInternalObjectImpl t2 = new FInternalObjectImpl(metaModel.getTestObject());
		EReference testObject_crossReferences = metaModel.getTestObject_CrossReferences();		
		EList crossReferences = (EList) t1.eGet(testObject_crossReferences);
		crossReferences.add(t2);
		
		Assert.assertEquals(1, crossReferences.size());
		Assert.assertEquals(t2, crossReferences.get(0));
	}
	
	private ResourceSet createAndConfigureAResourceSet(DataStore dataStore, EPackage... metaModels) {
		ResourceSet resourceSet = new ResourceSetImpl();
		for (EPackage metaModel: metaModels) {
		resourceSet.getPackageRegistry().put(metaModel.getNsURI(), metaModel);
			metaModel.setEFactoryInstance(new org.eclipse.emf.ecore.impl.EFactoryImpl() {
				@Override
				public EObject create(EClass eClass) {
					return new FInternalObjectImpl(eClass);
				}
			});
		}
		resourceSet.getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("hbase", new XMIResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new Fragment(uri, null);
			}
		});
		return resourceSet;
	}
	
	private Resource[] createResourceSet(DataStore dataStore, EPackage metaModel, int numberOfResources, boolean loadResources) {
		DataIndex<Long> index = new DataIndex<Long>(dataStore, "f_", LongKeyType.instance);
		ResourceSet resourceSet = createAndConfigureAResourceSet(dataStore, metaModel);
				
		Resource[] resources = new Resource[numberOfResources];
		for (int i = 0; i < numberOfResources; i++) {
			URI uri = index.getURI((long)i);
			if (loadResources) {
				resources[i] = resourceSet.getResource(uri, true);
			} else {
				resources[i] = resourceSet.createResource(uri);
			}
		}
		
		return resources;
	}
	
	private void saveResources(Resource[] resources) {
		for (Resource resource: resources) {
			try {
				if (resource.getResourceSet() != null) {
					resource.save(null);				
				}
			} catch (IOException e) {
				Assert.assertTrue("IO error that could not be happening: " + e.getMessage(), false);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testContainmentProxyResolution() {
		DataStore dataStore = createTestDataStore();
		TestModelPackage metaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);		
		
		FInternalObjectImpl internalContainer = new FInternalObjectImpl(metaModel.getTestObject());
		FInternalObjectImpl internalContents = new FInternalObjectImpl(metaModel.getTestObject());
		EReference fragmentedContents = metaModel.getTestObject_FragmentedContents();
		EList contents = (EList) internalContainer.eGet(fragmentedContents);
		contents.add(internalContents);
		internalContents.eSet(metaModel.getTestObject_Name(), "testValue");
		
		Resource[] resources = createResourceSet(dataStore, metaModel, 2, false);
		resources[0].getContents().add(internalContainer);
		resources[1].getContents().add(internalContents);
		saveResources(resources);
		
		resources = createResourceSet(dataStore, metaModel, 1, true);
		internalContainer = (FInternalObjectImpl) resources[0].getContents().get(0);
		contents = (EList)internalContainer.eGet(fragmentedContents);
		
		Assert.assertEquals(1, contents.size());
		internalContents = (FInternalObjectImpl) contents.get(0);		
		Assert.assertEquals(internalContents, contents.get(0));		
		Assert.assertTrue(resources[0] != internalContents.eResource());
		Assert.assertTrue(resources[0] == internalContainer.eResource());
		Assert.assertFalse(internalContents.eIsProxy());
		Assert.assertEquals(internalContents.eGet(metaModel.getTestObject_Name()), "testValue");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testProxyResolution() {
		DataStore dataStore = createTestDataStore();
		TestModelPackage metaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		
		FInternalObjectImpl t1 = new FInternalObjectImpl(metaModel.getTestObject());
		FInternalObjectImpl t2 = new FInternalObjectImpl(metaModel.getTestObject());
		EReference testObject_crossReferences = metaModel.getTestObject_CrossReferences();		
		EList crossReferences = (EList) t1.eGet(testObject_crossReferences);
		crossReferences.add(t2);
		t2.eSet(metaModel.getTestObject_Name(), "testValue");
		
		Resource[] resources = createResourceSet(dataStore, metaModel, 2, false);
		resources[0].getContents().add(t1);
		resources[1].getContents().add(t2);
		saveResources(resources);
		
		resources = createResourceSet(dataStore, metaModel, 1, true);
		t1 = (FInternalObjectImpl) resources[0].getContents().get(0);
		crossReferences = (EList)t1.eGet(testObject_crossReferences);
		t2 = (FInternalObjectImpl) crossReferences.get(0);
				
		Assert.assertEquals(1, crossReferences.size());
		Assert.assertEquals(t2, crossReferences.get(0));		
		Assert.assertTrue(resources[0] != t2.eResource());
		Assert.assertTrue(resources[0] == t1.eResource());
		Assert.assertFalse(t2.eIsProxy());
		Assert.assertEquals("testValue", t2.eGet(metaModel.getTestObject_Name()));
	}
	
	@Test
	public void testReflectiveMetaModelRegistryTest() {
		TestModelPackage one = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		TestModelPackage two = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		
		Assert.assertEquals(one, two);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testMoveContents() {
		DataStore dataStore = createTestDataStore();
		TestModelPackage metaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(TestModelPackage.eINSTANCE);
		FInternalObjectImpl t1 = new FInternalObjectImpl(metaModel.getTestObject());
		FInternalObjectImpl t2 = new FInternalObjectImpl(metaModel.getTestObject());
		((EList)t1.eGet(metaModel.getTestObject_FragmentedContents())).add(t2);
		Resource[] resources = createResourceSet(dataStore, metaModel, 2, false);
		resources[0].getContents().add(t1);
		resources[1].getContents().add(t2);
		saveResources(resources);
		
		((EList)t1.eGet(metaModel.getTestObject_RegularContents())).add(t2);
		saveResources(resources);		
	}
}
