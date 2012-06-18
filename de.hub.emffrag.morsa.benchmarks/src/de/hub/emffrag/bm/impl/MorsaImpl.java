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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.benchmark.LoadBenchmark;
import de.hub.emffrag.bm.benchmark.LoadFromTestModel;
import de.hub.emffrag.bm.benchmark.QueryBenchmark;
import de.hub.emffrag.bm.benchmark.SaveBenchmark;
import es.modelum.morsa.MorsaResource;
import es.modelum.morsa.MorsaResourceFactoryImpl;
import es.modelum.morsa.backend.mongodb.MongoDBMorsaBackendFactory;
import es.modelum.morsa.cache.policy.FIFOEObjectCachePolicy;

public class MorsaImpl extends AbstractImpl {
    
    protected void registerResourceFactories(Resource.Factory.Registry registry) {
        super.registerResourceFactories(registry);
        registry.getProtocolToFactoryMap().put("morsa",  new MorsaResourceFactoryImpl(new MongoDBMorsaBackendFactory()));
    }
    
    private ResourceSet rs = null;
    
    private void initializeResourceSet() {        
        rs = new ResourceSetImpl();
        registerResourceFactories(rs.getResourceFactoryRegistry());
    }

    public int importModel(SaveBenchmark bm, String xmiFileName, String modelName, Gauge gauge) {
        final int saveCacheSize = 1024;
        initializeResourceSet();        
        Resource metaResource = rs.getResource(URI.createURI("../de.hub.emffrag/models/JDTAST.ecore"), true);
        
        for (EObject ePackageObj: metaResource.getContents()) {
            EPackage ePackage = (EPackage)ePackageObj;
            EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);
        }
        
        Resource r = rs.getResource(URI.createURI(xmiFileName), true);
        List<EObject> roots = new ArrayList<EObject>(r.getContents());
                
        gauge.startTimeMeasure();
        Resource morsaResource = rs.createResource(URI.createURI("morsa://" + modelName));
        morsaResource.getContents().clear();
        for (EObject root : roots) {
            morsaResource.getContents().add(root);
        }

        try {
            Map<Object, Object> options = new HashMap<Object, Object>();
            options.put(MorsaResource.OPTION_SERVER_URI, "localhost");
            if (saveCacheSize > 0) {
                options.put(MorsaResource.OPTION_MAX_SAVE_CACHE_SIZE, saveCacheSize);
            }
            options.put(MorsaResource.OPTION_PRINT_TRACE, false);
            gauge.takeMemoryMeasure();
            morsaResource.save(options);
            gauge.takeMemoryMeasure();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gauge.takeTimeMeasure();
        
        int count = 0;
        TreeIterator<EObject> allContents = morsaResource.getAllContents();
        while(allContents.hasNext()) {
            allContents.next();
            count++;
        }
        return count;
    }
    
