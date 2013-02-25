package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Assert;
import org.junit.Before;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class AbstractFragmentationTests  extends AbstractTests {

	protected DataStore dataStore = null;
	protected FragmentedModel model = null;
	protected TestModelPackage metaModel = null;
	protected URI rootFragmentURI = null;
	protected TestObject object1 = null;
	protected TestObject object2 = null;
	protected TestObject object3 = null;
	
	@Before
	public void resetUOCController() {
		UserObjectsCache.resetUOCController();
	}

	@Before
	public void registerPackages() {
		if (!EPackage.Registry.INSTANCE.containsKey(EmfFragPackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), EmfFragPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey(TestModelPackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), TestModelPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		}		
	}
	
	protected boolean doInitializeModel() {
		return true;
	}
	
	@Before
	public void standardInitialization() {
		dataStore = createTestDataStore();
		metaModel = TestModelPackage.eINSTANCE;
		ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(metaModel);
		
		if (doInitializeModel()) {
			model = createFragmentedModel(dataStore, null, metaModel);
			rootFragmentURI = model.getRootFragmentURI();
		}
		
		object1 = createTestObject();
		object2 = createTestObject();
		object3 = createTestObject();
	}
	
	protected TestObject createTestObject() {
		TestObject testObject = TestModelFactory.eINSTANCE.createTestObject();
		testObject.setName("testValue");
		return testObject;
	}
	
	protected final FragmentedModel createFragmentedModel(DataStore dataStore, URI rootFragmentURI, EPackage metaModel) {
		return createFragmentedModel(dataStore, rootFragmentURI, -1, metaModel);
	}
	
	protected FragmentedModel createFragmentedModel(DataStore dataStore, URI rootFragmentURI, int cacheSize, EPackage metaModel) {
		return new FragmentedModel(dataStore, rootFragmentURI, cacheSize, metaModel);
	}
	
	protected void reinitializeModel() {
		model = createFragmentedModel(dataStore, rootFragmentURI, TestModelPackage.eINSTANCE);
	}
	
	protected void assertRootFragment(EObject object) {
		Assert.assertNotNull(object.eResource());
		Assert.assertTrue(object.eResource() instanceof Fragment);
	}
	
	protected TestObject assertHasModelRootFragment() {
		Assert.assertEquals(1, model.getRootContents().size());
		Assert.assertTrue(model.getRootContents().get(0) instanceof TestObject);
		TestObject contents = (TestObject) model.getRootContents().get(0);
		Assert.assertEquals("testValue", contents.getName());
		Assert.assertTrue(contents.eResource() instanceof Fragment);
		return contents;
	}
	
	@SuppressWarnings("rawtypes")
	protected TestObject assertHasContents(TestObject container, EStructuralFeature feature) {
		Assert.assertTrue(feature.isMany());
		Assert.assertEquals(1, ((EList)container.eGet(feature)).size());
		Object contentsObject = ((EList)container.eGet(feature)).get(0);
		Assert.assertTrue(contentsObject instanceof TestObject);
		TestObject contents = (TestObject)contentsObject;
		Assert.assertFalse(contents.eIsProxy());
		Assert.assertEquals("testValue", ((TestObject)contents).getName());
		Assert.assertTrue(contents.eResource() instanceof Fragment);
		return (TestObject)contents;
	}
	
	protected void assertContainment(EObject container, EObject contents, EStructuralFeature feature, boolean fragmenting) {
		Assert.assertSame(container, contents.eContainer());
		if (fragmenting) {		
			Assert.assertNotSame(container.eResource(), contents.eResource());
		} else {
			Assert.assertSame(container.eResource(), contents.eResource());
		}
		Assert.assertSame(feature, contents.eContainingFeature());				
	}

	protected TestObject addObject(TestObject container, boolean fragmented) {
		TestObject contents = TestModelFactory.eINSTANCE.createTestObject();
		contents.setName("testValue");

		if (container != null) {
			if (fragmented) {
				container.getFragmentedContents().add(contents);
			} else {
				container.getRegularContents().add(contents);
			}
		}

		return contents;
	}
	
	@SuppressWarnings("unchecked")
	protected boolean removeObject(TestObject contents) {
		EStructuralFeature containingFeature = contents.eContainingFeature();
		((EList<EObject>) contents.eContainer().eGet(containingFeature)).remove(contents);
		return containingFeature.getName().equals(TestModelPackage.eINSTANCE.getTestObject_FragmentedContents().getName());				
	}

}
