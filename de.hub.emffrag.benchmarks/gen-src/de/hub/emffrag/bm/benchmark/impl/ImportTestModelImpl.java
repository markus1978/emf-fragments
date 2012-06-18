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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.ImportTestModel;
import de.hub.emffrag.bm.impl.AbstractImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Test Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.ImportTestModelImpl#getFragmentSize <em>Fragment Size</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.ImportTestModelImpl#getModelSize <em>Model Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportTestModelImpl extends BenchmarkImpl implements ImportTestModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

	/**
	 * The default value of the '{@link #getFragmentSize() <em>Fragment Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragmentSize()
	 * @generated
	 * @ordered
	 */
	protected static final int FRAGMENT_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFragmentSize() <em>Fragment Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragmentSize()
	 * @generated
	 * @ordered
	 */
	protected int fragmentSize = FRAGMENT_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelSize() <em>Model Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelSize()
	 * @generated
	 * @ordered
	 */
	protected static final int MODEL_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getModelSize() <em>Model Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelSize()
	 * @generated
	 * @ordered
	 */
	protected int modelSize = MODEL_SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportTestModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.IMPORT_TEST_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFragmentSize() {
		return fragmentSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFragmentSize(int newFragmentSize) {
		int oldFragmentSize = fragmentSize;
		fragmentSize = newFragmentSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.IMPORT_TEST_MODEL__FRAGMENT_SIZE, oldFragmentSize, fragmentSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getModelSize() {
		return modelSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelSize(int newModelSize) {
		int oldModelSize = modelSize;
		modelSize = newModelSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.IMPORT_TEST_MODEL__MODEL_SIZE, oldModelSize, modelSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.IMPORT_TEST_MODEL__FRAGMENT_SIZE:
				return getFragmentSize();
			case BenchmarkPackage.IMPORT_TEST_MODEL__MODEL_SIZE:
				return getModelSize();
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
			case BenchmarkPackage.IMPORT_TEST_MODEL__FRAGMENT_SIZE:
				setFragmentSize((Integer)newValue);
				return;
			case BenchmarkPackage.IMPORT_TEST_MODEL__MODEL_SIZE:
				setModelSize((Integer)newValue);
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
			case BenchmarkPackage.IMPORT_TEST_MODEL__FRAGMENT_SIZE:
				setFragmentSize(FRAGMENT_SIZE_EDEFAULT);
				return;
			case BenchmarkPackage.IMPORT_TEST_MODEL__MODEL_SIZE:
				setModelSize(MODEL_SIZE_EDEFAULT);
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
			case BenchmarkPackage.IMPORT_TEST_MODEL__FRAGMENT_SIZE:
				return fragmentSize != FRAGMENT_SIZE_EDEFAULT;
			case BenchmarkPackage.IMPORT_TEST_MODEL__MODEL_SIZE:
				return modelSize != MODEL_SIZE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    @Override
    public void run(EMFFragCandidates candidate) {
        try {
            AbstractImpl candidateImpl = MeasurementImpl.CANDIDATE_FACTORY.createCandidateImpl(candidate);
            
            MyGauge gauge = new MyGauge();
            gauge.startTimeMeasure();

            int size = 0;
            EObject root = candidateImpl.create(this, null, false);
            size++;
            while(size < getModelSize()) {                                
            	EObject fragmentRoot = candidateImpl.create(this, root, true);
            	size++;
            	for (int ii = 1; ii < getFragmentSize(); ii++) {
            		candidateImpl.create(this, fragmentRoot, false);
            		size++;
            	}
            }          
                        
            candidateImpl.finalize(this);
            gauge.takeTimeMeasure();
            gauge.takeMemoryMeasure();
            setRNumberOfObjects(size);            
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
		out.print(getModelSize()); out.print(" ");
		out.print(getFragmentSize()); out.print(" ");
		super.printResult(out, candidate, run);
	}

	@Override
	public void printResultHeader(PrintStream out) {
		out.print("modelSize fragmentDepth");
		super.printResultHeader(out);
	}
    
    

} //ImportTestModelImpl
