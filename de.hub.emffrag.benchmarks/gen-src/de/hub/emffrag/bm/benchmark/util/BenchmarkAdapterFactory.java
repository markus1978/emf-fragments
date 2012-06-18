/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.util;

import de.hub.emffrag.bm.benchmark.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage
 * @generated
 */
public class BenchmarkAdapterFactory extends AdapterFactoryImpl {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";
				/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static BenchmarkPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BenchmarkAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BenchmarkPackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
    @Override
    public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected BenchmarkSwitch<Adapter> modelSwitch =
        new BenchmarkSwitch<Adapter>() {
			@Override
			public Adapter caseBenchmark(Benchmark object) {
				return createBenchmarkAdapter();
			}
			@Override
			public Adapter caseLoadBenchmark(LoadBenchmark object) {
				return createLoadBenchmarkAdapter();
			}
			@Override
			public Adapter caseSaveBenchmark(SaveBenchmark object) {
				return createSaveBenchmarkAdapter();
			}
			@Override
			public Adapter caseAbstractGrabatsBenchmark(AbstractGrabatsBenchmark object) {
				return createAbstractGrabatsBenchmarkAdapter();
			}
			@Override
			public Adapter caseMeasurement(Measurement object) {
				return createMeasurementAdapter();
			}
			@Override
			public Adapter caseQueryBenchmark(QueryBenchmark object) {
				return createQueryBenchmarkAdapter();
			}
			@Override
			public Adapter caseImportTestModel(ImportTestModel object) {
				return createImportTestModelAdapter();
			}
			@Override
			public Adapter caseLoadFromTestModel(LoadFromTestModel object) {
				return createLoadFromTestModelAdapter();
			}
			@Override
			public Adapter caseImportDeepTestModel(ImportDeepTestModel object) {
				return createImportDeepTestModelAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
    @Override
    public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.Benchmark <em>Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.Benchmark
	 * @generated
	 */
    public Adapter createBenchmarkAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.LoadBenchmark <em>Load Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.LoadBenchmark
	 * @generated
	 */
    public Adapter createLoadBenchmarkAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.SaveBenchmark <em>Save Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.SaveBenchmark
	 * @generated
	 */
    public Adapter createSaveBenchmarkAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark <em>Abstract Grabats Benchmark</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark
	 * @generated
	 */
    public Adapter createAbstractGrabatsBenchmarkAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.Measurement
	 * @generated
	 */
    public Adapter createMeasurementAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.QueryBenchmark <em>Query Benchmark</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.QueryBenchmark
	 * @generated
	 */
	public Adapter createQueryBenchmarkAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.ImportTestModel <em>Import Test Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.ImportTestModel
	 * @generated
	 */
	public Adapter createImportTestModelAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.LoadFromTestModel <em>Load From Test Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.LoadFromTestModel
	 * @generated
	 */
	public Adapter createLoadFromTestModelAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.bm.benchmark.ImportDeepTestModel <em>Import Deep Test Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.bm.benchmark.ImportDeepTestModel
	 * @generated
	 */
	public Adapter createImportDeepTestModelAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
    public Adapter createEObjectAdapter() {
		return null;
	}

} //BenchmarkAdapterFactory
