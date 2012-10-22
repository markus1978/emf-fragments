package de.hub.emffrag.kvstore;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.KeyType;

public class IndexTests {

	private DataStore createTestDataStore() {
		return new InMemoryDataStore("", "", "");
	}

	private DataIndex<Long> createIndex(String prefix, DataStore store) {
		return new DataIndex<Long>(store, prefix, new LongKeyType());
	}
	
	@Test
	public void testAddEmpty() {
		DataIndex<Long> index = createIndex("f", createTestDataStore());
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
		Random r = new Random(0);
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
		KeyType<Long> type = new LongKeyType();
		Random r = new Random(0);
		for (int i = 0; i < 1000; i++) {
			long one = Math.abs(r.nextLong());
			Assert.assertEquals("one=" + one, (long) one, (long) type.deserialize(type.serialize(one), 0));
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
		KeyType<Long> type = new LongKeyType();
		for (long distance = 0; distance < 10; distance++) {
			for (long base = 0; base < 1000; base++) {
				testLongKeyTypeOrder(type, base, base + distance);
				testLongKeyTypeOrder(type, base + distance, base);
			}
		}
		Random r = new Random(0);
		for (int i = 0; i < 1000; i++) {
			testLongKeyTypeOrder(type, Math.abs(r.nextLong()), Math.abs(r.nextLong()));
		}
	}
}
