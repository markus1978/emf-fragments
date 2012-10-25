package de.hub.emffrag.fragmentation;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import com.google.common.base.Throwables;

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

	@Override
	public void detached(EObject eObject) {
		super.detached(eObject);
		if (getContents().isEmpty()) {
			try {
				delete(null);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
	}
}
