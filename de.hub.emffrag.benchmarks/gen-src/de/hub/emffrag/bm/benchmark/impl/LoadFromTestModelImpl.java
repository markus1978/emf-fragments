/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.impl;

import java.io.PrintStream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.ImportDeepTestModel;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.benchmark.LoadFromTestModel;
import de.hub.emffrag.bm.impl.AbstractImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Load From Test Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.LoadFromTestModelImpl#getLoadSize <em>Load Size</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.LoadFromTestModelImpl#getImportBenchmark <em>Import Benchmark</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoadFromTestModelImpl extends BenchmarkImpl implements LoadFromTestModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

	/**
	 * The default value of the '{@link #getLoadSize() <em>Load Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadSize()
	 * @generated
	 * @ordered
	 */
	protected static final int LOAD_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLoadSize() <em>Load Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadSize()
	 * @generated
	 * @ordered
	 */
	protected int loadSize = LOAD_SIZE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImportBenchmark() <em>Import Benchmark</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportBenchmark()
	 * @generated
	 * @ordered
	 */
	protected ImportTestModel importBenchmark;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LoadFromTestModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.LOAD_FROM_TEST_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLoadSize() {
		return loadSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadSize(int newLoadSize) {
		int oldLoadSize = loadSize;
		loadSize = newLoadSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.LOAD_FROM_TEST_MODEL__LOAD_SIZE, oldLoadSize, loadSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportTestModel getImportBenchmark() {
		if (importBenchmark != null && importBenchmark.eIsProxy()) {
			InternalEObject oldImportBenchmark = (InternalEObject)importBenchmark;
			importBenchmark = (ImportTestModel)eResolveProxy(oldImportBenchmark);
			if (importBenchmark != oldImportBenchmark) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BenchmarkPackage.LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK, oldImportBenchmark, importBenchmark));
			}
		}
		return importBenchmark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportTestModel basicGetImportBenchmark() {
		return importBenchmark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportBenchmark(ImportTestModel newImportBenchmark) {
		ImportTestModel oldImportBenchmark = importBenchmark;
		importBenchmark = newImportBenchmark;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK, oldImportBenchmark, importBenchmark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__LOAD_SIZE:
				return getLoadSize();
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK:
				if (resolve) return getImportBenchmark();
				return basicGetImportBenchmark();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__LOAD_SIZE:
				setLoadSize((Integer)newValue);
				return;
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK:
				setImportBenchmark((ImportTestModel)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__LOAD_SIZE:
				setLoadSize(LOAD_SIZE_EDEFAULT);
				return;
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK:
				setImportBenchmark((ImportTestModel)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__LOAD_SIZE:
				return loadSize != LOAD_SIZE_EDEFAULT;
			case BenchmarkPackage.LOAD_FROM_TEST_MODEL__IMPORT_BENCHMARK:
				return importBenchmark != null;
		}
		return super.eIsSet(featureID);
	}

    @Override
    public void run(EMFFragCandidates candidate) {
        try {
            AbstractImpl candidateImpl = MeasurementImpl.CANDIDATE_FACTORY.createCandidateImpl(candidate);
            MyGauge gauge = new MyGauge();       
            int result = candidateImpl.traverse(this, gauge, getLoadSize());                       
            gauge.takeMemoryMeasure();
            setRNumberOfObjects(result);
            candidateImpl.addStatistics(this);   
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }
    
    @Override
    public String toString() {
        return eClass().getName() + " " + getPModelName();
    }

	@Override
	public void printResult(PrintStream out, EMFFragCandidates candidate, int run) {
		if (this.getImportBenchmark() instanceof ImportDeepTestModel) {
			out.print(((ImportDeepTestModel)this.getImportBenchmark()).getBase()); out.print(" ");	
		} else {
			out.print("- ");
		}
		out.print(this.getImportBenchmark().getModelSize()); out.print(" ");
		out.print(this.getImportBenchmark().getFragmentSize()); out.print(" ");
		out.print(this.getLoadSize()); out.print(" ");
		super.printResult(out, candidate, run);
	}

	@Override
	public void printResultHeader(PrintStream out) {		
		out.print("base modelSize fragmentDepth loadDepth");
		super.printResultHeader(out);
	}
    

} //LoadFromTestModelImpl
