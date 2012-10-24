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

import de.hub.emffrag.testmodels.fragf2.DOM.BodyDeclaration;
import de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage;
import de.hub.emffrag.testmodels.fragf2.DOM.ExtendedModifier;
import de.hub.emffrag.testmodels.fragf2.DOM.Javadoc;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Body Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.BodyDeclarationImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.BodyDeclarationImpl#getJavadoc <em>Javadoc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BodyDeclarationImpl extends ASTNodeImpl implements BodyDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BodyDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.BODY_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ExtendedModifier> getModifiers() {
		return (EList<ExtendedModifier>)eGet(DOMPackage.Literals.BODY_DECLARATION__MODIFIERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc getJavadoc() {
		return (Javadoc)eGet(DOMPackage.Literals.BODY_DECLARATION__JAVADOC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavadoc(Javadoc newJavadoc) {
		eSet(DOMPackage.Literals.BODY_DECLARATION__JAVADOC, newJavadoc);
	}

} //BodyDeclarationImpl
