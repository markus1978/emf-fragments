package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.AbstractTestModelTests;
import de.hub.emffrag.testmodels.AccessNotifyingTestEObjectImpl;
import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;
import de.hub.emffrag.testmodels.eobject.testmodel.accessnot.meta.TestModelPackage;

public class AccessNotificationTests extends AbstractTestModelTests<TestObject, TestModelPackage> {

	private Map<EObject, Integer> accessCounts = new HashMap<EObject, Integer>();

	@Override
	protected TestModelPackage testModelPackage() {
		return TestModelPackage.eINSTANCE;
	}

	@Override
	protected TestObject createTO(String name, TestObject parent, EReference reference) {
		TestObject testObject = super.createTO(name, parent, reference);
		accessCounts.put(testObject, ((AccessNotifyingTestEObjectImpl)testObject).fAccessCount());
		return testObject;
	}

	@Override
	public void initializeTestModel() {
		accessCounts.clear();
		super.initializeTestModel();
	}

	private void assertAccess(TestObject to, boolean accessed) {
		Integer lastCount = accessCounts.get(to);
		if (lastCount == null) {
			lastCount = 0;
		}
		int currentCount = ((AccessNotifyingTestEObjectImpl)to).fAccessCount();
		accessCounts.put(to, currentCount);
		Assert.assertTrue(accessed ? currentCount > lastCount : currentCount == lastCount);
	}
	
	private void assertAccessed(TestObject to) {
		assertAccess(to, true);
	}
	
	private void assertNotAccessed(TestObject to) {
		assertAccess(to, false);
	}
	
	@Test
	public void testSingleValuedReferences() {
		TestObject to = createTO("1");
		assertNotAccessed(to);
		to.setName("HH");
		assertAccessed(to);
		
		assertNotAccessed(to);
		to.getName();
		assertAccessed(to);
		
		assertNotAccessed(to);
		to.eIsSet(testModelPackage().getTestObject_Name());
		assertAccessed(to);
		
		assertNotAccessed(to);
		to.eUnset(testModelPackage().getTestObject_Name());
		assertAccessed(to);
	}
	
	@Test
	public void testMultiValuedReferences() {
		TestObject to = createTO("1");
		assertNotAccessed(to);
		createTO("2", to, testModelPackage().getTestObject_RegularContents());
		assertAccessed(to);
		
		assertNotAccessed(to);
		to.getRegularContents().clear();
		assertAccessed(to);
	}
	
	@Test
	public void testContainerAccess() {
		TestObject to = createTO("1");
		assertNotAccessed(to);
		to.eContainer();
		assertAccessed(to);
		
		TestObject contents = createTO("2");
		assertNotAccessed(contents);
		to.getRegularContents().add(contents);
		assertAccessed(contents);
		assertAccessed(to);
	}
	
	@Test
	public void testImmediateValueSetAccess() {
		TestObject to = createTO("1");
		EList<TestObject> contents = to.getFragmentedContents();
		assertAccessed(to);
		contents.size();
		assertAccessed(to);
	}
}
