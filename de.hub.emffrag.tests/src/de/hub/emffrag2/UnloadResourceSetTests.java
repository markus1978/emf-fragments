package de.hub.emffrag2;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.AbstractTestModelTests;
import de.hub.emffrag.testmodels.eobject.testmodel.unload.impl.TestObjectImpl;
import de.hub.emffrag.testmodels.eobject.testmodel.unload.meta.TestModelPackage;
import de.hub.emffrag2.unload.UUIDBasedUnloadResourceSetImpl;
import de.hub.emffrag2.unload.UnloadEObjectImpl;
import de.hub.emffrag2.unload.UnloadResourceSetImpl;

public class UnloadResourceSetTests extends AbstractTestModelTests<TestObjectImpl, TestModelPackage> {

	@Override
	protected TestModelPackage testModelPackage() {
		return TestModelPackage.eINSTANCE;
	}
	
	private void assertLoadStatus(EObject eObject, ResourceSet rs, boolean loaded) {
		// Lock the resource to prevent accidental loading during checking assertions.
		//
		((UnloadResourceSetImpl<?>)rs).uLock(true);
		
		if (loaded) {
			Assert.assertTrue(!eObject.eIsProxy());
			Assert.assertNull(((UnloadEObjectImpl)eObject).uIsUnloadedFrom());
			Assert.assertNotNull(eObject.eResource());
			Assert.assertTrue(eObject.eResource().isLoaded());
		} else {
			Assert.assertTrue(eObject.eIsProxy());
			Assert.assertEquals(rs, ((UnloadEObjectImpl)eObject).uIsUnloadedFrom());
			Assert.assertNull(eObject.eResource());
			Assert.assertTrue(!rs.getResource(((UnloadEObjectImpl)eObject).eProxyURI().trimFragment(), false).isLoaded());
		}
		
		((UnloadResourceSetImpl<?>)rs).uLock(false);
	}

	@Test
	public void testBasicManualLoad() {		
		UUIDBasedUnloadResourceSetImpl rs = new UUIDBasedUnloadResourceSetImpl();
		URI uri = URI.createURI("models/test.bin");
		Resource resource = rs.createResource(uri);
		String modelDefinition = "1r(2r(3),4)c(2,3,4)";
		TestObjectImpl model = createTOFromModelString(modelDefinition);
		resource.getContents().add(model);
		try {
			rs.uSaveAndUnload(resource);
		} catch (IOException e) {
			Assert.fail();
		}
		
		assertLoadStatus(model, rs, false);
		
		// manually loading
		//
		rs.uLoad(model); 
		
		assertLoadStatus(model, rs, true);
		Assert.assertTrue(rs.getResource(uri, false).isLoaded());
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), model));
	}
	
	@Test
	public void testBasicAutoLoad() {		
		UUIDBasedUnloadResourceSetImpl rs = new UUIDBasedUnloadResourceSetImpl();
		URI uri = URI.createURI("models/test.bin");
		Resource resource = rs.createResource(uri);
		String modelDefinition = "1r(2r(3),4)c(2,3,4)";
		TestObjectImpl model = createTOFromModelString(modelDefinition);
		resource.getContents().add(model);
		try {
			rs.uSaveAndUnload(resource);
		} catch (IOException e) {
			Assert.fail();
		}
		
		assertLoadStatus(model, rs, false);
		
		// automatically loaded via accessing an object
		//
		model.getName(); 
		
		assertLoadStatus(model, rs, true);
		Assert.assertEquals(model, resource.getContents().get(0));
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), model));
	}
	
	@Test
	public void testProxyAutoLoad() {		
		UUIDBasedUnloadResourceSetImpl rs = new UUIDBasedUnloadResourceSetImpl();
		URI uri1 = URI.createURI("models/test1.bin");
		URI uri2 = URI.createURI("models/test2.bin");
		Resource resource1 = rs.createResource(uri1);
		Resource resource2 = rs.createResource(uri2);
		String modelDefinition = "1r(2r(3),4)c(2,3,4)";
		TestObjectImpl model = createTOFromModelString(modelDefinition);
		resource1.getContents().add(model);
		TestObjectImpl proxy = queryTO(model, "1r2");
		resource2.getContents().add(proxy);
		
		Assert.assertTrue(!resource2.getContents().isEmpty());
		Assert.assertEquals(resource2, proxy.eResource());
		
		try {
			rs.uSaveAndUnload(resource2);
		} catch (IOException e) {
			Assert.fail();
		}
		assertLoadStatus(model, rs, true);
		assertLoadStatus(proxy, rs, false);
		
		try {
			rs.uSaveAndUnload(resource1);
		} catch (IOException e) {
			Assert.fail();
		}		
		assertLoadStatus(model, rs, false);
		assertLoadStatus(proxy, rs, false);		
		
		
		// auto loading resource 1
		model.getName(); 		
		assertLoadStatus(model, rs, true);
		assertLoadStatus(proxy, rs, true);
		
		// auot loading resource 2
		proxy.getName();
		assertLoadStatus(model, rs, true);
		assertLoadStatus(proxy, rs, true);
				
		Assert.assertTrue(EcoreUtil.equals(createTOFromModelString(modelDefinition), model));
	}
}
