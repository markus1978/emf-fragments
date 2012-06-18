/*******************************************************************************
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.hub.emffrag.reflective;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.xmi.XMIResource;

public class FDynamicEObjectImpl extends DynamicEObjectImpl {

	private boolean isCrossReferenced = false;

	public FDynamicEObjectImpl(EClass eClass) {
		super(eClass);
	}

	public void trulyUnload(Fragment fragment) {
		if (eProperties != null) {
			eProperties.setEContents(null);
			eProperties.setECrossReferences(null);
		}
		eSettings = null;
	}

	/**
	 * Remember if the object gets cross references with this inverse add. Set
	 * an new ID if the object is in a resource and does not already have an ID
	 * assigned.
	 */
	public void setIsCrossReferenced() {
		if (!isCrossReferenced) {
			isCrossReferenced = true;
			XMIResource eResource = (XMIResource) eResource();
			if (eResource != null && eResource.getID(this) == null) {
				String id = FStoreImpl.INSTANCE.updateOIDTable(null, this);
				eResource.setID(this, id);
			}
		}
	}

	/**
	 * If the object changes resources, its cross referenced status has to move
	 * too. Further, the id table needs to be updated.
	 */
	@Override
	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
		XMIResource newResource = (XMIResource) eResource();
		XMIResource oldResource = newResource;
		String id = null;
		if (oldResource != null) {
			id = oldResource.getID(this);
		}
		NotificationChain result = super.eSetResource(resource, notifications);
		if (isCrossReferenced || id != null) {
			id = FStoreImpl.INSTANCE.updateOIDTable(id, this);
			newResource.setID(this, id);
		}
		return result;
	}

}
