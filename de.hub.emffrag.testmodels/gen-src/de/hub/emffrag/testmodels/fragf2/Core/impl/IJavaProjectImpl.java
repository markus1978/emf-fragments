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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.fragf2.Core.CorePackage;
import de.hub.emffrag.testmodels.fragf2.Core.IJavaProject;
import de.hub.emffrag.testmodels.fragf2.Core.IPackageFragmentRoot;
import de.hub.emffrag.testmodels.fragf2.Core.PhysicalElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IJavaProjectImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IJavaProjectImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IJavaProjectImpl#getPackageFragmentRoots <em>Package Fragment Roots</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IJavaProjectImpl#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.Core.impl.IJavaProjectImpl#getRequiredProjects <em>Required Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaProjectImpl extends IJavaElementImpl implements IJavaProject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IJavaProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IJAVA_PROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return (String)eGet(CorePackage.Literals.PHYSICAL_ELEMENT__PATH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		eSet(CorePackage.Literals.PHYSICAL_ELEMENT__PATH, newPath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsReadOnly() {
		return (Boolean)eGet(CorePackage.Literals.PHYSICAL_ELEMENT__IS_READ_ONLY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Boolean newIsReadOnly) {
		eSet(CorePackage.Literals.PHYSICAL_ELEMENT__IS_READ_ONLY, newIsReadOnly);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IPackageFragmentRoot> getPackageFragmentRoots() {
		return (EList<IPackageFragmentRoot>)eGet(CorePackage.Literals.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IPackageFragmentRoot> getExternalPackageFragmentRoots() {
		return (EList<IPackageFragmentRoot>)eGet(CorePackage.Literals.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<IJavaProject> getRequiredProjects() {
		return (EList<IJavaProject>)eGet(CorePackage.Literals.IJAVA_PROJECT__REQUIRED_PROJECTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PhysicalElement.class) {
			switch (derivedFeatureID) {
				case CorePackage.IJAVA_PROJECT__PATH: return CorePackage.PHYSICAL_ELEMENT__PATH;
				case CorePackage.IJAVA_PROJECT__IS_READ_ONLY: return CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PhysicalElement.class) {
			switch (baseFeatureID) {
				case CorePackage.PHYSICAL_ELEMENT__PATH: return CorePackage.IJAVA_PROJECT__PATH;
				case CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY: return CorePackage.IJAVA_PROJECT__IS_READ_ONLY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //IJavaProjectImpl
