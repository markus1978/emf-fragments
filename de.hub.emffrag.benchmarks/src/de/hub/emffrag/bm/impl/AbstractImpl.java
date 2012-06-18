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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.benchmark.LoadBenchmark;
import de.hub.emffrag.bm.benchmark.LoadFromTestModel;
import de.hub.emffrag.bm.benchmark.QueryBenchmark;
import de.hub.emffrag.bm.benchmark.SaveBenchmark;

public abstract class AbstractImpl {
    
    protected void registerResourceFactories(Resource.Factory.Registry registry) {
        registry.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        registry.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }
    
    protected void registerPackages(EPackage.Registry registry, EPackage[] packages) {
        for (EPackage ePackage: packages) {
            registry.put(ePackage.getNsURI(), ePackage);
        }
    }
    
    public interface Gauge {
        public final static Gauge NULL_GAUGE = new Gauge() {
            @Override
            public void startTimeMeasure() {                
            }

            @Override
            public void takeTimeMeasure() {                
            }

            @Override
            public void takeMemoryMeasure() {               
            }            
        };
        
        public void startTimeMeasure();
        public void takeTimeMeasure();
        
        public void takeMemoryMeasure();
    }
    
    @SuppressWarnings("unchecked")
    protected boolean grabatsQueryTypeDeclaration(EObject typeDeclaration, 
            EClass typeDeclarationClass,
            EClass methodDeclarationClass,
            EClass modifierClass,
            EClass simpleNameClass) {
        EObject typeName = (EObject)typeDeclaration.eGet(typeDeclarationClass.getEStructuralFeature("name"));
        if (typeName == null) {
            return false;
        }

        String typeQName = (String)typeName.eGet(typeName.eClass().getEStructuralFeature("fullyQualifiedName"));
        EReference bodyDeclarationsRef = (EReference)typeDeclarationClass.getEStructuralFeature("bodyDeclarations");

        Object o = typeDeclaration.eGet(bodyDeclarationsRef);
        if (o != null) {
            List<EObject> bodyDeclarationList = (List<EObject>)o;
            boolean found = false;
            for (int j = 0; j < bodyDeclarationList.size() && !found; j++) {
                EObject bodyDeclaration = bodyDeclarationList.get(j);
                if (bodyDeclaration.eClass() == methodDeclarationClass) {
                    EReference modifiersRef = (EReference)methodDeclarationClass.getEStructuralFeature("modifiers");
                    Object o2 = bodyDeclaration.eGet(modifiersRef);
                    if (o2 != null) {
                        List<EObject> modifiersList = (List<EObject>)o2;
                        boolean foundStatic = false;
                        boolean foundPublic = false;
                        for (int k = 0; k < modifiersList.size(); k++) {
                            EObject modifier = modifiersList.get(k);
                            if (modifier.eClass() == modifierClass) {
                                boolean isStatic = (Boolean)modifier.eGet(modifierClass.getEStructuralFeature("static"));
                                boolean isPublic = (Boolean)modifier.eGet(modifierClass.getEStructuralFeature("public"));
                                if (isStatic)
                                    foundStatic = true;
                                if (isPublic)
                                    foundPublic = true;
                            }
                        }

                        if (foundStatic && foundPublic) {
                            EReference returnTypeRef = (EReference)methodDeclarationClass.getEStructuralFeature("returnType");
                            Object o3 = bodyDeclaration.eGet(returnTypeRef);
                            if (o3 != null) {
                                EObject returnType = (EObject)o3;
                                EReference typeNameRef = (EReference)returnType.eClass().getEStructuralFeature("name");

                                if (typeNameRef != null) {
                                    EObject returnTypeName = (EObject)returnType.eGet(typeNameRef);
                                    if (returnTypeName != null) {
                                        String returnTypeQName = (String)returnTypeName.eGet(returnTypeName.eClass()
                                                .getEStructuralFeature("fullyQualifiedName"));
                                        if (returnTypeQName.equals(typeQName)) {
                                            EObject simpleName = (EObject)bodyDeclaration.eGet(methodDeclarationClass
                                                    .getEStructuralFeature("name"));
                                            simpleName.eGet(simpleNameClass.getEStructuralFeature("fullyQualifiedName"));
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public abstract int traverseModel(LoadBenchmark benchmark, String pSourceXmiURI, String pModelName, Gauge myGauge);

    public abstract int importModel(SaveBenchmark benchmark, String pSourceXmiURI, String pModelName, Gauge myGauge);
    
    public abstract int grabatsQueryModel(QueryBenchmark benchmark, Gauge gauge);
    
    public abstract EObject create(ImportTestModel benchmark, EObject parent, boolean frag);
    
    public abstract void finalize(Benchmark benchmark);
    
    public void addStatistics(Benchmark benchmark) {
    	
    }

	public abstract int traverse(LoadFromTestModel benchmark, Gauge gauge, int loadSize);
}
