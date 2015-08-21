package de.hub.emffrag.temp;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import de.hub.emffrag.EmfFragActivator;

public class TreeIteratorSameTest {
	public static void main(String args[]) {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE);
		
		Resource r = new ResourceImpl(URI.createURI("test")) {

			@Override
			public void attached(EObject eObject) {
				super.attached(eObject);
				System.out.println("ATTACHED: " + eObject);
			}

			@Override
			public void detached(EObject eObject) {
				super.detached(eObject);
				System.out.println("DETACHED: " + eObject);
			}
			
		};
		
		EPackage parent = EcoreFactory.eINSTANCE.createEPackage();
		EPackage child = EcoreFactory.eINSTANCE.createEPackage();
		
		parent.setName("p");
		child.setName("c");
		
		r.getContents().add(parent);
		parent.getESubpackages().add(child);
		
		System.out.println(r.getAllContents().equals(r.getAllContents()));
		System.out.println("" + (r.getAllContents() == r.getAllContents()));
		System.out.println(parent.eAllContents().equals(parent.eAllContents()));
		System.out.println("" + (parent.eAllContents() == parent.eAllContents()));
	}
}
