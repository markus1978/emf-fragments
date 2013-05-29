package de.hub.emffrag.fragmentation;

import java.io.File;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.FileSystemDataStore;
import de.hub.emffrag.datastore.IDataStore;

public class FileSystemDataStoreTests extends BasicFragmentationTests {
	protected IDataStore createTestDataStore() {
		URI uri = URI.createURI("file://" + new File("testmodel").getAbsolutePath());
		return new DataStoreImpl(new FileSystemDataStore("testmodel", new File("testmodel"), true), uri);
	}
}
