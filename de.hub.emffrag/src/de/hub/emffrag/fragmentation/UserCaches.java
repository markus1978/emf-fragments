package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import de.hub.emffrag.statistics.IWithStatistics;
import de.hub.emffrag.statistics.Statistic;
import de.hub.emffrag.statistics.services.Plot;
import de.hub.emffrag.statistics.services.Summary;

/**
 * In emf-fragments, we need to know if users hold java references to emf
 * objects directly or indirectly via holding references to value-sets contained
 * within an object. Only if no client java references to an object or one of
 * its value-sets (i.e. EList) exits, can we remove an object from memory.
 * 
 * This pseudo singleton (one per {@link Fragmentation}) is responsible to keep
 * track of user references to an object via {@link #userObjectCache} and to
 * value-sets via {@link #userReferenceCache}.
 */
public class UserCaches implements IWithStatistics {

	private final Cache<UserObjectID, FObjectImpl> userObjectCache;
	private final Cache<UserReferenceID, EList<?>> userReferenceCache;

	private final Statistic userObjectCacheStat = new Statistic.StatisticBuilder()
			.withService(new Summary())
			.withService(new Plot(500))
			.register(this, "UserObject");
	private final Statistic userReferenceCacheStat = new Statistic.StatisticBuilder()
			.withService(new Summary())
			.withService(new Plot(500))
			.register(this, "UserReferences");
	
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
		return (EList<T>) result;
	}

	@Override
	public void trackStatistics() {
		userObjectCacheStat.track(userObjectCache.size());
		userReferenceCacheStat.track(userObjectCache.size());
	}

	private static class UserObjectID {
		private final long fragmentID;
		private final int intrinsicObjectID;

		protected UserObjectID(long fragmentID, int intrinsicObjectID) {
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

	private static class UserReferenceID extends UserObjectID {
		private final int featureID;

		protected UserReferenceID(long fragmentID, int intrinsicObjectID, int featureID) {
			super(fragmentID, intrinsicObjectID);
			this.featureID = featureID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + featureID;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserReferenceID other = (UserReferenceID) obj;
			if (featureID != other.featureID)
				return false;
			return true;
		}
	}
}
