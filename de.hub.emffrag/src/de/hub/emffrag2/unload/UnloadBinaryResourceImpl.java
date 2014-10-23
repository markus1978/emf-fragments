package de.hub.emffrag2.unload;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import de.hub.emffrag2.UUIDBinaryResourceImpl;

public class UnloadBinaryResourceImpl extends UUIDBinaryResourceImpl {

	public UnloadBinaryResourceImpl(URI uri) {
		super(uri);
	}

	@Override
	protected void unloaded(InternalEObject internalEObject) {
		super.unloaded(internalEObject);
		if (internalEObject instanceof UnloadEObjectImpl) {
			UnloadResourceSetImpl<?> uResourceSet = uResourceSet();
			UnloadEObjectImpl uObject = (UnloadEObjectImpl) internalEObject;
			uResourceSet.registerUserObject(this, uObject);
			uObject.uSetIsUnloadedFrom(uResourceSet);
		}
	}

	/**
	 * Modified super class bahaviour to an iterator that only returns each
	 * object once.
	 */
	@Override
	protected TreeIterator<EObject> getAllProperContents(List<EObject> contents) {
		final TreeIterator<EObject> base = super.getAllProperContents(contents);
		return new TreeIterator<EObject>() {
			Collection<EObject> iterated = new HashSet<EObject>();
			EObject current = null;

			@Override
			public boolean hasNext() {
				checkNext();
				return current != null;
			}

			private void checkNext() {
				while (current == null && base.hasNext()) {
					current = base.next();
					if (!iterated.add(current)) {
						current = null;
					}
				}
			}

			@Override
			public EObject next() {
				EObject result = current;
				current = null;
				checkNext();
				return result;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void prune() {
				throw new UnsupportedOperationException();
			}

		};
	}

	public UnloadResourceSetImpl<?> uResourceSet() {
		return (UnloadResourceSetImpl<?>) getResourceSet();
	}

	@Override
	protected InternalEObject createProxy(InternalEObject internalEObject, URI proxyURI, boolean nop) throws IOException {
		if (internalEObject instanceof UnloadEObjectImpl) {
			// first, we try to find a (still) existing user object proxy
			UnloadEObjectImpl proxyObject = uResourceSet().getRegisteredUserObject(this, (UnloadEObjectImpl) internalEObject);
			// second, we try to find an already loaded object
			if (proxyObject == null) {
				proxyObject = (UnloadEObjectImpl) uResourceSet().getEObject(proxyURI, false);
			}
			// third, we turn the object into an fObject-like proxy (i.e. with
			// load from fragmentation reference).
			if (proxyObject == null) {
				proxyObject = (UnloadEObjectImpl) internalEObject;
				proxyObject.eSetProxyURI(proxyURI);
				proxyObject.uSetIsUnloadedFrom(uResourceSet());
			}
			return proxyObject;
		} else {
			return super.createProxy(internalEObject, uri, nop);
		}
	}

	@Override
	protected InternalEObject createEObject(InternalEObject internalEObject, int extrinsicID) {
		if (internalEObject instanceof UnloadEObjectImpl) {
			// We try to reuse former objects that are still on
			// the heap
			internalEObject.eSetProxyURI(this.getURI().appendFragment(Integer.toString(extrinsicID)));
			UnloadEObjectImpl uObject = uResourceSet().getRegisteredUserObject(this, (UnloadEObjectImpl) internalEObject);
			if (uObject != null) {
				uObject.eSetProxyURI(null);
				uObject.uSetIsUnloadedFrom(null);
				return uObject;
			}
		}

		return super.createEObject(internalEObject, extrinsicID);
	}
}
