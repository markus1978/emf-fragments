/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.bm.benchmark.BenchmarkFactory
 * @model kind="package"
 * @generated
 */
public interface BenchmarkPackage extends EPackage {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "benchmark";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://de.hub.emffrag.benchmark";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "bm";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    BenchmarkPackage eINSTANCE = de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl.init();

    /**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl <em>Benchmark</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getBenchmark()
	 * @generated
	 */
    int BENCHMARK = 0;

    /**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__RMEMORY_USAGE = 0;

				/**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__RTIME = 1;

				/**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__RNUMBER_OF_OBJECTS = 2;

				/**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__WEAK_UNLOADS = 3;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__WEAK_LOADS = 4;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__LOADS = 5;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__UNLOADS = 6;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__MAX_ACTIVE_FAGMENTS = 7;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__MAX_WEAK_FRAGMENTS = 8;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__REMARKS = 9;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENCHMARK__PMODEL_NAME = 10;

				/**
	 * The number of structural features of the '<em>Benchmark</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BENCHMARK_FEATURE_COUNT = 11;

    /**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.AbstractGrabatsBenchmarkImpl <em>Abstract Grabats Benchmark</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.AbstractGrabatsBenchmarkImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getAbstractGrabatsBenchmark()
	 * @generated
	 */
    int ABSTRACT_GRABATS_BENCHMARK = 3;

    /**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_GRABATS_BENCHMARK__RMEMORY_USAGE = BENCHMARK__RMEMORY_USAGE;

    /**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_GRABATS_BENCHMARK__RTIME = BENCHMARK__RTIME;

    /**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_GRABATS_BENCHMARK__RNUMBER_OF_OBJECTS = BENCHMARK__RNUMBER_OF_OBJECTS;

    /**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__WEAK_UNLOADS = BENCHMARK__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__WEAK_LOADS = BENCHMARK__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__LOADS = BENCHMARK__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__UNLOADS = BENCHMARK__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__MAX_ACTIVE_FAGMENTS = BENCHMARK__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__MAX_WEAK_FRAGMENTS = BENCHMARK__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__REMARKS = BENCHMARK__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_GRABATS_BENCHMARK__PMODEL_NAME = BENCHMARK__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>PSource Xmi URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI = BENCHMARK_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Frag2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_GRABATS_BENCHMARK__FRAG2 = BENCHMARK_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>Abstract Grabats Benchmark</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_GRABATS_BENCHMARK_FEATURE_COUNT = BENCHMARK_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.LoadBenchmarkImpl <em>Load Benchmark</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.LoadBenchmarkImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getLoadBenchmark()
	 * @generated
	 */
    int LOAD_BENCHMARK = 1;

    /**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOAD_BENCHMARK__RMEMORY_USAGE = ABSTRACT_GRABATS_BENCHMARK__RMEMORY_USAGE;

    /**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOAD_BENCHMARK__RTIME = ABSTRACT_GRABATS_BENCHMARK__RTIME;

    /**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOAD_BENCHMARK__RNUMBER_OF_OBJECTS = ABSTRACT_GRABATS_BENCHMARK__RNUMBER_OF_OBJECTS;

    /**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__WEAK_UNLOADS = ABSTRACT_GRABATS_BENCHMARK__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__WEAK_LOADS = ABSTRACT_GRABATS_BENCHMARK__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__LOADS = ABSTRACT_GRABATS_BENCHMARK__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__UNLOADS = ABSTRACT_GRABATS_BENCHMARK__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__MAX_ACTIVE_FAGMENTS = ABSTRACT_GRABATS_BENCHMARK__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__MAX_WEAK_FRAGMENTS = ABSTRACT_GRABATS_BENCHMARK__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__REMARKS = ABSTRACT_GRABATS_BENCHMARK__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOAD_BENCHMARK__PMODEL_NAME = ABSTRACT_GRABATS_BENCHMARK__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>PSource Xmi URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOAD_BENCHMARK__PSOURCE_XMI_URI = ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI;

				/**
	 * The feature id for the '<em><b>Frag2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_BENCHMARK__FRAG2 = ABSTRACT_GRABATS_BENCHMARK__FRAG2;

				/**
	 * The number of structural features of the '<em>Load Benchmark</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOAD_BENCHMARK_FEATURE_COUNT = ABSTRACT_GRABATS_BENCHMARK_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.SaveBenchmarkImpl <em>Save Benchmark</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.SaveBenchmarkImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getSaveBenchmark()
	 * @generated
	 */
    int SAVE_BENCHMARK = 2;

