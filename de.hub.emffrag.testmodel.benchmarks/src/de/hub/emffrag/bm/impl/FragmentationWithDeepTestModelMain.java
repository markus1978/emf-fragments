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

import de.hub.emffrag.bm.BenchmarkMain;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;
import de.hub.emffrag.bm.benchmark.util.IConstraint;
import de.hub.emffrag.bm.benchmark.util.builder.BenchmarkBuilders;
import de.hub.emffrag.bm.benchmark.util.builder.MeasurementBuilder;

public class FragmentationWithDeepTestModelMain extends BenchmarkMain {

    @Override
    protected ICandidateFactory createCandidateFactory() {
        return new CandidateFactoryImpl();
    }

    @Override
    public Measurement getMeasurement() {
    	int modelSize = 1000000;
    	int[] fragmentSizes = new int[] { 10, 100, 1000, 10000, 100000 };
    	int[] loadSizes = new int[] { 1, 3, 10, 30, 100, 300, 1000, 3000, 10000, 30000, 100000, 300000, 1000000 };
    	int[] bases = new int[] { 2, 1000};
    	
    	MeasurementBuilder builder = MeasurementBuilder.newMeasurementBuilder().
                withName("results/FragOnMac_22_2").                                   
                withCandidates(EMFFragCandidates.EMFFRAG).
                withRuns(23).
                withConstraints(IConstraint.TRUE);
    	
    	for (int base: bases) {
	    	for (int fragmentSize: fragmentSizes) {    		                          
	            ImportTestModel importBenchmark = BenchmarkBuilders.newImportDeepTestModelBuilder().
	                  withPModelName("testmodel").
	                  withFragmentSize(fragmentSize).
	                  withModelSize(modelSize).
	                  withBase(base).
	                  withRemarks("deep").build();   
	                    	
	        	builder = builder.withBenchmarks(importBenchmark);
	            
	        	for (int loadSize: loadSizes) {
	        	builder = builder.withBenchmarks(BenchmarkBuilders.newLoadFromTestModelBuilder().
	        			withPModelName("testmodel").
	        			withImportBenchmark(importBenchmark).
	        			withLoadSize(loadSize).build());
	        	}       		
	    	}
    	}
    	    	
    	Measurement measurement = builder.build();    	
    	return measurement;
        
    }
    
    public static void main(String[] args) {
    	new FragmentationWithDeepTestModelMain().run(args);
//    	new FragmentationWithDeepTestModelMain().run(new String[] {"0"});
    }
}
