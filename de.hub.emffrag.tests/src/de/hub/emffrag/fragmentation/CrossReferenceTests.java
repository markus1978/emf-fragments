package de.hub.emffrag.fragmentation;

import org.junit.Assert;
import org.junit.Test;

import de.hub.emffrag.testmodels.fobject.testmodel.TestObject;

public class CrossReferenceTests extends AbstractFragmentationTests {
	
	@Test
	public void testInterFragmentCrossReferencesPerRecursiveAddSimple() {
		TestObject model = createTOFromModelString("1f(2,3c(2))");
		
		fragmentation.getContents().add(model);
		
		assertFragmentation();
		Assert.assertEquals(3, fragmentation.getIndexOfLastAddedAndStillExistingFragment());
		fragmentation.close();
		
		reinitializeFragmentation(2);
		model = (TestObject)fragmentation.getContents().get(0);
		Assert.assertSame(0, queryTO(model, "1f2").getCrossReferences().size());
		Assert.assertSame(1, queryTO(model, "1f3").getCrossReferences().size());
				
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f3").getCrossReferences().get(0));		
	}
	
	@Test
	public void testInterFragmentCrossReferencesPerRecursiveAddComplex() {
		TestObject model = createTOFromModelString("1f(2,3c(2),4c(2,3),5c(2,3,4))");
		
		fragmentation.getContents().add(model);
		
		assertFragmentation();
		
		reinitializeFragmentation(2);
		model = (TestObject)fragmentation.getContents().get(0);
		Assert.assertSame(0, queryTO(model, "1f2").getCrossReferences().size());
		Assert.assertSame(1, queryTO(model, "1f3").getCrossReferences().size());
		Assert.assertSame(2, queryTO(model, "1f4").getCrossReferences().size());
		Assert.assertSame(3, queryTO(model, "1f5").getCrossReferences().size());
		
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f3").getCrossReferences().get(0));
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f4").getCrossReferences().get(0));
		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f4").getCrossReferences().get(1));
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f5").getCrossReferences().get(0));
		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f5").getCrossReferences().get(1));
		Assert.assertEquals(queryTO(model, "1f4"), queryTO(model, "1f5").getCrossReferences().get(2));
	}
	
	@Test
	public void testInterFragmentCrossReferencesPerRegularAdd() {
		TestObject model = createTOFromModelString("1f(2,3,4,5)");
		fragmentation.getContents().add(model);
		queryTO(model, "1f3").getCrossReferences().add(queryTO(model, "1f2"));
		queryTO(model, "1f4").getCrossReferences().add(queryTO(model, "1f2"));
		queryTO(model, "1f4").getCrossReferences().add(queryTO(model, "1f3"));
		queryTO(model, "1f5").getCrossReferences().add(queryTO(model, "1f2"));
		queryTO(model, "1f5").getCrossReferences().add(queryTO(model, "1f3"));
		queryTO(model, "1f5").getCrossReferences().add(queryTO(model, "1f4"));
			
		assertFragmentation();
		
		reinitializeFragmentation(2);
		model = (TestObject)fragmentation.getContents().get(0);
		Assert.assertSame(0, queryTO(model, "1f2").getCrossReferences().size());
		Assert.assertSame(1, queryTO(model, "1f3").getCrossReferences().size());
		Assert.assertSame(2, queryTO(model, "1f4").getCrossReferences().size());
		Assert.assertSame(3, queryTO(model, "1f5").getCrossReferences().size());
		
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f3").getCrossReferences().get(0));
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f4").getCrossReferences().get(0));
		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f4").getCrossReferences().get(1));
		Assert.assertEquals(queryTO(model, "1f2"), queryTO(model, "1f5").getCrossReferences().get(0));
		Assert.assertEquals(queryTO(model, "1f3"), queryTO(model, "1f5").getCrossReferences().get(1));
		Assert.assertEquals(queryTO(model, "1f4"), queryTO(model, "1f5").getCrossReferences().get(2));
	}
	
	@Test
	public void testIntraFragmentCrossReferences() {
		TestObject model = createTOFromModelString("1r(2,3c(2))");
	
		fragmentation.getContents().add(model);
		
		assertFragmentation();
		
		reinitializeFragmentation(2);
		model = (TestObject)fragmentation.getContents().get(0);
		Assert.assertSame(1, queryTO(model, "1r3").getCrossReferences().size());
	
		TestObject referenceValue = queryTO(model, "1r3").getCrossReferences().get(0);
		Assert.assertFalse(referenceValue.eIsProxy());
		Assert.assertEquals(queryTO(model, "1r2"), referenceValue);
	}

}
