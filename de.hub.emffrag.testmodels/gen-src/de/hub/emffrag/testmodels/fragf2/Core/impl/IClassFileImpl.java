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

import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.fragf2.Core.CorePackage;
import de.hub.emffrag.testmodels.fragf2.Core.IClassFile;
import de.hub.emffrag.testmodels.fragf2.Core.IType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IClass File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IClassFileImpl#getIsClass <em>Is Class</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IClassFileImpl#getIsInterface <em>Is Interface</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IClassFileImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IClassFileImpl extends ITypeRootImpl implements IClassFile {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IClassFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ICLASS_FILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsClass() {
		return (Boolean)eGet(CorePackage.Literals.ICLASS_FILE__IS_CLASS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsClass(Boolean newIsClass) {
		eSet(CorePackage.Literals.ICLASS_FILE__IS_CLASS, newIsClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsInterface() {
		return (Boolean)eGet(CorePackage.Literals.ICLASS_FILE__IS_INTERFACE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInterface(Boolean newIsInterface) {
		eSet(CorePackage.Literals.ICLASS_FILE__IS_INTERFACE, newIsInterface);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType getType() {
		return (IType)eGet(CorePackage.Literals.ICLASS_FILE__TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(IType newType) {
		eSet(CorePackage.Literals.ICLASS_FILE__TYPE, newType);
	}

} //IClassFileImpl
