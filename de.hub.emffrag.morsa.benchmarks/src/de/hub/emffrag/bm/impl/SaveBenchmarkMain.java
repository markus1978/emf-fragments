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
import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;
import de.hub.emffrag.bm.benchmark.util.IConstraint;
import de.hub.emffrag.bm.benchmark.util.builder.BenchmarkBuilders;
import de.hub.emffrag.bm.benchmark.util.builder.MeasurementBuilder;

public class SaveBenchmarkMain extends BenchmarkMain {

    @Override
    protected ICandidateFactory createCandidateFactory() {
        return new CandidateFactoryImpl();
    }

    @Override
    public Measurement getMeasurement() {
        return MeasurementBuilder.newMeasurementBuilder().
                withName("../de.hub.emffrag/results/MorsaOnMac_16_5").
                withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
                       withPModelName("testmodel_t2_0").
                       withPSourceXmiURI("../de.hub.emffrag/models/set0.xmi")).
               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
                       withPModelName("testmodel_t2_1").
                       withPSourceXmiURI("../de.hub.emffrag/models/set1.xmi")).
               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
                       withPModelName("testmodel_t2_2").
                       withPSourceXmiURI("../de.hub.emffrag/models/set2.xmi")).
               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
                       withPModelName("testmodel_t2_3").
                       withPSourceXmiURI("../de.hub.emffrag/models/set3.xmi")).
               withBenchmarks(BenchmarkBuilders.newSaveBenchmarkBuilder().
                       withPModelName("testmodel_t2_4").
                       withPSourceXmiURI("../de.hub.emffrag/models/set4.xmi")).
               withCandidates(EMFFragCandidates.MORSA).
                withRuns(1).
                withConstraints(IConstraint.TRUE).build();
    }
    
    public static void main(String[] args) {
        new SaveBenchmarkMain().run(args);
    }
}
