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
package de.hub.emffrag.testmodels.reflective.Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMethod</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getIsConstructor <em>Is Constructor</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getIsMainMethod <em>Is Main Method</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getParameters <em>Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getExceptionTypes <em>Exception Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIMethod()
 * @model
 * @generated
 */
public interface IMethod extends IMember {
	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' attribute.
	 * @see #setReturnType(String)
	 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIMethod_ReturnType()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.reflective.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getReturnType();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getReturnType <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' attribute.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(String value);

	/**
	 * Returns the value of the '<em><b>Is Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Constructor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Constructor</em>' attribute.
	 * @see #setIsConstructor(Boolean)
	 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIMethod_IsConstructor()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.reflective.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsConstructor();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getIsConstructor <em>Is Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Constructor</em>' attribute.
	 * @see #getIsConstructor()
	 * @generated
	 */
	void setIsConstructor(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Main Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Main Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Main Method</em>' attribute.
	 * @see #setIsMainMethod(Boolean)
	 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIMethod_IsMainMethod()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.reflective.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsMainMethod();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.Core.IMethod#getIsMainMethod <em>Is Main Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Main Method</em>' attribute.
	 * @see #getIsMainMethod()
	 * @generated
	 */
	void setIsMainMethod(Boolean value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.Core.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIMethod_Parameters()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Exception Types</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Types</em>' attribute list.
	 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIMethod_ExceptionTypes()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.reflective.PrimitiveTypes.String" ordered="false"
	 * @generated
	 */
	EList<String> getExceptionTypes();

} // IMethod
