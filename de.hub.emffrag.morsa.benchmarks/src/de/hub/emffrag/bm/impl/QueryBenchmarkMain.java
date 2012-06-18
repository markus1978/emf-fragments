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


import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;
import de.hub.emffrag.bm.benchmark.util.IConstraint;
import de.hub.emffrag.bm.benchmark.util.builder.BenchmarkBuilders;
import de.hub.emffrag.bm.benchmark.util.builder.MeasurementBuilder;

public class QueryBenchmarkMain extends LoadBenchmarkMain {
    
    @Override
    protected ICandidateFactory createCandidateFactory() {
        return new CandidateFactoryImpl();
    }
    
    protected Benchmark createBenchmark(int model, boolean withIndex) {
    	return BenchmarkBuilders.newQueryBenchmarkBuilder().
                withPModelName("testmodel_t2_" + model).
                withPSourceXmiURI("../de.hub.emffrag/models/set" + model + ".xmi").
                withWithIndex(withIndex).
                withRemarks(withIndex ? "index": "noindex").build();
    }

    @Override
    public Measurement getMeasurement() {
        return MeasurementBuilder.newMeasurementBuilder().
                withName("../de.hub.emffrag/results/MorsaOnMac_18_1").
                withBenchmarks(super.createBenchmark(0)).
                withBenchmarks(createBenchmark(0, false)).
                withBenchmarks(createBenchmark(0, true)).
                withBenchmarks(super.createBenchmark(1)).
                withBenchmarks(createBenchmark(1, false)).
                withBenchmarks(createBenchmark(1, true)).
                withBenchmarks(super.createBenchmark(2)).
                withBenchmarks(createBenchmark(2, false)).
                withBenchmarks(createBenchmark(2, true)).
                withBenchmarks(super.createBenchmark(3)).
                withBenchmarks(createBenchmark(3, false)).
                withBenchmarks(createBenchmark(3, true)).
                withBenchmarks(super.createBenchmark(4)).
                withBenchmarks(createBenchmark(4, false)).
                withBenchmarks(createBenchmark(4, true)).

                withCandidates(EMFFragCandidates.MORSA).
                withRuns(23).
                withConstraints(IConstraint.TRUE).build();
    }
    
    public static void main(String[] args) {
        new QueryBenchmarkMain().run(args);
    }
}
