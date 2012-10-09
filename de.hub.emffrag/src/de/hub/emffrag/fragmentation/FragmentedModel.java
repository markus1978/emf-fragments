package de.hub.emffrag.fragmentation;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class FragmentedModel {

	private final ResourceSet resourceSet;
	private Fragment rootFragment;
	private final FragmentCache fragmentCache;
	
	private final Collection<EPackage> metaModelPackages;
	private final IKeyValueTable persistence;
	private final String rootFragmentKey;
	
	private class FragmentCache {
		
	}
	
	public FragmentedModel(IKeyValueTable persistence, String rootFragmentKey, Collection<EPackage> metaModel) {
		this.persistence = persistence;
		this.metaModelPackages = metaModel;
		this.rootFragmentKey = rootFragmentKey;
		
		resourceSet = new ResourceSetImpl();
		fragmentCache = new FragmentCache();
		rootFragment = null;
	}
	
	public EList<EObject> getRootContents() {
		return getRootFragment().getContents();
	}

	public Fragment getRootFragment() {
		if (rootFragment == null) {
			loadRootFragment();
		}
		return rootFragment;
	}

	private void loadRootFragment() {
		// TODO
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}
	
	
}
