package de.hub.emffrag2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;

public class Fragment extends BinaryResourceImpl {

	private final long id;

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
		Fragmentation fragmentation = getFragmentation();
		if (fragmentation != null) {
			fragmentation.onRootFragmentChange(notification);
		}
	}

	@Override
	public boolean eNotificationRequired() {
		return fIsRoot() || super.eNotificationRequired();
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
	 */
	@Override
	protected void doUnload() {
		Iterator<EObject> allContents = getAllProperContents(unloadingContents);

		getErrors().clear();
		getWarnings().clear();

		while (allContents.hasNext()) {
			unloaded((InternalEObject) allContents.next());
		}

		// Removing reference, instead of clearing it. Will be re-instantiated
		// empty for next use.
		contents = null;
	}

	/**
	 * Perform a EMF-Fragments flavored unload via
	 * {@link FObjectImpl#eSetProxyURI(URI, Fragmentation)} not
	 * {@link InternalEObject#eSetProxyURI(URI)}. 
	 * 
	 * Does not clear the adaptors
	 * like {@link #unloaded(InternalEObject)}. This is to make clients unaware
	 * of automatic background unloaded/loading.
	 */
	@Override
	protected void unloaded(InternalEObject internalEObject) {
		if (!internalEObject.eIsProxy()) {
			((FObjectImpl) internalEObject).eSetProxyURI(uri.appendFragment(getURIFragment(internalEObject)),
					getFragmentation());
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
			EObjectInputStream eObjectInputStream = new MyEObjectInputStream(inputStream, options);
			eObjectInputStream.loadResource(this);
		}
	}

	// This is a copy from BinaryResourceImpl
	private static class InternalEObjectList extends BasicEList<InternalEObject> {
		private static final long serialVersionUID = 1L;

		public InternalEObject[] eObjects;

		public InternalEObjectList() {
			super(1000);
		}

		@Override
		protected Object[] newData(int capacity) {
			return eObjects = new InternalEObject[capacity];
		}

		@Override
		public final boolean add(InternalEObject object) {
			if (size == eObjects.length) {
				grow(size + 1);
			}
			eObjects[size++] = object;
			return true;
		}
	}

	/**
	 * Special {@link EObjectInputStream} that tries to retrieve a loaded object
	 * from a fragmentation user object cache before it creates a new object.
	 * Thus allowing to reuse still existing user object and preventing multiple
	 * EObject instances for the same logical object.
	 */
	private class MyEObjectInputStream extends EObjectInputStream {
		// This is a copy from BinaryResourceImpl. It is only used in
		// #loadEObject()
		private InternalEObjectList internalInternalEObjectList = new InternalEObjectList();

		// This is a copy from super class, except the marked pieces
		public MyEObjectInputStream(InputStream is, Map<?, ?> options) throws IOException {
			super(is, options);
		}

		@Override
		public InternalEObject loadEObject() throws IOException {
			int id = readCompressedInt();
			if (id == -1) {
				return null;
			} else {
				if (internalInternalEObjectList.size() <= id) {
					EClassData eClassData = readEClass();

					// We try to reuse former objects that are still on the heap
					InternalEObject internalEObject = getFragmentation().getRegisteredUserObject(Fragment.this, id);
					if (internalEObject == null) {
						internalEObject = (InternalEObject) eClassData.eFactory.create(eClassData.eClass);
					} else {
						((FObjectImpl)internalEObject).eSetProxyURI(null, null);
					}
					InternalEObject result = internalEObject;

					// Check if we have a "feature" representing the proxy
					// URI...
					//
					int featureID = readCompressedInt() - 1;
					if (featureID == -2) {
						// all proxies have to be EMF-Fragments flavoured
						// proxies
						//
						((FObjectImpl)internalEObject).eSetProxyURI(readURI(), getFragmentation());


						if (isEagerProxyResolution) {
							result = (InternalEObject) EcoreUtil.resolve(internalEObject, resource);
							internalInternalEObjectList.add(result);
							if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
								return result;
							}
						} else {
							internalInternalEObjectList.add(internalEObject);
							if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
								return internalEObject;
							}
						}

						// We must process the proxy attributes even for the
						// case of eager proxy resolution when we will
						// immediately discard the proxy.
						//
						featureID = readCompressedInt() - 1;
					} else {
						internalInternalEObjectList.add(internalEObject);
					}

					for (; featureID != -1; featureID = readCompressedInt() - 1) {
						EStructuralFeatureData eStructuralFeatureData = getEStructuralFeatureData(eClassData, featureID);
						loadFeatureValue(internalEObject, eStructuralFeatureData);
					}

					return result;
				} else {
					return internalInternalEObjectList.eObjects[id];
				}
			}
		}
	}

	/**
	 * Special {@link EObjectOutputStream} that registers the saved objects to
	 * the user object cache. We do this as part of the save, because only here
	 * it is ensured that each object already has an id. These ids are necessary
	 * as keys for the user object cache.
	 */
	public class MyEObjectOutputStream extends EObjectOutputStream {
		boolean isWritingCrossReferenceURI = false;
		FInternalObjectImpl currentObject = null;

		public MyEObjectOutputStream(OutputStream os, Map<?, ?> options) throws IOException {
			super(os, options);
		}

		@Override
		public void saveEObject(InternalEObject internalEObject, Check check) throws IOException {
			super.saveEObject(internalEObject, check);
			if (internalEObject != null) {
				Integer id = eObjectIDMap.get(internalEObject);
				getFragmentation().registerUserObject(Fragment.this, id, (FObjectImpl) internalEObject);
			}
		}
	}
}