    /**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SAVE_BENCHMARK__RMEMORY_USAGE = ABSTRACT_GRABATS_BENCHMARK__RMEMORY_USAGE;

    /**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SAVE_BENCHMARK__RTIME = ABSTRACT_GRABATS_BENCHMARK__RTIME;

    /**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SAVE_BENCHMARK__RNUMBER_OF_OBJECTS = ABSTRACT_GRABATS_BENCHMARK__RNUMBER_OF_OBJECTS;

    /**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__WEAK_UNLOADS = ABSTRACT_GRABATS_BENCHMARK__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__WEAK_LOADS = ABSTRACT_GRABATS_BENCHMARK__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__LOADS = ABSTRACT_GRABATS_BENCHMARK__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__UNLOADS = ABSTRACT_GRABATS_BENCHMARK__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__MAX_ACTIVE_FAGMENTS = ABSTRACT_GRABATS_BENCHMARK__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__MAX_WEAK_FRAGMENTS = ABSTRACT_GRABATS_BENCHMARK__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__REMARKS = ABSTRACT_GRABATS_BENCHMARK__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SAVE_BENCHMARK__PMODEL_NAME = ABSTRACT_GRABATS_BENCHMARK__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>PSource Xmi URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SAVE_BENCHMARK__PSOURCE_XMI_URI = ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI;

				/**
	 * The feature id for the '<em><b>Frag2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAVE_BENCHMARK__FRAG2 = ABSTRACT_GRABATS_BENCHMARK__FRAG2;

				/**
	 * The number of structural features of the '<em>Save Benchmark</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SAVE_BENCHMARK_FEATURE_COUNT = ABSTRACT_GRABATS_BENCHMARK_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl <em>Measurement</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.MeasurementImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getMeasurement()
	 * @generated
	 */
    int MEASUREMENT = 4;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT__NAME = 0;

    /**
	 * The feature id for the '<em><b>Candidates</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT__CANDIDATES = 1;

    /**
	 * The feature id for the '<em><b>Benchmarks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT__BENCHMARKS = 2;

    /**
	 * The feature id for the '<em><b>Runs</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT__RUNS = 3;

    /**
	 * The feature id for the '<em><b>Constraints</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT__CONSTRAINTS = 4;

    /**
	 * The feature id for the '<em><b>Number Of Benchmarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT__NUMBER_OF_BENCHMARKS = 5;

    /**
	 * The number of structural features of the '<em>Measurement</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MEASUREMENT_FEATURE_COUNT = 6;

    /**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.QueryBenchmarkImpl <em>Query Benchmark</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.QueryBenchmarkImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getQueryBenchmark()
	 * @generated
	 */
	int QUERY_BENCHMARK = 5;

				/**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__RMEMORY_USAGE = ABSTRACT_GRABATS_BENCHMARK__RMEMORY_USAGE;

				/**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__RTIME = ABSTRACT_GRABATS_BENCHMARK__RTIME;

				/**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__RNUMBER_OF_OBJECTS = ABSTRACT_GRABATS_BENCHMARK__RNUMBER_OF_OBJECTS;

				/**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__WEAK_UNLOADS = ABSTRACT_GRABATS_BENCHMARK__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__WEAK_LOADS = ABSTRACT_GRABATS_BENCHMARK__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__LOADS = ABSTRACT_GRABATS_BENCHMARK__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__UNLOADS = ABSTRACT_GRABATS_BENCHMARK__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__MAX_ACTIVE_FAGMENTS = ABSTRACT_GRABATS_BENCHMARK__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__MAX_WEAK_FRAGMENTS = ABSTRACT_GRABATS_BENCHMARK__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__REMARKS = ABSTRACT_GRABATS_BENCHMARK__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__PMODEL_NAME = ABSTRACT_GRABATS_BENCHMARK__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>PSource Xmi URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__PSOURCE_XMI_URI = ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI;

				/**
	 * The feature id for the '<em><b>Frag2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__FRAG2 = ABSTRACT_GRABATS_BENCHMARK__FRAG2;

				/**
	 * The feature id for the '<em><b>With Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK__WITH_INDEX = ABSTRACT_GRABATS_BENCHMARK_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Query Benchmark</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_BENCHMARK_FEATURE_COUNT = ABSTRACT_GRABATS_BENCHMARK_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.ImportTestModelImpl <em>Import Test Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.ImportTestModelImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getImportTestModel()
	 * @generated
	 */
	int IMPORT_TEST_MODEL = 6;

