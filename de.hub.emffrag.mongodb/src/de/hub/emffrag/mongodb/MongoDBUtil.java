package de.hub.emffrag.mongodb;

import org.eclipse.emf.common.util.URI;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoDBUtil {

	public static void dropCollection(URI uri) {
		String hostName = null;
		int hostPort = -1;
		
		String host = uri.authority();
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

		String dataStoreId = uri.path().substring(1);
		DB db = EmfFragMongoDBActivator.instance.getDataBase(hostName, hostPort);
		DBCollection collection = db.getCollection(dataStoreId);		

		collection.dropIndexes();
		collection.drop();
	}
}
