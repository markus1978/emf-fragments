package de.hub.emffrag.fragmentation;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.junit.Assert;

import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes;

public class Assertions {

	private static String prefix = "testValue";
	
	private TestObject owner;
	private Object value;
	private int index;
	private TestObject save;
	
	public static Assertions context(TestObject testObject) {
		Assertions result = new Assertions();
		result.owner = testObject;
		return result;
	}
	
	public static TestObject createTestObject(int id) {
		TestObject testObject = TestModelFactory.eINSTANCE.createTestObject();
		testObject.setName(prefix + id);
		return testObject;
	}
	
	public static TestObject createTestObjectWithIndexes(int id) {
		TestObject testObject = TestModelFactory.eINSTANCE.createTestObjectWithIndexes();
		testObject.setName(prefix + id);
		return testObject;
	}
	
	public static Assertions root(FragmentedModel model, int size, int i) {
		Assert.assertEquals(size, model.root().getContents().size());
		Assert.assertTrue(model.root().getContents().get(0) instanceof TestObject);
		return context((TestObject) model.root().getContents().get(i)).assertTestObject();
	}
	
	public static Assertions root(FragmentedModel model) {
		return root(model, 1, 0);
	}

	public TestObject value() {
		if (value != null) {
			try {
				if (index != -1) {
					return (TestObject)((EList<?>)value).get(index);
				} else {
					if (value instanceof TestObject) {
						return (TestObject)value;
					} else {
						return owner;
					}					
				}
			} catch (ClassCastException e) {
				Assert.fail("There is no test object.");
				throw e;
			} catch (IndexOutOfBoundsException e) {
				Assert.fail("No value at this index.");
				throw e;
			}
		} else {
			return owner;
		}
	}
	
	public Assertions assertTestObject() {
		FObjectImpl value = (FObjectImpl)value();
		Fragment fragment = value.fInternalObject().getFragment();
		if (fragment != null) {
			UserObjectsCache.newUserObjectsCache.assertNotCached(value);
			fragment.getUserObjectsCache().assertCached(value);
		} else {
			UserObjectsCache.newUserObjectsCache.assertCached(value);
		}
		
		Assert.assertTrue("Not a test object.", value().getName().startsWith(prefix));
		Assert.assertTrue("Not part of a fragmented model.", ((FObjectImpl)value()).fInternalObject().eResource() instanceof Fragment);
		return this;
	}
	
	public Assertions assertId(int id) {
		assertTestObject();
		Assert.assertEquals("Wrong test object.", ""+id, value().getName().substring(prefix.length()));
		return this;
	}
	
	public Assertions assertSameFragmentAsContainer() {
		FInternalObjectImpl internalObject = ((FObjectImpl)value()).fInternalObject();
		Assert.assertNotNull("Is not part of a fragment.", internalObject.eResource());		
		Assert.assertTrue("Is not part of a fragment.", internalObject.eResource() instanceof Fragment);
		Assert.assertNotNull("Has no container.", internalObject.eContainer());
		Assert.assertSame("Not the same fragment as container", internalObject.eResource(), internalObject.eContainer().eResource());
		return this;
	}
	
	public Assertions assertDifferentFragmentAsContainer() {
		FInternalObjectImpl internalObject = ((FObjectImpl)value()).fInternalObject();
		Assert.assertNotNull("Is not part of a fragment.", internalObject.eResource());		
		Assert.assertTrue("Is not part of a fragment.", internalObject.eResource() instanceof Fragment);
		Assert.assertNotNull("Has no container.", internalObject.eContainer());
		Assert.assertNotSame("Not the same fragment as container", internalObject.eResource(), internalObject.eContainer().eResource());
		return this;
	}
	
	public Assertions get(EReference feature) {
		value = value().eGet(feature);
		index = -1;
		return this;
	}
	
	public Assertions getFragmentedContents() {
		value = value().getFragmentedContents();
		index = -1;
		return this;
	}
	
	public Assertions getRegularContents() {
		value = value().getRegularContents();
		index = -1;
		return this;
	}

	public Assertions getCrossReferences() {
		value = value().getCrossReferences();
		index = -1;
		return this;
	}

	public Assertions getIndexedContents() {
		Assert.assertTrue("Object has wrong type.", value() instanceof TestObjectWithIndexes);
		value = ((TestObjectWithIndexes)value()).getIndexedContents();
		index = -1;
		return this;
	}

	public Assertions getIndexedReferences() {
		Assert.assertTrue("Object has wrong type.", value() instanceof TestObjectWithIndexes);
		value = ((TestObjectWithIndexes)value()).getIndexedReferences();
		index = -1;
		return this;
	}
	
	public Assertions eContents() {
		value = value().eContents();
		index = -1;
		return this;
	}
	
	public Assertions get(int index) {
		this.index = index;
		return this;
	}
	
	public Assertions assertSize(int size) {
		Assert.assertEquals("Wrong size.", size, ((EList<?>)value).size());
		return this;
	}

	public Assertions eContainer() {
		EObject eContainer = value().eContainer();
		Assert.assertNotNull("There is no container.", eContainer);
		Assert.assertTrue("Container is not a test object.", eContainer instanceof TestObject);
		owner = (TestObject)eContainer;
		index = -1;
		value = null;
		return this;
	}
	
	public Assertions save() {
		save = value();
		return this;
	}
	
	public Assertions assertContains() {
		Assert.assertTrue("List does not contain saved object.", ((EList<?>)value).contains(save));		
		return this;
	}

	public Assertions load() {
		owner = save;
		value = null;
		index = -1;
		return this;
	}
	
	public Assertions assertIterator(int size) {
		Iterator<?> iterator = ((List<?>)value).iterator();
		Assert.assertTrue("Iterator is emtpy.", iterator.hasNext() || size == 0);
		int i = 0;
		while (iterator.hasNext()) {
			context((TestObject)iterator.next()).assertTestObject();
			i++;
		}
		Assert.assertEquals("Iterator has the wrong size.", size, i);
		return this;
	}
	
}
