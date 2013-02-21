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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.KeyType;
import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.Fragment;
import de.hub.emffrag.fragmentation.FragmentedModel;
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
	protected FragmentedModel model = null;
	protected Fragment fragment = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexedMapImpl() {
		super();
	}
	
	private void init() {
		if (index == null) {
			Resource eResource = eResource();
			if (eResource instanceof Fragment) {
				fragment = (Fragment)eResource;
				model = fragment.getFragmentedModel();
				DataStore dataStore = model.getDataStore();
				
				String prefix = getPrefix();
				if (prefix == null) {
					prefix = model.createNextIndexPrefix();					
					setPrefix(prefix);
				}
				index = new DataIndex<K>(dataStore, prefix, getKeytype());
			} else {
				throw new IllegalStateException("Map operations can only be used, if the map is part of a fragmented model.");
			}
		}
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
		init();
		return new Iterator<V>() {
			K current = null;
			K next = getFirstKey();
			@Override
			public boolean hasNext() {
				if (next == null) {
					if (current != null) {
						next = index.next(current);
					} else {
						next = null;
					}
				}
				return next != null;
			}

			@SuppressWarnings("unchecked")
			@Override
			public V next() {
				if (next == null) {
					next = index.next(current);
				}
				EObject result = getValueForExactKey(next);
				current = next;
				next = null;
				return (V)result;			
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove on indexed iterators is not supported.");
			}			
		};
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Iterator<V> iterator(K from, K to) {
		init();
		final K firstIncl = index.exactOrNext(from);
		final K lastExcl = index.next(to);
		return new Iterator<V>() {
			
			K current = null;
			K next = firstIncl;
			@Override
			public boolean hasNext() {
				if (next == null) {
					if (current != null) {
						next = index.next(current);
					} else {
						next = null;
					}
				}
				return next != null && !next.equals(lastExcl);
			}

			@SuppressWarnings("unchecked")
			@Override
			public V next() {
				if (next == null) {
					next = index.next(current);
				}
				EObject result = getValueForExactKey(next);
				current = next;
				next = null;
				return (V)result;			
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove on indexed iterators is not supported.");
			}			
		};
	}

	protected EObject getValueForExactKey(K key) {
		String value = index.get(key);
		if (value == null) {
			return null;
		}
		URI objectUri = model.getExtrinsicIdIndex().getObjectUriForExtrinsicIdUri(URI.createURI(value));
		FInternalObjectImpl internalObject = (FInternalObjectImpl)model.resolveObjectURI(objectUri);
		return internalObject.getUserObject();
	}
	
	protected void setValueForKey(K key, V value) {
		FInternalObjectImpl internalObject = ((FObjectImpl)value).internalObject();
		internalObject.setIsCrossReferenced();
		Fragment fragment = internalObject.getFragment();
		URI uri = model.getExtrinsicIdIndex().createExtrinsicIdUri(fragment.getID(internalObject));
		index.set(key, uri.toString());
	}
	
	protected void removeValueForKey(K key, V value) {
		index.remove(key);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public V exact(K key) {
		init();
		return (V)getValueForExactKey(key);			
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
		return key == null ? null : (V)getValueForExactKey(key);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void put(K key, V value) {
		init();
		if (value instanceof FObjectImpl) {
			setValueForKey(key, value);
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
		V result = (V)getValueForExactKey(key);
		if (result != null) {
			removeValueForKey(key, result);
		}
		return result;
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
