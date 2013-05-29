package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.IDataStore;

public class BinaryFragmentCacheTests extends CacheTests {
	@Override
	protected FragmentedModel createFragmentedModel(IDataStore dataStore, int cacheSize, EPackage metaModel) {
		return new FragmentedModel(dataStore, cacheSize) {
			@Override
			protected Fragment newFragment(URI uri, FragmentedModel model) {
				return new BinaryFragmentImpl(uri, model);
			}			
		};
	}
}
