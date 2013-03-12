package de.hub.emffrag.fragmentation;

import java.util.Collection;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NoReferencesIdSemantics extends IndexBasedIdSemantics {

	private MultiMap saves = new MultiValueMap();

	public NoReferencesIdSemantics(IdBehaviour behavior) {
		super(behavior);
	}

	@Override
	public void onObjectAsReferenced(FInternalObjectImpl internalObject) {
		// nothing
	}

	@Override
	public URI getURI(FInternalObjectImpl internalObject, FragmentedModel model, boolean forceIssue, SaveURI save) {
		URI uri = EcoreUtil.getURI(internalObject);
		if (internalObject.getFragment() == null) {
			Collection<?> saves = (Collection<?>)this.saves.get(internalObject);
			if (save != null && (saves == null || !saves.contains(save))) {
				this.saves.put(internalObject, save);
			}
		}
		return uri;		
	}

	@Override
	public void onObjectSaved(FInternalObjectImpl internalObject) {
		Collection<?> saves = (Collection<?>)this.saves.get(internalObject);
		if (saves != null) {
			URI uri = EcoreUtil.getURI(internalObject);
			for(Object saveObj: saves) {
				((SaveURI)saveObj).saveURI(uri);
			}
		}
	}

	@Override
	public void onContainerChange(FInternalObjectImpl object, FragmentedModel model) {
		// nothing
	}

	@Override
	public FInternalObject resolveURI(URI uri, FragmentedModel model) {
		return (FInternalObject) model.getInternalResourceSet().getEObject(uri, true);
	}

}
