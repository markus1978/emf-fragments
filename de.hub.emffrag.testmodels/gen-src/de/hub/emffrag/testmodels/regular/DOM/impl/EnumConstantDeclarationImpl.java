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

import de.hub.emffrag.testmodels.regular.DOM.AnonymousClassDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.EnumConstantDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.Expression;
import de.hub.emffrag.testmodels.regular.DOM.SimpleName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Constant Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.EnumConstantDeclarationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.EnumConstantDeclarationImpl#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.EnumConstantDeclarationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumConstantDeclarationImpl extends BodyDeclarationImpl implements EnumConstantDeclaration {
	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arguments;

	/**
	 * The cached value of the '{@link #getAnonymousClassDeclaration() <em>Anonymous Class Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnonymousClassDeclaration()
	 * @generated
	 * @ordered
	 */
	protected AnonymousClassDeclaration anonymousClassDeclaration;

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
	protected EnumConstantDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ENUM_CONSTANT_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getArguments() {
		if (arguments == null) {
			arguments = new EObjectContainmentEList.Resolving<Expression>(Expression.class, this, DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS);
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration getAnonymousClassDeclaration() {
		if (anonymousClassDeclaration != null && anonymousClassDeclaration.eIsProxy()) {
			InternalEObject oldAnonymousClassDeclaration = (InternalEObject)anonymousClassDeclaration;
			anonymousClassDeclaration = (AnonymousClassDeclaration)eResolveProxy(oldAnonymousClassDeclaration);
			if (anonymousClassDeclaration != oldAnonymousClassDeclaration) {
				InternalEObject newAnonymousClassDeclaration = (InternalEObject)anonymousClassDeclaration;
				NotificationChain msgs = oldAnonymousClassDeclaration.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, null);
				if (newAnonymousClassDeclaration.eInternalContainer() == null) {
					msgs = newAnonymousClassDeclaration.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, oldAnonymousClassDeclaration, anonymousClassDeclaration));
			}
		}
		return anonymousClassDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration basicGetAnonymousClassDeclaration() {
		return anonymousClassDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration, NotificationChain msgs) {
		AnonymousClassDeclaration oldAnonymousClassDeclaration = anonymousClassDeclaration;
		anonymousClassDeclaration = newAnonymousClassDeclaration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, oldAnonymousClassDeclaration, newAnonymousClassDeclaration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnonymousClassDeclaration(AnonymousClassDeclaration newAnonymousClassDeclaration) {
		if (newAnonymousClassDeclaration != anonymousClassDeclaration) {
			NotificationChain msgs = null;
			if (anonymousClassDeclaration != null)
				msgs = ((InternalEObject)anonymousClassDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
			if (newAnonymousClassDeclaration != null)
				msgs = ((InternalEObject)newAnonymousClassDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, null, msgs);
			msgs = basicSetAnonymousClassDeclaration(newAnonymousClassDeclaration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION, newAnonymousClassDeclaration, newAnonymousClassDeclaration));
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
				NotificationChain msgs = oldName.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, null);
				if (newName.eInternalContainer() == null) {
					msgs = newName.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, oldName, newName);
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
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.ENUM_CONSTANT_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				return basicSetAnonymousClassDeclaration(null, msgs);
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				return getArguments();
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				if (resolve) return getAnonymousClassDeclaration();
				return basicGetAnonymousClassDeclaration();
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)newValue);
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				getArguments().clear();
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				setAnonymousClassDeclaration((AnonymousClassDeclaration)null);
				return;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
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
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
			case DOMPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
				return anonymousClassDeclaration != null;
			case DOMPackage.ENUM_CONSTANT_DECLARATION__NAME:
				return name != null;
		}
		return super.eIsSet(featureID);
	}

} //EnumConstantDeclarationImpl