				/**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__RMEMORY_USAGE = BENCHMARK__RMEMORY_USAGE;

				/**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__RTIME = BENCHMARK__RTIME;

				/**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__RNUMBER_OF_OBJECTS = BENCHMARK__RNUMBER_OF_OBJECTS;

				/**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__WEAK_UNLOADS = BENCHMARK__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__WEAK_LOADS = BENCHMARK__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__LOADS = BENCHMARK__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__UNLOADS = BENCHMARK__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__MAX_ACTIVE_FAGMENTS = BENCHMARK__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__MAX_WEAK_FRAGMENTS = BENCHMARK__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__REMARKS = BENCHMARK__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__PMODEL_NAME = BENCHMARK__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>Fragment Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__FRAGMENT_SIZE = BENCHMARK_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Model Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL__MODEL_SIZE = BENCHMARK_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>Import Test Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TEST_MODEL_FEATURE_COUNT = BENCHMARK_FEATURE_COUNT + 2;

				/**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.LoadFromTestModelImpl <em>Load From Test Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.LoadFromTestModelImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getLoadFromTestModel()
	 * @generated
	 */
	int LOAD_FROM_TEST_MODEL = 7;

				/**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__RMEMORY_USAGE = BENCHMARK__RMEMORY_USAGE;

				/**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__RTIME = BENCHMARK__RTIME;

				/**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__RNUMBER_OF_OBJECTS = BENCHMARK__RNUMBER_OF_OBJECTS;

				/**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__WEAK_UNLOADS = BENCHMARK__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__WEAK_LOADS = BENCHMARK__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__LOADS = BENCHMARK__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__UNLOADS = BENCHMARK__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__MAX_ACTIVE_FAGMENTS = BENCHMARK__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__MAX_WEAK_FRAGMENTS = BENCHMARK__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__REMARKS = BENCHMARK__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__PMODEL_NAME = BENCHMARK__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>Load Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__LOAD_SIZE = BENCHMARK_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Import Benchmark</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK = BENCHMARK_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>Load From Test Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FROM_TEST_MODEL_FEATURE_COUNT = BENCHMARK_FEATURE_COUNT + 2;

				/**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.impl.ImportDeepTestModelImpl <em>Import Deep Test Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.impl.ImportDeepTestModelImpl
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getImportDeepTestModel()
	 * @generated
	 */
	int IMPORT_DEEP_TEST_MODEL = 8;

				/**
	 * The feature id for the '<em><b>RMemory Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__RMEMORY_USAGE = IMPORT_TEST_MODEL__RMEMORY_USAGE;

				/**
	 * The feature id for the '<em><b>RTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__RTIME = IMPORT_TEST_MODEL__RTIME;

				/**
	 * The feature id for the '<em><b>RNumber Of Objects</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__RNUMBER_OF_OBJECTS = IMPORT_TEST_MODEL__RNUMBER_OF_OBJECTS;

				/**
	 * The feature id for the '<em><b>Weak Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__WEAK_UNLOADS = IMPORT_TEST_MODEL__WEAK_UNLOADS;

				/**
	 * The feature id for the '<em><b>Weak Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__WEAK_LOADS = IMPORT_TEST_MODEL__WEAK_LOADS;

				/**
	 * The feature id for the '<em><b>Loads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__LOADS = IMPORT_TEST_MODEL__LOADS;

				/**
	 * The feature id for the '<em><b>Unloads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__UNLOADS = IMPORT_TEST_MODEL__UNLOADS;

				/**
	 * The feature id for the '<em><b>Max Active Fagments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__MAX_ACTIVE_FAGMENTS = IMPORT_TEST_MODEL__MAX_ACTIVE_FAGMENTS;

				/**
	 * The feature id for the '<em><b>Max Weak Fragments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__MAX_WEAK_FRAGMENTS = IMPORT_TEST_MODEL__MAX_WEAK_FRAGMENTS;

				/**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__REMARKS = IMPORT_TEST_MODEL__REMARKS;

				/**
	 * The feature id for the '<em><b>PModel Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__PMODEL_NAME = IMPORT_TEST_MODEL__PMODEL_NAME;

				/**
	 * The feature id for the '<em><b>Fragment Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__FRAGMENT_SIZE = IMPORT_TEST_MODEL__FRAGMENT_SIZE;

				/**
	 * The feature id for the '<em><b>Model Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__MODEL_SIZE = IMPORT_TEST_MODEL__MODEL_SIZE;

				/**
	 * The feature id for the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL__BASE = IMPORT_TEST_MODEL_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Import Deep Test Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DEEP_TEST_MODEL_FEATURE_COUNT = IMPORT_TEST_MODEL_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link de.hub.emffrag.bm.benchmark.EMFFragCandidates <em>EMF Frag Candidates</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.EMFFragCandidates
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getEMFFragCandidates()
	 * @generated
	 */
    int EMF_FRAG_CANDIDATES = 9;

