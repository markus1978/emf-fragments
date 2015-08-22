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
	public boolean hasProxyType(Object source) {
		return getProxyType(source) != null;
	}
	
	@Override
	public Class<?> getProxyType(Object source) {
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
		if (source instanceof FragmentImpl) {
			return (FragmentImpl)source;
		} else if (source instanceof FObject) {
			return (FragmentImpl)((FObject)source).fFragment();
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
}
