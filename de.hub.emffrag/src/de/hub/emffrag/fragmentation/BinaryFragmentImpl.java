package de.hub.emffrag.fragmentation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.util.EMFFragUtil;
import de.hub.emffrag.util.EMFFragUtil.FragmentationType;

public class BinaryFragmentImpl extends BinaryResourceImpl implements Fragment {

	private final FragmentedModel model;

	public BinaryFragmentImpl(URI uri, FragmentedModel model) {
		super(uri);
		this.model = model;
	}

	@Override
	public FragmentedModel getFragmentedModel() {
		return model;
	}

	@Override
	public void detached(EObject eObject) {
		super.detached(eObject);
		if (getContents().isEmpty()) {
			try {
				delete(null);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
	}

	/**
	 * EMF does not break down the reference graph of a resources contents
	 * property. Under some circumstances the JVM cannot remove this contents
	 * even if the resource is unloaded and removed from the resource set. This
	 * change in the EMF standard behavior of the resources fixes that.
	 */
	@Override
	protected void doUnload() {
		Collection<FInternalObject> nonFragmentingContents = new ArrayList<FInternalObject>();
		for (EObject topLevel : unloadingContents) {
			EMFFragUtil.collectAllNonFragmentingContents((FInternalObject) topLevel, nonFragmentingContents);
		}

		super.doUnload();
		for (EObject eObject : nonFragmentingContents) {
			FInternalObjectImpl internalObject = (FInternalObjectImpl) eObject;
			internalObject.trulyUnload();
		}
	}

	@Override
	protected TreeIterator<EObject> getAllProperContents(EObject eObject) {
		return EMFFragUtil.getAllNonFragmentingContentsIterator(eObject);
	}

	@Override
	protected TreeIterator<EObject> getAllProperContents(List<EObject> contents) {
		return EMFFragUtil.getAllNonFragmentingContentsIterator(contents);
	}

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		// This is a copy of the super method, except for the market pieces
		// of code
		if (outputStream instanceof URIConverter.Saveable) {
			((URIConverter.Saveable) outputStream).saveResource(this);
		} else {
			boolean buffer = !(outputStream instanceof BufferedOutputStream);
			if (buffer) {
				int bufferCapacity = getBufferCapacity(options);
				if (bufferCapacity > 0) {
					outputStream = new BufferedOutputStream(outputStream, bufferCapacity);
				} else {
					buffer = false;
				}
			}

			try {
				// We use our own EObjectOutputStream
				// EObjectOutputStream eObjectOutputStream = new
				// EObjectOutputStream(outputStream, options);
				EObjectOutputStream eObjectOutputStream = new MyEObjectOutputStream(outputStream, options);
				eObjectOutputStream.saveResource(this);
			} finally {
				if (buffer) {
					outputStream.flush();
				}
			}
		}
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		// This is a copy of the super method, except for the market pieces
		// of code
		if (inputStream instanceof URIConverter.Loadable) {
			((URIConverter.Loadable) inputStream).loadResource(this);
		} else {
			if (!(inputStream instanceof BufferedInputStream)) {
				int bufferCapacity = getBufferCapacity(options);
				if (bufferCapacity > 0) {
					inputStream = new BufferedInputStream(inputStream, bufferCapacity);
				}
			}

			// We use our own EObjectOutputStream
			// EObjectInputStream eObjectInputStream = new
			// EObjectInputStream(inputStream, options);
			EObjectInputStream eObjectInputStream = new MyEObjectInputStream(inputStream, options);
			eObjectInputStream.loadResource(this);
		}
	}

	@Override
	protected boolean isAttachedDetachedHelperRequired() {
		return false;
	}

	private class MyEObjectInputStream extends EObjectInputStream {

		public MyEObjectInputStream(InputStream is, Map<?, ?> options) throws IOException {
			super(is, options);
		}

		@Override
		protected EPackageData readEPackage() throws IOException {
			EPackageData ePackageData = super.readEPackage();
			if (resourceSet != null) {
				EPackage ePackage = resourceSet.getPackageRegistry().getEPackage(ePackageData.ePackage.getNsURI());
				if (ePackage != null) {
					ePackageData.ePackage = ePackage;
				}
				if (ePackageData.eClassData.length != ePackage.getEClassifiers().size()) {
					ePackageData.eClassData = new EClassData[ePackage.getEClassifiers().size()];
				}
			}
			return ePackageData;
		}

	}

	public String getURIFragment(EObject eObject) {
		if (eObject.eIsProxy()) {
			URI eProxyURI = ((InternalEObject) eObject).eProxyURI();
			if (eProxyURI.trimFragment().equals(getURI())) {
				return eProxyURI.fragment();
			} else {
				throw new RuntimeException("Unreachable?");
			}
		} else {
			return super.getURIFragment(eObject);
		}
	}

	public class MyEObjectOutputStream extends EObjectOutputStream {
		boolean isWritingCrossReferenceURI = false;
		FInternalObjectImpl currentObject = null;

		public MyEObjectOutputStream(OutputStream os, Map<?, ?> options) throws IOException {
			super(os, options);
		}

		@Override
		public void saveEObject(InternalEObject internalEObject, Check check) throws IOException {
			// Ensure that URIs in the id
			// index are saved, when the object is saved.
			FInternalObjectImpl fInternalObject = (FInternalObjectImpl) internalEObject;
			EmfFragActivator.instance.idSemantics.onObjectSaved(fInternalObject);

			if (check == Check.RESOURCE) {
				isWritingCrossReferenceURI = true;
				currentObject = (FInternalObjectImpl) internalEObject;
				if (internalEObject.eIsProxy()) {
					// Here I am not sure about what EMF is doing. On
					// DIRECT_RESOURCE is simply saves the URI of eProxies, on
					// RESOURCE it tries to determine a proxies resource by
					// walking up its containers, finding the wrong resources.
					// But for some reason, EMF chooses RESOURCE for all proxy
					// resolving non containment lists.
					check = Check.DIRECT_RESOURCE;
				}
			} else {
				isWritingCrossReferenceURI = false;
			}
			super.saveEObject(internalEObject, check);
		}

		@Override
		protected void saveFeatureValue(InternalEObject internalEObject, int featureID, EStructuralFeatureData eStructuralFeatureData) throws IOException {
			FragmentationType type = EMFFragUtil.getFragmentationType(internalEObject.eClass().getEStructuralFeature(featureID));
			if (type == FragmentationType.FragmentsContainment || type == FragmentationType.None) {
				super.saveFeatureValue(internalEObject, featureID, eStructuralFeatureData);
			}
		}

		@Override
		public void writeURI(URI uri, String uriFragment) throws IOException {
			if (!isWritingCrossReferenceURI) {
				super.writeURI(uri, uriFragment);
			} else {
				URI refURI = null;
				Fragment fragment = (Fragment) currentObject.eResource();
				FragmentedModel model = null;
				if (fragment != null) {
					model = fragment.getFragmentedModel();
				} else {
					model = EmfFragActivator.instance.defaultModel;
				}
				if (model != null) {
					refURI = EmfFragActivator.instance.idSemantics.getURI(currentObject, model, false, null);
				}
				if (refURI != null) {
					// basically the code from [super.writeURI(refURI, null)]
					// but without the failing assertion that I do not
					// understand.
					Integer id = uriToIDMap.get(refURI);
					if (id == null) {
						int idValue = uriToIDMap.size();
						uriToIDMap.put(refURI, idValue);
						writeCompressedInt(idValue);
						writeString(deresolve(refURI).toString());
					} else {
						writeCompressedInt(id);
					}
					writeString(null);
				} else {
					super.writeURI(uri, uriFragment);
				}
			}
		}
	}
}
