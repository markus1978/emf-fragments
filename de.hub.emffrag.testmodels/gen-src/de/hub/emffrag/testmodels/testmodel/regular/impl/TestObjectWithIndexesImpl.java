/**
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hub.emffrag.testmodels.testmodel.regular.impl;

import de.hub.emffrag.testmodels.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Object With Indexes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectWithIndexesImpl#getIndexedReferences <em>Indexed References</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectWithIndexesImpl#getIndexedContents <em>Indexed Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestObjectWithIndexesImpl extends TestObjectImpl implements TestObjectWithIndexes {
	/**
	 * The cached value of the '{@link #getIndexedReferences() <em>Indexed References</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<TestObject> indexedReferences;

	/**
	 * The cached value of the '{@link #getIndexedContents() <em>Indexed Contents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedContents()
	 * @generated
	 * @ordered
	 */
	protected EList<TestObject> indexedContents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestObjectWithIndexesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestModelPackage.Literals.TEST_OBJECT_WITH_INDEXES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObject> getIndexedReferences() {
		if (indexedReferences == null) {
			indexedReferences = new EObjectResolvingEList<TestObject>(TestObject.class, this, TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES);
		}
		return indexedReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObject> getIndexedContents() {
		if (indexedContents == null) {
			indexedContents = new EObjectContainmentEList<TestObject>(TestObject.class, this, TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS);
		}
		return indexedContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS:
				return ((InternalEList<?>)getIndexedContents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES:
				return getIndexedReferences();
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS:
				return getIndexedContents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES:
				getIndexedReferences().clear();
				getIndexedReferences().addAll((Collection<? extends TestObject>)newValue);
				return;
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS:
				getIndexedContents().clear();
				getIndexedContents().addAll((Collection<? extends TestObject>)newValue);
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
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES:
				getIndexedReferences().clear();
				return;
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS:
				getIndexedContents().clear();
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
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES:
				return indexedReferences != null && !indexedReferences.isEmpty();
			case TestModelPackage.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS:
				return indexedContents != null && !indexedContents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestObjectWithIndexesImpl
