package de.hub.emffrag.mongodb;

import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.Assert;

import org.junit.Test;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.AbstractFragmentationTests;

public class MongodbGridFsTest extends AbstractFragmentationTests {
	
	private final byte[] testKey = "TEST_KEY".getBytes();
	private final byte[] testData = new byte[1024*1024*18]; // 18MB is larger than mongo's MAX_BSON_SIZE
	
	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return MongoDBDataStoreFactory.createDataStore();
	}
	
	@Test
	public void testWriteToGridFS() throws Exception {		
		OutputStream os = dataStore.openOutputStream(testKey);
		
		os.write(testData);
		os.close();
	}
	
	@Test
	public void testReadFromGridFS() throws Exception {
		testWriteToGridFS();
		
		InputStream is = dataStore.openInputStream(testKey);
		int i = 0;
		while (is.read() != -1) i++;
		
		Assert.assertEquals(testData.length, i);
	}
}
