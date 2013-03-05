/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.fragmentation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.fragmentation.InternalFactory
 * @model kind="package"
 * @generated
 */
public interface InternalPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "fragmentation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://de.hub.emffrag/emffrag_internal";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "efi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InternalPackage eINSTANCE = de.hub.emffrag.fragmentation.InternalPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.hub.emffrag.fragmentation.FInternalObjectImpl <em>FInternal Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.hub.emffrag.fragmentation.FInternalObjectImpl
	 * @see de.hub.emffrag.fragmentation.InternalPackageImpl#getFInternalObject()
	 * @generated
	 */
	int FINTERNAL_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINTERNAL_OBJECT__ID = 0;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINTERNAL_OBJECT__EXTENSIONS = 1;

	/**
	 * The feature id for the '<em><b>Accessed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINTERNAL_OBJECT__ACCESSED = 2;

	/**
	 * The feature id for the '<em><b>Loaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINTERNAL_OBJECT__LOADED = 3;

	/**
	 * The number of structural features of the '<em>FInternal Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINTERNAL_OBJECT_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link de.hub.emffrag.fragmentation.FInternalObject <em>FInternal Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FInternal Object</em>'.
	 * @see de.hub.emffrag.fragmentation.FInternalObject
	 * @generated
	 */
	EClass getFInternalObject();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.fragmentation.FInternalObject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.hub.emffrag.fragmentation.FInternalObject#getId()
	 * @see #getFInternalObject()
	 * @generated
	 */
	EAttribute getFInternalObject_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link de.hub.emffrag.fragmentation.FInternalObject#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extensions</em>'.
	 * @see de.hub.emffrag.fragmentation.FInternalObject#getExtensions()
	 * @see #getFInternalObject()
	 * @generated
	 */
	EReference getFInternalObject_Extensions();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.fragmentation.FInternalObject#getAccessed <em>Accessed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accessed</em>'.
	 * @see de.hub.emffrag.fragmentation.FInternalObject#getAccessed()
	 * @see #getFInternalObject()
	 * @generated
	 */
	EAttribute getFInternalObject_Accessed();

	/**
	 * Returns the meta object for the attribute '{@link de.hub.emffrag.fragmentation.FInternalObject#getLoaded <em>Loaded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loaded</em>'.
	 * @see de.hub.emffrag.fragmentation.FInternalObject#getLoaded()
	 * @see #getFInternalObject()
	 * @generated
	 */
	EAttribute getFInternalObject_Loaded();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	InternalFactory getInternalFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.hub.emffrag.fragmentation.FInternalObjectImpl <em>FInternal Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.hub.emffrag.fragmentation.FInternalObjectImpl
		 * @see de.hub.emffrag.fragmentation.InternalPackageImpl#getFInternalObject()
		 * @generated
		 */
		EClass FINTERNAL_OBJECT = eINSTANCE.getFInternalObject();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINTERNAL_OBJECT__ID = eINSTANCE.getFInternalObject_Id();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FINTERNAL_OBJECT__EXTENSIONS = eINSTANCE.getFInternalObject_Extensions();

		/**
		 * The meta object literal for the '<em><b>Accessed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINTERNAL_OBJECT__ACCESSED = eINSTANCE.getFInternalObject_Accessed();

		/**
		 * The meta object literal for the '<em><b>Loaded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINTERNAL_OBJECT__LOADED = eINSTANCE.getFInternalObject_Loaded();

	}

} //InternalPackage
