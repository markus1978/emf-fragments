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
package de.hub.emffrag.testmodels.reflective.DOM;

import org.eclipse.emf.common.util.EList;

import de.hub.emffrag.testmodels.reflective.Core.IPackageFragment;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getJavadoc <em>Javadoc</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getPackageDeclaration()
 * @model
 * @generated
 */
public interface PackageDeclaration extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.DOM.Annotation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getPackageDeclaration_Annotations()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Annotation> getAnnotations();

	/**
	 * Returns the value of the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Javadoc</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadoc</em>' containment reference.
	 * @see #setJavadoc(Javadoc)
	 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getPackageDeclaration_Javadoc()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Javadoc getJavadoc();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getJavadoc <em>Javadoc</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Javadoc</em>' containment reference.
	 * @see #getJavadoc()
	 * @generated
	 */
	void setJavadoc(Javadoc value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(Name)
	 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getPackageDeclaration_Name()
	 * @model containment="true" resolveProxies="true" required="true" ordered="false"
	 * @generated
	 */
	Name getName();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Name value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(IPackageFragment)
	 * @see de.hub.emffrag.testmodels.reflective.DOM.DOMPackage#getPackageDeclaration_Binding()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	IPackageFragment getBinding();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.reflective.DOM.PackageDeclaration#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(IPackageFragment value);

} // PackageDeclaration
