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
package de.hub.emffrag.testmodels.frag.Core.impl;

import de.hub.emffrag.testmodels.frag.Core.CorePackage;
import de.hub.emffrag.testmodels.frag.Core.IJavaModel;
import de.hub.emffrag.testmodels.frag.Core.IJavaProject;
import de.hub.emffrag.testmodels.frag.Core.IPackageFragmentRoot;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.IJavaModelImpl#getJavaProjects <em>Java Projects</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.Core.impl.IJavaModelImpl#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaModelImpl extends PhysicalElementImpl implements IJavaModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IJavaModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IJAVA_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IJavaProject> getJavaProjects() {
		return (EList<IJavaProject>)eGet(CorePackage.Literals.IJAVA_MODEL__JAVA_PROJECTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IPackageFragmentRoot> getExternalPackageFragmentRoots() {
		return (EList<IPackageFragmentRoot>)eGet(CorePackage.Literals.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS, true);
	}

} //IJavaModelImpl
