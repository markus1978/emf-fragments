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

import de.hub.emffrag.testmodels.frag.Core.IType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.Expression#getResolveBoxing <em>Resolve Boxing</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.Expression#getResolveUnboxing <em>Resolve Unboxing</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.Expression#getTypeBinding <em>Type Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolve Boxing</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Boxing</em>' attribute.
	 * @see #setResolveBoxing(Boolean)
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getExpression_ResolveBoxing()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.frag.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getResolveBoxing();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.Expression#getResolveBoxing <em>Resolve Boxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolve Boxing</em>' attribute.
	 * @see #getResolveBoxing()
	 * @generated
	 */
	void setResolveBoxing(Boolean value);

	/**
	 * Returns the value of the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolve Unboxing</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Unboxing</em>' attribute.
	 * @see #setResolveUnboxing(Boolean)
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getExpression_ResolveUnboxing()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.frag.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getResolveUnboxing();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.Expression#getResolveUnboxing <em>Resolve Unboxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolve Unboxing</em>' attribute.
	 * @see #getResolveUnboxing()
	 * @generated
	 */
	void setResolveUnboxing(Boolean value);

	/**
	 * Returns the value of the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Binding</em>' reference.
	 * @see #setTypeBinding(IType)
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getExpression_TypeBinding()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	IType getTypeBinding();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.Expression#getTypeBinding <em>Type Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Binding</em>' reference.
	 * @see #getTypeBinding()
	 * @generated
	 */
	void setTypeBinding(IType value);

} // Expression
