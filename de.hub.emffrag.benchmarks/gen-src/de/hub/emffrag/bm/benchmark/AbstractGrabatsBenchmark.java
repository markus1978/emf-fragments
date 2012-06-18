/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Grabats Benchmark</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#getPSourceXmiURI <em>PSource Xmi URI</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#isFrag2 <em>Frag2</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getAbstractGrabatsBenchmark()
 * @model
 * @generated
 */
public interface AbstractGrabatsBenchmark extends Benchmark {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * Returns the value of the '<em><b>PSource Xmi URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>PSource Xmi URI</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>PSource Xmi URI</em>' attribute.
	 * @see #setPSourceXmiURI(String)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getAbstractGrabatsBenchmark_PSourceXmiURI()
	 * @model
	 * @generated
	 */
    String getPSourceXmiURI();

    /**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#getPSourceXmiURI <em>PSource Xmi URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PSource Xmi URI</em>' attribute.
	 * @see #getPSourceXmiURI()
	 * @generated
	 */
    void setPSourceXmiURI(String value);

				/**
	 * Returns the value of the '<em><b>Frag2</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frag2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frag2</em>' attribute.
	 * @see #setFrag2(boolean)
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getAbstractGrabatsBenchmark_Frag2()
	 * @model default="false"
	 * @generated
	 */
	boolean isFrag2();

				/**
	 * Sets the value of the '{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#isFrag2 <em>Frag2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frag2</em>' attribute.
	 * @see #isFrag2()
	 * @generated
	 */
	void setFrag2(boolean value);

} // AbstractGrabatsBenchmark
