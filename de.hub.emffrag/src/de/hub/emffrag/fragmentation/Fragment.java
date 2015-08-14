package de.hub.emffrag.fragmentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.hub.emffrag.statistics.Statistic;
import de.hub.emffrag.statistics.Statistic.StatisticBuilder;
import de.hub.emffrag.statistics.services.Histogram;
import de.hub.emffrag.statistics.services.Summary;

public class Fragment extends UUIDBinaryResourceImpl {

	private static final Statistic fragmentSizeAtUnloadStatistic = new StatisticBuilder()
			.withService(new Summary())
			.withService(new Histogram())
			.register(Fragment.class, "FragmentSizeAtUnload");
	
	private final long id;
	private boolean isDeleted = false;

	// helper of #doUnload()
	private List<InternalEObject> contentToUnload = new ArrayList<InternalEObject>();

	public Fragment(URI uri, long id) {
		super(uri);
		this.id = id;
	}

	public long fFragmentId() {
		return id;
	}

	public boolean fIsRoot() {
		return id == 0l;
	}
	
	public void fDelete() {
		isDeleted = true;
	}
	
	public boolean fIsDeleted() {
		return isDeleted;
	}

	@Override
	public void eNotify(Notification notification) {
		if (notification.getFeatureID(Resource.class) != RESOURCE__IS_MODIFIED) {
			setModified(true);			
			Fragmentation fragmentation = getFragmentation();
			if (fragmentation != null && fIsRoot()) {
				fragmentation.onRootFragmentChange(notification);
			}			
		}
	}

	@Override
	public boolean eNotificationRequired() {
		return true;
	}

	/**
	 * Overridden to ensure resourceSet is {@link Fragmentation}
	 */
	@Override
	public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
		if (resourceSet != null && !(resourceSet instanceof Fragmentation)) {
			throw new IllegalStateException("Fragments can only be used within Fragmentations, not other resource sets.");
		}
		return super.basicSetResourceSet(resourceSet, notifications);
	}

	/**
	 * Overridden to not clear the contents list, but loose its reference. This
	 * allows {@link TreeIterator}s to work, even when fragments in it become
	 * unloaded.
	 */
	@Override
	protected void doUnload() {
		int numberOfUnloadedObjects = 0;
		
		Iterator<EObject> allContents = getAllProperContents(unloadingContents);

		getErrors().clear();
		getWarnings().clear();

		while (allContents.hasNext()) {
			// Do not unload directly out of the iterator, because it accesses
			// the children of the current object.
			//
			contentToUnload.add((InternalEObject) allContents.next());
			numberOfUnloadedObjects++;
		}

		// Removing reference, instead of clearing it. Will be re-instantiated
		// empty for next use.
		//
		contents = null;

		// Unload the collected object to unload
		//
		for (InternalEObject internalEObject : contentToUnload) {
			unloaded(internalEObject);
		}
		contentToUnload.clear();

		clearIDs();
		
		fragmentSizeAtUnloadStatistic.track(numberOfUnloadedObjects);
	}

	/**
	 * Perform a EMF-Fragments flavored unload via
	 * {@link FObjectImpl#eSetProxyURI(URI, Fragmentation)} not
	 * {@link InternalEObject#eSetProxyURI(URI)}.
	 * 
	 * Does not clear the adaptors like {@link #unloaded(InternalEObject)}. This
	 * is to make clients unaware of automatic background unloaded/loading.
	 */
	@Override
	protected void unloaded(InternalEObject internalEObject) {
		if (internalEObject instanceof FObjectImpl) {
			if (!((FObject)internalEObject).fIsProxy()) {
				FObjectImpl fObject = (FObjectImpl) internalEObject;
				
				// register all unloaded object with the user object cache
				getFragmentation().registerUserObject(Fragment.this, getID(fObject, true), fObject);
				
				fObject.fUnload(getFragmentation());
				fObject.eSetProxyURI(uri.appendFragment(getURIFragment(internalEObject)));
			}
		} else {
			super.unloaded(internalEObject);
		}
	}

	/**
	 * @return the fragmentation that this fragment belongs to.
	 */
	public Fragmentation getFragmentation() {
		return (Fragmentation) resourceSet;
	}
	
	@Override
	protected InternalEObject createProxy(InternalEObject internalEObject, URI proxyURI, boolean nop)
			throws IOException {
		if (internalEObject instanceof FObjectImpl) {
			// first, we try to find a (still) existing user object proxy
			FObjectImpl proxyObject = getFragmentation().getRegisteredUserObject(proxyURI);
			// second, we try to find an already loaded object
			if (proxyObject == null) {
				proxyObject = (FObjectImpl)getFragmentation().getEObject(proxyURI, false);
			}
			// third, we turn the object into an fObject-like proxy (i.e. with load from fragmentation reference).
			if (proxyObject == null) {
				proxyObject = (FObjectImpl)internalEObject;
				proxyObject.eSetProxyURI(proxyURI);
				proxyObject.fSetFragmentationToLoadFrom(getFragmentation());
			}
			return proxyObject;			
		} else {
			return super.createProxy(internalEObject, uri, nop);
		}
	}
	
	@Override
	protected InternalEObject createEObject(InternalEObject internalEObject, int extrinsicID) {
		if (internalEObject instanceof FObjectImpl) {
			// We try to reuse former objects that are still on
			// the heap
			Fragmentation fragmentation = getFragmentation();
			FObjectImpl fObject = fragmentation.getRegisteredUserObject(Fragment.this, extrinsicID);
			if (fObject != null) {
				fObject.eSetProxyURI(null);
				fObject.fSetFragmentationToLoadFrom(null);
				fObject.fSetItsLoadingIntoFragment(this);
				return fObject;
			} else {
				InternalEObject result = super.createEObject(internalEObject, extrinsicID);
				((FObjectImpl)result).fSetItsLoadingIntoFragment(this);		
				return result;
			}
		} else {
			return super.createEObject(internalEObject, extrinsicID);
		}		
	}
	
	

	@Override
	protected void afterLoad(InternalEObject internalEObject) {
		if (internalEObject instanceof FObjectImpl) {
			((FObjectImpl)internalEObject).fSetItsLoadingIntoFragment(null);
		}
		super.afterLoad(internalEObject);
	}

	@Override
	protected void beforeSaveFeature(InternalEObject internalEObject, int featureId) {
		Object value = ((FObjectImpl)internalEObject).fNoAccessDynamicGet(featureId);
		if (value instanceof EList<?>) {
			getFragmentation().getUserCaches().registerUserReference(fFragmentId(), getID(internalEObject, true), featureId, (EList<?>)value);
		}
	}
}
