package de.hub.emffrag.fragmentation;

import junit.framework.Assert;

import org.junit.Test;

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

}
