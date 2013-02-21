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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EMFFragUtil {

	/**
	 * @param An {@link EReference}, must not be null.
	 * @return true, iff the given reference is designated as a cross fragment reference.
	 */
	public static boolean isFragReference(EReference reference) {
		EAnnotation annotation = reference.getEAnnotation("de.hub.emffrag");
		return annotation != null && annotation.getDetails().get("fragments") != null;
	}

	/**
	 * @param An {@link EStructuralFeature}, must not be null.
	 * @return true, iff the given feature is a reference and is designated as a cross fragment reference.
	 */
	public static boolean isFragFreature(EStructuralFeature feature) {
		return feature instanceof EReference && isFragReference((EReference)feature);
	}
}
