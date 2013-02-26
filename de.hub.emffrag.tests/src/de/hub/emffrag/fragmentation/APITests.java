package de.hub.emffrag.fragmentation;

import org.junit.Test;

public class APITests extends AbstractFragmentationTests {

	@Test
	public void simpleTest() {
		System.out.println(model.root().eResource());
	}
}
