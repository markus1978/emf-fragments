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
package de.hub.emffrag.testmodels.eobject.testmodel.accessnot.impl;

import de.hub.emffrag.testmodels.eobject.testmodel.TestEnum;
import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;

import de.hub.emffrag.testmodels.eobject.testmodel.accessnot.meta.TestModelFactory;
import de.hub.emffrag.testmodels.eobject.testmodel.accessnot.meta.TestModelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestModelPackageImpl extends EPackageImpl implements TestModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum testEnumEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.hub.emffrag.testmodels.eobject.testmodel.accessnot.meta.TestModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestModelPackageImpl() {
		super(eNS_URI, TestModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TestModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestModelPackage init() {
		if (isInited) return (TestModelPackage)EPackage.Registry.INSTANCE.getEPackage(TestModelPackage.eNS_URI);

		// Obtain or create and register package
		TestModelPackageImpl theTestModelPackage = (TestModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TestModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TestModelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTestModelPackage.createPackageContents();

		// Initialize created meta-data
		theTestModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestModelPackage.eNS_URI, theTestModelPackage);
		return theTestModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestObject() {
		return testObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestObject_Name() {
		return (EAttribute)testObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestObject_RegularContents() {
		return (EReference)testObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestObject_FragmentedContents() {
		return (EReference)testObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestObject_CrossReferences() {
		return (EReference)testObjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestObject_EnumAttribute() {
		return (EAttribute)testObjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestObject_ArbitraryContents() {
		return (EReference)testObjectEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTestEnum() {
		return testEnumEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestModelFactory getTestModelFactory() {
		return (TestModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testObjectEClass = createEClass(TEST_OBJECT);
		createEAttribute(testObjectEClass, TEST_OBJECT__NAME);
		createEReference(testObjectEClass, TEST_OBJECT__REGULAR_CONTENTS);
		createEReference(testObjectEClass, TEST_OBJECT__FRAGMENTED_CONTENTS);
		createEReference(testObjectEClass, TEST_OBJECT__CROSS_REFERENCES);
		createEAttribute(testObjectEClass, TEST_OBJECT__ENUM_ATTRIBUTE);
		createEReference(testObjectEClass, TEST_OBJECT__ARBITRARY_CONTENTS);

		// Create enums
		testEnumEEnum = createEEnum(TEST_ENUM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(testObjectEClass, TestObject.class, "TestObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestObject_RegularContents(), this.getTestObject(), null, "regularContents", null, 0, -1, TestObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestObject_FragmentedContents(), this.getTestObject(), null, "fragmentedContents", null, 0, -1, TestObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestObject_CrossReferences(), this.getTestObject(), null, "crossReferences", null, 0, -1, TestObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestObject_EnumAttribute(), this.getTestEnum(), "enumAttribute", null, 0, 1, TestObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestObject_ArbitraryContents(), ecorePackage.getEObject(), null, "arbitraryContents", null, 0, -1, TestObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(testEnumEEnum, TestEnum.class, "TestEnum");
		addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL1);
		addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL2);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// de.hub.emffrag
		createDeAnnotations();
	}

	/**
	 * Initializes the annotations for <b>de.hub.emffrag</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createDeAnnotations() {
		String source = "de.hub.emffrag";		
		addAnnotation
		  (getTestObject_FragmentedContents(), 
		   source, 
		   new String[] {
			 "fragments", "true"
		   });
	}

} //TestModelPackageImpl
