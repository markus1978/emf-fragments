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
package de.hub.emffrag.testmodels.regular.Core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.ICompilationUnit;
import de.hub.emffrag.testmodels.regular.Core.IImportDeclaration;
import de.hub.emffrag.testmodels.regular.Core.IType;
import de.hub.emffrag.testmodels.regular.DOM.CompilationUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ICompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.ICompilationUnitImpl#getAllType <em>All Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.ICompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.ICompilationUnitImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.ICompilationUnitImpl#getPrimary <em>Primary</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.ICompilationUnitImpl#getAst <em>Ast</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ICompilationUnitImpl extends ITypeRootImpl implements ICompilationUnit {
	/**
	 * The cached value of the '{@link #getAllType() <em>All Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllType()
	 * @generated
	 * @ordered
	 */
	protected EList<IType> allType;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<IImportDeclaration> imports;

	/**
	 * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<IType> types;

	/**
	 * The cached value of the '{@link #getPrimary() <em>Primary</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimary()
	 * @generated
	 * @ordered
	 */
	protected ICompilationUnit primary;

	/**
	 * The cached value of the '{@link #getAst() <em>Ast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAst()
	 * @generated
	 * @ordered
	 */
	protected CompilationUnit ast;

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
	public EList<IType> getAllType() {
		if (allType == null) {
			allType = new EObjectResolvingEList<IType>(IType.class, this, CorePackage.ICOMPILATION_UNIT__ALL_TYPE);
		}
		return allType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IImportDeclaration> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList.Resolving<IImportDeclaration>(IImportDeclaration.class, this, CorePackage.ICOMPILATION_UNIT__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IType> getTypes() {
		if (types == null) {
			types = new EObjectContainmentEList.Resolving<IType>(IType.class, this, CorePackage.ICOMPILATION_UNIT__TYPES);
		}
		return types;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit getPrimary() {
		if (primary != null && primary.eIsProxy()) {
			InternalEObject oldPrimary = (InternalEObject)primary;
			primary = (ICompilationUnit)eResolveProxy(oldPrimary);
			if (primary != oldPrimary) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.ICOMPILATION_UNIT__PRIMARY, oldPrimary, primary));
			}
		}
		return primary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit basicGetPrimary() {
		return primary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimary(ICompilationUnit newPrimary) {
		ICompilationUnit oldPrimary = primary;
		primary = newPrimary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICOMPILATION_UNIT__PRIMARY, oldPrimary, primary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit getAst() {
		if (ast != null && ast.eIsProxy()) {
			InternalEObject oldAst = (InternalEObject)ast;
			ast = (CompilationUnit)eResolveProxy(oldAst);
			if (ast != oldAst) {
				InternalEObject newAst = (InternalEObject)ast;
				NotificationChain msgs = oldAst.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICOMPILATION_UNIT__AST, null, null);
				if (newAst.eInternalContainer() == null) {
					msgs = newAst.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICOMPILATION_UNIT__AST, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.ICOMPILATION_UNIT__AST, oldAst, ast));
			}
		}
		return ast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit basicGetAst() {
		return ast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAst(CompilationUnit newAst, NotificationChain msgs) {
		CompilationUnit oldAst = ast;
		ast = newAst;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.ICOMPILATION_UNIT__AST, oldAst, newAst);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAst(CompilationUnit newAst) {
		if (newAst != ast) {
			NotificationChain msgs = null;
			if (ast != null)
				msgs = ((InternalEObject)ast).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICOMPILATION_UNIT__AST, null, msgs);
			if (newAst != null)
				msgs = ((InternalEObject)newAst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.ICOMPILATION_UNIT__AST, null, msgs);
			msgs = basicSetAst(newAst, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ICOMPILATION_UNIT__AST, newAst, newAst));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
			case CorePackage.ICOMPILATION_UNIT__AST:
				return basicSetAst(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				return getAllType();
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				return getImports();
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				return getTypes();
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				if (resolve) return getPrimary();
				return basicGetPrimary();
			case CorePackage.ICOMPILATION_UNIT__AST:
				if (resolve) return getAst();
				return basicGetAst();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				getAllType().clear();
				getAllType().addAll((Collection<? extends IType>)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends IImportDeclaration>)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				getTypes().clear();
				getTypes().addAll((Collection<? extends IType>)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				setPrimary((ICompilationUnit)newValue);
				return;
			case CorePackage.ICOMPILATION_UNIT__AST:
				setAst((CompilationUnit)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				getAllType().clear();
				return;
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				getImports().clear();
				return;
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				getTypes().clear();
				return;
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				setPrimary((ICompilationUnit)null);
				return;
			case CorePackage.ICOMPILATION_UNIT__AST:
				setAst((CompilationUnit)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.ICOMPILATION_UNIT__ALL_TYPE:
				return allType != null && !allType.isEmpty();
			case CorePackage.ICOMPILATION_UNIT__IMPORTS:
				return imports != null && !imports.isEmpty();
			case CorePackage.ICOMPILATION_UNIT__TYPES:
				return types != null && !types.isEmpty();
			case CorePackage.ICOMPILATION_UNIT__PRIMARY:
				return primary != null;
			case CorePackage.ICOMPILATION_UNIT__AST:
				return ast != null;
		}
		return super.eIsSet(featureID);
	}

} //ICompilationUnitImpl
