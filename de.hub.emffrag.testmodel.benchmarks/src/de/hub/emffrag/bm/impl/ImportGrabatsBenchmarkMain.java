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
import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;
import de.hub.emffrag.bm.benchmark.util.IConstraint;
import de.hub.emffrag.bm.benchmark.util.builder.BenchmarkBuilders;
import de.hub.emffrag.bm.benchmark.util.builder.MeasurementBuilder;

public class ImportGrabatsBenchmarkMain extends BenchmarkMain {
    
    @Override
    protected ICandidateFactory createCandidateFactory() {
        return new CandidateFactoryImpl();
    }
    
    protected Benchmark createBuilder(boolean frag2, int model) {
        return BenchmarkBuilders.newSaveBenchmarkBuilder().
                withPModelName("testmodel_t3" +( frag2? "_f2":"") + "_" + model).
                withPSourceXmiURI("../de.hub.emffrag.testmodels/models/set" + model + ".xmi").
                withRemarks(frag2? "f2":"f1").build();
    }

    protected String getName() {
    	return "results/FragOnMac_24_1";
    }
    
    @Override
    public Measurement getMeasurement() {
        return MeasurementBuilder.newMeasurementBuilder().
                withName(getName()).
//                withBenchmarks(createBuilder(false, 0)).
//                withBenchmarks(createBuilder(true, 0)).
//                
//                withBenchmarks(createBuilder(false, 1)).
//                withBenchmarks(createBuilder(true, 1)).
//                
//                withBenchmarks(createBuilder(false, 2)).
//                withBenchmarks(createBuilder(true, 2)).
//                
//                withBenchmarks(createBuilder(false, 3)).
//                withBenchmarks(createBuilder(true, 3)).
//                
//                withBenchmarks(createBuilder(false, 4)).
                withBenchmarks(createBuilder(true, 4)).
 
//                withCandidates(EMFFragCandidates.XMI).
                withCandidates(EMFFragCandidates.EMFFRAG).
                withRuns(1).
                withConstraints(new IConstraint() {            
                    @Override
                    public boolean isFulfilled(Benchmark benchmark, EMFFragCandidates candidate) {

                        if (benchmark.getRemarks().equals("f2") && candidate == EMFFragCandidates.EMFFRAG) {
                            return true;
                        } 
                        if (benchmark.getRemarks().equals("f1") && (candidate == EMFFragCandidates.EMFFRAG || candidate == EMFFragCandidates.XMI)) {
                            return true;
                        }
                        return false;
                    }
                }).build();
    }
    
    public static void main(String[] args) {
        new ImportGrabatsBenchmarkMain().run(args);
    }
}
