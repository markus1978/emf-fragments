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

import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.Type;
import de.hub.emffrag.testmodels.regular.DOM.TypeDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.TypeParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.TypeDeclarationImpl#getSuperclassType <em>Superclass Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.TypeDeclarationImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.TypeDeclarationImpl#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.TypeDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationImpl extends AbstractTypeDeclarationImpl implements TypeDeclaration {
	/**
	 * The cached value of the '{@link #getSuperclassType() <em>Superclass Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperclassType()
	 * @generated
	 * @ordered
	 */
	protected Type superclassType;

	/**
	 * The default value of the '{@link #getInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected Boolean interface_ = INTERFACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSuperInterfaceTypes() <em>Super Interface Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperInterfaceTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> superInterfaceTypes;

	/**
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeParameter> typeParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.TYPE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getSuperclassType() {
		if (superclassType != null && superclassType.eIsProxy()) {
			InternalEObject oldSuperclassType = (InternalEObject)superclassType;
			superclassType = (Type)eResolveProxy(oldSuperclassType);
			if (superclassType != oldSuperclassType) {
				InternalEObject newSuperclassType = (InternalEObject)superclassType;
				NotificationChain msgs = oldSuperclassType.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, null);
				if (newSuperclassType.eInternalContainer() == null) {
					msgs = newSuperclassType.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, oldSuperclassType, superclassType));
			}
		}
		return superclassType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetSuperclassType() {
		return superclassType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperclassType(Type newSuperclassType, NotificationChain msgs) {
		Type oldSuperclassType = superclassType;
		superclassType = newSuperclassType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, oldSuperclassType, newSuperclassType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperclassType(Type newSuperclassType) {
		if (newSuperclassType != superclassType) {
			NotificationChain msgs = null;
			if (superclassType != null)
				msgs = ((InternalEObject)superclassType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, msgs);
			if (newSuperclassType != null)
				msgs = ((InternalEObject)newSuperclassType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, null, msgs);
			msgs = basicSetSuperclassType(newSuperclassType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE, newSuperclassType, newSuperclassType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(Boolean newInterface) {
		Boolean oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.TYPE_DECLARATION__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getSuperInterfaceTypes() {
		if (superInterfaceTypes == null) {
			superInterfaceTypes = new EObjectContainmentEList.Resolving<Type>(Type.class, this, DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES);
		}
		return superInterfaceTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeParameter> getTypeParameters() {
		if (typeParameters == null) {
			typeParameters = new EObjectContainmentEList.Resolving<TypeParameter>(TypeParameter.class, this, DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS);
		}
		return typeParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				return basicSetSuperclassType(null, msgs);
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				return ((InternalEList<?>)getSuperInterfaceTypes()).basicRemove(otherEnd, msgs);
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				if (resolve) return getSuperclassType();
				return basicGetSuperclassType();
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				return getInterface();
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				return getSuperInterfaceTypes();
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return getTypeParameters();
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				setSuperclassType((Type)newValue);
				return;
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				setInterface((Boolean)newValue);
				return;
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				getSuperInterfaceTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				getTypeParameters().clear();
				getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				setSuperclassType((Type)null);
				return;
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				return;
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				getTypeParameters().clear();
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
			case DOMPackage.TYPE_DECLARATION__SUPERCLASS_TYPE:
				return superclassType != null;
			case DOMPackage.TYPE_DECLARATION__INTERFACE:
				return INTERFACE_EDEFAULT == null ? interface_ != null : !INTERFACE_EDEFAULT.equals(interface_);
			case DOMPackage.TYPE_DECLARATION__SUPER_INTERFACE_TYPES:
				return superInterfaceTypes != null && !superInterfaceTypes.isEmpty();
			case DOMPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return typeParameters != null && !typeParameters.isEmpty();
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
		result.append(" (interface: ");
		result.append(interface_);
		result.append(')');
		return result.toString();
	}

} //TypeDeclarationImpl
