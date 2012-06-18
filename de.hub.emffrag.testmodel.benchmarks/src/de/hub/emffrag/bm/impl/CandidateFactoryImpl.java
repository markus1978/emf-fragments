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

import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;

public class CandidateFactoryImpl implements ICandidateFactory {

    @Override
    public AbstractImpl createCandidateImpl(EMFFragCandidates candidate) {
        if (candidate == EMFFragCandidates.XMI) {
            return new XMIImpl();            
        } else if (candidate == EMFFragCandidates.CDO) {
            return new CDOImpl();
        } else if (candidate == EMFFragCandidates.EMFFRAG) {
            return new EMFFragImpl();
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
