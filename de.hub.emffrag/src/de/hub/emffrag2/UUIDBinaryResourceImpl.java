package de.hub.emffrag2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Different to XMI/XMI Resources, EMF's BinaryResourceImpl does not support
 * UUID/extrinsicIDs. This extension to {@link BinaryResourceImpl} does.
 */
public class UUIDBinaryResourceImpl extends BinaryResourceImpl {

	private SortedMap<Integer, EObject> idToEObjectMap = new TreeMap<Integer, EObject>();
	private Map<EObject, Integer> eObjectToIDMap = new HashMap<EObject, Integer>();

	public UUIDBinaryResourceImpl(URI uri) {
		super(uri);
	}

	public Integer getID(EObject object) {
		return getID(object, false);
	}

	public Integer getID(EObject object, boolean createOnDemand) {
		Integer id = eObjectToIDMap.get(object);
		if (id == null && createOnDemand) {
			if (idToEObjectMap.isEmpty()) {
				id = 0;
			} else {
				id = idToEObjectMap.lastKey() + 1;
			}
			setID(object, id);
		}
		return id;
	}

	protected void setID(EObject object, Integer id) {
		if (idToEObjectMap.get(id) == null) {
			idToEObjectMap.put(id, object);
			eObjectToIDMap.put(object, id);
		} else {
			throw new IllegalStateException("Cannot double assign an ID.");
		}
	}

	protected void clearIDs() {
		eObjectToIDMap.clear();
		idToEObjectMap.clear();
	}

	@Override
	public String getURIFragment(EObject eObject) {
		return getID(eObject, true).toString();
	}

	@Override
	public EObject getEObject(String uriFragment) {
		int id = Integer.parseInt(uriFragment);
		return idToEObjectMap.get(id);
	}

	@Override
	protected void doUnload() {
		super.doUnload();
		clearIDs();
	}

