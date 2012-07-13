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
 * A representation of the model object '<em><b>IPackage Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragment#getIsDefaultPackage <em>Is Default Package</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragment#getPackageFragmentRoot <em>Package Fragment Root</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragment#getClassFiles <em>Class Files</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragment#getCompilationUnits <em>Compilation Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIPackageFragment()
 * @model
 * @generated
 */
public interface IPackageFragment extends IJavaElement, PhysicalElement {
	/**
	 * Returns the value of the '<em><b>Is Default Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Default Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Default Package</em>' attribute.
	 * @see #setIsDefaultPackage(Boolean)
	 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIPackageFragment_IsDefaultPackage()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.cdo.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsDefaultPackage();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragment#getIsDefaultPackage <em>Is Default Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Default Package</em>' attribute.
	 * @see #getIsDefaultPackage()
	 * @generated
	 */
	void setIsDefaultPackage(Boolean value);

	/**
	 * Returns the value of the '<em><b>Package Fragment Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragmentRoot#getPackageFragments <em>Package Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Fragment Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Fragment Root</em>' container reference.
	 * @see #setPackageFragmentRoot(IPackageFragmentRoot)
	 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIPackageFragment_PackageFragmentRoot()
	 * @see de.hub.emffrag.testmodels.cdo.Core.IPackageFragmentRoot#getPackageFragments
	 * @model opposite="packageFragments" required="true" transient="false" ordered="false"
	 * @generated
	 */
	IPackageFragmentRoot getPackageFragmentRoot();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.cdo.Core.IPackageFragment#getPackageFragmentRoot <em>Package Fragment Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Fragment Root</em>' container reference.
	 * @see #getPackageFragmentRoot()
	 * @generated
	 */
	void setPackageFragmentRoot(IPackageFragmentRoot value);

	/**
	 * Returns the value of the '<em><b>Class Files</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.Core.IClassFile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Files</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIPackageFragment_ClassFiles()
	 * @model containment="true"
	 *        annotation="de.hub.emfhbase Fragmentation='true'"
	 * @generated
	 */
	EList<IClassFile> getClassFiles();

	/**
	 * Returns the value of the '<em><b>Compilation Units</b></em>' containment reference list.
	 * The list contents are of type {@link de.hub.emffrag.testmodels.cdo.Core.ICompilationUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compilation Units</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compilation Units</em>' containment reference list.
	 * @see de.hub.emffrag.testmodels.cdo.Core.CorePackage#getIPackageFragment_CompilationUnits()
	 * @model containment="true"
	 *        annotation="de.hub.emfhbase Fragmentation='true'"
	 * @generated
	 */
	EList<ICompilationUnit> getCompilationUnits();

} // IPackageFragment
