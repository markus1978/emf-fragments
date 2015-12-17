package de.hub.emffrag.internal;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.FURI;
import de.hub.emffrag.FragmentationUtil;

public class ObjectOutputStream {
	private final List<EPackage> packages = new ArrayList<EPackage>();
	private final Map<FStoreObject, Integer> internalObjectIDMap = new HashMap<FStoreObject, Integer>();
	private final boolean withUnload;

	private final BufferedOutputStream out;
	private final byte[] buffer;
	private int index = 0;
	
	private int objectIDCounter = 0;
	
	private int thisFragmentID = -1;

	public ObjectOutputStream(java.io.OutputStream out, boolean withUnload) {
		this.out = new BufferedOutputStream(out, 1000);
		this.buffer = new byte[1000];
		this.withUnload = true;
	}
	
	private int getObjectId(FStoreObject fStoreObject) {
		Integer objectID = internalObjectIDMap.get(fStoreObject);
		if (objectID == null) {
			objectID = objectIDCounter++;
			internalObjectIDMap.put(fStoreObject, objectID);
		}
		return objectID;
	}

	public void writeInt(int value) {
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
		writeInt(value.length());
		for (byte b : value.getBytes()) {
			writeByte(b);
		}
	}

	/** 
	 * Write a single object, never a list of objects.
	 */
	private void writeValue(EStructuralFeature feature, Object value) {
		if (feature instanceof EReference) {
			EReference reference = (EReference) feature;
			FStoreObject fStoreObject = (FStoreObject) value;
			if (reference.isContainment() && FragmentationUtil.isFragmenting(reference)) {
				writeObject(fStoreObject);
				if (withUnload) {
					fStoreObject.fUnload();
				}
			} else {				
				if (fStoreObject.fFragmentID() != thisFragmentID) {
					FURI uri = fStoreObject.fUnload();
					writeInt(uri.segment().size() + 1);
					writeInt(uri.fragment());
					for (int segmentPart: uri.segment()) {
						writeInt(segmentPart);
					}
				} else {
					writeInt(-1);
					writeInt(getObjectId(fStoreObject));
				}
			}				
		} else {
			String stringValue = feature.getEType().getEPackage().getEFactoryInstance()
					.convertToString((EDataType) feature.getEType(), value);
			writeString(stringValue);
		}
	}

	@SuppressWarnings("rawtypes")
	private void writeObject(FStoreObject object) {
		writeInt(getObjectId(object)); // write object id for intra fragment references
		EClass eClass = object.fClass();
		writeInt(packages.indexOf(eClass.getEPackage()));
		writeInt(eClass.getClassifierID());
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (object.fIsSet(feature)) {
				features.add(feature);
			}
		}
		writeInt(features.size());
		for (EStructuralFeature feature : features) {
			writeInt(feature.getFeatureID());
			if (feature.isMany()) {
				List values = (List) object.fGet(feature);
				writeInt(values.size());
				for (Object value : values) {
					writeValue(feature, value);
				}
			} else {
				writeValue(feature, object.fGet(feature));
			}
		}
	}

	public void writeFragment(FStoreObject object) {
		thisFragmentID = object.fFragmentID();
		writeObject(object);
	}
}
