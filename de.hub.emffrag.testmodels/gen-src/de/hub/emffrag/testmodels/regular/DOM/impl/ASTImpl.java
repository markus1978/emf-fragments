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
package de.hub.emffrag.testmodels.regular.DOM.impl;

import de.hub.emffrag.testmodels.regular.DOM.AST;
import de.hub.emffrag.testmodels.regular.DOM.ASTNode;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AST</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ASTImpl#getCompilationUnits <em>Compilation Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASTImpl extends EObjectImpl implements AST {
	/**
	 * The cached value of the '{@link #getCompilationUnits() <em>Compilation Units</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompilationUnits()
	 * @generated
	 * @ordered
	 */
	protected ASTNode compilationUnits;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASTImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.AST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode getCompilationUnits() {
		if (compilationUnits != null && compilationUnits.eIsProxy()) {
			InternalEObject oldCompilationUnits = (InternalEObject)compilationUnits;
			compilationUnits = (ASTNode)eResolveProxy(oldCompilationUnits);
			if (compilationUnits != oldCompilationUnits) {
				InternalEObject newCompilationUnits = (InternalEObject)compilationUnits;
				NotificationChain msgs = oldCompilationUnits.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.AST__COMPILATION_UNITS, null, null);
				if (newCompilationUnits.eInternalContainer() == null) {
					msgs = newCompilationUnits.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.AST__COMPILATION_UNITS, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.AST__COMPILATION_UNITS, oldCompilationUnits, compilationUnits));
			}
		}
		return compilationUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASTNode basicGetCompilationUnits() {
		return compilationUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompilationUnits(ASTNode newCompilationUnits, NotificationChain msgs) {
		ASTNode oldCompilationUnits = compilationUnits;
		compilationUnits = newCompilationUnits;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.AST__COMPILATION_UNITS, oldCompilationUnits, newCompilationUnits);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompilationUnits(ASTNode newCompilationUnits) {
		if (newCompilationUnits != compilationUnits) {
			NotificationChain msgs = null;
			if (compilationUnits != null)
				msgs = ((InternalEObject)compilationUnits).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.AST__COMPILATION_UNITS, null, msgs);
			if (newCompilationUnits != null)
				msgs = ((InternalEObject)newCompilationUnits).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.AST__COMPILATION_UNITS, null, msgs);
			msgs = basicSetCompilationUnits(newCompilationUnits, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.AST__COMPILATION_UNITS, newCompilationUnits, newCompilationUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.AST__COMPILATION_UNITS:
				return basicSetCompilationUnits(null, msgs);
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
			case DOMPackage.AST__COMPILATION_UNITS:
				if (resolve) return getCompilationUnits();
				return basicGetCompilationUnits();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.AST__COMPILATION_UNITS:
				setCompilationUnits((ASTNode)newValue);
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
			case DOMPackage.AST__COMPILATION_UNITS:
				setCompilationUnits((ASTNode)null);
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
			case DOMPackage.AST__COMPILATION_UNITS:
				return compilationUnits != null;
		}
		return super.eIsSet(featureID);
	}

} //ASTImpl