    /**
	 * The meta object id for the '<em>Print Stream</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see java.io.PrintStream
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getPrintStream()
	 * @generated
	 */
    int PRINT_STREAM = 10;


    /**
	 * The meta object id for the '<em>IConstraint</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.hub.emffrag.bm.benchmark.util.IConstraint
	 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getIConstraint()
	 * @generated
	 */
    int ICONSTRAINT = 11;


    /**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.Benchmark <em>Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Benchmark</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark
	 * @generated
	 */
    EClass getBenchmark();

    /**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRMemoryUsage <em>RMemory Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RMemory Usage</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getRMemoryUsage()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_RMemoryUsage();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRTime <em>RTime</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RTime</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getRTime()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_RTime();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRNumberOfObjects <em>RNumber Of Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RNumber Of Objects</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getRNumberOfObjects()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_RNumberOfObjects();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getWeakUnloads <em>Weak Unloads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weak Unloads</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getWeakUnloads()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_WeakUnloads();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getWeakLoads <em>Weak Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weak Loads</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getWeakLoads()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_WeakLoads();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getLoads <em>Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loads</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getLoads()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_Loads();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getUnloads <em>Unloads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unloads</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getUnloads()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_Unloads();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getMaxActiveFagments <em>Max Active Fagments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Active Fagments</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getMaxActiveFagments()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_MaxActiveFagments();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getMaxWeakFragments <em>Max Weak Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Weak Fragments</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getMaxWeakFragments()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_MaxWeakFragments();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getRemarks <em>Remarks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remarks</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getRemarks()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_Remarks();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Benchmark#getPModelName <em>PModel Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PModel Name</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark#getPModelName()
	 * @see #getBenchmark()
	 * @generated
	 */
	EAttribute getBenchmark_PModelName();

				/**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.LoadBenchmark <em>Load Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load Benchmark</em>'.
	 * @see de.hub.emffrag.bm.benchmark.LoadBenchmark
	 * @generated
	 */
    EClass getLoadBenchmark();

    /**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.SaveBenchmark <em>Save Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Save Benchmark</em>'.
	 * @see de.hub.emffrag.bm.benchmark.SaveBenchmark
	 * @generated
	 */
    EClass getSaveBenchmark();

    /**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark <em>Abstract Grabats Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Grabats Benchmark</em>'.
	 * @see de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark
	 * @generated
	 */
    EClass getAbstractGrabatsBenchmark();

    /**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#getPSourceXmiURI <em>PSource Xmi URI</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PSource Xmi URI</em>'.
	 * @see de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#getPSourceXmiURI()
	 * @see #getAbstractGrabatsBenchmark()
	 * @generated
	 */
    EAttribute getAbstractGrabatsBenchmark_PSourceXmiURI();

    /**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#isFrag2 <em>Frag2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frag2</em>'.
	 * @see de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark#isFrag2()
	 * @see #getAbstractGrabatsBenchmark()
	 * @generated
	 */
	EAttribute getAbstractGrabatsBenchmark_Frag2();

				/**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement
	 * @generated
	 */
    EClass getMeasurement();

