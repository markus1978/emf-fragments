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

import de.hub.emffrag.testmodels.regular.DOM.Assignment;
import de.hub.emffrag.testmodels.regular.DOM.AssignmentOperatorKind;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.Expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AssignmentImpl#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AssignmentImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AssignmentImpl#getRightHandSide <em>Right Hand Side</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentImpl extends ExpressionImpl implements Assignment {
	/**
	 * The cached value of the '{@link #getLeftHandSide() <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftHandSide()
	 * @generated
	 * @ordered
	 */
	protected Expression leftHandSide;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final AssignmentOperatorKind OPERATOR_EDEFAULT = AssignmentOperatorKind.RIGHT_SHIFT_SIGNED_ASSIGN;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected AssignmentOperatorKind operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRightHandSide() <em>Right Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightHandSide()
	 * @generated
	 * @ordered
	 */
	protected Expression rightHandSide;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ASSIGNMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftHandSide() {
		if (leftHandSide != null && leftHandSide.eIsProxy()) {
			InternalEObject oldLeftHandSide = (InternalEObject)leftHandSide;
			leftHandSide = (Expression)eResolveProxy(oldLeftHandSide);
			if (leftHandSide != oldLeftHandSide) {
				InternalEObject newLeftHandSide = (InternalEObject)leftHandSide;
				NotificationChain msgs = oldLeftHandSide.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, null);
				if (newLeftHandSide.eInternalContainer() == null) {
					msgs = newLeftHandSide.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, oldLeftHandSide, leftHandSide));
			}
		}
		return leftHandSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetLeftHandSide() {
		return leftHandSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftHandSide(Expression newLeftHandSide, NotificationChain msgs) {
		Expression oldLeftHandSide = leftHandSide;
		leftHandSide = newLeftHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, oldLeftHandSide, newLeftHandSide);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftHandSide(Expression newLeftHandSide) {
		if (newLeftHandSide != leftHandSide) {
			NotificationChain msgs = null;
			if (leftHandSide != null)
				msgs = ((InternalEObject)leftHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
			if (newLeftHandSide != null)
				msgs = ((InternalEObject)newLeftHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, null, msgs);
			msgs = basicSetLeftHandSide(newLeftHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE, newLeftHandSide, newLeftHandSide));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentOperatorKind getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(AssignmentOperatorKind newOperator) {
		AssignmentOperatorKind oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightHandSide() {
		if (rightHandSide != null && rightHandSide.eIsProxy()) {
			InternalEObject oldRightHandSide = (InternalEObject)rightHandSide;
			rightHandSide = (Expression)eResolveProxy(oldRightHandSide);
			if (rightHandSide != oldRightHandSide) {
				InternalEObject newRightHandSide = (InternalEObject)rightHandSide;
				NotificationChain msgs = oldRightHandSide.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, null);
				if (newRightHandSide.eInternalContainer() == null) {
					msgs = newRightHandSide.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, oldRightHandSide, rightHandSide));
			}
		}
		return rightHandSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetRightHandSide() {
		return rightHandSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightHandSide(Expression newRightHandSide, NotificationChain msgs) {
		Expression oldRightHandSide = rightHandSide;
		rightHandSide = newRightHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, oldRightHandSide, newRightHandSide);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightHandSide(Expression newRightHandSide) {
		if (newRightHandSide != rightHandSide) {
			NotificationChain msgs = null;
			if (rightHandSide != null)
				msgs = ((InternalEObject)rightHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
			if (newRightHandSide != null)
				msgs = ((InternalEObject)newRightHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, null, msgs);
			msgs = basicSetRightHandSide(newRightHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE, newRightHandSide, newRightHandSide));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return basicSetLeftHandSide(null, msgs);
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				return basicSetRightHandSide(null, msgs);
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				if (resolve) return getLeftHandSide();
				return basicGetLeftHandSide();
			case DOMPackage.ASSIGNMENT__OPERATOR:
				return getOperator();
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				if (resolve) return getRightHandSide();
				return basicGetRightHandSide();
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				setLeftHandSide((Expression)newValue);
				return;
			case DOMPackage.ASSIGNMENT__OPERATOR:
				setOperator((AssignmentOperatorKind)newValue);
				return;
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)newValue);
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				setLeftHandSide((Expression)null);
				return;
			case DOMPackage.ASSIGNMENT__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)null);
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
			case DOMPackage.ASSIGNMENT__LEFT_HAND_SIDE:
				return leftHandSide != null;
			case DOMPackage.ASSIGNMENT__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case DOMPackage.ASSIGNMENT__RIGHT_HAND_SIDE:
				return rightHandSide != null;
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
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}

} //AssignmentImpl
