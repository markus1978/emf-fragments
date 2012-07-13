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
 * A representation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.CharacterLiteral#getCharValue <em>Char Value</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.DOM.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getCharacterLiteral()
 * @model
 * @generated
 */
public interface CharacterLiteral extends Expression {
	/**
	 * Returns the value of the '<em><b>Char Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char Value</em>' attribute.
	 * @see #setCharValue(String)
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getCharacterLiteral_CharValue()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getCharValue();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.CharacterLiteral#getCharValue <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Char Value</em>' attribute.
	 * @see #getCharValue()
	 * @generated
	 */
	void setCharValue(String value);

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
	 * @see de.hub.emffrag.testmodels.cdo.DOM.DOMPackage#getCharacterLiteral_EscapedValue()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getEscapedValue();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.DOM.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escaped Value</em>' attribute.
	 * @see #getEscapedValue()
	 * @generated
	 */
	void setEscapedValue(String value);

} // CharacterLiteral
