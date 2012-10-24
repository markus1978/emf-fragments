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

import de.hub.emffrag.testmodels.regular.Core.IPackageFragment;
import de.hub.emffrag.testmodels.regular.DOM.Annotation;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.Javadoc;
import de.hub.emffrag.testmodels.regular.DOM.Name;
import de.hub.emffrag.testmodels.regular.DOM.PackageDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.PackageDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.PackageDeclarationImpl#getJavadoc <em>Javadoc</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.PackageDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.PackageDeclarationImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageDeclarationImpl extends ASTNodeImpl implements PackageDeclaration {
	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<Annotation> annotations;

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
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Name name;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected IPackageFragment binding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.PACKAGE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Annotation> getAnnotations() {
		if (annotations == null) {
			annotations = new EObjectContainmentEList.Resolving<Annotation>(Annotation.class, this, DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS);
		}
		return annotations;
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
				NotificationChain msgs = oldJavadoc.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, null);
				if (newJavadoc.eInternalContainer() == null) {
					msgs = newJavadoc.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.PACKAGE_DECLARATION__JAVADOC, oldJavadoc, javadoc));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__JAVADOC, oldJavadoc, newJavadoc);
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
				msgs = ((InternalEObject)javadoc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, msgs);
			if (newJavadoc != null)
				msgs = ((InternalEObject)newJavadoc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__JAVADOC, null, msgs);
			msgs = basicSetJavadoc(newJavadoc, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__JAVADOC, newJavadoc, newJavadoc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name getName() {
		if (name != null && name.eIsProxy()) {
			InternalEObject oldName = (InternalEObject)name;
			name = (Name)eResolveProxy(oldName);
			if (name != oldName) {
				InternalEObject newName = (InternalEObject)name;
				NotificationChain msgs = oldName.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__NAME, null, null);
				if (newName.eInternalContainer() == null) {
					msgs = newName.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__NAME, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.PACKAGE_DECLARATION__NAME, oldName, name));
			}
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name basicGetName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Name newName, NotificationChain msgs) {
		Name oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(Name newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.PACKAGE_DECLARATION__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment getBinding() {
		if (binding != null && binding.eIsProxy()) {
			InternalEObject oldBinding = (InternalEObject)binding;
			binding = (IPackageFragment)eResolveProxy(oldBinding);
			if (binding != oldBinding) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.PACKAGE_DECLARATION__BINDING, oldBinding, binding));
			}
		}
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment basicGetBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(IPackageFragment newBinding) {
		IPackageFragment oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.PACKAGE_DECLARATION__BINDING, oldBinding, binding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				return basicSetJavadoc(null, msgs);
			case DOMPackage.PACKAGE_DECLARATION__NAME:
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				return getAnnotations();
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				if (resolve) return getJavadoc();
				return basicGetJavadoc();
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				if (resolve) return getName();
				return basicGetName();
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				if (resolve) return getBinding();
				return basicGetBinding();
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Annotation>)newValue);
				return;
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)newValue);
				return;
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				setName((Name)newValue);
				return;
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				setBinding((IPackageFragment)newValue);
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				setJavadoc((Javadoc)null);
				return;
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				setName((Name)null);
				return;
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				setBinding((IPackageFragment)null);
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
			case DOMPackage.PACKAGE_DECLARATION__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case DOMPackage.PACKAGE_DECLARATION__JAVADOC:
				return javadoc != null;
			case DOMPackage.PACKAGE_DECLARATION__NAME:
				return name != null;
			case DOMPackage.PACKAGE_DECLARATION__BINDING:
				return binding != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageDeclarationImpl
