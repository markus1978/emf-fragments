package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.mongodb.MongoDBDataStore;
import de.hub.emffrag.tests.FragmentationTests;

@SuppressWarnings("all")
public class MongodbFragmentationTests extends FragmentationTests {
  @Override
  public IBaseDataStore createDataStore() {
    return new MongoDBDataStore("localhost", "testmodel", true);
  }
}
