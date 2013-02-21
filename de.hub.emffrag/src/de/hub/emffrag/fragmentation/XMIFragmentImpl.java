package de.hub.emffrag.fragmentation;

import java.io.IOException;
import java.util.Iterator;

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

import com.google.common.base.Throwables;

import de.hub.emffrag.util.EMFFragUtil;

public class XMIFragmentImpl extends XMIResourceImpl implements Fragment {

	private final UserObjectsCache userObjectsCache;
	private final FragmentedModel model;	

	public XMIFragmentImpl(URI uri, FragmentedModel model) {
		super(uri);
		this.model = model;
		userObjectsCache = new UserObjectsCache();
	}

	@Override
	public UserObjectsCache getUserObjectsCache() {
		return userObjectsCache;
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
	 * This custom {@link XMLHelperImpl} allows us to determine the form of used
	 * HREF URIs in XMI. This is necessary to point cross references towards
	 * cross reference entries in the data-base rather then the objects
	 * themselves. This requires each cross-referenced object to have an
	 * extrinsic id. But EMF exports HREFs towards objects with extrinsic IDs as
	 * a URI that uses these extrinsic IDs. We don't want that. This
	 * implementation uses regular URI-fragments for containment references and
	 * URIs pointing at cross-reference entries in the data-store for cross
	 * references.
	 */
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
					String extrinsicID = ((Fragment) otherResource).getID((FInternalObjectImpl)obj);
					FragmentedModel fragmentedModel = ((Fragment) otherResource).getFragmentedModel();
					URI uri = fragmentedModel.getExtrinsicIdIndex().createExtrinsicIdUri(extrinsicID);
					return uri;
				} else if (EMFFragUtil.isFragFreature(currentFeature)) {
					return otherResource.getURI().appendFragment("/");
				}
			}
			URI href2 = super.getHREF(otherResource, obj);
			return href2;
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

	/**
	 * EMF does not break down the reference graph of a resources contents
	 * property. Under some circumstances the JVM cannot remove this contents
	 * even if the resource is unloaded and removed from the resource set. This
	 * change in the EMF standard behavior of the resources fixes that.
	 */
	@Override
	protected void doUnload() {
	    Iterator<EObject> allContents = getAllProperContents(unloadingContents); 
	    
		super.doUnload();
		while (allContents.hasNext())
	    {
			FInternalObjectImpl internalObject = (FInternalObjectImpl) allContents.next();
			internalObject.trulyUnload();
	    }
	}

	@Override
	public void setID(FInternalObjectImpl object, String id) {
		super.setID(object, id);
		
	}

	@Override
	public String getID(FInternalObjectImpl object) {
		return super.getID(object);
	}
	
	
}