package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;

public interface IdSemantics {

	public interface SaveURI {
		void saveURI(URI uri);
	}

	public void onRequiredId(FInternalObjectImpl internalObject);

	public void onObjectAsReferenced(FInternalObjectImpl internalObject);

	/**
	 * Returns the direct URI for the given {@link FInternalObjectImpl}.
	 * 
	 * @param forceIssue
	 *            true if an ID should be issued if necessary for the URI.
	 * @param saveURI
	 *            a callback that can be used to store the URI later, if no correct
	 *            URI can be given this time.
	 */
	public URI getURI(FInternalObjectImpl internalObject, FragmentedModel model, boolean forceIssue, SaveURI saveURI);

	public String getPrefixID(FInternalObjectImpl object);

	public void onObjectSaved(FInternalObjectImpl fInternalObject);

	public FInternalObject resolveURI(URI uri, FragmentedModel model);

	public void onContainerChange(FInternalObjectImpl fInternalObjectImpl, FragmentedModel model);
}
