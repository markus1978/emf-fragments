package de.hub.emffrag2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;

public class Fragment extends BinaryResourceImpl {

	private Fragmentation fragmentation = null;
	private final String id;
	
	public Fragment(URI uri) {
		super(uri);
		id = uri.segment(uri.segmentCount()-1);
	}
	
	public String fFragmentId() {
		return id;
	}

	@Override
	public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
		if (resourceSet instanceof Fragmentation) {
			fragmentation = (Fragmentation)resourceSet;
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
	 * Perform a true unload after regular EMF {@link #unloaded(InternalEObject)}.
	 */
	@Override
	protected void unloaded(InternalEObject internalEObject) {
		((FObjectImpl)internalEObject).fTrueUnload(getFragmentation());
		super.unloaded(internalEObject);
	}

	public Fragmentation getFragmentation() {
		if (fragmentation == null) {
			ResourceSet resourceSet = getResourceSet();
			if (resourceSet != null) {
				if (resourceSet instanceof Fragmentation) {
					fragmentation = (Fragmentation)resourceSet;
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

	private class MyEObjectInputStream extends EObjectInputStream {

		public MyEObjectInputStream(InputStream is, Map<?, ?> options) throws IOException {
			super(is, options);
		}

		@Override
		public InternalEObject loadEObject() throws IOException {
			InternalEObject loadEObject = super.loadEObject();
			return loadEObject;
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
				getFragmentation().registerUserObject(Fragment.this, id, (FObjectImpl)internalEObject);
			}
		}
	}
}
