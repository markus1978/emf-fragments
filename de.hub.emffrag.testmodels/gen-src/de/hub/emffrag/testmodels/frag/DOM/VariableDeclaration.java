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
package de.hub.emffrag.testmodels.frag.DOM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.VariableDeclaration#getExtraDimensions <em>Extra Dimensions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.VariableDeclaration#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.VariableDeclaration#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getVariableDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface VariableDeclaration extends ASTNode {
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
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getVariableDeclaration_ExtraDimensions()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.frag.PrimitiveTypes.Integer" required="true" ordered="false"
	 * @generated
	 */
	Integer getExtraDimensions();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.VariableDeclaration#getExtraDimensions <em>Extra Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extra Dimensions</em>' attribute.
	 * @see #getExtraDimensions()
	 * @generated
	 */
	void setExtraDimensions(Integer value);

	/**
	 * Returns the value of the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializer</em>' containment reference.
	 * @see #setInitializer(Expression)
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getVariableDeclaration_Initializer()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getInitializer();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.VariableDeclaration#getInitializer <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initializer</em>' containment reference.
	 * @see #getInitializer()
	 * @generated
	 */
	void setInitializer(Expression value);

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
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getVariableDeclaration_Name()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.VariableDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

} // VariableDeclaration
