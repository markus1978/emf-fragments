package de.hub.emffrag.fragmentation;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.hub.emffrag.proxies.ProxyContainer;
import de.hub.emffrag.proxies.ProxyManager;

public class FragmentationProxyManager extends ProxyManager {
	
	public static final FragmentationProxyManager INSTANCE = new FragmentationProxyManager();
	
	private FragmentationProxyManager() {
		super(new FragmentImpl());
	}

	@Override
	public boolean hasProxyType(Object source) {
		return getProxyType(source) != null;
	}
	
	@Override
	public Class<?> getProxyType(Object source) {
		if (source instanceof TreeIterator<?>) {
			return null; // do not proxy this kind of iterator
		} else if (source instanceof Fragment) {
			return Fragment.class;
		} else if (source instanceof FObject) {
			return FObject.class;
		} else if (source instanceof EList) {
			return EList.class;
		} else if (source instanceof Iterator) {
			return Iterator.class;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean hasProxyRootType(Object source) {
		return source instanceof FObject || source instanceof Fragment;
	}
	
	@Override
	public Class<?>[] getParentTypes(Class<?> proxiedType) {
		if (proxiedType == Fragment.class) {
			return new Class<?>[]{};
		} else if (proxiedType == FObject.class) {
			return new Class<?>[]{};
		} else if (proxiedType == EList.class) {
			return new Class<?>[]{FObject.class,Fragment.class};
		} else if (proxiedType == Iterator.class) {
			return new Class<?>[]{EList.class,EObject.class};
		} else {
			throw new IllegalArgumentException(proxiedType.getCanonicalName() + " is not a proxied type.");
		}
	}

	@Override
	protected ProxyContainer getContainerFromProxyRootSource(Object source) {
		if (source instanceof ProxyContainer) {
			return (ProxyContainer)source;
		} else if (source instanceof FObject) {
			return (ProxyContainer)((FObject)source).fFragment();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	protected void onFinishedClientOperation(Object source) {
		if (source instanceof FObject) {
			Fragmentation fFragmentation = ((FObject)source).fFragmentation();
			if (fFragmentation != null) {
				fFragmentation.onFinishedClientOperation();
			}
		}
	}

	@Override
	protected Object resolve(Object object, ProxyContainer container) {
		object = super.resolve(object, container);
		if (object instanceof EObject) {
			EObject eObject = (EObject)object;
			if (eObject.eIsProxy()) {
				object = EcoreUtil.resolve(eObject, ((Resource)container).getResourceSet());
			}
		}
		return object;
	}
	
	private final static Method eObject_eAllContents;
	private final static Method fObject_pAllContents;
	private final static Method resource_getAllContents;
	private final static Method fragment_pAllContents;
	static {
		try {
			eObject_eAllContents = EObject.class.getMethod("eAllContents", new Class<?>[]{});
			fObject_pAllContents = FObjectImpl.class.getMethod("pAllContents", new Class<?>[]{});
			resource_getAllContents = Resource.class.getMethod("getAllContents", new Class<?>[]{});
			fragment_pAllContents = FragmentImpl.class.getMethod("pAllContents", new Class<?>[]{});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Method replace(Method method) {
		if (EObject.class.isAssignableFrom(method.getDeclaringClass()) && method.getName().equals(eObject_eAllContents.getName())) {
			method = fObject_pAllContents;
		} else if (Resource.class.isAssignableFrom(method.getDeclaringClass()) && method.getName().equals(resource_getAllContents.getName())) {
			method = fragment_pAllContents;
		}
		return super.replace(method);
	}
	
	
}
