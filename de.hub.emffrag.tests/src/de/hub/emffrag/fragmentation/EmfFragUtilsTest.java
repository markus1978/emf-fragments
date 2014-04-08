package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;
import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.util.EMFFragUtil;

public class EmfFragUtilsTest extends AbstractFragmentationTests {
	
	@Test
	public void testNonFragmentingContents() {
		model.root().getContents().add(object1);
		object1.getRegularContents().add(object2);
		object2.getFragmentedContents().add(object3);
		object1.getCrossReferences().add(object3);
		
		Assert.assertEquals(2, EMFFragUtil.getAllNonFragmentingContents(((FObjectImpl)object1).fInternalObject()).size());
	}
	
	@Test
	public void testToURI() {
		model.root().getContents().add(object1);
		object1.getRegularContents().add(object2);
		object2.getFragmentedContents().add(object3);
		object1.getCrossReferences().add(object3);
		
		URI uri = EMFFragUtil.getURI(object1);
		Assert.assertEquals(2, uri.segmentCount());
		Assert.assertEquals("/", uri.fragment());
		
		EObject resolvedObject = EMFFragUtil.resolveURI(uri, model);
		Assert.assertTrue(resolvedObject instanceof TestObject);
		Assert.assertEquals(object1.getName(), ((TestObject)resolvedObject).getName());
		
	}

//	@Test
	public void testDynamicEObject() {
		EmfFragActivator.instance.idSemantics = new NoReferencesIdSemantics(IdBehaviour.preliminary);
		int i = 0;
		TestObject last = null;
		while (true) {
			i++;
			if (i % 1000 == 0) {
				System.gc();
			}
			TestObject o = TestModelFactory.eINSTANCE.createTestObject();
			if (last != null) {
				o.getCrossReferences().add(last);
				last.getCrossReferences().add(o);
			}

			last = o;
		}
	}
}
