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
package de.hub.emffrag.testmodels.frag.Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMember</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.IMember#getJavadocRange <em>Javadoc Range</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.IMember#getNameRange <em>Name Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.frag.Core.CorePackage#getIMember()
 * @model abstract="true"
 * @generated
 */
public interface IMember extends IJavaElement, ISourceReference {
	/**
	 * Returns the value of the '<em><b>Javadoc Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Javadoc Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadoc Range</em>' containment reference.
	 * @see #setJavadocRange(ISourceRange)
	 * @see de.hub.emffrag.testmodels.frag.Core.CorePackage#getIMember_JavadocRange()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	ISourceRange getJavadocRange();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.Core.IMember#getJavadocRange <em>Javadoc Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Javadoc Range</em>' containment reference.
	 * @see #getJavadocRange()
	 * @generated
	 */
	void setJavadocRange(ISourceRange value);

	/**
	 * Returns the value of the '<em><b>Name Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Range</em>' containment reference.
	 * @see #setNameRange(ISourceRange)
	 * @see de.hub.emffrag.testmodels.frag.Core.CorePackage#getIMember_NameRange()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	ISourceRange getNameRange();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.Core.IMember#getNameRange <em>Name Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name Range</em>' containment reference.
	 * @see #getNameRange()
	 * @generated
	 */
	void setNameRange(ISourceRange value);

} // IMember
