package de.hub.emf.pfb;

import java.util.concurrent.TimeUnit;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.caliper.AfterExperiment;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.base.Stopwatch;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IBaseDataStore;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.Fragmentation;
import de.hub.emffrag.mongodb.MongoDBDataStore;
import de.hub.jstattrack.Statistics;
import pfbmm.PfbmmPackage;

public class CreateBenchmark {
	
	@Param({"100","200", "300"}) int numberOfObjects = 1000000;
	
	public CreateBenchmark() {
		EmfFragActivator.standalone(PfbmmPackage.eINSTANCE);
		EmfFragActivator.instance.logInStandAlone = false;
		EmfFragActivator.instance.logFragmentPrettyPrints = false;
	}
	
	@BeforeClass
	public static void init() {
		EmfFragActivator.standalone(PfbmmPackage.eINSTANCE);
		EmfFragActivator.instance.logInStandAlone = true;
		EmfFragActivator.instance.logFragmentPrettyPrints = false;
	}
	
	private BaseGenerator generator = null;
	private Fragmentation fragmentation = null;
	
	@BeforeExperiment
	@Before
	public void before() {
		IBaseDataStore baseDataStore = new MongoDBDataStore("localhost", "benchmark", true);
		IDataStore dataStore = new DataStoreImpl(baseDataStore, URI.createURI("mongodb://localhost/benchmark"));
		fragmentation = new Fragmentation(dataStore, 100);
	
		generator = new BaseGenerator(10, 5, 25, 2.5);
	  	generator.setResource(fragmentation.getRootFragment());
	}
	
	@AfterExperiment
	@After
	public void after() {
		
	}
	
	@Benchmark
	@Test
	public void run() {		
		generator.generate(numberOfObjects);
		fragmentation.close();
	}
	
	public static void main(String args[]) {
		CreateBenchmark bm = new CreateBenchmark();
		bm.before();
		Stopwatch watch = Stopwatch.createStarted();
		bm.run();
		System.out.println(watch.elapsed(TimeUnit.SECONDS));
		bm.after();
		
		System.out.println(Statistics.reportToString());
	}
}
