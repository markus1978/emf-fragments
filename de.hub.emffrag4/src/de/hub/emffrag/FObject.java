package de.hub.emffrag;

import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.internal.FStoreObject;

public interface FObject extends EObject {

	FStoreObject fStoreObject();

}