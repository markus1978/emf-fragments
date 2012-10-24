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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.Expression;
import de.hub.emffrag.testmodels.regular.DOM.SimpleName;
import de.hub.emffrag.testmodels.regular.DOM.VariableDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.VariableDeclarationImpl#getExtraDimensions <em>Extra Dimensions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.VariableDeclarationImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.VariableDeclarationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class VariableDeclarationImpl extends ASTNodeImpl implements VariableDeclaration {
	/**
	 * The default value of the '{@link #getExtraDimensions() <em>Extra Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final Integer EXTRA_DIMENSIONS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtraDimensions() <em>Extra Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraDimensions()
	 * @generated
	 * @ordered
	 */
	protected Integer extraDimensions = EXTRA_DIMENSIONS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializer()
	 * @generated
	 * @ordered
	 */
	protected Expression initializer;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected SimpleName name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.VARIABLE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getExtraDimensions() {
		return extraDimensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtraDimensions(Integer newExtraDimensions) {
		Integer oldExtraDimensions = extraDimensions;
		extraDimensions = newExtraDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS, oldExtraDimensions, extraDimensions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInitializer() {
		if (initializer != null && initializer.eIsProxy()) {
			InternalEObject oldInitializer = (InternalEObject)initializer;
			initializer = (Expression)eResolveProxy(oldInitializer);
			if (initializer != oldInitializer) {
				InternalEObject newInitializer = (InternalEObject)initializer;
				NotificationChain msgs = oldInitializer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, null);
				if (newInitializer.eInternalContainer() == null) {
					msgs = newInitializer.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.VARIABLE_DECLARATION__INITIALIZER, oldInitializer, initializer));
			}
		}
		return initializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetInitializer() {
		return initializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitializer(Expression newInitializer, NotificationChain msgs) {
		Expression oldInitializer = initializer;
		initializer = newInitializer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__INITIALIZER, oldInitializer, newInitializer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitializer(Expression newInitializer) {
		if (newInitializer != initializer) {
			NotificationChain msgs = null;
			if (initializer != null)
				msgs = ((InternalEObject)initializer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
			if (newInitializer != null)
				msgs = ((InternalEObject)newInitializer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__INITIALIZER, null, msgs);
			msgs = basicSetInitializer(newInitializer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__INITIALIZER, newInitializer, newInitializer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		if (name != null && name.eIsProxy()) {
			InternalEObject oldName = (InternalEObject)name;
			name = (SimpleName)eResolveProxy(oldName);
			if (name != oldName) {
				InternalEObject newName = (InternalEObject)name;
				NotificationChain msgs = oldName.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__NAME, null, null);
				if (newName.eInternalContainer() == null) {
					msgs = newName.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__NAME, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.VARIABLE_DECLARATION__NAME, oldName, name));
			}
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName basicGetName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(SimpleName newName, NotificationChain msgs) {
		SimpleName oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(SimpleName newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.VARIABLE_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.VARIABLE_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				return basicSetInitializer(null, msgs);
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				return basicSetName(null, msgs);
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				return getExtraDimensions();
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				if (resolve) return getInitializer();
				return basicGetInitializer();
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				if (resolve) return getName();
				return basicGetName();
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				setExtraDimensions((Integer)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				setInitializer((Expression)newValue);
				return;
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				setName((SimpleName)newValue);
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				setExtraDimensions(EXTRA_DIMENSIONS_EDEFAULT);
				return;
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				setInitializer((Expression)null);
				return;
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				setName((SimpleName)null);
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
			case DOMPackage.VARIABLE_DECLARATION__EXTRA_DIMENSIONS:
				return EXTRA_DIMENSIONS_EDEFAULT == null ? extraDimensions != null : !EXTRA_DIMENSIONS_EDEFAULT.equals(extraDimensions);
			case DOMPackage.VARIABLE_DECLARATION__INITIALIZER:
				return initializer != null;
			case DOMPackage.VARIABLE_DECLARATION__NAME:
				return name != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (extraDimensions: ");
		result.append(extraDimensions);
		result.append(')');
		return result.toString();
	}

} //VariableDeclarationImpl
