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
package de.hub.emffrag.testmodels.testmodel.frag.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Object With Indexes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.frag.impl.TestObjectWithIndexesImpl#getIndexedReferences <em>Indexed References</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.testmodel.frag.impl.TestObjectWithIndexesImpl#getIndexedContents <em>Indexed Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestObjectWithIndexesImpl extends TestObjectImpl implements TestObjectWithIndexes {
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
	@SuppressWarnings("unchecked")
	public EList<TestObject> getIndexedReferences() {
		return (EList<TestObject>)eGet(TestModelPackage.Literals.TEST_OBJECT_WITH_INDEXES__INDEXED_REFERENCES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getIndexedContents() {
		return (EList<TestObject>)eGet(TestModelPackage.Literals.TEST_OBJECT_WITH_INDEXES__INDEXED_CONTENTS, true);
	}

} //TestObjectWithIndexesImpl
