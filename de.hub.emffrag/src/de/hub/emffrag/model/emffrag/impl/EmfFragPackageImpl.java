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
package de.hub.emffrag.model.emffrag.impl;

import de.hub.emffrag.model.emffrag.EmfFragFactory;
import de.hub.emffrag.model.emffrag.EmfFragPackage;
import de.hub.emffrag.model.emffrag.IndexedList;
import de.hub.emffrag.model.emffrag.IndexedMap;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EmfFragPackageImpl extends EPackageImpl implements EmfFragPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexedMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexedListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iteratorEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.hub.emffrag.model.emffrag.EmfFragPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EmfFragPackageImpl() {
		super(eNS_URI, EmfFragFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link EmfFragPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EmfFragPackage init() {
		if (isInited) return (EmfFragPackage)EPackage.Registry.INSTANCE.getEPackage(EmfFragPackage.eNS_URI);

		// Obtain or create and register package
		EmfFragPackageImpl theEmfFragPackage = (EmfFragPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EmfFragPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EmfFragPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theEmfFragPackage.createPackageContents();

		// Initialize created meta-data
		theEmfFragPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEmfFragPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EmfFragPackage.eNS_URI, theEmfFragPackage);
		return theEmfFragPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexedMap() {
		return indexedMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexedList() {
		return indexedListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIterator() {
		return iteratorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmfFragFactory getEmfFragFactory() {
		return (EmfFragFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		indexedMapEClass = createEClass(INDEXED_MAP);

		indexedListEClass = createEClass(INDEXED_LIST);

		// Create data types
		iteratorEDataType = createEDataType(ITERATOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		ETypeParameter indexedMapEClass_K = addETypeParameter(indexedMapEClass, "K");
		ETypeParameter indexedMapEClass_V = addETypeParameter(indexedMapEClass, "V");
		ETypeParameter indexedListEClass_V = addETypeParameter(indexedListEClass, "V");
		addETypeParameter(iteratorEDataType, "E");

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(this.getIndexedMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEIntegerObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(indexedListEClass_V);
		g1.getETypeArguments().add(g2);
		indexedListEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes and features; add operations and parameters
		initEClass(indexedMapEClass, IndexedMap.class, "IndexedMap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = addEOperation(indexedMapEClass, null, "iterator", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getIterator());
		g2 = createEGenericType(indexedMapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(indexedMapEClass, null, "iterator", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_K);
		addEParameter(op, g1, "from", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_K);
		addEParameter(op, g1, "to", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getIterator());
		g2 = createEGenericType(indexedMapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(indexedMapEClass, null, "exact", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_K);
		addEParameter(op, g1, "key", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_V);
		initEOperation(op, g1);

		op = addEOperation(indexedMapEClass, null, "next", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_K);
		addEParameter(op, g1, "key", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_V);
		initEOperation(op, g1);

		op = addEOperation(indexedMapEClass, null, "put", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_K);
		addEParameter(op, g1, "key", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedMapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(indexedListEClass, IndexedList.class, "IndexedList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(indexedListEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(indexedListEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(iteratorEDataType, Iterator.class, "Iterator", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //EmfFragPackageImpl
