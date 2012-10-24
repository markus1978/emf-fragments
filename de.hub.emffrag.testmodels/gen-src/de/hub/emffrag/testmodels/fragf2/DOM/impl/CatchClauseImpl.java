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

import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.fragf2.DOM.Block;
import de.hub.emffrag.testmodels.fragf2.DOM.CatchClause;
import de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage;
import de.hub.emffrag.testmodels.fragf2.DOM.SingleVariableDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catch Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.CatchClauseImpl#getBody <em>Body</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.CatchClauseImpl#getException <em>Exception</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CatchClauseImpl extends ASTNodeImpl implements CatchClause {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CatchClauseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.CATCH_CLAUSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		return (Block)eGet(DOMPackage.Literals.CATCH_CLAUSE__BODY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Block newBody) {
		eSet(DOMPackage.Literals.CATCH_CLAUSE__BODY, newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration getException() {
		return (SingleVariableDeclaration)eGet(DOMPackage.Literals.CATCH_CLAUSE__EXCEPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setException(SingleVariableDeclaration newException) {
		eSet(DOMPackage.Literals.CATCH_CLAUSE__EXCEPTION, newException);
	}

} //CatchClauseImpl
