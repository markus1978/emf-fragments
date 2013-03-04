/**
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hub.emffrag.testmodels.frag.testmodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Object With Indexes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes#getIndexedReferences <em>Indexed References</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes#getIndexedContents <em>Indexed Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage#getTestObjectWithIndexes()
 * @model
 * @generated
 */
public interface TestObjectWithIndexes extends TestObject {
	/**
	 * Returns the value of the '<em><b>Indexed References</b></em>' reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.frag.testmodel.TestObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed References</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed References</em>' reference list.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage#getTestObjectWithIndexes_IndexedReferences()
	 * @model annotation="de.hub.emffrag indexes='true'"
	 * @generated
	 */
	EList<TestObject> getIndexedReferences();

	/**
	 * Returns the value of the '<em><b>Indexed Contents</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.frag.testmodel.TestObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed Contents</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage#getTestObjectWithIndexes_IndexedContents()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="de.hub.emffrag indexes='true'"
	 * @generated
	 */
	EList<TestObject> getIndexedContents();

} // TestObjectWithIndexes
