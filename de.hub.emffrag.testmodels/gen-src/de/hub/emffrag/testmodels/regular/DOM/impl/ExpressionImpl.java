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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.hub.emffrag.testmodels.regular.Core.IType;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.Expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ExpressionImpl#getResolveBoxing <em>Resolve Boxing</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ExpressionImpl#getResolveUnboxing <em>Resolve Unboxing</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ExpressionImpl#getTypeBinding <em>Type Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExpressionImpl extends ASTNodeImpl implements Expression {
	/**
	 * The default value of the '{@link #getResolveBoxing() <em>Resolve Boxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveBoxing()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean RESOLVE_BOXING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolveBoxing() <em>Resolve Boxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveBoxing()
	 * @generated
	 * @ordered
	 */
	protected Boolean resolveBoxing = RESOLVE_BOXING_EDEFAULT;

	/**
	 * The default value of the '{@link #getResolveUnboxing() <em>Resolve Unboxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveUnboxing()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean RESOLVE_UNBOXING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolveUnboxing() <em>Resolve Unboxing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveUnboxing()
	 * @generated
	 * @ordered
	 */
	protected Boolean resolveUnboxing = RESOLVE_UNBOXING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTypeBinding() <em>Type Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeBinding()
	 * @generated
	 * @ordered
	 */
	protected IType typeBinding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getResolveBoxing() {
		return resolveBoxing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveBoxing(Boolean newResolveBoxing) {
		Boolean oldResolveBoxing = resolveBoxing;
		resolveBoxing = newResolveBoxing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.EXPRESSION__RESOLVE_BOXING, oldResolveBoxing, resolveBoxing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getResolveUnboxing() {
		return resolveUnboxing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveUnboxing(Boolean newResolveUnboxing) {
		Boolean oldResolveUnboxing = resolveUnboxing;
		resolveUnboxing = newResolveUnboxing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.EXPRESSION__RESOLVE_UNBOXING, oldResolveUnboxing, resolveUnboxing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType getTypeBinding() {
		if (typeBinding != null && typeBinding.eIsProxy()) {
			InternalEObject oldTypeBinding = (InternalEObject)typeBinding;
			typeBinding = (IType)eResolveProxy(oldTypeBinding);
			if (typeBinding != oldTypeBinding) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.EXPRESSION__TYPE_BINDING, oldTypeBinding, typeBinding));
			}
		}
		return typeBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType basicGetTypeBinding() {
		return typeBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeBinding(IType newTypeBinding) {
		IType oldTypeBinding = typeBinding;
		typeBinding = newTypeBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.EXPRESSION__TYPE_BINDING, oldTypeBinding, typeBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				return getResolveBoxing();
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				return getResolveUnboxing();
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				if (resolve) return getTypeBinding();
				return basicGetTypeBinding();
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				setResolveBoxing((Boolean)newValue);
				return;
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				setResolveUnboxing((Boolean)newValue);
				return;
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				setTypeBinding((IType)newValue);
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				setResolveBoxing(RESOLVE_BOXING_EDEFAULT);
				return;
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				setResolveUnboxing(RESOLVE_UNBOXING_EDEFAULT);
				return;
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				setTypeBinding((IType)null);
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
			case DOMPackage.EXPRESSION__RESOLVE_BOXING:
				return RESOLVE_BOXING_EDEFAULT == null ? resolveBoxing != null : !RESOLVE_BOXING_EDEFAULT.equals(resolveBoxing);
			case DOMPackage.EXPRESSION__RESOLVE_UNBOXING:
				return RESOLVE_UNBOXING_EDEFAULT == null ? resolveUnboxing != null : !RESOLVE_UNBOXING_EDEFAULT.equals(resolveUnboxing);
			case DOMPackage.EXPRESSION__TYPE_BINDING:
				return typeBinding != null;
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
		result.append(" (resolveBoxing: ");
		result.append(resolveBoxing);
		result.append(", resolveUnboxing: ");
		result.append(resolveUnboxing);
		result.append(')');
		return result.toString();
	}

} //ExpressionImpl
