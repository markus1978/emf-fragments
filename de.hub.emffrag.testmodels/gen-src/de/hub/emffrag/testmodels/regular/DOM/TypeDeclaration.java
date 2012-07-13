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
package de.hub.emffrag.testmodels.regular.DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration#getSuperclassType <em>Superclass Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration#getInterface <em>Interface</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTypeDeclaration()
 * @model
 * @generated
 */
public interface TypeDeclaration extends AbstractTypeDeclaration {
	/**
	 * Returns the value of the '<em><b>Superclass Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Superclass Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Superclass Type</em>' containment reference.
	 * @see #setSuperclassType(Type)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTypeDeclaration_SuperclassType()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Type getSuperclassType();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration#getSuperclassType <em>Superclass Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Superclass Type</em>' containment reference.
	 * @see #getSuperclassType()
	 * @generated
	 */
	void setSuperclassType(Type value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' attribute.
	 * @see #setInterface(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTypeDeclaration_Interface()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getInterface();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration#getInterface <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' attribute.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(Boolean value);

	/**
	 * Returns the value of the '<em><b>Super Interface Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Interface Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Interface Types</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTypeDeclaration_SuperInterfaceTypes()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Type> getSuperInterfaceTypes();

	/**
	 * Returns the value of the '<em><b>Type Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.TypeParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Parameters</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTypeDeclaration_TypeParameters()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<TypeParameter> getTypeParameters();

} // TypeDeclaration
