package de.hub.emffrag.testmodels;

import de.hub.emffrag.fragmentation.AccessNotifyingEObjectImpl;

public class AccessNotifyingTestEObjectImpl extends AccessNotifyingEObjectImpl {

	public int accessCounter = 0;
	
	@Override
	protected void onAccess() {
		super.onAccess();
		accessCounter++;
	}

	public int fAccessCount() {
		return accessCounter;
	}
}
