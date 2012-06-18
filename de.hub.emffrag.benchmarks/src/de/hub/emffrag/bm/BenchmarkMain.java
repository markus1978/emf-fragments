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

import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.impl.MeasurementImpl;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;

public abstract class BenchmarkMain {
    
    public abstract Measurement getMeasurement();
    
    public void run(String[] args) {
        setUp();
        Measurement measurement = getMeasurement();
        if (args.length == 1) {
            int run = new Integer(args[0]);
            measurement.run(run);
        } else {
            int totalBenchmarkRuns = measurement.getNumberOfBenchmarks();
            Runtime runtime = Runtime.getRuntime();
            for (int i = 0; i < totalBenchmarkRuns; i++) {
                try {
                    String commandStr = "java -Xmx7000m -classpath " + System.getProperty("java.class.path") + " " + getClass().getCanonicalName() + " " + i;
                    System.out.println(commandStr);
                    final Process process = runtime.exec(commandStr);
////                    new Thread() {
////                        @Override
////                        public void run() {
////                            while (true) {
////                                int size = 0;
////                                byte[] buffer = new byte[1024];
////                                try {
////                                    while ((size = process.getErrorStream().read(buffer)) != -1) 
////                                        System.out.write(buffer, 0, size);
////                                    wait(100);
////                                } catch (Exception e) {
////                                    
////                                }                               
////                            }
////                        }
////                    }.start();
                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                    } finally {
                        process.destroy();
                    }
                    if (process.exitValue() != 0) {
                        System.out.println("error in JVM for a measurement run (" + i + ")");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("could not create JVM for a measurement run (" + i + ")");
                }
            }
        }
    }
    
    protected abstract ICandidateFactory createCandidateFactory();

    private void setUp() {
        MeasurementImpl.CANDIDATE_FACTORY = createCandidateFactory();
        if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eINSTANCE.getNsURI())) {
            EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
        }
        if (!EPackage.Registry.INSTANCE.containsKey(XMLTypePackage.eINSTANCE.getNsURI())) {
            EPackage.Registry.INSTANCE.put(XMLTypePackage.eINSTANCE.getNsURI(), XMLTypePackage.eINSTANCE);
        }
        Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("hbase", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());               
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }
}
