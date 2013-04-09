/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.fragmentation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FInternal Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.fragmentation.FInternalObject#getId <em>Id</em>}</li>
 *   <li>{@link de.hub.emffrag.fragmentation.FInternalObject#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link de.hub.emffrag.fragmentation.FInternalObject#getAccessed <em>Accessed</em>}</li>
 *   <li>{@link de.hub.emffrag.fragmentation.FInternalObject#getLoaded <em>Loaded</em>}</li>
 *   <li>{@link de.hub.emffrag.fragmentation.FInternalObject#getIndexes <em>Indexes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.fragmentation.InternalPackage#getFInternalObject()
 * @model
 * @generated
 */
public interface FInternalObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.hub.emffrag.fragmentation.InternalPackage#getFInternalObject_Id()
	 * @model id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.fragmentation.FInternalObject#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensions</em>' containment reference list.
	 * @see de.hub.emffrag.fragmentation.InternalPackage#getFInternalObject_Extensions()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<EObject> getExtensions();

	/**
	 * Returns the value of the '<em><b>Accessed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accessed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accessed</em>' attribute.
	 * @see #setAccessed(long)
	 * @see de.hub.emffrag.fragmentation.InternalPackage#getFInternalObject_Accessed()
	 * @model
	 * @generated
	 */
	long getAccessed();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.fragmentation.FInternalObject#getAccessed <em>Accessed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accessed</em>' attribute.
	 * @see #getAccessed()
	 * @generated
	 */
	void setAccessed(long value);

	/**
	 * Returns the value of the '<em><b>Loaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loaded</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loaded</em>' attribute.
	 * @see #setLoaded(long)
	 * @see de.hub.emffrag.fragmentation.InternalPackage#getFInternalObject_Loaded()
	 * @model
	 * @generated
	 */
	long getLoaded();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.fragmentation.FInternalObject#getLoaded <em>Loaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loaded</em>' attribute.
	 * @see #getLoaded()
	 * @generated
	 */
	void setLoaded(long value);

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Long}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' attribute list.
	 * @see de.hub.emffrag.fragmentation.InternalPackage#getFInternalObject_Indexes()
	 * @model unique="false"
	 * @generated
	 */
	EList<Long> getIndexes();

} // FInternalObject
