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
package de.hub.emffrag.testmodels.reflective.DOM.impl;

import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.reflective.Core.IType;
import de.hub.emffrag.testmodels.reflective.DOM.DOMPackage;
import de.hub.emffrag.testmodels.reflective.DOM.Expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.ExpressionImpl#getResolveBoxing <em>Resolve Boxing</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.ExpressionImpl#getResolveUnboxing <em>Resolve Unboxing</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.ExpressionImpl#getTypeBinding <em>Type Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExpressionImpl extends ASTNodeImpl implements Expression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getResolveBoxing() {
		return (Boolean)eGet(DOMPackage.Literals.EXPRESSION__RESOLVE_BOXING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveBoxing(Boolean newResolveBoxing) {
		eSet(DOMPackage.Literals.EXPRESSION__RESOLVE_BOXING, newResolveBoxing);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getResolveUnboxing() {
		return (Boolean)eGet(DOMPackage.Literals.EXPRESSION__RESOLVE_UNBOXING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveUnboxing(Boolean newResolveUnboxing) {
		eSet(DOMPackage.Literals.EXPRESSION__RESOLVE_UNBOXING, newResolveUnboxing);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType getTypeBinding() {
		return (IType)eGet(DOMPackage.Literals.EXPRESSION__TYPE_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeBinding(IType newTypeBinding) {
		eSet(DOMPackage.Literals.EXPRESSION__TYPE_BINDING, newTypeBinding);
	}

} //ExpressionImpl
