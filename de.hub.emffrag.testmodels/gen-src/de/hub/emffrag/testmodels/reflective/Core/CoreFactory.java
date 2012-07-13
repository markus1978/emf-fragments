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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.testmodels.reflective.Core.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CoreFactory eINSTANCE = de.hub.emffrag.testmodels.reflective.Core.impl.CoreFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>IJava Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IJava Model</em>'.
	 * @generated
	 */
	IJavaModel createIJavaModel();

	/**
	 * Returns a new object of class '<em>IJava Project</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IJava Project</em>'.
	 * @generated
	 */
	IJavaProject createIJavaProject();

	/**
	 * Returns a new object of class '<em>Binary Package Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Package Fragment Root</em>'.
	 * @generated
	 */
	BinaryPackageFragmentRoot createBinaryPackageFragmentRoot();

	/**
	 * Returns a new object of class '<em>Source Package Fragment Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source Package Fragment Root</em>'.
	 * @generated
	 */
	SourcePackageFragmentRoot createSourcePackageFragmentRoot();

	/**
	 * Returns a new object of class '<em>IPackage Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IPackage Fragment</em>'.
	 * @generated
	 */
	IPackageFragment createIPackageFragment();

	/**
	 * Returns a new object of class '<em>ICompilation Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ICompilation Unit</em>'.
	 * @generated
	 */
	ICompilationUnit createICompilationUnit();

	/**
	 * Returns a new object of class '<em>IClass File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IClass File</em>'.
	 * @generated
	 */
	IClassFile createIClassFile();

	/**
	 * Returns a new object of class '<em>IImport Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IImport Declaration</em>'.
	 * @generated
	 */
	IImportDeclaration createIImportDeclaration();

	/**
	 * Returns a new object of class '<em>ISource Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ISource Range</em>'.
	 * @generated
	 */
	ISourceRange createISourceRange();

	/**
	 * Returns a new object of class '<em>IType</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IType</em>'.
	 * @generated
	 */
	IType createIType();

	/**
	 * Returns a new object of class '<em>IType Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IType Parameter</em>'.
	 * @generated
	 */
	ITypeParameter createITypeParameter();

	/**
	 * Returns a new object of class '<em>IInitializer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IInitializer</em>'.
	 * @generated
	 */
	IInitializer createIInitializer();

	/**
	 * Returns a new object of class '<em>IField</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IField</em>'.
	 * @generated
	 */
	IField createIField();

	/**
	 * Returns a new object of class '<em>IMethod</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IMethod</em>'.
	 * @generated
	 */
	IMethod createIMethod();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CorePackage getCorePackage();

} //CoreFactory
