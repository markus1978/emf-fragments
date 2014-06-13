package de.hub.emffrag2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.eclipse.emf.ecore.resource.URIConverter;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;

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

	/**
	 * Overridden to use a the custom object output stream
	 * {@link MyEObjectOutputStream}.
	 */
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		// This is a copy of the super method, except for the market pieces
		// of code
		if (outputStream instanceof URIConverter.Saveable) {
			((URIConverter.Saveable) outputStream).saveResource(this);
		} else {
			boolean buffer = !(outputStream instanceof BufferedOutputStream);
			if (buffer) {
				int bufferCapacity = getBufferCapacity(options);
				if (bufferCapacity > 0) {
					outputStream = new BufferedOutputStream(outputStream, bufferCapacity);
				} else {
					buffer = false;
				}
			}

			try {
				// We use our own EObjectOutputStream
				// EObjectOutputStream eObjectOutputStream = new
				// EObjectOutputStream(outputStream, options);
				//
				EObjectOutputStream eObjectOutputStream = new MyEObjectOutputStream(outputStream, options);
				eObjectOutputStream.saveResource(this);
			} finally {
				if (buffer) {
					outputStream.flush();
				}
			}
		}
	}

	/**
	 * Overridden to use the custom object input stream
	 * {@link MyEObjectInputStream}.
	 */
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		idToEObjectMap.clear();
		eObjectToIDMap.clear();
		// This is a copy of the super method, except for the market pieces
		// of code
		if (inputStream instanceof URIConverter.Loadable) {
			((URIConverter.Loadable) inputStream).loadResource(this);
		} else {
			if (!(inputStream instanceof BufferedInputStream)) {
				int bufferCapacity = getBufferCapacity(options);
				if (bufferCapacity > 0) {
					inputStream = new BufferedInputStream(inputStream, bufferCapacity);
				}
			}

			// We use our own EObjectOutputStream
			// EObjectInputStream eObjectInputStream = new
			// EObjectInputStream(inputStream, options);
			//
			EObjectInputStream eObjectInputStream = new MyEObjectInputStream(inputStream, options);
			eObjectInputStream.loadResource(this);
		}

	}

	/**
	 * Special {@link EObjectInputStream} that tries to retrieve a loaded object
	 * from a fragmentation user object cache before it creates a new object.
	 * Thus allowing to reuse still existing user object and preventing multiple
	 * EObject instances for the same logical object.
	 * 
	 * It also loads IDs into the extrinsic ID implementation of
	 * {@link Fragment}.
	 * 
	 * TODO cache streams
	 */
	private class MyEObjectInputStream extends UUIDEObjectInputStream {

		public MyEObjectInputStream(InputStream is, Map<?, ?> options) throws IOException {
			super(is, options);
		}

		@Override
		protected InternalEObject createProxy(InternalEObject internalEObject, EClassData eClassData, URI proxyURI)
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
				return super.createProxy(internalEObject, eClassData, uri);
			}
		}

		@Override
		protected InternalEObject createEObject(InternalEObject internalEObject, EClassData eClassData, int extrinsicID) {
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
			
			return super.createEObject(internalEObject, eClassData, extrinsicID);
		}
	}

	/**
	 * Implementation that disables access notifications during save and registers 
	 * saved objects that belong to this resource (i.e. non proxy objects) with 
	 * the fragmentation's user object cache.
	 */
	public class MyEObjectOutputStream extends UUIDEObjectOutputStream {
		boolean isWritingCrossReferenceURI = false;
		FInternalObjectImpl currentObject = null;

		public MyEObjectOutputStream(OutputStream os, Map<?, ?> options) throws IOException {
			super(os, options);
		}

		public void saveEObject(InternalEObject internalEObject, Check check) throws IOException {
			if (internalEObject instanceof FObjectImpl) {
				FObjectImpl fObject = (FObjectImpl) internalEObject;
				if (fObject.eResource() == Fragment.this) {
					getFragmentation().registerUserObject(Fragment.this, getID(fObject, true), fObject);
				}
			}
			super.saveEObject(internalEObject, check);	
		}			
	}
}
