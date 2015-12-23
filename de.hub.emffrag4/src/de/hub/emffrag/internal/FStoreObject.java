package de.hub.emffrag.internal;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.hub.emffrag.FURI;

public interface FStoreObject {

	public EClass fClass();
	public void fSetClass(EClass eClass);
	
	public Object fGet(EStructuralFeature feature);
	public void fSet(EStructuralFeature feature, Object value);
	public boolean fIsSet(EStructuralFeature feature);
	public void fUnSet(EStructuralFeature feature);
	
	public boolean fIsRoot();
	public FStoreObject fRoot();
	public int fFragmentID();
	public void fSetFragmentID(FStoreFragmentation fragmentation, int fragmentID);
	public FStoreFragmentation fFragmentation();
	
	public void fSetContainer(FStoreObject container, EReference containingFeature);
	public FStoreObject fContainer();
	public EReference fContainingFeature();
	
	public boolean fIsProxy();
	public FURI fCreateURI();
	public FURI fProxyURI();
	public FURI fUnload(FURI uri);
	
	public boolean fModified();
	public void fMarkModified(boolean modified);
	
	public Iterable<FStoreObject> fContents();
	public Iterable<FStoreObject> fAllContents();
}
