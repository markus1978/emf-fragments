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
package de.hub.emffrag.util;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EMFFragUtil {
	
	public enum FragmentationType { None, FragmentsContainment, FragmentsIndexedContainment, IndexedReferences };
	
	public static FragmentationType getFragmentationType(EStructuralFeature feature) {
		EAnnotation annotation = feature.getEAnnotation("de.hub.emffrag");
		if (!(feature instanceof EReference) || annotation == null) {
			return FragmentationType.None;
		} else { 
			EMap<String, String> details = annotation.getDetails();
			if (((EReference)feature).isContainment()) {			
				if (details.get("indexes") != null) {
					return FragmentationType.FragmentsIndexedContainment;
				} else if (details.get("fragments") != null) {
					return FragmentationType.FragmentsContainment;
				} else {
					return FragmentationType.None;
				}
			} else {
				if (details.get("indexes") != null) {
					return FragmentationType.IndexedReferences;
				} else {
					return FragmentationType.None;
				}
			}
		}
	}
}
