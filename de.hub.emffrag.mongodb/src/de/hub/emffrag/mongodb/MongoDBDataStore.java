package de.hub.emffrag.mongodb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Stopwatch;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.IScanExtension;
import de.hub.emffrag.datastore.ScanningDataStore;
import de.hub.emffrag.datastore.URIUtils;
import de.hub.jstattrack.Statistic;
import de.hub.jstattrack.StatisticBuilder;
import de.hub.jtrackstat.services.Histogram;
import de.hub.jtrackstat.services.Summary;

public class MongoDBDataStore implements IBaseDataStore, IScanExtension {
	
	private static final Statistic entrySizeStatistic = new StatisticBuilder()
			.withService(new Summary())
			.withService(new Histogram())
			.register(MongoDBDataStore.class, "EntrySize");
	private static final Statistic writeTimeStatistic = new StatisticBuilder()
			.withService(new Summary())
			.register(MongoDBDataStore.class, "WriteTimes");
	
	public static IDataStore createDataStore(URI uri, boolean useScanning) {
		MongoDBDataStore baseDataStore = new MongoDBDataStore(uri.authority(), uri.path().substring(1));
		if (useScanning) {
			return new DataStoreImpl(new ScanningDataStore(baseDataStore, baseDataStore), uri);
		} else {
			return new DataStoreImpl(baseDataStore, uri);
		}				
	}
	
	private final static Map<String, DB> dataBases = new HashMap<String, DB>();
	
	public static DB getDataBase(String host, int port) {
		DB dataBase = dataBases.get(host + port);
		if (dataBase == null) {
			MongoClient dbClient;
			try {
				ReadPreference.nearest();
				if (port != -1) {
					dbClient = new MongoClient(host, port);
				} else {
					dbClient = new MongoClient(host);	
				}			
			} catch (UnknownHostException e) {
				throw new IllegalArgumentException("Given host does not exists or DB is not running: " + host);	
			}
			dataBase = dbClient.getDB("emffrag");
			dataBases.put(host+port, dataBase);
		}
		return dataBase;				
	}
	
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
		DB db = MongoDBDataStore.getDataBase(hostName, hostPort);
		DBCollection collection = db.getCollection(dataStoreId);		

		collection.dropIndexes();
		collection.drop();
	}

	private static final int MAX_BSON_SIZE = 1024*1024*16 - 256;
	
	private static final String TYPE = "type";
	private static final int TYPE_EMPTY = 0;
	private static final int TYPE_BSON = 1;
	private static final int TYPE_GRID_FS = 2;
	
	private static final String VALUE = "value";
	private static final String KEY = "key";
	private static final String FILE_NAME = "file";
	private DBCollection collection;
	private DB db;
	private GridFS gridFs;
	
	public MongoDBDataStore(String host, String dataStoreId) {
		this(host, dataStoreId, false);
	}

	@SuppressWarnings("deprecation")
	public MongoDBDataStore(String host, String dataStoreId, boolean dropFirst) {
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

		db = getDataBase(hostName, hostPort);
		collection = db.getCollection(dataStoreId);		
		if (dropFirst) {
			collection.dropIndexes();
			collection.drop();
			collection = db.getCollection(dataStoreId);	
		}
		collection.ensureIndex(new BasicDBObject(KEY, 1), new BasicDBObject("unique", true));
		gridFs = new GridFS(db);
	}
	
	private byte[] adoptKey(byte[] key) {
		assert(key.length <= 60);
		byte[] result = new byte[60];
		for (int i = 0; i < 60; i++) {
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
		return inputStreamFromDBObject(result);
	}
	
	private InputStream inputStreamFromDBObject(DBObject result) {
		if (result != null) {			
			Integer typeAsInteger = (Integer)result.get(TYPE);
			if (typeAsInteger == null) {
				EmfFragActivator.instance.warning("No type of mongodb database entry.");
				return null;
			}
			int type = typeAsInteger;
			if (type == TYPE_BSON) {
				byte[] value = (byte[])result.get(VALUE);
				if (value != null) {
					return new ByteArrayInputStream(value);
				} else {
					return new ByteArrayInputStream(new byte[]{});
				}
			} else if (type == TYPE_GRID_FS) {
				String fileName = (String)result.get(FILE_NAME);
				if (fileName == null) {
					throw new IllegalStateException("There is no file name.");
				}
				GridFSDBFile gridFsFile = gridFs.findOne(fileName);
				if (gridFsFile == null) {
					throw new IllegalStateException("There is no grid fs file for the given key.");	
				}
				return gridFsFile.getInputStream();
			} else if (type == TYPE_EMPTY) {
				return new ByteArrayInputStream(new byte[]{});
			} else {
				throw new IllegalStateException("Unknown mongo-db value storage type.");
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
				Stopwatch watch = Stopwatch.createStarted();
				super.close();
				byte[] keyString = adoptKey(key);
				byte[] byteArray = toByteArray();
				if (byteArray.length < MAX_BSON_SIZE) {
					collection.update(new BasicDBObject(KEY, keyString), new BasicDBObject(KEY, keyString).append(TYPE, TYPE_BSON).append(VALUE, byteArray), true, false);
				} else {
					// do grid fs
					GridFSInputFile gridFsFile = gridFs.createFile(byteArray);
					String fileName = URIUtils.encode(key);
					gridFsFile.setFilename(fileName);
					gridFsFile.save();
					collection.update(new BasicDBObject(KEY, keyString), new BasicDBObject(KEY, keyString).append(TYPE, TYPE_GRID_FS).append(FILE_NAME, fileName), true, false);
				}
				writeTimeStatistic.track(watch.stop().elapsed(TimeUnit.MICROSECONDS));
				entrySizeStatistic.track(byteArray.length);
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
	synchronized public boolean checkAndCreate(byte[] key) {				
		try {
			collection.insert(new BasicDBObject(KEY, adoptKey(key)).append(TYPE, TYPE_EMPTY));
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

	@Override
	public void close() {
		
	}
	
	@Override
	public void flush() {
		db.cleanCursors(true);
	}
	
	private class Cursor implements ICursor {
		final DBCursor dbCursor;
		
		public Cursor(DBCursor dbCursor) {
			super();
			this.dbCursor = dbCursor;
		}

		@Override
		public boolean hasNext() {
			return dbCursor.hasNext();
		}

		@Override
		public byte[] next() {
			DBObject next = dbCursor.next();
			if (next == null) {
				throw new IndexOutOfBoundsException();
			}
			return (byte[])next.get(KEY);
		}

		@Override
		public InputStream openNextInputStream() {
			DBObject next = dbCursor.curr();
			if (next == null) {
				throw new IndexOutOfBoundsException();
			}
			return inputStreamFromDBObject(next);
		}

		@Override
		public void close() {
			dbCursor.close();
		}	
		
	}

	@Override
	public ICursor cursor(byte[] key) {
		DBCursor cursor = collection.find(new BasicDBObject(KEY, new BasicDBObject("$gte", adoptKey(key))));
		cursor.sort(new BasicDBObject(KEY, 1));
		return new Cursor(cursor);		
	}	
	
}
