package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.EmfFragActivator.IdBehaviour;
import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

/**
 * Each {@link FragmentedModel} holds an {@link IdIndex}. This index
 * maps IDs (UUIDs for single objects) to object URIs. This are URIs
 * that locate the actual objects within their respective fragments.
 * 
 * An ID is associated with an ID URI. These URIs identify
 * objects based on their ID and not their location within their respective
 * fragments. Thus IDs are independent of the location of the
 * identified object.
 * 
 * Not all objects have an ID. An ID is only issues if
 * necessary (e.g. when the object is referenced by other objects). Once an
 * ID is issues, an object never looses this ID.
 * 
 * IDs are persisted within fragments as normal xmi IDs, or in EMF
 * terminology as "IDs".
 * 
 * The index uses the prefix "c_" for historic reasons. It once was only
 * designed to manage IDs for (c)ross references objects.
 */
public class IdIndex extends DataIndex<Long> {

	public IdIndex(DataStore store) {
		super(store, FragmentedModel.ID_INDEX_PREFIX, LongKeyType.instance);
	}

	/**
	 * Extracts the ID from the given ID URI. This is the
	 * reverse of {@link #createIdUri(String)}.
	 */
	public String getId(URI idURI) {
		return Long.toString(getKeyFromURI(idURI));
	}

	/**
	 * Constructs an ID URI from the given ID. This is the
	 * reverse of {@link #getId(URI)}.
	 */
	public URI createIdUri(String id) {
		return getURI(Long.parseLong(id));
	}

	/**
	 * Changes (or adds) the object URI for an object with ID. This is
	 * used when the object moves and its object URI changes.
	 * 
	 * @param id
	 *            , the ID of the given object or null, if the
	 *            object needs one issued.
	 * @param object
	 *            The IDed object.
	 */
	public void updateObjectURI(String id, FInternalObjectImpl object) {
		if (id == null) {
			issueId(object);
		} else {
			Resource resource = object.eResource();
			if (resource != null) { // TODO check if the object was moved to a different fragmented model
				URI objectURI = resource.getURI().appendFragment(resource.getURIFragment(object));
				set(Long.parseLong(id), objectURI.toString());
			} else {
				remove(Long.parseLong(id));
			}
		}
	}
	
	public String issueId(FInternalObjectImpl object) {		
		String id = Long.toString(add());
		Resource resource = object.eResource();
		if (resource == null || !(resource instanceof Fragment)) {
			if (EmfFragActivator.instance.idBehaviour != IdBehaviour.defaultModel) {
				throw new NotInAFragmentedModelException("Only objects in a fragmented model can have an id.");
			} else {
				set(Long.parseLong(id), "#never added and saved within a fragmented model#");
			}
		} else {
			URI objectURI = resource.getURI().appendFragment(resource.getURIFragment(object));
			set(Long.parseLong(id), objectURI.toString());
		}
		return id;		
	}

	/**
	 * @return The object URI for the given ID URI.
	 */
	public URI getObjectUriForIdUri(URI uri) {
		return URI.createURI(get(getKeyFromURI(uri)));
	}

}
