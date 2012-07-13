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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

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
	 * The number of structural features of the '<em>Indexed Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_MAP_FEATURE_COUNT = 0;

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
	 * The number of structural features of the '<em>Indexed List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEXED_LIST_FEATURE_COUNT = INDEXED_MAP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '<em>Iterator</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Iterator
	 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIterator()
	 * @generated
	 */
	int ITERATOR = 2;


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
	 * Returns the meta object for class '{@link de.hub.emffrag.model.emffrag.IndexedList <em>Indexed List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indexed List</em>'.
	 * @see de.hub.emffrag.model.emffrag.IndexedList
	 * @generated
	 */
	EClass getIndexedList();

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
		 * The meta object literal for the '{@link de.hub.emffrag.model.emffrag.impl.IndexedListImpl <em>Indexed List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.model.emffrag.impl.IndexedListImpl
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIndexedList()
		 * @generated
		 */
		EClass INDEXED_LIST = eINSTANCE.getIndexedList();

		/**
		 * The meta object literal for the '<em>Iterator</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Iterator
		 * @see de.hub.emffrag.model.emffrag.impl.EmfFragPackageImpl#getIterator()
		 * @generated
		 */
		EDataType ITERATOR = eINSTANCE.getIterator();

	}

} //EmfFragPackage
