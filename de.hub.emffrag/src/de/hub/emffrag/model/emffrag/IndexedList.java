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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexed List</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#getIndexedList()
 * @model superTypes="de.hub.emffrag.model.emffrag.IndexedMap<org.eclipse.emf.ecore.EIntegerObject, V>"
 * @generated
 */
public interface IndexedList<V> extends IndexedMap<Integer, V> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void add(V value);

} // IndexedList
