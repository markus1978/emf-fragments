package de.hub.emffrag2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;

public class Fragment extends BinaryResourceImpl {

	private Fragmentation fragmentation = null;
	private final String id;

	public Fragment(URI uri) {
		super(uri);
		id = uri.segment(uri.segmentCount() - 1);
	}

	public String fFragmentId() {
		return id;
	}

	@Override
	public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
		if (resourceSet instanceof Fragmentation) {
			fragmentation = (Fragmentation) resourceSet;
		} else {
			throw new IllegalStateException("Fragments can only be used within Fragmentations, not other resource sets.");
		}

		return super.basicSetResourceSet(resourceSet, notifications);
	}

	/**
	 * Save before regular EMF {@link #doUnload()}.
	 */
	@Override
	protected void doUnload() {
		try {
			save(resourceSet.getLoadOptions());
		} catch (IOException e) {
			// TODO
		}
		super.doUnload();
	}

	/**
	 * Perform a true unload after regular EMF
	 * {@link #unloaded(InternalEObject)}.
	 */
	@Override
	protected void unloaded(InternalEObject internalEObject) {
		((FObjectImpl) internalEObject).fTrueUnload(getFragmentation());
		super.unloaded(internalEObject);
	}

	public Fragmentation getFragmentation() {
		if (fragmentation == null) {
			ResourceSet resourceSet = getResourceSet();
			if (resourceSet != null) {
				if (resourceSet instanceof Fragmentation) {
					fragmentation = (Fragmentation) resourceSet;
				} else { // assertion
					throw new IllegalStateException("Fragments can only be contained in Fragmentations.");
				}
			}
		}
		return fragmentation;
	}

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
					InternalEObject internalEObject = getFragmentation().getUserObject(Fragment.this, id);
					if (internalEObject == null) {
						internalEObject = (InternalEObject) eClassData.eFactory.create(eClassData.eClass);
					}
					InternalEObject result = internalEObject;

					// Check if we have a "feature" representing the proxy
					// URI...
					//
					int featureID = readCompressedInt() - 1;
					if (featureID == -2) {
						internalEObject.eSetProxyURI(readURI());
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