    /**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Measurement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement#getName()
	 * @see #getMeasurement()
	 * @generated
	 */
    EAttribute getMeasurement_Name();

    /**
	 * Returns the meta object for the attribute list '{@link de.hub.emffrag.bm.benchmark.Measurement#getCandidates <em>Candidates</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Candidates</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement#getCandidates()
	 * @see #getMeasurement()
	 * @generated
	 */
    EAttribute getMeasurement_Candidates();

    /**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.bm.benchmark.Measurement#getBenchmarks <em>Benchmarks</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Benchmarks</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement#getBenchmarks()
	 * @see #getMeasurement()
	 * @generated
	 */
    EReference getMeasurement_Benchmarks();

    /**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Measurement#getRuns <em>Runs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Runs</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement#getRuns()
	 * @see #getMeasurement()
	 * @generated
	 */
    EAttribute getMeasurement_Runs();

    /**
	 * Returns the meta object for the attribute list '{@link de.hub.emffrag.bm.benchmark.Measurement#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Constraints</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement#getConstraints()
	 * @see #getMeasurement()
	 * @generated
	 */
    EAttribute getMeasurement_Constraints();

    /**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.Measurement#getNumberOfBenchmarks <em>Number Of Benchmarks</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Benchmarks</em>'.
	 * @see de.hub.emffrag.bm.benchmark.Measurement#getNumberOfBenchmarks()
	 * @see #getMeasurement()
	 * @generated
	 */
    EAttribute getMeasurement_NumberOfBenchmarks();

    /**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.QueryBenchmark <em>Query Benchmark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Benchmark</em>'.
	 * @see de.hub.emffrag.bm.benchmark.QueryBenchmark
	 * @generated
	 */
	EClass getQueryBenchmark();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.QueryBenchmark#isWithIndex <em>With Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>With Index</em>'.
	 * @see de.hub.emffrag.bm.benchmark.QueryBenchmark#isWithIndex()
	 * @see #getQueryBenchmark()
	 * @generated
	 */
	EAttribute getQueryBenchmark_WithIndex();

				/**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.ImportTestModel <em>Import Test Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Test Model</em>'.
	 * @see de.hub.emffrag.bm.benchmark.ImportTestModel
	 * @generated
	 */
	EClass getImportTestModel();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.ImportTestModel#getFragmentSize <em>Fragment Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fragment Size</em>'.
	 * @see de.hub.emffrag.bm.benchmark.ImportTestModel#getFragmentSize()
	 * @see #getImportTestModel()
	 * @generated
	 */
	EAttribute getImportTestModel_FragmentSize();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.ImportTestModel#getModelSize <em>Model Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Size</em>'.
	 * @see de.hub.emffrag.bm.benchmark.ImportTestModel#getModelSize()
	 * @see #getImportTestModel()
	 * @generated
	 */
	EAttribute getImportTestModel_ModelSize();

				/**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel <em>Load From Test Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load From Test Model</em>'.
	 * @see de.hub.emffrag.bm.benchmark.LoadFromTestModel
	 * @generated
	 */
	EClass getLoadFromTestModel();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel#getLoadSize <em>Load Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Size</em>'.
	 * @see de.hub.emffrag.bm.benchmark.LoadFromTestModel#getLoadSize()
	 * @see #getLoadFromTestModel()
	 * @generated
	 */
	EAttribute getLoadFromTestModel_LoadSize();

				/**
	 * Returns the meta object for the reference '{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel#getImportBenchmark <em>Import Benchmark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Import Benchmark</em>'.
	 * @see de.hub.emffrag.bm.benchmark.LoadFromTestModel#getImportBenchmark()
	 * @see #getLoadFromTestModel()
	 * @generated
	 */
	EReference getLoadFromTestModel_ImportBenchmark();

				/**
	 * Returns the meta object for class '{@link de.hub.emffrag.bm.benchmark.ImportDeepTestModel <em>Import Deep Test Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Deep Test Model</em>'.
	 * @see de.hub.emffrag.bm.benchmark.ImportDeepTestModel
	 * @generated
	 */
	EClass getImportDeepTestModel();

				/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.bm.benchmark.ImportDeepTestModel#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see de.hub.emffrag.bm.benchmark.ImportDeepTestModel#getBase()
	 * @see #getImportDeepTestModel()
	 * @generated
	 */
	EAttribute getImportDeepTestModel_Base();

