package de.hub.emffrag2.unload;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public abstract class UnloadResourceSetImpl<UOD> extends ResourceSetImpl {

	private final Cache<UOD, UnloadEObjectImpl> userObjectCache;
	private boolean lock = false;

	public UnloadResourceSetImpl() {
		userObjectCache = CacheBuilder.newBuilder().weakValues().build();
	}

	protected abstract UOD createUserObjectId(UnloadBinaryResourceImpl uResource, UnloadEObjectImpl uObject);

	/**
	 * Never resolve a proxy, proxy objects resolve themselves via loading their
	 * resources on access directly, see {@link #uLoad(UnloadEObjectImpl)}.
	 */
	@Override
	public EObject getEObject(URI uri, boolean loadOnDemand) {
		return null;
	}

	/**
	 * EMF proxy resolution is disabled /{@link #getEObject(URI, boolean)}, this
	 * is used to load objects instead.
	 */
	public void uLoad(EObject eObject) {
		URI resourceURI = ((InternalEObject)eObject).eProxyURI().trimFragment();
		uLoad(resourceURI);
	}
	
	/**
	 * Allows to lock the resource set and prevent proxy resolution via {@link #uLoad(URI)}.
	 */
	public void uLock(boolean lock) {
		this.lock = lock;
	}
	
	public synchronized void uLoad(URI resourceURI) {
		if (!lock) {
			lock = true;
			getResource(resourceURI, true);
			lock = false;
		}
	}
	
	public synchronized void uSaveAndUnload(Resource resource) throws IOException {
		lock = true;
		resource.save(getLoadOptions());
		resource.unload();
		lock = false;
	}

	protected void registerUserObject(UnloadBinaryResourceImpl uResource, UnloadEObjectImpl uObject) {
		userObjectCache.put(createUserObjectId(uResource, uObject), uObject);
	}

	protected UnloadEObjectImpl getRegisteredUserObject(UnloadBinaryResourceImpl uResource, UnloadEObjectImpl uObject) {
		return userObjectCache.getIfPresent(createUserObjectId(uResource, uObject));
	}

	@Override
	public Resource createResource(URI uri, String contentType) {
		UnloadBinaryResourceImpl resource = new UnloadBinaryResourceImpl(uri);
		getResources().add(resource);
		return resource;
	}
}
