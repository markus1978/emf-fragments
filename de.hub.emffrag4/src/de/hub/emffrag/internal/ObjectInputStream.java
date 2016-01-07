package de.hub.emffrag.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import de.hub.emffrag.FStore;
import de.hub.emffrag.FURI;
import de.hub.emffrag.FURIImpl;
import de.hub.emffrag.FragmentationUtil;

public abstract class ObjectInputStream {
	
	private final BufferedInputStream in;
	private final byte[] buffer;
	private int index;
	private int bufferLength = 0;
	
	private final FStreamURIImpl currentURI;
	
	private final Map<Integer, FStoreObject> internalObjectIDMap = new HashMap<Integer, FStoreObject>();
	
	public ObjectInputStream(InputStream in, int fragmentID) {
		this.in = new BufferedInputStream(in, 1000);
		this.buffer = new byte[1000];
		index = buffer.length;
		currentURI = new FStreamURIImpl(fragmentID);
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
	
	public boolean readBoolean() throws IOException {
		return readByte() != 0;
	}

	public char readChar() {
		return (char) ((readByte() << 8) & 0xFF00 | readByte() & 0xFF);
	}

	public short readShort() {
		return (short) ((readByte() << 8) & 0xFF00 | readByte() & 0xFF);
	}

	public int readInt() {
		return (readByte() << 24) | (readByte() << 16) & 0xFF0000 | (readByte() << 8) & 0xFF00 | readByte() & 0xFF;
	}

	public long readLong() {
		return (long) readInt() << 32 | readInt() & 0xFFFFFFFFL;
	}

	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	private int readCompressedInt() {
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
		uri.setFragment(readCompressedInt());
		for (int i = 1; i < size; i = i + 2) {
			uri.addFeatureToSegment(readCompressedInt(), readCompressedInt());
		}
		return uri;
	}

	private Object readValue(FStoreObject container, EStructuralFeature feature, int index) {
		if (feature instanceof EReference) {
			EReference reference = (EReference) feature;
			if (reference.isContainment() && !FragmentationUtil.isFragmenting(reference)) {
				currentURI.onDown(container.fClass().getFeatureID(feature), index);
				FStoreObject object = readObject(container, (EReference)feature);
				FStore.fINSTANCE.proxyManager.onFStoreObjectLoaded(currentURI, object);
				currentURI.onUp();
				return object;
			} else {
				int idSize = readCompressedInt();
				if (idSize == -1) {
					int objectId = readCompressedInt();
					return getObject(objectId, null);
				} else {
					return createProxy(readURI(idSize), (EClass)feature.getEType());
				}
			}
		} else {
			EClassifier dataType = feature.getEType();
			EPackage dataTypePackage = dataType.getEPackage();
			if (dataTypePackage == EcorePackage.eINSTANCE) {
				switch (dataType.getClassifierID()) {
					case EcorePackage.EBYTE: { return readByte(); }
					case EcorePackage.ECHAR: { return readChar(); }
					case EcorePackage.EDOUBLE: { return readDouble(); }
					case EcorePackage.EFLOAT: { return readFloat(); }
					case EcorePackage.EINT: { return readInt(); }
					case EcorePackage.ELONG: { return readLong(); }
					case EcorePackage.ECHARACTER_OBJECT: { return readChar(); }
					case EcorePackage.EDOUBLE_OBJECT: { return readDouble(); }
					case EcorePackage.EFLOAT_OBJECT: { return readFloat(); }
					case EcorePackage.EINTEGER_OBJECT: { return readInt(); }
					case EcorePackage.ELONG_OBJECT: { return readLong(); }
					case EcorePackage.ESTRING: { return readString(); }
				}
			}
			
			return feature.getEType().getEPackage().getEFactoryInstance().createFromString((EDataType) feature.getEType(), readString());
		}
	}
	
	protected abstract FStoreObject createProxy(FURI uri, EClass eClass);
	protected abstract FStoreObject createObject(EClass eClass);
	
	private FStoreObject getObject(int objectId, EClass eClass) {
		FStoreObject object = internalObjectIDMap.get(objectId);
		if (object == null) {
			object = createObject(eClass);
			internalObjectIDMap.put(objectId, object);
		} else {
			if (object.fClass() == null && eClass != null) {
				object.fSetClass(eClass);
			}
		}
		return object;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private FStoreObject readObject(FStoreObject container, EReference containingFeature) {
		int objectId = readCompressedInt();
		int packageId = readCompressedInt();
		int classifierId = readCompressedInt();
		EClass eClass = (EClass) getPackage(packageId).getEClassifiers().get(classifierId);
		FStoreObject object = getObject(objectId, eClass);
		if (container != null) {
			object.fSetContainer(container, containingFeature, true);
		}

		int featureCount = readCompressedInt();
		for (int featureIndex = 0; featureIndex < featureCount; featureIndex++) {
			int featureID = readCompressedInt();
			EStructuralFeature feature = eClass.getEStructuralFeature(featureID);
			if (feature.isMany()) {
				List values = (List) object.fGet(feature);
				int valueCount = readCompressedInt();
				for (int valueIndex = 0; valueIndex < valueCount; valueIndex++) {
					values.add(readValue(object, feature, valueIndex));					
				}
			} else {
				object.fSet(feature, readValue(object, feature,-1));
			}
		}

		return object;
	}

	public FStoreObject readFragment() {
		int firstContainerInt = readCompressedInt();
		FStoreObject root = null;
		if (firstContainerInt != -1) {
			EPackage containerPackage = getPackage(firstContainerInt);
			EClass containerClass = (EClass)containerPackage.getEClassifiers().get(readCompressedInt());
			EStructuralFeature containingFeature = containerClass.getEStructuralFeature(readCompressedInt());
			FURI containerURI = readURI(readCompressedInt());
			root = readObject(createProxy(containerURI, containerClass), (EReference)containingFeature);
		} else {
			root = readObject(null, null);
		}
		FStore.fINSTANCE.proxyManager.onFStoreObjectLoaded(currentURI, root);
//		readString(); // read human readable output
		return root;
	}

	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
