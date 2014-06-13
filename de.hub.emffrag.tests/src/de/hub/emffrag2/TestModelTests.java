package de.hub.emffrag2;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.AbstractTestModelTests;
import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;
import de.hub.emffrag.testmodels.eobject.testmodel.eobject.meta.TestModelPackage;

public class TestModelTests extends AbstractTestModelTests<TestObject, TestModelPackage> {

	@Override
	protected TestModelPackage testModelPackage() {
		return TestModelPackage.eINSTANCE;
	}
	
	private void testCreateTOFromModel(String modelDefinition, String equalModelDefinition, int expectedSize) {
		TestObject root = createTOFromModelString(modelDefinition);
		
		int counter = 0;
		TreeIterator<EObject> it = root.eAllContents();
		while(it.hasNext()) {
			counter++;
			EObject next = it.next();				
			Assert.assertTrue(next instanceof TestObject);
		}
		
		Assert.assertEquals(expectedSize-1, counter);		
		
		Assert.assertTrue(EcoreUtil.equals(root, createTOFromModelString(equalModelDefinition)));
	}
	
	@Test
	public void basicCreateTOFromModelStringTest() {
		testCreateTOFromModel("1", "1", 1);
		testCreateTOFromModel("1f(2f(3),4)r(5)c(2,3,4,5)", "1c(2,3,4)r(5)f(2f(3),4)c(5)", 5);		
	}
	
	private void testQuery(String modelDefinition, String query, String queryResultDefinition) {
		EObject root = createFromModelString(modelDefinition);
		EObject queryResult = query(root, query);
		Assert.assertNotNull(queryResult);
		Assert.assertTrue(EcoreUtil.equals((EObject)createFromModelString(queryResultDefinition), queryResult));
	}
	
	@Test
	public void basicQueryTest() {
		testQuery("1r(2f(3r(4),5c(3,4)))", "1r2", "2f(3r(4),5c(3,4))");
		testQuery("1f(2f(3),4)", "1f2", "2f(3)");
	}
	
	private void testPrint(String normalizedDefinition) {
		Assert.assertEquals(normalizedDefinition, printTO(createTOFromModelString(normalizedDefinition)));
	}
	
	@Test
	public void basicPrintTest() {
		testPrint("1");
		testPrint("1f(2)");
		testPrint("1f(2)r(3,4)c(3,4)");
	}
	
	@Test
	public void basicEObjectTest() {
		testQuery("1a(E)", "1aE", "E");		
	}
}
