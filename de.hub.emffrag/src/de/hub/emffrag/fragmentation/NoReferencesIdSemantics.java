package de.hub.emffrag.fragmentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NoReferencesIdSemantics extends IndexBasedIdSemantics {

	private final MultiMap saves = new MultiValueMap();

	// Sometimes a references and not yet contained (thus no actual fragmented
	// model URI) elements are references by objects that can unloaded. When
	// their are loaded, also their references are resolved. We use special
	// save://<id> URIs for these elements.
	private final Map<String, FInternalObjectImpl> saveKeyToSavedObject = new HashMap<String, FInternalObjectImpl>();
	private final Map<FInternalObjectImpl, String> savedObjectToSaveKey = new HashMap<FInternalObjectImpl, String>();

	private long unique = 0;

	public NoReferencesIdSemantics(IdBehaviour behavior) {
		super(behavior);
	}

	@Override
	public void onObjectAsReferenced(FInternalObjectImpl internalObject) {
		// nothing
	}

	@Override
	public URI getURI(FInternalObjectImpl internalObject, FragmentedModel model, boolean forceIssue, SaveURI save) {
		if (internalObject.eIsProxy()) {
			return internalObject.eProxyURI();
		} else if (internalObject.getFragment() == null) {
			Collection<?> saves = (Collection<?>) this.saves.get(internalObject);
			if (save == null) {
				throw new RuntimeException("No save given for an object that is not contained in a fragment. Cannot create a URI for this object.");
			} else {
				if (saves == null || !saves.contains(save)) {
					// The save is later used to save the real URI, once we can
					// assign it. This is usually done, when the resource the
					// the not yet contained element is added to is saved.
					this.saves.put(internalObject, save);

					// The save URI is used, as long no real URI could be saved.
					String saveKey = "" + unique++;
					saveKeyToSavedObject.put(saveKey, internalObject);
					savedObjectToSaveKey.put(internalObject, saveKey);
					return URI.createURI("save://" + saveKey);
				} else {
					return URI.createURI("save://" + savedObjectToSaveKey.get(internalObject));
				}
			}
		} else {
			URI uri = EcoreUtil.getURI(internalObject);
			return uri;
		}
	}

	@Override
	public void onObjectSaved(FInternalObjectImpl internalObject) {
		Collection<?> saves = (Collection<?>) this.saves.get(internalObject);
		if (saves != null) {
			Fragment fragment = internalObject.getFragment();
			URI uri = null;
			if (fragment != null) {
				// the object not longer needs a save, since is has been added
				// to the fragmented model
				uri = EcoreUtil.getURI(internalObject);
				this.saves.remove(internalObject);
				this.saveKeyToSavedObject.remove(savedObjectToSaveKey.get(internalObject));
				this.savedObjectToSaveKey.remove(internalObject);
			} else {
				// as long as the object is not part of the model, we use a save
				// reference to refer to this object
				uri = URI.createURI("save://" + savedObjectToSaveKey.get(internalObject));				
			}
			for (Object saveObj : saves) {
				((SaveURI) saveObj).saveURI(uri);
			}
		}
	}

	@Override
	public void onContainerChange(FInternalObjectImpl object, FragmentedModel model) {
		// nothing
	}

	@Override
	public FInternalObject resolveURI(URI uri, FragmentedModel model) {	
		if (uri.scheme().equals("save")) {
			String saveKey = uri.device();
			return saveKeyToSavedObject.get(saveKey);
		} else if (uri.scheme().equals("mongodb") || uri.scheme().equals("hbase") || uri.scheme().equals("memory")) { // TODO this looks not right
			return (FInternalObject) model.getInternalResourceSet().getEObject(uri, true);
		} else {
			throw new RuntimeException("Discovered an unknown URI.");
		}
	}

}
