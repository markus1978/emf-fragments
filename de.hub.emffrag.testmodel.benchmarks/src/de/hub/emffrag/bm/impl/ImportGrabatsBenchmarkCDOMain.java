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
import de.hub.emffrag.bm.benchmark.util.builder.BenchmarkBuilders;
import de.hub.emffrag.bm.benchmark.util.builder.MeasurementBuilder;

public class ImportGrabatsBenchmarkCDOMain extends BenchmarkMain {
    
    @Override
    protected ICandidateFactory createCandidateFactory() {
        return new CandidateFactoryImpl();
    }
    
    protected Benchmark createBuilder(int model, boolean incremental) {
        return BenchmarkBuilders.newSaveBenchmarkBuilder().
                withPModelName("testmodel_" + model).
                withPSourceXmiURI("../de.hub.emffrag.testmodels/models/set" + model + (incremental?"":".xmi")).
                withRemarks("CDO").build();
    }

    protected String getName() {
    	return "results/CDOOnMac_23_1";
    }
    
    @Override
    public Measurement getMeasurement() {
        return MeasurementBuilder.newMeasurementBuilder().
                withName(getName()).
                withBenchmarks(createBuilder(4, false)).
                withCandidates(EMFFragCandidates.CDO).
                withRuns(1).build();
    }
    
    public static void main(String[] args) {
        new ImportGrabatsBenchmarkCDOMain().run(new String[] {"0"});
    }
}
