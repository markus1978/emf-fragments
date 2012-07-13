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
package de.hub.emffrag.testmodels.reflective.Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IPackage Fragment Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.IPackageFragmentRoot#getPackageFragments <em>Package Fragments</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIPackageFragmentRoot()
 * @model abstract="true"
 * @generated
 */
public interface IPackageFragmentRoot extends IJavaElement, PhysicalElement {
	/**
	 * Returns the value of the '<em><b>Package Fragments</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.reflective.Core.IPackageFragment}.
	 * It is bidirectional and its opposite is '{@link de.hub.emffrag.testmodels.reflective.Core.IPackageFragment#getPackageFragmentRoot <em>Package Fragment Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Fragments</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage#getIPackageFragmentRoot_PackageFragments()
	 * @see de.hub.emffrag.testmodels.reflective.Core.IPackageFragment#getPackageFragmentRoot
	 * @model opposite="packageFragmentRoot" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<IPackageFragment> getPackageFragments();

} // IPackageFragmentRoot
