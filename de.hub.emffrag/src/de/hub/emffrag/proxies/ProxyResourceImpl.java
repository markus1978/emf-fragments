package de.hub.emffrag.proxies;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.hub.emffrag.fragmentation.FragmentImpl;

public class ProxyResourceImpl extends AbstractRootProxyImpl<FragmentImpl> implements Resource {

	public ProxyResourceImpl(FragmentImpl source) {
		super(source);
	}
	
	@Override
	public ProxyContainer fContainer() {
		return source;
	}

	public EList<Adapter> eAdapters() {
		disgurageUsage();
		return source.eAdapters();
	}

	public boolean eDeliver() {
		disgurageUsage();
		return source.eDeliver();
	}

	public void eSetDeliver(boolean deliver) {
		disgurageUsage();
		source.eSetDeliver(deliver);
	}

	public void eNotify(Notification notification) {
		disgurageUsage();
		source.eNotify(notification);
	}

	public ResourceSet getResourceSet() {
		disgurageUsage();
		return source.getResourceSet();
	}

	public URI getURI() {
		return source.getURI();
	}

	public void setURI(URI uri) {
		disgurageUsage();
		source.setURI(uri);
	}

	public long getTimeStamp() {
		return source.getTimeStamp();
	}

	public void setTimeStamp(long timeStamp) {
		disgurageUsage();
		source.setTimeStamp(timeStamp);
	}

	public EList<EObject> getContents() {
		return fProxy(source.getContents());
	}

	public TreeIterator<EObject> getAllContents() {
		return fProxy(source.getAllContents());
	}

	public String getURIFragment(EObject eObject) {
		return source.getURIFragment(fSource(eObject));
	}

	public EObject getEObject(String uriFragment) {
		return fProxy(source.getEObject(uriFragment));
	}

	public void save(Map<?, ?> options) throws IOException {
		disgurageUsage();
		source.save(options);
	}

	public void load(Map<?, ?> options) throws IOException {
		disgurageUsage();
		source.load(options);
	}

	public void save(OutputStream outputStream, Map<?, ?> options) throws IOException {
		disgurageUsage();
		source.save(outputStream, options);
	}

	public void load(InputStream inputStream, Map<?, ?> options) throws IOException {
		disgurageUsage();
		source.load(inputStream, options);
	}

	public boolean isTrackingModification() {
		return source.isTrackingModification();
	}

	public void setTrackingModification(boolean isTrackingModification) {
		disgurageUsage();
		source.setTrackingModification(isTrackingModification);
	}

	public boolean isModified() {
		return source.isModified();
	}

	public void setModified(boolean isModified) {
		disgurageUsage();
		source.setModified(isModified);
	}

	public boolean isLoaded() {
		disgurageUsage();
		return source.isLoaded();
	}

	public void unload() {
		disgurageUsage();
		source.unload();
	}

	public void delete(Map<?, ?> options) throws IOException {
		disgurageUsage();
		source.delete(options);
	}

	public EList<Diagnostic> getErrors() {
		return source.getErrors();
	}

	public EList<Diagnostic> getWarnings() {
		return source.getWarnings();
	}
}
