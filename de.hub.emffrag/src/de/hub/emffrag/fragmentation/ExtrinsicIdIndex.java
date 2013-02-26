package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import de.hub.emffrag.datastore.DataIndex;
import de.hub.emffrag.datastore.DataStore;
import de.hub.emffrag.datastore.LongKeyType;

/**
 * Each {@link FragmentedModel} holds an {@link ExtrinsicIdIndex}. This index
 * maps extrinsic IDs (UUIDs for single objects) to object URIs. This are URIs
 * that locate the actual objects within their respective fragments.
 * 
 * An extrinsic ID is associated with an extrinsic ID URI. These URIs identify
 * objects based on their ID and not their location within their respective
 * fragments. Thus extrinsic IDs are independent of the location of the
 * identified object.
 * 
 * Not all objects have an extrinsic ID. An extrinsic ID is only issues if
 * necessary (e.g. when the object is referenced by other objects). Once an
 * extrinsic ID is issues, an object never looses this ID.
 * 
 * Extrinsic IDs are persisted within fragments as normal xmi IDs, or in EMF
 * terminology as "extrinsic IDs".
 * 
 * The index uses the prefix "c_" for historic reasons. It once was only
 * designed to manage IDs for (c)ross references objects.
 */
public class ExtrinsicIdIndex extends DataIndex<Long> {

	public ExtrinsicIdIndex(DataStore store) {
		super(store, FragmentedModel.EXTRINSIC_ID_INDEX_PREFIX, LongKeyType.instance);
	}

	/**
	 * Extracts the extrinsic ID from the given extrinsic ID URI. This is the
	 * reverse of {@link #createExtrinsicIdUri(String)}.
	 */
	public String getExtrinsicId(URI extrinsicURI) {
		return Long.toString(getKeyFromURI(extrinsicURI));
	}

	/**
	 * Constructs an extrinsic ID URI from the given extrinsic ID. This is the
	 * reverse of {@link #getExtrinsicId(URI)}.
	 */
	public URI createExtrinsicIdUri(String extrinsicID) {
		return getURI(Long.parseLong(extrinsicID));
	}

	/**
	 * Changes (or adds) the object URI for an object with extrinsic ID. This is
	 * used when the object moves and its object URI changes.
	 * 
	 * @param extrinsic
	 *            ID, the extrinsic ID of the given object or null, if the
	 *            object needs one issued.
	 * @param object
	 *            The IDed object.
	 */
	public void updateObjectURI(String extrinsicId, FInternalObjectImpl object) {
		if (extrinsicId == null) {
			issueExtrinsicID(object);
		} else {
			Resource resource = object.eResource();
			if (resource != null) { // TODO check if the object was moved to a different fragmented model
				URI objectURI = resource.getURI().appendFragment(resource.getURIFragment(object));
				set(Long.parseLong(extrinsicId), objectURI.toString());
			} else {
				remove(Long.parseLong(extrinsicId));
			}
		}
	}
	
	public String issueExtrinsicID(FInternalObjectImpl object) {		
		String extrinsicId = Long.toString(add());
		Resource resource = object.eResource();
		if (resource == null || !(resource instanceof Fragment)) {
			throw new RuntimeException("Only objects in a fragmented model can have an extrinsic id.");
		}
		((Fragment)resource).setID(object, extrinsicId);
		URI objectURI = resource.getURI().appendFragment(resource.getURIFragment(object));
		set(Long.parseLong(extrinsicId), objectURI.toString());
		return extrinsicId;		
	}

	/**
	 * @return The object URI for the given extrinsic ID URI.
	 */
	public URI getObjectUriForExtrinsicIdUri(URI uri) {
		return URI.createURI(get(getKeyFromURI(uri)));
	}

}