    private void loadMorsaResource(Resource morsaResource) {
        try {
            Map<Object, Object> options = new HashMap<Object, Object>();
            options.put(MorsaResource.OPTION_SERVER_URI,  "localhost");
            options.put(MorsaResource.OPTION_CACHE_POLICY, FIFOEObjectCachePolicy.class.getCanonicalName());
            options.put(MorsaResource.OPTION_MAX_CACHE_SIZE, 1000);
            options.put(MorsaResource.OPTION_CACHE_FLUSH_FACTOR, 0.3f);
            options.put(MorsaResource.OPTION_DEMAND_LOAD_STRATEGY, MorsaResource.OPTION_SINGLE_LOAD_STRATEGY);
            options.put(MorsaResource.OPTION_DEMAND_LOAD, true);
            options.put(MorsaResource.OPTION_PRINT_TRACE, false);
            options.put(MorsaResource.OPTION_READ_ONLY_MODE, true);

            morsaResource.load(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int traverseModel(LoadBenchmark bm, String xmiFileName, String modelName, Gauge gauge) {
        initializeResourceSet();
//        for (EPackage ePackage: getRegularPackages()) {
//            rs.getPackageRegistry().remove(ePackage.getNsURI());
//            EPackage.Registry.INSTANCE.remove(ePackage.getNsURI());
//        }
        
        gauge.startTimeMeasure();
        Resource morsaResource = rs.createResource(URI.createURI("morsa://" + modelName));       
        loadMorsaResource(morsaResource);
        
        TreeIterator<EObject> allContents = morsaResource.getAllContents();
        int count = 0;
        while (allContents.hasNext()) {
            allContents.next();
            count++;
            if ((count - 1000)% 100000 == 0) {
                gauge.takeMemoryMeasure();
            }
        }
        gauge.takeTimeMeasure();
        return count;
    }
    
    public int grabatsQueryModel(QueryBenchmark benchmark, Gauge gauge) {
        initializeResourceSet();
        
        
        gauge.startTimeMeasure();
        MorsaResource morsaResource = (MorsaResource)rs.createResource(URI.createURI("morsa://" + benchmark.getPModelName()));       
        loadMorsaResource(morsaResource);
        
        int result = 0;
        if (benchmark.isWithIndex()) {
        	result = grabatsQueryAsCode((MorsaResource)morsaResource, gauge);
        } else {
        	EPackage dom = morsaResource.loadMetamodel("org.amma.dsl.jdt.dom");
        	EPackage core = morsaResource.loadMetamodel("org.amma.dsl.jdt.core");	
        	result = grabatsQueryAsCode(morsaResource.getContents().get(0), gauge, core, dom);
        }
        gauge.takeTimeMeasure();
        return result;        
    }
    
    public interface ICondition {
        public boolean fulfilled(EObject eObject);
    }
    
    protected void collect(EObject eObject, ICondition condition, ICondition continueCondition) {
        if (condition.fulfilled(eObject)) {
            
        }
        if (continueCondition.fulfilled(eObject)) {
	        for (EObject content: eObject.eContents()) {
	            collect(content, condition, continueCondition);
	        }
        }
    }
    
    private int count = 0;
    
    protected int grabatsQueryAsCode(EObject eObject, final Gauge gauge, EPackage corePackage, EPackage domPackage) {
        count = 0;       
        final EClass initializerClass = (EClass)domPackage.getEClassifier("Initializer");
        final EClass typeDeclarationClass = (EClass)domPackage.getEClassifier("TypeDeclaration");
        final EClass methodDeclarationClass = (EClass)domPackage.getEClassifier("MethodDeclaration");
        final EClass modifierClass =  (EClass)domPackage.getEClassifier("Modifier");
        final EClass simpleNameClass =  (EClass)domPackage.getEClassifier("SimpleName");
                
        collect(eObject, new ICondition() {            
            @Override
            public boolean fulfilled(EObject eObject) {
                boolean result = eObject.eClass() == typeDeclarationClass;
                if (result) {
                	boolean found = grabatsQueryTypeDeclaration(eObject, typeDeclarationClass, methodDeclarationClass, modifierClass, simpleNameClass);
                    if (found) {
                        count++;
                        if ((count - 4) % 5 == 0) {
                            gauge.takeMemoryMeasure();
                        }
                    }   	
                }
                return result;
            }
        }, new ICondition() {			
			@Override
			public boolean fulfilled(EObject eObject) {
			
				EClass eClass = eObject.eClass();
				
				return eClass.getEPackage().getName().equals("Core") ||						
						!(initializerClass == eClass || 
						methodDeclarationClass == eClass);				
			}
		});
        gauge.takeMemoryMeasure();
        return count;
    }
    
    private int grabatsQueryAsCode(MorsaResource morsaResource, Gauge gauge) {
        int count = 0;

        EPackage dom = morsaResource.loadMetamodel("org.amma.dsl.jdt.dom");
        EClass typeDeclarationClass = (EClass)dom.getEClassifier("TypeDeclaration");
        EClass methodDeclarationClass = (EClass)dom.getEClassifier("MethodDeclaration");
        EClass modifierClass = (EClass)dom.getEClassifier("Modifier");
        EClass simpleNameClass = (EClass)dom.getEClassifier("SimpleName");
        Collection<EObject> typeDeclarationList = morsaResource.loadObjects(typeDeclarationClass, true);

        Iterator<EObject> typeDeclarationIterator = typeDeclarationList.iterator();
        while (typeDeclarationIterator.hasNext()) {
            boolean found = grabatsQueryTypeDeclaration(typeDeclarationIterator.next(), typeDeclarationClass, methodDeclarationClass, modifierClass, simpleNameClass);
            if (found) {
                count++;
                if ((count - 3) % 5 == 0) {
                    gauge.takeMemoryMeasure();
                }
            }            
        }
        return count;
    }

	@Override
	public EObject create(ImportTestModel benchmark, EObject parent, boolean frag) {		
		return null;
	}    
    
	@Override
	public int traverse(LoadFromTestModel benchmark, Gauge gauge, int loadSize) {	
		return 0;
	}

	@Override
	public void finalize(Benchmark benchmark) {
		
	}  
   
}
