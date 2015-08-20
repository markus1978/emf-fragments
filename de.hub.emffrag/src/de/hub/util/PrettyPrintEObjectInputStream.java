package de.hub.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectInputStream;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class PrettyPrintEObjectInputStream extends EObjectInputStream {
	
	public static String prettyPrint(URI uri, InputStream inputStream, boolean withExtrinsicId) throws IOException {
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = new ResourceImpl(uri);
		rs.getResources().add(resource);
		
		StringBuilder out = new StringBuilder();
		PrettyPrintEObjectInputStream prettyPrintEObjectInputStream = new PrettyPrintEObjectInputStream(out, inputStream, null, withExtrinsicId);
		out.append(uri.toString() + "\n");
		prettyPrintEObjectInputStream.loadResource(resource);
		prettyPrintEObjectInputStream.flush();
		out.append("\n");
		return out.toString();
	}

	private final StringBuilder out;
	private final boolean withExtrinsicId;
	private int indent = 0;
	
	public PrettyPrintEObjectInputStream(StringBuilder out, InputStream inputStream, Map<?, ?> options, boolean withExtrinsicId) throws IOException {
		super(inputStream, options);
		this.out = out;
		this.withExtrinsicId = withExtrinsicId;
	}
	
	private void indent() {
		for (int i = 0; i < indent; i++) {
			out.append(" ");
		}
	}
	
	// This is a replacement for
	// BinaryResourceImpl#internalInternalEObjectList, which is only used in
	// #loadEObject()
	private List<InternalEObject> internalInternalEObjectList = new ArrayList<InternalEObject>();
	
	private InternalEObject createObject(EClassData eClassData) {
		final MinimalEObjectImpl.Container actual = new MinimalEObjectImpl.Container();
		actual.eSetClass(eClassData.eClass);
		Class<?> instanceClass = eClassData.eClass.getInstanceClass();
		return (InternalEObject)Proxy.newProxyInstance(instanceClass.getClassLoader(), new Class[] { instanceClass, InternalEObject.class }, new InvocationHandler() {			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return method.invoke(actual, args);
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public InternalEObject loadEObject() throws IOException {
		int id = readCompressedInt();
		int extrinsicId = -1;
		if (withExtrinsicId) {
			extrinsicId = readCompressedInt();
		}
		if (id == -1) {
			return null;
		} else {
			if (internalInternalEObjectList.size() <= id) {
				EClassData eClassData = readEClass();
				indent();
				out.append("(" + id + (extrinsicId >= 0 ? "/" + extrinsicId : "") + "):" + eClassData.eClass.getName());
				InternalEObject internalEObject =  createObject(eClassData); //(InternalEObject)eClassData.eFactory.create(eClassData.eClass);
				InternalEObject result = internalEObject;

				// Check if we have a "feature" representing the proxy URI...
				//
				int featureID = readCompressedInt() - 1;
				if (featureID == -2) {
					internalEObject.eSetProxyURI(readURI());
					internalInternalEObjectList.add(internalEObject);
					if ((style & STYLE_PROXY_ATTRIBUTES) == 0) {
						out.append("@(" + internalEObject.eProxyURI() +")\n");
						return internalEObject;
					}

					// We must process the proxy attributes even for the case of
					// eager proxy resolution when we will immediately discard
					// the proxy.
					//
					featureID = readCompressedInt() - 1;
				} else {
					internalInternalEObjectList.add(internalEObject);
				}
				
				indent += 4;
				out.append(" {\n");

				for (; featureID != -1; featureID = readCompressedInt() - 1) {
					EStructuralFeatureData eStructuralFeatureData = getEStructuralFeatureData(eClassData, featureID);
					indent();
					EStructuralFeature feature = eClassData.eClass.getEStructuralFeature(eStructuralFeatureData.featureID);
					out.append(feature.getName());
					if (feature instanceof EReference) {
						out.append("=[\n");
						indent += 4;						
						loadFeatureValue(internalEObject, eStructuralFeatureData);
						indent -= 4;
						indent();
						out.append("]\n");	
					} else {
						loadFeatureValue(internalEObject, eStructuralFeatureData);
						if (feature.isMany()) {
							out.append("=[\n");
							indent += 4;						
							for (Object value: (EList)internalEObject.eGet(feature)) {
								out.append(value + "\n");
							}
							indent -= 4;
							indent();
							out.append("]\n");
						} else {
							out.append("=" + internalEObject.eGet(feature) + "\n");
						}
					}
				}
				indent -= 4;
				indent();
				out.append("}\n");
				return result;
			} else {
				return internalInternalEObjectList.get(id);
			}
		}
	}
}
