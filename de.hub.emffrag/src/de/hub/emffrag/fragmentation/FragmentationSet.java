package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import de.hub.emffrag.datastore.IDataStore;

public class FragmentationSet {

	public interface IDataStoreFactory {
		IDataStore createDataStore(URI uri);		
	}
	
	private final Map<URI, Fragmentation> fragmentations = new HashMap<URI, Fragmentation>();
	private final IDataStoreFactory dataStoreFactory;
	private final int fragmentationCacheSize;
	
	public FragmentationSet(IDataStoreFactory dataStoreFactory, int fragmentationCacheSize) {
		this.dataStoreFactory = dataStoreFactory;
		this.fragmentationCacheSize = fragmentationCacheSize;
	}
	
	private Fragmentation createFragmentation(URI uri) {
		Fragmentation existingFragmentation = fragmentations.get(uri);
		if (existingFragmentation == null) {
			Fragmentation result = new Fragmentation(dataStoreFactory.createDataStore(uri), fragmentationCacheSize);
			result.setFragmentationSet(this);
			fragmentations.put(uri, result);
			return result;
		} else {
			throw new IllegalArgumentException("Fragmentation already exists");
		}
	}
	
	public Fragmentation getFragmentation(URI uri) {
		Fragmentation existingFragmentation = fragmentations.get(uri);
		if (existingFragmentation == null) {
			existingFragmentation = createFragmentation(uri);
		}
		
		return existingFragmentation;
	}
	
	public FObject getFObject(URI uri, boolean loadOnDemand) {
		Fragmentation fragmentation = getFragmentation(uri.trimFragment().trimSegments(1));
		return fragmentation.getFObject(uri, loadOnDemand);
	}
	
	public void close() {
		for (Fragmentation fragmentation: fragmentations.values()) {
			fragmentation.close();
			fragmentation.setFragmentationSet(null);
		}
		fragmentations.clear();
	}
}
