/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Load From Test Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel#getLoadSize <em>Load Size</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel#getImportBenchmark <em>Import Benchmark</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getLoadFromTestModel()
 * @model
 * @generated
 */
public interface LoadFromTestModel extends Benchmark {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

	/**
	 * Returns the value of the '<em><b>Load Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Size</em>' attribute.
	 * @see #setLoadSize(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getLoadFromTestModel_LoadSize()
	 * @model
	 * @generated
	 */
	int getLoadSize();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel#getLoadSize <em>Load Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Size</em>' attribute.
	 * @see #getLoadSize()
	 * @generated
	 */
	void setLoadSize(int value);

	/**
	 * Returns the value of the '<em><b>Import Benchmark</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Benchmark</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Benchmark</em>' reference.
	 * @see #setImportBenchmark(ImportTestModel)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getLoadFromTestModel_ImportBenchmark()
	 * @model
	 * @generated
	 */
	ImportTestModel getImportBenchmark();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel#getImportBenchmark <em>Import Benchmark</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import Benchmark</em>' reference.
	 * @see #getImportBenchmark()
	 * @generated
	 */
	void setImportBenchmark(ImportTestModel value);

} // LoadFromTestModel
