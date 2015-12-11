package de.hub.emffrag;

public abstract class FAbstractStoreObject {
	protected int fFlags;
	private Object fStorage;
	
	protected abstract int firstField();
	protected abstract int lastField();
	protected abstract int fieldMask();
	
	protected final boolean hasField(int field) {
		return (fFlags & field) != 0;
	}

	protected final Object getField(int field) {
		if (hasField(field)) {
			int fieldIndex = fieldIndex(field);
			return fieldIndex == -1 ? fStorage : ((Object[]) fStorage)[fieldIndex];
		} else {
			return null;
		}
	}

	protected final void setField(int field, Object value) {
		if (hasField(field)) {
			if (value == null) {
				removeField(field);
			} else {
				int fieldIndex = fieldIndex(field);
				if (fieldIndex == -1) {
					fStorage = value;
				} else {
					((Object[]) fStorage)[fieldIndex] = value;
				}
			}
		} else if (value != null) {
			addField(field, value);
		}
	}

	private final int fieldIndex(int field) {
		int result = 0;
		for (int bit = firstField(); bit < field; bit <<= 1) {
			if ((fFlags & bit) != 0) {
				++result;
			}
		}
		if (result == 0) {
			for (int bit = field <<= 1; bit <= lastField(); bit <<= 1) {
				if ((fFlags & bit) != 0) {
					return 0;
				}
			}
			return -1;
		} else {
			return result;
		}
	}

	private final void addField(int field, Object value) {
		int fieldCount = Integer.bitCount(fFlags & fieldMask());
		if (fieldCount == 0) {
			fStorage = value;
		} else {
			Object[] result;
			if (fieldCount == 1) {
				result = new Object[2];
				int fieldIndex = fieldIndex(field);
				if (fieldIndex == 0) {
					result[0] = value;
					result[1] = fStorage;
				} else {
					result[0] = fStorage;
					result[1] = value;
				}
			} else {
				result = new Object[fieldCount + 1];
				Object[] oldStorage = (Object[]) fStorage;
				for (int bit = firstField(), sourceIndex = 0, targetIndex = 0; bit <= lastField(); bit <<= 1) {
					if (bit == field) {
						result[targetIndex++] = value;
					} else if ((fFlags & bit) != 0) {
						result[targetIndex++] = oldStorage[sourceIndex++];
					}
				}
			}
			fStorage = result;
		}
		fFlags |= field;
	}

	private final void removeField(int field) {
		int fieldCount = Integer.bitCount(fFlags & fieldMask());
		if (fieldCount == 1) {
			fStorage = null;
		} else {
			Object[] oldStorage = (Object[]) fStorage;
			if (fieldCount == 2) {
				int fieldIndex = fieldIndex(field);
				fStorage = oldStorage[fieldIndex == 0 ? 1 : 0];
			} else {
				Object[] result = new Object[fieldCount - 1];
				for (int bit = firstField(), sourceIndex = 0, targetIndex = 0; bit <= lastField(); bit <<= 1) {
					if (bit == field) {
						sourceIndex++;
					} else if ((fFlags & bit) != 0) {
						result[targetIndex++] = oldStorage[sourceIndex++];
					}
				}
				fStorage = result;
			}
		}
		fFlags &= ~field;
	}
	
	protected Object[] fStorage() {
		return fStorage();
	}
	
	protected int fFlags() {
		return fFlags;
	}
}
