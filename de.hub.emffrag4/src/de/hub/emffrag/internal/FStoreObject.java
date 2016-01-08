package de.hub.emffrag.internal;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

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
	
	/**
	 * Sets the container for this object. Updates fRoot and fIsRoot in this
	 * object and its content accordingly.
	 * 
	 * @param container
	 *            The container.
	 * @param containingFeature
	 *            The container feature that contains this object.
	 * @param isEmtpy
	 *            A performance hint that this object has no contents.
	 */
	public void fSetContainer(FStoreObject container, EReference containingFeature, boolean isEmtpy);
	public FStoreObject fContainer();
	public EReference fContainingFeature();
	
	public boolean fIsProxy();
	public FURI fCreateURI();
	public FURI fProxyURI();
	public FURI fUnload(FURI uri);
	
	public boolean fModified();
	public void fMarkModified(boolean modified);
	
	/**
	 * @return An unmodifiable iterable that allows to iterate the direct
	 *         contents of this object. Results do not contain the object
	 *         itself.
	 * @param onlyWithInSameFragment
	 *            If true, only objects that are contained in the same fragment
	 *            as this object.
	 */
	public Iterable<FStoreObject> fContents(boolean onlyWithInSameFragment);
	
	/**
	 * @return An unmodifiable iterable that allows to iterate all direct and
	 *         recursive contents of this object. Results do not contain the
	 *         object itself.
	 * @param onlyWithInSameFragment
	 *            If true, only objects that are contained in the same fragment
	 *            as this object.
	 */
	public Iterable<FStoreObject> fAllContents(boolean onlyWithInSameFragment);
}
