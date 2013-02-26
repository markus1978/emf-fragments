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
package de.hub.emffrag.model.emffrag.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.model.emffrag.Root;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.model.emffrag.impl.RootImpl#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RootImpl extends FObjectImpl implements Root {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EmfFragPackage.Literals.ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<EObject> getContents() {
		return (EList<EObject>)eGet(EmfFragPackage.Literals.ROOT__CONTENTS, true);
	}
	
	@Override
	public String toString() {
		FInternalObjectImpl fInternalObject = fInternalObject();
		FragmentedModel fragmentation = fInternalObject.getFragmentation();
		if (fragmentation != null) {
			return "root of " + fragmentation.getURI();
		} else {
			return "unattached root";
		}
	}

} //RootImpl
