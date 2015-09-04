package de.hub.emf.pfb;

import java.util.concurrent.TimeUnit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.After;
import org.junit.Assert;
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
import de.hub.emffrag.proxies.Proxy;
import de.hub.jstattrack.Statistics;
import de.hub.jstattrack.ValueStatistic;
import de.hub.jstattrack.services.Summary;
import pfbmm.PfbmmPackage;

public class TraverseBenchmark {
	
	@Param({"1000", "2000", "3000" }) int numberOfObjects = 1000000;
	
	private ValueStatistic valueStat = new ValueStatistic().with(Summary.class).register(TraverseBenchmark.class, "values");
	
	public TraverseBenchmark() {
		EmfFragActivator.standalone(PfbmmPackage.eINSTANCE);
		EmfFragActivator.instance.logInStandAlone = false;
		EmfFragActivator.instance.logFragmentPrettyPrints = false;
	}
	
	@BeforeClass
	public static void init() {
		EmfFragActivator.standalone(PfbmmPackage.eINSTANCE);
	}
	
	private Fragmentation fragmentation = null;
	private IDataStore dataStore = null;

	private boolean extendedTraverse = false;
	private boolean create = false;
	
	@Before
	public void beforeJunit() {
		EmfFragActivator.instance.logInStandAlone = true;
		EmfFragActivator.instance.logFragmentPrettyPrints = false;
	}
	
	protected IDataStore createDataStore(boolean drop) {
		IBaseDataStore baseDataStore = new MongoDBDataStore("localhost", "benchmark", drop);
		dataStore = new DataStoreImpl(baseDataStore, URI.createURI("mongodb://localhost/benchmark"));
		return dataStore;
	}
	
	@BeforeExperiment
	@Before
	public void before() {
		if (create) {
			dataStore = createDataStore(true);
			fragmentation = new Fragmentation(dataStore, 100);
		
			BaseGenerator generator = new BaseGenerator(10, 5, 25, 2.5);
		  	generator.setResource(fragmentation.getRootFragment());
		  	generator.generate(numberOfObjects);
		  	fragmentation.close();
		}
	  
		dataStore = createDataStore(false);
		fragmentation = new Fragmentation(dataStore, (int)(100));
	}
	
	@AfterExperiment
	@After
	public void after() {
		fragmentation.close();
		dataStore.close();
	}
	
	@Benchmark
	@Test
	public void run() {
		int i = 0;
		TreeIterator<EObject> it = fragmentation.getRootFragment().getAllContents();
		while (it.hasNext()) {
			i++;
			EObject next = it.next();
			if (extendedTraverse ) {
				int valueCount = 0;
				for (EStructuralFeature feature: next.eClass().getEAllStructuralFeatures()) {
					if (!(feature instanceof EReference && ((EReference)feature).isContainment())) {
						if (feature.isMany()) {
							EList<?> values = (EList<?>)next.eGet(feature);
							for (Object value: values) {
								Assert.assertTrue( !(value instanceof EObject) || value instanceof Proxy);
								valueCount++;
							}
						} else {
							Object value = next.eGet(feature);
							Assert.assertTrue( !(value instanceof EObject) || value instanceof Proxy);
							valueCount++;
						}
					}
				}
				valueStat.track(valueCount);
			}
			if (i % 10000 == 0) {
				System.gc();
				System.out.println(i);
			}
		}
		org.junit.Assert.assertTrue(i > 100);
	}
	
	public static void main(String[] args) {
		TraverseBenchmark benchma = new TraverseBenchmark();
		benchma.before();
		Stopwatch stopwatch = Stopwatch.createStarted();
		benchma.run();
		System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
		benchma.after();
		
		StringBuilder out = new StringBuilder();
		Statistics.report(out);
		System.out.println(out);
	}
}
