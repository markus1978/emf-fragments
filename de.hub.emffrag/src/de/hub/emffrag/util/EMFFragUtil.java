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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.fragmentation.FInternalObject;
import de.hub.emffrag.fragmentation.Fragment;

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
	
	@SuppressWarnings("unchecked")
	public static void collectAllNonFragmentingContents(FInternalObject object, Collection<FInternalObject> collection) {
		if (object == null) {
			return;
		}
		collection.add(object);
		for (EStructuralFeature feature: object.eClass().getEStructuralFeatures()) {
			if (feature instanceof EReference &&
					((EReference)feature).isContainment() &&
					EMFFragUtil.getFragmentationType((EReference)feature) == FragmentationType.None) {
				if (feature.isMany()) {
					for (FInternalObject child: (EList<FInternalObject>)object.eGet(feature)) {
						collectAllNonFragmentingContents(child, collection);	
					}
				} else {
					collectAllNonFragmentingContents((FInternalObject)object.eGet(feature), collection);
				}
			}
		}
	}
	
	public static Collection<FInternalObject> getAllNonFragmentingContents(FInternalObject object) {
		Collection<FInternalObject> result = new ArrayList<FInternalObject>();
		collectAllNonFragmentingContents(object, result);
		return result;
	}
	
	public static Collection<FInternalObject> getAllNonFragmentingContents(Fragment fragment) {
		Collection<FInternalObject> result = new ArrayList<FInternalObject>();
		for (EObject object: fragment.getContents()) {
			collectAllNonFragmentingContents((FInternalObject)object, result);
		}
		return result;
	}
	
	public static TreeIterator<EObject> getAllNonFragmentingContentsIterator(EObject eObject) {
		return new AbstractTreeIterator<EObject>(eObject) {
			@Override
			protected Iterator<? extends EObject> getChildren(Object o) {
				return prepareChildrenList((EObject)o).iterator();
			}			
		};
	}
	
	@SuppressWarnings("unchecked")
	private static Collection<EObject> prepareChildrenList(EObject object) {
		Collection<EObject> children = new ArrayList<EObject>();
		for (EStructuralFeature feature: object.eClass().getEAllStructuralFeatures()) {
			if (feature instanceof EReference &&
					((EReference)feature).isContainment() &&
					EMFFragUtil.getFragmentationType((EReference)feature) == FragmentationType.None) {
				if (feature.isMany()) {
					children.addAll((EList<EObject>)object.eGet(feature));
				} else {
					EObject value = (EObject)object.eGet(feature);
					if (value != null) {
						children.add(value);
					}
				}
			}
		}
		return children;
	}

	public static TreeIterator<EObject> getAllNonFragmentingContentsIterator(List<EObject> contents) {
		return new AbstractTreeIterator<EObject>(contents, false) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			protected Iterator<? extends EObject> getChildren(Object o) {
				if (o instanceof List) {
					return ((List)o).iterator();
				} else {
					return prepareChildrenList((EObject)o).iterator();
				}
			}			
		};
	}
	
	public static boolean test(EObject object, String className, String attributeName, Object attributeValue) {
		EClass eClass = object.eClass();
		if (eClass.getName().equals(className)) {
			EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(attributeName);
			if (attributeValue.equals(object.eGet(eStructuralFeature))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
