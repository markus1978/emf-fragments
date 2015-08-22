package de.hub.emffrag.proxies;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.FragmentImpl;

public class ProxyFactory {
	
	public static final ProxyFactory INSTANCE = new ProxyFactory();
	
	private ProxyFactory() {
		// empty
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Proxy create(Object source, Proxy parent) {
		if (source instanceof FObjectImpl) {
			EClass eClass = ((FObjectImpl) source).eClass();
			ProxyEObjectImpl proxy = (ProxyEObjectImpl)eClass.getEPackage().getEFactoryInstance().create(eClass);
			proxy.fSetSource((FObjectImpl)source);
			return proxy;
		} else if (source instanceof FragmentImpl) {
			return new ProxyResourceImpl((FragmentImpl)source);
		} else if (source instanceof TreeIterator) {
			return new ProxyTreeIteratorImpl((TreeIterator)source, parent);
		} else if (source instanceof InternalEList) {
			return new ProxyInternalEListImpl((InternalEList)source, parent); 
		} else if (source instanceof EList) {
			return new ProxyEListImpl((EList)source, parent);
		} else if (source instanceof ListIterator) {
			return new ProxyListIteratorImpl((ListIterator)source, parent);
		} else if (source instanceof Iterator) {
			return new ProxyIteratorImpl((Iterator)source, parent);
		} else {
			return null;
		}
	}
}
