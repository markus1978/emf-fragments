package de.hub.emffrag.fragmentation;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.proxies.ProxyContainer;
import de.hub.emffrag.proxies.ProxyManager;

public class FragmentationProxyManager extends ProxyManager {
	
	public static final FragmentationProxyManager INSTANCE = new FragmentationProxyManager();
	
	private FragmentationProxyManager() {
		super(new FragmentImpl());
	}

	@Override
	protected boolean hasProxyType(Object source) {
		return getProxyType(source) != null;
	}
	
	@Override
	protected Class<?> getProxyType(Object source) {
		if (source instanceof Fragment) {
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
	protected boolean hasProxyRootType(Object source) {
		return source instanceof FObject || source instanceof Fragment;
	}
	
	@Override
	protected Class<?>[] getParentTypes(Class<?> proxiedType) {
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
		if (source instanceof Fragment) {
			return (Fragment)source;
		} else if (source instanceof FObject) {
			return ((FObject)source).fFragment();
		} else {
			throw new IllegalArgumentException();
		}
	}
}
