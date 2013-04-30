package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.resource.Resource;

public interface FGlobalEventListener {		
	public void onInternalObjectCreated(FInternalObjectImpl internalObject);
	public void onInternalObjectSetResource(FInternalObjectImpl internalObject, Resource resource);
	public void onUserObjectCreated(FInternalObjectImpl internalObject, FObjectImpl userObject);
	public void onUnloadInternalObject(FInternalObjectImpl internalObject);
	
	public static FGlobalEventListener emptyInstance = new FGlobalEvenListenerEmptyImpl();
	
	public class FGlobalEvenListenerEmptyImpl implements FGlobalEventListener {

		@Override
		public void onInternalObjectCreated(FInternalObjectImpl internalObject) {
			
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
		
	}
}
