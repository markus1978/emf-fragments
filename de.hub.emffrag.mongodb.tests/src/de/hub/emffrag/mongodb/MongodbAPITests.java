package de.hub.emffrag.mongodb;

import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import de.hub.emffrag.datastore.InMemoryDataStore;
import de.hub.emffrag.datastore.internal.DataMap;
import de.hub.emffrag.datastore.internal.IDataMap;
import de.hub.emffrag.datastore.internal.LongKeyType;

public class MongodbAPITests {

	private static final String dataBaseName = "emffrag-test";
	
	private LongKeyType keyType;	
	private MongoClient dbClient;
	private DB db;
	private DBCollection collection;
	
	@SuppressWarnings("deprecation")
	@Before 
	public void init() {
		keyType = LongKeyType.instance;
		dbClient = null;
		try {
			dbClient = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			Assert.fail("Could not instantiate client.");
		}
		db = dbClient.getDB(dataBaseName);
		db.dropDatabase();
		db = dbClient.getDB(dataBaseName);
		collection = db.getCollection("test");
		collection.ensureIndex(new BasicDBObject("key",1), new BasicDBObject("unique", true));
	}

	@Test
	public void testClient() {
		init();
		
		collection.createIndex(new BasicDBObject("key", 1));
		
		collection.insert(new BasicDBObject("key", new String(new byte[]{102, 96, 0, 2, 3, 4, 5 })).append("value", "HelloWorld".getBytes()));
		collection.insert(new BasicDBObject("key", new String(new byte[]{102, 95, 0, 0, 0, 0, 1 })).append("value", "HelloWorld".getBytes()));
		collection.insert(new BasicDBObject("key", new String(new byte[]{102, 95 })).append("value", "HelloWorld".getBytes()));
				
		assertCollection();
	}
	
	@Test
	public void testKeys() {
		for (long i = 0; i < Long.MAX_VALUE/3l; i = i < 1000 ? i + 1 : (i+1)*2) {
			addLong(i);
		}
		assertCollection();
	}
	
	private void addLong(long key) {
		addLong(key, "HelloWorld: " + key);
	}
	
	private void addLong(long key, String value) {
		collection.insert(new BasicDBObject("key", new String(keyType.serialize(key))).append("value", value.getBytes()));
	}
	
	private void addBytes(byte[] bytes, String value) {
		collection.insert(new BasicDBObject("key", new String(bytes)).append("value", value.getBytes()));
	}
	
	@Test
	public void testCollision() {		
		addLong(0, "a");
		try {
			addLong(0, "b");
		} catch (MongoException e) {
			return;
		}
		Assert.fail("Mongo added entry with dublicate key.");
	}
	
	@Test
	public void test136() {
		IDataMap<Long> dataIndex = new DataMap<Long>(null, null, "c".getBytes(), LongKeyType.instance);
		for (long i = 0; i < 136; i++) {
			addBytes(dataIndex.getStoreKey(i), "value");	
		}
		addBytes(dataIndex.getStoreKey(136l), "value");
		
		((String)collection.findOne(new BasicDBObject("key", new BasicDBObject("$lte", new String("f_"))), new BasicDBObject("key", ""), new BasicDBObject("key", -1)).get("key")).getBytes();
	}
	
	private void assertCollection() {
		DBCursor cursor = collection.find().sort(new BasicDBObject("key", 1));
		byte[] last = new byte[] {};
		try {
			while (cursor.hasNext()) {
				DBObject next = cursor.next();
				byte[] current = (byte[])((String)next.get("key")).getBytes();
				Assert.assertTrue("Wrong key order.", InMemoryDataStore.compareBytes(last, current) < 0);
				Assert.assertTrue("Wrong value.", new String((byte[])next.get("value")).startsWith("HelloWorld"));				
			}
		} finally {
			cursor.close();
		}
	}
}
