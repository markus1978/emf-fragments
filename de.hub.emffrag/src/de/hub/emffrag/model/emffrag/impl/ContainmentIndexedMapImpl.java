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
package de.hub.emffrag.model.emffrag.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.model.emffrag.ContainmentIndexedMap;
import de.hub.emffrag.model.emffrag.EmfFragPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Containment Indexed Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ContainmentIndexedMapImpl<K, V> extends IndexedMapImpl<K, V> implements ContainmentIndexedMap<K, V> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainmentIndexedMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EmfFragPackage.Literals.CONTAINMENT_INDEXED_MAP;
	}

	@Override
	protected EObject getValueForExactKey(K key) {
		if (index.existis(key)) {
			return ((FInternalObjectImpl)model.resolveContainmentURI(index.getURI(key).appendFragment("/"))).getUserObject();
		} else {
			return null;
		}
	}
	
	@Override
	protected void setValueForKey(K key, V value) {
		// this creates an DB entry indirectly
		((FObjectImpl)value).updateContainment(this, -1, index.getURI(key));
	}

	@Override
	protected void removeValueForKey(K key, V value) {
		super.removeValueForKey(key, value);
		((FObjectImpl)value).updateContainment(null, -1, null);
	}

		
} //ContainmentIndexedMapImpl
