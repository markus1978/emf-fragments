package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class UserCaches {
	
	private final Cache<UserObjectID, FObjectImpl> userObjectCache;
	private final Cache<UserReferenceID, EList<?>> userReferenceCache;

	protected UserCaches() {
		userObjectCache = CacheBuilder.newBuilder().weakValues().build();
		userReferenceCache = CacheBuilder.newBuilder().weakValues().build();
	}
	
	public void registerUserObject(long fragmentId, int objectId, FObjectImpl fObject) {
		userObjectCache.put(new UserObjectID(fragmentId, objectId), fObject);
	}
	
	public FObjectImpl getRegisterdUserObject(long fragmentId, int id) {
		FObjectImpl fObjectImpl = userObjectCache.getIfPresent(new UserObjectID(fragmentId, id));
		return fObjectImpl;
	}
	
	public <T> void registerUserReference(long fragmentId, int objectId, int featureId, EList<T> values) {
		if (values == null) {
			throw new IllegalArgumentException();
		}
		userReferenceCache.put(new UserReferenceID(fragmentId, objectId, featureId), values);
	}
	
	@SuppressWarnings("unchecked")
	public <T> EList<T> getRegisteredUserReference(long fragmentId, int objectId, int featureId) {
		EList<?> result = userReferenceCache.getIfPresent(new UserReferenceID(fragmentId, objectId, featureId));
		return (EList<T>)result;
	}
	
	private static class UserObjectID {
		private final long fragmentID;
		private final int intrinsicObjectID;

		private UserObjectID(long fragmentID, int intrinsicObjectID) {
			super();
			this.fragmentID = fragmentID;
			this.intrinsicObjectID = intrinsicObjectID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (fragmentID ^ (fragmentID >>> 32));
			result = prime * result + intrinsicObjectID;
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
			UserObjectID other = (UserObjectID) obj;
			if (fragmentID != other.fragmentID)
				return false;
			if (intrinsicObjectID != other.intrinsicObjectID)
				return false;
			return true;
		}
	}
	
	private static class UserReferenceID {
		private final long fragmentID;
		private final long intrinsicObjectID;
		private final int featureID;
		
		private UserReferenceID(long fragmentID, long intrinsicObjectID, int featureID) {
			super();
			this.fragmentID = fragmentID;
			this.intrinsicObjectID = intrinsicObjectID;
			this.featureID = featureID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + featureID;
			result = prime * result + (int) (fragmentID ^ (fragmentID >>> 32));
			result = prime * result + (int) (intrinsicObjectID ^ (intrinsicObjectID >>> 32));
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
			UserReferenceID other = (UserReferenceID) obj;
			if (featureID != other.featureID)
				return false;
			if (fragmentID != other.fragmentID)
				return false;
			if (intrinsicObjectID != other.intrinsicObjectID)
				return false;
			return true;
		}
	}
}
