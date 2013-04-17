package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
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
