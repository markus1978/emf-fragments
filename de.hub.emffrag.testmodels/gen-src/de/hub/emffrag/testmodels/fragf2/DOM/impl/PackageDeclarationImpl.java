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
package de.hub.emffrag.testmodels.fragf2.DOM.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.fragf2.Core.IPackageFragment;
import de.hub.emffrag.testmodels.fragf2.DOM.Annotation;
import de.hub.emffrag.testmodels.fragf2.DOM.DOMPackage;
import de.hub.emffrag.testmodels.fragf2.DOM.Javadoc;
import de.hub.emffrag.testmodels.fragf2.DOM.Name;
import de.hub.emffrag.testmodels.fragf2.DOM.PackageDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.PackageDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.PackageDeclarationImpl#getJavadoc <em>Javadoc</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.PackageDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.fragf2.DOM.impl.PackageDeclarationImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageDeclarationImpl extends ASTNodeImpl implements PackageDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.PACKAGE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Annotation> getAnnotations() {
		return (EList<Annotation>)eGet(DOMPackage.Literals.PACKAGE_DECLARATION__ANNOTATIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc getJavadoc() {
		return (Javadoc)eGet(DOMPackage.Literals.PACKAGE_DECLARATION__JAVADOC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavadoc(Javadoc newJavadoc) {
		eSet(DOMPackage.Literals.PACKAGE_DECLARATION__JAVADOC, newJavadoc);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Name getName() {
		return (Name)eGet(DOMPackage.Literals.PACKAGE_DECLARATION__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(Name newName) {
		eSet(DOMPackage.Literals.PACKAGE_DECLARATION__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment getBinding() {
		return (IPackageFragment)eGet(DOMPackage.Literals.PACKAGE_DECLARATION__BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(IPackageFragment newBinding) {
		eSet(DOMPackage.Literals.PACKAGE_DECLARATION__BINDING, newBinding);
	}

} //PackageDeclarationImpl
