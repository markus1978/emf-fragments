package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NoReferencesIdSemantics extends IndexBasedIdSemantics {

	
	public NoReferencesIdSemantics(IdBehaviour behavior) {
		super(behavior);
	}

	@Override
	public void onObjectAsReferenced(FInternalObjectImpl internalObject) {
		// nothing
	}

	@Override
	public URI getURI(FInternalObjectImpl internalObject, FragmentedModel model, boolean forceIssue) {
		return EcoreUtil.getURI(internalObject);
	}

	@Override
	public void onObjectSaved(FInternalObjectImpl fInternalObject) {
		// nothing
	}

	@Override
	public void onContainerChange(FInternalObjectImpl object, FragmentedModel model) {
		// nothing
	}

	@Override
	public FInternalObject resolveURI(URI uri, FragmentedModel model) {
		return (FInternalObject)model.getInternalResourceSet().getEObject(uri, true);
	}

	
}
