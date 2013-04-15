package de.hub.emffrag.fragmentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.junit.Assert;

import com.google.common.base.Throwables;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.DataStoreURIHandler;
import de.hub.emffrag.datastore.KeyType;
import de.hub.emffrag.datastore.LongKeyType;
import de.hub.emffrag.model.emffrag.EmfFragFactory;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.model.emffrag.Root;
import de.hub.emffrag.util.EMFFragUtil;

public class FragmentedModel extends ResourceImpl {
	
	public static final String FRAGMENTS_INDEX_PREFIX = "f";
	public static final String ID_INDEX_PREFIX = "c";
	public static final String INDEX_CLASSES_PREFIX = "i";
	public static final String INDEX_FEATURES_PREFIX = "j";
	
	private int protect = 0;

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
	
//	private final LRUMap fragmentCache;
	private final FragmentsCache fragmentCache;
	
	private final DataStore dataStore;
	private final DataIndex<Long> fragmentIndex;
	private final IdIndex idIndex;
	private final Statistics statistics = new Statistics();
	private final Fragment rootFragment;

	public FragmentedModel(DataStore dataStore) {
		this(dataStore, -1);
	}

	FragmentedModel(DataStore dataStore, int cacheSize) {
		super(URI.createURI(dataStore.getURIString()));
		
		ReflectiveMetaModelRegistry.instance.registerUserMetaModel(EmfFragPackage.eINSTANCE);
		
		this.dataStore = dataStore;
		if (cacheSize == -1) {
			cacheSize = EmfFragActivator.instance.cacheSize;
		}
		
//		fragmentCache = new LRUMap(cacheSize) {
//			@Override
//			protected boolean removeLRU(LinkEntry entry) {
//				unloadFragment((Fragment)entry.getValue());
//				return true;
//			}			
//		};
		
		fragmentCache = new FragmentsCache(cacheSize) {	
			@Override
			protected void onEvict(Fragment fragment) {
				unloadFragment(fragment);
			}
		};				

		this.fragmentIndex = new DataIndex<Long>(dataStore, FRAGMENTS_INDEX_PREFIX, LongKeyType.instance);
		this.idIndex = new IdIndex(dataStore);

		resourceSet = createAndConfigureAResourceSet(dataStore);

		Long first = fragmentIndex.first();
		if (first == null) {
			rootFragment = createFragment(null, null);
		} else {
			rootFragment = (Fragment) resourceSet.getResource(fragmentIndex.getURI(first), true);
			for (EObject object: rootFragment.getContents()) {
				getContents().add(((FInternalObjectImpl)object).getUserObject());
			}
		}
	}
	
	void touch(Fragment fragment) {	
		fragmentCache.touch(fragment);
	}
	
	void protect(Fragment fragment) {
		protect++;
		fragmentCache.protect(fragment);
	}
	
	void unprotect(Fragment fragment) {
		protect--;
		fragmentCache.unprotect(fragment);
	}

	public class Statistics {
		private int creates = 0;
		private int loads = 0;
		private int unloads = 0;

		public int getCreates() {
			return creates;
		}

		public int getLoads() {
			return loads;
		}

		public int getUnloads() {
			return unloads;
		}
	}

