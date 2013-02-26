package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Before;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.model.emffrag.Root;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class AbstractFragmentationTests  extends AbstractTests {

	protected DataStore dataStore = null;
	protected FragmentedModel model = null;
	protected TestModelPackage metaModel = null;
	protected Root root = null;
	protected TestObject object1 = null;
	protected TestObject object2 = null;
	protected TestObject object3 = null;
	
	@Before
	public void resetUOCController() {
		UserObjectsCache.resetUOCController();
	}
	
	protected TestModelPackage getMetaModel() {
		return TestModelPackage.eINSTANCE;
	}

	@Before
	public void registerPackages() {
		if (!EPackage.Registry.INSTANCE.containsKey(EmfFragPackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), EmfFragPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey(TestModelPackage.eINSTANCE.getNsURI())) {
			EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), getMetaModel());
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
		metaModel = getMetaModel();
		ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(metaModel);
		
		if (doInitializeModel()) {
			model = createFragmentedModel(dataStore, metaModel);
			root = model.root();
		}
		
		object1 = Assertions.createTestObject(1);
		object2 = Assertions.createTestObject(2);
		object3 = Assertions.createTestObject(3);
	}
	
	protected final FragmentedModel createFragmentedModel(DataStore dataStore, EPackage metaModel) {
		return createFragmentedModel(dataStore, -1, metaModel);
	}
	
	protected FragmentedModel createFragmentedModel(DataStore dataStore, int cacheSize, EPackage metaModel) {
		return new FragmentedModel(dataStore, cacheSize);
	}
	
	protected void reinitializeModel() {
		model = createFragmentedModel(dataStore, TestModelPackage.eINSTANCE);
	}
	
	protected TestObject addObject(TestObject container, boolean fragmented) {
		TestObject contents = Assertions.createTestObject(4);

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
