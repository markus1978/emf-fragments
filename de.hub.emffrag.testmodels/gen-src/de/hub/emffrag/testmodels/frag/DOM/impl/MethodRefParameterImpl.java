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
package de.hub.emffrag.testmodels.frag.DOM.impl;

import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.frag.DOM.DOMPackage;
import de.hub.emffrag.testmodels.frag.DOM.MethodRefParameter;
import de.hub.emffrag.testmodels.frag.DOM.SimpleName;
import de.hub.emffrag.testmodels.frag.DOM.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Ref Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.MethodRefParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.MethodRefParameterImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.MethodRefParameterImpl#getVarargs <em>Varargs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodRefParameterImpl extends ASTNodeImpl implements MethodRefParameter {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodRefParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_REF_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		return (SimpleName)eGet(DOMPackage.Literals.METHOD_REF_PARAMETER__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(SimpleName newName) {
		eSet(DOMPackage.Literals.METHOD_REF_PARAMETER__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		return (Type)eGet(DOMPackage.Literals.METHOD_REF_PARAMETER__TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		eSet(DOMPackage.Literals.METHOD_REF_PARAMETER__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVarargs() {
		return (Boolean)eGet(DOMPackage.Literals.METHOD_REF_PARAMETER__VARARGS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(Boolean newVarargs) {
		eSet(DOMPackage.Literals.METHOD_REF_PARAMETER__VARARGS, newVarargs);
	}

} //MethodRefParameterImpl
