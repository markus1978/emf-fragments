package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EObject;

public interface FObject extends EObject {
	/**
	 * @return The {@link FragmentImpl} that this object belongs to. Null if it is
	 *         not part of a fragmentation yet.
	 */
	public Fragment fFragment();

	/**
	 * @return the fragmentation this object belongs to or null, if the object
	 *         was not added to a fragmentation yet.
	 */
	public Fragmentation fFragmentation();
	
	/**
	 * @return true iff the object is at the top of the containment hierarchy
	 *         within the resource that contains it.
	 */
	public boolean fIsRoot();
}
