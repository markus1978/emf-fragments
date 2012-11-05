package de.hub.emffrag.fragmentation;

import java.io.File;

import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.FileSystemDataStore;

public class FileSystemDataStoreTests extends BasicFragmentationTests {
	protected DataStore createTestDataStore() {
		return new FileSystemDataStore("testmodel", new File("testmodel"), true);
	}
}
