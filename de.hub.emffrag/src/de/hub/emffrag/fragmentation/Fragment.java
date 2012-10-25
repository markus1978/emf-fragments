package de.hub.emffrag.fragmentation;

import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.util.EMFFragUtil;

public class Fragment extends XMIResourceImpl {

	private final UserObjectsCache userObjectsCache = new UserObjectsCache();
	private FragmentedModel model = null;

	public Fragment() {
		super();
	}

	public Fragment(URI uri) {
		super(uri);
	}

	public UserObjectsCache getUserObjectsCache() {
		return userObjectsCache;
	}

	protected void setFragmentedModel(FragmentedModel model) {
		this.model = model;
	}

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

	private class MyXMLHelper extends XMLHelperImpl {
		private EStructuralFeature currentFeature = null;

		public MyXMLHelper(XMLResource resource) {
			super(resource);
		}

		@Override
		protected URI getHREF(Resource otherResource, EObject obj) {
			if (obj instanceof FInternalObjectImpl && ((FInternalObjectImpl) obj).isCrossReferenced()
					&& otherResource instanceof Fragment) {
				if (currentFeature instanceof EReference && !((EReference) currentFeature).isContainment()) {
					// FragmentedModel fragmentedModel =
					// ((Fragment)otherResource).getFragmentedModel();
					String extrinsicID = ((Fragment) otherResource).getID(obj);
					return URI.createURI(DatatypeConverter.printBase64Binary(("c_" + extrinsicID).getBytes())); // TODO intermediate
				} else if (EMFFragUtil.isFragFreature(currentFeature)) {
					 return otherResource.getURI().appendFragment("/"); 
				}
			}
			return super.getHREF(otherResource, obj);			
		}

	}

	@Override
	protected XMLHelper createXMLHelper() {
		return new MyXMLHelper(this);
	}

	@Override
	protected XMLSave createXMLSave() {
		return new XMISaveImpl(createXMLHelper()) {
			@Override
			protected void saveHref(EObject remote, EStructuralFeature f) {
				((MyXMLHelper) helper).currentFeature = f;
				super.saveHref(remote, f);
			}
		};
	}

	// @Override
	// protected EObject getEObjectByID(String id) {
	// EObject eObject = super.getEObjectByID(id);
	// if (eObject == null) {
	// eObject = FStoreImpl.INSTANCE.resolve(id);
	// }
	// return eObject;
	// }
}
