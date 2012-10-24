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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.frag.DOM.ArrayCreation;
import de.hub.emffrag.testmodels.frag.DOM.ArrayInitializer;
import de.hub.emffrag.testmodels.frag.DOM.ArrayType;
import de.hub.emffrag.testmodels.frag.DOM.DOMPackage;
import de.hub.emffrag.testmodels.frag.DOM.Expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ArrayCreationImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ArrayCreationImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ArrayCreationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayCreationImpl extends ExpressionImpl implements ArrayCreation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayCreationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ARRAY_CREATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Expression> getDimensions() {
		return (EList<Expression>)eGet(DOMPackage.Literals.ARRAY_CREATION__DIMENSIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayInitializer getInitializer() {
		return (ArrayInitializer)eGet(DOMPackage.Literals.ARRAY_CREATION__INITIALIZER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitializer(ArrayInitializer newInitializer) {
		eSet(DOMPackage.Literals.ARRAY_CREATION__INITIALIZER, newInitializer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayType getType() {
		return (ArrayType)eGet(DOMPackage.Literals.ARRAY_CREATION__TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ArrayType newType) {
		eSet(DOMPackage.Literals.ARRAY_CREATION__TYPE, newType);
	}

} //ArrayCreationImpl
