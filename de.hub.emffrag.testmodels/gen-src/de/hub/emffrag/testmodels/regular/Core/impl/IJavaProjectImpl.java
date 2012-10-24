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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.IJavaProject;
import de.hub.emffrag.testmodels.regular.Core.IPackageFragmentRoot;
import de.hub.emffrag.testmodels.regular.Core.PhysicalElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaProjectImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaProjectImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaProjectImpl#getPackageFragmentRoots <em>Package Fragment Roots</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaProjectImpl#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IJavaProjectImpl#getRequiredProjects <em>Required Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaProjectImpl extends IJavaElementImpl implements IJavaProject {
	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_READ_ONLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected Boolean isReadOnly = IS_READ_ONLY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPackageFragmentRoots() <em>Package Fragment Roots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageFragmentRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragmentRoot> packageFragmentRoots;

	/**
	 * The cached value of the '{@link #getExternalPackageFragmentRoots() <em>External Package Fragment Roots</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackageFragmentRoots()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragmentRoot> externalPackageFragmentRoots;

	/**
	 * The cached value of the '{@link #getRequiredProjects() <em>Required Projects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<IJavaProject> requiredProjects;

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
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IJAVA_PROJECT__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsReadOnly() {
		return isReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Boolean newIsReadOnly) {
		Boolean oldIsReadOnly = isReadOnly;
		isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IJAVA_PROJECT__IS_READ_ONLY, oldIsReadOnly, isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragmentRoot> getPackageFragmentRoots() {
		if (packageFragmentRoots == null) {
			packageFragmentRoots = new EObjectContainmentEList.Resolving<IPackageFragmentRoot>(IPackageFragmentRoot.class, this, CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS);
		}
		return packageFragmentRoots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragmentRoot> getExternalPackageFragmentRoots() {
		if (externalPackageFragmentRoots == null) {
			externalPackageFragmentRoots = new EObjectResolvingEList<IPackageFragmentRoot>(IPackageFragmentRoot.class, this, CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS);
		}
		return externalPackageFragmentRoots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IJavaProject> getRequiredProjects() {
		if (requiredProjects == null) {
			requiredProjects = new EObjectResolvingEList<IJavaProject>(IJavaProject.class, this, CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS);
		}
		return requiredProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				return ((InternalEList<?>)getPackageFragmentRoots()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IJAVA_PROJECT__PATH:
				return getPath();
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				return getIsReadOnly();
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				return getPackageFragmentRoots();
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return getExternalPackageFragmentRoots();
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				return getRequiredProjects();
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
			case CorePackage.IJAVA_PROJECT__PATH:
				setPath((String)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				getPackageFragmentRoots().clear();
				getPackageFragmentRoots().addAll((Collection<? extends IPackageFragmentRoot>)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				getExternalPackageFragmentRoots().addAll((Collection<? extends IPackageFragmentRoot>)newValue);
				return;
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				getRequiredProjects().clear();
				getRequiredProjects().addAll((Collection<? extends IJavaProject>)newValue);
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
			case CorePackage.IJAVA_PROJECT__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				setIsReadOnly(IS_READ_ONLY_EDEFAULT);
				return;
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				getPackageFragmentRoots().clear();
				return;
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				getExternalPackageFragmentRoots().clear();
				return;
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				getRequiredProjects().clear();
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
			case CorePackage.IJAVA_PROJECT__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case CorePackage.IJAVA_PROJECT__IS_READ_ONLY:
				return IS_READ_ONLY_EDEFAULT == null ? isReadOnly != null : !IS_READ_ONLY_EDEFAULT.equals(isReadOnly);
			case CorePackage.IJAVA_PROJECT__PACKAGE_FRAGMENT_ROOTS:
				return packageFragmentRoots != null && !packageFragmentRoots.isEmpty();
			case CorePackage.IJAVA_PROJECT__EXTERNAL_PACKAGE_FRAGMENT_ROOTS:
				return externalPackageFragmentRoots != null && !externalPackageFragmentRoots.isEmpty();
			case CorePackage.IJAVA_PROJECT__REQUIRED_PROJECTS:
				return requiredProjects != null && !requiredProjects.isEmpty();
		}
		return super.eIsSet(featureID);
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (path: ");
		result.append(path);
		result.append(", isReadOnly: ");
		result.append(isReadOnly);
		result.append(')');
		return result.toString();
	}

} //IJavaProjectImpl
