/*******************************************************************************
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.hub.emffrag.reflective;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;

import com.google.common.base.Throwables;
import com.google.inject.Inject;

import de.hub.emffrag.kvstore.IKeyValueStore;
import de.hub.emffrag.kvstore.KeyValueStoreURIHandler;
import de.hub.emffrag.util.ILogger;

/**
 * TODO This class is to big and has to much responsibilities.
 * 
 * TODO This class is a static variable based singleton. This is not a good idea
 * and does not allow to work with more then one fragmented model. It might be
 * possible to keep the singleton, if this class does not manage concrete packages, tables,
 * caches, fragments, and a specific {@link FragmentedModel} etc. anymore.
 * 
 */
public class FStoreImpl implements EStore {

	public static FStoreImpl INSTANCE = null;

	private boolean isReadOnly = false;

	@Inject
	private ILogger logger;
	@Inject
	private FragmentedModel resourceSet;
	@Inject
	private IKeyValueStore keyValueStore;
	private FObjectCache looseObjectsCache = new FObjectCache();
	
	// TODO why do we need to deal with table names?
	private String tableName = null;
	private IKeyValueStore.Table table = null;
	private IKeyValueStore.Table idTable = null;
	private Fragment rootFragment = null;

	private static XMLParserPoolImpl xmlParserPool = new XMLParserPoolImpl(true);
	private static Map<Object, Object> options = null;
	static {
		options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.FALSE);
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_USE_PARSER_POOL, xmlParserPool);
		HashMap<String, Boolean> parserFeatures = new HashMap<String, Boolean>();
		parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		options.put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
	}

	public void initialize(Collection<EPackage> packages, String tableName, boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
		packages = EcoreUtil.copyAll(packages);
		for (EPackage ePackage : packages) {
			resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
			ePackage.setEFactoryInstance(new org.eclipse.emf.ecore.impl.EFactoryImpl() {
				@Override
				public EObject create(EClass eClass) {
					return new FDynamicEObjectImpl(eClass);
				}
			});
		}
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("hbase", Fragment.Factory.INSTANCE);
		resourceSet.getURIConverter().getURIHandlers().add(0, new KeyValueStoreURIHandler(keyValueStore));
		resourceSet.getLoadOptions().putAll(options);

		this.tableName = tableName;
		table = keyValueStore.getTable(tableName, true);
		if (table.exists("0")) {
			rootFragment = (Fragment) resourceSet.getResource(createURI(0), true);
		} else {
			rootFragment = (Fragment) resourceSet.createResource(createURI(0));
			table.set("0", "");
		}
		idTable = keyValueStore.getTable(tableName + "_OIDs", true);
	}

	public void save() {
		for (Resource resource : resourceSet.getResources()) {
			try {
				resource.save(getSaveOptions());
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
		table.flush();
		idTable.flush();
	}

	public FragmentedModel getFragmentSet() {
		return resourceSet;
	}

	private Map<?, ?> getSaveOptions() {
		return null;
	}

	private URI createURI(String key) {
		return URI.createURI("hbase://localhost/" + tableName + "/" + key);
	}

	private URI createURI(long key) {
		return createURI(new Long(key).toString());
	}

	// TODO: this needs to be replaced since HBase sorts on byte arrays not
	// numbers
	// BIG TODO
	long largestKey = 0;

	private URI createNewFragmentURI() {
		if (largestKey == -1) {
			largestKey = new Long(table.getLargestKey());
		}
		String key = new Long(++largestKey).toString();
		if (!table.exists(key)) {
			table.set(key, "");
		}
		return createURI(key);
	}

	public EObject resolve(String oid) {
		String uriStr = idTable.get(oid);
		if (uriStr == null) {
			return null;
		}
		URI uri = URI.createURI(uriStr);
		return resourceSet.getEObject(uri, false);
	}

	// Same TODO than createNewFragmentURI
	long largestOIDKey = 0;

	public String updateOIDTable(String id, EObject eObject) {
		if (id == null) {
			if (largestOIDKey == -1) {
				largestOIDKey = new Long(idTable.getLargestKey());
			}
			id = new Long(++largestKey).toString();
		}
		Resource eResource = eObject.eResource();
		URI uri = eResource.getURI().appendFragment(eResource.getURIFragment(eObject));
		idTable.set(id, uri.toString());
		logger.log(ILogger.DEBUG, "update id table with " + id + "=" + uri.toString(), null);
		return id;
	}

	public Map<Object, Object> getLoadOptions() {
		return null;
	}

	public void delete() {
		EcoreUtil.delete(getContents().get(0), true);
	}

	public EList<EObject> getContents() {
		EList<EObject> contents = rootFragment.getContents();
		EList<EObject> result = new BasicEList<EObject>(contents.size());
		for (EObject fiObject : contents) {
			result.add((EObject) getEValue(fiObject, null));
		}
		return result;
	}

	public void addContent(EObject eObject) {
		EObject fiObject = getFIObject(eObject);
		if (fiObject.eResource() == null) {
			looseObjectsCache.removeEObject(fiObject, eObject);
			rootFragment.getContents().add(fiObject);
			rootFragment.getCache().addEObject(fiObject, eObject);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private EObject getFIObject(EObject eObject) {
		EObject fiObject = ((FObjectImpl) eObject).eGetFIObject();
		if (fiObject == null) {
			fiObject = instantiateAnObject(eObject.eClass(), resourceSet.getPackageRegistry());
			initializeEObject(eObject, fiObject);
		}
		if (fiObject.eIsProxy()) {
			logger.log(ILogger.DEBUG,
					"re-resolve for " + fiObject.eClass().getName() + ":" + ((InternalEObject) fiObject).eProxyURI(), null);
			fiObject = EcoreUtil.resolve(fiObject, resourceSet);
		}
		return fiObject;
	}

	private final Map<EStructuralFeature, EStructuralFeature> featureCache = new HashMap<EStructuralFeature, EStructuralFeature>();

	private EStructuralFeature getFIFeature(EStructuralFeature feature) {
		EStructuralFeature result = featureCache.get(feature);
		if (result == null) {
			EClass eClass = feature.getEContainingClass();
			EPackage fiPackage = resourceSet.getPackageRegistry().getEPackage(eClass.getEPackage().getNsURI());
			result = ((EClass) fiPackage.getEClassifier(eClass.getName())).getEStructuralFeature(feature.getFeatureID());
			featureCache.put(feature, result);
		}
		return result;
	}

	private void initializeEObject(EObject eObject, EObject fiObject) {
		Fragment fragment = (Fragment) fiObject.eResource();
		if (fragment == null) {
			looseObjectsCache.addEObject(fiObject, eObject);
		} else {
			fragment.getCache().addEObject(fiObject, eObject);
		}
		((FObjectImpl) eObject).eSetFIObject(fiObject);
	}

	public static EObject instantiateAnObject(EClass eClass, EPackage.Registry registry) {
		EPackage ePackage = registry.getEPackage(eClass.getEPackage().getNsURI());
		return ePackage.getEFactoryInstance().create((EClass) ePackage.getEClassifier(eClass.getName()));
	}

	private Object getFIValue(Object value, EStructuralFeature fiFeature) {
		if (fiFeature.getEType() instanceof EEnum) {
			return EcoreUtil.createFromString((EDataType) fiFeature.getEType(), value.toString());
		} else if (value != null && value instanceof FObjectImpl) {
			return getFIObject((EObject) value);
		} else {
			return value;
		}
	}

	private Object getEValue(Object value, EStructuralFeature eFeature) {
		if (eFeature != null && eFeature.getEType() instanceof EEnum) {
			return EcoreUtil.createFromString((EDataType) eFeature.getEType(), value.toString());
		} else if (value != null && value instanceof DynamicEObjectImpl) {
			EObject fiObject = (EObject) value;
			Fragment fragment = (Fragment) fiObject.eResource();
			EObject eObject = null;
			if (fragment != null) {
				eObject = fragment.getCache().getEObject(fiObject);
			} else {
				eObject = looseObjectsCache.getEObject(fiObject);
			}

			if (eObject == null) {
				eObject = instantiateAnObject(fiObject.eClass(), EPackage.Registry.INSTANCE);
				initializeEObject(eObject, fiObject);
			}
			return eObject;
		} else {
			return value;
		}
	}

	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		feature = getFIFeature(feature);
		EObject fiObject = getFIObject(object);
		if (feature.isMany()) {
			return getEValue(((EList<?>) fiObject.eGet(feature)).get(index), feature);
		} else {
			return getEValue(fiObject.eGet(feature), feature);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		feature = getFIFeature(feature);
		Object fiValue = getFIValue(value, feature);
		EObject fiObject = getFIObject(object);
		Object result = null;
		if (feature.isMany()) {
			result = getEValue(((EList) fiObject.eGet(feature)).set(index, fiValue), feature);
		} else {
			Object oldValue = getEValue(fiObject.eGet(feature), feature);
			fiObject.eSet(feature, fiValue);
			result = oldValue;
		}
		if (feature instanceof EReference && !((EReference) feature).isContainment()) {
			((FDynamicEObjectImpl) fiValue).setIsCrossReferenced();
		}
		return result;
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		if (!(getFIObject(object) instanceof DynamicEObjectImpl)) {
			System.out.println("##");
		}

		return getFIObject(object).eIsSet(feature);
	}

	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		getFIObject(object).eUnset(feature);
	}

	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		return ((EList<?>) getFIObject(object).eGet(feature)).isEmpty();
	}

	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		return ((EList<?>) getFIObject(object).eGet(feature)).size();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		feature = getFIFeature(feature);
		return ((EList) getFIObject(object).eGet(feature)).contains(getFIValue(value, feature));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		feature = getFIFeature(feature);
		return ((EList) getFIObject(object).eGet(feature)).indexOf(getFIValue(value, feature));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		feature = getFIFeature(feature);
		return ((EList) getFIObject(object).eGet(feature)).lastIndexOf(getFIValue(value, feature));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		feature = getFIFeature(feature);
		Object fiValue = getFIValue(value, feature);
		((EList) getFIObject(object).eGet(feature)).add(index, fiValue);
		if (feature instanceof EReference && !((EReference) feature).isContainment()) {
			((FDynamicEObjectImpl) fiValue).setIsCrossReferenced();
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		feature = getFIFeature(feature);
		return getEValue(((EList<?>) getFIObject(object).eGet(feature)).remove(index), feature);
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		feature = getFIFeature(feature);
		return getEValue(((EList<?>) getFIObject(object).eGet(feature)).move(targetIndex, sourceIndex), feature);
	}

	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		((EList<?>) getFIObject(object).eGet(feature)).clear();

	}

	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		Object[] array = ((EList<?>) getFIObject(object).eGet(feature)).toArray();
		Object[] result = new Object[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = getEValue(array[i], feature);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		feature = getFIFeature(feature);
		Object[] values = ((EList<?>) getFIObject(object).eGet(feature)).toArray(new Object[array.length]);
		Object[] result = new Object[array.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = getEValue(values[i], feature);
		}
		return (T[]) result;
	}

	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		feature = getFIFeature(feature);
		return getFIObject(object).eGet(feature).hashCode();
	}

	@Override
	public InternalEObject getContainer(InternalEObject object) {
		return (InternalEObject) getEValue(getFIObject(object).eContainer(), null);
	}

	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		EStructuralFeature fiFeature = getFIObject(object).eContainingFeature();
		EClass fiClass = fiFeature.getEContainingClass();
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(fiClass.getEPackage().getNsURI());
		return ((EClass) ePackage.getEClassifier(fiClass.getName())).getEStructuralFeature(fiFeature.getFeatureID());
	}

	@Override
	public EObject create(EClass eClass) {
		throw new UnsupportedOperationException();
	}

	// TODO move to FragmentSet
	public void fragment(EObject eObject, EObject eContainer, EStructuralFeature feature) {
		logger.log(ILogger.DEBUG, "fragment on object of class " + eObject.eClass().getName(), null);
		EObject fiObject = getFIObject(eObject);
		Fragment newFragment = (Fragment) resourceSet.createResource(createNewFragmentURI());
		Fragment oldFragment = (Fragment) fiObject.eResource();
		newFragment.getContents().add(fiObject);
		newFragment.getCache().addEObject(fiObject, eObject);
		if (oldFragment != null) {
			oldFragment.getCache().removeEObject(fiObject, eObject);
		}
		newFragment.setAsNew();
		getFragmentSet().unloadUnreferencedFragments(newFragment, false);
	}

	public void deFragment(EObject eObject) {
		logger.log(ILogger.DEBUG, "de-fragment on object of class " + eObject.eClass().getName(), null);
		EObject fiObject = getFIObject(eObject);
		Fragment oldFragment = (Fragment) fiObject.eResource();
		try {
			oldFragment.delete(null);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		looseObjectsCache.addEObject(fiObject, eObject);
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

}
