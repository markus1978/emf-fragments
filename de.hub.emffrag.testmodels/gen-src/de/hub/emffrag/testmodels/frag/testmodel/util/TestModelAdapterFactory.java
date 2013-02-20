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
package de.hub.emffrag.testmodels.frag.testmodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.model.emffrag.ContainmentIndexedMap;
import de.hub.emffrag.model.emffrag.IndexedMap;
import de.hub.emffrag.testmodels.frag.testmodel.Container;
import de.hub.emffrag.testmodels.frag.testmodel.Contents;
import de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex;
import de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage;
import de.hub.emffrag.testmodels.frag.testmodel.TestObject;
import de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage
 * @generated
 */
public class TestModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TestModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestModelSwitch<Adapter> modelSwitch =
		new TestModelSwitch<Adapter>() {
			@Override
			public Adapter caseContainer(Container object) {
				return createContainerAdapter();
			}
			@Override
			public Adapter caseContents(Contents object) {
				return createContentsAdapter();
			}
			@Override
			public Adapter caseTestObject(TestObject object) {
				return createTestObjectAdapter();
			}
			@Override
			public Adapter caseTestObjectIndex(TestObjectIndex object) {
				return createTestObjectIndexAdapter();
			}
			@Override
			public Adapter caseTestContainmentIndex(TestContainmentIndex object) {
				return createTestContainmentIndexAdapter();
			}
			@Override
			public <K, V> Adapter caseIndexedMap(IndexedMap<K, V> object) {
				return createIndexedMapAdapter();
			}
			@Override
			public <K, V> Adapter caseContainmentIndexedMap(ContainmentIndexedMap<K, V> object) {
				return createContainmentIndexedMapAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.testmodels.frag.testmodel.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.Container
	 * @generated
	 */
	public Adapter createContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.testmodels.frag.testmodel.Contents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.Contents
	 * @generated
	 */
	public Adapter createContentsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObject <em>Test Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObject
	 * @generated
	 */
	public Adapter createTestObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex <em>Test Object Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex
	 * @generated
	 */
	public Adapter createTestObjectIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex <em>Test Containment Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex
	 * @generated
	 */
	public Adapter createTestContainmentIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.model.emffrag.IndexedMap <em>Indexed Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.model.emffrag.IndexedMap
	 * @generated
	 */
	public Adapter createIndexedMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.hub.emffrag.model.emffrag.ContainmentIndexedMap <em>Containment Indexed Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.hub.emffrag.model.emffrag.ContainmentIndexedMap
	 * @generated
	 */
	public Adapter createContainmentIndexedMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TestModelAdapterFactory
