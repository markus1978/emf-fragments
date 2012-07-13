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
package de.hub.emffrag.testmodels.fragf2.Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IType</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getFullyQualifiedParametrizedName <em>Fully Qualified Parametrized Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getFields <em>Fields</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getMethods <em>Methods</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getTypes <em>Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType()
 * @model
 * @generated
 */
public interface IType extends IMember {
	/**
	 * Returns the value of the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fully Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fully Qualified Name</em>' attribute.
	 * @see #setFullyQualifiedName(String)
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_FullyQualifiedName()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.fragf2.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getFullyQualifiedName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getFullyQualifiedName <em>Fully Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fully Qualified Name</em>' attribute.
	 * @see #getFullyQualifiedName()
	 * @generated
	 */
	void setFullyQualifiedName(String value);

	/**
	 * Returns the value of the '<em><b>Fully Qualified Parametrized Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fully Qualified Parametrized Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fully Qualified Parametrized Name</em>' attribute.
	 * @see #setFullyQualifiedParametrizedName(String)
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_FullyQualifiedParametrizedName()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.fragf2.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getFullyQualifiedParametrizedName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.fragf2.Core.IType#getFullyQualifiedParametrizedName <em>Fully Qualified Parametrized Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fully Qualified Parametrized Name</em>' attribute.
	 * @see #getFullyQualifiedParametrizedName()
	 * @generated
	 */
	void setFullyQualifiedParametrizedName(String value);

	/**
	 * Returns the value of the '<em><b>Initializers</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.fragf2.Core.IInitializer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializers</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_Initializers()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<IInitializer> getInitializers();

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.fragf2.Core.IField}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_Fields()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<IField> getFields();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.fragf2.Core.IMethod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_Methods()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<IMethod> getMethods();

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.fragf2.Core.IType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_Types()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<IType> getTypes();

	/**
	 * Returns the value of the '<em><b>Type Parameters</b></em>' reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.fragf2.Core.ITypeParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Parameters</em>' reference list.
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getIType_TypeParameters()
	 * @model ordered="false"
	 * @generated
	 */
	EList<ITypeParameter> getTypeParameters();

} // IType
