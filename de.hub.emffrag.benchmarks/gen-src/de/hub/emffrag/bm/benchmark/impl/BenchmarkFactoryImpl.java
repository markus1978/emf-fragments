/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark;
import de.hub.emffrag.bm.benchmark.BenchmarkFactory;
import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.ImportDeepTestModel;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.benchmark.LoadBenchmark;
import de.hub.emffrag.bm.benchmark.LoadFromTestModel;
import de.hub.emffrag.bm.benchmark.Measurement;
import de.hub.emffrag.bm.benchmark.QueryBenchmark;
import de.hub.emffrag.bm.benchmark.SaveBenchmark;
import de.hub.emffrag.bm.benchmark.util.IConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BenchmarkFactoryImpl extends EFactoryImpl implements BenchmarkFactory {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static BenchmarkFactory init() {
		try {
			BenchmarkFactory theBenchmarkFactory = (BenchmarkFactory)EPackage.Registry.INSTANCE.getEFactory("http://de.hub.emffrag.benchmark"); 
			if (theBenchmarkFactory != null) {
				return theBenchmarkFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BenchmarkFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BenchmarkFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BenchmarkPackage.LOAD_BENCHMARK: return createLoadBenchmark();
			case BenchmarkPackage.SAVE_BENCHMARK: return createSaveBenchmark();
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK: return createAbstractGrabatsBenchmark();
			case BenchmarkPackage.MEASUREMENT: return createMeasurement();
			case BenchmarkPackage.QUERY_BENCHMARK: return createQueryBenchmark();
			case BenchmarkPackage.IMPORT_TEST_MODEL: return createImportTestModel();
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL: return createLoadFromTestModel();
			case BenchmarkPackage.IMPORT_DEEP_TEST_MODEL: return createImportDeepTestModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BenchmarkPackage.EMF_FRAG_CANDIDATES:
				return createEMFFragCandidatesFromString(eDataType, initialValue);
			case BenchmarkPackage.ICONSTRAINT:
				return createIConstraintFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BenchmarkPackage.EMF_FRAG_CANDIDATES:
				return convertEMFFragCandidatesToString(eDataType, instanceValue);
			case BenchmarkPackage.ICONSTRAINT:
				return convertIConstraintToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public LoadBenchmark createLoadBenchmark() {
		LoadBenchmarkImpl loadBenchmark = new LoadBenchmarkImpl();
		return loadBenchmark;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SaveBenchmark createSaveBenchmark() {
		SaveBenchmarkImpl saveBenchmark = new SaveBenchmarkImpl();
		return saveBenchmark;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public AbstractGrabatsBenchmark createAbstractGrabatsBenchmark() {
		AbstractGrabatsBenchmarkImpl abstractGrabatsBenchmark = new AbstractGrabatsBenchmarkImpl();
		return abstractGrabatsBenchmark;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Measurement createMeasurement() {
		MeasurementImpl measurement = new MeasurementImpl();
		return measurement;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryBenchmark createQueryBenchmark() {
		QueryBenchmarkImpl queryBenchmark = new QueryBenchmarkImpl();
		return queryBenchmark;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportTestModel createImportTestModel() {
		ImportTestModelImpl importTestModel = new ImportTestModelImpl();
		return importTestModel;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadFromTestModel createLoadFromTestModel() {
		LoadFromTestModelImpl loadFromTestModel = new LoadFromTestModelImpl();
		return loadFromTestModel;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeepTestModel createImportDeepTestModel() {
		ImportDeepTestModelImpl importDeepTestModel = new ImportDeepTestModelImpl();
		return importDeepTestModel;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EMFFragCandidates createEMFFragCandidatesFromString(EDataType eDataType, String initialValue) {
		EMFFragCandidates result = EMFFragCandidates.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertEMFFragCandidatesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IConstraint createIConstraintFromString(EDataType eDataType, String initialValue) {
		return (IConstraint)super.createFromString(eDataType, initialValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertIConstraintToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BenchmarkPackage getBenchmarkPackage() {
		return (BenchmarkPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
    public static BenchmarkPackage getPackage() {
		return BenchmarkPackage.eINSTANCE;
	}

} //BenchmarkFactoryImpl
