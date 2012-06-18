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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.hub.emffrag.bm.benchmark.Benchmark;
import de.hub.emffrag.bm.benchmark.BenchmarkPackage;
import de.hub.emffrag.bm.benchmark.EMFFragCandidates;
import de.hub.emffrag.bm.impl.AbstractImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Benchmark</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getRMemoryUsage <em>RMemory Usage</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getRTime <em>RTime</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getRNumberOfObjects <em>RNumber Of Objects</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getWeakUnloads <em>Weak Unloads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getWeakLoads <em>Weak Loads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getLoads <em>Loads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getUnloads <em>Unloads</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getMaxActiveFagments <em>Max Active Fagments</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getMaxWeakFragments <em>Max Weak Fragments</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getRemarks <em>Remarks</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.BenchmarkImpl#getPModelName <em>PModel Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BenchmarkImpl extends EObjectImpl implements Benchmark {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";
				/**
	 * The default value of the '{@link #getRMemoryUsage() <em>RMemory Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRMemoryUsage()
	 * @generated
	 * @ordered
	 */
	protected static final long RMEMORY_USAGE_EDEFAULT = 0L;
	/**
	 * The cached value of the '{@link #getRMemoryUsage() <em>RMemory Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRMemoryUsage()
	 * @generated
	 * @ordered
	 */
	protected long rMemoryUsage = RMEMORY_USAGE_EDEFAULT;
	/**
	 * The default value of the '{@link #getRTime() <em>RTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTime()
	 * @generated
	 * @ordered
	 */
	protected static final long RTIME_EDEFAULT = 0L;
	/**
	 * The cached value of the '{@link #getRTime() <em>RTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTime()
	 * @generated
	 * @ordered
	 */
	protected long rTime = RTIME_EDEFAULT;
	/**
	 * The default value of the '{@link #getRNumberOfObjects() <em>RNumber Of Objects</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRNumberOfObjects()
	 * @generated
	 * @ordered
	 */
	protected static final int RNUMBER_OF_OBJECTS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getRNumberOfObjects() <em>RNumber Of Objects</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRNumberOfObjects()
	 * @generated
	 * @ordered
	 */
	protected int rNumberOfObjects = RNUMBER_OF_OBJECTS_EDEFAULT;
	/**
	 * The default value of the '{@link #getWeakUnloads() <em>Weak Unloads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeakUnloads()
	 * @generated
	 * @ordered
	 */
	protected static final int WEAK_UNLOADS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getWeakUnloads() <em>Weak Unloads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeakUnloads()
	 * @generated
	 * @ordered
	 */
	protected int weakUnloads = WEAK_UNLOADS_EDEFAULT;
	/**
	 * The default value of the '{@link #getWeakLoads() <em>Weak Loads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeakLoads()
	 * @generated
	 * @ordered
	 */
	protected static final int WEAK_LOADS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getWeakLoads() <em>Weak Loads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeakLoads()
	 * @generated
	 * @ordered
	 */
	protected int weakLoads = WEAK_LOADS_EDEFAULT;
	/**
	 * The default value of the '{@link #getLoads() <em>Loads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoads()
	 * @generated
	 * @ordered
	 */
	protected static final int LOADS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getLoads() <em>Loads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoads()
	 * @generated
	 * @ordered
	 */
	protected int loads = LOADS_EDEFAULT;
	/**
	 * The default value of the '{@link #getUnloads() <em>Unloads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnloads()
	 * @generated
	 * @ordered
	 */
	protected static final int UNLOADS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getUnloads() <em>Unloads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnloads()
	 * @generated
	 * @ordered
	 */
	protected int unloads = UNLOADS_EDEFAULT;
	/**
	 * The default value of the '{@link #getMaxActiveFagments() <em>Max Active Fagments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActiveFagments()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_ACTIVE_FAGMENTS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getMaxActiveFagments() <em>Max Active Fagments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActiveFagments()
	 * @generated
	 * @ordered
	 */
	protected int maxActiveFagments = MAX_ACTIVE_FAGMENTS_EDEFAULT;
	/**
	 * The default value of the '{@link #getMaxWeakFragments() <em>Max Weak Fragments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWeakFragments()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_WEAK_FRAGMENTS_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getMaxWeakFragments() <em>Max Weak Fragments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWeakFragments()
	 * @generated
	 * @ordered
	 */
	protected int maxWeakFragments = MAX_WEAK_FRAGMENTS_EDEFAULT;
	/**
	 * The default value of the '{@link #getRemarks() <em>Remarks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemarks()
	 * @generated
	 * @ordered
	 */
	protected static final String REMARKS_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getRemarks() <em>Remarks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemarks()
	 * @generated
	 * @ordered
	 */
	protected String remarks = REMARKS_EDEFAULT;

				/**
	 * The default value of the '{@link #getPModelName() <em>PModel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPModelName()
	 * @generated
	 * @ordered
	 */
	protected static final String PMODEL_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getPModelName() <em>PModel Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPModelName()
	 * @generated
	 * @ordered
	 */
	protected String pModelName = PMODEL_NAME_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected BenchmarkImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.BENCHMARK;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getRMemoryUsage() {
		return rMemoryUsage;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRMemoryUsage(long newRMemoryUsage) {
		long oldRMemoryUsage = rMemoryUsage;
		rMemoryUsage = newRMemoryUsage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__RMEMORY_USAGE, oldRMemoryUsage, rMemoryUsage));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getRTime() {
		return rTime;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRTime(long newRTime) {
		long oldRTime = rTime;
		rTime = newRTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__RTIME, oldRTime, rTime));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRNumberOfObjects() {
		return rNumberOfObjects;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRNumberOfObjects(int newRNumberOfObjects) {
		int oldRNumberOfObjects = rNumberOfObjects;
		rNumberOfObjects = newRNumberOfObjects;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__RNUMBER_OF_OBJECTS, oldRNumberOfObjects, rNumberOfObjects));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWeakUnloads() {
		return weakUnloads;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeakUnloads(int newWeakUnloads) {
		int oldWeakUnloads = weakUnloads;
		weakUnloads = newWeakUnloads;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__WEAK_UNLOADS, oldWeakUnloads, weakUnloads));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWeakLoads() {
		return weakLoads;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeakLoads(int newWeakLoads) {
		int oldWeakLoads = weakLoads;
		weakLoads = newWeakLoads;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__WEAK_LOADS, oldWeakLoads, weakLoads));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLoads() {
		return loads;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoads(int newLoads) {
		int oldLoads = loads;
		loads = newLoads;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__LOADS, oldLoads, loads));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUnloads() {
		return unloads;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnloads(int newUnloads) {
		int oldUnloads = unloads;
		unloads = newUnloads;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__UNLOADS, oldUnloads, unloads));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxActiveFagments() {
		return maxActiveFagments;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxActiveFagments(int newMaxActiveFagments) {
		int oldMaxActiveFagments = maxActiveFagments;
		maxActiveFagments = newMaxActiveFagments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__MAX_ACTIVE_FAGMENTS, oldMaxActiveFagments, maxActiveFagments));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxWeakFragments() {
		return maxWeakFragments;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxWeakFragments(int newMaxWeakFragments) {
		int oldMaxWeakFragments = maxWeakFragments;
		maxWeakFragments = newMaxWeakFragments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__MAX_WEAK_FRAGMENTS, oldMaxWeakFragments, maxWeakFragments));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemarks() {
		return remarks;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemarks(String newRemarks) {
		String oldRemarks = remarks;
		remarks = newRemarks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__REMARKS, oldRemarks, remarks));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPModelName() {
		return pModelName;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPModelName(String newPModelName) {
		String oldPModelName = pModelName;
		pModelName = newPModelName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.BENCHMARK__PMODEL_NAME, oldPModelName, pModelName));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void run(EMFFragCandidates candidate) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    private long startTime = 0;
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void startTimeMeasure() {
        startTime = System.currentTimeMillis();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public long getTimeMeasure() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public long getMemoryMeasure() {
        long start = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        long result = runtime.totalMemory() - runtime.freeMemory();
        long end = System.currentTimeMillis();
        this.startTime += (end - start);
        return result;
    }

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.BENCHMARK__RMEMORY_USAGE:
				return getRMemoryUsage();
			case BenchmarkPackage.BENCHMARK__RTIME:
				return getRTime();
			case BenchmarkPackage.BENCHMARK__RNUMBER_OF_OBJECTS:
				return getRNumberOfObjects();
			case BenchmarkPackage.BENCHMARK__WEAK_UNLOADS:
				return getWeakUnloads();
			case BenchmarkPackage.BENCHMARK__WEAK_LOADS:
				return getWeakLoads();
			case BenchmarkPackage.BENCHMARK__LOADS:
				return getLoads();
			case BenchmarkPackage.BENCHMARK__UNLOADS:
				return getUnloads();
			case BenchmarkPackage.BENCHMARK__MAX_ACTIVE_FAGMENTS:
				return getMaxActiveFagments();
			case BenchmarkPackage.BENCHMARK__MAX_WEAK_FRAGMENTS:
				return getMaxWeakFragments();
			case BenchmarkPackage.BENCHMARK__REMARKS:
				return getRemarks();
			case BenchmarkPackage.BENCHMARK__PMODEL_NAME:
				return getPModelName();
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
			case BenchmarkPackage.BENCHMARK__RMEMORY_USAGE:
				setRMemoryUsage((Long)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__RTIME:
				setRTime((Long)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__RNUMBER_OF_OBJECTS:
				setRNumberOfObjects((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__WEAK_UNLOADS:
				setWeakUnloads((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__WEAK_LOADS:
				setWeakLoads((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__LOADS:
				setLoads((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__UNLOADS:
				setUnloads((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__MAX_ACTIVE_FAGMENTS:
				setMaxActiveFagments((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__MAX_WEAK_FRAGMENTS:
				setMaxWeakFragments((Integer)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__REMARKS:
				setRemarks((String)newValue);
				return;
			case BenchmarkPackage.BENCHMARK__PMODEL_NAME:
				setPModelName((String)newValue);
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
			case BenchmarkPackage.BENCHMARK__RMEMORY_USAGE:
				setRMemoryUsage(RMEMORY_USAGE_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__RTIME:
				setRTime(RTIME_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__RNUMBER_OF_OBJECTS:
				setRNumberOfObjects(RNUMBER_OF_OBJECTS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__WEAK_UNLOADS:
				setWeakUnloads(WEAK_UNLOADS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__WEAK_LOADS:
				setWeakLoads(WEAK_LOADS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__LOADS:
				setLoads(LOADS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__UNLOADS:
				setUnloads(UNLOADS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__MAX_ACTIVE_FAGMENTS:
				setMaxActiveFagments(MAX_ACTIVE_FAGMENTS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__MAX_WEAK_FRAGMENTS:
				setMaxWeakFragments(MAX_WEAK_FRAGMENTS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__REMARKS:
				setRemarks(REMARKS_EDEFAULT);
				return;
			case BenchmarkPackage.BENCHMARK__PMODEL_NAME:
				setPModelName(PMODEL_NAME_EDEFAULT);
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
			case BenchmarkPackage.BENCHMARK__RMEMORY_USAGE:
				return rMemoryUsage != RMEMORY_USAGE_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__RTIME:
				return rTime != RTIME_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__RNUMBER_OF_OBJECTS:
				return rNumberOfObjects != RNUMBER_OF_OBJECTS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__WEAK_UNLOADS:
				return weakUnloads != WEAK_UNLOADS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__WEAK_LOADS:
				return weakLoads != WEAK_LOADS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__LOADS:
				return loads != LOADS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__UNLOADS:
				return unloads != UNLOADS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__MAX_ACTIVE_FAGMENTS:
				return maxActiveFagments != MAX_ACTIVE_FAGMENTS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__MAX_WEAK_FRAGMENTS:
				return maxWeakFragments != MAX_WEAK_FRAGMENTS_EDEFAULT;
			case BenchmarkPackage.BENCHMARK__REMARKS:
				return REMARKS_EDEFAULT == null ? remarks != null : !REMARKS_EDEFAULT.equals(remarks);
			case BenchmarkPackage.BENCHMARK__PMODEL_NAME:
				return PMODEL_NAME_EDEFAULT == null ? pModelName != null : !PMODEL_NAME_EDEFAULT.equals(pModelName);
		}
		return super.eIsSet(featureID);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (rMemoryUsage: ");
		result.append(rMemoryUsage);
		result.append(", rTime: ");
		result.append(rTime);
		result.append(", rNumberOfObjects: ");
		result.append(rNumberOfObjects);
		result.append(", weakUnloads: ");
		result.append(weakUnloads);
		result.append(", weakLoads: ");
		result.append(weakLoads);
		result.append(", loads: ");
		result.append(loads);
		result.append(", unloads: ");
		result.append(unloads);
		result.append(", maxActiveFagments: ");
		result.append(maxActiveFagments);
		result.append(", maxWeakFragments: ");
		result.append(maxWeakFragments);
		result.append(", remarks: ");
		result.append(remarks);
		result.append(", pModelName: ");
		result.append(pModelName);
		result.append(')');
		return result.toString();
	}
	
	@Override
    public void printResult(PrintStream out, EMFFragCandidates candidate, int run) {
        out.print(getPModelName()); out.print(" ");
        out.print(getRemarks()); out.print(" ");
        out.print(candidate.getValue()); out.print(" ");
        out.print(getRNumberOfObjects()); out.print(" ");
        out.print(getRTime()); out.print(" ");
        out.print(getRMemoryUsage()); out.print(" ");
        out.print(getMaxActiveFagments()); out.print(" ");
        out.print(getMaxWeakFragments()); out.print(" ");
        out.print(getLoads()); out.print(" ");
        out.print(getUnloads()); out.print(" ");
        out.print(getWeakLoads()); out.print(" ");
        out.print(getWeakUnloads()); out.print("\n");
    }

    @Override
    public void printResultHeader(PrintStream out) {
        out.println("#modelName remarks candidate noo time mem maxActiveFragments maxWeakFragments loads unloads weakLoads weakUnloads");
    }
    
    protected class MyGauge implements AbstractImpl.Gauge {
        long start = 0;
        @Override
        public void takeTimeMeasure() {
            setRTime(System.currentTimeMillis() - start);
        }
        
        @Override
        public void takeMemoryMeasure() {
            long start = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();
            System.gc();
            long result = runtime.totalMemory() - runtime.freeMemory();
            long end = System.currentTimeMillis();
            this.start += (end - start);
            setRMemoryUsage(Math.max(result, getRMemoryUsage()));
        }
        
        @Override
        public void startTimeMeasure() {
            start = System.currentTimeMillis();
        }
    }

} //BenchmarkImpl
