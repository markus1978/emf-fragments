/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.util.ICandidateFactory;
import de.hub.emffrag.bm.benchmark.util.IConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl#getCandidates <em>Candidates</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl#getBenchmarks <em>Benchmarks</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl#getRuns <em>Runs</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl#getNumberOfBenchmarks <em>Number Of Benchmarks</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementImpl extends EObjectImpl implements Measurement {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				public static ICandidateFactory CANDIDATE_FACTORY = null;
    
    /**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
    protected static final String NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
    protected String name = NAME_EDEFAULT;

    /**
	 * The cached value of the '{@link #getCandidates() <em>Candidates</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCandidates()
	 * @generated
	 * @ordered
	 */
    protected EList<EMFFragCandidates> candidates;

    /**
	 * The cached value of the '{@link #getBenchmarks() <em>Benchmarks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBenchmarks()
	 * @generated
	 * @ordered
	 */
    protected EList<Benchmark> benchmarks;

    /**
	 * The default value of the '{@link #getRuns() <em>Runs</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRuns()
	 * @generated
	 * @ordered
	 */
    protected static final int RUNS_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getRuns() <em>Runs</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRuns()
	 * @generated
	 * @ordered
	 */
    protected int runs = RUNS_EDEFAULT;

    /**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
    protected EList<IConstraint> constraints;

    /**
	 * The default value of the '{@link #getNumberOfBenchmarks() <em>Number Of Benchmarks</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNumberOfBenchmarks()
	 * @generated
	 * @ordered
	 */
    protected static final int NUMBER_OF_BENCHMARKS_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getNumberOfBenchmarks() <em>Number Of Benchmarks</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNumberOfBenchmarks()
	 * @generated
	 * @ordered
	 */
    protected int numberOfBenchmarks = NUMBER_OF_BENCHMARKS_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected MeasurementImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.MEASUREMENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getName() {
		return name;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.MEASUREMENT__NAME, oldName, name));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<EMFFragCandidates> getCandidates() {
		if (candidates == null) {
			candidates = new EDataTypeUniqueEList<EMFFragCandidates>(EMFFragCandidates.class, this, BenchmarkPackage.MEASUREMENT__CANDIDATES);
		}
		return candidates;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Benchmark> getBenchmarks() {
		if (benchmarks == null) {
			benchmarks = new EObjectContainmentEList<Benchmark>(Benchmark.class, this, BenchmarkPackage.MEASUREMENT__BENCHMARKS);
		}
		return benchmarks;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getRuns() {
		return runs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRuns(int newRuns) {
		int oldRuns = runs;
		runs = newRuns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.MEASUREMENT__RUNS, oldRuns, runs));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<IConstraint> getConstraints() {
		if (constraints == null) {
			constraints = new EDataTypeUniqueEList<IConstraint>(IConstraint.class, this, BenchmarkPackage.MEASUREMENT__CONSTRAINTS);
		}
		return constraints;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int getNumberOfBenchmarks() {
        int result = 0;
        for (Benchmark benchmark: getBenchmarks()) {
            for (EMFFragCandidates candidate: getCandidates()) {
                boolean run = true;
                for (IConstraint contraint: getConstraints()) {
                    run &= contraint.isFulfilled(benchmark, candidate);
                }
                if (run) {
                    result++;
                }
            }
        }
        return result*getRuns();
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNumberOfBenchmarks(int newNumberOfBenchmarks) {
		int oldNumberOfBenchmarks = numberOfBenchmarks;
		numberOfBenchmarks = newNumberOfBenchmarks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.MEASUREMENT__NUMBER_OF_BENCHMARKS, oldNumberOfBenchmarks, numberOfBenchmarks));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void run(int measure) {   
        int result = 0;
        int run = 0;
        while (true) {
            for (Benchmark benchmark: getBenchmarks()) {
                for (EMFFragCandidates candidate: getCandidates()) {
                    boolean doRun = true;
                    for (IConstraint contraint: getConstraints()) {
                        doRun &= contraint.isFulfilled(benchmark, candidate);
                    }               
                    if (doRun) {
                        try {
                            File file = new File(name + "_" + benchmark.eClass().getName() + ".csv");             
                            if (!file.exists()) {
                                PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file, false)));
                                benchmark.printResultHeader(out);
                                file.createNewFile();
                                out.close();
                            }                        
                            
                            if (measure == result) {
                                PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file, true))); 
                                System.out.println("## measure now benchmark " + benchmark+ " for candidate " + candidate.getLiteral() + " for the " + (run + 1) +  "th time.");
                                benchmark.run(candidate);
                                benchmark.printResult(out, candidate, run);
                                out.flush();
                                out.close();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("could not measure " + measure + " for benchmark + " + benchmark + " and candidate " + candidate.getLiteral());
                            return;
                        }
                        result++;
                    }
                }
            }
            run++;
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BenchmarkPackage.MEASUREMENT__BENCHMARKS:
				return ((InternalEList<?>)getBenchmarks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.MEASUREMENT__NAME:
				return getName();
			case BenchmarkPackage.MEASUREMENT__CANDIDATES:
				return getCandidates();
			case BenchmarkPackage.MEASUREMENT__BENCHMARKS:
				return getBenchmarks();
			case BenchmarkPackage.MEASUREMENT__RUNS:
				return getRuns();
			case BenchmarkPackage.MEASUREMENT__CONSTRAINTS:
				return getConstraints();
			case BenchmarkPackage.MEASUREMENT__NUMBER_OF_BENCHMARKS:
				return getNumberOfBenchmarks();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BenchmarkPackage.MEASUREMENT__NAME:
				setName((String)newValue);
				return;
			case BenchmarkPackage.MEASUREMENT__CANDIDATES:
				getCandidates().clear();
				getCandidates().addAll((Collection<? extends EMFFragCandidates>)newValue);
				return;
			case BenchmarkPackage.MEASUREMENT__BENCHMARKS:
				getBenchmarks().clear();
				getBenchmarks().addAll((Collection<? extends Benchmark>)newValue);
				return;
			case BenchmarkPackage.MEASUREMENT__RUNS:
				setRuns((Integer)newValue);
				return;
			case BenchmarkPackage.MEASUREMENT__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection<? extends IConstraint>)newValue);
				return;
			case BenchmarkPackage.MEASUREMENT__NUMBER_OF_BENCHMARKS:
				setNumberOfBenchmarks((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case BenchmarkPackage.MEASUREMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BenchmarkPackage.MEASUREMENT__CANDIDATES:
				getCandidates().clear();
				return;
			case BenchmarkPackage.MEASUREMENT__BENCHMARKS:
				getBenchmarks().clear();
				return;
			case BenchmarkPackage.MEASUREMENT__RUNS:
				setRuns(RUNS_EDEFAULT);
				return;
			case BenchmarkPackage.MEASUREMENT__CONSTRAINTS:
				getConstraints().clear();
				return;
			case BenchmarkPackage.MEASUREMENT__NUMBER_OF_BENCHMARKS:
				setNumberOfBenchmarks(NUMBER_OF_BENCHMARKS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BenchmarkPackage.MEASUREMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BenchmarkPackage.MEASUREMENT__CANDIDATES:
				return candidates != null && !candidates.isEmpty();
			case BenchmarkPackage.MEASUREMENT__BENCHMARKS:
				return benchmarks != null && !benchmarks.isEmpty();
			case BenchmarkPackage.MEASUREMENT__RUNS:
				return runs != RUNS_EDEFAULT;
			case BenchmarkPackage.MEASUREMENT__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case BenchmarkPackage.MEASUREMENT__NUMBER_OF_BENCHMARKS:
				return numberOfBenchmarks != NUMBER_OF_BENCHMARKS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", candidates: ");
		result.append(candidates);
		result.append(", runs: ");
		result.append(runs);
		result.append(", constraints: ");
		result.append(constraints);
		result.append(", numberOfBenchmarks: ");
		result.append(numberOfBenchmarks);
		result.append(')');
		return result.toString();
	}

} //MeasurementImpl