	/**
	 * Overridden to use a the custom object output stream
	 * {@link UUIDEObjectOutputStream}.
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
				EObjectOutputStream eObjectOutputStream = new UUIDEObjectOutputStream(outputStream, options);
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
	 * {@link UUIDEObjectInputStream}.
	 */
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		// We will use the IDs that we later load
		//
		clearIDs();
		
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
			EObjectInputStream eObjectInputStream = new UUIDEObjectInputStream(inputStream, options);
			eObjectInputStream.loadResource(this);
		}

	}
	
	protected void beforeSave(InternalEObject internalEObject) {
		// empty
	}
	
	protected void afterLoad(InternalEObject internalEObject) {
		// emtpy
	}
	
	protected InternalEObject createProxy(InternalEObject internalEObject, URI uri, boolean isEagerProxyResolution) throws IOException {
		InternalEObject result = internalEObject;
		internalEObject.eSetProxyURI(uri);
		if (isEagerProxyResolution) {
			result = (InternalEObject) EcoreUtil.resolve(internalEObject, this);				
			return result;
		} else {
			return internalEObject;
		}					
	}
	
	protected InternalEObject createEObject(InternalEObject internalEObject, int extrinsicID) {
		return internalEObject;
	}

	protected class UUIDEObjectInputStream extends EObjectInputStream {
		// This is a replacement for
		// BinaryResourceImpl#internalInternalEObjectList, which is only used in
		// #loadEObject()
		private List<InternalEObject> internalInternalEObjectList = new ArrayList<InternalEObject>();

		public UUIDEObjectInputStream(InputStream is, Map<?, ?> options) throws IOException {
			super(is, options);
		}
		
		/**
		 * Overridden to provide a callback to post process all loaded objects.
		 */
		@Override
		public void loadResource(Resource resource) throws IOException {
			super.loadResource(resource);
			for (InternalEObject internalEObject : internalInternalEObjectList) {			
				afterLoad(internalEObject);
			}
		}

		@Override
		public InternalEObject loadEObject() throws IOException {
			int id = readCompressedInt();
			// Also process the extrinsic ID
			//
			int extrinsicID = readCompressedInt();
			if (id == -1) {
				return null;
			} else {
				if (internalInternalEObjectList.size() <= id) {
					EClassData eClassData = readEClass();
					InternalEObject internalEObject = (InternalEObject) eClassData.eFactory.create(eClassData.eClass);
					InternalEObject result = internalEObject;

					// Check if we have a "feature" representing the proxy
					// URI...
					//
					int featureID = readCompressedInt() - 1;
					if (featureID == -2) {
						result = createProxy(internalEObject, readURI(), isEagerProxyResolution);
						internalInternalEObjectList.add(result);
						
						if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
							return result;
						}						

						// We must process the proxy attributes even for the
						// case of eager proxy resolution when we will
						// immediately discard the proxy.
						//
						featureID = readCompressedInt() - 1;
					} else {
						result = internalEObject = createEObject(internalEObject, extrinsicID);
						internalInternalEObjectList.add(internalEObject);
					}

					// Use the extrinsicID if there is one
					//
					if (extrinsicID != -1) {
						setID(internalEObject, extrinsicID);
					}

					for (; featureID != -1; featureID = readCompressedInt() - 1) {
						EStructuralFeatureData eStructuralFeatureData = getEStructuralFeatureData(eClassData, featureID);
						loadFeatureValue(internalEObject, eStructuralFeatureData);
					}

					return result;
				} else {
					return internalInternalEObjectList.get(id);
				}
			}
		}
	}

	public class UUIDEObjectOutputStream extends EObjectOutputStream {
		public UUIDEObjectOutputStream(OutputStream os, Map<?, ?> options) throws IOException {
			super(os, options);
		}

		public void saveEObject(InternalEObject internalEObject, Check check) throws IOException {
			if (internalEObject == null) {
				writeCompressedInt(-1);
			} else {
				beforeSave(internalEObject);
				Integer id = eObjectIDMap.get(internalEObject);
				if (id == null) {
					int idValue = eObjectIDMap.size();
					writeCompressedInt(idValue);
					// Also save the an extrinsic id, if the object is actually
					// part of the resource and not a proxy.
					//
					Integer extrinsicID = -1;
					if (internalEObject.eResource() == UUIDBinaryResourceImpl.this) {
						extrinsicID = getID(internalEObject, true);
					}
					writeCompressedInt(extrinsicID);

					eObjectIDMap.put(internalEObject, idValue);
					EClass eClass = internalEObject.eClass();
					EClassData eClassData = writeEClass(eClass);
					boolean checkIsTransientProxy = false;
					switch (check) {
					case DIRECT_RESOURCE: {
						Internal resource = internalEObject.eDirectResource();
						if (resource != null) {
							writeCompressedInt(-1);
							writeURI(resource.getURI(), resource.getURIFragment(internalEObject));
							if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
								return;
							}
							checkIsTransientProxy = true;
						} else if ((internalEObject instanceof FObject) ? ((FObject)internalEObject).fIsProxy() : internalEObject.eIsProxy()) {
							writeCompressedInt(-1);
							writeURI(internalEObject.eProxyURI());
							if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
								return;
							}
							checkIsTransientProxy = true;
						}
						break;
					}
					case RESOURCE: {
						Resource resource = internalEObject.eResource();
						if (resource != this.resource && resource != null) {
							writeCompressedInt(-1);
							writeURI(resource.getURI(), resource.getURIFragment(internalEObject));
							if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
								return;
							}
							checkIsTransientProxy = true;
						} else if ((internalEObject instanceof FObject) ? ((FObject)internalEObject).fIsProxy() : internalEObject.eIsProxy()) {
							writeCompressedInt(-1);
							writeURI(internalEObject.eProxyURI());
							if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
								return;
							}
							checkIsTransientProxy = true;
						}
						break;
					}
					case NOTHING:
					case CONTAINER: {
						break;
					}
					}
					EStructuralFeatureData[] eStructuralFeatureData = eClassData.eStructuralFeatureData;
					for (int i = 0, length = eStructuralFeatureData.length; i < length; ++i) {
						EStructuralFeatureData structuralFeatureData = eStructuralFeatureData[i];
						if (!structuralFeatureData.isTransient
								&& (structuralFeatureData.kind != FeatureKind.EOBJECT_CONTAINER_PROXY_RESOLVING || check == Check.CONTAINER)
								&& (!checkIsTransientProxy || !structuralFeatureData.isProxyTransient)) {
							saveFeatureValue(internalEObject, i, structuralFeatureData);
						}
					}
					writeCompressedInt(0);
				} else {
					writeCompressedInt(id);
					// Store an empty extrinsicID for objects that are already
					// stored.
					//
					writeCompressedInt(-1);
				}
			}
			
			
		}
		
		@Override
		protected void saveFeatureValue(InternalEObject internalEObject, int featureID, EStructuralFeatureData eStructuralFeatureData)
				throws IOException {
			// callback required for fragmentation
			//
			beforeSaveFeature(internalEObject, featureID);
			super.saveFeatureValue(internalEObject, featureID, eStructuralFeatureData);
		}
	}
	
	protected void beforeSaveFeature(InternalEObject internalEObject, int featureId) {
		// empty
	}
}
