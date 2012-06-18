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

public class LoadBenchmarkMain extends BenchmarkMain {
    
    @Override
    protected ICandidateFactory createCandidateFactory() {
        return new CandidateFactoryImpl();
    }

    protected Benchmark createBenchmark(int model) {
    	return BenchmarkBuilders.newLoadBenchmarkBuilder().
                withPModelName("testmodel_t2_" + model).
                withPSourceXmiURI("../de.hub.emffrag/models/set" + model + ".xmi").build();
    }
    
    @Override
    public Measurement getMeasurement() {
        return MeasurementBuilder.newMeasurementBuilder().
                withName("../de.hub.emffrag/results/MorsaOnMac_16_1").
                withBenchmarks(BenchmarkBuilders.newLoadBenchmarkBuilder().
                       withPModelName("testmodel_0").
                       withPSourceXmiURI("../de.hub.emffrag/models/set0.xmi")).
               withBenchmarks(BenchmarkBuilders.newLoadBenchmarkBuilder().
                       withPModelName("testmodel_1").
                       withPSourceXmiURI("models/set1.xmi")).
               withBenchmarks(BenchmarkBuilders.newLoadBenchmarkBuilder().
                       withPModelName("testmodel_2").
                       withPSourceXmiURI("models/set2.xmi")).
//               withBenchmarks(BenchmarkBuilders.newLoadBenchmarkBuilder().
//                       withPModelName("testmodel_3").
//                       withPSourceXmiURI("models/set3.xmi")).
//               withBenchmarks(BenchmarkBuilders.newLoadBenchmarkBuilder().
//                       withPModelName("testmodel_4").
//                       withPSourceXmiURI("models/set4.xmi")).
//               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
//                       withPModelName("testmodel_1").
//                       withPSourceXmiURI("models/set1.xmi")).
//               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
//                       withPModelName("testmodel_2").
//                       withPSourceXmiURI("models/set2.xmi")).
//               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
//                       withPModelName("testmodel_3").
//                       withPSourceXmiURI("models/set3.xmi")).
//               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
//                       withPModelName("testmodel_4").
//                       withPSourceXmiURI("models/set4.xmi")).
//                withCandidates(EMFFragCandidates.XMI).
//                withCandidates(EMFFragCandidates.CDO).
               withCandidates(EMFFragCandidates.MORSA).
//                withCandidates(EMFFragCandidates.EMFFRAG).
                withRuns(3).
                withConstraints(IConstraint.TRUE).build();
    }
    
    public static void main(String[] args) {
        new LoadBenchmarkMain().run(args);
    }
}
