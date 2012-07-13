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
package de.hub.emffrag.testmodels.regular.Core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IImport Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.IImportDeclaration#getIsOnDemand <em>Is On Demand</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.regular.Core.IImportDeclaration#getIsStatic <em>Is Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIImportDeclaration()
 * @model
 * @generated
 */
public interface IImportDeclaration extends IJavaElement, ISourceReference {
	/**
	 * Returns the value of the '<em><b>Is On Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is On Demand</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is On Demand</em>' attribute.
	 * @see #setIsOnDemand(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIImportDeclaration_IsOnDemand()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsOnDemand();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.IImportDeclaration#getIsOnDemand <em>Is On Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is On Demand</em>' attribute.
	 * @see #getIsOnDemand()
	 * @generated
	 */
	void setIsOnDemand(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Static</em>' attribute.
	 * @see #setIsStatic(Boolean)
	 * @see de.hub.emffrag.testmodels.regular.Core.CorePackage#getIImportDeclaration_IsStatic()
	 * @model unique="false" dataType="de.hub.emffrag.testmodels.regular.PrimitiveTypes.Boolean" required="true" ordered="false"
	 * @generated
	 */
	Boolean getIsStatic();

	/**
	 * Sets the value of the '{@link de.hub.emffrag.testmodels.regular.Core.IImportDeclaration#getIsStatic <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Static</em>' attribute.
	 * @see #getIsStatic()
	 * @generated
	 */
	void setIsStatic(Boolean value);

} // IImportDeclaration
