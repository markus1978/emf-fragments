/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;

import java.io.PrintStream;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Benchmark</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getRMemoryUsage <em>RMemory Usage</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getRTime <em>RTime</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getRNumberOfObjects <em>RNumber Of Objects</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getWeakUnloads <em>Weak Unloads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getWeakLoads <em>Weak Loads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getLoads <em>Loads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getUnloads <em>Unloads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getMaxActiveFagments <em>Max Active Fagments</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getMaxWeakFragments <em>Max Weak Fragments</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getRemarks <em>Remarks</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.Benchmark#getPModelName <em>PModel Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark()
 * @model abstract="true"
 * @generated
 */
public interface Benchmark extends EObject {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * Returns the value of the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RMemory Usage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RMemory Usage</em>' attribute.
	 * @see #setRMemoryUsage(long)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_RMemoryUsage()
	 * @model
	 * @generated
	 */
	long getRMemoryUsage();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRMemoryUsage <em>RMemory Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RMemory Usage</em>' attribute.
	 * @see #getRMemoryUsage()
	 * @generated
	 */
	void setRMemoryUsage(long value);

	/**
	 * Returns the value of the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RTime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RTime</em>' attribute.
	 * @see #setRTime(long)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_RTime()
	 * @model
	 * @generated
	 */
	long getRTime();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRTime <em>RTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RTime</em>' attribute.
	 * @see #getRTime()
	 * @generated
	 */
	void setRTime(long value);

	/**
	 * Returns the value of the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RNumber Of Objects</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RNumber Of Objects</em>' attribute.
	 * @see #setRNumberOfObjects(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_RNumberOfObjects()
	 * @model
	 * @generated
	 */
	int getRNumberOfObjects();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRNumberOfObjects <em>RNumber Of Objects</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RNumber Of Objects</em>' attribute.
	 * @see #getRNumberOfObjects()
	 * @generated
	 */
	void setRNumberOfObjects(int value);

	/**
	 * Returns the value of the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weak Unloads</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weak Unloads</em>' attribute.
	 * @see #setWeakUnloads(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_WeakUnloads()
	 * @model
	 * @generated
	 */
	int getWeakUnloads();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getWeakUnloads <em>Weak Unloads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weak Unloads</em>' attribute.
	 * @see #getWeakUnloads()
	 * @generated
	 */
	void setWeakUnloads(int value);

	/**
	 * Returns the value of the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weak Loads</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weak Loads</em>' attribute.
	 * @see #setWeakLoads(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_WeakLoads()
	 * @model
	 * @generated
	 */
	int getWeakLoads();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getWeakLoads <em>Weak Loads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weak Loads</em>' attribute.
	 * @see #getWeakLoads()
	 * @generated
	 */
	void setWeakLoads(int value);

	/**
	 * Returns the value of the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loads</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loads</em>' attribute.
	 * @see #setLoads(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_Loads()
	 * @model
	 * @generated
	 */
	int getLoads();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getLoads <em>Loads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loads</em>' attribute.
	 * @see #getLoads()
	 * @generated
	 */
	void setLoads(int value);

	/**
	 * Returns the value of the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unloads</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unloads</em>' attribute.
	 * @see #setUnloads(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_Unloads()
	 * @model
	 * @generated
	 */
	int getUnloads();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getUnloads <em>Unloads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unloads</em>' attribute.
	 * @see #getUnloads()
	 * @generated
	 */
	void setUnloads(int value);

	/**
	 * Returns the value of the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Active Fagments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Active Fagments</em>' attribute.
	 * @see #setMaxActiveFagments(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_MaxActiveFagments()
	 * @model
	 * @generated
	 */
	int getMaxActiveFagments();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getMaxActiveFagments <em>Max Active Fagments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Active Fagments</em>' attribute.
	 * @see #getMaxActiveFagments()
	 * @generated
	 */
	void setMaxActiveFagments(int value);

	/**
	 * Returns the value of the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Weak Fragments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Weak Fragments</em>' attribute.
	 * @see #setMaxWeakFragments(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_MaxWeakFragments()
	 * @model
	 * @generated
	 */
	int getMaxWeakFragments();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getMaxWeakFragments <em>Max Weak Fragments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Weak Fragments</em>' attribute.
	 * @see #getMaxWeakFragments()
	 * @generated
	 */
	void setMaxWeakFragments(int value);

	/**
	 * Returns the value of the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remarks</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remarks</em>' attribute.
	 * @see #setRemarks(String)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_Remarks()
	 * @model
	 * @generated
	 */
	String getRemarks();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRemarks <em>Remarks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remarks</em>' attribute.
	 * @see #getRemarks()
	 * @generated
	 */
	void setRemarks(String value);

				/**
	 * Returns the value of the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PModel Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PModel Name</em>' attribute.
	 * @see #setPModelName(String)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getBenchmark_PModelName()
	 * @model
	 * @generated
	 */
	String getPModelName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.Benchmark#getPModelName <em>PModel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PModel Name</em>' attribute.
	 * @see #getPModelName()
	 * @generated
	 */
	void setPModelName(String value);

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model outDataType="de.hub.emffrag.bm.benchmark.PrintStream"
	 * @generated
	 */
    void printResult(PrintStream out, EMFFragCandidates candidate, int run);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model outDataType="de.hub.emffrag.bm.benchmark.PrintStream"
	 * @generated
	 */
    void printResultHeader(PrintStream out);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
    void run(EMFFragCandidates candidate);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
    void startTimeMeasure();

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
    long getTimeMeasure();

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
    long getMemoryMeasure();

} // Benchmark
