/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.util;

import de.hub.emffrag.bm.benchmark.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage
 * @generated
 */
public class BenchmarkSwitch<T> extends Switch<T> {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";
				/**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static BenchmarkPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BenchmarkSwitch() {
		if (modelPackage == null) {
			modelPackage = BenchmarkPackage.eINSTANCE;
		}
	}

    /**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BenchmarkPackage.BENCHMARK: {
				Benchmark benchmark = (Benchmark)theEObject;
				T result = caseBenchmark(benchmark);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.LOAD_BENCHMARK: {
				LoadBenchmark loadBenchmark = (LoadBenchmark)theEObject;
				T result = caseLoadBenchmark(loadBenchmark);
				if (result == null) result = caseAbstractGrabatsBenchmark(loadBenchmark);
				if (result == null) result = caseBenchmark(loadBenchmark);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.SAVE_BENCHMARK: {
				SaveBenchmark saveBenchmark = (SaveBenchmark)theEObject;
				T result = caseSaveBenchmark(saveBenchmark);
				if (result == null) result = caseAbstractGrabatsBenchmark(saveBenchmark);
				if (result == null) result = caseBenchmark(saveBenchmark);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK: {
				AbstractGrabatsBenchmark abstractGrabatsBenchmark = (AbstractGrabatsBenchmark)theEObject;
				T result = caseAbstractGrabatsBenchmark(abstractGrabatsBenchmark);
				if (result == null) result = caseBenchmark(abstractGrabatsBenchmark);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.MEASUREMENT: {
				Measurement measurement = (Measurement)theEObject;
				T result = caseMeasurement(measurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.QUERY_BENCHMARK: {
				QueryBenchmark queryBenchmark = (QueryBenchmark)theEObject;
				T result = caseQueryBenchmark(queryBenchmark);
				if (result == null) result = caseAbstractGrabatsBenchmark(queryBenchmark);
				if (result == null) result = caseBenchmark(queryBenchmark);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.IMPORT_TEST_MODEL: {
				ImportTestModel importTestModel = (ImportTestModel)theEObject;
				T result = caseImportTestModel(importTestModel);
				if (result == null) result = caseBenchmark(importTestModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL: {
				LoadFromTestModel loadFromTestModel = (LoadFromTestModel)theEObject;
				T result = caseLoadFromTestModel(loadFromTestModel);
				if (result == null) result = caseBenchmark(loadFromTestModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BenchmarkPackage.IMPORT_DEEP_TEST_MODEL: {
				ImportDeepTestModel importDeepTestModel = (ImportDeepTestModel)theEObject;
				T result = caseImportDeepTestModel(importDeepTestModel);
				if (result == null) result = caseImportTestModel(importDeepTestModel);
				if (result == null) result = caseBenchmark(importDeepTestModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Benchmark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseBenchmark(Benchmark object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Load Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Load Benchmark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseLoadBenchmark(LoadBenchmark object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Save Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Save Benchmark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseSaveBenchmark(SaveBenchmark object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Grabats Benchmark</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Grabats Benchmark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseAbstractGrabatsBenchmark(AbstractGrabatsBenchmark object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseMeasurement(Measurement object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Query Benchmark</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Benchmark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQueryBenchmark(QueryBenchmark object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Import Test Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import Test Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImportTestModel(ImportTestModel object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Load From Test Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Load From Test Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoadFromTestModel(LoadFromTestModel object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Import Deep Test Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import Deep Test Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImportDeepTestModel(ImportDeepTestModel object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
    @Override
    public T defaultCase(EObject object) {
		return null;
	}

} //BenchmarkSwitch
