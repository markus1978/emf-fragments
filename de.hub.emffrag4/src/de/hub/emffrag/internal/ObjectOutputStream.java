package de.hub.emffrag.internal;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import de.hub.emffrag.FURI;
import de.hub.emffrag.FragmentationUtil;

public abstract class ObjectOutputStream {
	
	private final Map<FStoreObject, Integer> internalObjectIDMap = new HashMap<FStoreObject, Integer>();
	private final boolean withUnload;

	private final BufferedOutputStream out;
	private final byte[] buffer;
	private int index = 0;
	
	private int objectIDCounter = 0;
	
	private int thisFragmentID = -1;
	private FStreamURIImpl currentURI = null;

	public ObjectOutputStream(java.io.OutputStream out, boolean withUnload) {
		this.out = new BufferedOutputStream(out, 1000);
		this.buffer = new byte[1000];
		this.withUnload = withUnload;
	}
	
	private int getObjectId(FStoreObject fStoreObject) {
		Integer objectID = internalObjectIDMap.get(fStoreObject);
		if (objectID == null) {
			objectID = objectIDCounter++;
			internalObjectIDMap.put(fStoreObject, objectID);
		}
		return objectID;
	}
	
	public void writeBoolean(boolean value) {
		writeByte(value ? 1 : 0);
	}

	public void writeChar(int value) {
		writeByte((byte) (value >> 8 & 0xFF));
		writeByte((byte) (value & 0xFF));
	}

	public void writeShort(int value) {
		writeByte((byte) (value >> 8 & 0xFF));
		writeByte((byte) (value & 0xFF));
	}

	public void writeInt(int value) {
		writeByte((byte) (value >> 24 & 0xFF));
		writeByte((byte) (value >> 16 & 0xFF));
		writeByte((byte) (value >> 8 & 0xFF));
		writeByte((byte) (value & 0xFF));
	}

	public void writeLong(long value) {
		writeInt((int) (value >> 32));
		writeInt((int) value);
	}

	public void writeFloat(float value) {
		writeInt(Float.floatToIntBits(value));
	}

	public void writeDouble(double value) {
		writeLong(Double.doubleToLongBits(value));
	}

	public void writeCompressedInt(int value) {
		++value;
		if (value < 0) {
			handleInvalidValue(value);
		} else if (value <= 0x3F) {
			writeByte(value);
		} else if (value <= 0x3FFF) {
			writeByte(value >> 8 | 0x40);
			writeByte(value & 0xFF);
		} else if (value <= 0x3FFFFF) {
			writeByte(value >> 16 | 0x80);
			writeByte(value >> 8 & 0xFF);
			writeByte(value & 0xFF);
		} else if (value <= 0x3FFFFFFF) {
			writeByte(value >> 24 | 0xC0);
			writeByte(value >> 16 & 0xFF);
			writeByte(value >> 8 & 0xFF);
			writeByte(value & 0xFF);
		} else {
			handleInvalidValue(value);
		}
	}

	private final void handleInvalidValue(int value) {
		throw new RuntimeException("Invalid value " + value);
	}

	private void writeByte(int value) {
		if (index >= buffer.length) {
			flush();
		}
		buffer[index++] = (byte) value;
	}

	private void flush() {
		try {
			out.write(buffer, 0, index);
			index = 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		flush();
		try {
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeString(String value) {
		writeCompressedInt(value.length());
		for (byte b : value.getBytes()) {
			writeByte(b);
		}
	}
	
	private void writeURI(FStoreObject fStoreObject) {
		FURI uri = fStoreObject.fCreateURI();
		writeCompressedInt(uri.segment().size() + 1);
		writeCompressedInt(uri.fragment());
		for (int segmentPart: uri.segment()) {
			writeCompressedInt(segmentPart);
		}
	}

	/** 
	 * Write a single object, never a list of objects.
	 */
	private void writeValue(EStructuralFeature feature, Object value, int index) {
		if (feature instanceof EReference) {
			EReference reference = (EReference) feature;
			FStoreObject fStoreObject = (FStoreObject) value;
			if (reference.isContainment() && !FragmentationUtil.isFragmenting(reference)) {
				currentURI.onDown(feature.getFeatureID(), index);
				writeObject(fStoreObject);
				currentURI.onUp();
			} else {				
				if (fStoreObject.fFragmentID() != thisFragmentID) {
					writeURI(fStoreObject);
				} else {
					writeCompressedInt(-1);
					writeCompressedInt(getObjectId(fStoreObject));
				}
			}				
		} else {
			EClassifier dataType = feature.getEType();
			EPackage dataTypePackage = dataType.getEPackage();
			if (dataTypePackage == EcorePackage.eINSTANCE) {
				switch (dataType.getClassifierID()) {
					case EcorePackage.EBYTE: { writeByte((byte)value); return; }
					case EcorePackage.ECHAR: { writeChar((int)value); return; }
					case EcorePackage.EDOUBLE: { writeDouble((double)value); return; }
					case EcorePackage.EFLOAT: { writeFloat((float)value); return; }
					case EcorePackage.EINT: { writeInt((int)value); return; }
					case EcorePackage.ELONG: { writeLong((long)value); return; }
					case EcorePackage.ECHARACTER_OBJECT: { writeChar((int)value); return; }
					case EcorePackage.EDOUBLE_OBJECT: { writeDouble((double)value); return; }
					case EcorePackage.EFLOAT_OBJECT: { writeFloat((float)value); return; }
					case EcorePackage.EINTEGER_OBJECT: { writeInt((int)value); return; }
					case EcorePackage.ELONG_OBJECT: { writeLong((long)value); return; }
					case EcorePackage.ESTRING: { writeString((String)value); return; }
				}
			}
			String stringValue = dataTypePackage.getEFactoryInstance().convertToString((EDataType) dataType, value);
			writeString(stringValue);
		}
	}

	@SuppressWarnings("rawtypes")
	private void writeObject(FStoreObject object) {
		writeCompressedInt(getObjectId(object)); // write object id for intra fragment references
		EClass eClass = object.fClass();
		writeCompressedInt(getPackageID(eClass.getEPackage()));
		writeCompressedInt(eClass.getClassifierID());
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (object.fIsSet(feature)) {
				features.add(feature);
			}
		}
		writeCompressedInt(features.size());
		for (EStructuralFeature feature : features) {
			writeCompressedInt(feature.getFeatureID());
			if (feature.isMany()) {
				List values = (List) object.fGet(feature);
				writeCompressedInt(values.size());
				int i = 0;
				for (Object value : values) {
					writeValue(feature, value, i++);
				}
			} else {
				writeValue(feature, object.fGet(feature), -1);
			}
		}
		
		if (withUnload) {
			object.fUnload(currentURI);
		}
	}

	public void writeFragment(FStoreObject object) {
		thisFragmentID = object.fFragmentID();
		currentURI = new FStreamURIImpl(thisFragmentID);
		// write container info
		FStoreObject fContainer = object.fContainer();
		if (fContainer != null) {
			EClass eClass = fContainer.fClass();
			writeCompressedInt(getPackageID(eClass.getEPackage()));
			writeCompressedInt(eClass.getClassifierID());
			writeCompressedInt(object.fContainingFeature().getFeatureID());
			writeURI(fContainer);
		} else {
			writeCompressedInt(-1);
		}
		
		writeObject(object);
	}
	
	protected abstract int getPackageID(EPackage pkg);
}
