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

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.KeyType;
import de.hub.emffrag.fragmentation.AbstractValueSetSemantics;
import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.FragmentedModel;
import de.hub.emffrag.fragmentation.IndexedValueSetSemantics;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.model.emffrag.IndexedMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indexed Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.model.emffrag.impl.IndexedMapImpl#getFirstKey <em>First Key</em>}</li>
 *   <li>{@link de.hub.emffrag.model.emffrag.impl.IndexedMapImpl#getLastKey <em>Last Key</em>}</li>
 *   <li>{@link de.hub.emffrag.model.emffrag.impl.IndexedMapImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link de.hub.emffrag.model.emffrag.impl.IndexedMapImpl#getKeytype <em>Keytype</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndexedMapImpl<K, V> extends FObjectImpl implements IndexedMap<K, V> {
	
	protected DataIndex<K> index = null;	
	private AbstractValueSetSemantics<K> semantics;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexedMapImpl() {
		super();
	}
	
	private void init() {
		if (semantics == null) {
			Resource eResource = fInternalObject().eResource();
			if (eResource instanceof Fragment) {
				Fragment fragment = (Fragment)eResource;
				FragmentedModel model = fragment.getFragmentedModel();
				DataStore dataStore = model.getDataStore();
								
				String prefix = getPrefix();
				if (prefix == null) {
					String id = fInternalObject().getId(true);
					if (FInternalObjectImpl.isPreliminary(id)) {
						throw new RuntimeException("Inxed classes must be part of a fragmented model before they can be used.");
					}
					prefix = FragmentedModel.INDEX_CLASSES_PREFIX + "_" + id;
					setPrefix(prefix);
				}
				index = new DataIndex<K>(dataStore, prefix, getKeytype());
				semantics = createValueSet(model, index);
			} else {
				throw new IllegalStateException("Map operations can only be used, if the map is part of a fragmented model.");
			}
		}
	}
	
	protected AbstractValueSetSemantics<K> createValueSet(FragmentedModel model, DataIndex<K> index) {
		return new IndexedValueSetSemantics<K>(model, index){};
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EmfFragPackage.Literals.INDEXED_MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public K getFirstKey() {
		init();
		return index.first();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSetFirstKey() {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public K getLastKey() {
		init();
		return index.last();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetLastKey() {
		return eIsSet(EmfFragPackage.Literals.INDEXED_MAP__LAST_KEY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrefix() {
		return (String)eGet(EmfFragPackage.Literals.INDEXED_MAP__PREFIX, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(String newPrefix) {
		eSet(EmfFragPackage.Literals.INDEXED_MAP__PREFIX, newPrefix);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public KeyType<K> getKeytype() {
		throw new UnsupportedOperationException("This needs to be overwritten");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetKeytype() {
		return eIsSet(EmfFragPackage.Literals.INDEXED_MAP__KEYTYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Iterator<V> iterator() {
		return iterator(index.first(), index.last());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Iterator<V> iterator(K from, K to) {
		init();
		final Iterator<FInternalObjectImpl> sourceIterator = semantics.iterator(from, to);
		return new Iterator<V>() {

			@Override
			public boolean hasNext() {
				return sourceIterator.hasNext();
			}

			@SuppressWarnings("unchecked")
			@Override
			public V next() {
				return (V)sourceIterator.next().getUserObject();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}			
		};
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public V exact(K key) {
		init();
		FInternalObjectImpl result = semantics.getValueForExactKey(key);
		return result == null ? null : (V)result.getUserObject();			
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public V next(K key) {
		init();
		key = index.exactOrNext(key);
		return key == null ? null : (V)semantics.getValueForExactKey(key).getUserObject();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void put(K key, V value) {
		init();
		if (value instanceof FObjectImpl) {
			semantics.setValueForKey(key, ((FObjectImpl)value).fInternalObject());
		} else {
			throw new IllegalArgumentException("Non fragmented model objects are not supported as map values.");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public V remove(K key) {
		init();
		FInternalObjectImpl result = semantics.getValueForExactKey(key);
		if (result != null) {
			semantics.removeValueForKey(key, result);
		}
		return (V)result.getUserObject();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public byte[] keyToBytes(K key) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public K bytesToKey(byte[] bytes) {
		throw new UnsupportedOperationException();
	}

} //IndexedMapImpl
