package de.hub.emffrag.mongodb;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class MongodbAPITests {

	private static final String dataBaseName = "emffrag-test";
	
	private LongKeyType keyType;	
	private MongoClient dbClient;
	private DB db;
	private DBCollection collection;
	
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
		collection.ensureIndex("key");
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
		collection.insert(new BasicDBObject("key", new String(keyType.serialize(key))).append("value", ("HelloWorld: " + key).getBytes()));
	}
	
	@Test
	public void testCollision() {		
		addLong(0);
		addLong(0);
		assertCollection();
	}
	

	private void assertCollection() {
		DBCursor cursor = collection.find().sort(new BasicDBObject("key", 1));
		byte[] last = new byte[] {};
		try {
			while (cursor.hasNext()) {
				DBObject next = cursor.next();
				byte[] current = (byte[])((String)next.get("key")).getBytes();
				Assert.assertTrue("Wrong key order.", DataStore.compareBytes(last, current) < 0);
				Assert.assertTrue("Wrong value.", new String((byte[])next.get("value")).startsWith("HelloWorld"));				
			}
		} finally {
			cursor.close();
		}
	}
}
