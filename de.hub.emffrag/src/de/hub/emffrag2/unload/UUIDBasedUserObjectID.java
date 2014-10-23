package de.hub.emffrag2.unload;

import org.eclipse.emf.common.util.URI;

class UUIDBasedUserObjectID {

	private final int uObjectID;
	private final String uResourceURI;

	UUIDBasedUserObjectID(URI uResourceURI, int uObjectID) {
		super();
		this.uResourceURI = uResourceURI.toString();
		this.uObjectID = uObjectID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uObjectID;
		result = prime * result + ((uResourceURI == null) ? 0 : uResourceURI.hashCode());
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
		UUIDBasedUserObjectID other = (UUIDBasedUserObjectID) obj;
		if (uObjectID != other.uObjectID)
			return false;
		if (uResourceURI == null) {
			if (other.uResourceURI != null)
				return false;
		} else if (!uResourceURI.equals(other.uResourceURI))
			return false;
		return true;
	}

	
}