				/**
	 * Returns the meta object for enum '{@link de.hub.emffrag.bm.benchmark.EMFFragCandidates <em>EMF Frag Candidates</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EMF Frag Candidates</em>'.
	 * @see de.hub.emffrag.bm.benchmark.EMFFragCandidates
	 * @generated
	 */
    EEnum getEMFFragCandidates();

    /**
	 * Returns the meta object for data type '{@link java.io.PrintStream <em>Print Stream</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Print Stream</em>'.
	 * @see java.io.PrintStream
	 * @model instanceClass="java.io.PrintStream" serializeable="false"
	 * @generated
	 */
    EDataType getPrintStream();

    /**
	 * Returns the meta object for data type '{@link de.hub.emffrag.bm.benchmark.util.IConstraint <em>IConstraint</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IConstraint</em>'.
	 * @see de.hub.emffrag.bm.benchmark.util.IConstraint
	 * @model instanceClass="de.hub.emffrag.bm.benchmark.util.IConstraint"
	 * @generated
	 */
    EDataType getIConstraint();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    BenchmarkFactory getBenchmarkFactory();

    /**
	 * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
	 * @generated
	 */
    interface Literals {
        /**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl <em>Benchmark</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getBenchmark()
		 * @generated
		 */
        EClass BENCHMARK = eINSTANCE.getBenchmark();

        /**
		 * The meta object literal for the '<em><b>RMemory Usage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__RMEMORY_USAGE = eINSTANCE.getBenchmark_RMemoryUsage();

								/**
		 * The meta object literal for the '<em><b>RTime</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__RTIME = eINSTANCE.getBenchmark_RTime();

								/**
		 * The meta object literal for the '<em><b>RNumber Of Objects</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__RNUMBER_OF_OBJECTS = eINSTANCE.getBenchmark_RNumberOfObjects();

								/**
		 * The meta object literal for the '<em><b>Weak Unloads</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__WEAK_UNLOADS = eINSTANCE.getBenchmark_WeakUnloads();

								/**
		 * The meta object literal for the '<em><b>Weak Loads</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__WEAK_LOADS = eINSTANCE.getBenchmark_WeakLoads();

								/**
		 * The meta object literal for the '<em><b>Loads</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__LOADS = eINSTANCE.getBenchmark_Loads();

								/**
		 * The meta object literal for the '<em><b>Unloads</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__UNLOADS = eINSTANCE.getBenchmark_Unloads();

								/**
		 * The meta object literal for the '<em><b>Max Active Fagments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__MAX_ACTIVE_FAGMENTS = eINSTANCE.getBenchmark_MaxActiveFagments();

								/**
		 * The meta object literal for the '<em><b>Max Weak Fragments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__MAX_WEAK_FRAGMENTS = eINSTANCE.getBenchmark_MaxWeakFragments();

								/**
		 * The meta object literal for the '<em><b>Remarks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__REMARKS = eINSTANCE.getBenchmark_Remarks();

								/**
		 * The meta object literal for the '<em><b>PModel Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENCHMARK__PMODEL_NAME = eINSTANCE.getBenchmark_PModelName();

								/**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.LoadBenchmarkImpl <em>Load Benchmark</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.LoadBenchmarkImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getLoadBenchmark()
		 * @generated
		 */
        EClass LOAD_BENCHMARK = eINSTANCE.getLoadBenchmark();

        /**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.SaveBenchmarkImpl <em>Save Benchmark</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.SaveBenchmarkImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getSaveBenchmark()
		 * @generated
		 */
        EClass SAVE_BENCHMARK = eINSTANCE.getSaveBenchmark();

        /**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.AbstractGrabatsBenchmarkImpl <em>Abstract Grabats Benchmark</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.AbstractGrabatsBenchmarkImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getAbstractGrabatsBenchmark()
		 * @generated
		 */
        EClass ABSTRACT_GRABATS_BENCHMARK = eINSTANCE.getAbstractGrabatsBenchmark();

