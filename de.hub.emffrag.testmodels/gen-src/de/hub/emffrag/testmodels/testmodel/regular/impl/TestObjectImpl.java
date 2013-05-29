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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.hub.emffrag.testmodels.testmodel.TestEnum;
import de.hub.emffrag.testmodels.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.testmodel.TestObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectImpl#getRegularContents <em>Regular Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectImpl#getFragmentedContents <em>Fragmented Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectImpl#getCrossReferences <em>Cross References</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.regular.impl.TestObjectImpl#getEnumAttribute <em>Enum Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestObjectImpl extends EObjectImpl implements TestObject {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRegularContents() <em>Regular Contents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegularContents()
	 * @generated
	 * @ordered
	 */
	protected EList<TestObject> regularContents;

	/**
	 * The cached value of the '{@link #getFragmentedContents() <em>Fragmented Contents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragmentedContents()
	 * @generated
	 * @ordered
	 */
	protected EList<TestObject> fragmentedContents;

	/**
	 * The cached value of the '{@link #getCrossReferences() <em>Cross References</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<TestObject> crossReferences;

	/**
	 * The default value of the '{@link #getEnumAttribute() <em>Enum Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final TestEnum ENUM_ATTRIBUTE_EDEFAULT = TestEnum.LITERAL1;

	/**
	 * The cached value of the '{@link #getEnumAttribute() <em>Enum Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumAttribute()
	 * @generated
	 * @ordered
	 */
	protected TestEnum enumAttribute = ENUM_ATTRIBUTE_EDEFAULT;

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
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestModelPackage.TEST_OBJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObject> getRegularContents() {
		if (regularContents == null) {
			regularContents = new EObjectContainmentEList<TestObject>(TestObject.class, this, TestModelPackage.TEST_OBJECT__REGULAR_CONTENTS);
		}
		return regularContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObject> getFragmentedContents() {
		if (fragmentedContents == null) {
			fragmentedContents = new EObjectContainmentEList<TestObject>(TestObject.class, this, TestModelPackage.TEST_OBJECT__FRAGMENTED_CONTENTS);
		}
		return fragmentedContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObject> getCrossReferences() {
		if (crossReferences == null) {
			crossReferences = new EObjectResolvingEList<TestObject>(TestObject.class, this, TestModelPackage.TEST_OBJECT__CROSS_REFERENCES);
		}
		return crossReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestEnum getEnumAttribute() {
		return enumAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumAttribute(TestEnum newEnumAttribute) {
		TestEnum oldEnumAttribute = enumAttribute;
		enumAttribute = newEnumAttribute == null ? ENUM_ATTRIBUTE_EDEFAULT : newEnumAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestModelPackage.TEST_OBJECT__ENUM_ATTRIBUTE, oldEnumAttribute, enumAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestModelPackage.TEST_OBJECT__REGULAR_CONTENTS:
				return ((InternalEList<?>)getRegularContents()).basicRemove(otherEnd, msgs);
			case TestModelPackage.TEST_OBJECT__FRAGMENTED_CONTENTS:
				return ((InternalEList<?>)getFragmentedContents()).basicRemove(otherEnd, msgs);
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
			case TestModelPackage.TEST_OBJECT__NAME:
				return getName();
			case TestModelPackage.TEST_OBJECT__REGULAR_CONTENTS:
				return getRegularContents();
			case TestModelPackage.TEST_OBJECT__FRAGMENTED_CONTENTS:
				return getFragmentedContents();
			case TestModelPackage.TEST_OBJECT__CROSS_REFERENCES:
				return getCrossReferences();
			case TestModelPackage.TEST_OBJECT__ENUM_ATTRIBUTE:
				return getEnumAttribute();
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
			case TestModelPackage.TEST_OBJECT__NAME:
				setName((String)newValue);
				return;
			case TestModelPackage.TEST_OBJECT__REGULAR_CONTENTS:
				getRegularContents().clear();
				getRegularContents().addAll((Collection<? extends TestObject>)newValue);
				return;
			case TestModelPackage.TEST_OBJECT__FRAGMENTED_CONTENTS:
				getFragmentedContents().clear();
				getFragmentedContents().addAll((Collection<? extends TestObject>)newValue);
				return;
			case TestModelPackage.TEST_OBJECT__CROSS_REFERENCES:
				getCrossReferences().clear();
				getCrossReferences().addAll((Collection<? extends TestObject>)newValue);
				return;
			case TestModelPackage.TEST_OBJECT__ENUM_ATTRIBUTE:
				setEnumAttribute((TestEnum)newValue);
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
			case TestModelPackage.TEST_OBJECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TestModelPackage.TEST_OBJECT__REGULAR_CONTENTS:
				getRegularContents().clear();
				return;
			case TestModelPackage.TEST_OBJECT__FRAGMENTED_CONTENTS:
				getFragmentedContents().clear();
				return;
			case TestModelPackage.TEST_OBJECT__CROSS_REFERENCES:
				getCrossReferences().clear();
				return;
			case TestModelPackage.TEST_OBJECT__ENUM_ATTRIBUTE:
				setEnumAttribute(ENUM_ATTRIBUTE_EDEFAULT);
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
			case TestModelPackage.TEST_OBJECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TestModelPackage.TEST_OBJECT__REGULAR_CONTENTS:
				return regularContents != null && !regularContents.isEmpty();
			case TestModelPackage.TEST_OBJECT__FRAGMENTED_CONTENTS:
				return fragmentedContents != null && !fragmentedContents.isEmpty();
			case TestModelPackage.TEST_OBJECT__CROSS_REFERENCES:
				return crossReferences != null && !crossReferences.isEmpty();
			case TestModelPackage.TEST_OBJECT__ENUM_ATTRIBUTE:
				return enumAttribute != ENUM_ATTRIBUTE_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", enumAttribute: ");
		result.append(enumAttribute);
		result.append(')');
		return result.toString();
	}

} //TestObjectImpl
