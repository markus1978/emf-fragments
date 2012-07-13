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
 * A representation of the model object '<em><b>For Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.ForStatement#getBody <em>Body</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.ForStatement#getExpression <em>Expression</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.ForStatement#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.ForStatement#getUpdaters <em>Updaters</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getForStatement()
 * @model
 * @generated
 */
public interface ForStatement extends Statement {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Statement)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getForStatement_Body()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Statement getBody();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.ForStatement#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Statement value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getForStatement_Expression()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.ForStatement#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Initializers</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializers</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getForStatement_Initializers()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Expression> getInitializers();

	/**
	 * Returns the value of the '<em><b>Updaters</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Updaters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updaters</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getForStatement_Updaters()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Expression> getUpdaters();

} // ForStatement