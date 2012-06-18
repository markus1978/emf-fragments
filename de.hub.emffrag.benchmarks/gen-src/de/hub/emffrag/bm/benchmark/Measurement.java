/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.bm.benchmark.util.IConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Measurement#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Measurement#getCandidates <em>Candidates</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Measurement#getBenchmarks <em>Benchmarks</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Measurement#getRuns <em>Runs</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Measurement#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Measurement#getNumberOfBenchmarks <em>Number Of Benchmarks</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement()
 * @model
 * @generated
 */
public interface Measurement extends EObject {
    
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement_Name()
	 * @model
	 * @generated
	 */
    String getName();

    /**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Measurement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
    void setName(String value);

    /**
	 * Returns the value of the '<em><b>Candidates</b></em>' attribute list.
	 * The list contents are of type {@link de.hub.emffrag.bm.benchmark.EMFFragCandidates}.
	 * The literals are from the enumeration {@link de.hub.emffrag.bm.benchmark.EMFFragCandidates}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Candidates</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Candidates</em>' attribute list.
	 * @see de.hub.emffrag.bm.benchmark.EMFFragCandidates
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement_Candidates()
	 * @model
	 * @generated
	 */
    EList<EMFFragCandidates> getCandidates();

    /**
	 * Returns the value of the '<em><b>Benchmarks</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.bm.benchmark.Benchmark}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Benchmarks</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Benchmarks</em>' containment reference list.
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement_Benchmarks()
	 * @model containment="true"
	 * @generated
	 */
    EList<Benchmark> getBenchmarks();

    /**
	 * Returns the value of the '<em><b>Runs</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Runs</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Runs</em>' attribute.
	 * @see #setRuns(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement_Runs()
	 * @model
	 * @generated
	 */
    int getRuns();

    /**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Measurement#getRuns <em>Runs</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runs</em>' attribute.
	 * @see #getRuns()
	 * @generated
	 */
    void setRuns(int value);

    /**
	 * Returns the value of the '<em><b>Constraints</b></em>' attribute list.
	 * The list contents are of type {@link de.hub.emffrag.bm.benchmark.util.IConstraint}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Constraints</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' attribute list.
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement_Constraints()
	 * @model dataType="de.hub.emffrag.bm.benchmark.IConstraint" transient="true"
	 * @generated
	 */
    EList<IConstraint> getConstraints();

    /**
	 * Returns the value of the '<em><b>Number Of Benchmarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Number Of Benchmarks</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Benchmarks</em>' attribute.
	 * @see #setNumberOfBenchmarks(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getMeasurement_NumberOfBenchmarks()
	 * @model derived="true"
	 * @generated
	 */
    int getNumberOfBenchmarks();

    /**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Measurement#getNumberOfBenchmarks <em>Number Of Benchmarks</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Benchmarks</em>' attribute.
	 * @see #getNumberOfBenchmarks()
	 * @generated
	 */
    void setNumberOfBenchmarks(int value);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
    void run(int measure);

} // Measurement
