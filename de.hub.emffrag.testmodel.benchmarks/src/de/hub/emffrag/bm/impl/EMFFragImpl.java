/*******************************************************************************
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.hub.emffrag.bm.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Throwables;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;

import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.benchmark.LoadBenchmark;
import de.hub.emffrag.bm.benchmark.LoadFromTestModel;
import de.hub.emffrag.bm.benchmark.QueryBenchmark;
import de.hub.emffrag.bm.benchmark.SaveBenchmark;
import de.hub.emffrag.hbase.HBaseKeyValueStore;
import de.hub.emffrag.kvstore.IKeyValueStore;
import de.hub.emffrag.reflective.FStoreImpl;
import de.hub.emffrag.reflective.FragmentedModel;
import de.hub.emffrag.reflective.FragmentedModel.Statitics;
import de.hub.emffrag.testmodels.frag.Core.CorePackage;
import de.hub.emffrag.testmodels.frag.DOM.DOMPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.util.ILogger;

public class EMFFragImpl extends XMIImpl {
    
    private ResourceSet rs = null;
    
    protected EPackage[] getFrag2Packages() {
        return new EPackage[] {
                de.hub.emffrag.testmodels.fragf2.Core.CorePackage.eINSTANCE,
                de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage.eINSTANCE,
                de.hub.emffrag.testmodels.fragf2.PrimitiveTypes.PrimitiveTypesPackage.eINSTANCE
        };
    }
    
    private void initializeResourceSet(boolean frag2) {
    	if (frag2) {
    		registerPackages(EPackage.Registry.INSTANCE, getFrag2Packages());
    	} else {
    		registerPackages(EPackage.Registry.INSTANCE, getFragPackages());
    	}
        rs = new ResourceSetImpl();
        registerResourceFactories(rs.getResourceFactoryRegistry());        
        
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(ILogger.class).toInstance(new ILogger() {                        
                    @Override
                    public void log(int level, String message, Throwable exception) {
//                        System.out.println(message);
                    }
                });
                binder.bind(IKeyValueStore.class).to(HBaseKeyValueStore.class);
                binder.bind(Integer.class).annotatedWith(Names.named(FragmentedModel.OPTION_WEAK_UNLOAD_CACHE_SIZE)).toInstance(100);
            }           
        });        
        FStoreImpl.INSTANCE = injector.getInstance(FStoreImpl.class);
    }
    
    private void clearDB(String testTable) {
        HBaseKeyValueStore hBaseKeyValueStore = new HBaseKeyValueStore();
        if (hBaseKeyValueStore.tableExists(testTable)) {
            hBaseKeyValueStore.deleteTable(testTable);
        }
        if (hBaseKeyValueStore.tableExists(testTable + "_OIDs")) {
            hBaseKeyValueStore.deleteTable(testTable + "_OIDs");
        }
    }

    public int importModel(SaveBenchmark benchmark, String xmiFileName, String modelName, Gauge gauge) {
        clearDB(modelName);        
        initializeResourceSet(benchmark.isFrag2());                        
        if (benchmark.isFrag2()) {
        	FStoreImpl.INSTANCE.initialize(Arrays.asList(getFrag2Packages()), modelName, true);
        } else {
        	FStoreImpl.INSTANCE.initialize(Arrays.asList(getFragPackages()), modelName, true);
        }
                
        gauge.startTimeMeasure();     
        Resource resource = rs.createResource(URI.createURI(xmiFileName));
        try {
            resource.load(null);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        EList<EObject> contents = resource.getContents();
        for (EObject content: new ArrayList<EObject>(contents)) {
            FStoreImpl.INSTANCE.addContent(content);
        }
       
        contents = FStoreImpl.INSTANCE.getContents();
        gauge.takeMemoryMeasure();
        
        FStoreImpl.INSTANCE.save();
        gauge.takeTimeMeasure();
       
        int count = 0;
        for (EObject content: contents) {
            count++;
            TreeIterator<EObject> eAllContents = content.eAllContents();
            while (eAllContents.hasNext()) {
                eAllContents.next();
                count++;
            }
        }
        addStatistics(benchmark);
        return count;
    }
    
    public int traverseModel(LoadBenchmark benchmark, String xmiFileName, String modelName,  Gauge gauge) {
        initializeResourceSet(false);                        
        FStoreImpl.INSTANCE.initialize(Arrays.asList(getFragPackages()), modelName, true);
        
        gauge.startTimeMeasure();        
        EObject content = FStoreImpl.INSTANCE.getContents().get(0);        
        TreeIterator<EObject> allContents = content.eAllContents();
        int count = 1;
        while (allContents.hasNext()) {
            allContents.next();
            count++;
            if (count - 1000 % 100000 == 0) {
                gauge.takeMemoryMeasure();
            }
        }
        gauge.takeTimeMeasure();
        addStatistics(benchmark);
        return count;
    }
    
    @Override
    public int grabatsQueryModel(QueryBenchmark benchmark, Gauge gauge) {
        initializeResourceSet(false);                        
        FStoreImpl.INSTANCE.initialize(Arrays.asList(getFragPackages()), benchmark.getPModelName(), true);
        
        gauge.startTimeMeasure();
        int result = grabatsQueryAsCode(FStoreImpl.INSTANCE.getContents().get(0), gauge,
        		benchmark.isFrag2() ? de.hub.emffrag.testmodels.fragf2.Core.CorePackage.eINSTANCE : CorePackage.eINSTANCE,
        		benchmark.isFrag2() ? de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage.eINSTANCE : DOMPackage.eINSTANCE);
        gauge.takeTimeMeasure();
        return result;        
    }

    private boolean importTestModelInitialized = false;
    private int id = 0;
    
    private void initializeImportTestModel(Benchmark benchmark) {
    	if (!importTestModelInitialized) {
    		id = 0;
    		clearDB(benchmark.getPModelName());
	    	initializeResourceSet(false);
	        FStoreImpl.INSTANCE.initialize(Arrays.asList(getFragPackages()), benchmark.getPModelName(), false);
	        importTestModelInitialized = true;
    	}
    }
	
  
	@Override
	public EObject create(ImportTestModel benchmark, EObject eParent, boolean frag) {
		initializeImportTestModel(benchmark);
		TestObject parent = (TestObject)eParent;
		TestObject testObject = TestModelFactory.eINSTANCE.createTestObject();
		testObject.setName("value_" + id++);
		if (parent != null) {
			if (frag) {
				parent.getFragmentedContents().add(testObject);
			} else {
				parent.getRegularContents().add(testObject);
			}
		} else {
			FStoreImpl.INSTANCE.addContent(testObject);
		}
//		FStoreImpl.INSTANCE.getFragmentSet().unloadUnreferencedFragments(null);
		return testObject;
	}
	
	@Override
	public void finalize(Benchmark benchmark) {
		FStoreImpl.INSTANCE.save();
	}

	@Override
	public void addStatistics(Benchmark benchmark) {
		Statitics statitics = FStoreImpl.INSTANCE.getFragmentSet().getStatitics();
		benchmark.setMaxActiveFagments(statitics.maxActiveFragments);
		benchmark.setMaxWeakFragments(statitics.maxWeakFragments);
		benchmark.setLoads(statitics.loads);
		benchmark.setUnloads(statitics.unloads);
		benchmark.setWeakLoads(statitics.weakLoads);
		benchmark.setWeakUnloads(statitics.weakUnloads);
	}

	@Override
	public int traverse(LoadFromTestModel benchmark, Gauge gauge, int loadSize) {
        initializeResourceSet(false);                        
        FStoreImpl.INSTANCE.initialize(Arrays.asList(getFragPackages()), benchmark.getPModelName(), false);
        
        gauge.startTimeMeasure();        
        EObject content = FStoreImpl.INSTANCE.getContents().get(0);        
        TreeIterator<EObject> allContents = content.eAllContents();
        int count = 1;
        while (allContents.hasNext() && count < loadSize) {
            allContents.next();
            count++;
            if (count - 1000 % 100000 == 0) {
                gauge.takeMemoryMeasure();
            }
        }
        gauge.takeTimeMeasure();
        addStatistics(benchmark);
        return count;
	}
    
    
}
