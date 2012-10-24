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
package de.hub.emffrag.testmodels.reflective.Core.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.hub.emffrag.testmodels.reflective.Core.CorePackage;
import de.hub.emffrag.testmodels.reflective.Core.ISourceRange;
import de.hub.emffrag.testmodels.reflective.Core.ISourceReference;
import de.hub.emffrag.testmodels.reflective.Core.ITypeParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IType Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.ITypeParameterImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.ITypeParameterImpl#getSourceRange <em>Source Range</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.reflective.Core.impl.ITypeParameterImpl#getBounds <em>Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ITypeParameterImpl extends IJavaElementImpl implements ITypeParameter {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ITypeParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ITYPE_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSource() {
		return (String)eGet(CorePackage.Literals.ISOURCE_REFERENCE__SOURCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(String newSource) {
		eSet(CorePackage.Literals.ISOURCE_REFERENCE__SOURCE, newSource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ISourceRange getSourceRange() {
		return (ISourceRange)eGet(CorePackage.Literals.ISOURCE_REFERENCE__SOURCE_RANGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceRange(ISourceRange newSourceRange) {
		eSet(CorePackage.Literals.ISOURCE_REFERENCE__SOURCE_RANGE, newSourceRange);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<String> getBounds() {
		return (EList<String>)eGet(CorePackage.Literals.ITYPE_PARAMETER__BOUNDS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ISourceReference.class) {
			switch (derivedFeatureID) {
				case CorePackage.ITYPE_PARAMETER__SOURCE: return CorePackage.ISOURCE_REFERENCE__SOURCE;
				case CorePackage.ITYPE_PARAMETER__SOURCE_RANGE: return CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ISourceReference.class) {
			switch (baseFeatureID) {
				case CorePackage.ISOURCE_REFERENCE__SOURCE: return CorePackage.ITYPE_PARAMETER__SOURCE;
				case CorePackage.ISOURCE_REFERENCE__SOURCE_RANGE: return CorePackage.ITYPE_PARAMETER__SOURCE_RANGE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ITypeParameterImpl
