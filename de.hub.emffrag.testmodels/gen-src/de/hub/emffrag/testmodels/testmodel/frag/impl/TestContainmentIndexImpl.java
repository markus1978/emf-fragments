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

import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.datastore.KeyType;
import de.hub.emffrag.datastore.StringKeyType;
import de.hub.emffrag.model.emffrag.impl.ContainmentIndexedMapImpl;
import de.hub.emffrag.testmodels.testmodel.TestContainmentIndex;
import de.hub.emffrag.testmodels.testmodel.TestObject;
import de.hub.emffrag.testmodels.testmodel.frag.meta.TestModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Containment Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class TestContainmentIndexImpl extends ContainmentIndexedMapImpl<String, TestObject> implements TestContainmentIndex {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestContainmentIndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestModelPackage.Literals.TEST_CONTAINMENT_INDEX;
	}
	
	@Override
	public KeyType<String> getKeytype() {
		return StringKeyType.instance;
	}

} //TestContainmentIndexImpl
