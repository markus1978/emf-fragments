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
 * A representation of the model object '<em><b>Tag Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TagElement#getFragments <em>Fragments</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TagElement#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.TagElement#getNested <em>Nested</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTagElement()
 * @model
 * @generated
 */
public interface TagElement extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.ASTNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragments</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTagElement_Fragments()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ASTNode> getFragments();

	/**
	 * Returns the value of the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Name</em>' attribute.
	 * @see #setTagName(String)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTagElement_TagName()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getTagName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.TagElement#getTagName <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Name</em>' attribute.
	 * @see #getTagName()
	 * @generated
	 */
	void setTagName(String value);

	/**
	 * Returns the value of the '<em><b>Nested</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested</em>' attribute.
	 * @see #setNested(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getTagElement_Nested()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getNested();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.TagElement#getNested <em>Nested</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nested</em>' attribute.
	 * @see #getNested()
	 * @generated
	 */
	void setNested(Boolean value);

} // TagElement
