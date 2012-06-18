package de.hub.emffrag.bm.benchmark.util;

import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;

public interface IConstraint {
    
    public static final IConstraint TRUE = new IConstraint() {    
        @Override
        public boolean isFulfilled(Benchmark benchmark, EMFFragCandidates candidate) {
            return true;
        }
    };
    
    public boolean isFulfilled(Benchmark benchmark, EMFFragCandidates candidate);
}
