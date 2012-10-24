package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class Fragment extends XMIResourceImpl {
	
	private final UserObjectsCache userObjectsCache = new UserObjectsCache();
	private FragmentedModel model = null;
	
	public Fragment() {
		super();
	}

	public Fragment(URI uri) {
		super(uri);
	}

	public UserObjectsCache getUserObjectsCache() {
		return userObjectsCache;
	}
		
	protected void setFragmentedModel(FragmentedModel model) {
		this.model = model;
	}

	public FragmentedModel getFragmentedModel() {
		return model;
	}
	
}
