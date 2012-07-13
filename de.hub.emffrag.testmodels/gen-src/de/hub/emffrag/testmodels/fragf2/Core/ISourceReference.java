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
package de.hub.emffrag.testmodels.fragf2.Core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ISource Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.ISourceReference#getSource <em>Source</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.ISourceReference#getSourceRange <em>Source Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getISourceReference()
 * @model abstract="true"
 * @generated
 */
public interface ISourceReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getISourceReference_Source()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.fragf2.PrimitiveTypes.String" required="true" ordered="false"
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.fragf2.Core.ISourceReference#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

	/**
	 * Returns the value of the '<em><b>Source Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Range</em>' containment reference.
	 * @see #setSourceRange(ISourceRange)
	 * @see de.hub.emffrag.testmodels.fragf2.Core.CorePackage#getISourceReference_SourceRange()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	ISourceRange getSourceRange();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.fragf2.Core.ISourceReference#getSourceRange <em>Source Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Range</em>' containment reference.
	 * @see #getSourceRange()
	 * @generated
	 */
	void setSourceRange(ISourceRange value);

} // ISourceReference
