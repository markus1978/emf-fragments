package de.hub.emffrag.fragmentation;

import java.text.NumberFormat;
import java.util.Random;

import org.junit.Test;

import de.hub.emffrag.testmodels.frag.testmodel.TestObject;

public class RamUsageTests extends AbstractFragmentationTests {

	/**
	 * TODO
	 * 
	 * There is no data store that does not need memory when it grows.
	 */
	@Test
	public void testConstantRamUsage() throws Exception {
		TestObject container = addObject(null, false);
		root.getContents().add(container);
		long totalMemory = -1;
		long freeMemory = -1;
		int meassure = 0;

		Random random = new Random(0);
		int numberOfFragments = 0;
		int objects = 1000000;
		for (int i = 0; i <= objects; i++) {
			boolean fragmenting = random.nextBoolean();
			TestObject object = addObject(container, fragmenting);
			if (random.nextFloat() < 0.3f) {
				container = object;
			}
			if (fragmenting) {
				numberOfFragments++;
			}

			if (i % 1000 == 0) {
				System.gc();
				System.out.println("tm: " + NumberFormat.getIntegerInstance().format(Runtime.getRuntime().totalMemory())
						+ " fm: " + NumberFormat.getIntegerInstance().format(Runtime.getRuntime().freeMemory()) 
						+ " unloads: " + model.getStatistics().getUnloads() 
						+ " loads: " + model.getStatistics().getLoads() 
						+ " creates: " + model.getStatistics().getCreates());
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
		}
		model.save();

		model.assertFragmentsIndex(0l, (long) numberOfFragments + 1);
		model.assertStatistics(0, 5000, 0, objects, (int) ((objects / 2) * 0.8f), (int) ((objects / 2) * 1.2),
				(int) ((objects / 2) * 0.8f), (int) ((objects / 2) * 1.2));
	}

}
