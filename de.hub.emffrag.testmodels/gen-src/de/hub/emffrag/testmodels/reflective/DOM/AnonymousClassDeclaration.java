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
package de.hub.emffrag.testmodels.reflective.DOM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anonymous Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.AnonymousClassDeclaration#getBodyDeclarations <em>Body Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getAnonymousClassDeclaration()
 * @model
 * @generated
 */
public interface AnonymousClassDeclaration extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.DOM.BodyDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declarations</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getAnonymousClassDeclaration_BodyDeclarations()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<BodyDeclaration> getBodyDeclarations();

} // AnonymousClassDeclaration
