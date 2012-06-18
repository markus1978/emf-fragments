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

import org.apache.commons.collections.map.ReferenceMap;
import org.eclipse.emf.ecore.EObject;

public class FObjectCache {

	private ReferenceMap eObjectCache = new ReferenceMap(ReferenceMap.HARD, ReferenceMap.WEAK);
//	private Map<EObject, MyWeakReference> eObjectCache = new HashMap<EObject, MyWeakReference>();
//	private ReferenceQueue<EObject> lostRerefences = new ReferenceQueue<EObject>();
	
//	private class MyWeakReference extends WeakReference<EObject> {
//		private final EObject fiObject;
//		public MyWeakReference(EObject fiObject, EObject eObject, ReferenceQueue<? super EObject> q) {
//			super(eObject, q);
//			this.fiObject = fiObject;
//		}		
//	}
//	
	public EObject getEObject(EObject fiObject) {
//		MyWeakReference reference = eObjectCache.get(fiObject);
//		if (reference == null) {
//			return null;
//		} else {
//			EObject eObject = reference.get();
//			if (eObject == null) {
//				removeLostReferences();
//				return null;
//			} else {
//				return eObject;
//			}
//		}
		return (EObject)eObjectCache.get(fiObject);		
	}
	
	private void removeLostReferences() {
//		MyWeakReference reference = (MyWeakReference)lostRerefences.poll();
//		while (reference != null) {
//			eObjectCache.remove(reference.fiObject);
//			reference = (MyWeakReference)lostRerefences.poll();
//		}
	}
	
	public boolean hasReferences() {
		removeLostReferences();	
		return !eObjectCache.isEmpty();
	}

	public void addEObject(EObject fiObject, EObject eObject) {
//		eObjectCache.put(fiObject, new MyWeakReference(fiObject, eObject, lostRerefences));
		eObjectCache.put(fiObject, eObject);
	}

	public void removeEObject(EObject fiObject, EObject eObject) {
		eObjectCache.remove(fiObject);
	}
}
