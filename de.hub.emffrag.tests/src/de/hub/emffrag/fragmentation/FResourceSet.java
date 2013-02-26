package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class FResourceSet extends ResourceSetImpl {
	@Override
	public Resource createResource(URI uri, String contentType) {
		return new FragmentedModel(uri);
	}
}
