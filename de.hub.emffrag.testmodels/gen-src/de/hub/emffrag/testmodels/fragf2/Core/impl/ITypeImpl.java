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
package de.hub.emffrag.testmodels.fragf2.Core.impl;

import de.hub.emffrag.testmodels.fragf2.Core.CorePackage;
import de.hub.emffrag.testmodels.fragf2.Core.IField;
import de.hub.emffrag.testmodels.fragf2.Core.IInitializer;
import de.hub.emffrag.testmodels.fragf2.Core.IMethod;
import de.hub.emffrag.testmodels.fragf2.Core.IType;
import de.hub.emffrag.testmodels.fragf2.Core.ITypeParameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IType</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getFullyQualifiedName <em>Fully Qualified Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getFullyQualifiedParametrizedName <em>Fully Qualified Parametrized Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.ITypeImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ITypeImpl extends IMemberImpl implements IType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ITypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ITYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullyQualifiedName() {
		return (String)eGet(CorePackage.Literals.ITYPE__FULLY_QUALIFIED_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullyQualifiedName(String newFullyQualifiedName) {
		eSet(CorePackage.Literals.ITYPE__FULLY_QUALIFIED_NAME, newFullyQualifiedName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullyQualifiedParametrizedName() {
		return (String)eGet(CorePackage.Literals.ITYPE__FULLY_QUALIFIED_PARAMETRIZED_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullyQualifiedParametrizedName(String newFullyQualifiedParametrizedName) {
		eSet(CorePackage.Literals.ITYPE__FULLY_QUALIFIED_PARAMETRIZED_NAME, newFullyQualifiedParametrizedName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IInitializer> getInitializers() {
		return (EList<IInitializer>)eGet(CorePackage.Literals.ITYPE__INITIALIZERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IField> getFields() {
		return (EList<IField>)eGet(CorePackage.Literals.ITYPE__FIELDS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IMethod> getMethods() {
		return (EList<IMethod>)eGet(CorePackage.Literals.ITYPE__METHODS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IType> getTypes() {
		return (EList<IType>)eGet(CorePackage.Literals.ITYPE__TYPES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ITypeParameter> getTypeParameters() {
		return (EList<ITypeParameter>)eGet(CorePackage.Literals.ITYPE__TYPE_PARAMETERS, true);
	}

} //ITypeImpl
