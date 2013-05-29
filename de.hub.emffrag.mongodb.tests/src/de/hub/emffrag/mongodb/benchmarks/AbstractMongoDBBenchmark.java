package de.hub.emffrag.mongodb.benchmarks;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.benchmarks.reference.IBenchmarkFactory;
import de.hub.emffrag.benchmarks.reference.ReferenceBenchmark;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.mongodb.EmfFragMongoDBActivator;
import de.hub.emffrag.mongodb.MongoDBDataStore;

public abstract class AbstractMongoDBBenchmark extends ReferenceBenchmark implements IBenchmarkFactory {

	@Override
	protected IDataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		URI uri = URI.createURI("mongodb://localhost/" + parameters.getModelName());
		MongoDBDataStore baseDataStore = new MongoDBDataStore("localhost", parameters.getModelName(), parameters.command == Command.importModel);
		return new DataStoreImpl(baseDataStore, uri);
	}

}
