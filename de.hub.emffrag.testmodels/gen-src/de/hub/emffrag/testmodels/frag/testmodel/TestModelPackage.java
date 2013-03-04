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
package de.hub.emffrag.testmodels.frag.testmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.hub.emffrag.model.emffrag.EmfFragPackage;

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
 * @see de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory
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
	TestModelPackage eINSTANCE = de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectImpl <em>Test Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectImpl
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestObject()
	 * @generated
	 */
	int TEST_OBJECT = 0;

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
	 * The feature id for the '<em><b>Enum Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT__ENUM_ATTRIBUTE = 4;

	/**
	 * The number of structural features of the '<em>Test Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_FEATURE_COUNT = 5;


	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectIndexImpl <em>Test Object Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectIndexImpl
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestObjectIndex()
	 * @generated
	 */
	int TEST_OBJECT_INDEX = 1;

	/**
	 * The feature id for the '<em><b>First Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_INDEX__FIRST_KEY = EmfFragPackage.INDEXED_MAP__FIRST_KEY;

	/**
	 * The feature id for the '<em><b>Last Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_INDEX__LAST_KEY = EmfFragPackage.INDEXED_MAP__LAST_KEY;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_INDEX__PREFIX = EmfFragPackage.INDEXED_MAP__PREFIX;

	/**
	 * The feature id for the '<em><b>Keytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_INDEX__KEYTYPE = EmfFragPackage.INDEXED_MAP__KEYTYPE;

	/**
	 * The number of structural features of the '<em>Test Object Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_INDEX_FEATURE_COUNT = EmfFragPackage.INDEXED_MAP_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestContainmentIndexImpl <em>Test Containment Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestContainmentIndexImpl
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestContainmentIndex()
	 * @generated
	 */
	int TEST_CONTAINMENT_INDEX = 2;

	/**
	 * The feature id for the '<em><b>First Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINMENT_INDEX__FIRST_KEY = EmfFragPackage.CONTAINMENT_INDEXED_MAP__FIRST_KEY;

	/**
	 * The feature id for the '<em><b>Last Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINMENT_INDEX__LAST_KEY = EmfFragPackage.CONTAINMENT_INDEXED_MAP__LAST_KEY;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINMENT_INDEX__PREFIX = EmfFragPackage.CONTAINMENT_INDEXED_MAP__PREFIX;

	/**
	 * The feature id for the '<em><b>Keytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINMENT_INDEX__KEYTYPE = EmfFragPackage.CONTAINMENT_INDEXED_MAP__KEYTYPE;

	/**
	 * The number of structural features of the '<em>Test Containment Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONTAINMENT_INDEX_FEATURE_COUNT = EmfFragPackage.CONTAINMENT_INDEXED_MAP_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectWithIndexesImpl <em>Test Object With Indexes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectWithIndexesImpl
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestObjectWithIndexes()
	 * @generated
	 */
	int TEST_OBJECT_WITH_INDEXES = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__NAME = TEST_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Regular Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__REGULAR_CONTENTS = TEST_OBJECT__REGULAR_CONTENTS;

	/**
	 * The feature id for the '<em><b>Fragmented Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__FRAGMENTED_CONTENTS = TEST_OBJECT__FRAGMENTED_CONTENTS;

	/**
	 * The feature id for the '<em><b>Cross References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__CROSS_REFERENCES = TEST_OBJECT__CROSS_REFERENCES;

	/**
	 * The feature id for the '<em><b>Enum Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__ENUM_ATTRIBUTE = TEST_OBJECT__ENUM_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Indexed References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES = TEST_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexed Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS = TEST_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Object With Indexes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECT_WITH_INDEXES_FEATURE_COUNT = TEST_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.testmodels.frag.testmodel.TestEnum <em>Test Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestEnum
	 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestEnum()
	 * @generated
	 */
	int TEST_ENUM = 4;


	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject <em>Test Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Object</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject
	 * @generated
	 */
	EClass getTestObject();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject#getName()
	 * @see #getTestObject()
	 * @generated
	 */
	EAttribute getTestObject_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject#getRegularContents <em>Regular Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regular Contents</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject#getRegularContents()
	 * @see #getTestObject()
	 * @generated
	 */
	EReference getTestObject_RegularContents();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject#getFragmentedContents <em>Fragmented Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragmented Contents</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject#getFragmentedContents()
	 * @see #getTestObject()
	 * @generated
	 */
	EReference getTestObject_FragmentedContents();

	/**
	 * Returns the meta object for the reference list '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject#getCrossReferences <em>Cross References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cross References</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject#getCrossReferences()
	 * @see #getTestObject()
	 * @generated
	 */
	EReference getTestObject_CrossReferences();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject#getEnumAttribute <em>Enum Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enum Attribute</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject#getEnumAttribute()
	 * @see #getTestObject()
	 * @generated
	 */
	EAttribute getTestObject_EnumAttribute();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex <em>Test Object Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Object Index</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex
	 * @generated
	 */
	EClass getTestObjectIndex();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex <em>Test Containment Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Containment Index</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex
	 * @generated
	 */
	EClass getTestContainmentIndex();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes <em>Test Object With Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Object With Indexes</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes
	 * @generated
	 */
	EClass getTestObjectWithIndexes();

	/**
	 * Returns the meta object for the reference list '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes#getIndexedReferences <em>Indexed References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Indexed References</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes#getIndexedReferences()
	 * @see #getTestObjectWithIndexes()
	 * @generated
	 */
	EReference getTestObjectWithIndexes_IndexedReferences();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes#getIndexedContents <em>Indexed Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexed Contents</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes#getIndexedContents()
	 * @see #getTestObjectWithIndexes()
	 * @generated
	 */
	EReference getTestObjectWithIndexes_IndexedContents();

	/**
	 * Returns the meta object for enum '{@link de.hub.emffrag.testmodels.frag.testmodel.TestEnum <em>Test Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Test Enum</em>'.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestEnum
	 * @generated
	 */
	EEnum getTestEnum();

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
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectImpl <em>Test Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectImpl
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestObject()
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

		/**
		 * The meta object literal for the '<em><b>Enum Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_OBJECT__ENUM_ATTRIBUTE = eINSTANCE.getTestObject_EnumAttribute();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectIndexImpl <em>Test Object Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectIndexImpl
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestObjectIndex()
		 * @generated
		 */
		EClass TEST_OBJECT_INDEX = eINSTANCE.getTestObjectIndex();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestContainmentIndexImpl <em>Test Containment Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestContainmentIndexImpl
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestContainmentIndex()
		 * @generated
		 */
		EClass TEST_CONTAINMENT_INDEX = eINSTANCE.getTestContainmentIndex();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectWithIndexesImpl <em>Test Object With Indexes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestObjectWithIndexesImpl
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestObjectWithIndexes()
		 * @generated
		 */
		EClass TEST_OBJECT_WITH_INDEXES = eINSTANCE.getTestObjectWithIndexes();

		/**
		 * The meta object literal for the '<em><b>Indexed References</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES = eINSTANCE.getTestObjectWithIndexes_IndexedReferences();

		/**
		 * The meta object literal for the '<em><b>Indexed Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS = eINSTANCE.getTestObjectWithIndexes_IndexedContents();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.testmodels.frag.testmodel.TestEnum <em>Test Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.testmodels.frag.testmodel.TestEnum
		 * @see de.hub.emffrag.testmodels.frag.testmodel.impl.TestModelPackageImpl#getTestEnum()
		 * @generated
		 */
		EEnum TEST_ENUM = eINSTANCE.getTestEnum();

	}

} //TestModelPackage
