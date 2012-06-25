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

import java.util.Comparator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class Fragment extends XMIResourceImpl {
	
	private final FObjectCache cache = new FObjectCache();
	private boolean isNew = false;
	private boolean isWeaklyUnloaded = false;
	private long lastWeakLoad = -1;
	
	private static class CompareByLastWeakLoad implements Comparator<Fragment> {
		@Override
		public int compare(Fragment one, Fragment two) {
			return new Long(two.lastWeakLoad).compareTo(one.lastWeakLoad);		
		}	
	}
	
	public static Comparator<Fragment> lastWeakLoadComparator = new CompareByLastWeakLoad();
	
	public Fragment(URI uri) {
		super(uri);
	}
	
	public FObjectCache getCache() {
		if (isWeaklyUnloaded) {
			isWeaklyUnloaded = false;
			lastWeakLoad = System.currentTimeMillis();
			((FragmentedModel)getResourceSet()).weaklyLoad(this);
		}
		return cache;
	}
	
	public void weaklyUnload() {
		isWeaklyUnloaded = true;
	}
	
	public static class Factory implements Resource.Factory {
		
		public static final Factory INSTANCE = new Factory();
		
		@Override
		public Resource createResource(URI uri) {
			return new Fragment(uri);
		}
	}

	public void setAsNew() {
		this.isNew = true;		
	}
	
	public boolean isNew() {
		return isNew;
	}

	@Override
	protected void unloaded(InternalEObject internalEObject) {
		super.unloaded(internalEObject);
		FDynamicEObjectImpl dynamicEObjectImpl = (FDynamicEObjectImpl)internalEObject;
		dynamicEObjectImpl.trulyUnload(this);	
	}
	
	
//	@Override
//	protected XMLSave createXMLSave() {
//		return new XMISaveImpl(createXMLHelper()) {
//			@Override
//			protected void saveHref(EObject remote, EStructuralFeature f) {
//				if (f instanceof EReference && !((EReference)f).isContainment()) {
//					XMIResource eResource = (XMIResource)remote.eResource();
//					if (eResource != null) {						
//						eResource.setID(remote, "newExtrID");
//					}
//				}
//				super.saveHref(remote, f);
//			}			
//		};
//	}

//	@Override
//	protected XMLHelper createXMLHelper() {
//		return new XMLHelperImpl(this) {
//			@Override
//			protected URI getHREF(Resource otherResource, EObject obj) {
//				return super.getHREF(otherResource, obj);
//			}			
//		};
//	}

	@Override
	protected EObject getEObjectByID(String id) {
		EObject eObject = super.getEObjectByID(id);
		if (eObject == null) {
			eObject = FStoreImpl.INSTANCE.resolve(id);
		}
		return eObject;
	}

}
