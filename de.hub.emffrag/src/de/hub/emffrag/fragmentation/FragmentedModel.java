package de.hub.emffrag.fragmentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.DatatypeConverter;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.datastore.StringKeyType;

public class FragmentedModel {
	
	// TODO remove after testing (or introduce a resonable logging
	private void log(Fragment fragment, String message) {
// 		try { 
// 			System.out.println(LongKeyType.instance.deserialize(DatatypeConverter.parseBase64Binary(fragment.getURI().lastSegment()), 2) + " " + message);
// 		} catch (Exception e) {
// 			System.out.println("e " + e.getMessage() + "/" + message);
// 		}
	}

	private final static XMLParserPoolImpl xmlParserPool = new XMLParserPoolImpl(true);
	private final static Map<Object, Object> options = new HashMap<Object, Object>();
	static {
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.FALSE);
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_USE_PARSER_POOL, xmlParserPool);
		HashMap<String, Boolean> parserFeatures = new HashMap<String, Boolean>();
		parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		options.put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
	}

	private final ResourceSet resourceSet;
	private Fragment rootFragment;
	private final FragmentCache fragmentCache; // TODO handle fragment caching
												// issues
												// issues
	private final DataIndex<Long> fragmentIndex;
	private final DataIndex<String> crossReferenceIndex;
	private final URI rootFragmentKeyURI;

	static class CacheState {
		private final Fragment fragment;
		private int useKey;

		public CacheState(Fragment fragment) {
			super();
			this.fragment = fragment;
		}

		void registerUse(int useKey) {
			this.useKey = useKey;
		}
	}

	class FragmentCache {
		private List<CacheState> cacheStatesToRemove = new ArrayList<FragmentedModel.CacheState>();
		private final int cacheSize;
		private int currentUseKey = 0;
		private final TreeMap<CacheState, CacheState> cacheContents = new TreeMap<CacheState, CacheState>(
				new Comparator<CacheState>() {
					@Override
					public int compare(CacheState o1, CacheState o2) {
						return Integer.valueOf(o1.useKey).compareTo(o2.useKey);
					}
				});

		public FragmentCache(int cacheSize) {
			super();
			this.cacheSize = cacheSize;
		}

		void markAsUsed(Fragment fragment) {
			log(fragment, "mark as used");
			CacheState cacheState = fragment.getCacheState();
			// Attention! It is important to remove the cacheState first. The change of the
			// useKey, which is the basis for the order of cacheContents,
			// changes the "id" of the cacheState from the point of view of
			// cacheContents.
			cacheContents.remove(cacheState);
			cacheState.registerUse(currentUseKey++);
			cacheContents.put(cacheState, cacheState);
			purgeUnreferencedFragments();
		}

		private void purgeUnreferencedFragments() {
			int unreferencedFragments = 0;
			for (CacheState state : cacheContents.descendingKeySet()) {
				if (!state.fragment.getUserObjectsCache().hasReferences()) {
					if (unreferencedFragments >= cacheSize) {
						cacheStatesToRemove.add(state);
					} else {
						unreferencedFragments++;
					}
				}
			}

			for (CacheState state : cacheStatesToRemove) {
				unloadFragment(state.fragment);
				cacheContents.remove(state);
			}
			cacheStatesToRemove.clear();
		}

		void remove(Fragment oldFragment) {
			cacheContents.remove(oldFragment.getCacheState());
			purgeUnreferencedFragments();
		}
	}

	private void unloadFragment(Fragment fragment) {
		log(fragment, "unload");
		try {
			fragment.save(options);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		fragment.unload();
		resourceSet.getResources().remove(fragment);
	}

	/**
	 * The {@link ResourceSet}s used to store a {@link FragmentedModel} need to
	 * match specific characteristics. These are: It has to create
	 * {@link Fragment}s and {@link FInternalObjectImpl}s on loading resources;
	 * it has to use a {@link DataStoreURIHandler}; it needs to refer to the
	 * reflective version of meta models; it needs specific performance
	 * configuration loading and saving models. This methods creates a
	 * {@link ResourceSet} with the listed characteristics.
	 */
	private ResourceSet createAndConfigureAResourceSet(DataStore dataStore, EPackage... metaModels) {
		ResourceSet resourceSet = new ResourceSetImpl() {
			@Override
			public EObject getEObject(URI uri, boolean loadOnDemand) {
				Resource resource = getResource(uri.trimFragment(), loadOnDemand);
				if (resource != null) {
					if (uri.fragment() == null) {
						// The URI must reference a cross reference entry
						String objectURIString = crossReferenceIndex.get(crossReferenceIndex.getKeyFromURI(uri));
						EObject result = super.getEObject(URI.createURI(objectURIString), true);
						return result;
					} else {
						return resource.getEObject(uri.fragment());
					}
				} else {
					return null;
				}
			}
		};
		for (EPackage metaModel : metaModels) {
			EPackage reflectiveMetaModel = ReflectiveMetaModelRegistry.instance.registerRegularMetaModel(metaModel);
			resourceSet.getPackageRegistry().put(metaModel.getNsURI(), reflectiveMetaModel);
		}
		resourceSet.getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		resourceSet.getLoadOptions().putAll(options);
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("hbase", new XMIResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				Fragment fragment = new Fragment(uri);
				log(fragment, "create");
				fragment.setFragmentedModel(FragmentedModel.this);
				return fragment;
			}
		});

		return resourceSet;
	}

	public FragmentedModel(DataStore dataStore, URI rootFragmentKeyURI, EPackage... metaModel) {
		this(dataStore, rootFragmentKeyURI, 100, metaModel);
	}

	public FragmentedModel(DataStore dataStore, URI rootFragmentKeyURI, int cacheSize, EPackage... metaModel) {
		if (cacheSize < 1) {
			throw new IllegalArgumentException("A zero fragment cache is not allowed. Try a larger cache size.");
		}
		fragmentCache = new FragmentCache(cacheSize);
		this.fragmentIndex = new DataIndex<Long>(dataStore, "f", LongKeyType.instance);
		this.crossReferenceIndex = new DataIndex<String>(dataStore, "c", StringKeyType.instance);

		resourceSet = createAndConfigureAResourceSet(dataStore, metaModel);

		if (rootFragmentKeyURI == null) {
			rootFragment = crateFragment(null, null, null, null);
			this.rootFragmentKeyURI = rootFragment.getURI();
		} else {
			this.rootFragmentKeyURI = rootFragmentKeyURI;
			rootFragment = (Fragment) resourceSet.getResource(this.rootFragmentKeyURI, true);
		}
	}

	public EList<EObject> getRootContents() {
		EList<EObject> contents = rootFragment.getContents();
		EList<EObject> result = new BasicEList<EObject>(contents.size());
		for (EObject internalObject : contents) {
			result.add(rootFragment.getUserObjectsCache().getUserObject((FInternalObjectImpl) internalObject));
		}
		return result;
	}

	public void addContent(EObject eObject) {
		FInternalObjectImpl internalObject = ((FObjectImpl) eObject).internalObject();
		if (internalObject.eResource() == null) {
			UserObjectsCache.newUserObjectsCache.removeCachedUserObject(internalObject);
			rootFragment.getContents().add(internalObject);
			rootFragment.getUserObjectsCache().addUserObjectToCache(internalObject, (FObjectImpl) eObject);
		} else {
			// TODO allow to move objects from one fragmented model to another
			throw new UnsupportedOperationException();
		}
	}

	protected ResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * Creates a new fragment. That means it creates a resource (fragment), adds
	 * it to the resource set, and creates an appropriate entry in the
	 * persistence.
	 * 
	 * @param fragmentRoot
	 *            The object that is going to be the root of the new fragment.
	 *            It can be a part of an existing fragment or something from the
	 *            realm of new objects.
	 * @param fragmentRootUserObject
	 *            The user object of the fragment root. Can be null.
	 * @param containingObject
	 *            The object that contains the root. It can be null, if this
	 *            method is called for the root fragment.
	 * @param containmentFeature
	 *            Can be null (see containingObject)
	 */
	public Fragment crateFragment(FInternalObjectImpl fragmentRoot, FObjectImpl fragmentRootUserObject,
			InternalEObject containingObject, EStructuralFeature containmentFeature) {
		Fragment newFragment = (Fragment) resourceSet.createResource(createNewFragmentURI());
		newFragment.setFragmentedModel(this);

		if (fragmentRoot != null) {
			Resource oldResource = fragmentRoot.eResource();
			Fragment oldFragment = null;
			if (oldResource != null && oldResource instanceof Fragment) {
				oldFragment = (Fragment) oldResource;
			}

			newFragment.getContents().add(fragmentRoot);
			if (fragmentRootUserObject != null) {
				newFragment.getUserObjectsCache().addUserObjectToCache(fragmentRoot, fragmentRootUserObject);
			}
			if (oldFragment != null) {
				oldFragment.getUserObjectsCache().removeCachedUserObject(fragmentRoot);
			}
		}
		fragmentCache.markAsUsed(newFragment);
		return newFragment;
	}

	/**
	 * Creates a new fragment in persistence.
	 * 
	 * @return The URI for a new fragment.
	 */
	private URI createNewFragmentURI() {
		return fragmentIndex.getURI(fragmentIndex.add());
	}

	/**
	 * Removes the fragment of the given root. Removes the resource and the
	 * corresponding entry in persistence. This must not remove the fragment
	 * immediately, since the fragmentRoot is still attached to it.
	 * 
	 * @param fObjectImpl
	 *            The root of the fragment to delete.
	 */
	protected void removeFragment(FInternalObjectImpl fragmentRoot) {
		Fragment oldFragment = fragmentRoot.getFragment();
		oldFragment.setFragmentedModel(null);
		try {
			oldFragment.delete(options);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		fragmentCache.remove(oldFragment);
	}

	public void save() {
		for (Resource resource : resourceSet.getResources()) {
			try {
				resource.save(options);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
	}

	public URI getRootFragmentURI() {
		return rootFragmentKeyURI;
	}

	/**
	 * Changes (or add) the entry for a cross referenced object.
	 * 
	 * @param crossReferenceURI
	 *            An extrinsic that identifies an object within a resource, as
	 *            long as it is already given (null if the object is cross
	 *            referenced for the first time).
	 * @param object
	 *            The object that is cross references.
	 * @return The extrinsic ID that the cross referenced object should be
	 *         identified with from now on.
	 */
	public String updateCrossReference(String extrinsicID, FInternalObjectImpl object) {
		if (extrinsicID == null) {
			extrinsicID = crossReferenceIndex.add();
		}

		Resource resource = object.eResource();
		URI objectURI = resource.getURI().appendFragment(resource.getURIFragment(object));
		crossReferenceIndex.set(extrinsicID, objectURI.toString());
		return extrinsicID;
	}

	public URI getURIForExtrinsicCrossReferencedObjectID(String extrinsicID) {
		return crossReferenceIndex.getURI(extrinsicID);
	}

	void markAsUsed(Fragment fragment) {
		fragmentCache.markAsUsed(fragment);
	}

	/**
	 * Tries to remove unnecessary fragments from main memory.
	 */
	void purgeCache() {
		fragmentCache.purgeUnreferencedFragments();
	}

	int numberOfLoadedFragments() {
		return getResourceSet().getResources().size();
	}

	Fragment getFragment(URI fragmentURI) {
		return (Fragment) getResourceSet().getResource(fragmentURI, false);
	}
}
