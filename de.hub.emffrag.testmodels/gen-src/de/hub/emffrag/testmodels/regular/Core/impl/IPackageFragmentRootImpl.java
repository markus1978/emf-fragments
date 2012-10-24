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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.IPackageFragment;
import de.hub.emffrag.testmodels.regular.Core.IPackageFragmentRoot;
import de.hub.emffrag.testmodels.regular.Core.PhysicalElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IPackage Fragment Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IPackageFragmentRootImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IPackageFragmentRootImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IPackageFragmentRootImpl#getPackageFragments <em>Package Fragments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IPackageFragmentRootImpl extends IJavaElementImpl implements IPackageFragmentRoot {
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
	 * The cached value of the '{@link #getPackageFragments() <em>Package Fragments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageFragments()
	 * @generated
	 * @ordered
	 */
	protected EList<IPackageFragment> packageFragments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IPackageFragmentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IPACKAGE_FRAGMENT_ROOT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH, oldPath, path));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY, oldIsReadOnly, isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPackageFragment> getPackageFragments() {
		if (packageFragments == null) {
			packageFragments = new EObjectContainmentWithInverseEList.Resolving<IPackageFragment>(IPackageFragment.class, this, CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS, CorePackage.IPACKAGE_FRAGMENT__PACKAGE_FRAGMENT_ROOT);
		}
		return packageFragments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackageFragments()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return ((InternalEList<?>)getPackageFragments()).basicRemove(otherEnd, msgs);
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				return getPath();
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				return getIsReadOnly();
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return getPackageFragments();
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				setPath((String)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				getPackageFragments().clear();
				getPackageFragments().addAll((Collection<? extends IPackageFragment>)newValue);
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				setIsReadOnly(IS_READ_ONLY_EDEFAULT);
				return;
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				getPackageFragments().clear();
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
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY:
				return IS_READ_ONLY_EDEFAULT == null ? isReadOnly != null : !IS_READ_ONLY_EDEFAULT.equals(isReadOnly);
			case CorePackage.IPACKAGE_FRAGMENT_ROOT__PACKAGE_FRAGMENTS:
				return packageFragments != null && !packageFragments.isEmpty();
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
				case CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH: return CorePackage.PHYSICAL_ELEMENT__PATH;
				case CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY: return CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY;
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
				case CorePackage.PHYSICAL_ELEMENT__PATH: return CorePackage.IPACKAGE_FRAGMENT_ROOT__PATH;
				case CorePackage.PHYSICAL_ELEMENT__IS_READ_ONLY: return CorePackage.IPACKAGE_FRAGMENT_ROOT__IS_READ_ONLY;
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

} //IPackageFragmentRootImpl
