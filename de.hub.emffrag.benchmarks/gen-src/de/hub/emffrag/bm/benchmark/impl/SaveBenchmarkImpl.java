/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.impl;

import org.eclipse.emf.ecore.EClass;

import com.google.common.base.Throwables;

import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.SaveBenchmark;
import de.hub.emffrag.bm.impl.AbstractImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Save Benchmark</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SaveBenchmarkImpl extends AbstractGrabatsBenchmarkImpl implements SaveBenchmark {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SaveBenchmarkImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.SAVE_BENCHMARK;
	}
    
    @Override
    public void run(EMFFragCandidates candidate) {
        try {
            AbstractImpl candidateImpl = MeasurementImpl.CANDIDATE_FACTORY.createCandidateImpl(candidate);
            int result = candidateImpl.importModel(this, getPSourceXmiURI(), getPModelName(), new MyGauge());
            setRNumberOfObjects(result);
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }
    
    @Override
    public String toString() {
        return eClass().getName() + " " + getPModelName();
    }

} //SaveBenchmarkImpl
