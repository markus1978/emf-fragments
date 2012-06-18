/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.impl;

import java.io.PrintStream;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark;
import de.hub.emffrag.bm.benchmark.Benchmark;
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
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BenchmarkPackageImpl extends EPackageImpl implements BenchmarkPackage {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass benchmarkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass loadBenchmarkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass saveBenchmarkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass abstractGrabatsBenchmarkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass measurementEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryBenchmarkEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importTestModelEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loadFromTestModelEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importDeepTestModelEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum emfFragCandidatesEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType printStreamEDataType = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType iConstraintEDataType = null;

    /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private BenchmarkPackageImpl() {
		super(eNS_URI, BenchmarkFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link BenchmarkPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static BenchmarkPackage init() {
		if (isInited) return (BenchmarkPackage)EPackage.Registry.INSTANCE.getEPackage(BenchmarkPackage.eNS_URI);

		// Obtain or create and register package
		BenchmarkPackageImpl theBenchmarkPackage = (BenchmarkPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BenchmarkPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BenchmarkPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theBenchmarkPackage.createPackageContents();

		// Initialize created meta-data
		theBenchmarkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBenchmarkPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BenchmarkPackage.eNS_URI, theBenchmarkPackage);
		return theBenchmarkPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getBenchmark() {
		return benchmarkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_RMemoryUsage() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_RTime() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_RNumberOfObjects() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_WeakUnloads() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_WeakLoads() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_Loads() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_Unloads() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_MaxActiveFagments() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_MaxWeakFragments() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(8);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_Remarks() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(9);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBenchmark_PModelName() {
		return (EAttribute)benchmarkEClass.getEStructuralFeatures().get(10);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLoadBenchmark() {
		return loadBenchmarkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSaveBenchmark() {
		return saveBenchmarkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getAbstractGrabatsBenchmark() {
		return abstractGrabatsBenchmarkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getAbstractGrabatsBenchmark_PSourceXmiURI() {
		return (EAttribute)abstractGrabatsBenchmarkEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractGrabatsBenchmark_Frag2() {
		return (EAttribute)abstractGrabatsBenchmarkEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getMeasurement() {
		return measurementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getMeasurement_Name() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getMeasurement_Candidates() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getMeasurement_Benchmarks() {
		return (EReference)measurementEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getMeasurement_Runs() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getMeasurement_Constraints() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getMeasurement_NumberOfBenchmarks() {
		return (EAttribute)measurementEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQueryBenchmark() {
		return queryBenchmarkEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryBenchmark_WithIndex() {
		return (EAttribute)queryBenchmarkEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportTestModel() {
		return importTestModelEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportTestModel_FragmentSize() {
		return (EAttribute)importTestModelEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportTestModel_ModelSize() {
		return (EAttribute)importTestModelEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoadFromTestModel() {
		return loadFromTestModelEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoadFromTestModel_LoadSize() {
		return (EAttribute)loadFromTestModelEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLoadFromTestModel_ImportBenchmark() {
		return (EReference)loadFromTestModelEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportDeepTestModel() {
		return importDeepTestModelEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportDeepTestModel_Base() {
		return (EAttribute)importDeepTestModelEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getEMFFragCandidates() {
		return emfFragCandidatesEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getPrintStream() {
		return printStreamEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getIConstraint() {
		return iConstraintEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BenchmarkFactory getBenchmarkFactory() {
		return (BenchmarkFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		benchmarkEClass = createEClass(BENCHMARK);
		createEAttribute(benchmarkEClass, BENCHMARK__RMEMORY_USAGE);
		createEAttribute(benchmarkEClass, BENCHMARK__RTIME);
		createEAttribute(benchmarkEClass, BENCHMARK__RNUMBER_OF_OBJECTS);
		createEAttribute(benchmarkEClass, BENCHMARK__WEAK_UNLOADS);
		createEAttribute(benchmarkEClass, BENCHMARK__WEAK_LOADS);
		createEAttribute(benchmarkEClass, BENCHMARK__LOADS);
		createEAttribute(benchmarkEClass, BENCHMARK__UNLOADS);
		createEAttribute(benchmarkEClass, BENCHMARK__MAX_ACTIVE_FAGMENTS);
		createEAttribute(benchmarkEClass, BENCHMARK__MAX_WEAK_FRAGMENTS);
		createEAttribute(benchmarkEClass, BENCHMARK__REMARKS);
		createEAttribute(benchmarkEClass, BENCHMARK__PMODEL_NAME);

		loadBenchmarkEClass = createEClass(LOAD_BENCHMARK);

		saveBenchmarkEClass = createEClass(SAVE_BENCHMARK);

		abstractGrabatsBenchmarkEClass = createEClass(ABSTRACT_GRABATS_BENCHMARK);
		createEAttribute(abstractGrabatsBenchmarkEClass, ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI);
		createEAttribute(abstractGrabatsBenchmarkEClass, ABSTRACT_GRABATS_BENCHMARK__FRAG2);

		measurementEClass = createEClass(MEASUREMENT);
		createEAttribute(measurementEClass, MEASUREMENT__NAME);
		createEAttribute(measurementEClass, MEASUREMENT__CANDIDATES);
		createEReference(measurementEClass, MEASUREMENT__BENCHMARKS);
		createEAttribute(measurementEClass, MEASUREMENT__RUNS);
		createEAttribute(measurementEClass, MEASUREMENT__CONSTRAINTS);
		createEAttribute(measurementEClass, MEASUREMENT__NUMBER_OF_BENCHMARKS);

		queryBenchmarkEClass = createEClass(QUERY_BENCHMARK);
		createEAttribute(queryBenchmarkEClass, QUERY_BENCHMARK__WITH_INDEX);

		importTestModelEClass = createEClass(IMPORT_TEST_MODEL);
		createEAttribute(importTestModelEClass, IMPORT_TEST_MODEL__FRAGMENT_SIZE);
		createEAttribute(importTestModelEClass, IMPORT_TEST_MODEL__MODEL_SIZE);

		loadFromTestModelEClass = createEClass(LOAD_FROM_TEST_MODEL);
		createEAttribute(loadFromTestModelEClass, LOAD_FROM_TEST_MODEL__LOAD_SIZE);
		createEReference(loadFromTestModelEClass, LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK);

		importDeepTestModelEClass = createEClass(IMPORT_DEEP_TEST_MODEL);
		createEAttribute(importDeepTestModelEClass, IMPORT_DEEP_TEST_MODEL__BASE);

		// Create enums
		emfFragCandidatesEEnum = createEEnum(EMF_FRAG_CANDIDATES);

		// Create data types
		printStreamEDataType = createEDataType(PRINT_STREAM);
		iConstraintEDataType = createEDataType(ICONSTRAINT);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		loadBenchmarkEClass.getESuperTypes().add(this.getAbstractGrabatsBenchmark());
		saveBenchmarkEClass.getESuperTypes().add(this.getAbstractGrabatsBenchmark());
		abstractGrabatsBenchmarkEClass.getESuperTypes().add(this.getBenchmark());
		queryBenchmarkEClass.getESuperTypes().add(this.getAbstractGrabatsBenchmark());
		importTestModelEClass.getESuperTypes().add(this.getBenchmark());
		loadFromTestModelEClass.getESuperTypes().add(this.getBenchmark());
		importDeepTestModelEClass.getESuperTypes().add(this.getImportTestModel());

		// Initialize classes and features; add operations and parameters
		initEClass(benchmarkEClass, Benchmark.class, "Benchmark", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBenchmark_RMemoryUsage(), ecorePackage.getELong(), "rMemoryUsage", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_RTime(), ecorePackage.getELong(), "rTime", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_RNumberOfObjects(), ecorePackage.getEInt(), "rNumberOfObjects", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_WeakUnloads(), ecorePackage.getEInt(), "weakUnloads", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_WeakLoads(), ecorePackage.getEInt(), "weakLoads", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_Loads(), ecorePackage.getEInt(), "loads", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_Unloads(), ecorePackage.getEInt(), "unloads", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_MaxActiveFagments(), ecorePackage.getEInt(), "maxActiveFagments", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_MaxWeakFragments(), ecorePackage.getEInt(), "maxWeakFragments", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_Remarks(), ecorePackage.getEString(), "remarks", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenchmark_PModelName(), ecorePackage.getEString(), "pModelName", null, 0, 1, Benchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(benchmarkEClass, null, "printResult", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPrintStream(), "out", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEMFFragCandidates(), "candidate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "run", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(benchmarkEClass, null, "printResultHeader", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPrintStream(), "out", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(benchmarkEClass, null, "run", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEMFFragCandidates(), "candidate", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(benchmarkEClass, null, "startTimeMeasure", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(benchmarkEClass, ecorePackage.getELong(), "getTimeMeasure", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(benchmarkEClass, ecorePackage.getELong(), "getMemoryMeasure", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(loadBenchmarkEClass, LoadBenchmark.class, "LoadBenchmark", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(saveBenchmarkEClass, SaveBenchmark.class, "SaveBenchmark", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractGrabatsBenchmarkEClass, AbstractGrabatsBenchmark.class, "AbstractGrabatsBenchmark", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractGrabatsBenchmark_PSourceXmiURI(), ecorePackage.getEString(), "pSourceXmiURI", null, 0, 1, AbstractGrabatsBenchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractGrabatsBenchmark_Frag2(), ecorePackage.getEBoolean(), "frag2", "false", 0, 1, AbstractGrabatsBenchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurementEClass, Measurement.class, "Measurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasurement_Name(), ecorePackage.getEString(), "name", null, 0, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_Candidates(), this.getEMFFragCandidates(), "candidates", null, 0, -1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasurement_Benchmarks(), this.getBenchmark(), null, "benchmarks", null, 0, -1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_Runs(), ecorePackage.getEInt(), "runs", null, 0, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_Constraints(), this.getIConstraint(), "constraints", null, 0, -1, Measurement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasurement_NumberOfBenchmarks(), ecorePackage.getEInt(), "numberOfBenchmarks", null, 0, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(measurementEClass, null, "run", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "measure", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(queryBenchmarkEClass, QueryBenchmark.class, "QueryBenchmark", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQueryBenchmark_WithIndex(), ecorePackage.getEBoolean(), "withIndex", null, 0, 1, QueryBenchmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importTestModelEClass, ImportTestModel.class, "ImportTestModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImportTestModel_FragmentSize(), ecorePackage.getEInt(), "fragmentSize", null, 0, 1, ImportTestModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImportTestModel_ModelSize(), ecorePackage.getEInt(), "modelSize", null, 0, 1, ImportTestModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(loadFromTestModelEClass, LoadFromTestModel.class, "LoadFromTestModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLoadFromTestModel_LoadSize(), ecorePackage.getEInt(), "loadSize", null, 0, 1, LoadFromTestModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLoadFromTestModel_ImportBenchmark(), this.getImportTestModel(), null, "importBenchmark", null, 0, 1, LoadFromTestModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importDeepTestModelEClass, ImportDeepTestModel.class, "ImportDeepTestModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImportDeepTestModel_Base(), ecorePackage.getEInt(), "base", null, 0, 1, ImportDeepTestModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(emfFragCandidatesEEnum, EMFFragCandidates.class, "EMFFragCandidates");
		addEEnumLiteral(emfFragCandidatesEEnum, EMFFragCandidates.XMI);
		addEEnumLiteral(emfFragCandidatesEEnum, EMFFragCandidates.CDO);
		addEEnumLiteral(emfFragCandidatesEEnum, EMFFragCandidates.MORSA);
		addEEnumLiteral(emfFragCandidatesEEnum, EMFFragCandidates.EMFFRAG);

		// Initialize data types
		initEDataType(printStreamEDataType, PrintStream.class, "PrintStream", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iConstraintEDataType, IConstraint.class, "IConstraint", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //BenchmarkPackageImpl
