package de.hub.emffrag.mongodb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import de.hub.emffrag.datastore.DataStore;

public class MongoDBDataStore extends DataStore {

	private static final String VALUE = "value";
	private static final String KEY = "key";
	private DBCollection collection;
	private DB db;
	
	public MongoDBDataStore(String host, String dataStoreId) {
		this(host, dataStoreId, false);
	}

	public MongoDBDataStore(String host, String dataStoreId, boolean dropFirst) {
		super("mongodb", host, dataStoreId);
		String hostName = null;
		int hostPort = -1;
		
		String[] hostParts = host.split(":");
		if (hostParts.length == 1) {
			hostName = hostParts[0];
		} else if (hostParts.length == 2) {
			hostName = hostParts[0];
			try {
				hostPort = Integer.parseInt(hostParts[1]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid host format: " + host);
			}
		} else {
			throw new IllegalArgumentException("Invalid host format: " + host);			
		}

		db = EmfFragMongoDBActivator.instance.getDataBase(hostName, hostPort);
		collection = db.getCollection(dataStoreId);		
		if (dropFirst) {
			collection.dropIndexes();
			collection.drop();
			collection = db.getCollection(dataStoreId);	
		}
		collection.ensureIndex(new BasicDBObject(KEY, 1), new BasicDBObject("unique", true));		
	}
	
	private byte[] adoptKey(byte[] key) {
		assert(key.length <= 30);
		byte[] result = new byte[30];
		for (int i = 0; i < 17; i++) {
			result[i] = i < key.length ? key[i] : 0;
		}
		return result;
	}

	@Override
	synchronized public byte[] ceiling(byte[] key) {
		byte[] keyString = adoptKey(key);
		DBObject result = collection.findOne(new BasicDBObject(KEY, new BasicDBObject("$gte", keyString)), new BasicDBObject(KEY, ""), new BasicDBObject(KEY, 1));
		if (result != null) {
			return (byte[])result.get(KEY);
		} else {
			return null;
		}
	}

	@Override
	synchronized public byte[] floor(byte[] key) {
		DBObject result = collection.findOne(new BasicDBObject(KEY, new BasicDBObject("$lte", adoptKey(key))), new BasicDBObject(KEY, ""), new BasicDBObject(KEY, -1));
		if (result != null) {
			return (byte[])result.get(KEY);
		} else {
			return null;
		}		
	}

	@Override
	synchronized public InputStream openInputStream(byte[] key) {
		DBObject result = collection.findOne(new BasicDBObject(KEY, adoptKey(key)));
		if (result != null) {
			byte[] value = (byte[])result.get(VALUE);
			if (value != null) {
				return new ByteArrayInputStream(value);
			} else {
				return new ByteArrayInputStream(new byte[]{});
			}
		} else {
			return null;
		}
	}

	@Override
	synchronized public OutputStream openOutputStream(final byte[] key) {
		return new ByteArrayOutputStream(256) {
			@Override
			public void close() throws IOException {
				super.close();
				byte[] keyString = adoptKey(key);
				collection.update(new BasicDBObject(KEY, keyString), new BasicDBObject(KEY, keyString).append(VALUE, toByteArray()), true, false);
			}
		};
	}

	@Override
	synchronized public boolean check(byte[] key) {
		DBCursor cursor = collection.find(new BasicDBObject(KEY, adoptKey(key)));
		try {
			return !cursor.hasNext();
		} finally {
			cursor.close();
		}		
	}

	@Override
	synchronized public boolean ckeckAndCreate(byte[] key) {				
		try {
			collection.insert(new BasicDBObject(KEY, adoptKey(key)));
		} catch (MongoException e) {
			return false;
		}
		return true;
	}

	@Override
	synchronized public void delete(byte[] key) {
		collection.findAndRemove(new BasicDBObject(KEY, adoptKey(key)));
	}

	@Override
	synchronized public void drop() {
		collection.drop();
	}

	
}
