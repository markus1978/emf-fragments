package de.hub.emffrag.mongodb;

import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.mongodb.MongoDBDataStore;
import de.hub.emffrag.tests.PerformanceMeasures;
import org.junit.Test;

@SuppressWarnings("all")
public class MongodbPerformanceMeasures extends PerformanceMeasures {
  @Override
  public IBaseDataStore createDataStore() {
    return new MongoDBDataStore("localhost", "testmodel", true);
  }
  
  @Test
  @Override
  public void measureModelCreate() {
    super.measureModelCreate();
  }
}
