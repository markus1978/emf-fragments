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
package de.hub.emffrag.testmodels.fragf2.DOM.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage;
import de.hub.emffrag.testmodels.fragf2.DOM.Type;
import de.hub.emffrag.testmodels.fragf2.DOM.TypeDeclaration;
import de.hub.emffrag.testmodels.fragf2.DOM.TypeParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.TypeDeclarationImpl#getSuperclassType <em>Superclass Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.TypeDeclarationImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.TypeDeclarationImpl#getSuperInterfaceTypes <em>Super Interface Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.TypeDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationImpl extends AbstractTypeDeclarationImpl implements TypeDeclaration {
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
		return (Type)eGet(DOMPackage.Literals.TYPE_DECLARATION__SUPERCLASS_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperclassType(Type newSuperclassType) {
		eSet(DOMPackage.Literals.TYPE_DECLARATION__SUPERCLASS_TYPE, newSuperclassType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getInterface() {
		return (Boolean)eGet(DOMPackage.Literals.TYPE_DECLARATION__INTERFACE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(Boolean newInterface) {
		eSet(DOMPackage.Literals.TYPE_DECLARATION__INTERFACE, newInterface);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Type> getSuperInterfaceTypes() {
		return (EList<Type>)eGet(DOMPackage.Literals.TYPE_DECLARATION__SUPER_INTERFACE_TYPES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TypeParameter> getTypeParameters() {
		return (EList<TypeParameter>)eGet(DOMPackage.Literals.TYPE_DECLARATION__TYPE_PARAMETERS, true);
	}

} //TypeDeclarationImpl
