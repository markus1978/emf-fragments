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
package de.hub.emffrag.testmodels.frag.Core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.hub.emffrag.testmodels.frag.Core.BinaryPackageFragmentRoot;
import de.hub.emffrag.testmodels.frag.Core.CoreFactory;
import de.hub.emffrag.testmodels.frag.Core.CorePackage;
import de.hub.emffrag.testmodels.frag.Core.IClassFile;
import de.hub.emffrag.testmodels.frag.Core.ICompilationUnit;
import de.hub.emffrag.testmodels.frag.Core.IField;
import de.hub.emffrag.testmodels.frag.Core.IImportDeclaration;
import de.hub.emffrag.testmodels.frag.Core.IInitializer;
import de.hub.emffrag.testmodels.frag.Core.IJavaModel;
import de.hub.emffrag.testmodels.frag.Core.IJavaProject;
import de.hub.emffrag.testmodels.frag.Core.IMethod;
import de.hub.emffrag.testmodels.frag.Core.IPackageFragment;
import de.hub.emffrag.testmodels.frag.Core.ISourceRange;
import de.hub.emffrag.testmodels.frag.Core.IType;
import de.hub.emffrag.testmodels.frag.Core.ITypeParameter;
import de.hub.emffrag.testmodels.frag.Core.Modifiers;
import de.hub.emffrag.testmodels.frag.Core.Parameter;
import de.hub.emffrag.testmodels.frag.Core.SourcePackageFragmentRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoreFactory init() {
		try {
			CoreFactory theCoreFactory = (CoreFactory)EPackage.Registry.INSTANCE.getEFactory("org.amma.dsl.jdt.core"); 
			if (theCoreFactory != null) {
				return theCoreFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CorePackage.IJAVA_MODEL: return createIJavaModel();
			case CorePackage.IJAVA_PROJECT: return createIJavaProject();
			case CorePackage.BINARY_PACKAGE_FRAGMENT_ROOT: return createBinaryPackageFragmentRoot();
			case CorePackage.SOURCE_PACKAGE_FRAGMENT_ROOT: return createSourcePackageFragmentRoot();
			case CorePackage.IPACKAGE_FRAGMENT: return createIPackageFragment();
			case CorePackage.ICOMPILATION_UNIT: return createICompilationUnit();
			case CorePackage.ICLASS_FILE: return createIClassFile();
			case CorePackage.IIMPORT_DECLARATION: return createIImportDeclaration();
			case CorePackage.ISOURCE_RANGE: return createISourceRange();
			case CorePackage.ITYPE: return createIType();
			case CorePackage.ITYPE_PARAMETER: return createITypeParameter();
			case CorePackage.IINITIALIZER: return createIInitializer();
			case CorePackage.IFIELD: return createIField();
			case CorePackage.IMETHOD: return createIMethod();
			case CorePackage.PARAMETER: return createParameter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CorePackage.MODIFIERS:
				return createModifiersFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CorePackage.MODIFIERS:
				return convertModifiersToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaModel createIJavaModel() {
		IJavaModelImpl iJavaModel = new IJavaModelImpl();
		return iJavaModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaProject createIJavaProject() {
		IJavaProjectImpl iJavaProject = new IJavaProjectImpl();
		return iJavaProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryPackageFragmentRoot createBinaryPackageFragmentRoot() {
		BinaryPackageFragmentRootImpl binaryPackageFragmentRoot = new BinaryPackageFragmentRootImpl();
		return binaryPackageFragmentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourcePackageFragmentRoot createSourcePackageFragmentRoot() {
		SourcePackageFragmentRootImpl sourcePackageFragmentRoot = new SourcePackageFragmentRootImpl();
		return sourcePackageFragmentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackageFragment createIPackageFragment() {
		IPackageFragmentImpl iPackageFragment = new IPackageFragmentImpl();
		return iPackageFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICompilationUnit createICompilationUnit() {
		ICompilationUnitImpl iCompilationUnit = new ICompilationUnitImpl();
		return iCompilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IClassFile createIClassFile() {
		IClassFileImpl iClassFile = new IClassFileImpl();
		return iClassFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IImportDeclaration createIImportDeclaration() {
		IImportDeclarationImpl iImportDeclaration = new IImportDeclarationImpl();
		return iImportDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange createISourceRange() {
		ISourceRangeImpl iSourceRange = new ISourceRangeImpl();
		return iSourceRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType createIType() {
		ITypeImpl iType = new ITypeImpl();
		return iType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITypeParameter createITypeParameter() {
		ITypeParameterImpl iTypeParameter = new ITypeParameterImpl();
		return iTypeParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IInitializer createIInitializer() {
		IInitializerImpl iInitializer = new IInitializerImpl();
		return iInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IField createIField() {
		IFieldImpl iField = new IFieldImpl();
		return iField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMethod createIMethod() {
		IMethodImpl iMethod = new IMethodImpl();
		return iMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifiers createModifiersFromString(EDataType eDataType, String initialValue) {
		Modifiers result = Modifiers.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertModifiersToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorePackage getCorePackage() {
		return (CorePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CorePackage getPackage() {
		return CorePackage.eINSTANCE;
	}

} //CoreFactoryImpl
