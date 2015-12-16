package de.hub.emffrag;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class Fragment {
	private final List<FStoreObject> contents = new ArrayList<FStoreObject>();
	private final List<FStoreObject> unmodifiableContents = Collections.unmodifiableList(contents);

	private final IDataStore dataStore;
	private final int id;

	public Fragment(IDataStore dataStore, int id) {
		super();
		this.dataStore = dataStore;
		this.id = id;
	}

	public List<FStoreObject> getContents() {
		return unmodifiableContents;
	}

	/**
	 * Adds the object to this fragment. Removes it from its prior fragment.
	 * Only adds the object itself, not its content.
	 */
	private void basicAddContents(FStoreObject object) {
		
		Fragment oldDirectFragment = object.fDirectFragment();
		if (oldDirectFragment != null) {
				
		} else {
			Fragment oldFragment = object.fFragment();
			if (oldFragment != this) {
				if (oldFragment != null) {
					oldFragment.basicRemoveContents(object);
				}
			}
		}
	}

	public void addContents(FStoreObject object) {
		Fragment oldDirectFragment = object.fDirectFragment();
		if (oldDirectFragment != this) {
			Fragment oldFragment = object.fFragment();
			if (oldFragment != this) {
				if (oldFragment == oldDirectFragment) {
					oldFragment.contents.remove(object);
				}
				object.fSetDirectFragment(this);
				contents.add(object);
			} // else already indirectly contained in this fragment
		} // else already a root in this fragment
	}

	public int getID(FStoreObject object) {
		return 0;
	}

	public int getFragmentID() {
		return id;
	}

	public void unload() {
		int objectIndex = 0;
		for (FStoreObject content : contents) {
			content.fUnload(new FURI(objectIndex++, id));
		}
		contents.clear();
	}

	public void create() {
		dataStore.checkAndCreate(LongKeyType.instance.serialize((long) id));
	}

	public void save() {
		try {
			OutputStream outputStream = dataStore.openOutputStream(LongKeyType.instance.serialize((long) id));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeFragment();
			objectOutputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void load() {
		try {
			InputStream inputStream = dataStore.openInputStream(LongKeyType.instance.serialize((long) id));
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			objectInputStream.readFragment();
			objectInputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private class ObjectInputStream {
		private final List<EPackage> packages = new ArrayList<EPackage>();
		private final BufferedInputStream in;
		private final byte[] buffer;
		private int index;
		private int objectIndex = 0;

		public ObjectInputStream(InputStream in) {
			this.in = new BufferedInputStream(in, 1000);
			this.buffer = new byte[1000];
			index = buffer.length;
		}

		private byte readByte() {
			if (buffer.length <= index) {
				try {
					if (in.read(buffer) <= 0) {
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

		private FStoreObject newFStoreObject(int id) {
			FStoreObject newFStoreObject = null;
			if (id < contents.size()) {
				newFStoreObject = contents.get(id);
			} else {
				for (int i = contents.size(); i <= id; i++) {
					contents.add(null);
				}
			}
			if (newFStoreObject == null) {
				newFStoreObject = new FStoreObject(new FURI(id));
				contents.set(id, newFStoreObject);
			}

			return newFStoreObject;
		}

		private Object readValue(EStructuralFeature feature) {
			if (feature instanceof EReference) {
				byte typeByte = readByte();
				if (typeByte == ObjectOutputStream.ID_ONLY) {
					int id = readInt();
					return newFStoreObject(id);
				} else {
					int id = readInt();
					int fragment = readInt();
					return new FStoreObject(new FURI(id, fragment)); // TODO
																		// resolve,
																		// check
																		// for
																		// existing
																		// proxies
				}
			} else {
				return feature.getEType().getEPackage().getEFactoryInstance()
						.createFromString((EDataType) feature.getEType(), readString());
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private FStoreObject readObject() {
			int packageId = readInt();
			int classifierId = readInt();
			EClass eClass = (EClass) packages.get(packageId).getEClassifiers().get(classifierId);
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

		public void readFragment() {
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

		public void close() {
			try {
				in.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private class ObjectOutputStream {
		private final List<EPackage> packages = new ArrayList<EPackage>();

		private final BufferedOutputStream out;
		private final byte[] buffer;
		private int index = 0;

		public ObjectOutputStream(java.io.OutputStream out) {
			this.out = new BufferedOutputStream(out, 1000);
			this.buffer = new byte[1000];
		}

		private static final byte ID_ONLY = 1;
		private static final byte FRAGMENT = 2;

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

		private void writeValue(EStructuralFeature feature, Object value) {
			if (feature instanceof EReference) {
				FStoreObject objectValue = (FStoreObject) value;
				if (objectValue.fDirectFragment() == Fragment.this) {
					writeByte(ID_ONLY);
					writeInt(getID(objectValue));
				} else {
					writeByte(FRAGMENT);
					writeInt(objectValue.fDirectFragment().getFragmentID());
					writeInt(objectValue.fDirectFragment().getID(objectValue));
				}
			} else {
				String stringValue = feature.getEType().getEPackage().getEFactoryInstance()
						.convertToString((EDataType) feature.getEType(), value);
				writeString(stringValue);
			}
		}

		@SuppressWarnings("rawtypes")
		private void writeObject(FStoreObject object) {
			EClass eClass = object.eClass();
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

		public void writeFragment() {
			for (FStoreObject object : contents) {
				if (!packages.contains(object.eClass().getEPackage())) {
					packages.add(object.eClass().getEPackage());
				}
			}

			writeInt(packages.size());
			for (EPackage ePackage : packages) {
				writeString(ePackage.getNsURI().toString());
			}

			writeInt(contents.size());
			for (FStoreObject object : contents) {
				writeObject(object);
			}
		}
	}
}
