package de.hub.emffrag2.unload;

import org.eclipse.emf.common.util.URI;

public class UUIDBasedUnloadResourceSetImpl extends UnloadResourceSetImpl<UUIDBasedUserObjectID> {

	@Override
	protected UUIDBasedUserObjectID createUserObjectId(UnloadBinaryResourceImpl uResource, UnloadEObjectImpl uObject) {
		URI proxyURI = uObject.eProxyURI();
		int uObjectID;
		if (proxyURI == null) {
			uObjectID = uResource.getID(uObject, true);
		} else {
			uObjectID = Integer.parseInt(proxyURI.fragment());
		}
		return new UUIDBasedUserObjectID(uResource.getURI(), uObjectID);
	}
}
