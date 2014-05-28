package de.hub.emffrag2;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class Fragmentation extends ResourceSetImpl {
	
	private int counter = 0;

	private final Cache<Fragment, Fragment> resourceCache = CacheBuilder.newBuilder()
			.maximumSize(1)
			.removalListener(new RemovalListener<Fragment, Fragment>() {
				@Override
				public void onRemoval(RemovalNotification<Fragment, Fragment> notification) {
					Fragment fragment = notification.getValue();
					fragment.unload();
					getResources().remove(fragment);
				}				
			})
			.build();
	
	private static class UserObjectID {
		private final String fragmentID;
		private final int intrinsicObjectID;
		private UserObjectID(String fragmentID, int intrinsicObjectID) {
			super();
			this.fragmentID = fragmentID;
			this.intrinsicObjectID = intrinsicObjectID;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((fragmentID == null) ? 0 : fragmentID.hashCode());
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
			if (fragmentID == null) {
				if (other.fragmentID != null)
					return false;
			} else if (!fragmentID.equals(other.fragmentID))
				return false;
			if (intrinsicObjectID != other.intrinsicObjectID)
				return false;
			return true;
		}
	}
	
	private final Cache<UserObjectID, FObjectImpl> userObjectCache = CacheBuilder.newBuilder()
			.weakValues()
			.build();

	@Override
	public Resource createResource(URI uri, String contentType) {
		Resource resource = super.createResource(uri, contentType);
		if (!(resource instanceof Fragment)) { // assertion
			throw new IllegalStateException("Only Fragments can be created within an Fragmentation.");
		}
		Fragment fragment = (Fragment)resource;
		resourceCache.put(fragment, fragment);
		return resource;
	}

	public void doFragment(FObjectImpl fObject) {
		Resource resource = createResource(URI.createURI("test" + (counter++) + ".frag"));
		resource.getContents().add(fObject);
	}

	public void registerUserObject(Fragment fragment, Integer id, FObjectImpl fObject) {
		userObjectCache.put(new UserObjectID(fragment.fFragmentId(), id), fObject);
	}
	
	
}
