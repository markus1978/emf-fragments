package de.hub.emffrag;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.IDataStore.IDataStoreFactory;

public class FragmentationSet {
	
	private final Map<URI, Fragmentation> fragmentations = new HashMap<URI, Fragmentation>();
	private final List<EPackage> packages;
	private final IDataStoreFactory dataStoreFactory;
	private final int fragmentationCacheSize;
	
	public FragmentationSet(List<EPackage> packages, IDataStoreFactory dataStoreFactory, int fragmentsCacheSize) {
		this.packages = packages;
		this.dataStoreFactory = dataStoreFactory;
		this.fragmentationCacheSize = fragmentsCacheSize;
	}
	
	private Fragmentation createFragmentation(URI uri) {
		Fragmentation existingFragmentation = fragmentations.get(uri);
		if (existingFragmentation == null) {
			Fragmentation result = new FragmentationImpl(this, uri, packages, dataStoreFactory.createDataStore(uri), fragmentationCacheSize);
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
	
	public Collection<Fragmentation> getFragmentations() {
		return fragmentations.values();
	}
	
	public FObject getFObject(URI uri) {
		return getFragmentation(uri).getRoot();
	}
	
	public void close() {
		for (Fragmentation fragmentation: fragmentations.values()) {
			fragmentation.close();
			fragmentation.getDataStore().close();
		}
		fragmentations.clear();
	}

	public void save() {
		for (Fragmentation fragmentation: fragmentations.values()) {
			fragmentation.save();			
		}
	}
}
