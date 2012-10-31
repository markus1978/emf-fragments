package de.hub.emffrag.fragmentation;

import java.util.Random;

import junit.framework.Assert;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.KeyType;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.datastore.StringKeyType;

public class IndexTests extends AbstractTests {
	
	@Test
	public void testAddEmpty() {
		DataStore dataStore = createTestDataStore();
		DataIndex<Long> index = createIndex("f", dataStore);
		Long add = index.add();
		Assert.assertNotNull(add);
		Assert.assertEquals(0l, (long)add);
		Assert.assertFalse(index.add(0l));
	}
	
	@Test
	public void testAddNonEmpty() {
		DataIndex<Long> index = createIndex("f", createTestDataStore());
		index.add();
		for (int i = 1; i < 1000; i++) {
			Long add = index.add();
			Assert.assertNotNull("rep=" + i, add);
			Assert.assertEquals("rep=" + i, i, (long)add);
			Assert.assertFalse("rep=" + i, index.add(add));
		}
	}
	
	@Test
	public void testSetAndGet() {
		DataIndex<Long> index = createIndex("f", createTestDataStore());
		index.set(3l, "Test");
		Assert.assertEquals("Test", index.get(3l));
		index.set(3l, "Test2");
		Assert.assertEquals("Test2", index.get(3l));
	}
	
	@Test
	public void testFirstLast() {
		DataIndex<Long> index = createIndex("f", createTestDataStore());
		Assert.assertNull(index.first());
		Assert.assertNull(index.last());
		
		index.set(3l, "Test");
		Assert.assertNotNull(index.first());
		Assert.assertEquals((Long)3l, index.first());
		Assert.assertNotNull(index.last());
		Assert.assertEquals((Long)3l, index.last());
		
		index.set(6l, "Test");
		Assert.assertNotNull(index.first());
		Assert.assertEquals((Long)3l, index.first());
		Assert.assertNotNull(index.last());
		Assert.assertEquals((Long)6l, index.last());
	}

	/**
	 * Test if the serialization and de-serialization of long keys works.
	 */
	@Test
	public void testLongKeyTypeSerialization() {
		Random r = new Random(0);
		for (int i = 0; i < 1000; i++) {
			long one = Math.abs(r.nextLong());
			Assert.assertEquals("one=" + one, (long) one, (long) LongKeyType.instance.deserialize(LongKeyType.instance.serialize(one), 0));
		}
	}

	private void testLongKeyTypeOrder(KeyType<Long> type, long one, long two) {
		Assert.assertEquals("one=" + one + " and two=" + two,
				Math.signum(InMemoryDataStore.compareBytes(type.serialize(one), type.serialize(two))),
				Math.signum(Long.valueOf(one).compareTo(Long.valueOf(two))));
	}

	/**
	 * Test if the natural numerical order of longs is preserved by the
	 * lexicographical order of the serialized strings/byte arrays. This test
	 * only done for positive longs.
	 */
	@Test
	public void testLongKeyTypeOrder() {
		for (long distance = 0; distance < 10; distance++) {
			for (long base = 0; base < 1000; base++) {
				testLongKeyTypeOrder(LongKeyType.instance, base, base + distance);
				testLongKeyTypeOrder(LongKeyType.instance, base + distance, base);
			}
		}
		Random r = new Random(0);
		for (int i = 0; i < 1000; i++) {
			testLongKeyTypeOrder(LongKeyType.instance, Math.abs(r.nextLong()), Math.abs(r.nextLong()));
		}
	}
	
	@Test
	public void testStringKeyType() {
		StringKeyType keyType = StringKeyType.instance;
		Assert.assertEquals("b", keyType.next("a"));
		Assert.assertEquals("za", keyType.next("z"));
	}
	
	@Test
	public void testLargeKeyURIs() {
		DataStore dataStore = createTestDataStore();
		DataIndex<Long> index = createIndex("f", dataStore);
		for (long i = 0; i < 10000; i++) {
			URI uri = index.getURI(i);
			long key = index.getKeyFromURI(uri);
			Assert.assertEquals(i, key);
		}
	}
}
