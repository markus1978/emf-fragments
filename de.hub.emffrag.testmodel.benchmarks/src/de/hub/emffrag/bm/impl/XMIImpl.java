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
import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;

public class XMIImpl extends AbstractImpl {
    
    private ResourceSet rs = null;
    
    protected EPackage[] getRegularPackages() {
        return new EPackage[] {
                de.hub.emffrag.testmodels.regular.Core.CorePackage.eINSTANCE,
                de.hub.emffrag.testmodels.regular.DOM.DOMPackage.eINSTANCE,
                de.hub.emffrag.testmodels.regular.PrimitiveTypes.PrimitiveTypesPackage.eINSTANCE
        };
    }
    
    protected EPackage[] getFragPackages() {
        return new EPackage[] {
                de.hub.emffrag.testmodels.frag.Core.CorePackage.eINSTANCE,
                de.hub.emffrag.testmodels.frag.DOM.DOMPackage.eINSTANCE,
                de.hub.emffrag.testmodels.frag.PrimitiveTypes.PrimitiveTypesPackage.eINSTANCE,
                de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE
        };
    }
    
    protected EPackage[] getCDOPackages() {
        return new EPackage[] {
                de.hub.emffrag.testmodels.cdo.Core.CorePackage.eINSTANCE,
                de.hub.emffrag.testmodels.cdo.DOM.DOMPackage.eINSTANCE,
                de.hub.emffrag.testmodels.cdo.PrimitiveTypes.PrimitiveTypesPackage.eINSTANCE
        };
    }
    
    private void initializeResourceSet() {
        registerPackages(EPackage.Registry.INSTANCE, getRegularPackages());
        rs = new ResourceSetImpl();
        registerResourceFactories(rs.getResourceFactoryRegistry());
    }

    public int importModel(SaveBenchmark bm, String xmiFileName, String modelName, Gauge gauge) {
        return 0;
    }
    
    public int traverseModel(LoadBenchmark bm, String xmiFileName, String modelName,  Gauge gauge) {
        initializeResourceSet();
        
        gauge.startTimeMeasure();
        Resource morsaResource = rs.getResource(URI.createURI(xmiFileName), true);
        
        TreeIterator<EObject> allContents = morsaResource.getAllContents();
        int count = 0;
        while (allContents.hasNext()) {
            allContents.next();
            count++;
            if (count == 1000) {
                gauge.takeMemoryMeasure();
            }
        }
        gauge.takeTimeMeasure();
        return count;
    }
    
    public int grabatsQueryModel(QueryBenchmark benchmark, Gauge gauge) {
        initializeResourceSet();
        
        gauge.startTimeMeasure();
        Resource resource = rs.getResource(URI.createURI(benchmark.getPSourceXmiURI()), true);
        int result = grabatsQueryAsCode(resource.getContents().get(0), gauge, CorePackage.eINSTANCE, DOMPackage.eINSTANCE);
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

	@Override
	public EObject create(ImportTestModel benchmark, EObject parent, boolean frag) {
		// TODO Auto-generated method stub
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
