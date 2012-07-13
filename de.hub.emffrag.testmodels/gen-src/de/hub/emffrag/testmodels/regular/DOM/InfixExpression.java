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
 * A representation of the model object '<em><b>Infix Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getExtendedOperands <em>Extended Operands</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getInfixExpression()
 * @model
 * @generated
 */
public interface InfixExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Extended Operands</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.DOM.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Operands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Operands</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getInfixExpression_ExtendedOperands()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Expression> getExtendedOperands();

	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(Expression)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getInfixExpression_LeftOperand()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getLeftOperand();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link de.hub.emffrag.testmodels.regular.DOM.InfixExpressionOperatorKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see de.hub.emffrag.testmodels.regular.DOM.InfixExpressionOperatorKind
	 * @see #setOperator(InfixExpressionOperatorKind)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getInfixExpression_Operator()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	InfixExpressionOperatorKind getOperator();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see de.hub.emffrag.testmodels.regular.DOM.InfixExpressionOperatorKind
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(InfixExpressionOperatorKind value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(Expression)
	 * @see de.hub.emffrag.testmodels.regular.DOM.DOMPackage#getInfixExpression_RightOperand()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Expression getRightOperand();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.DOM.InfixExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(Expression value);

} // InfixExpression
