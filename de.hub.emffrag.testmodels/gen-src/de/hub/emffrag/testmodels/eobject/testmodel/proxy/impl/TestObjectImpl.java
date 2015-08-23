/**
 */
package de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl;

import de.hub.emffrag.proxies.ProxyEObjectImpl;

import de.hub.emffrag.testmodels.eobject.testmodel.TestEnum;
import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;

import de.hub.emffrag.testmodels.eobject.testmodel.proxy.meta.TestmodelPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestObjectImpl#getRegularContents <em>Regular Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestObjectImpl#getFragmentedContents <em>Fragmented Contents</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestObjectImpl#getCrossReferences <em>Cross References</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestObjectImpl#getEnumAttribute <em>Enum Attribute</em>}</li>
 *   <li>{@link de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestObjectImpl#getArbitraryContents <em>Arbitrary Contents</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestObjectImpl extends ProxyEObjectImpl implements TestObject {
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
		return TestmodelPackage.Literals.TEST_OBJECT;
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
		return (String)eGet(TestmodelPackage.Literals.TEST_OBJECT__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(TestmodelPackage.Literals.TEST_OBJECT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getRegularContents() {
		return (EList<TestObject>)eGet(TestmodelPackage.Literals.TEST_OBJECT__REGULAR_CONTENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getFragmentedContents() {
		return (EList<TestObject>)eGet(TestmodelPackage.Literals.TEST_OBJECT__FRAGMENTED_CONTENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<TestObject> getCrossReferences() {
		return (EList<TestObject>)eGet(TestmodelPackage.Literals.TEST_OBJECT__CROSS_REFERENCES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestEnum getEnumAttribute() {
		return (TestEnum)eGet(TestmodelPackage.Literals.TEST_OBJECT__ENUM_ATTRIBUTE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumAttribute(TestEnum newEnumAttribute) {
		eSet(TestmodelPackage.Literals.TEST_OBJECT__ENUM_ATTRIBUTE, newEnumAttribute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<EObject> getArbitraryContents() {
		return (EList<EObject>)eGet(TestmodelPackage.Literals.TEST_OBJECT__ARBITRARY_CONTENTS, true);
	}

} //TestObjectImpl
