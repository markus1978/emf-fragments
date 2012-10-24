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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.EnumConstantDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.EnumDeclaration;
import de.hub.emffrag.testmodels.regular.DOM.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.EnumDeclarationImpl#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.EnumDeclarationImpl#getEnumConstants <em>Enum Constants</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumDeclarationImpl extends AbstractTypeDeclarationImpl implements EnumDeclaration {
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
	 * The cached value of the '{@link #getEnumConstants() <em>Enum Constants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumConstants()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumConstantDeclaration> enumConstants;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ENUM_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getSuperInterfaceTypes() {
		if (superInterfaceTypes == null) {
			superInterfaceTypes = new EObjectContainmentEList.Resolving<Type>(Type.class, this, DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES);
		}
		return superInterfaceTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnumConstantDeclaration> getEnumConstants() {
		if (enumConstants == null) {
			enumConstants = new EObjectContainmentEList.Resolving<EnumConstantDeclaration>(EnumConstantDeclaration.class, this, DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS);
		}
		return enumConstants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				return ((InternalEList<?>)getSuperInterfaceTypes()).basicRemove(otherEnd, msgs);
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return ((InternalEList<?>)getEnumConstants()).basicRemove(otherEnd, msgs);
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
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				return getSuperInterfaceTypes();
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return getEnumConstants();
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
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				getSuperInterfaceTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				getEnumConstants().clear();
				getEnumConstants().addAll((Collection<? extends EnumConstantDeclaration>)newValue);
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
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				getSuperInterfaceTypes().clear();
				return;
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				getEnumConstants().clear();
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
			case DOMPackage.ENUM_DECLARATION__SUPER_INTERFACE_TYPES:
				return superInterfaceTypes != null && !superInterfaceTypes.isEmpty();
			case DOMPackage.ENUM_DECLARATION__ENUM_CONSTANTS:
				return enumConstants != null && !enumConstants.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EnumDeclarationImpl
