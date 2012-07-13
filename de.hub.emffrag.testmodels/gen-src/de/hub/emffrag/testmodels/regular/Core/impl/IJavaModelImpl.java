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
package de.hub.emffrag.testmodels.regular.Core.impl;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.IJavaModel;
import de.hub.emffrag.testmodels.regular.Core.IJavaProject;
import de.hub.emffrag.testmodels.regular.Core.IPackageFragmentRoot;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaModelImpl#getJavaProjects <em>Java Projects</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaModelImpl#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaModelImpl extends PhysicalElementImpl implements IJavaModel {
	/**
	 * The cached value of the '{@link #getJavaProjects() <em>Java Projects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<IJavaProject> javaProjects;

	/**
	 * The cached value of the '{@link #getExternalPackageFragmentRoots() <em>External Package Fragment Roots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackageFragmentRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragmentRoot> externalPackageFragmentRoots;

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
	public EList<IJavaProject> getJavaProjects() {
		if (javaProjects == null) {
			javaProjects = new EObjectContainmentEList.Resolving<IJavaProject>(IJavaProject.class, this, CorePackage.IJAVA_MODEL__JAVA_PROJECTS);
		}
		return javaProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragmentRoot> getExternalPackageFragmentRoots() {
		if (externalPackageFragmentRoots == null) {
			externalPackageFragmentRoots = new EObjectContainmentEList.Resolving<IPackageFragmentRoot>(IPackageFragmentRoot.class, this, CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);
		}
		return externalPackageFragmentRoots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				return ((InternalEList<?>)getJavaProjects()).basicRemove(otherEnd, msgs);
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return ((InternalEList<?>)getExternalPackageFragmentRoots()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				return getJavaProjects();
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return getExternalPackageFragmentRoots();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				getJavaProjects().clear();
				getJavaProjects().addAll((Collection<? extends IJavaProject>)newValue);
				return;
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				getExternalPackageFragmentRoots().addAll((Collection<? extends IPackageFragmentRoot>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				getJavaProjects().clear();
				return;
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.IJAVA_MODEL__JAVA_PROJECTS:
				return javaProjects != null && !javaProjects.isEmpty();
			case CorePackage.IJAVA_MODEL__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return externalPackageFragmentRoots != null && !externalPackageFragmentRoots.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IJavaModelImpl
