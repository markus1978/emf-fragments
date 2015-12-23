package de.hub.emffrag.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Lists;

import de.hub.emffrag.FStore;
import de.hub.emffrag.FURI;
import de.hub.emffrag.FURIImpl;
import de.hub.emffrag.FragmentationUtil;

public abstract class ObjectInputStream {
	private int fragmentID = -1;
	
	private final BufferedInputStream in;
	private final byte[] buffer;
	private int index;
	private int bufferLength = 0;
	
	private final FStreamURIImpl currentURI = new FStreamURIImpl();
	
	private final Map<Integer, FStoreObject> internalObjectIDMap = new HashMap<Integer, FStoreObject>();

	public ObjectInputStream(InputStream in) {
		this.in = new BufferedInputStream(in, 1000);
		this.buffer = new byte[1000];
		index = buffer.length;
	}
	
	protected abstract EPackage getPackage(int packageID);

	private byte readByte() {
		if (bufferLength <= index) {
			try {
				bufferLength = in.read(buffer);
				if (bufferLength <= 0) {
					throw new RuntimeException("Unexpected end of stream.");
				}
				index = 0;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return buffer[index++];
	}

	private int readInt() {
		int initialByte = readByte();
		int code = (initialByte >> 6) & 0x3;
		switch (code) {
		case 0: {
			return initialByte - 1;
		}
		case 1: {
			return (initialByte << 8 & 0x3F00 | readByte() & 0xFF) - 1;
		}
		case 2: {
			return ((initialByte << 16) & 0x3F0000 | (readByte() << 8) & 0xFF00 | readByte() & 0xFF) - 1;
		}
		default: {
			return ((initialByte << 24) & 0x3F000000 | (readByte() << 16) & 0xFF0000 | (readByte() << 8) & 0xFF00
					| readByte() & 0xFF) - 1;
		}
		}
	}

	private String readString() {
		int length = readInt();
		ByteBuffer stringBytes = ByteBuffer.allocate(length);
		for (int i = 0; i < length; i++) {
			stringBytes.put(readByte());
		}
		return new String(stringBytes.array());
	}

	private FURI readURI(int size) {
		FURIImpl uri = new FURIImpl();
		uri.setFragment(readInt());
		for (int i = 1; i < size; i = i + 2) {
			uri.addFeatureToSegment(readInt(), readInt());
		}
		return uri;
	}

	private Object readValue(FStoreObject container, EStructuralFeature feature, int index) {
		if (feature instanceof EReference) {
			EReference reference = (EReference) feature;
			if (reference.isContainment() && !FragmentationUtil.isFragmenting(reference)) {
				currentURI.onDown(feature.getFeatureID(), index);
				FStoreObject object = readObject();
				FStore.fINSTANCE.proxyManager.onFStoreObjectLoaded(currentURI, object);
				object.fSetContainer(container, (EReference)feature);
				currentURI.onUp();
				return object;
			} else {
				int idSize = readInt();
				if (idSize == -1) {
					int objectId = readInt();
					return getObject(objectId, null);
				} else {
					return createProxy(readURI(idSize));
				}
			}
		} else {
			return feature.getEType().getEPackage().getEFactoryInstance()
					.createFromString((EDataType) feature.getEType(), readString());
		}
	}
	
	protected abstract FStoreObject createProxy(FURI uri);
	protected abstract FStoreObject createObject();
	
	private FStoreObject getObject(int objectId, EClass eClass) {
		FStoreObject object = internalObjectIDMap.get(objectId);
		if (object == null) {
			object = createObject();
			internalObjectIDMap.put(objectId, object);
		}
		if (eClass != null) {
			object.fSetClass(eClass);
		}
		return object;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private FStoreObject readObject() {
		int objectId = readInt();
		int packageId = readInt();
		int classifierId = readInt();
		EClass eClass = (EClass) getPackage(packageId).getEClassifiers().get(classifierId);
		FStoreObject object = getObject(objectId, eClass);

		int featureCount = readInt();
		for (int featureIndex = 0; featureIndex < featureCount; featureIndex++) {
			EStructuralFeature feature = eClass.getEStructuralFeature(readInt());
			if (feature.isMany()) {
				List values = (List) object.fGet(feature);
				int valueCount = readInt();
				for (int valueIndex = 0; valueIndex < valueCount; valueIndex++) {
					values.add(readValue(object, feature, valueIndex));					
				}
			} else {
				object.fSet(feature, readValue(object, feature,-1));
			}
		}

		return object;
	}

	public FStoreObject readFragment(int fragmentID) {
		this.fragmentID = fragmentID;
		int firstContainerInt = readInt();
		FStoreObject root = null;
		if (firstContainerInt != -1) {
			EPackage containerPackage = getPackage(firstContainerInt);
			EClass containerClass = (EClass)containerPackage.getEClassifiers().get(readInt());
			EStructuralFeature containingFeature = containerClass.getEStructuralFeature(readInt());
			FURI containerURI = readURI(readInt());
			root = readObject();
			root.fSetContainer(createProxy(containerURI), (EReference)containingFeature);
		} else {
			root = readObject();
		}
		FStore.fINSTANCE.proxyManager.onFStoreObjectLoaded(currentURI, root);
		return root;
	}

	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private class FStreamURIImpl implements FURI {
		private final List<Integer> reversedSegment = new ArrayList<Integer>();
		private final List<Integer> segment = Lists.reverse(reversedSegment);
		
		@Override
		public int fragment() {
			return fragmentID;
		}

		@Override
		public List<Integer> segment() {
			return segment;
		}
		
		private void onDown(int featureID, int index) {
			reversedSegment.add(index);
			reversedSegment.add(featureID);
		}
		
		private void onUp() {
			int size = reversedSegment.size();
			reversedSegment.remove(size-1);
			reversedSegment.remove(size-2);
		}

		@Override
		public int hashCode() {
			return FURI.hashCode(this);
		}

		@Override
		public boolean equals(Object obj) {
			return FURI.equals(this, obj);
		}
		
	}
}
