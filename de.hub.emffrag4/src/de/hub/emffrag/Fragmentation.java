package de.hub.emffrag;

import de.hub.emffrag.datastore.IDataStore;

public interface Fragmentation {
	public <T extends FObject> T getRoot();

	/**
	 * Set the root object of the fragmentation. A fragmentation can only have
	 * one root object, which can be set exactly one time.
	 * 
	 * @param root
	 *            The root object.
	 * @throws IllegalArgumentException
	 *             if the fragmentation already has a root.
	 */
	public void setRoot(FObject root);

	/**
	 * Unloads all open fragments. Clients must call this method before they can
	 * discard the fragmentation.
	 */
	public void close();
	
	public IDataStore getDataStore();
	
	public FragmentationSet getFragmentationSet();
}
