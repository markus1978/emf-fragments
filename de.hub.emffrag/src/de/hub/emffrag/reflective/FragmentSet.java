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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.hub.emffrag.util.ILogger;

public class FragmentSet extends ResourceSetImpl {
	
	public static final String OPTION_WEAK_UNLOAD_CACHE_SIZE = "cacheSize";
	
	@Inject private ILogger logger;
	@Inject @Named(FragmentSet.OPTION_WEAK_UNLOAD_CACHE_SIZE) int CACHE_SIZE;
	
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
			for (Resource resource: getResources()) {
				Fragment fragment = (Fragment)resource;
				if (!fragment.getCache().hasReferences() && fragment != except) {
					if (fragment.isLoaded() || fragment.isNew()) {										
						weaklyUnloads.add(fragment);
					}
				}
			}
			for (Fragment fragment: weaklyUnloads) {
				weaklyUnload(fragment);
			}
		}
		
//		if (gc_count++ % 100 == 0) {
//			System.gc();
//			Runtime runtime = Runtime.getRuntime();
//			System.out.println(runtime.totalMemory() + " : " + runtime.freeMemory());
//		}
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
		stronglyLoad((Fragment)resource);
	}

	@Override
	public Resource createResource(URI uri, String contentType) {
		Resource resource = super.createResource(uri, contentType);
		stronglyLoad((Fragment)resource);
		return resource;
	}
	
}