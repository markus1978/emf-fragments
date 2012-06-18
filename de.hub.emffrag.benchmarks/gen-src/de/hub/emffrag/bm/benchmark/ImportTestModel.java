/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Test Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.ImportTestModel#getFragmentSize <em>Fragment Size</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.ImportTestModel#getModelSize <em>Model Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getImportTestModel()
 * @model
 * @generated
 */
public interface ImportTestModel extends Benchmark {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

	/**
	 * Returns the value of the '<em><b>Fragment Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragment Size</em>' attribute.
	 * @see #setFragmentSize(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getImportTestModel_FragmentSize()
	 * @model
	 * @generated
	 */
	int getFragmentSize();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.ImportTestModel#getFragmentSize <em>Fragment Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fragment Size</em>' attribute.
	 * @see #getFragmentSize()
	 * @generated
	 */
	void setFragmentSize(int value);

	/**
	 * Returns the value of the '<em><b>Model Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Size</em>' attribute.
	 * @see #setModelSize(int)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getImportTestModel_ModelSize()
	 * @model
	 * @generated
	 */
	int getModelSize();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.ImportTestModel#getModelSize <em>Model Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Size</em>' attribute.
	 * @see #getModelSize()
	 * @generated
	 */
	void setModelSize(int value);

} // ImportTestModel
