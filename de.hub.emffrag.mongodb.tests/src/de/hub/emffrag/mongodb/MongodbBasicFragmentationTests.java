package de.hub.emffrag.mongodb;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag2.BasicFragmentationTests;

public class MongodbBasicFragmentationTests extends BasicFragmentationTests {

	
	@Override
	protected IBaseDataStore createBaseDataStore() {
		URI uri = URI.createURI("mongodb://localhost/testmodel");
		return new MongoDBDataStore(uri.authority(), uri.path().substring(1), true);	
	}
}
