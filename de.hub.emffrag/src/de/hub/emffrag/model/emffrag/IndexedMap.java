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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.datastore.KeyType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexed Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.model.emffrag.IndexedMap#getFirstKey <em>First Key</em>}</li>
 *   <li>{@link de.hub.emffrag.model.emffrag.IndexedMap#getLastKey <em>Last Key</em>}</li>
 *   <li>{@link de.hub.emffrag.model.emffrag.IndexedMap#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link de.hub.emffrag.model.emffrag.IndexedMap#getKeytype <em>Keytype</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#getIndexedMap()
 * @model
 * @generated
 */
public interface IndexedMap<K, V> extends EObject {
	/**
	 * Returns the value of the '<em><b>First Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Key</em>' attribute.
	 * @see #isSetFirstKey()
	 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#getIndexedMap_FirstKey()
	 * @model unsettable="true" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	K getFirstKey();

	/**
	 * Returns whether the value of the '{@link de.hub.emffrag.model.emffrag.IndexedMap#getFirstKey <em>First Key</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>First Key</em>' attribute is set.
	 * @see #getFirstKey()
	 * @generated
	 */
	boolean isSetFirstKey();

	/**
	 * Returns the value of the '<em><b>Last Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Key</em>' attribute.
	 * @see #isSetLastKey()
	 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#getIndexedMap_LastKey()
	 * @model unsettable="true" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	K getLastKey();

	/**
	 * Returns whether the value of the '{@link de.hub.emffrag.model.emffrag.IndexedMap#getLastKey <em>Last Key</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Last Key</em>' attribute is set.
	 * @see #getLastKey()
	 * @generated
	 */
	boolean isSetLastKey();

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#getIndexedMap_Prefix()
	 * @model
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.model.emffrag.IndexedMap#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Keytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keytype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keytype</em>' attribute.
	 * @see #isSetKeytype()
	 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#getIndexedMap_Keytype()
	 * @model unsettable="true" dataType="de.hub.emffrag.model.emffrag.KeyType<K>" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	KeyType<K> getKeytype();

	/**
	 * Returns whether the value of the '{@link de.hub.emffrag.model.emffrag.IndexedMap#getKeytype <em>Keytype</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Keytype</em>' attribute is set.
	 * @see #getKeytype()
	 * @generated
	 */
	boolean isSetKeytype();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="de.hub.emffrag.model.emffrag.Iterator<V>"
	 * @generated
	 */
	Iterator<V> iterator();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="de.hub.emffrag.model.emffrag.Iterator<V>"
	 * @generated
	 */
	Iterator<V> iterator(K from, K to);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model keyRequired="true"
	 * @generated
	 */
	V exact(K key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model keyRequired="true"
	 * @generated
	 */
	V next(K key);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model keyRequired="true"
	 * @generated
	 */
	void put(K key, V value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	V remove(K key);

} // IndexedMap
