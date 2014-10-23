package de.hub.emffrag2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class Fragment extends UUIDBinaryResourceImpl {

	private final long id;

	// helper of #doUnload()
	private List<InternalEObject> contentToUnload = new ArrayList<InternalEObject>();

	private SortedMap<Integer, EObject> idToEObjectMap = new TreeMap<Integer, EObject>();
	private Map<EObject, Integer> eObjectToIDMap = new HashMap<EObject, Integer>();

	public Fragment(URI uri, long id) {
		super(uri);
		this.id = id;
	}

	public long fFragmentId() {
		return id;
	}

	public boolean fIsRoot() {
		return id == 0l;
	}

	@Override
	public void eNotify(Notification notification) {
		if (notification.getFeatureID(Resource.class) != RESOURCE__IS_MODIFIED) {
			setModified(true);			
			Fragmentation fragmentation = getFragmentation();
			if (fragmentation != null && fIsRoot()) {
				fragmentation.onRootFragmentChange(notification);
			}			
		}
	}

	@Override
	public boolean eNotificationRequired() {
		return true;
	}

	/**
	 * Overridden to ensure resourceSet is {@link Fragmentation}
	 */
	@Override
	public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
		if (resourceSet != null && !(resourceSet instanceof Fragmentation)) {
			throw new IllegalStateException("Fragments can only be used within Fragmentations, not other resource sets.");
		}
		return super.basicSetResourceSet(resourceSet, notifications);
	}

	/**
	 * Overridden to not clear the contents list, but loose its reference. This
	 * allows {@link TreeIterator}s to work, even when fragments in it become
	 * unloaded.
	 * 
	 * It also does not unload objects directly out of an iterator, since tree
	 * iterators depend on the children of their current element. TODO: write an
	 * iterator that collects an element's children before it hands out the
	 * element.
	 */
	@Override
	protected void doUnload() {
		Iterator<EObject> allContents = getAllProperContents(unloadingContents);

		getErrors().clear();
		getWarnings().clear();

		while (allContents.hasNext()) {
			// Do not unload directly out of the iterator, because it accesses
			// the
			// children of the current object.
			//
			contentToUnload.add((InternalEObject) allContents.next());
		}

		// Removing reference, instead of clearing it. Will be re-instantiated
		// empty for next use.
		//
		contents = null;

		// Unload the collected object to unload
		//
		for (InternalEObject internalEObject : contentToUnload) {
			unloaded(internalEObject);
		}
		contentToUnload.clear();

		clearIDs();
	}

	/**
	 * Perform a EMF-Fragments flavored unload via
	 * {@link FObjectImpl#eSetProxyURI(URI, Fragmentation)} not
	 * {@link InternalEObject#eSetProxyURI(URI)}.
	 * 
	 * Does not clear the adaptors like {@link #unloaded(InternalEObject)}. This
	 * is to make clients unaware of automatic background unloaded/loading.
	 */
	@Override
	protected void unloaded(InternalEObject internalEObject) {
		if (internalEObject instanceof FObjectImpl) {
			if (!internalEObject.eIsProxy()) {
				FObjectImpl fObject = (FObjectImpl) internalEObject;
				fObject.fUnload(getFragmentation());
				fObject.eSetProxyURI(uri.appendFragment(getURIFragment(internalEObject)));
			}
		} else {
			super.unloaded(internalEObject);
		}
	}

	/**
	 * @return the fragmentation that this fragment belongs to.
	 */
	public Fragmentation getFragmentation() {
		return (Fragmentation) resourceSet;
	}
	
	@Override
	protected InternalEObject createProxy(InternalEObject internalEObject, URI proxyURI, boolean nop)
			throws IOException {
		if (internalEObject instanceof FObjectImpl) {
			// first, we try to find a (still) existing user object proxy
			FObjectImpl proxyObject = getFragmentation().getRegisteredUserObject(proxyURI);
			// second, we try to find an already loaded object
			if (proxyObject == null) {
				proxyObject = (FObjectImpl)getFragmentation().getEObject(proxyURI, false);
			}
			// third, we turn the object into an fObject-like proxy (i.e. with load from fragmentation reference).
			if (proxyObject == null) {
				proxyObject = (FObjectImpl)internalEObject;
				proxyObject.eSetProxyURI(proxyURI);
				proxyObject.fSetFragmentationToLoadFrom(getFragmentation());
			}
			return proxyObject;			
		} else {
			return super.createProxy(internalEObject, uri, nop);
		}
	}
	
	@Override
	protected InternalEObject createEObject(InternalEObject internalEObject, int extrinsicID) {
		if (internalEObject instanceof FObjectImpl) {
			// We try to reuse former objects that are still on
			// the heap
			FObjectImpl fObject = getFragmentation().getRegisteredUserObject(Fragment.this, extrinsicID);
			if (fObject != null) {
				fObject.eSetProxyURI(null);
				fObject.fSetFragmentationToLoadFrom(null);
				return fObject;
			}							
		}			
		
		return super.createEObject(internalEObject, extrinsicID);
	}
		
	@Override
	protected void beforeSave(InternalEObject internalEObject) {
		if (internalEObject instanceof FObjectImpl) {
			FObjectImpl fObject = (FObjectImpl) internalEObject;
			if (fObject.eResource() == Fragment.this) {
				getFragmentation().registerUserObject(Fragment.this, getID(fObject, true), fObject);
			}
		}
	}
}
