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

import de.hub.emffrag.testmodels.reflective.Core.IMethod;

import de.hub.emffrag.testmodels.reflective.DOM.Block;
import de.hub.emffrag.testmodels.reflective.DOM.DOMPackage;
import de.hub.emffrag.testmodels.reflective.DOM.MethodDeclaration;
import de.hub.emffrag.testmodels.reflective.DOM.Name;
import de.hub.emffrag.testmodels.reflective.DOM.SimpleName;
import de.hub.emffrag.testmodels.reflective.DOM.SingleVariableDeclaration;
import de.hub.emffrag.testmodels.reflective.DOM.Type;
import de.hub.emffrag.testmodels.reflective.DOM.TypeParameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getBody <em>Body</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getExtraDimensions <em>Extra Dimensions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getConstructor <em>Constructor</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getVarargs <em>Varargs</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getThrownExceptions <em>Thrown Exceptions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.impl.MethodDeclarationImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodDeclarationImpl extends BodyDeclarationImpl implements MethodDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.METHOD_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		return (Block)eGet(DOMPackage.Literals.METHOD_DECLARATION__BODY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Block newBody) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__BODY, newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getExtraDimensions() {
		return (Integer)eGet(DOMPackage.Literals.METHOD_DECLARATION__EXTRA_DIMENSIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtraDimensions(Integer newExtraDimensions) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__EXTRA_DIMENSIONS, newExtraDimensions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName getName() {
		return (SimpleName)eGet(DOMPackage.Literals.METHOD_DECLARATION__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(SimpleName newName) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getReturnType() {
		return (Type)eGet(DOMPackage.Literals.METHOD_DECLARATION__RETURN_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(Type newReturnType) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__RETURN_TYPE, newReturnType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getConstructor() {
		return (Boolean)eGet(DOMPackage.Literals.METHOD_DECLARATION__CONSTRUCTOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructor(Boolean newConstructor) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__CONSTRUCTOR, newConstructor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVarargs() {
		return (Boolean)eGet(DOMPackage.Literals.METHOD_DECLARATION__VARARGS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarargs(Boolean newVarargs) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__VARARGS, newVarargs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<SingleVariableDeclaration> getParameters() {
		return (EList<SingleVariableDeclaration>)eGet(DOMPackage.Literals.METHOD_DECLARATION__PARAMETERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Name> getThrownExceptions() {
		return (EList<Name>)eGet(DOMPackage.Literals.METHOD_DECLARATION__THROWN_EXCEPTIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TypeParameter> getTypeParameters() {
		return (EList<TypeParameter>)eGet(DOMPackage.Literals.METHOD_DECLARATION__TYPE_PARAMETERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod getBinding() {
		return (IMethod)eGet(DOMPackage.Literals.METHOD_DECLARATION__BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(IMethod newBinding) {
		eSet(DOMPackage.Literals.METHOD_DECLARATION__BINDING, newBinding);
	}

} //MethodDeclarationImpl
