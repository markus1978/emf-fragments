package de.hub.emffrag;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class Fragment {
	private final List<FStoreObject> contents = new ArrayList<FStoreObject>();
	private final IDataStore dataStore;
	private final int id;

	public Fragment(IDataStore dataStore, int id) {
		super();
		this.dataStore = dataStore;
		this.id = id;
	}

	public List<FStoreObject> getContents() {
		return contents;
	}
	
	public int getID(FStoreObject object) {
		return 0;
	}
	

	public int getFragmentID() {
		return id;
	}
	
	public void unload() {
		int objectIndex = 0;
		for (FStoreObject content: contents) {
			content.fUnload(new FURI(objectIndex++, id));
		}
		contents.clear();
	}
	
	public void save() {
		try {
			OutputStream outputStream = dataStore.openOutputStream(LongKeyType.instance.serialize((long)id));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeFragment();
			objectOutputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void load() {
		try {
			InputStream inputStream = dataStore.openInputStream(LongKeyType.instance.serialize((long)id));
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			objectInputStream.readFragment();
			objectInputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private class ObjectInputStream extends BufferedInputStream {
		private List<EPackage> packages = new ArrayList<EPackage>();
		private byte[] buffer = new byte[1000];
		private int index = buffer.length;
		private int objectIndex = 0;
		
		public ObjectInputStream(InputStream in) {
			super(in, 1000);
		}
		
		private byte next() {
			if (buffer.length <= index) {
				try {
					if (read(buffer) <= 0) {
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
			int result = next() << 24;
			result += next() << 16;
			result += next() << 8;
			result += next();
			return result;
		}
		
		private String readString() {
			int length = readInt();
			ByteBuffer stringBytes = ByteBuffer.allocate(length);
			for (int i = 0; i < length; i++) {
				stringBytes.put(next());
			}
			return new String(stringBytes.array());
		}
		
		private FStoreObject newFStoreObject(int id) {
			FStoreObject newFStoreObject = null;
			if (id < contents.size()) {
				newFStoreObject = contents.get(id);
			}
			if (newFStoreObject == null) {
				newFStoreObject = new FStoreObject(new FURI(id));
				contents.set(id, newFStoreObject);
			}
			
			return newFStoreObject;
		}
		
		private Object readValue(EStructuralFeature feature) {
			if (feature instanceof EReference) {
				byte typeByte = next();
				if (typeByte == ObjectOutputStream.ID_ONLY) {
					int id = readInt();
					return newFStoreObject(id);
				} else {
					int id = readInt();
					int fragment = readInt();					
					return new FStoreObject(new FURI(id, fragment)); // TODO resolve, check for existing proxies
				}
			} else {
				return feature.getEType().getEPackage().getEFactoryInstance().createFromString((EDataType)feature.getEType(), readString());
			}
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private FStoreObject readObject() {
			int packageId = readInt();
			int classifierId = readInt();
			EClass eClass = (EClass)packages.get(packageId).getEClassifiers().get(classifierId);
			int objectId = objectIndex++;
			FStoreObject object = newFStoreObject(objectId);
			if (object.fIsProxy()) {
				object.fSetProxyURI(null);
			}
			object.fSetClass(eClass);
			
			int featureCount = readInt();
			for (int featureIndex = 0; featureIndex < featureCount; featureIndex++) {
				EStructuralFeature feature = eClass.getEStructuralFeature(readInt());
				if (feature.isMany()) {
					List values = (List) object.fGet(feature);
					int valueCount = readInt();
					for (int valueIndex = 0; valueIndex < valueCount; valueIndex++) {
						values.add(readValue(feature));
					}
				} else {
					object.fSet(feature, readValue(feature));
				}
			}
			
			return object;
		}
		
		private void readFragment() {
			unload();
			
			int packageCount = readInt();
			for (int packageIndex = 0; packageIndex < packageCount; packageIndex++) {
				String packageNsURI = readString();
				EPackage pkg = EPackage.Registry.INSTANCE.getEPackage(packageNsURI);
				if (pkg == null) {
					throw new RuntimeException("Package with NS URI " + packageNsURI + " not registered.");
				}
				packages.add(pkg);
			}
			
			int objectCount = readInt();
			for (int objectIndex = 0; objectIndex < objectCount; objectIndex++) {
				readObject();
			}
		}
	}
	
	private class ObjectOutputStream extends PrintStream {
		private List<EPackage> packages = new ArrayList<EPackage>();
		
		public ObjectOutputStream(java.io.OutputStream out) {
			super(out);
		}
		
		private static final byte ID_ONLY = 1;
		private static final byte FRAGMENT = 2;
		
		private void writeInt(int value) {
			print(value);
		}
		
		private void writeString(String value) {
			print(value.length());
			for (byte b: value.getBytes()) {
				print(b);
			}			
		}
		
		private void writeValue(EStructuralFeature feature, Object value) {
			if (feature instanceof EReference) {
				FStoreObject objectValue = (FStoreObject)value;
				if (objectValue.fFragment() == Fragment.this) {
					write(ID_ONLY);
					writeInt(getID(objectValue));
				} else {
					write(FRAGMENT);
					writeInt(objectValue.fFragment().getFragmentID());
					writeInt(objectValue.fFragment().getID(objectValue));
				}
			} else {
				String stringValue = feature.getEType().getEPackage().getEFactoryInstance().convertToString((EDataType)feature.getEType(), value);
				writeString(stringValue);
			}	
		}
		
		@SuppressWarnings("rawtypes")
		private void writeObject(FStoreObject object) {
			EClass eClass = object.eClass();
			writeInt(packages.indexOf(eClass.getEPackage()));
			writeInt(eClass.getClassifierID());			
			List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
			for(EStructuralFeature feature: eClass.getEAllStructuralFeatures()) {
				if (object.fIsSet(feature)) {
					features.add(feature);
				}
			}
			writeInt(features.size());
			for(EStructuralFeature feature: features) {
				writeInt(feature.getFeatureID());
				if (feature.isMany()) {
					List values = (List)object.fGet(feature);
					writeInt(values.size());
					for (Object value: values) {
						writeValue(feature, value);
					}
				} else {
					writeValue(feature, object.fGet(feature));
				}				
			}
		}
		
		public void writeFragment() {
			for (FStoreObject object: contents) {
				if (!packages.contains(object.eClass().getEPackage())) {
					packages.add(object.eClass().getEPackage());
				}
			}
			
			writeInt(packages.size());
			for (EPackage ePackage: packages) {
				writeString(ePackage.getNsURI().toString());
			}
			
			writeInt(contents.size());
			for (FStoreObject object: contents) {
				writeObject(object);
			}
		}
	}
}
