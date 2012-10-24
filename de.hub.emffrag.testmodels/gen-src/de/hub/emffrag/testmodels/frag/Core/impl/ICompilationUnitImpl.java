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
package de.hub.emffrag.testmodels.frag.Core.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.frag.Core.CorePackage;
import de.hub.emffrag.testmodels.frag.Core.ICompilationUnit;
import de.hub.emffrag.testmodels.frag.Core.IImportDeclaration;
import de.hub.emffrag.testmodels.frag.Core.IType;
import de.hub.emffrag.testmodels.frag.DOM.CompilationUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ICompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.ICompilationUnitImpl#getAllType <em>All Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.ICompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.ICompilationUnitImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.ICompilationUnitImpl#getPrimary <em>Primary</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.ICompilationUnitImpl#getAst <em>Ast</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ICompilationUnitImpl extends ITypeRootImpl implements ICompilationUnit {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ICompilationUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ICOMPILATION_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IType> getAllType() {
		return (EList<IType>)eGet(CorePackage.Literals.ICOMPILATION_UNIT__ALL_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IImportDeclaration> getImports() {
		return (EList<IImportDeclaration>)eGet(CorePackage.Literals.ICOMPILATION_UNIT__IMPORTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IType> getTypes() {
		return (EList<IType>)eGet(CorePackage.Literals.ICOMPILATION_UNIT__TYPES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit getPrimary() {
		return (ICompilationUnit)eGet(CorePackage.Literals.ICOMPILATION_UNIT__PRIMARY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimary(ICompilationUnit newPrimary) {
		eSet(CorePackage.Literals.ICOMPILATION_UNIT__PRIMARY, newPrimary);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getAst() {
		return (CompilationUnit)eGet(CorePackage.Literals.ICOMPILATION_UNIT__AST, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAst(CompilationUnit newAst) {
		eSet(CorePackage.Literals.ICOMPILATION_UNIT__AST, newAst);
	}

} //ICompilationUnitImpl
