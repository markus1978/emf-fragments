package de.hub.emffrag.util;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.fragmentation.FGlobalEventListener;
import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.UserObjectsCache;

public class MemoryGlobalEvenListener implements FGlobalEventListener {

	private final Cache<FInternalObjectImpl, FInternalObjectImpl> allInstances = CacheBuilder.newBuilder().weakKeys().weakValues().build();
	private final Cache<FInternalObjectImpl, FInternalObjectImpl> definitelyUnloaded = CacheBuilder.newBuilder().weakKeys().weakValues().build();
	
	private long removedInstances = 0;
	
	@Override
	public void onInternalObjectCreated(FInternalObjectImpl internalObject) {
		allInstances.put(internalObject, internalObject);
	}

	@Override
	public void onInternalObjectSetResource(FInternalObjectImpl internalObject, Resource resource) {
		
	}

	@Override
	public void onUserObjectCreated(FInternalObjectImpl internalObject, FObjectImpl userObject) {
		
	}

	@Override
	public void onUnloadInternalObject(FInternalObjectImpl internalObject) {
		
	}
	
	public void removeUnessesaryObjects() {			
		allInstances.cleanUp();
		allInstances.size();
		
		for (FInternalObjectImpl instance: allInstances.asMap().keySet()) {	
			if (!UserObjectsCache.instance.hasUserObject(instance) && instance.eResource() == null) {
				allInstances.asMap().remove(instance);
				definitelyUnloaded.put(instance, instance);
				instance.trulyUnload();
				removedInstances++;
			}	
		}
		
		System.gc();
		
		EmfFragActivator.instance.info("Removed unnesseary objects: " + allInstances.size() + " instances and " + definitelyUnloaded.size() + "(" + removedInstances + ") removed instances.");
	}
}
