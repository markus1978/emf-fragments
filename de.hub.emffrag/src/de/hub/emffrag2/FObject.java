package de.hub.emffrag2;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * This is an optional interface to objects contained in a fragmented model,
 * i.e. {@link Fragmentation}.
 * 
 * Optional means that you can generate your EMF interfaces as extensions to
 * {@link FObject}, but you can it also leave with {@link EObject}, as long as
 * the implementations extends {@link FObjectImpl}. Thus, it is often enough to
 * generate new implementations and meta-data and reuse existing interfaces,
 * when adapting existing meta-models for the use with EMF-Fragments.
 */
public interface FObject extends EObject {

	/**
	 * @return true iff the object is at the top of the containment hierarchy
	 *         within the resource that contains it.
	 */
	public boolean fIsRoot();

	/**
	 * {@link #eIsProxy()} will cause the object to be loaded and provided
	 * everything works as expected, should always return false. This we
	 * hide the actual proxy status. This method can be used to determine
	 * the current actual proxy status.s
	 */
	public boolean fIsProxy();

	/**
	 * @return True, iff the object is currently loaded.
	 */
	public boolean fIsUnLoaded();

	/**
	 * @return The {@link Fragment} that this object belongs to. Null if it is
	 *         not part of a fragmentation yet.
	 */
	public Fragment fFragment();

	/**
	 * @return the fragmentation this object belongs to or null, if the object
	 *         was not added to a fragmentation yet.
	 */
	public Fragmentation fFragmentation();

	/**
	 * Ensures that this {@link FObject} and its fragment is loaded. This is
	 * automatically called when ever a client accesses an {@link FObject}.
	 * 
	 * An access is only detected if the object is accessed via the reflective
	 * methods in {@link EObject}:
	 * {@link EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)},
	 * {@link EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature, Object)},
	 * {@link EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)},
	 * {@link EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)},
	 * {@link EObject#eInvoke(org.eclipse.emf.ecore.EOperation, org.eclipse.emf.common.util.EList)}
	 * . If you do something fancy, e.g. calling {@link InternalEObject}
	 * specific methods or you cannot generate reflective implementations, etc.
	 * then you might have to call this method manually at the appropriate
	 * places.
	 * 
	 * TODO this should be moved to {@link Fragmentation} which can better
	 * control load/unloads and the fragments cache including its locks.
	 */
	public void fEnsureLoaded();

}