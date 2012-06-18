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
import de.hub.emffrag.bm.benchmark.ImportDeepTestModel;
import de.hub.emffrag.bm.benchmark.util.BreathFirstGenerator;
import de.hub.emffrag.bm.impl.AbstractImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Import Deep Test Model</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.ImportDeepTestModelImpl#getBase <em>Base</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportDeepTestModelImpl extends ImportTestModelImpl implements ImportDeepTestModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final int BASE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected int base = BASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportDeepTestModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.IMPORT_DEEP_TEST_MODEL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase(int newBase) {
		int oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.IMPORT_DEEP_TEST_MODEL__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.IMPORT_DEEP_TEST_MODEL__BASE:
				return getBase();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BenchmarkPackage.IMPORT_DEEP_TEST_MODEL__BASE:
				setBase((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BenchmarkPackage.IMPORT_DEEP_TEST_MODEL__BASE:
				setBase(BASE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BenchmarkPackage.IMPORT_DEEP_TEST_MODEL__BASE:
				return base != BASE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public void run(EMFFragCandidates candidate) {
		try {
			final AbstractImpl candidateImpl = MeasurementImpl.CANDIDATE_FACTORY.createCandidateImpl(candidate);

			MyGauge gauge = new MyGauge();		
			BreathFirstGenerator<EObject> treeGenerator = new BreathFirstGenerator<EObject>(getBase(), getFragmentSize(),
					getModelSize()) {
				@Override
				public EObject create(EObject parent, boolean frag) {
					return candidateImpl.create(ImportDeepTestModelImpl.this, parent, frag);
				}
			};

			gauge.startTimeMeasure();
			int size = treeGenerator.generateAll();			

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
		out.print(getBase()); out.print(" ");
		super.printResult(out, candidate, run);
	}

	@Override
	public void printResultHeader(PrintStream out) {
		out.print("base ");
		super.printResultHeader(out);
	}
} // ImportDeepTestModelImpl
