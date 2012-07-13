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
package de.hub.emffrag.testmodels.cdo.Core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IJava Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.Core.IJavaModel#getJavaProjects <em>Java Projects</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.Core.IJavaModel#getExternalPackageFragmentRoots <em>External Package Fragment Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIJavaModel()
 * @model
 * @generated
 */
public interface IJavaModel extends PhysicalElement {
	/**
	 * Returns the value of the '<em><b>Java Projects</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.Core.IJavaProject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Projects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Projects</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIJavaModel_JavaProjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<IJavaProject> getJavaProjects();

	/**
	 * Returns the value of the '<em><b>External Package Fragment Roots</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragmentRoot}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Package Fragment Roots</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Package Fragment Roots</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIJavaModel_ExternalPackageFragmentRoots()
	 * @model containment="true"
	 * @generated
	 */
	EList<IPackageFragmentRoot> getExternalPackageFragmentRoots();

} // IJavaModel
