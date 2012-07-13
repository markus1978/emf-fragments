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
package de.hub.emffrag.bm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;

import de.hub.emffrag.testmodels.reflective.testmodel.TestModelPackage;

public class XMIPerformance {
    
    private final double[] sizes = Util.logSpace(6, 10, 20);
    private final double[] objectSizes = new double[] {1}; //Util.logSpace(2, 10, 4);
    private final int repetitionSize = (int)5e4;
    private final boolean isContainment = false;
    

    
    private static XMLParserPoolImpl xmlParserPool = new XMLParserPoolImpl(true);
    private static Map<Object, Object> options = null;
    static {
        options = new HashMap<Object, Object>();
        options.put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_USE_PARSER_POOL, xmlParserPool);
        HashMap<String, Boolean> parserFeatures = new HashMap<String, Boolean>();
        parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        options.put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
                
        Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        extensionToFactoryMap.put("wsdl", new XSDResourceFactoryImpl());
        extensionToFactoryMap.put("xsd", new XSDResourceFactoryImpl());
        extensionToFactoryMap.put("xml", new XMLResourceFactoryImpl());
        extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new GenericXMLResourceFactoryImpl());
    }
    
    static {
        if (!EPackage.Registry.INSTANCE.containsKey(TestModelPackage.eINSTANCE.getNsURI())) {
            EPackage.Registry.INSTANCE.put(TestModelPackage.eINSTANCE.getNsURI(), TestModelPackage.eINSTANCE);
        }
        if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eINSTANCE.getNsURI())) {
            EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
        }
        if (!EPackage.Registry.INSTANCE.containsKey(XMLTypePackage.eINSTANCE.getNsURI())) {
            EPackage.Registry.INSTANCE.put(XMLTypePackage.eINSTANCE.getNsURI(), XMLTypePackage.eINSTANCE);
        }
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
    }
    
    private EObject createTestObject(int objectSize) {
        EClass testObject = EcoreFactory.eINSTANCE.createEClass();
        testObject.setName("ASomeWhatLongButAcceptableName");
        for (int i = 0; i < objectSize; i++) {
            EAttribute testAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            testAttribute.setName("attributeName");
            testObject.getEStructuralFeatures().add(testAttribute);
        }
        return testObject;
    }
    
    private static class Result {
        private double time;
        private int byteSize;
        public Result(double time, int byteSize) {
            super();
            this.time = time;
            this.byteSize = byteSize;
        }        
    }
    
    public Result performMeasure(int size, int objectSize) {
        ResourceSet rs = new ResourceSetImpl();
        Resource resource = rs.createResource(URI.createURI("write.ecore"));
            
        EObject testObject = createTestObject(objectSize);
        EClass latest = null;
        for (int i = 0; i < size; i++) {
            if (isContainment) {
                resource.getContents().add(EcoreUtil.copy(testObject));
            } else {
                EClass newObject = (EClass)EcoreUtil.copy(testObject);
                if (latest != null) {
                    newObject.getESuperTypes().add(latest);
                }
                resource.getContents().add(newObject);
                latest = newObject;
            }
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            resource.save(baos, options);
        } catch (Exception e) {
            System.err.println("unexpected exception");
        }
        byte[] xmi = baos.toByteArray();
        
        System.gc();
        long start = System.currentTimeMillis();
        int repetitions = Math.max(repetitionSize / size,1);
        for (int i = 0; i < repetitions; i++) {
            resource = rs.createResource(URI.createURI("read" + i + ".ecore"));          
            ByteArrayInputStream bais = new ByteArrayInputStream(xmi);
            try {
                resource.load(bais, options);
            } catch (Exception e) {
                System.err.println("unexpected exception");
            }
            for (@SuppressWarnings("unused") EObject obj: resource.getContents()) {
                // empty
            }
        }
        long stop = System.currentTimeMillis();
        
        return new Result((stop - start) / (1.0 * repetitions), xmi.length);
    }
    
    public void performExperiment() {
    	PrintStream out = null;
        try {
            out = new PrintStream(new File("out.csv"));
            System.out.println(sizes.toString());
            for (int run = 0; run < 23; run++) {
                for (double objectSize: objectSizes) {      
                    for (double size: sizes) {
                        System.gc();
                        Result result = performMeasure((int)size, (int)objectSize);
                        String outString = run + " " + (int)objectSize + " " + (int)size + " " + result.time + " " + result.byteSize;
                        out.println(outString);
                        System.out.println(outString);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IO error: " + e.getMessage());
        } finally {
        	out.close();
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        new XMIPerformance().performExperiment();
    }

}
