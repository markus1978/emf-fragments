package de.hub.emffrag.util;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.hub.emffrag.fragmentation.FGlobalEventListener;
import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.UserObjectsCache;

public class TelemetryGlobalEvenListener implements FGlobalEventListener {

	private final Cache<FInternalObjectImpl, Entry> allInstances = CacheBuilder.newBuilder().weakKeys().build();
	
	public TelemetryGlobalEvenListener() { 
		
	}
	
	private class Entry {
		private boolean onceHadBeenInAResource = false;
		private boolean onceHadAUserObject = false;
		private boolean onceHadBeenUnloaded = false;
	}
	
	@Override
	public void onInternalObjectCreated(FInternalObjectImpl internalObject) {
		allInstances.put(internalObject, new Entry());
	}

	@Override
	public void onInternalObjectSetResource(FInternalObjectImpl internalObject, Resource resource) {
		allInstances.getIfPresent(internalObject).onceHadBeenInAResource = true;
	}

	@Override
	public void onUserObjectCreated(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		allInstances.getIfPresent(internalObject).onceHadAUserObject = true;
	}

	@Override
	public void onUnloadInternalObject(FInternalObjectImpl internalObject) {
		allInstances.getIfPresent(internalObject).onceHadBeenUnloaded = true;
	}
	
	public void printTelemetry() {
		try {
			System.gc();
			Thread.sleep(200);
			allInstances.cleanUp();
			allInstances.size();
			System.gc();
			Thread.sleep(200);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		Multimap<String, Resource> resources = HashMultimap.create();		
		
		long proxies = 0;
		long withResource = 0;
		long total = 0;
		long withUserObject = 0;
		long withOutResourceButProxy = 0;
		long withOutResourceAndWithoutProxy = 0;
		long withOutResourceProxyAndUserObject = 0;
		long withOutResourceProxyUserObjectAnWasInAResource = 0;
		
		for (FInternalObjectImpl instance: allInstances.asMap().keySet()) {
			if (instance.eIsProxy()) proxies++;				
			Resource eResource = instance.eResource();
			boolean isWithOutResourceAndProxy = false;
			if (eResource != null) {
				withResource++;
				resources.put(eResource.getURI().toString(), eResource);
			} else {
				if (instance.eIsProxy()) {
					withOutResourceButProxy++;
				} else {
					withOutResourceAndWithoutProxy++;
					isWithOutResourceAndProxy = true;
				}
			}
			if (UserObjectsCache.instance.hasUserObject(instance)) {
				withUserObject++;				
			} else {
				if (isWithOutResourceAndProxy) {
					withOutResourceProxyAndUserObject++;
					if (allInstances.getIfPresent(instance).onceHadBeenInAResource) {
						withOutResourceProxyUserObjectAnWasInAResource++;
					}
					instance.trulyUnload(); // what TODO else
				}
			}
			total++;
		}
		
		long totalNumberOfURIs = 0;
		long maxResourcesWithSameURI = 0;
		long totalNumberOfResources = 0;
		
		for (String uri: resources.keySet()) {
			totalNumberOfURIs++;
			int numberOfResources = resources.get(uri).size();
			maxResourcesWithSameURI = Math.max(maxResourcesWithSameURI, numberOfResources);
			totalNumberOfResources += numberOfResources;
		}
		
		System.out.println("------- FInternalObjectImpl instance set telemetry ----------------- ");
		System.out.println("total: " + total);
		System.out.println("proxies: " + proxies);
		System.out.println("withResource: " + withResource);
		System.out.println("withOutResourceButProxy: " + withOutResourceButProxy);
		System.out.println("withOutResourceAndWithoutProxy: " + withOutResourceAndWithoutProxy);
		System.out.println("withOutResourceProxyAndUserObject (i.e. definetely dead): " + withOutResourceProxyAndUserObject);
		System.out.println("withOutResourceProxyUserObjectAndNeverWasInAResource (i.e. never alive dead): " + (withOutResourceProxyAndUserObject-withOutResourceProxyUserObjectAnWasInAResource));
		System.out.println("withUserObject: " + withUserObject);	
		System.out.println("totalNumberOfURIs: " + totalNumberOfURIs);	
		System.out.println("maxResourcesWithSameURI: " + maxResourcesWithSameURI);	
		System.out.println("totalNumberOfResources: " + totalNumberOfResources);
		System.out.println("------- END telemetry ---------------------------------------------- ");
	}
}
