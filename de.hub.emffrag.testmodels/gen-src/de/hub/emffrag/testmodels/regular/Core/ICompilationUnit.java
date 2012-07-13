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
package de.hub.emffrag.testmodels.regular.Core;

import de.hub.emffrag.testmodels.regular.DOM.CompilationUnit;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ICompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getAllType <em>All Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getImports <em>Imports</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getTypes <em>Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getPrimary <em>Primary</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getAst <em>Ast</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getICompilationUnit()
 * @model
 * @generated
 */
public interface ICompilationUnit extends ITypeRoot {
	/**
	 * Returns the value of the '<em><b>All Type</b></em>' reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.Core.IType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Type</em>' reference list.
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getICompilationUnit_AllType()
	 * @model ordered="false"
	 * @generated
	 */
	EList<IType> getAllType();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.Core.IImportDeclaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getICompilationUnit_Imports()
	 * @model containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<IImportDeclaration> getImports();

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.regular.Core.IType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getICompilationUnit_Types()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<IType> getTypes();

	/**
	 * Returns the value of the '<em><b>Primary</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary</em>' reference.
	 * @see #setPrimary(ICompilationUnit)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getICompilationUnit_Primary()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ICompilationUnit getPrimary();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getPrimary <em>Primary</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary</em>' reference.
	 * @see #getPrimary()
	 * @generated
	 */
	void setPrimary(ICompilationUnit value);

	/**
	 * Returns the value of the '<em><b>Ast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ast</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ast</em>' containment reference.
	 * @see #setAst(CompilationUnit)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getICompilationUnit_Ast()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	CompilationUnit getAst();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.ICompilationUnit#getAst <em>Ast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ast</em>' containment reference.
	 * @see #getAst()
	 * @generated
	 */
	void setAst(CompilationUnit value);

} // ICompilationUnit
