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

import com.google.common.base.Preconditions;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.FragmentationUtil;

public abstract class ObjectOutputStream {
	
	private final Map<FStoreObject, Integer> internalObjectIDMap = new HashMap<FStoreObject, Integer>();
	private final boolean withUnload;

	private final BufferedOutputStream out;
	private final byte[] buffer;
	private int index = 0;
	
	private int objectIDCounter = 0;
	
	private int thisFragmentID = -1;
	private FStoreFragmentation thisFragmentation = null;
	private FStreamURIImpl currentURI = null;
	
//	private StringBuilder humanReadableOutput = new StringBuilder();
//	private int humanReadableIndent = 0;

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
		value = value + 2;
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
		byte[] bytes = value.getBytes();
		writeInt(bytes.length);
		for (byte b : bytes) {
			writeByte(b);
		}
	}
	
	private void writeURI(FStoreObject fStoreObject) {
		FURI uri = fStoreObject.fCreateURI();
		if (uri.fragment() == -1) {
			writeCompressedInt(-1);
			EmfFragActivator.instance.warning("Dangling object reference. Cannot store the value. Some model data might get lost.");
			if (!fStoreObject.fRoot().getClass().getName().toLowerCase().equals("javadoc")) {
				EmfFragActivator.instance.warning("Dangling object reference was not just a MoDisco javadoc."); // TODO remove	
			}
		} else {
			if (fStoreObject.fFragmentation() != thisFragmentation) {
				writeCompressedInt(-2);
				String fragmentationURIString = fStoreObject.fFragmentation().getURI().toString();
				writeString(fragmentationURIString);
			}
			writeCompressedInt(uri.segment().size() + 1);
			writeCompressedInt(uri.fragment());
			for (int segmentPart: uri.segment()) {
				writeCompressedInt(segmentPart);
			}
			if (uri.fragment() == 247) {
				if (uri.segment().size() >=1 && uri.segment().get(1) == 3) { // TODO remove			
					System.out.println("....");
				}
			}
			hr(uri.toString());
		}
	}

	/** 
	 * Write a single object, never a list of objects.
	 */
	private void writeValue(FStoreObject object, EStructuralFeature feature, Object value, int index) {
		if (feature instanceof EReference) {
			EReference reference = (EReference) feature;
			FStoreObject fStoreObject = (FStoreObject) value;
			if (reference.isContainment() && !FragmentationUtil.isFragmenting(reference)) {
				currentURI.onDown(object.fClass().getFeatureID(feature), index);
				writeObject(fStoreObject);
				currentURI.onUp();
			} else {			
				if ((fStoreObject.fFragmentation() != thisFragmentation) || (fStoreObject.fFragmentID() != thisFragmentID)) {
					writeURI(fStoreObject);
				} else {
					writeCompressedInt(-1);
					writeCompressedInt(getObjectId(fStoreObject));
					hr("ref:");
					hr(""+getObjectId(fStoreObject));
				}
			}				
		} else {
			EClassifier dataType = feature.getEType();
			EPackage dataTypePackage = dataType.getEPackage();
			if (dataTypePackage == EcorePackage.eINSTANCE) {
				switch (dataType.getClassifierID()) {
					case EcorePackage.EBYTE: { writeByte((byte)value); hr(value); return; }
					case EcorePackage.ECHAR: { writeChar((int)value); hr(value); return; }
					case EcorePackage.EDOUBLE: { writeDouble((double)value); hr(value); return; }
					case EcorePackage.EFLOAT: { writeFloat((float)value); hr(value); return; }
					case EcorePackage.EINT: { writeInt((int)value); hr(value); return; }
					case EcorePackage.ELONG: { writeLong((long)value); hr(value); return; }
					case EcorePackage.ECHARACTER_OBJECT: { writeChar((int)value); hr(value); return; }
					case EcorePackage.EDOUBLE_OBJECT: { writeDouble((double)value); hr(value); return; }
					case EcorePackage.EFLOAT_OBJECT: { writeFloat((float)value); hr(value); return; }
					case EcorePackage.EINTEGER_OBJECT: { writeInt((int)value); hr(value); return; }
					case EcorePackage.ELONG_OBJECT: { writeLong((long)value); hr(value); return; }
					case EcorePackage.ESTRING: { writeString((String)value); hr(value); return; }
				}				
			}
			
			String stringValue = dataTypePackage.getEFactoryInstance().convertToString((EDataType) dataType, value);
			writeString(stringValue);
			hr(dataType.getName() + ":" + stringValue);
		}
	}

	@SuppressWarnings("rawtypes")
	private void writeObject(FStoreObject object) {
		writeCompressedInt(getObjectId(object)); // write object id for inter fragment references
		hr(getObjectId(object)); hr(":");
		EClass eClass = object.fClass();
		writeCompressedInt(getPackageID(eClass.getEPackage()));
		writeCompressedInt(eClass.getClassifierID());
		hr(eClass.getName());
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (object.fIsSet(feature)) {
				features.add(feature);
			}
		}
		writeCompressedInt(features.size());
		hr("("); hr(features.size()); hr(") {\n", 1);
		for (EStructuralFeature feature : features) {
			int featureID = object.fClass().getFeatureID(feature);
			Preconditions.checkState(featureID >= 0, "Invalid freature id.");
			writeCompressedInt(featureID);
			hr(feature.getName()); hr("("); hr(featureID); hr(")=");
			if (feature.isMany()) {
				hr("[");
				List values = (List) object.fGet(feature);
				writeCompressedInt(values.size());
				hr("("); hr(Integer.toString(values.size())); hr(")\n", 1);
				int i = 0;
				for (Object value : values) {
					writeValue(object, feature, value, i++);
					hr("\n");
				}
				hr("]",-1);
			} else {
				writeValue(object, feature, object.fGet(feature), -1);
			}
			hr("\n");
		}
		hr("}", -1);
		
		if (withUnload) {
			object.fUnload(currentURI);
		}
	}

	public void writeFragment(FStoreObject object) {
		thisFragmentation = object.fFragmentation();
		thisFragmentID = object.fFragmentID();
		Preconditions.checkArgument(thisFragmentID != -1);
		currentURI = new FStreamURIImpl(thisFragmentation != null ? thisFragmentation.getURI() : null, thisFragmentID);
		// write container info
		FStoreObject fContainer = object.fContainer();
		if (fContainer != null) {
			EClass eClass = fContainer.fClass();
			writeCompressedInt(getPackageID(eClass.getEPackage()));
			hr(eClass.getEPackage().getNsURI());
			writeCompressedInt(eClass.getClassifierID());
			hr(" "); hr(eClass.getName());
			writeCompressedInt(object.fContainer().fClass().getFeatureID(object.fContainingFeature()));
			hr("."); hr(object.fContainingFeature().getName());
			hr("="); 
			writeURI(fContainer);
			hr("\n");
		} else {
			writeCompressedInt(-1);
			hr("=ROOT=\n");
		}
		
		writeObject(object);
		
//		String hrOut = humanReadableOutput.toString();
//		writeString(hrOut);
//		String hrUriString = "";
//		if (object.fFragmentation() != null && object.fFragmentation().getURI() != null) {
//			hrUriString = object.fFragmentation().getURI().toString();
//		}
//		System.out.println(">>> " + hrUriString + "/"+ object.fFragmentID() + ": " + hrOut);
	}
	
	private void hr(Object value, int indentChange) {
//		if (indentChange < 0) {
//			humanReadableOutput.deleteCharAt(humanReadableOutput.length()-1);
//			humanReadableOutput.deleteCharAt(humanReadableOutput.length()-1);
//		}
//		humanReadableIndent += indentChange;
//		hr(value);
	}
	
	private void hr(Object value) {
//		if (value instanceof String) {
//			String str = (String) value;
//			if (str.length() > 40) {
//				appendHumanReadableOutput(str.substring(0, 40));
//				hr("...");
//			} else {
//				appendHumanReadableOutput(str);
//			}
//		} else {
//			appendHumanReadableOutput(value.toString());
//		}
	}
	
//	private void appendHumanReadableOutput(String str) {
//		humanReadableOutput.append(str);
//		if (str.endsWith("\n")) {
//			for (int i = 0; i < humanReadableIndent; i++) humanReadableOutput.append("  ");
//		}
//	}
	
	protected abstract int getPackageID(EPackage pkg);
}
