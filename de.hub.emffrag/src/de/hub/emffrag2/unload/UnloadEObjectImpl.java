package de.hub.emffrag2.unload;

import de.hub.emffrag2.AccessNotifyingEObjectImpl;

public class UnloadEObjectImpl extends AccessNotifyingEObjectImpl {

	private UnloadResourceSetImpl<?> isUnloadedFrom = null;

	protected void uSetIsUnloadedFrom(UnloadResourceSetImpl<?> isUnloadedFrom) {
		if (this.isUnloadedFrom == isUnloadedFrom) {
			throw new IllegalStateException();
		}
		
		if (isUnloadedFrom != null) {
			// if this becomes something not MinimalEObjectImpl,
			// eBasicProperties
			// has to be cleaned instead
			//
			eBasicSetSettings(new Object[] {});
			eBasicSetContainer(null);
	
			// re-create empty eSettings for further use of the object
			int size = eClass().getFeatureCount() - eStaticFeatureCount();
			if (size != 0) {
				eBasicSetSettings(new Object[size]);
			}
		}
		
		this.isUnloadedFrom = isUnloadedFrom;
	}
	
	public UnloadResourceSetImpl<?> uIsUnloadedFrom() {
		return isUnloadedFrom;
	}

	@Override
	protected void onAccess() {
		if (isUnloadedFrom != null) {
			isUnloadedFrom.uLoad(this);
		}
	}
}
