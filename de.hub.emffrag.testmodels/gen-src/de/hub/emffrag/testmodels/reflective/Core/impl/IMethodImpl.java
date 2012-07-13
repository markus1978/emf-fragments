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
package de.hub.emffrag.testmodels.reflective.Core.impl;

import de.hub.emffrag.testmodels.reflective.Core.CorePackage;
import de.hub.emffrag.testmodels.reflective.Core.IMethod;
import de.hub.emffrag.testmodels.reflective.Core.Parameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMethod</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.IMethodImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.IMethodImpl#getIsConstructor <em>Is Constructor</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.IMethodImpl#getIsMainMethod <em>Is Main Method</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.IMethodImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.IMethodImpl#getExceptionTypes <em>Exception Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IMethodImpl extends IMemberImpl implements IMethod {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IMETHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReturnType() {
		return (String)eGet(CorePackage.Literals.IMETHOD__RETURN_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(String newReturnType) {
		eSet(CorePackage.Literals.IMETHOD__RETURN_TYPE, newReturnType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsConstructor() {
		return (Boolean)eGet(CorePackage.Literals.IMETHOD__IS_CONSTRUCTOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsConstructor(Boolean newIsConstructor) {
		eSet(CorePackage.Literals.IMETHOD__IS_CONSTRUCTOR, newIsConstructor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsMainMethod() {
		return (Boolean)eGet(CorePackage.Literals.IMETHOD__IS_MAIN_METHOD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMainMethod(Boolean newIsMainMethod) {
		eSet(CorePackage.Literals.IMETHOD__IS_MAIN_METHOD, newIsMainMethod);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Parameter> getParameters() {
		return (EList<Parameter>)eGet(CorePackage.Literals.IMETHOD__PARAMETERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<String> getExceptionTypes() {
		return (EList<String>)eGet(CorePackage.Literals.IMETHOD__EXCEPTION_TYPES, true);
	}

} //IMethodImpl
