package de.hub.emffrag2;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.AbstractTestModelTests;
import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;
import de.hub.emffrag.testmodels.eobject.testmodel.eobject.meta.TestModelPackage;

public class UUIDBinaryResourceImplTests extends AbstractTestModelTests<TestObject, TestModelPackage> {
	
	@Override
	protected TestModelPackage testModelPackage() {
		return TestModelPackage.eINSTANCE;
	}
	
	@Override
	public void initializeTestModel() {	
		super.initializeTestModel();
		EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bin", new ResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new UUIDBinaryResourceImpl(uri);
			}			
		});
	}

	private void testBasicSaveLoad(String modelDefinition) {
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createURI("models/test.bin");
		Resource resource = rs.createResource(uri);
		
		resource.getContents().add(createTOFromModelString(modelDefinition));
		try {
			resource.save(null);
		} catch (IOException e) {
			Assert.fail("IOException: " + e.getMessage());
		}
		
		rs = new ResourceSetImpl();
		resource = rs.getResource(uri, true);
		Assert.assertTrue(resource.getErrors().isEmpty());
		Assert.assertTrue(!resource.getContents().isEmpty());
		assertResource(resource, modelDefinition);
	}
	
	@Test
	public void testBasicSaveLoad1() {
		testBasicSaveLoad("1f(2f(3),4)r(5)c(2,3,4,5)");
	}
	
	@Test
	public void testBasicSaveLoad2() {
		testBasicSaveLoad("1f(2f(4f(6),5),3)");		
	}
	
	private void assertResource(Resource resource, String modelDefinition) {
		Assert.assertTrue(EcoreUtil.equals(resource.getContents().get(0), createTOFromModelString(modelDefinition)));
	}
	
	@Test
	public void testSaveLoadWithProxies() {
		URI uri1 = URI.createURI("models/test.1.bin");
		URI uri2 = URI.createURI("models/test.2.bin");
		
		ResourceSet rs = new ResourceSetImpl();		
		Resource resource1 = rs.createResource(uri1);
		
		String modelDefinition = "1f(2f(3),4)r(5)c(2,3,4,5)";
		String query = "1f2";
		String queryResultDefinition = "2f(3)";
		
		TestObject root = createTOFromModelString(modelDefinition);
		resource1.getContents().add(root);
		
		Resource resource2 = rs.createResource(uri2);
		TestObject queryResult = queryTO(root, query);
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(queryResultDefinition), queryResult));
		
		resource2.getContents().add(queryResult);
		
		assertResource(resource1, modelDefinition);
		assertResource(resource2, queryResultDefinition);
		
		try {
			resource1.save(null);
			resource2.save(null);
		} catch (IOException e) {
			Assert.fail();
		}
		
		rs = new ResourceSetImpl();
		resource1 = rs.getResource(uri1, true);
		resource2 = rs.getResource(uri2, true);
		Assert.assertTrue(resource1.getErrors().isEmpty());
		Assert.assertTrue(resource2.getErrors().isEmpty());
		
		assertResource(resource1, modelDefinition);
		assertResource(resource2, queryResultDefinition);
	}
	
	@Test
	public void testIDs() {
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createURI("models/test.bin");
		UUIDBinaryResourceImpl resource = (UUIDBinaryResourceImpl)rs.createResource(uri);
		
		String modelDefinition = "1f(2f(3),4)r(5)c(2,3,4,5)";
		TestObject model = createTOFromModelString(modelDefinition);
		resource.getContents().add(model);
		
		Integer id1f2 = resource.getID(queryTO(model, "1f2"), true);
		Integer id1c2 = resource.getID(queryTO(model, "1c2"), false);
		Assert.assertNotNull(id1f2);		
		Assert.assertNotNull(id1c2);
		Assert.assertEquals(id1f2, id1c2);

		try {
			resource.save(null);
		} catch (IOException e) {
			Assert.fail();
		}
		
		Integer id1f4 = resource.getID(queryTO(model, "1f4"), false);
		Assert.assertNotNull(id1f4);
		
		rs = new ResourceSetImpl();
		resource = (UUIDBinaryResourceImpl)rs.getResource(uri, true);
		Assert.assertTrue(resource.getErrors().isEmpty());
		model = (TestObject)resource.getContents().get(0);
		Assert.assertEquals(id1f2, resource.getID(queryTO(model, "1f2")));
		Assert.assertNotEquals(resource.getID(queryTO(model, "1f2")), resource.getID(queryTO(model, "1f4")));
	}
}
