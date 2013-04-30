package de.hub.emffrag.fragmentation;

import java.text.NumberFormat;
import java.util.Random;

import org.junit.Test;

import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;

public class RamUsageTests extends AbstractFragmentationTests {
	
	long totalMemory = -1;
	long freeMemory = -1;
	int meassure = 0;
	int objects = 0;

	/**
	 * With the use of the default in-memory data store, the memory usage will
	 * grow, even if everything works properly. This test only makes sense with
	 * a different data store.
	 */
	@Test
	public void testConstantRamUsage() throws Exception {
		initMemInfo();
		
		TestObject container = addObject(null, false);
		root.getContents().add(container);

		Random random = new Random(0);
		int numberOfFragments = 0;
		int objects = 1000000;
		for (int i = 0; i <= objects; i++) {
			boolean fragmenting = random.nextBoolean();
			TestObject object = addObject(container, fragmenting);
			container.getCrossReferences().add(object); // cross references
														// should not influence
														// unloading
			object.getCrossReferences().add(container);
			if (random.nextFloat() < 0.3f) {
				container = object;
			}
			if (fragmenting) {
				numberOfFragments++;
			}

			if (i % 1000 == 0) {
				printMemInfo();
			}
		}
		model.save(null);

		model.assertFragmentsIndex(0l, (long) numberOfFragments + 1);
		model.assertStatistics(0, 5000, 0, objects, (int) ((objects / 2) * 0.8f), (int) ((objects / 2) * 1.2),
				(int) ((objects / 2) * 0.8f), (int) ((objects / 2) * 1.2));
	}
	
	private void initMemInfo() {
		totalMemory = -1;
		freeMemory = -1;
		meassure = 0;
	}
	
	private void printMemInfo() {
		System.gc();
		System.out.println("tm: " + NumberFormat.getIntegerInstance().format(Runtime.getRuntime().totalMemory())
				+ " fm: " + NumberFormat.getIntegerInstance().format(Runtime.getRuntime().freeMemory()) + " unloads: "
				+ model.getStatistics().getUnloads() + " loads: " + model.getStatistics().getLoads() + " creates: "
				+ model.getStatistics().getCreates());
		meassure++;
		if (meassure > (objects / 1000) / 2) {
			if (totalMemory == -1) {
				totalMemory = Runtime.getRuntime().totalMemory();
			} else {
				// Assert.assertTrue("too much total memory: " +
				// NumberFormat.getIntegerInstance().format(Runtime.getRuntime().totalMemory())
				// +
				// " vs " + totalMemory,
				// Runtime.getRuntime().totalMemory() <
				// totalMemory*1.3f);
			}
			if (freeMemory == -1) {
				totalMemory = Runtime.getRuntime().freeMemory();
			} else {
				// Assert.assertTrue("too much memory used: " +
				// NumberFormat.getIntegerInstance().format(Runtime.getRuntime().freeMemory()),
				// Runtime.getRuntime().freeMemory() > freeMemory*0.8f);
			}
		}
	}
	
	@Test
	public void testRamUsageWithIndexedContainment() {
		initMemInfo();
		
		TestObjectWithIndexes container = TestModelFactory.eINSTANCE.createTestObjectWithIndexes();
		root.getContents().add(container);
		objects = 10000;
		for (int i = 0; i < objects; i++) {
			container.getIndexedContents().add(createTestObject(i));
			if (i % 1000 == 0) {
				printMemInfo();
			}
		}
	}
	
	@Test
	public void testRamUsageWithIndexedReferences() {
		initMemInfo();
		
		TestObjectWithIndexes container = TestModelFactory.eINSTANCE.createTestObjectWithIndexes();
		root.getContents().add(container);
		objects = 10000;
		TestObject current = container;
		for (int i = 0; i < objects; i++) {
			TestObject to = createTestObject(i);
			current.getFragmentedContents().add(to);
			current = to;
			container.getIndexedReferences().add(to);
			if (i % 1000 == 0) {
				printMemInfo();
			}
		}
	}

}
