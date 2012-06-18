/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.google.common.base.Throwables;

import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.benchmark.QueryBenchmark;
import de.hub.emffrag.bm.impl.AbstractImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Benchmark</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.QueryBenchmarkImpl#isWithIndex <em>With Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryBenchmarkImpl extends AbstractGrabatsBenchmarkImpl implements QueryBenchmark {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";
	/**
	 * The default value of the '{@link #isWithIndex() <em>With Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWithIndex()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WITH_INDEX_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isWithIndex() <em>With Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWithIndex()
	 * @generated
	 * @ordered
	 */
	protected boolean withIndex = WITH_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QueryBenchmarkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.QUERY_BENCHMARK;
	}


    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWithIndex() {
		return withIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWithIndex(boolean newWithIndex) {
		boolean oldWithIndex = withIndex;
		withIndex = newWithIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.QUERY_BENCHMARK__WITH_INDEX, oldWithIndex, withIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.QUERY_BENCHMARK__WITH_INDEX:
				return isWithIndex();
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
			case BenchmarkPackage.QUERY_BENCHMARK__WITH_INDEX:
				setWithIndex((Boolean)newValue);
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
			case BenchmarkPackage.QUERY_BENCHMARK__WITH_INDEX:
				setWithIndex(WITH_INDEX_EDEFAULT);
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
			case BenchmarkPackage.QUERY_BENCHMARK__WITH_INDEX:
				return withIndex != WITH_INDEX_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

				@Override
    public void run(EMFFragCandidates candidate) {
        try {
            AbstractImpl candidateImpl = MeasurementImpl.CANDIDATE_FACTORY.createCandidateImpl(candidate);
            int result = candidateImpl.grabatsQueryModel(this, new MyGauge());
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
} //QueryBenchmarkImpl
