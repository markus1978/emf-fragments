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

import de.hub.emffrag.bm.benchmark.AbstractGrabatsBenchmark;
import de.hub.emffrag.bm.benchmark.BenchmarkPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Grabats Benchmark</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.AbstractGrabatsBenchmarkImpl#getPSourceXmiURI <em>PSource Xmi URI</em>}</li>
 *   <li>{@link de.hub.emffrag.bm.benchmark.impl.AbstractGrabatsBenchmarkImpl#isFrag2 <em>Frag2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractGrabatsBenchmarkImpl extends BenchmarkImpl implements AbstractGrabatsBenchmark {
    
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * The default value of the '{@link #getPSourceXmiURI() <em>PSource Xmi URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPSourceXmiURI()
	 * @generated
	 * @ordered
	 */
    protected static final String PSOURCE_XMI_URI_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getPSourceXmiURI() <em>PSource Xmi URI</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPSourceXmiURI()
	 * @generated
	 * @ordered
	 */
    protected String pSourceXmiURI = PSOURCE_XMI_URI_EDEFAULT;

    /**
	 * The default value of the '{@link #isFrag2() <em>Frag2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFrag2()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FRAG2_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isFrag2() <em>Frag2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFrag2()
	 * @generated
	 * @ordered
	 */
	protected boolean frag2 = FRAG2_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected AbstractGrabatsBenchmarkImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return BenchmarkPackage.Literals.ABSTRACT_GRABATS_BENCHMARK;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getPSourceXmiURI() {
		return pSourceXmiURI;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPSourceXmiURI(String newPSourceXmiURI) {
		String oldPSourceXmiURI = pSourceXmiURI;
		pSourceXmiURI = newPSourceXmiURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI, oldPSourceXmiURI, pSourceXmiURI));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFrag2() {
		return frag2;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrag2(boolean newFrag2) {
		boolean oldFrag2 = frag2;
		frag2 = newFrag2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__FRAG2, oldFrag2, frag2));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI:
				return getPSourceXmiURI();
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__FRAG2:
				return isFrag2();
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
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI:
				setPSourceXmiURI((String)newValue);
				return;
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__FRAG2:
				setFrag2((Boolean)newValue);
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
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI:
				setPSourceXmiURI(PSOURCE_XMI_URI_EDEFAULT);
				return;
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__FRAG2:
				setFrag2(FRAG2_EDEFAULT);
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
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__PSOURCE_XMI_URI:
				return PSOURCE_XMI_URI_EDEFAULT == null ? pSourceXmiURI != null : !PSOURCE_XMI_URI_EDEFAULT.equals(pSourceXmiURI);
			case BenchmarkPackage.ABSTRACT_GRABATS_BENCHMARK__FRAG2:
				return frag2 != FRAG2_EDEFAULT;
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
		result.append(" (pSourceXmiURI: ");
		result.append(pSourceXmiURI);
		result.append(", frag2: ");
		result.append(frag2);
		result.append(')');
		return result.toString();
	}

} //AbstractGrabatsBenchmarkImpl
