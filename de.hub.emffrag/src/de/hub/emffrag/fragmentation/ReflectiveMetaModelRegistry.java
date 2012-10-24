package de.hub.emffrag.fragmentation;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * Since EMF does not allow to use the same model in a regular fashion
 * (generated classes) and purely reflective fashion, we need to maintain two
 * versions of the same meta-model. One is used by clients/users as the regular
 * meta-model. The other is used internally by EMF-Fragments as a purely
 * reflective version.
 * 
 * This class allows to manage meta-models and their two versions and access the
 * corresponding opposite elements of the other meta-model.
 * 
 */
public class ReflectiveMetaModelRegistry {

	public static final ReflectiveMetaModelRegistry instance = new ReflectiveMetaModelRegistry();

	private ReflectiveMetaModelRegistry() {
	}

	private final Map<EPackage, EPackage> reflectiveMetaModelRegistry = new HashMap<EPackage, EPackage>();
	private final Map<EPackage, EPackage> regularMetaModelRegistry = new HashMap<EPackage, EPackage>();

	@SuppressWarnings("unchecked")
	public <T extends EPackage> T registerRegularMetaModel(T source) {
		T target = (T) reflectiveMetaModelRegistry.get(source);
		if (target == null) {
			target = generateReflectiveMetaModel(source);
			reflectiveMetaModelRegistry.put(source, target);
			regularMetaModelRegistry.put(target, source);
		}
		return target;
	}

	public EClass getOppositeClass(EClass theClass) {
		EPackage metaModel = theClass.getEPackage();
		return (EClass) getOppositeMetaModel(metaModel).getEClassifier(theClass.getName());
	}

	public EPackage getOppositeMetaModel(EPackage metaModel) {
		EPackage reflectiveMetaModel = reflectiveMetaModelRegistry.get(metaModel);
		EPackage regularMetaModel = regularMetaModelRegistry.get(metaModel);
		if (reflectiveMetaModel == null) {
			if (regularMetaModel == null) {
				throw new IllegalArgumentException("meta model is not registered");
			} else {
				return regularMetaModel;
			}
		} else if (regularMetaModel == null) {
			return reflectiveMetaModel;
		} else {
			throw new IllegalArgumentException("meta model is registered in both directions");
		}
	}

	public EStructuralFeature getOppositeFeature(EStructuralFeature theFeature) {
		return getOppositeClass(theFeature.getEContainingClass()).getEStructuralFeature(theFeature.getFeatureID());
	}

	@SuppressWarnings("unchecked")
	private <T extends EPackage> T generateReflectiveMetaModel(T source) {
		EFactory regularFactory = source.getEFactoryInstance();
		T target = null;
		try {
			Constructor<? extends EPackage> declaredConstructor = source.getClass().getDeclaredConstructor(new Class[] {});
			declaredConstructor.setAccessible(true);
			target = (T) declaredConstructor.newInstance(new Object[] {});
			declaredConstructor.setAccessible(false);
			target.getClass().getMethod("createPackageContents", new Class<?>[] {}).invoke(target, new Object[] {});
			target.getClass().getMethod("initializePackageContents", new Class<?>[] {}).invoke(target, new Object[] {});
			target.getClass().getMethod("freeze", new Class<?>[] {}).invoke(target, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception during reflective meta-model generation, probably EMF internals have changed.", e);
		}
		// EMF uses its notification process to clear a packages factory, if a
		// new package instance is created. Weird.
		source.setEFactoryInstance(regularFactory);

		TreeIterator<EObject> iterator = target.eAllContents();
		while (iterator.hasNext()) {
			EObject next = iterator.next();
			if (next instanceof EClass) {
				((EClassImpl) next).setGeneratedInstanceClass(false);
				((EClass) next).setInstanceClass(null);
			}
		}

		EFactoryImpl factory = new EFactoryImpl() {
			@Override
			public EObject create(EClass eClass) {
				return new FInternalObjectImpl(eClass);
			}
		};
		factory.setEPackage(target);
		target.setEFactoryInstance(factory);
		return target;
	}
}
