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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.IField;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IField</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IFieldImpl#getConstant <em>Constant</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IFieldImpl#getIsEnumConstant <em>Is Enum Constant</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IFieldImpl#getTypeSignature <em>Type Signature</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IFieldImpl#getIsVolatile <em>Is Volatile</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IFieldImpl#getIsTransient <em>Is Transient</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IFieldImpl extends IMemberImpl implements IField {
	/**
	 * The default value of the '{@link #getConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected String constant = CONSTANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsEnumConstant() <em>Is Enum Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnumConstant()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ENUM_CONSTANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsEnumConstant() <em>Is Enum Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsEnumConstant()
	 * @generated
	 * @ordered
	 */
	protected Boolean isEnumConstant = IS_ENUM_CONSTANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeSignature() <em>Type Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeSignature() <em>Type Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeSignature()
	 * @generated
	 * @ordered
	 */
	protected String typeSignature = TYPE_SIGNATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_VOLATILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected Boolean isVolatile = IS_VOLATILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_TRANSIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsTransient()
	 * @generated
	 * @ordered
	 */
	protected Boolean isTransient = IS_TRANSIENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IFIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConstant() {
		return constant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstant(String newConstant) {
		String oldConstant = constant;
		constant = newConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__CONSTANT, oldConstant, constant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsEnumConstant() {
		return isEnumConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnumConstant(Boolean newIsEnumConstant) {
		Boolean oldIsEnumConstant = isEnumConstant;
		isEnumConstant = newIsEnumConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__IS_ENUM_CONSTANT, oldIsEnumConstant, isEnumConstant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeSignature() {
		return typeSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeSignature(String newTypeSignature) {
		String oldTypeSignature = typeSignature;
		typeSignature = newTypeSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__TYPE_SIGNATURE, oldTypeSignature, typeSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsVolatile() {
		return isVolatile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsVolatile(Boolean newIsVolatile) {
		Boolean oldIsVolatile = isVolatile;
		isVolatile = newIsVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__IS_VOLATILE, oldIsVolatile, isVolatile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsTransient() {
		return isTransient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsTransient(Boolean newIsTransient) {
		Boolean oldIsTransient = isTransient;
		isTransient = newIsTransient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IFIELD__IS_TRANSIENT, oldIsTransient, isTransient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.IFIELD__CONSTANT:
				return getConstant();
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				return getIsEnumConstant();
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				return getTypeSignature();
			case CorePackage.IFIELD__IS_VOLATILE:
				return getIsVolatile();
			case CorePackage.IFIELD__IS_TRANSIENT:
				return getIsTransient();
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
			case CorePackage.IFIELD__CONSTANT:
				setConstant((String)newValue);
				return;
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				setIsEnumConstant((Boolean)newValue);
				return;
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				setTypeSignature((String)newValue);
				return;
			case CorePackage.IFIELD__IS_VOLATILE:
				setIsVolatile((Boolean)newValue);
				return;
			case CorePackage.IFIELD__IS_TRANSIENT:
				setIsTransient((Boolean)newValue);
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
			case CorePackage.IFIELD__CONSTANT:
				setConstant(CONSTANT_EDEFAULT);
				return;
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				setIsEnumConstant(IS_ENUM_CONSTANT_EDEFAULT);
				return;
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				setTypeSignature(TYPE_SIGNATURE_EDEFAULT);
				return;
			case CorePackage.IFIELD__IS_VOLATILE:
				setIsVolatile(IS_VOLATILE_EDEFAULT);
				return;
			case CorePackage.IFIELD__IS_TRANSIENT:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
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
			case CorePackage.IFIELD__CONSTANT:
				return CONSTANT_EDEFAULT == null ? constant != null : !CONSTANT_EDEFAULT.equals(constant);
			case CorePackage.IFIELD__IS_ENUM_CONSTANT:
				return IS_ENUM_CONSTANT_EDEFAULT == null ? isEnumConstant != null : !IS_ENUM_CONSTANT_EDEFAULT.equals(isEnumConstant);
			case CorePackage.IFIELD__TYPE_SIGNATURE:
				return TYPE_SIGNATURE_EDEFAULT == null ? typeSignature != null : !TYPE_SIGNATURE_EDEFAULT.equals(typeSignature);
			case CorePackage.IFIELD__IS_VOLATILE:
				return IS_VOLATILE_EDEFAULT == null ? isVolatile != null : !IS_VOLATILE_EDEFAULT.equals(isVolatile);
			case CorePackage.IFIELD__IS_TRANSIENT:
				return IS_TRANSIENT_EDEFAULT == null ? isTransient != null : !IS_TRANSIENT_EDEFAULT.equals(isTransient);
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
		result.append(" (constant: ");
		result.append(constant);
		result.append(", isEnumConstant: ");
		result.append(isEnumConstant);
		result.append(", typeSignature: ");
		result.append(typeSignature);
		result.append(", isVolatile: ");
		result.append(isVolatile);
		result.append(", isTransient: ");
		result.append(isTransient);
		result.append(')');
		return result.toString();
	}

} //IFieldImpl
