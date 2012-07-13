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
package de.hub.emffrag.testmodels.frag.DOM.impl;

import de.hub.emffrag.testmodels.frag.DOM.DOMPackage;
import de.hub.emffrag.testmodels.frag.DOM.Modifier;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getAbstract <em>Abstract</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getNative <em>Native</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getNone <em>None</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getPrivate <em>Private</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getProtected <em>Protected</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getPublic <em>Public</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getStatic <em>Static</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getStrictfp <em>Strictfp</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getTransient <em>Transient</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.frag.DOM.impl.ModifierImpl#getVolatile <em>Volatile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModifierImpl extends ASTNodeImpl implements Modifier {
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
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__ABSTRACT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(Boolean newAbstract) {
		eSet(DOMPackage.Literals.MODIFIER__ABSTRACT, newAbstract);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFinal() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__FINAL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(Boolean newFinal) {
		eSet(DOMPackage.Literals.MODIFIER__FINAL, newFinal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNative() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__NATIVE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNative(Boolean newNative) {
		eSet(DOMPackage.Literals.MODIFIER__NATIVE, newNative);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getNone() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__NONE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNone(Boolean newNone) {
		eSet(DOMPackage.Literals.MODIFIER__NONE, newNone);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPrivate() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__PRIVATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrivate(Boolean newPrivate) {
		eSet(DOMPackage.Literals.MODIFIER__PRIVATE, newPrivate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getProtected() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__PROTECTED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProtected(Boolean newProtected) {
		eSet(DOMPackage.Literals.MODIFIER__PROTECTED, newProtected);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPublic() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__PUBLIC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublic(Boolean newPublic) {
		eSet(DOMPackage.Literals.MODIFIER__PUBLIC, newPublic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStatic() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__STATIC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(Boolean newStatic) {
		eSet(DOMPackage.Literals.MODIFIER__STATIC, newStatic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getStrictfp() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__STRICTFP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrictfp(Boolean newStrictfp) {
		eSet(DOMPackage.Literals.MODIFIER__STRICTFP, newStrictfp);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getSynchronized() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__SYNCHRONIZED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronized(Boolean newSynchronized) {
		eSet(DOMPackage.Literals.MODIFIER__SYNCHRONIZED, newSynchronized);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getTransient() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__TRANSIENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransient(Boolean newTransient) {
		eSet(DOMPackage.Literals.MODIFIER__TRANSIENT, newTransient);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getVolatile() {
		return (Boolean)eGet(DOMPackage.Literals.MODIFIER__VOLATILE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVolatile(Boolean newVolatile) {
		eSet(DOMPackage.Literals.MODIFIER__VOLATILE, newVolatile);
	}

} //ModifierImpl
