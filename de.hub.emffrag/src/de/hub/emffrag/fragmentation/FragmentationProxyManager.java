package de.hub.emffrag.fragmentation;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.proxies.ProxyContainer;
import de.hub.emffrag.proxies.ProxyManager;

public class FragmentationProxyManager extends ProxyManager {
	
	public static final FragmentationProxyManager INSTANCE = new FragmentationProxyManager();
	
	private FragmentationProxyManager() {
		super(new Fragment());
	}

	@Override
	protected boolean hasProxyType(Object source) {
		return getProxyType(source) != null;
	}
	
	@Override
	protected Class<?> getProxyType(Object source) {
		if (source instanceof FObject) {
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
	protected boolean hasProxyRootType(Object source) {
		return source instanceof EObject;
	}
	
	@Override
	protected Class<?>[] getParentTypes(Class<?> proxiedType) {
		if (proxiedType == EObject.class) {
			return new Class<?>[]{};
		} else if (proxiedType == EList.class) {
			return new Class<?>[]{EObject.class};
		} else if (proxiedType == Iterator.class) {
			return new Class<?>[]{EList.class,EObject.class};
		} else {
			throw new IllegalArgumentException(proxiedType.getCanonicalName() + " is not a proxied type.");
		}
	}

	@Override
	protected ProxyContainer getContainerFromProxyRootSource(Object source) {
		return ((FObject)source).fFragment();
	}
}
