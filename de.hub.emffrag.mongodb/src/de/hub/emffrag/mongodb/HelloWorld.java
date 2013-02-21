package de.hub.emffrag.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MongoClient dbClient = new MongoClient();
		DB db = dbClient.getDB("emffrag");
		db.dropDatabase();
		db = dbClient.getDB("emffrag");
		DBCollection collection = db.getCollection("helloworld");
		collection.createIndex(new BasicDBObject("key", 1));
		
		collection.insert(new BasicDBObject("key", new String(new byte[]{102, 96, 0, 2, 3, 4, 5 })).append("value", "HelloWorld".getBytes()));
		collection.insert(new BasicDBObject("key", new String(new byte[]{102, 95, 0, 0, 0, 0, 1 })).append("value", "HelloWorld".getBytes()));
		collection.insert(new BasicDBObject("key", new String(new byte[]{102, 95 })).append("value", "HelloWorld".getBytes()));
				
		DBCursor cursor = collection.find().sort(new BasicDBObject("key", 1));
		try {
			while (cursor.hasNext()) {
				for (byte b: (byte[])((String)cursor.next().get("key")).getBytes()) {
					System.out.print(b);
					System.out.print(" ");
				}
				System.out.println();
			}
		} finally {
			cursor.close();
		}
	}

}
