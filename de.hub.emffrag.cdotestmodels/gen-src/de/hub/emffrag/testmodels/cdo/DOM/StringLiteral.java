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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.StringLiteral#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.StringLiteral#getLiteralValue <em>Literal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getStringLiteral()
 * @model
 * @generated
 */
public interface StringLiteral extends Expression {
	/**
	 * Returns the value of the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Escaped Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Escaped Value</em>' attribute.
	 * @see #setEscapedValue(String)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getStringLiteral_EscapedValue()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getEscapedValue();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.StringLiteral#getEscapedValue <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escaped Value</em>' attribute.
	 * @see #getEscapedValue()
	 * @generated
	 */
	void setEscapedValue(String value);

	/**
	 * Returns the value of the '<em><b>Literal Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literal Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literal Value</em>' attribute.
	 * @see #setLiteralValue(String)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getStringLiteral_LiteralValue()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getLiteralValue();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.StringLiteral#getLiteralValue <em>Literal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal Value</em>' attribute.
	 * @see #getLiteralValue()
	 * @generated
	 */
	void setLiteralValue(String value);

} // StringLiteral