        /**
		 * The meta object literal for the '<em><b>PSource Xmi URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI = eINSTANCE.getAbstractGrabatsBenchmark_PSourceXmiURI();

        /**
		 * The meta object literal for the '<em><b>Frag2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_GRABATS_BENCHMARK__FRAG2 = eINSTANCE.getAbstractGrabatsBenchmark_Frag2();

								/**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.MeasurementImpl <em>Measurement</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.MeasurementImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getMeasurement()
		 * @generated
		 */
        EClass MEASUREMENT = eINSTANCE.getMeasurement();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MEASUREMENT__NAME = eINSTANCE.getMeasurement_Name();

        /**
		 * The meta object literal for the '<em><b>Candidates</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MEASUREMENT__CANDIDATES = eINSTANCE.getMeasurement_Candidates();

        /**
		 * The meta object literal for the '<em><b>Benchmarks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MEASUREMENT__BENCHMARKS = eINSTANCE.getMeasurement_Benchmarks();

        /**
		 * The meta object literal for the '<em><b>Runs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MEASUREMENT__RUNS = eINSTANCE.getMeasurement_Runs();

        /**
		 * The meta object literal for the '<em><b>Constraints</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MEASUREMENT__CONSTRAINTS = eINSTANCE.getMeasurement_Constraints();

        /**
		 * The meta object literal for the '<em><b>Number Of Benchmarks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MEASUREMENT__NUMBER_OF_BENCHMARKS = eINSTANCE.getMeasurement_NumberOfBenchmarks();

        /**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.QueryBenchmarkImpl <em>Query Benchmark</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.QueryBenchmarkImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getQueryBenchmark()
		 * @generated
		 */
		EClass QUERY_BENCHMARK = eINSTANCE.getQueryBenchmark();

								/**
		 * The meta object literal for the '<em><b>With Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_BENCHMARK__WITH_INDEX = eINSTANCE.getQueryBenchmark_WithIndex();

								/**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.ImportTestModelImpl <em>Import Test Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.ImportTestModelImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getImportTestModel()
		 * @generated
		 */
		EClass IMPORT_TEST_MODEL = eINSTANCE.getImportTestModel();

								/**
		 * The meta object literal for the '<em><b>Fragment Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT_TEST_MODEL__FRAGMENT_SIZE = eINSTANCE.getImportTestModel_FragmentSize();

								/**
		 * The meta object literal for the '<em><b>Model Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT_TEST_MODEL__MODEL_SIZE = eINSTANCE.getImportTestModel_ModelSize();

								/**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.LoadFromTestModelImpl <em>Load From Test Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.LoadFromTestModelImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getLoadFromTestModel()
		 * @generated
		 */
		EClass LOAD_FROM_TEST_MODEL = eINSTANCE.getLoadFromTestModel();

								/**
		 * The meta object literal for the '<em><b>Load Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_FROM_TEST_MODEL__LOAD_SIZE = eINSTANCE.getLoadFromTestModel_LoadSize();

								/**
		 * The meta object literal for the '<em><b>Import Benchmark</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK = eINSTANCE.getLoadFromTestModel_ImportBenchmark();

								/**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.impl.ImportDeepTestModelImpl <em>Import Deep Test Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.impl.ImportDeepTestModelImpl
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getImportDeepTestModel()
		 * @generated
		 */
		EClass IMPORT_DEEP_TEST_MODEL = eINSTANCE.getImportDeepTestModel();

								/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT_DEEP_TEST_MODEL__BASE = eINSTANCE.getImportDeepTestModel_Base();

								/**
		 * The meta object literal for the '{@link de.hub.emffrag.bm.benchmark.EMFFragCandidates <em>EMF Frag Candidates</em>}' enum.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.EMFFragCandidates
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getEMFFragCandidates()
		 * @generated
		 */
        EEnum EMF_FRAG_CANDIDATES = eINSTANCE.getEMFFragCandidates();

        /**
		 * The meta object literal for the '<em>Print Stream</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see java.io.PrintStream
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getPrintStream()
		 * @generated
		 */
        EDataType PRINT_STREAM = eINSTANCE.getPrintStream();

        /**
		 * The meta object literal for the '<em>IConstraint</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.hub.emffrag.bm.benchmark.util.IConstraint
		 * @see de.hub.emffrag.bm.benchmark.impl.BenchmarkPackageImpl#getIConstraint()
		 * @generated
		 */
        EDataType ICONSTRAINT = eINSTANCE.getIConstraint();

    }

} //BenchmarkPackage
