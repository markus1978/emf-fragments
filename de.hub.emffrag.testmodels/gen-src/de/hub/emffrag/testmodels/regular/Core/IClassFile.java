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
package de.hub.emffrag.testmodels.regular.Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IClass File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.IClassFile#getIsClass <em>Is Class</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.IClassFile#getIsInterface <em>Is Interface</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.IClassFile#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIClassFile()
 * @model
 * @generated
 */
public interface IClassFile extends ITypeRoot {
	/**
	 * Returns the value of the '<em><b>Is Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Class</em>' attribute.
	 * @see #setIsClass(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIClassFile_IsClass()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsClass();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.IClassFile#getIsClass <em>Is Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Class</em>' attribute.
	 * @see #getIsClass()
	 * @generated
	 */
	void setIsClass(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Interface</em>' attribute.
	 * @see #setIsInterface(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIClassFile_IsInterface()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsInterface();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.IClassFile#getIsInterface <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Interface</em>' attribute.
	 * @see #getIsInterface()
	 * @generated
	 */
	void setIsInterface(Boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(IType)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIClassFile_Type()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	IType getType();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.IClassFile#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(IType value);

} // IClassFile
