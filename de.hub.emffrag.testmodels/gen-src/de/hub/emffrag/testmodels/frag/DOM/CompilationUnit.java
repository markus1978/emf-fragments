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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.CompilationUnit#getComments <em>Comments</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.CompilationUnit#getPackage <em>Package</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.CompilationUnit#getImports <em>Imports</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.CompilationUnit#getTypes <em>Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getCompilationUnit()
 * @model
 * @generated
 */
public interface CompilationUnit extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.frag.DOM.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getCompilationUnit_Comments()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Comment> getComments();

	/**
	 * Returns the value of the '<em><b>Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' containment reference.
	 * @see #setPackage(PackageDeclaration)
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getCompilationUnit_Package()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	PackageDeclaration getPackage();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.frag.DOM.CompilationUnit#getPackage <em>Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' containment reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(PackageDeclaration value);

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.frag.DOM.ImportDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getCompilationUnit_Imports()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ImportDeclaration> getImports();

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.frag.DOM.AbstractTypeDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.frag.DOM.DOMPackage#getCompilationUnit_Types()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AbstractTypeDeclaration> getTypes();

} // CompilationUnit
