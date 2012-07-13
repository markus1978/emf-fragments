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
package de.hub.emffrag.testmodels.cdo.DOM;

import de.hub.emffrag.testmodels.cdo.Core.IMethod;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getBody <em>Body</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getExtraDimensions <em>Extra Dimensions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getConstructor <em>Constructor</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getVarargs <em>Varargs</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getParameters <em>Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getThrownExceptions <em>Thrown Exceptions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration()
 * @model
 * @generated
 */
public interface MethodDeclaration extends BodyDeclaration {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Block)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_Body()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Block getBody();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Block value);

	/**
	 * Returns the value of the '<em><b>Extra Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extra Dimensions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extra Dimensions</em>' attribute.
	 * @see #setExtraDimensions(Integer)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_ExtraDimensions()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.Integer" required="true" ordered="false"
	 * @generated
	 */
	Integer getExtraDimensions();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getExtraDimensions <em>Extra Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extra Dimensions</em>' attribute.
	 * @see #getExtraDimensions()
	 * @generated
	 */
	void setExtraDimensions(Integer value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(SimpleName)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_Name()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' containment reference.
	 * @see #setReturnType(Type)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_ReturnType()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Type getReturnType();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getReturnType <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' containment reference.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(Type value);

	/**
	 * Returns the value of the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constructor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constructor</em>' attribute.
	 * @see #setConstructor(Boolean)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_Constructor()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getConstructor();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getConstructor <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructor</em>' attribute.
	 * @see #getConstructor()
	 * @generated
	 */
	void setConstructor(Boolean value);

	/**
	 * Returns the value of the '<em><b>Varargs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Varargs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Varargs</em>' attribute.
	 * @see #setVarargs(Boolean)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_Varargs()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getVarargs();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getVarargs <em>Varargs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Varargs</em>' attribute.
	 * @see #getVarargs()
	 * @generated
	 */
	void setVarargs(Boolean value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.DOM.SingleVariableDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<SingleVariableDeclaration> getParameters();

	/**
	 * Returns the value of the '<em><b>Thrown Exceptions</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.DOM.Name}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thrown Exceptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thrown Exceptions</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_ThrownExceptions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Name> getThrownExceptions();

	/**
	 * Returns the value of the '<em><b>Type Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.DOM.TypeParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Parameters</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_TypeParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeParameter> getTypeParameters();

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(IMethod)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getMethodDeclaration_Binding()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	IMethod getBinding();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.MethodDeclaration#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(IMethod value);

} // MethodDeclaration
