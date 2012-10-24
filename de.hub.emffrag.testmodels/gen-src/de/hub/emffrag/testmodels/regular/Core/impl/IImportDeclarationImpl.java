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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.Core.IImportDeclaration;
import de.hub.emffrag.testmodels.regular.Core.ISourceRange;
import de.hub.emffrag.testmodels.regular.Core.ISourceReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IImport Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IImportDeclarationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IImportDeclarationImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IImportDeclarationImpl#getIsOnDemand <em>Is On Demand</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IImportDeclarationImpl#getIsStatic <em>Is Static</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IImportDeclarationImpl extends IJavaElementImpl implements IImportDeclaration {
	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceRange() <em>Source Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange sourceRange;

	/**
	 * The default value of the '{@link #getIsOnDemand() <em>Is On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOnDemand()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ON_DEMAND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsOnDemand() <em>Is On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOnDemand()
	 * @generated
	 * @ordered
	 */
	protected Boolean isOnDemand = IS_ON_DEMAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_STATIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected Boolean isStatic = IS_STATIC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IImportDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IIMPORT_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getSourceRange() {
		if (sourceRange != null && sourceRange.eIsProxy()) {
			InternalEObject oldSourceRange = (InternalEObject)sourceRange;
			sourceRange = (ISourceRange)eResolveProxy(oldSourceRange);
			if (sourceRange != oldSourceRange) {
				InternalEObject newSourceRange = (InternalEObject)sourceRange;
				NotificationChain msgs = oldSourceRange.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, null);
				if (newSourceRange.eInternalContainer() == null) {
					msgs = newSourceRange.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, oldSourceRange, sourceRange));
			}
		}
		return sourceRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange basicGetSourceRange() {
		return sourceRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceRange(ISourceRange newSourceRange, NotificationChain msgs) {
		ISourceRange oldSourceRange = sourceRange;
		sourceRange = newSourceRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, oldSourceRange, newSourceRange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceRange(ISourceRange newSourceRange) {
		if (newSourceRange != sourceRange) {
			NotificationChain msgs = null;
			if (sourceRange != null)
				msgs = ((InternalEObject)sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE, newSourceRange, newSourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsOnDemand() {
		return isOnDemand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOnDemand(Boolean newIsOnDemand) {
		Boolean oldIsOnDemand = isOnDemand;
		isOnDemand = newIsOnDemand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND, oldIsOnDemand, isOnDemand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsStatic() {
		return isStatic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsStatic(Boolean newIsStatic) {
		Boolean oldIsStatic = isStatic;
		isStatic = newIsStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IIMPORT_DECLARATION__IS_STATIC, oldIsStatic, isStatic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				return basicSetSourceRange(null, msgs);
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				return getSource();
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				if (resolve) return getSourceRange();
				return basicGetSourceRange();
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				return getIsOnDemand();
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				return getIsStatic();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				setIsOnDemand((Boolean)newValue);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				setIsStatic((Boolean)newValue);
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				setIsOnDemand(IS_ON_DEMAND_EDEFAULT);
				return;
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				setIsStatic(IS_STATIC_EDEFAULT);
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
			case CorePackage.IIMPORT_DECLARATION__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE:
				return sourceRange != null;
			case CorePackage.IIMPORT_DECLARATION__IS_ON_DEMAND:
				return IS_ON_DEMAND_EDEFAULT == null ? isOnDemand != null : !IS_ON_DEMAND_EDEFAULT.equals(isOnDemand);
			case CorePackage.IIMPORT_DECLARATION__IS_STATIC:
				return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
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
		if (baseClass == ISourceReference.class) {
			switch (derivedFeatureID) {
				case CorePackage.IIMPORT_DECLARATION__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
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
		if (baseClass == ISourceReference.class) {
			switch (baseFeatureID) {
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.IIMPORT_DECLARATION__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.IIMPORT_DECLARATION__SOURCE_RANGE;
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
		result.append(" (source: ");
		result.append(source);
		result.append(", isOnDemand: ");
		result.append(isOnDemand);
		result.append(", isStatic: ");
		result.append(isStatic);
		result.append(')');
		return result.toString();
	}

} //IImportDeclarationImpl
