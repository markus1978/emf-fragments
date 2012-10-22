package de.hub.emffrag.fragmentation;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

public class FragmentedModel {

	private final ResourceSet resourceSet;
	private Fragment rootFragment;
	private final FragmentCache fragmentCache; // TODO handle fragment caching issues

	private final Collection<EPackage> metaModelPackages; // TODO investigate the need for internal and user meta-models
	private final DataStore persistence;
	private final DataIndex<Long> fragmentIndex;
	private final URI rootFragmentKeyURI;

	private class FragmentCache {

	}

	public FragmentedModel(DataStore persistence, URI rootFragmentKeyURI, Collection<EPackage> metaModel) {
		this.persistence = persistence;
		this.fragmentIndex = new DataIndex<Long>(persistence, "f", LongKeyType.instance);
		this.metaModelPackages = metaModel;
		this.rootFragmentKeyURI = (rootFragmentKeyURI == null ? createNewFragmentURI() : rootFragmentKeyURI);

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
		rootFragment = (Fragment)resourceSet.getResource(rootFragmentKeyURI, true);
		if (rootFragment == null) {
			// it is a totally new model
			rootFragment = (Fragment)resourceSet.createResource(rootFragmentKeyURI);
		}
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * Creates a new fragment. That means it creates a resource (fragment), adds
	 * it to the resource set, and creates an appropriate entry in the
	 * persistence.
	 * 
	 * @param fragmentRoot
	 *            The object that is going to be the root of the new fragment.
	 *            It can be a part of an existing fragment or something from the
	 *            realm of new objects.
	 * @param fragmentRootUserObject
	 * 			  The user object of the fragment root. Can be null.
	 * @param containingObject
	 *            The object that contains the root. It can be null, if this
	 *            method is called for the root fragment.
	 * @param containmentFeature
	 *            Can be null (see containingObject)
	 */
	public void crateFragment(FInternalObjectImpl fragmentRoot, FObjectImpl fragmentRootUserObject, InternalEObject containingObject, EStructuralFeature containmentFeature) {
		Fragment newFragment = (Fragment) resourceSet.createResource(createNewFragmentURI());
		newFragment.setFragmentedModel(this);
		Resource oldResource = fragmentRoot.eResource();
		Fragment oldFragment = null;
		if (oldResource != null && oldResource instanceof Fragment) {
			oldFragment = (Fragment)oldResource;
		}
		newFragment.getContents().add(fragmentRoot);
		if (fragmentRootUserObject != null) {
			newFragment.getUserObjectsCache().addUserObjectToCache(fragmentRoot, fragmentRootUserObject);
		}
		if (oldFragment != null) {
			oldFragment.getUserObjectsCache().removeCachedUserObject(fragmentRoot);
		}
		// TODO handle fragment caching issues
	}

	/**
	 * Creates a new fragment in persistence.
	 * @return The URI for a new fragment.
	 */
	private URI createNewFragmentURI() {
		return URI.createURI(persistence.getURIString()).appendSegment(new String(LongKeyType.instance.serialize(fragmentIndex.add())));
	}

	/**
	 * Removes the fragment of the given root. Removes the resource and the corresponding entry in persistence.
	 * 
	 * @param fObjectImpl The root of the fragment to delete.
	 */
	public void removeFragment(FInternalObjectImpl fragmentRoot) {
		Fragment oldFragment = fragmentRoot.getFragment();
		try {
			oldFragment.setFragmentedModel(null);
			oldFragment.delete(null);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		// TODO handle fragment caching issues
	}

}
