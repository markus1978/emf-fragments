package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;

public interface IdSemantics {

	public void onRequiredId(FInternalObjectImpl internalObject);

	public void onObjectAsReferenced(FInternalObjectImpl internalObject);

	public URI getURI(FInternalObjectImpl internalObject, FragmentedModel model, boolean forceIssue);

	public String getPrefixID(FInternalObjectImpl object);

	public void onObjectSaved(FInternalObjectImpl fInternalObject);

	public FInternalObject resolveURI(URI uri, FragmentedModel model);

	public void onContainerChange(FInternalObjectImpl fInternalObjectImpl, FragmentedModel model);
}
