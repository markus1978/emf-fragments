package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.DataStore;

public class BinaryFragmentCrossReferenceTests extends CrossReferenceTests {

	@Override
	protected FragmentedModel createFragmentedModel(DataStore dataStore, URI rootFragmentURI, int cacheSize, EPackage metaModel) {
		return new FragmentedModel(dataStore, rootFragmentURI, cacheSize, metaModel) {
			@Override
			protected Fragment createFragment(URI uri, FragmentedModel model) {
				return new BinaryFragmentImpl(uri, model);
			}			
		};
	}
}
