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
package de.hub.emffrag.testmodels.reflective.testmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestModelFactory
 * @model kind="package"
 * @generated
 */
public interface TestModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "testmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://hu-berlin.de/sam/emfhbase/testmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestModelPackage eINSTANCE = de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.reflective.testmodel.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.ContainerImpl
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__CONTENTS = 0;

	/**
	 * The feature id for the '<em><b>Fragmented Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__FRAGMENTED_CONTENTS = 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.reflective.testmodel.impl.ContentsImpl <em>Contents</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.ContentsImpl
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl#getContents()
	 * @generated
	 */
	int CONTENTS = 1;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENTS__CONTENTS = CONTAINER__CONTENTS;

	/**
	 * The feature id for the '<em><b>Fragmented Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENTS__FRAGMENTED_CONTENTS = CONTAINER__FRAGMENTED_CONTENTS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENTS__VALUE = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Contents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENTS_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.reflective.testmodel.impl.TestObjectImpl <em>Test Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestObjectImpl
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl#getTestObject()
	 * @generated
	 */
	int TEST_OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Regular Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT__REGULAR_CONTENTS = 1;

	/**
	 * The feature id for the '<em><b>Fragmented Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT__FRAGMENTED_CONTENTS = 2;

	/**
	 * The feature id for the '<em><b>Cross References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT__CROSS_REFERENCES = 3;

	/**
	 * The number of structural features of the '<em>Test Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.reflective.testmodel.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.reflective.testmodel.Container#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.Container#getContents()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Contents();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.reflective.testmodel.Container#getFragmentedContents <em>Fragmented Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragmented Contents</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.Container#getFragmentedContents()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_FragmentedContents();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.reflective.testmodel.Contents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contents</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.Contents
	 * @generated
	 */
	EClass getContents();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.testmodels.reflective.testmodel.Contents#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.Contents#getValue()
	 * @see #getContents()
	 * @generated
	 */
	EAttribute getContents_Value();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject <em>Test Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Object</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestObject
	 * @generated
	 */
	EClass getTestObject();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getName()
	 * @see #getTestObject()
	 * @generated
	 */
	EAttribute getTestObject_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getRegularContents <em>Regular Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regular Contents</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getRegularContents()
	 * @see #getTestObject()
	 * @generated
	 */
	EReference getTestObject_RegularContents();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getFragmentedContents <em>Fragmented Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragmented Contents</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getFragmentedContents()
	 * @see #getTestObject()
	 * @generated
	 */
	EReference getTestObject_FragmentedContents();

	/**
	 * Returns the meta object for the reference list '{@link de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getCrossReferences <em>Cross References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cross References</em>'.
	 * @see de.hub.emffrag.testmodels.reflective.testmodel.TestObject#getCrossReferences()
	 * @see #getTestObject()
	 * @generated
	 */
	EReference getTestObject_CrossReferences();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestModelFactory getTestModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.reflective.testmodel.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.ContainerImpl
		 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__CONTENTS = eINSTANCE.getContainer_Contents();

		/**
		 * The meta object literal for the '<em><b>Fragmented Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__FRAGMENTED_CONTENTS = eINSTANCE.getContainer_FragmentedContents();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.reflective.testmodel.impl.ContentsImpl <em>Contents</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.ContentsImpl
		 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl#getContents()
		 * @generated
		 */
		EClass CONTENTS = eINSTANCE.getContents();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENTS__VALUE = eINSTANCE.getContents_Value();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.reflective.testmodel.impl.TestObjectImpl <em>Test Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestObjectImpl
		 * @see de.hub.emffrag.testmodels.reflective.testmodel.impl.TestModelPackageImpl#getTestObject()
		 * @generated
		 */
		EClass TEST_OBJECT = eINSTANCE.getTestObject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_OBJECT__NAME = eINSTANCE.getTestObject_Name();

		/**
		 * The meta object literal for the '<em><b>Regular Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OBJECT__REGULAR_CONTENTS = eINSTANCE.getTestObject_RegularContents();

		/**
		 * The meta object literal for the '<em><b>Fragmented Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OBJECT__FRAGMENTED_CONTENTS = eINSTANCE.getTestObject_FragmentedContents();

		/**
		 * The meta object literal for the '<em><b>Cross References</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OBJECT__CROSS_REFERENCES = eINSTANCE.getTestObject_CrossReferences();

	}

} //TestModelPackage
