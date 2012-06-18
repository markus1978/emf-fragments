/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage
 * @generated
 */
public interface BenchmarkFactory extends EFactory {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";
				/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    BenchmarkFactory eINSTANCE = de.hub.emffrag.bm.benchmark.impl.BenchmarkFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Load Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Load Benchmark</em>'.
	 * @generated
	 */
    LoadBenchmark createLoadBenchmark();

    /**
	 * Returns a new object of class '<em>Save Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Save Benchmark</em>'.
	 * @generated
	 */
    SaveBenchmark createSaveBenchmark();

    /**
	 * Returns a new object of class '<em>Abstract Grabats Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Grabats Benchmark</em>'.
	 * @generated
	 */
    AbstractGrabatsBenchmark createAbstractGrabatsBenchmark();

    /**
	 * Returns a new object of class '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement</em>'.
	 * @generated
	 */
    Measurement createMeasurement();

    /**
	 * Returns a new object of class '<em>Query Benchmark</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Benchmark</em>'.
	 * @generated
	 */
	QueryBenchmark createQueryBenchmark();

				/**
	 * Returns a new object of class '<em>Import Test Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import Test Model</em>'.
	 * @generated
	 */
	ImportTestModel createImportTestModel();

				/**
	 * Returns a new object of class '<em>Load From Test Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Load From Test Model</em>'.
	 * @generated
	 */
	LoadFromTestModel createLoadFromTestModel();

				/**
	 * Returns a new object of class '<em>Import Deep Test Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import Deep Test Model</em>'.
	 * @generated
	 */
	ImportDeepTestModel createImportDeepTestModel();

				/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    BenchmarkPackage getBenchmarkPackage();

} //BenchmarkFactory
