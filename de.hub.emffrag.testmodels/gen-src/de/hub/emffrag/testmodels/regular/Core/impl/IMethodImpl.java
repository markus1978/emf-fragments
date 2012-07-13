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

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.IMethod;
import de.hub.emffrag.testmodels.regular.Core.Parameter;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMethod</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMethodImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMethodImpl#getIsConstructor <em>Is Constructor</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMethodImpl#getIsMainMethod <em>Is Main Method</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMethodImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMethodImpl#getExceptionTypes <em>Exception Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IMethodImpl extends IMemberImpl implements IMethod {
	/**
	 * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected static final String RETURN_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected String returnType = RETURN_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsConstructor() <em>Is Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsConstructor()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_CONSTRUCTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsConstructor() <em>Is Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsConstructor()
	 * @generated
	 * @ordered
	 */
	protected Boolean isConstructor = IS_CONSTRUCTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsMainMethod() <em>Is Main Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsMainMethod()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_MAIN_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsMainMethod() <em>Is Main Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsMainMethod()
	 * @generated
	 * @ordered
	 */
	protected Boolean isMainMethod = IS_MAIN_METHOD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;

	/**
	 * The cached value of the '{@link #getExceptionTypes() <em>Exception Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> exceptionTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IMETHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(String newReturnType) {
		String oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMETHOD__RETURN_TYPE, oldReturnType, returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsConstructor() {
		return isConstructor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsConstructor(Boolean newIsConstructor) {
		Boolean oldIsConstructor = isConstructor;
		isConstructor = newIsConstructor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMETHOD__IS_CONSTRUCTOR, oldIsConstructor, isConstructor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsMainMethod() {
		return isMainMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMainMethod(Boolean newIsMainMethod) {
		Boolean oldIsMainMethod = isMainMethod;
		isMainMethod = newIsMainMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMETHOD__IS_MAIN_METHOD, oldIsMainMethod, isMainMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList.Resolving<Parameter>(Parameter.class, this, CorePackage.IMETHOD__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getExceptionTypes() {
		if (exceptionTypes == null) {
			exceptionTypes = new EDataTypeEList<String>(String.class, this, CorePackage.IMETHOD__EXCEPTION_TYPES);
		}
		return exceptionTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IMETHOD__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				return getReturnType();
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				return getIsConstructor();
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				return getIsMainMethod();
			case CorePackage.IMETHOD__PARAMETERS:
				return getParameters();
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				return getExceptionTypes();
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				setReturnType((String)newValue);
				return;
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				setIsConstructor((Boolean)newValue);
				return;
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				setIsMainMethod((Boolean)newValue);
				return;
			case CorePackage.IMETHOD__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				getExceptionTypes().clear();
				getExceptionTypes().addAll((Collection<? extends String>)newValue);
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				setReturnType(RETURN_TYPE_EDEFAULT);
				return;
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				setIsConstructor(IS_CONSTRUCTOR_EDEFAULT);
				return;
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				setIsMainMethod(IS_MAIN_METHOD_EDEFAULT);
				return;
			case CorePackage.IMETHOD__PARAMETERS:
				getParameters().clear();
				return;
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				getExceptionTypes().clear();
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
			case CorePackage.IMETHOD__RETURN_TYPE:
				return RETURN_TYPE_EDEFAULT == null ? returnType != null : !RETURN_TYPE_EDEFAULT.equals(returnType);
			case CorePackage.IMETHOD__IS_CONSTRUCTOR:
				return IS_CONSTRUCTOR_EDEFAULT == null ? isConstructor != null : !IS_CONSTRUCTOR_EDEFAULT.equals(isConstructor);
			case CorePackage.IMETHOD__IS_MAIN_METHOD:
				return IS_MAIN_METHOD_EDEFAULT == null ? isMainMethod != null : !IS_MAIN_METHOD_EDEFAULT.equals(isMainMethod);
			case CorePackage.IMETHOD__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case CorePackage.IMETHOD__EXCEPTION_TYPES:
				return exceptionTypes != null && !exceptionTypes.isEmpty();
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
		result.append(" (returnType: ");
		result.append(returnType);
		result.append(", isConstructor: ");
		result.append(isConstructor);
		result.append(", isMainMethod: ");
		result.append(isMainMethod);
		result.append(", exceptionTypes: ");
		result.append(exceptionTypes);
		result.append(')');
		return result.toString();
	}

} //IMethodImpl
