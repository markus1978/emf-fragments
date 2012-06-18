package de.hub.emffrag.bm.benchmark.util;

import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.impl.AbstractImpl;

public interface ICandidateFactory {

    public AbstractImpl createCandidateImpl(EMFFragCandidates candiate);
}
