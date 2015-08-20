package de.hub.emffrag.temp;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import de.hub.emffrag.EmfFragActivator;

public class TestAttach {

	public static void main(String args[]) {
		EmfFragActivator.standalone(EcorePackage.eINSTANCE);
		
		Resource r = new ResourceImpl() {

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
		
		r.getContents().clear();
		
		r.getContents().add(parent);
	}
}
