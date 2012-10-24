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

import de.hub.emffrag.testmodels.regular.DOM.BodyDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.ExtendedModifier;
import de.hub.emffrag.testmodels.regular.DOM.Javadoc;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Body Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.BodyDeclarationImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.BodyDeclarationImpl#getJavadoc <em>Javadoc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BodyDeclarationImpl extends ASTNodeImpl implements BodyDeclaration {
	/**
	 * The cached value of the '{@link #getModifiers() <em>Modifiers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendedModifier> modifiers;

	/**
	 * The cached value of the '{@link #getJavadoc() <em>Javadoc</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavadoc()
	 * @generated
	 * @ordered
	 */
	protected Javadoc javadoc;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BodyDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.BODY_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendedModifier> getModifiers() {
		if (modifiers == null) {
			modifiers = new EObjectContainmentEList.Resolving<ExtendedModifier>(ExtendedModifier.class, this, DOMPackage.BODY_DECLARATION__MODIFIERS);
		}
		return modifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc getJavadoc() {
		if (javadoc != null && javadoc.eIsProxy()) {
			InternalEObject oldJavadoc = (InternalEObject)javadoc;
			javadoc = (Javadoc)eResolveProxy(oldJavadoc);
			if (javadoc != oldJavadoc) {
				InternalEObject newJavadoc = (InternalEObject)javadoc;
				NotificationChain msgs = oldJavadoc.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.BODY_DECLARATION__JAVADOC, null, null);
				if (newJavadoc.eInternalContainer() == null) {
					msgs = newJavadoc.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.BODY_DECLARATION__JAVADOC, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.BODY_DECLARATION__JAVADOC, oldJavadoc, javadoc));
			}
		}
		return javadoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc basicGetJavadoc() {
		return javadoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavadoc(Javadoc newJavadoc, NotificationChain msgs) {
		Javadoc oldJavadoc = javadoc;
		javadoc = newJavadoc;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.BODY_DECLARATION__JAVADOC, oldJavadoc, newJavadoc);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavadoc(Javadoc newJavadoc) {
		if (newJavadoc != javadoc) {
			NotificationChain msgs = null;
			if (javadoc != null)
				msgs = ((InternalEObject)javadoc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.BODY_DECLARATION__JAVADOC, null, msgs);
			if (newJavadoc != null)
				msgs = ((InternalEObject)newJavadoc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.BODY_DECLARATION__JAVADOC, null, msgs);
			msgs = basicSetJavadoc(newJavadoc, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.BODY_DECLARATION__JAVADOC, newJavadoc, newJavadoc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				return ((InternalEList<?>)getModifiers()).basicRemove(otherEnd, msgs);
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				return basicSetJavadoc(null, msgs);
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				return getModifiers();
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				if (resolve) return getJavadoc();
				return basicGetJavadoc();
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends ExtendedModifier>)newValue);
				return;
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)newValue);
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				getModifiers().clear();
				return;
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)null);
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
			case DOMPackage.BODY_DECLARATION__MODIFIERS:
				return modifiers != null && !modifiers.isEmpty();
			case DOMPackage.BODY_DECLARATION__JAVADOC:
				return javadoc != null;
		}
		return super.eIsSet(featureID);
	}

} //BodyDeclarationImpl
