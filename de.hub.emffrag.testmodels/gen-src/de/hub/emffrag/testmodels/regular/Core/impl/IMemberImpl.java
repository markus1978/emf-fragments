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
import de.hub.emffrag.testmodels.regular.Core.IMember;
import de.hub.emffrag.testmodels.regular.Core.ISourceRange;
import de.hub.emffrag.testmodels.regular.Core.ISourceReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMember</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMemberImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMemberImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMemberImpl#getJavadocRange <em>Javadoc Range</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.impl.IMemberImpl#getNameRange <em>Name Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IMemberImpl extends IJavaElementImpl implements IMember {
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
	 * The cached value of the '{@link #getJavadocRange() <em>Javadoc Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavadocRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange javadocRange;

	/**
	 * The cached value of the '{@link #getNameRange() <em>Name Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameRange()
	 * @generated
	 * @ordered
	 */
	protected ISourceRange nameRange;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMemberImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.IMEMBER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__SOURCE, oldSource, source));
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
				NotificationChain msgs = oldSourceRange.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__SOURCE_RANGE, null, null);
				if (newSourceRange.eInternalContainer() == null) {
					msgs = newSourceRange.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__SOURCE_RANGE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.IMEMBER__SOURCE_RANGE, oldSourceRange, sourceRange));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__SOURCE_RANGE, oldSourceRange, newSourceRange);
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
				msgs = ((InternalEObject)sourceRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__SOURCE_RANGE, null, msgs);
			if (newSourceRange != null)
				msgs = ((InternalEObject)newSourceRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__SOURCE_RANGE, null, msgs);
			msgs = basicSetSourceRange(newSourceRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__SOURCE_RANGE, newSourceRange, newSourceRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getJavadocRange() {
		if (javadocRange != null && javadocRange.eIsProxy()) {
			InternalEObject oldJavadocRange = (InternalEObject)javadocRange;
			javadocRange = (ISourceRange)eResolveProxy(oldJavadocRange);
			if (javadocRange != oldJavadocRange) {
				InternalEObject newJavadocRange = (InternalEObject)javadocRange;
				NotificationChain msgs = oldJavadocRange.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__JAVADOC_RANGE, null, null);
				if (newJavadocRange.eInternalContainer() == null) {
					msgs = newJavadocRange.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__JAVADOC_RANGE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.IMEMBER__JAVADOC_RANGE, oldJavadocRange, javadocRange));
			}
		}
		return javadocRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange basicGetJavadocRange() {
		return javadocRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJavadocRange(ISourceRange newJavadocRange, NotificationChain msgs) {
		ISourceRange oldJavadocRange = javadocRange;
		javadocRange = newJavadocRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__JAVADOC_RANGE, oldJavadocRange, newJavadocRange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavadocRange(ISourceRange newJavadocRange) {
		if (newJavadocRange != javadocRange) {
			NotificationChain msgs = null;
			if (javadocRange != null)
				msgs = ((InternalEObject)javadocRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__JAVADOC_RANGE, null, msgs);
			if (newJavadocRange != null)
				msgs = ((InternalEObject)newJavadocRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__JAVADOC_RANGE, null, msgs);
			msgs = basicSetJavadocRange(newJavadocRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__JAVADOC_RANGE, newJavadocRange, newJavadocRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getNameRange() {
		if (nameRange != null && nameRange.eIsProxy()) {
			InternalEObject oldNameRange = (InternalEObject)nameRange;
			nameRange = (ISourceRange)eResolveProxy(oldNameRange);
			if (nameRange != oldNameRange) {
				InternalEObject newNameRange = (InternalEObject)nameRange;
				NotificationChain msgs = oldNameRange.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__NAME_RANGE, null, null);
				if (newNameRange.eInternalContainer() == null) {
					msgs = newNameRange.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__NAME_RANGE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.IMEMBER__NAME_RANGE, oldNameRange, nameRange));
			}
		}
		return nameRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange basicGetNameRange() {
		return nameRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNameRange(ISourceRange newNameRange, NotificationChain msgs) {
		ISourceRange oldNameRange = nameRange;
		nameRange = newNameRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__NAME_RANGE, oldNameRange, newNameRange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNameRange(ISourceRange newNameRange) {
		if (newNameRange != nameRange) {
			NotificationChain msgs = null;
			if (nameRange != null)
				msgs = ((InternalEObject)nameRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__NAME_RANGE, null, msgs);
			if (newNameRange != null)
				msgs = ((InternalEObject)newNameRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.IMEMBER__NAME_RANGE, null, msgs);
			msgs = basicSetNameRange(newNameRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.IMEMBER__NAME_RANGE, newNameRange, newNameRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.IMEMBER__SOURCE_RANGE:
				return basicSetSourceRange(null, msgs);
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				return basicSetJavadocRange(null, msgs);
			case CorePackage.IMEMBER__NAME_RANGE:
				return basicSetNameRange(null, msgs);
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
			case CorePackage.IMEMBER__SOURCE:
				return getSource();
			case CorePackage.IMEMBER__SOURCE_RANGE:
				if (resolve) return getSourceRange();
				return basicGetSourceRange();
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				if (resolve) return getJavadocRange();
				return basicGetJavadocRange();
			case CorePackage.IMEMBER__NAME_RANGE:
				if (resolve) return getNameRange();
				return basicGetNameRange();
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
			case CorePackage.IMEMBER__SOURCE:
				setSource((String)newValue);
				return;
			case CorePackage.IMEMBER__SOURCE_RANGE:
				setSourceRange((ISourceRange)newValue);
				return;
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				setJavadocRange((ISourceRange)newValue);
				return;
			case CorePackage.IMEMBER__NAME_RANGE:
				setNameRange((ISourceRange)newValue);
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
			case CorePackage.IMEMBER__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case CorePackage.IMEMBER__SOURCE_RANGE:
				setSourceRange((ISourceRange)null);
				return;
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				setJavadocRange((ISourceRange)null);
				return;
			case CorePackage.IMEMBER__NAME_RANGE:
				setNameRange((ISourceRange)null);
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
			case CorePackage.IMEMBER__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case CorePackage.IMEMBER__SOURCE_RANGE:
				return sourceRange != null;
			case CorePackage.IMEMBER__JAVADOC_RANGE:
				return javadocRange != null;
			case CorePackage.IMEMBER__NAME_RANGE:
				return nameRange != null;
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
				case CorePackage.IMEMBER__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.IMEMBER__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
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
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.IMEMBER__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.IMEMBER__SOURCE_RANGE;
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
		result.append(')');
		return result.toString();
	}

} //IMemberImpl
