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

import de.hub.emffrag.testmodels.fragf2.Core.IMethod;
import de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage;
import de.hub.emffrag.testmodels.fragf2.DOM.Expression;
import de.hub.emffrag.testmodels.fragf2.DOM.MethodInvocation;
import de.hub.emffrag.testmodels.fragf2.DOM.SimpleName;
import de.hub.emffrag.testmodels.fragf2.DOM.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.MethodInvocationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.MethodInvocationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.MethodInvocationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.MethodInvocationImpl#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.MethodInvocationImpl#getMethodBinding <em>Method Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodInvocationImpl extends ExpressionImpl implements MethodInvocation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodInvocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_INVOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Expression> getArguments() {
		return (EList<Expression>)eGet(DOMPackage.Literals.METHOD_INVOCATION__ARGUMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return (Expression)eGet(DOMPackage.Literals.METHOD_INVOCATION__EXPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		eSet(DOMPackage.Literals.METHOD_INVOCATION__EXPRESSION, newExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		return (SimpleName)eGet(DOMPackage.Literals.METHOD_INVOCATION__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(SimpleName newName) {
		eSet(DOMPackage.Literals.METHOD_INVOCATION__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Type> getTypeArguments() {
		return (EList<Type>)eGet(DOMPackage.Literals.METHOD_INVOCATION__TYPE_ARGUMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod getMethodBinding() {
		return (IMethod)eGet(DOMPackage.Literals.METHOD_INVOCATION__METHOD_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodBinding(IMethod newMethodBinding) {
		eSet(DOMPackage.Literals.METHOD_INVOCATION__METHOD_BINDING, newMethodBinding);
	}

} //MethodInvocationImpl
