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
package de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl;

import de.hub.emffrag.testmodels.AccessNotifyingTestEObjectImpl;

import de.hub.emffrag.testmodels.eobject.testmodel.TestEnum;
import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;

import de.hub.emffrag.testmodels.eobject.testmodel.fobject.meta.TestModelPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl.TestObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl.TestObjectImpl#getRegularContents <em>Regular Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl.TestObjectImpl#getFragmentedContents <em>Fragmented Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl.TestObjectImpl#getCrossReferences <em>Cross References</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl.TestObjectImpl#getEnumAttribute <em>Enum Attribute</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.fobject.impl.TestObjectImpl#getArbitraryContents <em>Arbitrary Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestObjectImpl extends AccessNotifyingTestEObjectImpl implements TestObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestModelPackage.Literals.TEST_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(TestModelPackage.Literals.TEST_OBJECT__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(TestModelPackage.Literals.TEST_OBJECT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getRegularContents() {
		return (EList<TestObject>)eGet(TestModelPackage.Literals.TEST_OBJECT__REGULAR_CONTENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getFragmentedContents() {
		return (EList<TestObject>)eGet(TestModelPackage.Literals.TEST_OBJECT__FRAGMENTED_CONTENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getCrossReferences() {
		return (EList<TestObject>)eGet(TestModelPackage.Literals.TEST_OBJECT__CROSS_REFERENCES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestEnum getEnumAttribute() {
		return (TestEnum)eGet(TestModelPackage.Literals.TEST_OBJECT__ENUM_ATTRIBUTE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumAttribute(TestEnum newEnumAttribute) {
		eSet(TestModelPackage.Literals.TEST_OBJECT__ENUM_ATTRIBUTE, newEnumAttribute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<EObject> getArbitraryContents() {
		return (EList<EObject>)eGet(TestModelPackage.Literals.TEST_OBJECT__ARBITRARY_CONTENTS, true);
	}

} //TestObjectImpl
