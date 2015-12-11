package de.hub.emffrag;

public class FURI extends FAbstractStoreObject {

	private static final int ID = 1 << 0;
	private static final int FRAGMENT = 1 << 1;
	private static final int DB = 1 << 2;
	private static final int HOST = 1 << 3;
	private static final int SCHEME = 1 << 4;

	private static final int FIELD_MASK = ID | FRAGMENT | DB | HOST | SCHEME;
	
	public FURI(int id) {
		setField(ID, id);
	}
	
	public FURI(int id, int fragment) {
		this(id);
		setField(FRAGMENT, fragment);
	}
	
	public FURI(int id, int fragment, String db, String host, String scheme) {
		this(id,fragment);
		setField(DB, db);
		setField(HOST, host);
		setField(SCHEME, scheme);
	}
	
	@Override
	protected int firstField() {
		return ID;
	}

	@Override
	protected int lastField() {
		return SCHEME;
	}

	@Override
	protected int fieldMask() {
		return FIELD_MASK;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fFlags();
		result = prime * result + ((fStorage() == null) ? 0 : fStorage().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FAbstractStoreObject other = (FAbstractStoreObject) obj;
		if (fFlags() != other.fFlags())
			return false;
		if (fStorage() == null) {
			if (other.fStorage() != null)
				return false;
		} else if (!fStorage().equals(other.fStorage()))
			return false;
		return true;
	}
	
}
