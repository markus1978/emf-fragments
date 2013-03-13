package de.hub.emffrag.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.fragmentation.FInternalObjectImpl;
import de.hub.emffrag.fragmentation.FObjectImpl;
import de.hub.emffrag.fragmentation.FStoreImpl;
import de.hub.emffrag.fragmentation.ReflectiveMetaModelRegistry;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.model.emffrag.Extension;

public class Extensions {
	
	public static void add(EObject owner, Extension extension) {
		if (owner instanceof FObjectImpl) {
			EObject internalExtensionObject = null;
			if (extension instanceof FObjectImpl) {
				internalExtensionObject = FStoreImpl.getInstance().getInternalObject(extension);
			} else {
				throw new IllegalArgumentException("Extensions must be FObjects.");
			}
			((FObjectImpl) owner).fInternalObject().getExtensions().add(internalExtensionObject);				
		} else {
			throw new IllegalArgumentException("Extension owner is not an FObject.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Extension> T get(EObject owner, Class<T> extensionClass) {
		if (owner instanceof FObjectImpl) {
			for (EObject internalExtension: ((FObjectImpl) owner).fInternalObject().getExtensions()) {
				EObject extension = FStoreImpl.getInstance().getUserObject((FInternalObjectImpl)internalExtension);
				if (extensionClass.isAssignableFrom(extension.getClass())) {
					return (T)extension;
				}
			}
			return null;
		} else {
			throw new IllegalArgumentException("Extension owner is not an FObject.");
		}	
	}
	
	public static Extension get(EObject owner, EClass theClass, boolean create) {
		if (owner instanceof FObjectImpl) {
			EList<org.eclipse.emf.ecore.EObject> extensions = ((FObjectImpl) owner).fInternalObject().getExtensions();
			EClass internalClass = ReflectiveMetaModelRegistry.instance.getInternalClass(theClass);
			for (EObject extension: extensions) {				
				if (internalClass.isInstance(extension)) {
					return (Extension)FStoreImpl.getInstance().getUserObject((FInternalObjectImpl)extension);
				}
			}
			if (create) {
				EObject result = theClass.getEPackage().getEFactoryInstance().create(theClass);
				if (result instanceof FObjectImpl) {
					FInternalObjectImpl internalResult = FStoreImpl.getInstance().getInternalObject(result);
					extensions.add(internalResult);		
				} else {
					throw new IllegalArgumentException("Extensions must be FObjects");
				}						
				return (Extension)result;
			} else {
				return null;
			}
		} else {
			throw new IllegalArgumentException("Extension owner is not an FObject.");
		}	
	}
	
	public static Extension[] getAll(EObject owner) {		
		if (owner instanceof FObjectImpl) {
			get(owner, EmfFragPackage.eINSTANCE.getStatistics(), true);
			EList<EObject> extensions = ((FObjectImpl) owner).fInternalObject().getExtensions();
			Extension[] result = new Extension[extensions.size()];
			int i = 0;
			for (EObject extension: extensions) {
				result[i++] = (Extension)FStoreImpl.getInstance().getUserObject((FInternalObjectImpl)extension);
			}			
			return result;
		} else {
			throw new IllegalArgumentException("Extension owner is not an FObject.");
		}
	}
}