	private void unloadFragment(Fragment fragment) {	
		if (fragment.isLoaded()) {
			statistics.unloads++;
			EmfFragActivator.instance.debug("Saving and unloading fragment: " + fragment.getURI().toString());
			try {
				fragment.save(options);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
			fragment.unload();
		} 
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
	private ResourceSet createAndConfigureAResourceSet(DataStore dataStore) {
		ResourceSet resourceSet = new ResourceSetImpl() {
			@Override
			public EObject getEObject(URI uri, boolean loadOnDemand) {
				if (uri.fragment() == null) {
					// The URI must be a ID URI
					EObject result = EmfFragActivator.instance.idSemantics.resolveURI(uri, FragmentedModel.this);
					return result;
				} else {
					return super.getEObject(uri, loadOnDemand);
				}
			}

			@Override
			protected void demandLoad(Resource resource) throws IOException {				
				EmfFragActivator.instance.debug("Demand loaded fragment: " + resource.getURI().toString());
				super.demandLoad(resource);
				statistics.loads++;
			}

		};

		resourceSet.getURIConverter().getURIHandlers().add(0, new DataStoreURIHandler(dataStore));
		resourceSet.getLoadOptions().putAll(options);
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap()
				.put(dataStore.getProtocol(), new XMIResourceFactoryImpl() {
					@Override
					public Resource createResource(URI uri) {
						EmfFragActivator.instance.debug("Created fragment: " + uri.toString());
						Fragment fragment = newFragment(uri, FragmentedModel.this);
						fragmentCache.put(uri, fragment);
						return fragment;
					}
				});
		resourceSet.setPackageRegistry(ReflectiveMetaModelRegistry.instance);

		return resourceSet;
	}
	
	/**
	 * Factory method for Fragments. Creates {@link XMIFragmentImpl} by default.
	 * Can be changed by subclasses.
	 */
	protected Fragment newFragment(URI uri, FragmentedModel model) {
		return new XMIFragmentImpl(uri, model);
	}

	/**
	 * We use a version of {@link ContentsEList} that handles inverses differently, because
	 * the resource does not need to be set on the user objects. They know that
	 * they belong to a {@link FragmentedModel} differently.
	 */
	@Override
	public EList<EObject> getContents() {
		if (contents == null) {
			contents = new ContentsEList<EObject>() {
				private static final long serialVersionUID = 1L;

				@Override
				public NotificationChain inverseAdd(EObject object, NotificationChain notifications) {										
					if (object instanceof FObjectImpl) {
						FInternalObjectImpl internalObject = ((FObjectImpl)object).fInternalObject();
						rootFragment.getContents().add(internalObject);
					} else {
						throw new IllegalArgumentException("Only FObjects can be added to fragmented models, generate you model code accordingly.");
					}
					FragmentedModel.this.attached(object);
					return notifications;
				}

				@Override
				public NotificationChain inverseRemove(EObject object, NotificationChain notifications) {
					if (FragmentedModel.this.isLoaded) {
				        FragmentedModel.this.detached(object);
				    }
					if (object instanceof FObjectImpl) {
						FInternalObjectImpl internalObject = ((FObjectImpl)object).fInternalObject();
						return internalObject.eSetResource(null, notifications);
					} else {
						throw new RuntimeException("Supposed unreachable.");
					}
				}																
			};
		}
		return contents;
	}

	public Root root() {
		if (getContents().isEmpty()) {
			Root root = EmfFragFactory.eINSTANCE.createRoot();
			getContents().add(root);
		}
		EObject eObject = getContents().get(0);
		if (eObject instanceof Root) {
			return (Root)eObject;
		} else {
			return null;
		}
	}

	ResourceSet getInternalResourceSet() {
		return resourceSet;
	}
	
	Fragment createFragment(URI fragmentURI, FInternalObjectImpl root) {
		if (fragmentURI == null) {
			fragmentURI = fragmentIndex.getURI(fragmentIndex.add());
		}
		Fragment newFragment = (Fragment) resourceSet.createResource(fragmentURI);		
		if (root != null) {
			newFragment.getContents().add(root);
		}
		statistics.creates++;
		return newFragment;
	}
	
	void deleteFragment(Fragment fragment) {
		try {
			fragment.delete(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	
	
	/**
	 * Fragment
	 */
	@Override
	public void load(Map<?, ?> options) throws IOException {
	
	}

	@Override
	public void delete(Map<?, ?> options) throws IOException {
		dataStore.drop();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void save(Map<?, ?> options) {
		if (options != null) {
			options.putAll((Map)FragmentedModel.options);
		} else {
			options = FragmentedModel.options;
		}
		for (Resource resource : resourceSet.getResources()) {
			try {
				resource.save(options);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
	}

	/**
	 * Resolved the given URI that denotes a DB entry that contains a serialized
	 * fragment.
	 * 
	 * @param uri
	 *            The containment URI to resolve.
	 * @return The resolved object.
	 */
	public EObject resolveObjectURI(URI uri) {
		return resourceSet.getEObject(uri, true);
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public DataStore getDataStore() {
		return dataStore;
	}

	IdIndex getIdIndex() {
		return idIndex;
	}
	
	public void printTelemetry() {
		int loadedObjects = 0;
		for (Resource resource: resourceSet.getResources()) {
			if (resource.isLoaded()) {
				loadedObjects += EMFFragUtil.getAllNonFragmentingContents((Fragment)resource).size();
			}
		}
		
		int loadedResources = 0;
		for (Resource resource: resourceSet.getResources()) {
			if (resource.isLoaded()) {
				loadedResources++;
			}
		}
		
		StringBuffer info = new StringBuffer();
		info.append("-- FragmentedModel telemetry ----------------------------------\n");
		info.append("Resources in ResourceSet: " + resourceSet.getResources().size() + "\n");
		info.append("Resources in Fragments Cache: " + fragmentCache.size() + "\n");
		info.append("Loaded Resources: " + loadedResources + "\n");
		info.append("Loaded objects: " + loadedObjects + "\n");
		info.append("UserObjectCache size: " + UserObjectsCache.instance.size() + "\n");
		info.append("Protect difference: " + protect + "\n");
		info.append("-- END --------------------------------------------------------" + "\n");
		
		System.out.println(info.toString());
	}

	/**
	 * Only for testing purposes, hence package visibility.
	 */
	Fragment getFragment(URI fragmentURI) {
		return (Fragment) getInternalResourceSet().getResource(fragmentURI, false);
	}

	private void assertStatistic(String name, int value, int min, int max) {
		if (max != -1) {
			Assert.assertTrue("Too many " + name + " " + value, value <= max);
		}
		if (min != -1) {
			Assert.assertTrue("Too few " + name + " " + value, value >= min);
		}
	}

	void assertNumberOfLoadedFragments(int min, int max) {
		assertStatistic("loaded fragments", getInternalResourceSet().getResources().size(), min, max);
	}

	void assertNumberOfLoadedFragments(int size) {
		assertNumberOfLoadedFragments(size, size);
	}

	void assertStatistics(int minLoaded, int maxLoaded, int minLoads, int maxLoads, int minUnloads, int maxUnloads,
			int minCreates, int maxCreates) {
		assertStatistic("loaded fragments", getInternalResourceSet().getResources().size(), minLoaded, maxLoaded);
		assertStatistic("loads", statistics.getLoads(), minLoads, maxLoads);
		assertStatistic("unloads", statistics.getUnloads(), minUnloads, maxUnloads);
		assertStatistic("creates", statistics.getCreates(), minCreates, maxCreates);
	}

	private void assertIndex(DataIndex<Long> index, String name, long first, long last) {
		if (first == -1) {
			Assert.assertEquals("Wrong first key for " + name + ".", null, index.first());
		} else {
			Assert.assertEquals("Wrong first key for " + name + ".", (Long) first, index.first());
		}
		if (last == -1) {
			Assert.assertEquals("Wrong last key for " + name + ".", null, index.last());
		} else {
			Assert.assertEquals("Wrong last key for " + name + ".", (Long) last, index.last());
		}
	}

	void assertMaxFragmentsIndexSize(long max) {
		Assert.assertTrue("Fragments index too large.", fragmentIndex.last() <= max);
	}

	void assertFragmentsIndex(long first, long last) {
		assertIndex(fragmentIndex, "fragments index", first, last);
	}

	void assertIdIndex(long first, long last) {
		assertIndex(idIndex, "id index", first, last);
	}

	void assertValueSetIndex(EObject owner, EStructuralFeature feature, long min, long max) {
		new DataIndex<Long>(dataStore, FValueSetList.createPrefix(((FObjectImpl) owner).fInternalObject(), feature),
				LongKeyType.instance);
	}

	<KT> void assertIndexClassIndex(EObject owner, KT min, KT max, KeyType<KT> keyType) {

	}
}
