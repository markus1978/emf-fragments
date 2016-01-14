package de.hub.emffrag;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.internal.FStore;
import de.hub.emffrag.internal.FStoreFragmentation;
import de.hub.emffrag.internal.FStoreFragmentationSet;
import de.hub.emffrag.internal.FStoreObject;

public class FragmentationImpl implements Fragmentation {
	private final FragmentationSet set;
	
	private final FStoreFragmentation fStoreFragmentation;

	public FragmentationImpl(List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		this(null, null, packages, dataStore, fragmentsCacheSize);
	}
	
	public FragmentationImpl(final FragmentationSet set, URI uri, List<EPackage> packages, IDataStore dataStore, int fragmentsCacheSize) {
		this.set = set;
		fStoreFragmentation = new FStoreFragmentation(new FStoreFragmentationSet() {			
			@Override
			public FStoreFragmentation getFragmentation(URI uri) {
				return ((FragmentationImpl)set.getFragmentation(uri)).fStoreFragmentation;
			}
		}, uri, packages, dataStore, fragmentsCacheSize);
	}

	@SuppressWarnings("unchecked")
	public <T extends FObject> T getRoot() {
		FStoreObject fStoreRoot = fStoreFragmentation.getRoot();
		if (fStoreRoot == null) {
			return null;
		} else {
			return (T) FStore.fINSTANCE.proxyManager.getFObject(fStoreRoot);
		}
	}
	
	public void setRoot(FObject root) {
		fStoreFragmentation.setRoot(root.fStoreObject());
	}

	public FStoreFragmentation getFStoreFragmentation() {
		return fStoreFragmentation;
	}

	@Override
	public void close() {		
		fStoreFragmentation.close();
	}
	
	@Override
	public void save() {
		fStoreFragmentation.save();
	}

	@Override
	public IDataStore getDataStore() {
		return fStoreFragmentation.getDataStore();
	}

	@Override
	public FragmentationSet getFragmentationSet() {
		return set;
	}
}
