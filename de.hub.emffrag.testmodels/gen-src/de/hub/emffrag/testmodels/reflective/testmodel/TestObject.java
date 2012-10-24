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
package de.hub.emffrag.testmodels.reflective.testmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getRegularContents <em>Regular Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getFragmentedContents <em>Fragmented Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getCrossReferences <em>Cross References</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestModelPackage#getTestObject()
 * @model
 * @generated
 */
public interface TestObject extends EObject {
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
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestModelPackage#getTestObject_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Regular Contents</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regular Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regular Contents</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestModelPackage#getTestObject_RegularContents()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestObject> getRegularContents();

	/**
	 * Returns the value of the '<em><b>Fragmented Contents</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragmented Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragmented Contents</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestModelPackage#getTestObject_FragmentedContents()
	 * @model containment="true"
	 *        annotation="de.hub.emfhbase Fragmentation='true'"
	 * @generated
	 */
	EList<TestObject> getFragmentedContents();

	/**
	 * Returns the value of the '<em><b>Cross References</b></em>' reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cross References</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cross References</em>' reference list.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestModelPackage#getTestObject_CrossReferences()
	 * @model
	 * @generated
	 */
	EList<TestObject> getCrossReferences();

} // TestObject
