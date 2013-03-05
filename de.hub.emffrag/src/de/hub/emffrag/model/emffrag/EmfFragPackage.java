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
package de.hub.emffrag.model.emffrag;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see de.hub.emffrag.model.emffrag.EmfFragFactory
 * @model kind="package"
 * @generated
 */
public interface EmfFragPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "emffrag";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://de.hub.emffrag/emffrag";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ef";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EmfFragPackage eINSTANCE = de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.hub.emffrag.model.emffrag.impl.IndexedMapImpl <em>Indexed Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.model.emffrag.impl.IndexedMapImpl
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIndexedMap()
	 * @generated
	 */
	int INDEXED_MAP = 0;

	/**
	 * The feature id for the '<em><b>First Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_MAP__FIRST_KEY = 0;

	/**
	 * The feature id for the '<em><b>Last Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_MAP__LAST_KEY = 1;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_MAP__PREFIX = 2;

	/**
	 * The feature id for the '<em><b>Keytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_MAP__KEYTYPE = 3;

	/**
	 * The number of structural features of the '<em>Indexed Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_MAP_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.model.emffrag.impl.IndexedListImpl <em>Indexed List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.model.emffrag.impl.IndexedListImpl
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIndexedList()
	 * @generated
	 */
	int INDEXED_LIST = 1;

	/**
	 * The feature id for the '<em><b>First Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_LIST__FIRST_KEY = INDEXED_MAP__FIRST_KEY;

	/**
	 * The feature id for the '<em><b>Last Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_LIST__LAST_KEY = INDEXED_MAP__LAST_KEY;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_LIST__PREFIX = INDEXED_MAP__PREFIX;

	/**
	 * The feature id for the '<em><b>Keytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_LIST__KEYTYPE = INDEXED_MAP__KEYTYPE;

	/**
	 * The number of structural features of the '<em>Indexed List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_LIST_FEATURE_COUNT = INDEXED_MAP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.model.emffrag.impl.ContainmentIndexedMapImpl <em>Containment Indexed Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.model.emffrag.impl.ContainmentIndexedMapImpl
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getContainmentIndexedMap()
	 * @generated
	 */
	int CONTAINMENT_INDEXED_MAP = 2;

	/**
	 * The feature id for the '<em><b>First Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINMENT_INDEXED_MAP__FIRST_KEY = INDEXED_MAP__FIRST_KEY;

	/**
	 * The feature id for the '<em><b>Last Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINMENT_INDEXED_MAP__LAST_KEY = INDEXED_MAP__LAST_KEY;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINMENT_INDEXED_MAP__PREFIX = INDEXED_MAP__PREFIX;

	/**
	 * The feature id for the '<em><b>Keytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINMENT_INDEXED_MAP__KEYTYPE = INDEXED_MAP__KEYTYPE;

	/**
	 * The number of structural features of the '<em>Containment Indexed Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINMENT_INDEXED_MAP_FEATURE_COUNT = INDEXED_MAP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.model.emffrag.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.model.emffrag.impl.RootImpl
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 3;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__CONTENTS = 0;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.model.emffrag.impl.ExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.model.emffrag.impl.ExtensionImpl
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getExtension()
	 * @generated
	 */
	int EXTENSION = 5;

	/**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.hub.emffrag.model.emffrag.impl.StatisticsImpl <em>Statistics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.model.emffrag.impl.StatisticsImpl
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getStatistics()
	 * @generated
	 */
	int STATISTICS = 4;

	/**
	 * The feature id for the '<em><b>Accessed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATISTICS__ACCESSED = EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Loaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATISTICS__LOADED = EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Statistics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATISTICS_FEATURE_COUNT = EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '<em>Iterator</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Iterator
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIterator()
	 * @generated
	 */
	int ITERATOR = 6;


	/**
	 * The meta object id for the '<em>Key Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.datastore.KeyType
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getKeyType()
	 * @generated
	 */
	int KEY_TYPE = 7;


	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.IndexedMap <em>Indexed Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indexed Map</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedMap
	 * @generated
	 */
	EClass getIndexedMap();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.model.emffrag.IndexedMap#getFirstKey <em>First Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Key</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedMap#getFirstKey()
	 * @see #getIndexedMap()
	 * @generated
	 */
	EAttribute getIndexedMap_FirstKey();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.model.emffrag.IndexedMap#getLastKey <em>Last Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Key</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedMap#getLastKey()
	 * @see #getIndexedMap()
	 * @generated
	 */
	EAttribute getIndexedMap_LastKey();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.model.emffrag.IndexedMap#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedMap#getPrefix()
	 * @see #getIndexedMap()
	 * @generated
	 */
	EAttribute getIndexedMap_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.model.emffrag.IndexedMap#getKeytype <em>Keytype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Keytype</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedMap#getKeytype()
	 * @see #getIndexedMap()
	 * @generated
	 */
	EAttribute getIndexedMap_Keytype();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.IndexedList <em>Indexed List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indexed List</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedList
	 * @generated
	 */
	EClass getIndexedList();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.ContainmentIndexedMap <em>Containment Indexed Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Containment Indexed Map</em>'.
	 * @see de.hub.emffrag.model.emffrag.ContainmentIndexedMap
	 * @generated
	 */
	EClass getContainmentIndexedMap();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see de.hub.emffrag.model.emffrag.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.model.emffrag.Root#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see de.hub.emffrag.model.emffrag.Root#getContents()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Contents();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.Statistics <em>Statistics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statistics</em>'.
	 * @see de.hub.emffrag.model.emffrag.Statistics
	 * @generated
	 */
	EClass getStatistics();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.model.emffrag.Statistics#getAccessed <em>Accessed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accessed</em>'.
	 * @see de.hub.emffrag.model.emffrag.Statistics#getAccessed()
	 * @see #getStatistics()
	 * @generated
	 */
	EAttribute getStatistics_Accessed();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.model.emffrag.Statistics#getLoaded <em>Loaded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loaded</em>'.
	 * @see de.hub.emffrag.model.emffrag.Statistics#getLoaded()
	 * @see #getStatistics()
	 * @generated
	 */
	EAttribute getStatistics_Loaded();

	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.Extension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see de.hub.emffrag.model.emffrag.Extension
	 * @generated
	 */
	EClass getExtension();

	/**
	 * Returns the meta object for data type '{@link java.util.Iterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Iterator</em>'.
	 * @see java.util.Iterator
	 * @model instanceClass="java.util.Iterator" serializeable="false" typeParameters="E"
	 * @generated
	 */
	EDataType getIterator();

	/**
	 * Returns the meta object for data type '{@link de.hub.emffrag.datastore.KeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Key Type</em>'.
	 * @see de.hub.emffrag.datastore.KeyType
	 * @model instanceClass="de.hub.emffrag.datastore.KeyType" serializeable="false" typeParameters="KT"
	 * @generated
	 */
	EDataType getKeyType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EmfFragFactory getEmfFragFactory();

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
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.IndexedMapImpl <em>Indexed Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.IndexedMapImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIndexedMap()
		 * @generated
		 */
		EClass INDEXED_MAP = eINSTANCE.getIndexedMap();

		/**
		 * The meta object literal for the '<em><b>First Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEXED_MAP__FIRST_KEY = eINSTANCE.getIndexedMap_FirstKey();

		/**
		 * The meta object literal for the '<em><b>Last Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEXED_MAP__LAST_KEY = eINSTANCE.getIndexedMap_LastKey();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEXED_MAP__PREFIX = eINSTANCE.getIndexedMap_Prefix();

		/**
		 * The meta object literal for the '<em><b>Keytype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEXED_MAP__KEYTYPE = eINSTANCE.getIndexedMap_Keytype();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.IndexedListImpl <em>Indexed List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.IndexedListImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIndexedList()
		 * @generated
		 */
		EClass INDEXED_LIST = eINSTANCE.getIndexedList();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.ContainmentIndexedMapImpl <em>Containment Indexed Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.ContainmentIndexedMapImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getContainmentIndexedMap()
		 * @generated
		 */
		EClass CONTAINMENT_INDEXED_MAP = eINSTANCE.getContainmentIndexedMap();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.RootImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__CONTENTS = eINSTANCE.getRoot_Contents();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.StatisticsImpl <em>Statistics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.StatisticsImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getStatistics()
		 * @generated
		 */
		EClass STATISTICS = eINSTANCE.getStatistics();

		/**
		 * The meta object literal for the '<em><b>Accessed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATISTICS__ACCESSED = eINSTANCE.getStatistics_Accessed();

		/**
		 * The meta object literal for the '<em><b>Loaded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATISTICS__LOADED = eINSTANCE.getStatistics_Loaded();

		/**
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.ExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.ExtensionImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getExtension()
		 * @generated
		 */
		EClass EXTENSION = eINSTANCE.getExtension();

		/**
		 * The meta object literal for the '<em>Iterator</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Iterator
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIterator()
		 * @generated
		 */
		EDataType ITERATOR = eINSTANCE.getIterator();

		/**
		 * The meta object literal for the '<em>Key Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.datastore.KeyType
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getKeyType()
		 * @generated
		 */
		EDataType KEY_TYPE = eINSTANCE.getKeyType();

	}

} //EmfFragPackage
