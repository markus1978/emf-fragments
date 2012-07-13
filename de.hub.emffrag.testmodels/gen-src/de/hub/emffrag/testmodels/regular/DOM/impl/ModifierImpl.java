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
package de.hub.emffrag.testmodels.regular.DOM.impl;

import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.DOM.Modifier;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getAbstract <em>Abstract</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getNative <em>Native</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getNone <em>None</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getPrivate <em>Private</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getProtected <em>Protected</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getPublic <em>Public</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getStatic <em>Static</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getStrictfp <em>Strictfp</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getTransient <em>Transient</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.DOM.impl.ModifierImpl#getVolatile <em>Volatile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModifierImpl extends ASTNodeImpl implements Modifier {
	/**
	 * The default value of the '{@link #getAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ABSTRACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstract()
	 * @generated
	 * @ordered
	 */
	protected Boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinal()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FINAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinal()
	 * @generated
	 * @ordered
	 */
	protected Boolean final_ = FINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNative()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean NATIVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNative()
	 * @generated
	 * @ordered
	 */
	protected Boolean native_ = NATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNone() <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNone()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean NONE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNone() <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNone()
	 * @generated
	 * @ordered
	 */
	protected Boolean none = NONE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivate()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PRIVATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivate()
	 * @generated
	 * @ordered
	 */
	protected Boolean private_ = PRIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProtected() <em>Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtected()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PROTECTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProtected() <em>Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtected()
	 * @generated
	 * @ordered
	 */
	protected Boolean protected_ = PROTECTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPublic() <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PUBLIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPublic() <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublic()
	 * @generated
	 * @ordered
	 */
	protected Boolean public_ = PUBLIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STATIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatic()
	 * @generated
	 * @ordered
	 */
	protected Boolean static_ = STATIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getStrictfp() <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrictfp()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STRICTFP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStrictfp() <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrictfp()
	 * @generated
	 * @ordered
	 */
	protected Boolean strictfp_ = STRICTFP_EDEFAULT;

	/**
	 * The default value of the '{@link #getSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchronized()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean SYNCHRONIZED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchronized()
	 * @generated
	 * @ordered
	 */
	protected Boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransient() <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransient()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean TRANSIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransient() <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransient()
	 * @generated
	 * @ordered
	 */
	protected Boolean transient_ = TRANSIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean VOLATILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolatile()
	 * @generated
	 * @ordered
	 */
	protected Boolean volatile_ = VOLATILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.MODIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(Boolean newAbstract) {
		Boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFinal() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(Boolean newFinal) {
		Boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNative() {
		return native_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNative(Boolean newNative) {
		Boolean oldNative = native_;
		native_ = newNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__NATIVE, oldNative, native_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNone() {
		return none;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNone(Boolean newNone) {
		Boolean oldNone = none;
		none = newNone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__NONE, oldNone, none));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPrivate() {
		return private_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrivate(Boolean newPrivate) {
		Boolean oldPrivate = private_;
		private_ = newPrivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__PRIVATE, oldPrivate, private_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getProtected() {
		return protected_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProtected(Boolean newProtected) {
		Boolean oldProtected = protected_;
		protected_ = newProtected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__PROTECTED, oldProtected, protected_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPublic() {
		return public_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublic(Boolean newPublic) {
		Boolean oldPublic = public_;
		public_ = newPublic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__PUBLIC, oldPublic, public_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStatic() {
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(Boolean newStatic) {
		Boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStrictfp() {
		return strictfp_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrictfp(Boolean newStrictfp) {
		Boolean oldStrictfp = strictfp_;
		strictfp_ = newStrictfp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__STRICTFP, oldStrictfp, strictfp_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getSynchronized() {
		return synchronized_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronized(Boolean newSynchronized) {
		Boolean oldSynchronized = synchronized_;
		synchronized_ = newSynchronized;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__SYNCHRONIZED, oldSynchronized, synchronized_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getTransient() {
		return transient_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransient(Boolean newTransient) {
		Boolean oldTransient = transient_;
		transient_ = newTransient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__TRANSIENT, oldTransient, transient_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVolatile() {
		return volatile_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVolatile(Boolean newVolatile) {
		Boolean oldVolatile = volatile_;
		volatile_ = newVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DOMPackage.MODIFIER__VOLATILE, oldVolatile, volatile_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DOMPackage.MODIFIER__ABSTRACT:
				return getAbstract();
			case DOMPackage.MODIFIER__FINAL:
				return getFinal();
			case DOMPackage.MODIFIER__NATIVE:
				return getNative();
			case DOMPackage.MODIFIER__NONE:
				return getNone();
			case DOMPackage.MODIFIER__PRIVATE:
				return getPrivate();
			case DOMPackage.MODIFIER__PROTECTED:
				return getProtected();
			case DOMPackage.MODIFIER__PUBLIC:
				return getPublic();
			case DOMPackage.MODIFIER__STATIC:
				return getStatic();
			case DOMPackage.MODIFIER__STRICTFP:
				return getStrictfp();
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				return getSynchronized();
			case DOMPackage.MODIFIER__TRANSIENT:
				return getTransient();
			case DOMPackage.MODIFIER__VOLATILE:
				return getVolatile();
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
			case DOMPackage.MODIFIER__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__FINAL:
				setFinal((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__NATIVE:
				setNative((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__NONE:
				setNone((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__PRIVATE:
				setPrivate((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__PROTECTED:
				setProtected((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__PUBLIC:
				setPublic((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__STATIC:
				setStatic((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__STRICTFP:
				setStrictfp((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				setSynchronized((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__TRANSIENT:
				setTransient((Boolean)newValue);
				return;
			case DOMPackage.MODIFIER__VOLATILE:
				setVolatile((Boolean)newValue);
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
			case DOMPackage.MODIFIER__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__NATIVE:
				setNative(NATIVE_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__NONE:
				setNone(NONE_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__PRIVATE:
				setPrivate(PRIVATE_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__PROTECTED:
				setProtected(PROTECTED_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__PUBLIC:
				setPublic(PUBLIC_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__STRICTFP:
				setStrictfp(STRICTFP_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				setSynchronized(SYNCHRONIZED_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__TRANSIENT:
				setTransient(TRANSIENT_EDEFAULT);
				return;
			case DOMPackage.MODIFIER__VOLATILE:
				setVolatile(VOLATILE_EDEFAULT);
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
			case DOMPackage.MODIFIER__ABSTRACT:
				return ABSTRACT_EDEFAULT == null ? abstract_ != null : !ABSTRACT_EDEFAULT.equals(abstract_);
			case DOMPackage.MODIFIER__FINAL:
				return FINAL_EDEFAULT == null ? final_ != null : !FINAL_EDEFAULT.equals(final_);
			case DOMPackage.MODIFIER__NATIVE:
				return NATIVE_EDEFAULT == null ? native_ != null : !NATIVE_EDEFAULT.equals(native_);
			case DOMPackage.MODIFIER__NONE:
				return NONE_EDEFAULT == null ? none != null : !NONE_EDEFAULT.equals(none);
			case DOMPackage.MODIFIER__PRIVATE:
				return PRIVATE_EDEFAULT == null ? private_ != null : !PRIVATE_EDEFAULT.equals(private_);
			case DOMPackage.MODIFIER__PROTECTED:
				return PROTECTED_EDEFAULT == null ? protected_ != null : !PROTECTED_EDEFAULT.equals(protected_);
			case DOMPackage.MODIFIER__PUBLIC:
				return PUBLIC_EDEFAULT == null ? public_ != null : !PUBLIC_EDEFAULT.equals(public_);
			case DOMPackage.MODIFIER__STATIC:
				return STATIC_EDEFAULT == null ? static_ != null : !STATIC_EDEFAULT.equals(static_);
			case DOMPackage.MODIFIER__STRICTFP:
				return STRICTFP_EDEFAULT == null ? strictfp_ != null : !STRICTFP_EDEFAULT.equals(strictfp_);
			case DOMPackage.MODIFIER__SYNCHRONIZED:
				return SYNCHRONIZED_EDEFAULT == null ? synchronized_ != null : !SYNCHRONIZED_EDEFAULT.equals(synchronized_);
			case DOMPackage.MODIFIER__TRANSIENT:
				return TRANSIENT_EDEFAULT == null ? transient_ != null : !TRANSIENT_EDEFAULT.equals(transient_);
			case DOMPackage.MODIFIER__VOLATILE:
				return VOLATILE_EDEFAULT == null ? volatile_ != null : !VOLATILE_EDEFAULT.equals(volatile_);
		}
		return super.eIsSet(featureID);
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
		result.append(" (abstract: ");
		result.append(abstract_);
		result.append(", final: ");
		result.append(final_);
		result.append(", native: ");
		result.append(native_);
		result.append(", none: ");
		result.append(none);
		result.append(", private: ");
		result.append(private_);
		result.append(", protected: ");
		result.append(protected_);
		result.append(", public: ");
		result.append(public_);
		result.append(", static: ");
		result.append(static_);
		result.append(", strictfp: ");
		result.append(strictfp_);
		result.append(", synchronized: ");
		result.append(synchronized_);
		result.append(", transient: ");
		result.append(transient_);
		result.append(", volatile: ");
		result.append(volatile_);
		result.append(')');
		return result.toString();
	}

} //ModifierImpl
