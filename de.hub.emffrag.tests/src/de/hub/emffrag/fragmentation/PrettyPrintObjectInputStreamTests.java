package de.hub.emffrag.fragmentation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.hub.emffrag.testmodels.AbstractTestModelTests;
import de.hub.emffrag.testmodels.fobject.testmodel.TestObject;
import de.hub.util.PrettyPrintEObjectInputStream;

@FixMethodOrder(MethodSorters.JVM)
public class PrettyPrintObjectInputStreamTests extends AbstractTestModelTests {
	
	@Test
	public void testPrettyPrint() {
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createURI("test.bin");
		Resource resource = new BinaryResourceImpl(uri);
		rs.getResources().add(resource);
		
		TestObject model = createFromModelString("1r(2,3)");
		resource.getContents().add(model);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			resource.save(baos, null);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("exception during test " + e);
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		String result = null;
		try {
			result = PrettyPrintEObjectInputStream.prettyPrint(uri, bais, false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("exception during test " + e);
		}
		
		System.out.print(result);
	}
}
