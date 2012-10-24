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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.testmodels.regular.DOM.AbstractTypeDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.BodyDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.SimpleName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AbstractTypeDeclarationImpl#getBodyDeclarations <em>Body Declarations</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AbstractTypeDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AbstractTypeDeclarationImpl#getLocalTypeDeclaration <em>Local Type Declaration</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AbstractTypeDeclarationImpl#getMemberTypeDeclaration <em>Member Type Declaration</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.AbstractTypeDeclarationImpl#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractTypeDeclarationImpl extends BodyDeclarationImpl implements AbstractTypeDeclaration {
	/**
	 * The cached value of the '{@link #getBodyDeclarations() <em>Body Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodyDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<BodyDeclaration> bodyDeclarations;

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
	 * The default value of the '{@link #getLocalTypeDeclaration() <em>Local Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LOCAL_TYPE_DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalTypeDeclaration() <em>Local Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean localTypeDeclaration = LOCAL_TYPE_DECLARATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMemberTypeDeclaration() <em>Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean MEMBER_TYPE_DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMemberTypeDeclaration() <em>Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean memberTypeDeclaration = MEMBER_TYPE_DECLARATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageMemberTypeDeclaration() <em>Package Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageMemberTypeDeclaration() <em>Package Member Type Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageMemberTypeDeclaration()
	 * @generated
	 * @ordered
	 */
	protected Boolean packageMemberTypeDeclaration = PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTypeDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ABSTRACT_TYPE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BodyDeclaration> getBodyDeclarations() {
		if (bodyDeclarations == null) {
			bodyDeclarations = new EObjectContainmentEList.Resolving<BodyDeclaration>(BodyDeclaration.class, this, DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS);
		}
		return bodyDeclarations;
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
				NotificationChain msgs = oldName.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, null);
				if (newName.eInternalContainer() == null) {
					msgs = newName.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, oldName, newName);
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
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getLocalTypeDeclaration() {
		return localTypeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalTypeDeclaration(Boolean newLocalTypeDeclaration) {
		Boolean oldLocalTypeDeclaration = localTypeDeclaration;
		localTypeDeclaration = newLocalTypeDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION, oldLocalTypeDeclaration, localTypeDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getMemberTypeDeclaration() {
		return memberTypeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemberTypeDeclaration(Boolean newMemberTypeDeclaration) {
		Boolean oldMemberTypeDeclaration = memberTypeDeclaration;
		memberTypeDeclaration = newMemberTypeDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION, oldMemberTypeDeclaration, memberTypeDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPackageMemberTypeDeclaration() {
		return packageMemberTypeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageMemberTypeDeclaration(Boolean newPackageMemberTypeDeclaration) {
		Boolean oldPackageMemberTypeDeclaration = packageMemberTypeDeclaration;
		packageMemberTypeDeclaration = newPackageMemberTypeDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION, oldPackageMemberTypeDeclaration, packageMemberTypeDeclaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return ((InternalEList<?>)getBodyDeclarations()).basicRemove(otherEnd, msgs);
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return getBodyDeclarations();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				if (resolve) return getName();
				return basicGetName();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				return getLocalTypeDeclaration();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				return getMemberTypeDeclaration();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				return getPackageMemberTypeDeclaration();
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				getBodyDeclarations().addAll((Collection<? extends BodyDeclaration>)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				setName((SimpleName)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				setLocalTypeDeclaration((Boolean)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				setMemberTypeDeclaration((Boolean)newValue);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				setPackageMemberTypeDeclaration((Boolean)newValue);
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				getBodyDeclarations().clear();
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				setName((SimpleName)null);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				setLocalTypeDeclaration(LOCAL_TYPE_DECLARATION_EDEFAULT);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				setMemberTypeDeclaration(MEMBER_TYPE_DECLARATION_EDEFAULT);
				return;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				setPackageMemberTypeDeclaration(PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT);
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
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
				return bodyDeclarations != null && !bodyDeclarations.isEmpty();
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__NAME:
				return name != null;
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION:
				return LOCAL_TYPE_DECLARATION_EDEFAULT == null ? localTypeDeclaration != null : !LOCAL_TYPE_DECLARATION_EDEFAULT.equals(localTypeDeclaration);
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION:
				return MEMBER_TYPE_DECLARATION_EDEFAULT == null ? memberTypeDeclaration != null : !MEMBER_TYPE_DECLARATION_EDEFAULT.equals(memberTypeDeclaration);
			case DOMPackage.ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION:
				return PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT == null ? packageMemberTypeDeclaration != null : !PACKAGE_MEMBER_TYPE_DECLARATION_EDEFAULT.equals(packageMemberTypeDeclaration);
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
		result.append(" (localTypeDeclaration: ");
		result.append(localTypeDeclaration);
		result.append(", memberTypeDeclaration: ");
		result.append(memberTypeDeclaration);
		result.append(", packageMemberTypeDeclaration: ");
		result.append(packageMemberTypeDeclaration);
		result.append(')');
		return result.toString();
	}

} //AbstractTypeDeclarationImpl
