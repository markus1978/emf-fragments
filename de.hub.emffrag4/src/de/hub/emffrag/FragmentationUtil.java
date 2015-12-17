package de.hub.emffrag;

import org.eclipse.emf.ecore.EReference;

public class FragmentationUtil {
	public static boolean isFragmenting(EReference reference) {
		return reference.getEAnnotations().size() > 0; // TODO
	}
}
