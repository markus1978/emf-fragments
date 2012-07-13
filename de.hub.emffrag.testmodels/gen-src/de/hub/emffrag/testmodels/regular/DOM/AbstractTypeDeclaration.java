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
 * A representation of the model object '<em><b>Abstract Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getLocalTypeDeclaration <em>Local Type Declaration</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getMemberTypeDeclaration <em>Member Type Declaration</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getAbstractTypeDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTypeDeclaration extends BodyDeclaration {
	/**
	 * Returns the value of the '<em><b>Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.BodyDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declarations</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getAbstractTypeDeclaration_BodyDeclarations()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<BodyDeclaration> getBodyDeclarations();

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
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getAbstractTypeDeclaration_Name()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	SimpleName getName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

	/**
	 * Returns the value of the '<em><b>Local Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Type Declaration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Type Declaration</em>' attribute.
	 * @see #setLocalTypeDeclaration(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getAbstractTypeDeclaration_LocalTypeDeclaration()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getLocalTypeDeclaration();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getLocalTypeDeclaration <em>Local Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Type Declaration</em>' attribute.
	 * @see #getLocalTypeDeclaration()
	 * @generated
	 */
	void setLocalTypeDeclaration(Boolean value);

	/**
	 * Returns the value of the '<em><b>Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member Type Declaration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member Type Declaration</em>' attribute.
	 * @see #setMemberTypeDeclaration(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getAbstractTypeDeclaration_MemberTypeDeclaration()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getMemberTypeDeclaration();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getMemberTypeDeclaration <em>Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member Type Declaration</em>' attribute.
	 * @see #getMemberTypeDeclaration()
	 * @generated
	 */
	void setMemberTypeDeclaration(Boolean value);

	/**
	 * Returns the value of the '<em><b>Package Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Member Type Declaration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Member Type Declaration</em>' attribute.
	 * @see #setPackageMemberTypeDeclaration(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getAbstractTypeDeclaration_PackageMemberTypeDeclaration()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getPackageMemberTypeDeclaration();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Member Type Declaration</em>' attribute.
	 * @see #getPackageMemberTypeDeclaration()
	 * @generated
	 */
	void setPackageMemberTypeDeclaration(Boolean value);

} // AbstractTypeDeclaration
