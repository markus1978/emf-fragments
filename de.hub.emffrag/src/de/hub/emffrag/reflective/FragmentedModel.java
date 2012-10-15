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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Throwables;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import de.hub.emffrag.kvstore.IKeyValueStore;
import de.hub.emffrag.util.ILogger;

public class FragmentedModel extends ResourceSetImpl {

	/**
	 * Creates a {@link FragmentedModel}. This is the only difference to
	 * {@link ResourceSetImpl}. Afterwards the model can be handled as usual
	 * with EMF.
	 * 
	 * @param keyValueStore
	 *            The {@link IKeyValueStore} that is used to persist the model.
	 * @param name
	 *            The name of the model. Refers to a table in the
	 *            {@link IKeyValueStore}.
	 * @param packages
	 *            All meta-model packages that are/will be used by the model.
	 */
	public FragmentedModel(final IKeyValueStore keyValueStore, String name, EPackage... packages) {
		Module module = new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(ILogger.class).toInstance(new ILogger() {
					@Override
					public void log(int level, String message, Throwable exception) {
						if (level <= ILogger.WARNING) {
							System.out.println("[" + level + "] " + message);
						}
					}
				});
				binder.bind(Integer.class).annotatedWith(Names.named(FragmentedModel.OPTION_WEAK_UNLOAD_CACHE_SIZE)).toInstance(0);
				binder.bind(IKeyValueStore.class).toInstance(keyValueStore);
				binder.bind(FragmentedModel.class).toInstance(FragmentedModel.this);
			}
		};

		// TODO FStoreImpl is build as a singleton, which is wrong with the
		// current design.
		FStoreImpl.INSTANCE = Guice.createInjector(module).getInstance(FStoreImpl.class);
		FStoreImpl.INSTANCE.initialize(Arrays.asList(packages), URI.createURI("hbase://localhost/" + name), false);
	}

	/**
	 * Creates a {@link FragmentedModel}. This is the only difference to
	 * {@link ResourceSetImpl}. Afterwards the model can be handled as usual
	 * with EMF.
	 * 
	 * @param module
	 *            The GUICE module that configures how emf-fragments is working.
	 *            Most importantly the module has to bind a concrete
	 *            {@link IKeyValueStore}.
	 * @param name
	 *            The name of the model. Refers to a table in the
	 *            {@link IKeyValueStore}.
	 * @param readOnly
	 *            True if the model does exist and is not changed. False
	 *            otherwise.
	 * @param packages
	 *            All meta-model packages that are/will be used by the model.
	 */
	public FragmentedModel(Module module, String name, boolean readOnly, EPackage... packages) {
		assert (module != null);

		// TODO FStoreImpl is build as a singleton, which is wrong with the
		// current design.
		FStoreImpl.INSTANCE = Guice.createInjector(module).getInstance(FStoreImpl.class);
		FStoreImpl.INSTANCE.initialize(Arrays.asList(packages), URI.createURI("hbase://localhost/" + name), readOnly);
	}

	/**
	 * Saves all loaded and cached fragments to the underlying
	 * {@link IKeyValueStore}. Unloaded fragments are stored independently when
	 * they are changed and unloaded.
	 */
	public void save() {
		// TODO Not supposed to be delegated. It should rather be moved here.
		FStoreImpl.INSTANCE.save();
	}

	/**
	 * Allows to add root-objects to the model's containment hierarchy. Only
	 * root-object have to be added; all children will be added automatically
	 * via composition.
	 * 
	 * @param content
	 *            The root-object to add.
	 */
	public void addContent(EObject content) {
		// TODO Not supposed to be delegated. It should rather be moved here.
		FStoreImpl.INSTANCE.addContent(content);
	}

	/**
	 * Returns a list of all root-objects in the model. This is the
	 * {@link Resource#getContents()} of the root {@link Fragment}.
	 * 
	 * @return A list of all root-objects in the model.
	 */
	public EList<EObject> getContents() {
		// TODO Not supposed to be delegated. It should rather be moved here.
		return FStoreImpl.INSTANCE.getContents();
	}

	public static final String OPTION_WEAK_UNLOAD_CACHE_SIZE = "cacheSize";

	@Inject
	private ILogger logger;
	@Inject
	@Named(FragmentedModel.OPTION_WEAK_UNLOAD_CACHE_SIZE)
	int CACHE_SIZE;

	static int gc_count = 0;
	private final WeakUnloadedFragmentsCache weakUnloadedFragmentsCache = new WeakUnloadedFragmentsCache();
	private final Statitics statistics = new Statitics();

	private class WeakUnloadedFragmentsCache {
		private final List<Fragment> fragments = new ArrayList<Fragment>(CACHE_SIZE);

		public void add(Fragment fragment) {
			fragments.add(fragment);
			if (fragments.size() > CACHE_SIZE) {
				Collections.sort(fragments, Fragment.lastWeakLoadComparator);
				for (int i = fragments.size() - 1; i >= CACHE_SIZE / 2; i--) {
					Fragment oldFragment = fragments.get(i);
					stronglyUnload(oldFragment);
					fragments.remove(i);
				}
			}
			statistics.maxWeakFragments = Math.max(fragments.size(), statistics.maxWeakFragments);
		}

		public void remove(Fragment fragment) {
			fragments.remove(fragment);
		}
	}

	int checkUnreferences = 0;

	public void unloadUnreferencedFragments(Fragment except, boolean force) {
		if (checkUnreferences++ % 100 == 0 || force) {
			List<Fragment> weaklyUnloads = new ArrayList<Fragment>();
			for (Resource resource : getResources()) {
				Fragment fragment = (Fragment) resource;
				if (!fragment.getCache().hasReferences() && fragment != except) {
					if (fragment.isLoaded() || fragment.isNew()) {
						weaklyUnloads.add(fragment);
					}
				}
			}
			for (Fragment fragment : weaklyUnloads) {
				weaklyUnload(fragment);
			}
		}

		// if (gc_count++ % 100 == 0) {
		// System.gc();
		// Runtime runtime = Runtime.getRuntime();
		// System.out.println(runtime.totalMemory() + " : " +
		// runtime.freeMemory());
		// }
	}

	public final class Statitics {
		public int weakLoads = 0;
		public int weakUnloads = 0;
		public int unloads = 0;
		public int loads = 0;
		public int maxActiveFragments = 0;
		public int maxWeakFragments = 0;
	}

	public Statitics getStatitics() {
		return this.statistics;
	}

	public void weaklyLoad(Fragment fragment) {
		logger.log(ILogger.DEBUG, "weakly load " + fragment.getURI().toString(), null);
		weakUnloadedFragmentsCache.remove(fragment);
		statistics.weakLoads++;
	}

	public void weaklyUnload(Fragment fragment) {
		logger.log(ILogger.DEBUG, "weakly unload " + fragment.getURI().toString(), null);
		fragment.weaklyUnload();
		weakUnloadedFragmentsCache.add(fragment);
		statistics.weakUnloads++;
	}

	public void stronglyUnload(Fragment fragment) {
		logger.log(ILogger.DEBUG, "strong unload " + fragment.getURI().toString(), null);
		if (!FStoreImpl.INSTANCE.isReadOnly()) {
			try {
				fragment.save(null);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
		fragment.unload();
		getResources().remove(fragment);
		statistics.unloads++;
	}

	private int count = 0;

	public void stronglyLoad(Fragment fragment) {
		if (count++ % 30 == 0) {
			unloadUnreferencedFragments(fragment, false);
		}
		statistics.maxActiveFragments = Math.max(getResources().size(), statistics.maxActiveFragments);
		statistics.loads++;
	}

	@Override
	protected void demandLoad(Resource resource) throws IOException {
		super.demandLoad(resource);
		stronglyLoad((Fragment) resource);
	}

	@Override
	public Resource createResource(URI uri, String contentType) {
		Resource resource = super.createResource(uri, contentType);
		stronglyLoad((Fragment) resource);
		return resource;
	}

}
