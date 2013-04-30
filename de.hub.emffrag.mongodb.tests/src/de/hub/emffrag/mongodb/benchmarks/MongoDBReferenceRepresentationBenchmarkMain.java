package de.hub.emffrag.mongodb.benchmarks;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.benchmarks.reference1.ReferenceRepresentationBenchmark;
import de.hub.emffrag.benchmarks.reference1.ReferenceRepresentationBenchmarkConfigurationImpl;
import de.hub.emffrag.benchmarks.reference1.ReferenceRepresentationBenchmarkMain;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.fragmentation.NoReferencesIdSemantics;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;
import de.hub.emffrag.mongodb.EmfFragMongoDBActivator;
import de.hub.emffrag.mongodb.MongoDBDataStore;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

public class MongoDBReferenceRepresentationBenchmarkMain extends ReferenceRepresentationBenchmarkMain {

	private String name;
	private boolean importModel;
	
	@Override
	protected DataStore createTestDataStore() {
		EmfFragMongoDBActivator.standalone();
		return new MongoDBDataStore("localhost", name, importModel);
	}
	
	public static void main(String args[]) {
		MongoDBReferenceRepresentationBenchmarkMain main = new MongoDBReferenceRepresentationBenchmarkMain();
		main.name = args[4]; // db name	
		main.importModel = args[5].equals("import");
		
		main.numberOfObjects = Long.parseLong(args[0]);
		main.numberOfObjectsToTravers = Long.parseLong(args[1]);
		main.fragmentSize = Integer.parseInt(args[2]);
		main.numberOfChildren = Long.parseLong(args[3]);
		
		setUp();
		EmfFragActivator.instance.idSemantics = new NoReferencesIdSemantics(IdBehaviour.defaultModel);
		EmfFragActivator.instance.cacheSize = 1000;
		
		main.resetErrorsAndWarnings();
		main.registerPackages();
		main.standardInitialization();
		
		EmfFragActivator.instance.defaultModel = main.model;
		
		ReferenceRepresentationBenchmarkConfigurationImpl configuration = null;
		if (args[6].equals("pos")) {
			configuration = new ReferenceRepresentationBenchmarkConfigurationImpl(TestModelFactory.eINSTANCE, TestModelPackage.eINSTANCE.getTestObject_CrossReferences());
		} else {
			configuration = new ReferenceRepresentationBenchmarkConfigurationImpl(TestModelFactory.eINSTANCE, TestModelPackage.eINSTANCE.getTestObjectWithIndexes_IndexedReferences());
		}

		ReferenceRepresentationBenchmark benchmark = new ReferenceRepresentationBenchmark(
				Long.parseLong(args[0]), // objects
				Long.parseLong(args[1]), // traversed objects
				Long.parseLong(args[3]), // children
				Integer.parseInt(args[2]), // fragment size
				configuration);
		
		if (main.importModel) {
			main.runImport(benchmark);		
		} else {
			main.runTask(benchmark);
		}
	}
}
